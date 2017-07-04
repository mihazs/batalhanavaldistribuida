/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principalJogador.batalha;


import principalJogador.gui.telaPrincipal;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class BatalhaNaval {

    private final ArrayList<ArrayList<Integer>> matrizMar;
    private final ArrayList<Integer> vetorOrdem;

    private final ArrayList<Integer> vetorBarcos;
    private int ID;

    private int linhaMinima;
    private int colunaMinima;
    private int linhaMaxima;
    private int colunaMaxima;
    private int linhas;
    private int colunas;
    private int indexJogando;

    //public boolean Pronto;
    public BatalhaNaval() {
        matrizMar = new ArrayList<>();
        vetorOrdem = new ArrayList<>();
        vetorBarcos = new ArrayList<>();
        indexJogando = 0;

        /* Frota padrão */
        vetorBarcos.add(4);
        vetorBarcos.add(3);
        vetorBarcos.add(3);
        vetorBarcos.add(2);
        vetorBarcos.add(2);
        vetorBarcos.add(2);
        vetorBarcos.add(1);
        vetorBarcos.add(1);

    }

    public void iniciaMar(String sN) {
        int n = Integer.parseInt(sN);
        this.linhas = 26;
        this.colunas = ((n + 1) / 2) * 14;

        for (int i = 0; i < linhas; i++) {
            // seu Objeto é a classe que representa o objeto q vc ta armazenando no ARrayList
            matrizMar.add(new ArrayList<>());
            for (int j = 0; j < colunas; j++) {
                matrizMar.get(i).add(0);
            }
        }
    }

    public void iniciaOrdem(ArrayList ordem) {
        vetorOrdem.addAll(ordem);
    }

    public void setID(String ID) {
        this.ID = Integer.parseInt(ID);
    }

    public void setMeuEspaco(String min, String max) {
        int linhaMinima, colunaMinima, linhaMaxima, colunaMaxima;
        linhaMinima = getIntString(String.valueOf(min.charAt(0)));
        colunaMinima = Integer.parseInt(min.substring(1));
        linhaMaxima = getIntString(String.valueOf(max.charAt(0)));
        colunaMaxima = Integer.parseInt(max.substring(1));
        
        this.linhaMinima = linhaMinima;
        this.colunaMinima = colunaMinima;
        this.linhaMaxima = linhaMaxima;
        this.colunaMaxima = colunaMaxima;
    }
    
    public boolean isMeuEspaco(int linha, int coluna) {
        return !(linha < this.linhaMinima || linha > this.linhaMaxima || coluna < this.colunaMinima || coluna > this.colunaMaxima);
    }

    public void updtJogadorAtual() {
        if (this.indexJogando + 1 < this.lenVetorOrdem()) {
            this.indexJogando++;
        } else {
            this.indexJogando = 0;
        }
    }

    /**
     *
     * @param telaPrincipal
     */
    public void enablePlaying(telaPrincipal telaPrincipal) {
        telaPrincipal.setBtAtacar(true);
    }

    public void remPlayer(int idPlayerMorto) {
        vetorOrdem.remove(idPlayerMorto);
    }

    /**/
    /**
     *
     * @return
     */
    public boolean acabouBarcos() {
        return vetorBarcos.isEmpty();
    }

    public int barcoColocado(int bRemoved) {
        return vetorBarcos.remove(bRemoved);
    }

    public int barcosRestantes() {
        return vetorBarcos.size();
    }

    public boolean perdi() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matrizMar.get(i).get(j) == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public void posicionaBarco(String tipoBarco, String lIni, String cIni, String orientacao) {
        int tpBarco = Integer.parseInt(tipoBarco);
        int linhaInicial = getIntString(lIni);
        int colunaInicial = Integer.parseInt(cIni);

        if (orientacao.toLowerCase().startsWith("v")) {
            for (int i = linhaInicial; i < linhaInicial + tpBarco; i++) {
                matrizMar.get(i).set(colunaInicial, 1);
            }
        } else {
            for (int j = colunaInicial; j < colunaInicial + tpBarco; j++) {
                matrizMar.get(linhaInicial).set(j, 1);
            }
        }
    }

    public void printMar() {
        System.out.println("~~~~~~~~~Mar~~~~~~~~~");

        System.out.print(" ");
        for (int j = 0; j < colunas; j++) {
            System.out.print(j % 10);
        }
        System.out.println();
        for (int i = 0; i < linhas; i++) {
            System.out.print(getStringInt(i));
            for (int j = 0; j < colunas; j++) {
                System.out.print(matrizMar.get(i).get(j));
            }
            System.out.println();
        }
    }

    public boolean minhaVez() {
        return vetorOrdem.get(indexJogando) == ID;
    }

    public int idJogadorAtual() {
        return vetorOrdem.get(indexJogando);
    }

    public int getIntString(String c) {
        char letra = 'A';
        return (int) c.toUpperCase().charAt(0) - (int) letra;
    }

    private char getStringInt(int i) {
        return (char) ((int) 'A' + i);
    }

    public int lenVetorOrdem() {
        return vetorOrdem.size();
    }

    public int getID() {
        return ID;
    }

    /*
    0: Acertou no mar
    1: Me acertou
    2: Morri
    */
    public int recebeTiro(String sLinha, String sColuna) {
        int linha, coluna;
        linha = getIntString(sLinha);
        coluna = Integer.parseInt(sColuna);
        if (matrizMar.get(linha).get(coluna) == 1) {
            matrizMar.get(linha).set(coluna, 5);
            
            printMar();
            if (perdi())
                return 2;
            else
                return 1;
        } else {
            matrizMar.get(linha).set(coluna, 4);
            printMar();
            return 0;
        }
    }
}