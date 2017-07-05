/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principalCoordenador;

import coordenador.DadosCoordenador;
import coordenador.EndLobby;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sockets.server.Server;

/**
 *
 * @author Mihael Zamin
 */
public class CoordenadorServer {
    public static void main(String[] args){
        final int port = 9987;
        try {
            
            Server server1 = new Server(port, "resourcescoordenador");
            DadosCoordenador.timer = new EndLobby();
            DadosCoordenador.timer.Executar(20);
            new Thread(server1).start();
            while(!DadosCoordenador.timer.getDone())
            {
                Thread.sleep(5);
                DadosCoordenador.timer.getTimer();
            }
            Thread.sleep(5000);
            server1.stopServer();
            Server server2 = new Server(port+1, "resourcescoordenadorconf");
            new Thread(server2).start();
            DadosCoordenador.timer.Executar(50);
            while(!DadosCoordenador.timer.getDone())
                Thread.sleep(5);
            Thread.sleep(5000);
            server2.stopServer();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(CoordenadorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
