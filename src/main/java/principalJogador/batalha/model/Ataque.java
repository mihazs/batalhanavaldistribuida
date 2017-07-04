/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principalJogador.batalha.model;

/**
 *
 * @author Mihael Zamin
 */
public class Ataque {
    private String linha;
    private String coluna;

    public Ataque() {
    }

    public Ataque(String linha, String coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

   
    
    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        this.coluna = coluna;
    }
    
}
