/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qytetetjava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * 
 * @author Angel
 */
public class Qytetet {
    public static int max_jugadores = 4;
    static int max_casillas = 20;
    static int max_cartas = 12;
    public static int precio_libertad = 200;
    static int saldo_salida = 1000;
    private int index_mazo = 0;
    
    private static final Qytetet gameQytetet = new Qytetet();
    
    private Tablero tablero = null;
    private ArrayList<Sorpresa> mazo = new ArrayList();
    private ArrayList<Jugador> jugadores = new ArrayList();
    private Jugador jugador_actual = null;
    private Sorpresa cartaActual = null;
    private Dado2 dado = null;
    
    private Qytetet () {
        inicializarTablero();
        dado = Dado2.crearDado();
    }
    
    public static Qytetet getQytetet() {
        return gameQytetet;
    }
        
    public boolean aplicarSorpresa() {
        boolean tienePropietario = false;
        
        switch (cartaActual.getTipoSorpresa()) {
            case PAGARCOBRAR:
                jugador_actual.modificarSaldo(cartaActual.getValor());
                break;
                
            case IRACASILLA:
                boolean esCarcel = tablero.esCasillaCarcel(cartaActual.getValor());
                if (esCarcel) {
                    encarcelarJugador();
                }
                else {
                    Casilla nuevaCasilla = tablero.obtenerCasillaNumero(cartaActual.getValor());
                    tienePropietario = jugador_actual.actualizarPosicion(nuevaCasilla);
                }   break;
                
            case PORCASAHOTEL:
                jugador_actual.pagarCobrarPorCasaYHotel(cartaActual.getValor());
                break;
                
            case PORJUGADOR:
                for (Jugador jugador: jugadores) {
                    if (jugador != jugador_actual) {
                        jugador.modificarSaldo(cartaActual.getValor());
                        jugador_actual.modificarSaldo(-cartaActual.getValor());
                    }
                }
                break;
                
            case SALIRCARCEL:
                jugador_actual.setCartaLibertad(cartaActual);
                break;
                
            case CONVERTIRME:
                for (int i = 0; i < jugadores.size(); ++i) {
                    if (jugadores.get(i).equals(jugador_actual)) {
                        Especulador aux = jugador_actual.convertirme(cartaActual.getValor());
                        jugadores.set(i, aux);
                        jugador_actual = jugadores.get(i);
                    }
                }
                break;
                
            default:
                mazo.add(cartaActual);
                break;
        }
        
        System.out.println("\n[!!] ¡Sorpresa activada!");
        System.out.println("[!!] " + cartaActual.getTexto());
        System.out.println("[i] Saldo actual: " + jugador_actual.getSaldo());

        return tienePropietario;
    }
    
    public boolean cancelarHipoteca(Casilla casilla) {
        if (casilla.getTituloPropiedad().getPropietario() == jugador_actual) {
            boolean sePuedeCancelar = casilla.estaHipotecada();
            
            if (sePuedeCancelar) {
                boolean puedoCancelar = jugador_actual.puedoPagarHipoteca(casilla);
                
                if (puedoCancelar) {
                    int cantidadAPagar = casilla.cancelarHipoteca();
                    jugador_actual.modificarSaldo(-cantidadAPagar);
                    return true;
                }
            }
        }          
        
        return false;
    }
    
    public boolean comprarTituloPropiedad() {
        return jugador_actual.comprarTitulo();
    }
    
