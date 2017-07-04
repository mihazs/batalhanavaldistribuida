/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenador;

import java.util.Random;

/**
 *
 * @author Maitê
 */
public class Mapa {
    int n, n_escolhidos;
    Random rand = new Random();
    int chosen[];  
    char letras[];
   
    public Mapa(int n){
        this.n = n;
        chosen = new int[n];
        n_escolhidos = 0;
        letras = new char[26];
        char letra = 'A';
        for (int i = 0; i < 26; i++){
            letras[i] = letra++;
        }
    }
    
    public String getMapaPos(){
        int j = -1;
        String pos = "";
        boolean jaescolhido = true;
        while (jaescolhido){
            jaescolhido = false;
            j = rand.nextInt(n+1);
            if (j == 0){
                jaescolhido = true;
            } else {
                for (int i = 0; i < n_escolhidos; i++){
                    if (chosen[i] == j){
                        jaescolhido = true;
                    }
                }
            }
        }
        chosen[n_escolhidos++] = j;
        int width = ((n+1)/2)*14;
        int v = rand.nextInt(4);
        if (j % 2 == 0)
            v += 13;
        
        int w = ((j+1) / 2 )- 1;
        int z = rand.nextInt(4) + 1;
        z += width/((n+1)/2) * w;
        pos += letras[v];
        pos += z;
        pos += "-";
        pos += letras[v+9];
        pos += z+9;
            
        return pos;
    }
}