/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcescoordenadorteste;

import resourcescoordenador.*;
import coordenador.DadosCoordenador;
import coordenador.Jogador;
import coordenador.Mapa;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.Source;
import reflect.annotations.Path;
import reflect.annotations.Resource;
import reflect.annotations.UserInfo;

/**
 *
 * @author Mihael Zamin
 */
@Resource
@Path("lobbyentrada")
public class LobbyEntradaResource {

    @UserInfo
    public Source source;

    @Path("getmapa")
    public String getMapa() {
        
        DadosCoordenador.executarTimer1();
        if(!DadosCoordenador.timer.getDone()){
        source.setId(DadosCoordenador.lastId);
        DadosCoordenador.lastId++;
        Logger.getLogger(LobbyEntradaResource.class.getName()).log(Level.INFO, "Entrando na fila de espera: id {0}",source.getId() );
        Jogador j = new Jogador();
        j.setId(source.getId());
        DadosCoordenador.resources.addPlayer(j);
        while (!DadosCoordenador.timer.getDone()) {
           
                
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(LobbyEntradaResource.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
        }
        DadosCoordenador.distribuiMapas();
        return j.getMapa();
        } return "timeout";
    }
}
