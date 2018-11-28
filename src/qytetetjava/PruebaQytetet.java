/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;

//import static qytetetjava.TipoSorpresa.PAGARCOBRAR;
import InterfazTextualQytetet.ControladorQytetet;
import java.util.*;

/**
 *
 * @author Angel
 */
public class PruebaQytetet {
    private static Tablero tablero = null;
    private static ArrayList<Sorpresa> mazo = new ArrayList();
    
    private static void inicializarSorpresas() {        
        mazo.add(new Sorpresa ("Tu perro encuentra una caja con unas joyas. Las vendes y ganas 500 euros.", 500, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Vuelves borracho a casa y al subir las escaleras te caes y te rompes las piernas. Los costes médicos te cuestan 450 euros.", -450, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Un paseo nunca viene mal, ve a la casilla 15.", 15, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Te invitan a una carrera no ilegal y por tanto vas a la salida.", 0, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Te pasas de listo en un control antidrogas y vas a la cárcel.", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("El mantenimiento básico de tus casa y hoteles te cuesta por cada uno 80 euros.", -80, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Ganas un concurso de hostelería y recibes por cada casa y hotel 100 euros.", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Eres guapo/a y cada jugador/a te debe dar 100 euros.", 100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Debido a tu gran generosidad regalas a cada jugador un dia en el campo de golf. Te cuesta 125 euros cada uno.", -125, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Llave maestra de la cárcel. Tiene un solo uso.", 0, TipoSorpresa.SALIRCARCEL));
    }
    
    private static ArrayList<Sorpresa> valorSorpresaMayorQue0(ArrayList<Sorpresa> mazo){
        ArrayList<Sorpresa> aux = new ArrayList();
        
        for(int i = 0; i < mazo.size(); ++i) {
            if (mazo.get(i).getValor() > 0)
                aux.add(mazo.get(i));
        }
        
        return aux;
    }
    
    private static ArrayList<Sorpresa> sonTipoIRACASILLA(ArrayList<Sorpresa> mazo){
        ArrayList<Sorpresa> aux = new ArrayList();
        
        for(int i = 0; i < mazo.size(); ++i) {
            if (mazo.get(i).getTipoSorpresa()== TipoSorpresa.IRACASILLA)
                aux.add(mazo.get(i));
        }
        
        return aux;
    }
    
    private static ArrayList<Sorpresa> sonTipoArg(ArrayList<Sorpresa> mazo, TipoSorpresa tipo){
        ArrayList<Sorpresa> aux = new ArrayList();

        for(int i = 0; i < mazo.size(); ++i) {
            if (mazo.get(i).getTipoSorpresa()== tipo)
                aux.add(mazo.get(i));
        }
        
        return aux;
    }
    
    public void test(){
        System.out.print("Mazo posicion 1.\n");
        System.out.print(mazo.get(1).toString() + "\n\n");
        
        ArrayList<Sorpresa> greaterThan0 = new ArrayList();
        ArrayList<Sorpresa> typeIRACASILLA = new ArrayList();
        ArrayList<Sorpresa> typeArg = new ArrayList();
        
        greaterThan0 = valorSorpresaMayorQue0(mazo);
        typeIRACASILLA = sonTipoIRACASILLA(mazo);
        typeArg = sonTipoArg(mazo, TipoSorpresa.SALIRCARCEL);
        
        System.out.print("Mazo mayores que 0.\n");
        for (Sorpresa sorpresa : greaterThan0)
            System.out.println(sorpresa.toString());
        
        System.out.print("\nMazo IRACASILLA.\n");
        for (Sorpresa sorpresa : typeIRACASILLA)
            System.out.println(sorpresa.toString());
        
        System.out.print("\nMazo arg.\n");
        for (Sorpresa sorpresa : typeArg)
            System.out.println(sorpresa.toString());
    }

    public static void test2() {
        for (Sorpresa s : mazo)
            System.out.println(s.toString());
    }
    
    public static void test3() {
        System.out.println(tablero.toString());
    }
    
    //public static void main(String[] args) {
    public void main(String[] args) {
        Qytetet game = Qytetet.getQytetet();     
        ControladorQytetet juego = new ControladorQytetet();
        juego.executeGame();
    }
}
