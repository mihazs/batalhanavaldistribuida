/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import json.MessageProcessor;
import json.ResponseWrapper;
import junit.framework.TestCase;
import messages.Source;
import messages.request.Request;
import messages.response.Response;
import org.junit.Ignore;
import org.junit.Test;
import sockets.client.Client;
import sockets.server.Server;

/**
 *
 * @author Mihael Zamin
 */
@Ignore
public class SocketTest extends TestCase {
    /*
    @Test
    public void testServer() throws IOException{
        ServerSocket ss = new ServerSocket(9987);
        Socket socket = ss.accept();
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        MessageProcessor mp = new MessageProcessor();
        ResponseWrapper rw = mp.processRequest(input.readUTF());
        output.writeUTF(rw.getResponse());
    }
    
    @Test
    public void testClient() throws UnknownHostException, IOException, ClassNotFoundException{
        int[][] matriz = new int[6][6];
        Random rdn = new Random();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                matriz[i][j] = rdn.nextInt();
            }
        }
        Socket socket = new Socket(InetAddress.getByName("192.168.0.109"), 9987);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        Request r = new Request();
        r.setResourcePath("leoloch/recebematriz");
        r.getContent().put("nome", "O leléo");
        r.getContent().put("matriz", matriz);
        r.setSource(new Source());
        r.getSource().setId(1);
        r.getSource().setName("Leonardinho");
        MessageProcessor mp = new MessageProcessor();
        output.writeObject(mp.prepareRequest(r));
        Response res = mp.processResponse(String.valueOf(input.readObject()));
        System.out.println("Valor obtido: " + String.valueOf(res.getContent()));
        
    }
    */
    @Test
    
    public void testServidor() throws IOException{
    Server s = new Server(9987, "resourcescoordenador");
    Server s2 = new Server(9988, "resourcescoordenadordois");
    
    //s.run();
    }
    @Test
    public void testClient() throws IOException, ClassNotFoundException{
        Client c = new Client(new Source(), "192.168.0.1", 9987);
        
        Request r = new Request();
        r.setResourcePath("helloworld/helloparam");
        Map<String, Object> m = new HashMap();
        m.put("nome", "Mihael");
        m.put("matriz", new int[5][5]);
        r.setContent(m);
        c.sendRequest(r);
        Response[] rs = c.getResponses();
        String retorno = String.valueOf(rs[0].getContent());
    }
    
}
