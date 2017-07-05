/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenador;

import coordenador.EndLobby;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mihael Zamin
 */
public class DadosCoordenador {
    public static int lastId = 0;
    public static EndLobby timer;
    public static Resources resources = new Resources();
    
    public static void distribuiMapas(){
        
        try {
            resources.setResources();
        } catch (Exception ex) {
            Logger.getLogger(DadosCoordenador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static List<Integer> getOrdem(){
        if(!resources.isConfirmados())
            resources.setOrdem();
        return resources.getOrdem();
    }
}
