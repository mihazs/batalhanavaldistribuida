/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principalJogador.batalha;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import coordenador.DadosCoordenador;
import java.io.IOException;
import principalJogador.gui.telaPrincipal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response.Status;
import messages.OrdemIp;
import messages.Source;
import messages.request.Request;
import messages.response.Response;
import org.apache.commons.collections4.BidiMap;
import principalJogador.batalha.model.Ataque;
import principalJogador.resources.DadosJogador;
import sockets.client.Client;
import sockets.server.Server;

/**
 *
 * @author ramon
 */
public class Comunicacao {
    private List<Client> clientes;
    private Server servidor;
    private Client clienteCoordenador;
    private Source source;
    private final int portCoordenador = 9987;
    private final int portJogadores = 9957;

    public Comunicacao() {
        clientes = new ArrayList();
        source = new Source();
        
    }
    
    
    
    /*Conexão*/
    public void conectaCoordenador() {
        try {
            clienteCoordenador = new Client(this.source, DadosJogador.ipCoordenador, portCoordenador);
            
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Envios*/
    
  
    
    //Em ataque
    public String enviaAtaque(Ataque ataque) {
        Map<String, Object> map = new HashMap();
        map.put("ataque", ataque);
        Request req = makeRequest("jogador/atacar", map);
        List<Response> res = sendRequestForAll(req);
        String retorno = "errou";
        
        for(Response r:res){
            retorno = String.valueOf(r.getContent());
            if(!retorno.toLowerCase().startsWith("errou")){
                break;
            }
        }
        return retorno;
    }
    
    //Envio de aviso
    public void enviaMorreu(int idMorto) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", idMorto);
        Request req = makeRequest("jogador/notadefalecimento", map);
        sendSimpleRequestForAll(req);
        
    }
     private List<Response> sendRequestForAll(Request req){
        List<Response> responses = new ArrayList<>();
         for(Client c : clientes){
            Response rs[];
            try {
                
                do{
                c.sendRequest(req);
                rs = c.getResponses();
                 
                }while(!rs[0].getStatus().equals(Status.OK));
                responses.add(rs[0]);
            } catch (IOException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
         return responses;
     }
    private void sendSimpleRequestForAll(Request req){
        for(Client c : clientes){
            Response rs[];
            try {
                
                do{
                c.sendRequest(req);
                rs = c.getResponses();
                 
                }while(!rs[0].getStatus().equals(Status.OK));
            } catch (IOException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public void enviaPassarVez() {
        Request req = makeRequest("jogador/passarvez", null);
        sendSimpleRequestForAll(req);
    }

   private Request makeRequest(String path, Map<String, Object> content){
       Request req = new Request();
            req.setResourcePath(path);
            req.setSource(source);
            req.setContent(content);
            return req;
   }

    /*Recebimento*/
    //Conectando
    public String esperaLimites() {
        try {
            //Espera limites do Coordenador
            Request req = makeRequest("lobbyentrada/getmapa", null);
            clienteCoordenador.sendRequest(req);
            Response[] responses = clienteCoordenador.getResponses();
            for(Response r : responses){
                if(r.getStatus().equals(Status.OK)){
                    if(r.getRequest().equals(req)){
                        return String.valueOf(r.getContent());
                    }
                }
            }
           
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "";
    }

    public ArrayList<Integer> esperaOrdem() {
        ArrayList<Integer> listOrdem = null;
        try {
            //Espera ordem de jogada do Coordenador
            clienteCoordenador = new Client(source, DadosJogador.ipCoordenador, portCoordenador+1);
                    Request req = makeRequest("lobbyconfirmacao/escolhi", null);
                    clienteCoordenador.sendRequest(req);
                    Response[] responses = clienteCoordenador.getResponses();
            for(Response r : responses){
                if(r.getStatus().equals(Status.OK)){
                    if(r.getRequest().equals(req)){
                        new Thread(servidor).start();
                        OrdemIp oip = (OrdemIp)(r.getContent());
                        listOrdem = new ArrayList(oip.getOrdem());
                        HashBiMap<Integer, String> mips = HashBiMap.create(oip.getIp());
                        BiMap<String, Integer> bmips = mips.inverse();
                        List<String> ifIps = Client.getInterfaceIps();
                        Integer thisid = null;
                        for(String str:ifIps){
                            
                            if((thisid = bmips.get(str)) != null){
                               servidor = new Server(portJogadores+thisid, str);
                               new Thread(servidor).start();
                               break;
                            }
                        }
                        
                        Thread.sleep(1000);
                               
                        Set<Entry<String, Integer>> ips = bmips.entrySet();
                        for(Entry i: ips){
                            if(i.getValue().equals(thisid))
                                continue;
                            clientes.add(new Client(this.source, String.valueOf(i.getKey()), Integer.parseInt(String.valueOf(i.getValue()))+portJogadores));
                            Thread.sleep(500);
                        }
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrdem;
    }
/*
    //sendo atacado
    public String recebeAcao(int idJogando) {
        //Espera do jogador atual sua ação
        try {    Thread.sleep(300);} catch (InterruptedException ex) {}
        
        return "passarVez";
    }

    
    //Respondendo a ataques
    public void enviaMeAcertou(int ID) {
        //Envia ao atacante a mensagem informando que ele acertou o ataque
        System.out.println("acertou-" + ID);
    }
    
    public void enviaMorri(int ID) {
        //Envia ao atacante a mensagem informando que eu morri
        System.out.println("morri-" + ID);
    }

    public void enviaErrou(int ID) {
        //Envia ao atacante a mensagem informando que ele errou o ataque
        System.out.println("errou-" + ID);
    }*/
    
    
    
}