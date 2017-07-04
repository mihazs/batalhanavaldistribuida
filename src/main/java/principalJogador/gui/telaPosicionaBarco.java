/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principalJogador.gui;
import java.util.ArrayList;
/**
 *
 * @author ramon
 */
public class telaPosicionaBarco extends javax.swing.JFrame {
    
    private final telaPrincipal tP;
    
    
    /**
     * Creates new form telaPosicionaBarco
     * @param tp
     */
    public telaPosicionaBarco(telaPrincipal tp) {
        initComponents();
        
        this.tP = tp;
        
        this.lbBarco.setText("" + tP.getBatalhaNaval().barcoColocado(0));
        this.lbRestantes.setText("(" + tP.getBatalhaNaval().barcosRestantes() + " restando)");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtColunaInicial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btPosicionar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbBarco = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtLinhaInicial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtOrientacao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbRestantes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Coluna");

        btPosicionar.setText("Posicionar");
        btPosicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPosicionarActionPerformed(evt);
            }
        });

        jLabel4.setText("Barco sendo posicionado:");

        lbBarco.setText("Barco");

        jLabel1.setText("Posição do barco");

        jLabel2.setText("Linha");

        jLabel5.setText("Orientação");

        lbRestantes.setText("Restantes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLinhaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtColunaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtOrientacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btPosicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbBarco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbRestantes)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbBarco)
                    .addComponent(lbRestantes))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtLinhaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtColunaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btPosicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtOrientacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btPosicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPosicionarActionPerformed
        
        tP.getBatalhaNaval().posicionaBarco(lbBarco.getText(), txtLinhaInicial.getText(), txtColunaInicial.getText(), txtOrientacao.getText());
        this.lbBarco.setText("" + tP.getBatalhaNaval().barcoColocado(0));
        this.lbRestantes.setText("(" + tP.getBatalhaNaval().barcosRestantes() + " restando)");
        
        tP.getBatalhaNaval().printMar();
        
        if (tP.getBatalhaNaval().acabouBarcos()) {
            ArrayList<Integer> ordem;
            //tP.batalhaNaval.Pronto = true;
            
            
            ordem = tP.Comunicacao.esperaOrdem();
            tP.getBatalhaNaval().iniciaOrdem(ordem);
            this.setVisible(false);
        } 
    }//GEN-LAST:event_btPosicionarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPosicionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbBarco;
    private javax.swing.JLabel lbRestantes;
    private javax.swing.JTextField txtColunaInicial;
    private javax.swing.JTextField txtLinhaInicial;
    private javax.swing.JTextField txtOrientacao;
    // End of variables declaration//GEN-END:variables
}