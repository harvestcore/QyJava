/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazTextualQytetet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import qytetetjava.Casilla;
import qytetetjava.Jugador;
import qytetetjava.Qytetet;

/**
 *
 * @author Angel
 */
public class VistaTextualQytetet {
    
    private static final Scanner in = new Scanner (System.in);
    
    public VistaTextualQytetet() {
    }
    
    boolean comprobarOpcion(String lectura, int min, int max) {
        boolean valido=true;   
        int opcion;
        
        try {  
            opcion = Integer.parseInt(lectura);
            if (opcion < min || opcion > max) { // No es un entero entre los válidos
                this.mostrar("\n[err] El número debe estar entre min y max.\n");
                valido = false;
            }
        } catch (NumberFormatException e) { // No se ha introducido un entero
            this.mostrar("[err] Debes introducir un número.\n");
            valido = false;  
        }
        
        return valido;
    }
    
    boolean elegirQuieroComprar() {
        boolean quieroComprar = false;
        Map<Integer, String> menuGI = new TreeMap();
        System.out.println("\n[?] ¿Quieres comprar la propiedad?");
        menuGI.put(0,"Sí.");
        menuGI.put(1,"No.");
        
        if (seleccionMenu(menuGI) == 0) {
            quieroComprar = true;
        }
        
        return quieroComprar;
    }
    
    int menuElegirPropiedad(ArrayList<String> listaPropiedades) {
        Map<Integer, String> menuEP = new TreeMap();
        int numeroOpcion=0;
        System.out.println("\n[+] Elige una propiedad:");
        for(String prop: listaPropiedades) {
            menuEP.put(numeroOpcion, prop); //opcion de menu, numero y nombre de propiedad
            numeroOpcion = numeroOpcion + 1;
        }
        int salida = this.seleccionMenu(menuEP); // Método para controlar la elección correcta en el menú 
        return salida;
    }
    
    int menuGestionInmobiliaria() {
        this.mostrar("\n[+] Elige la gestion inmobiliaria que deseas hacer:\n");
        Map<Integer, String> menuGI = new TreeMap();
        menuGI.put(0, "Atrás."); 
        menuGI.put(1, "Edificar casa.");
        menuGI.put(2, "Edificar hotel."); 
        menuGI.put(3, "Vender propiedad.");  	
        menuGI.put(4, "Hipotecar propiedad."); 
        menuGI.put(5, "Cancelar hipoteca.");
        int salida = this.seleccionMenu(menuGI);
        return salida;
    }
    
    int menuSalirCarcel() {
        Map<Integer, String> menuGI = new TreeMap();
        System.out.println("\n[i] Te encuentras en la carcel.");
        System.out.println("[?] ¿Qué quieres hacer?");
        menuGI.put(0,"Pagar libertad.");
        menuGI.put(1,"Tirar dado.");
        
        return seleccionMenu(menuGI) + 1;
    }
    
    int menuFinTurno() {
        Map<Integer, String> menuGI = new TreeMap();
        System.out.print("\n[?] ¿Qué quieres hacer ahora?\n");
        menuGI.put(0,"Ver mis propiedades.");
        menuGI.put(1,"Gestiones inmobiliarias.");
        menuGI.put(2,"Pasar turno.");

        return seleccionMenu(menuGI) + 1;
    }
    
    void mostrar(String texto) {
        System.out.print(texto);
    }
    
    ArrayList<String> obtenerNombreJugadores() {
        boolean valido = true; 
        String lectura;
        ArrayList<String> nombres = new ArrayList();
        
        do{ //repetir mientras que el usuario no escriba un número correcto 
            this.mostrar("[+] Escribe el número de jugadores (2-4): ");
            lectura = in.nextLine();  //lectura de teclado
            valido=this.comprobarOpcion(lectura, 2, 4); //método para comprobar la elección correcta
        }while (!valido);

        for (int i = 1; i <= Integer.parseInt(lectura); i++) { //solicitud del nombre de cada jugador
          this.mostrar("[+] Nombre del jugador " + i + ": ");
          nombres.add (in.nextLine());
        }
        
        return nombres;
    }
    
    int seleccionMenu(Map<Integer, String> menu) {
        boolean valido = true; 
        int numero;
        String lectura;
        do { // Hasta que se hace una selección válida
          for(Map.Entry<Integer, String> fila : menu.entrySet()) {
                numero = fila.getKey();
                String texto = fila.getValue();
                this.mostrar("\t(" + numero + ") - " + texto + "\n");  // número de opción y texto
          }
          this.mostrar("\n[>] Elige una opción: ");
          lectura = in.nextLine();  //lectura de teclado
          valido=this.comprobarOpcion(lectura, 0, menu.size()-1); //método para comprobar la elección correcta
        } while (!valido);
        return Integer.parseInt(lectura);
    }
    
    void infoPrincipioTurno(Jugador jugador_actual, Casilla casilla_actual) {
        System.out.print("\n###########################################\n");
        System.out.print("\n[i] Turno de: " + jugador_actual.getNombre() + "\n");
        System.out.print("\n[i] Casilla actual de " + jugador_actual.getNombre() + ": " + casilla_actual.getNumeroCasilla() + "\n");
        System.out.println("[i] Saldo de " + jugador_actual.getNombre() + ": " + jugador_actual.getSaldo());
    }
    
