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
            
            Server server = new Server(port, "resourcescoordenador");
            server.run();
            //server2.stopServer();
        } catch (IOException ex) {
            Logger.getLogger(CoordenadorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }
}
