/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenador;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Maitê
 */
public class Jogador {
    private int id;
    private String mapa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }
    
}