    void info(Jugador jugador_actual, Casilla casilla_actual) {
        System.out.println("\n[i] " + jugador_actual.getNombre() + " está en la casilla numero " + casilla_actual.getNumeroCasilla() + ".");
        System.out.println(casilla_actual.toString());
    }
    
    void jugadorLiberado(Jugador jugador_actual) {
        System.out.println("[i] " + jugador_actual.getNombre() + " ha sido liberado.\n");
    }
    
    void jugadorEncarcelado(Jugador jugador_actual) {
        System.out.println("[i] El jugador " + jugador_actual.getNombre() + " ha sido encarcelado.\n");
    }
    
    void jugadorEnLaCarcel(Jugador jugador_actual) {
        System.out.println("\n[i] El jugador " + jugador_actual.getNombre() + " está en la cárcel.\n");
    }
    
    void pause() {
        System.out.print("\n[-] Pulsa una tecla para continuar...: ");
        String aux = in.nextLine();
        System.out.print("\n");
    }
    
    void showGame(Qytetet juego, Jugador jugador_actual, Casilla casilla_actual) {
        String respuesta;
        
        System.out.print("\n");
        System.out.print("[?] ¿Mostrar jugadores?: ");
        respuesta = in.nextLine();
        if (respuesta.equals("s"))
            juego.getJugadores();
        
        System.out.print("[?] ¿Mostrar cartas?: ");
        respuesta = in.nextLine();
        if (respuesta.equals("s"))
            juego.showCartas();
        
        System.out.print("[?] ¿Mostrar tablero?: ");
        respuesta = in.nextLine();
        if (respuesta.equals("s"))
            juego.showTablero();
        
        System.out.println("\n~~~~~~~COMIENZA EL JUEGO~~~~~~~"); 
        
        this.pause();
    }
    
    void compra(Casilla casilla, Jugador jugador_actual) {
        System.out.print("\n[!] Enhorabuena, has comprado: " + casilla.getTituloPropiedad().getNombre());
        System.out.print("\n[i] Saldo actual: " + jugador_actual.getSaldo() + "\n");
    }
    
    void mostrarPropiedades(Jugador jugador) {
        ArrayList<String> aux = jugador.getPropiedades(3);
        if (aux.size() > 0) {
            System.out.println("\n[·] Propiedades:");
            for (String s: aux) {
                System.out.println("\t" + s);
            }
        } else {
            System.out.println("\n[!] Aún no tienes propiedades.");
        }
    }
    
    void textoInmobiliario(Casilla casilla, Jugador jugador, int modo) {
        switch (modo) {
            case 1:
                System.out.println("\n[i] Has edificado una casa en: " + casilla.getTituloPropiedad().getNombre());
                System.out.println("[i] Casas construidas en la casilla: " + casilla.getNumCasas());
                System.out.println("[i] Saldo actual: " + jugador.getSaldo());
                break;

            case 2:
                System.out.println("\n[i] Has edificado un hotel en: " + casilla.getTituloPropiedad().getNombre());
                System.out.println("[i] Hoteles construidos en la casilla: " + casilla.getNumHoteles());
                System.out.println("[i] Saldo actual: " + jugador.getSaldo());
                break;

            case 3:
                System.out.println("\n[i] Has vendido la propiedad: " + casilla.getTituloPropiedad().getNombre());
                System.out.println("[i] Saldo actual: " + jugador.getSaldo());
                break;

            case 4:
                System.out.println("\n[i] Has hipotecado la propiedad: " + casilla.getTituloPropiedad().getNombre());
                System.out.println("[i] Saldo actual: " + jugador.getSaldo());
                break;

            case 5:
                System.out.println("\n[i] Has cancelado la hipoteca de la propiedad: " + casilla.getTituloPropiedad().getNombre());
                System.out.println("[i] Saldo actual: " + jugador.getSaldo());
                break;
                
            case 6:
                System.out.println("\n[i] No tienes propiedades donde edificar casas.");
                break;

            case 7:
                System.out.println("\n[i] No tienes propiedades donde edificar hoteles.");
                break;

            case 8:
                System.out.println("\n[i] No tienes propiedades para vender.");
                break;

            case 9:
                System.out.println("\n[i] No tienes propiedades disponibles para hipotecar.");
                break;

            case 10:
                System.out.println("\n[i] No tienes propiedades hipotecadas.");
                break;
                
            case 11:
                System.out.println("[i] No puedes constuir más casas en esta casilla.");
                break;
                
            case 12:
                System.out.println("[i] No puedes constuir más hoteles en esta casilla.");
                break;
        }
    }
    
    void welcome() {
        System.out.println("#############################");
        System.out.println("#  BIENVENIDO A QYTETET xD  #");
        System.out.println("#############################");
        System.out.println("\n");
    }
    
    void endGame(HashMap<String, Integer> ranking, Qytetet juego) {
        System.out.println("\n[!!] Has caido en bancarrota, fin del juego.");
        System.out.println("\n[i] RANKING:\n");
        for (Jugador j: juego.jugadores()) {
            System.out.println("\t" + j.getNombre() + " - " + ranking.get(j.getNombre()));
        }
    }
}
