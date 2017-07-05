/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Ignore;
import org.junit.Test;
import principalCoordenador.CoordenadorServer;
import principalJogador.gui.telaPrincipal;

/**
 *
 * @author Mihael Zamin
 */
public class JogadorTest {
    
    @Test
    @Ignore
    public void testCoordenador(){
        CoordenadorServer.main(new String[0]);
    }
    
    @Test
    @Ignore
    public void testJogador(){
        telaPrincipal.main(new String[0]);
    }
    
    @Test
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
