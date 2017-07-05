/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import coordenador.DadosCoordenador;
import coordenador.EndLobby;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Ignore;
import org.junit.Test;
import principalCoordenador.CoordenadorServer;
import principalJogador.gui.telaPrincipal;
import sockets.server.Server;

/**
 *
 * @author Mihael Zamin
 */
public class JogadorTest {
    
    @Test
    @Ignore
    public void testOneCoordenador(){
        final int port = 9987;
        try {
            
            Server server = new Server(port, "resourcescoordenadorteste");
            server.run();
            //server2.stopServer();
        } catch (IOException ex) {
            Logger.getLogger(CoordenadorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    @Ignore
    public void testCoordenador(){
        CoordenadorServer.main(new String[0]);
    }
    
    @Test
    public void testJogador(){
        telaPrincipal.main(new String[0]);
    }
    
    @Test
    @Ignore
    public void testJogadorCoordenador(){
      Thread t = new Thread(new Runnable() {

          @Override
          public void run() {
              System.out.println("Iniciando Coordenador");
              CoordenadorServer.main(new String[0]);
              
          }
      });
      t.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(JogadorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        telaPrincipal.main(new String[0]);
       
    }
    
}
