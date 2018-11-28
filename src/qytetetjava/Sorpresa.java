/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;

/**
 *
 * @author Angel
 */
public class Sorpresa {
    
    private String texto;
    private int valor;
    private TipoSorpresa tipo;       
    
    public Sorpresa(String texto, int valor, TipoSorpresa tipo) {
        this.texto = texto;
        this.valor = valor;
        this.tipo = tipo;    
    }
    
    Sorpresa() {}
    
    public String getTexto() {
        return texto;
    }
        
    public TipoSorpresa getTipoSorpresa() {
        return tipo;
    }
    
    public int getValor() {
        return valor;
    }
    
    @Override
    public String toString() {
        return "\nSorpresa:" + "\n\tTexto = " + texto + "\n\tValor = " + Integer.toString(valor) + "\n\tTipo = " + tipo;
    }    
}
