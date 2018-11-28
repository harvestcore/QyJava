/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;

import qytetetjava.Casilla;
import qytetetjava.TipoCasilla;
import qytetetjava.TituloPropiedad;

/**
 *
 * @author Angel
 */
public class VistaCasilla extends javax.swing.JPanel {

    /**
     * Creates new form VistaCasilla
     */
    public VistaCasilla() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoCasilla = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoTitulo = new javax.swing.JTextArea();

        textoCasilla.setEditable(false);
        textoCasilla.setColumns(20);
        textoCasilla.setRows(5);
        jScrollPane2.setViewportView(textoCasilla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Casilla", jPanel1);

        textoTitulo.setEditable(false);
        textoTitulo.setColumns(20);
        textoTitulo.setRows(5);
        jScrollPane1.setViewportView(textoTitulo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Título", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea textoCasilla;
    private javax.swing.JTextArea textoTitulo;
    // End of variables declaration//GEN-END:variables


    public void actualizar(Casilla casilla, TituloPropiedad titulo) {
        String xd;
        if (casilla.getTipoCasilla() == TipoCasilla.CALLE) {
            xd = "" + "Numero Casilla = " + casilla.getNumeroCasilla() + "\nCoste = " + casilla.getCoste() + "\nNumero Hoteles = " + casilla.getNumHoteles() + "\nNumero Casas = " + casilla.getNumCasas() + "\nTipo = " + casilla.getTipoCasilla();
            textoCasilla.setText(xd);
            xd = "" + "Nombre = " + casilla.getTituloPropiedad().getNombre() + "\nHipotecada = " + casilla.getTituloPropiedad().isHipotecada() + "\nAlquiler Base = " + casilla.getTituloPropiedad().getAlquilerBase() + "\nFactorRevalorizacion = " + casilla.getTituloPropiedad().getFactorRevalorizacion() + "\nHipotecaBase = " + casilla.getTituloPropiedad().getHipotecaBase() + "\nPrecio Edificar = " + casilla.getTituloPropiedad().getPrecioEdificar();
            textoTitulo.setText(xd);
        } else {
            xd = "Numero Casilla = " + casilla.getNumeroCasilla() + "\nTipo = " + casilla.getTipoCasilla();
            textoCasilla.setText(xd);
            xd = "Esta casilla no tiene título de propiedad.";
            textoTitulo.setText(xd);
        }

        this.repaint();
        this.revalidate();
    }

}
