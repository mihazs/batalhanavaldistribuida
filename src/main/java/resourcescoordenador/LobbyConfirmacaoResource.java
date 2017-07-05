/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcescoordenador;


import messages.OrdemIp;
import coordenador.DadosCoordenador;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.Source;
import reflect.ServerInformation;
import reflect.annotations.Path;
import reflect.annotations.Resource;
import reflect.annotations.ServerInfo;
import reflect.annotations.UserInfo;
import reflect.annotations.casting.Broadcast;

/**
 *
 * @author Mihael Zamin
 */
@Resource
@Path("lobbyconfirmacao")
public class LobbyConfirmacaoResource {
    @ServerInfo
    public ServerInformation serverInformation;
    
    @UserInfo
    public Source source;
            
    @Path("escolhi")
    public OrdemIp distribuiOrdem(){
       DadosCoordenador.executarTimer2();
       if(!DadosCoordenador.timer2.getDone()){
           DadosCoordenador.resources.getConectados().add(source.getId());
        DadosCoordenador.ipsEscolhidos.put(source.getId(), serverInformation.getHostsConnected().get(source.getId()));
        while(!DadosCoordenador.timer2.getDone()){
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(LobbyConfirmacaoResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        OrdemIp oip = new OrdemIp();
        oip.setIp(DadosCoordenador.ipsEscolhidos);
        oip.setOrdem(DadosCoordenador.getOrdem());
           System.out.println("Retornando ordem...");
       return oip;
       } else return null;
        
        
    }
    
    
}