    public boolean edificarCasa(Casilla casilla) {
        if (casilla.soyEdificable()) {
            boolean sePuedeEdificar = casilla.sePuedeEdificarCasa(jugador_actual.factorEspeculador);
            if (sePuedeEdificar) {
                boolean puedoEdificar = jugador_actual.puedoEdificarCasa(casilla);
                if (puedoEdificar && jugador_actual.tengoSaldo(casilla.getTituloPropiedad().getPrecioEdificar())) {
                    int costeEdificarCasa = casilla.edificarCasa();
                    jugador_actual.modificarSaldo(-costeEdificarCasa);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean edificarHotel(Casilla casilla) {
        if (casilla.soyEdificable()) {
            boolean sePuedeEdificar = casilla.sePuedeEdificarHotel(jugador_actual.factorEspeculador);
            if (sePuedeEdificar) {
                boolean puedoEdificar = jugador_actual.puedoEdificarHotel(casilla);
                if (puedoEdificar && jugador_actual.tengoSaldo(casilla.getTituloPropiedad().getPrecioEdificar())) {
                    int costeEdificarHotel = casilla.edificarHotel();
                    jugador_actual.modificarSaldo(-costeEdificarHotel);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public Sorpresa getCartaActual() {
        return cartaActual;
    }
    
    public Jugador getJugadorActual() {
        return jugador_actual;
    }  
    
    public boolean hipotecarPropiedad(Casilla casilla) {
        if (casilla.soyEdificable()) {
            boolean sePuedeHipotecar = !casilla.estaHipotecada();
            if (sePuedeHipotecar) {
                boolean puedoHipotecar = jugador_actual.puedoHipotecar(casilla);
                if (puedoHipotecar) {
                    int cantidadRecibida = casilla.hipotecar();
                    jugador_actual.modificarSaldo(cantidadRecibida);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public Casilla getCarcel() {
        return tablero.getCarcel();
    }
    
    public void inicializarJuego(ArrayList<String> nombres) {
        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
        salidaJugadores();        
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo) {
        boolean libre = false;
        
        if (metodo == MetodoSalirCarcel.TIRANDODADO) {
            GUIQytetet.Dado GUIdado = GUIQytetet.Dado.getInstance();
            int valorDado = GUIdado.nextNumber();
            //int valorDado = dado.tirar();
            libre = valorDado >= 5;
            System.out.println("[i] Tiras el dado y obtienes: " + valorDado);
            if (!libre) {
                System.out.println("[i] No sales de la cárcel.");
            }
        }
        
        else if (metodo == MetodoSalirCarcel.PAGANDOLIBERTAD) {
            boolean tengoSaldo = jugador_actual.pagarLibertad(precio_libertad);
            libre = tengoSaldo;
            if (libre) {
                System.out.println("[i] Pagas " + precio_libertad + " para conseguir la libertad.");
            }
        }
        
        if (libre) {
            jugador_actual.setEncarcelado(false);
        }
        
        return libre;
    }
    
    public boolean jugar() {    
        GUIQytetet.Dado dadoGUI = GUIQytetet.Dado.getInstance();
        int valorDado = dadoGUI.nextNumber();
        //int valorDado = dado.tirar();
        System.out.print("[i] " + jugador_actual.getNombre() + " tira el dado y obtiene: " + valorDado);
        
        Casilla casillaPosicion = jugador_actual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        boolean tienePropietario = jugador_actual.actualizarPosicion(nuevaCasilla);
        
        if (!nuevaCasilla.soyEdificable()) {
            if (nuevaCasilla.getTipoCasilla() == TipoCasilla.JUEZ) {
                System.out.println("[i] Has caido en la casilla número 15.");
                System.out.println("[!!] El juez te manda a la carcel!!");
                encarcelarJugador();
            }
            else if (nuevaCasilla.getTipoCasilla() == TipoCasilla.SORPRESA) {
                cartaActual = mazo.get(index_mazo % mazo.size());
                index_mazo +=1;
            }
        }
        
        return tienePropietario;
    }
    
    public HashMap<String, Integer> obtenerRanking() {
        HashMap<String, Integer> ranking = new HashMap();
        
        for (Jugador jugador: jugadores) {
            int capital = jugador.obtenerCapital();
            ranking.put(jugador.getNombre(), capital);
        }

        return ranking;
    }
    
    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas) {
        ArrayList<Casilla> props = new ArrayList();
        
        if (hipotecadas) {
            for (int i = 0; i < max_casillas; ++i) {
                if (tablero.obtenerCasillaNumero(i).estaHipotecada())
                    props.add(tablero.obtenerCasillaNumero(i));
            }
        }
        
        if (!hipotecadas) {
            for (int i = 0; i < max_casillas; ++i) {
                if (!tablero.obtenerCasillaNumero(i).estaHipotecada())
                    props.add(tablero.obtenerCasillaNumero(i));
            }
        }
        
        return props;
    }
    
    public void siguienteJugador() {      
        boolean no_repetir = false;
        for (int i = 0; i < jugadores.size(); ++i) {
            if ((jugadores.get(i) == jugador_actual) && (i != jugadores.size()-1) && !no_repetir) {
                jugador_actual = jugadores.get(i+1);
                no_repetir = true;
            }
            if ((jugadores.get(i) == jugador_actual) && (i == jugadores.size()-1) && !no_repetir) {
                jugador_actual = jugadores.get(0);
                no_repetir = true;
            }
        }
    }
    
    public boolean venderPropiedad(Casilla casilla){
        if (casilla.soyEdificable()) {
            boolean puedoVender = jugador_actual.puedoVenderPropiedad(casilla);
            if (puedoVender) {
                jugador_actual.venderPropiedad(casilla);
                return true;
            }
        }
        
        return false;
    }
    
    private void encarcelarJugador() {
        if (!jugador_actual.tengoCartaLibertad()) {
            Casilla casillaCarcel = tablero.getCarcel();
            jugador_actual.irACarcel(casillaCarcel);
        }
        
        else {
            Sorpresa carta = jugador_actual.devolverCartaLibertad();
            mazo.add(carta);
        }
    }
    
    private void inicializarCartasSorpresa() {
        mazo.add(new Sorpresa ("Te conviertes en especulador. Valor 3000.", 3000, TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa ("Tu perro encuentra una caja con unas joyas. Las vendes y ganas 500 euros.", 500, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Llave maestra de la cárcel. Tiene un solo uso.", 0, TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa ("Un paseo nunca viene mal, ve a la casilla 14.", 14, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Eres guapo/a y cada jugador/a te debe dar 100 euros.", 100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("El mantenimiento básico de tus casa y hoteles te cuesta por cada uno 80 euros.", -80, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Te conviertes en especulador. Valor 5000.", 5000, TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa ("Te invitan a una carrera no ilegal y por tanto vas a la salida.", 0, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Te pasas de listo en un control antidrogas y vas a la cárcel.", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Vuelves borracho a casa y al subir las escaleras te caes y te rompes las piernas. Los costes médicos te cuestan 450 euros.", -450, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Ganas un concurso de hostelería y recibes por cada casa y hotel 100 euros.", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Debido a tu gran generosidad regalas a cada jugador un dia en el campo de golf. Te cuesta 125 euros cada uno.", -125, TipoSorpresa.PORJUGADOR));
    }

    private void inicializarJugadores(ArrayList<String> nombres) {
        if (nombres.size() >= 2 && nombres.size() <= max_jugadores)
            for(String s : nombres) {
                Jugador aux = new Jugador(s);
                jugadores.add(aux);
            }
    }
    
    private void inicializarTablero() {
        tablero = new Tablero();
    }
    
    private void salidaJugadores() {
        for (Jugador j: jugadores)
            j.setCasillaActual(tablero.obtenerCasillaNumero(0));
    
        Random rand = new Random();
        int aux = rand.nextInt(jugadores.size());

        jugador_actual = jugadores.get(aux);
    }
    
    public ArrayList<Jugador> jugadores() {
        return jugadores;
    }
    
    public void getJugadores() {
        for(Jugador j : jugadores)
            System.out.println(j.toString());
    }
    
    public void showCartas() {
        for(Sorpresa s : mazo)
            System.out.println(s.toString());
    }
    
    public void showTablero() {
        System.out.println(tablero.toString());
    }
}
