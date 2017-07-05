/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import coordenador.DadosCoordenador;
import coordenador.EndLobby;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.Source;
import messages.request.Request;
import messages.response.Response;
import org.junit.Ignore;
import org.junit.Test;
import principalCoordenador.CoordenadorServer;
import principalJogador.batalha.model.Ataque;
import principalJogador.gui.telaPrincipal;
import sockets.client.Client;
import sockets.server.Server;

/**
 *
 * @author Mihael Zamin
 */
public class JogadorTest {
    
    @Test
    @Ignore
    public void testServidorJogador() throws IOException, ClassNotFoundException{
        Server sv = new Server(9957, "principalJogador.resources");
        new Thread(sv).start();
        Client c = new Client(new Source(), "127.0.0.1", 9957);
         Request req = new Request();
        req.setResourcePath("jogador/atacar");
        Map<String, Object> map = new HashMap();
        map.put("ataque", new Ataque());
        req.setSource(c.getSource());
        req.setContent(map);
        c.sendRequest(req);
        Response[] r = c.getResponses();
        System.out.println("Resposta obtida" + r[0].getContent());
    }
    @Test
    @Ignore
    public void testCoordenador(){
        CoordenadorServer.main(new String[0]);
    }
    
    @Test
   // @Ignore
    public void testJogador(){
       // Logger.getGlobal().setLevel(Level.SEVERE);
        telaPrincipal.main(new String[0]);
    }
    
    @Test
    @Ignore
    public void testJogadorCoordenador(){
        //Logger.getGlobal().setLevel(Level.SEVERE);
         telaPrincipal.main(new String[0]);
       CoordenadorServer.main(new String[0]);
      
        
       
       
    }
    
}
