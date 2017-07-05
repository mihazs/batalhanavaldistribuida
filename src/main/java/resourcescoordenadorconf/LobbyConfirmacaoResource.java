/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcescoordenadorconf;

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
       if(!DadosCoordenador.timer.getDone()){
        while(!DadosCoordenador.timer.getDone()){
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(LobbyConfirmacaoResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        DadosCoordenador.resources.getConectados().add(source.getId());
        OrdemIp oip = new OrdemIp();
        oip.setIp(serverInformation.getHostsConnected());
        oip.setOrdem(DadosCoordenador.getOrdem());
       return oip;
       } else return null;
        
        
    }
    
    
}
