/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;

import java.util.Random;

/**
 *
 * @author Angel
 */
public class Dado2 {
    private static int numeroCaras;
    private static final Dado2 dado = new Dado2();
    
    static Dado2 crearDado() {        
        return dado;
    }
            
    private Dado2() {
        numeroCaras = 6;
    }
    
    int tirar() {
        Random rand = new Random();
        return rand.nextInt(numeroCaras) + 1;
    }   
}
