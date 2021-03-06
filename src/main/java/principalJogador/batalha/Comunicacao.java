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
import java.net.SocketTimeoutException;
import principalJogador.gui.telaPrincipal;
import java.util.ArrayList;
import java.util.Collections;
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
    private Thread tServer;

    public Comunicacao() {
        clientes = new ArrayList();
        source = new Source();
        try {
            servidor = new Server(portJogadores, "principalJogador.resources");
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        for(Response r : res){
            retorno = String.valueOf(r.getContent());
            Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Uma resposta do ataque {0}", retorno);
            if(!retorno.contains("errou"))
           break;
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

    private List<Response> sendRequestForAll(Request req) {
        List<Response> responses = new ArrayList<>();
        clientes.stream().forEach((c) -> {
            Response rs[];
            try {
                do {
                    Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Enviando requisicao {0}", req);
                    c.sendRequest(req);
                    Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Obtendo respostas");
                    rs = c.getResponses();
                    Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Respostas obtidas: \n{0}\n", rs);
                } while (!rs[0].getStatus().equals(Status.OK));
                responses.add(rs[0]);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return responses;
    }

    private void sendSimpleRequestForAll(Request req) {
        clientes.stream().forEach((c) -> {
            Response rs[];
            try {

                do {
                    c.sendRequest(req);
                    rs = c.getResponses();

                } while (!rs[0].getStatus().equals(Status.OK));
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void enviaPassarVez() {
        Request req = makeRequest("jogador/passarvez", null);
        sendSimpleRequestForAll(req);
    }

    private Request makeRequest(String path, Map<String, Object> content) {
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
            for (Response r : responses) {
                if (r.getStatus().equals(Status.OK)) {
                    if (r.getRequest().equals(req)) {
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
        Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "entrando em esperaOrdem");
        try {
            //Espera ordem de jogada do Coordenador
          //  clienteCoordenador = new Client(source, DadosJogador.ipCoordenador, portCoordenador + 1);
            Request req = makeRequest("lobbyconfirmacao/escolhi", null);
            clienteCoordenador.sendRequest(req);
            Response[] responses = clienteCoordenador.getResponses();
            Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Verificando respostas de esperaOrdem");
            Response r = responses[0];
                if (r.getStatus().equals(Status.OK)) {
                    if (r.getRequest().equals(req)) {
                        clienteCoordenador.stop();
                        tServer = new Thread(servidor);
                        tServer.start();
                        Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Servidor do jogador iniciado.");
                        Thread.sleep(15000);
                        Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Content da resposta {0}", r.getContent());
                        OrdemIp oip = (OrdemIp) (r.getContent());
                        Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Valor de OrdemIp {0}", oip);
                        listOrdem = new ArrayList(oip.getOrdem());
                        List<String> ifIps = Client.getInterfaceIps();
                        Set<Entry<Integer, String>> ips = Collections.synchronizedSet(oip.getIp().entrySet());
                        Integer idMe = null;
                        Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Checando se existe ips da lista de ordem \n " + ips.toString() + " \n na lista: \n{0}\n", ifIps.toString());
                        List<String> hosts = new ArrayList<>();
                        for (Entry i : ips) {
                            String k = String.valueOf(i.getValue()).replaceAll("/", "");
                            hosts.add(k);
                            for (String ip : ifIps) {
                                if (k.contains(ip)) {
                                    Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Removendo ip: {0}", ip);
                                    hosts.remove(k);
                                    break;
                                }
                            }
                        }
                        for (String k : hosts) {
                            Client c = null;
                            while(true){
                            try{
                                Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Solicitando conexão");
                                Thread.sleep(500);
                                c  = new Client(this.source, k, portJogadores);
                                
                                
                                } catch(SocketTimeoutException ex){
                                    Logger.getLogger(Comunicacao.class.getName()).log(Level.INFO, "Socket do cliente timed out");

                                } catch (IOException ex) {
                                    Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InterruptedException ex) {
                                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           break;
                            
                            }
                            clientes.add(c);
                        }
                        
                            
                        }
                       
                    }
                
                
            }
        
         catch (ClassNotFoundException | InterruptedException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrdem;
    }

}
