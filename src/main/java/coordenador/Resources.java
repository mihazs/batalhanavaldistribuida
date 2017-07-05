/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenador;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author PedroHenrique
 */
public class Resources {
    private ArrayList<Jogador> players = new ArrayList<>();
    
    private ArrayList<Integer> ordem = null;
    private ArrayList<Integer> conectados = new ArrayList<>();
    private Mapa mapa;
    Random rand = new Random();
    private int n;
    private boolean distribuidos = false;
    private boolean confirmados = false;
    
    
    public void setResources() throws Exception{
        if(!distribuidos){
        int i;
        n = players.size();
        mapa = new Mapa(n);
        for (Jogador j : players){
            j.setMapa(j.getId()+"-"+n+"-"+mapa.getMapaPos());
        }
        distribuidos = true;
        } 
    } 
    
    public void addPlayer(Jogador jogador){
        players.add(jogador);
    }
    /*
    public void compare(ArrayList<Socket> JogadoresC){
        n = JogadoresC.size();
        conectados = new int[JogadoresC.size()];
        int cont = 0;
        for (int i = 0; i < n; i++){
            boolean var = true;
            int j = 0;
            while(var){
                if (JogadoresC.get(i).getPort() == sockets.get(j).getPort()){
                    conectados[cont++] = j;
                    var = false;
                } else {
                    j++;
                }
            }
        }
        setOrdem();
    }*/

    public ArrayList<Integer> getConectados() {
        return conectados;
    }

    public void setConectados(ArrayList<Integer> conectados) {
        this.conectados = conectados;
    }
    
    public void setOrdem(){
        if(!confirmados){
        int j = 0;
        ordem = new ArrayList<>();
        for (int i = 0; i < n; i++){
            boolean jaescolhido = true;
            while (jaescolhido){
                jaescolhido = false;
                j = rand.nextInt(n);
                for (int k = 0; k < ordem.size(); k++){
                    if (ordem.get(k) == conectados.get(j)){
                        jaescolhido = true;
                    }
                }
            }
            ordem.add(conectados.get(j));
        }
        confirmados = true;
        } 
    }
    
    public String getMapa(int id){
        Jogador jogador = players.get(id);
        return jogador.getMapa();
    }
    public ArrayList<Integer> getOrdem(){
        return ordem;
    }
    public int getNumeroJogadores(){
        return n;
    }

    
  
}