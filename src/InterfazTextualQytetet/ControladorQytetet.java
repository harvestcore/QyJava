/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazTextualQytetet;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import qytetetjava.Casilla;
import qytetetjava.Jugador;
import qytetetjava.MetodoSalirCarcel;
import qytetetjava.Qytetet;
import qytetetjava.TipoCasilla;


/**
 *
 * @author Angel
 */
public class ControladorQytetet {
    
    private Qytetet juego;
    private Jugador jugador_actual;
    private Casilla casilla_actual;
    private VistaTextualQytetet vista;
    
    HashMap<String, Integer> ranking;
    
    private static final Scanner keyboard = new Scanner (System.in);
    
    public ControladorQytetet() {
    }
    
    private boolean jugadorEnBancarrota() {
        return jugador_actual.bancarrota();
    }
    
    private boolean jugadorEncarcelado() {
        return jugador_actual.getEncarcelado();
    }
    
    private void endGame() {
        ranking = juego.obtenerRanking();
        vista.endGame(ranking, juego);
        System.exit(0);
    }
    
    private void pasarTurno() {
        juego.siguienteJugador();
        jugador_actual = juego.getJugadorActual();
    }
    
    private void gestionesInmobiliarias() {
        int propiedad_elegida;
        ArrayList<String> props;
        String atras = "Atrás.";
        Casilla casilla_elegida = null;
        int opcion;

        do {
            opcion = vista.menuGestionInmobiliaria();
            switch (opcion) {
                case 1:
                    props = jugador_actual.getPropiedades(1);
                    props.add(atras);
                    if (props.size() > 1) {
                        propiedad_elegida = vista.menuElegirPropiedad(props);
                        if (propiedad_elegida < props.size() - 1) {
                            casilla_elegida = jugador_actual.searchCasilla(props.get(propiedad_elegida));
                            if (casilla_elegida.getNumCasas() < 4) {
                                juego.edificarCasa(casilla_elegida);
                                vista.textoInmobiliario(casilla_elegida, jugador_actual, 1);
                            } else {
                                vista.textoInmobiliario(casilla_elegida, jugador_actual, 11);
                            }
                        }
                    } else {
                        vista.textoInmobiliario(casilla_elegida, jugador_actual, 6);
                    }                    
                    break;

                case 2:
                    props = jugador_actual.getPropiedades(2);
                    props.add(atras);
                    if (props.size() > 1) {
                        propiedad_elegida = vista.menuElegirPropiedad(props);
                        if (propiedad_elegida < props.size() - 1) {
                            casilla_elegida = jugador_actual.searchCasilla(props.get(propiedad_elegida));
                            if (casilla_elegida.getNumHoteles() < 4) {
                                juego.edificarHotel(casilla_elegida);
                                vista.textoInmobiliario(casilla_elegida, jugador_actual, 2);
                            } else {
                                vista.textoInmobiliario(casilla_elegida, jugador_actual, 12);
                            }
                        }
                    } else {
                        vista.textoInmobiliario(casilla_elegida, jugador_actual, 7);
                    }                    
                    break;

                case 3:
                    props = jugador_actual.getPropiedades(3);
                    props.add(atras);
                    if (props.size() > 1) {
                        propiedad_elegida = vista.menuElegirPropiedad(props);
                        if (propiedad_elegida < props.size() - 1) {
                            casilla_elegida = jugador_actual.searchCasilla(props.get(propiedad_elegida));
                            juego.venderPropiedad(casilla_elegida);
                            vista.textoInmobiliario(casilla_elegida, jugador_actual, 3);
                        }
                    } else {
                        vista.textoInmobiliario(casilla_elegida, jugador_actual, 8);
                    }
                    break;

                case 4:
                    props = jugador_actual.getPropiedades(4);
                    props.add(atras);
                    if (props.size() > 1) {
                        propiedad_elegida = vista.menuElegirPropiedad(props);
                        if (propiedad_elegida < props.size() - 1) {
                            casilla_elegida = jugador_actual.searchCasilla(props.get(propiedad_elegida));
                            juego.hipotecarPropiedad(casilla_elegida);
                            vista.textoInmobiliario(casilla_elegida, jugador_actual, 4);
                        }
                    } else {
                        vista.textoInmobiliario(casilla_elegida, jugador_actual, 9);
                    }                    
                    break;

                case 5:
                    props = jugador_actual.getPropiedades(5);
                    props.add(atras);
                    if (props.size() > 1) {
                        propiedad_elegida = vista.menuElegirPropiedad(props);
                        if (propiedad_elegida < props.size() - 1) {
                            casilla_elegida = jugador_actual.searchCasilla(props.get(propiedad_elegida));
                            juego.cancelarHipoteca(casilla_elegida);
                            vista.textoInmobiliario(casilla_elegida, jugador_actual, 5);
                        }
                    } else {
                        vista.textoInmobiliario(casilla_elegida, jugador_actual, 10);
                    }                    
                    break;
            }
        } while (opcion != 0);
    }
    
    private void gestionCarcel() {
        if (jugador_actual.getFactorEspeculador() == 2) {
            boolean xd = jugador_actual.pagarFianza();
            if (!xd) {
                int metodo = vista.menuSalirCarcel();
                switch (metodo) {
                    case 1:
                        if (juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD))
                            vista.jugadorLiberado(jugador_actual);
                        break;
                    case 2:
                        if (juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO))
                            vista.jugadorLiberado(jugador_actual);
                        break;
                }
            }
        } else {
            int metodo = vista.menuSalirCarcel();

            switch (metodo) {
                case 1:
                    if (juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD))
                        vista.jugadorLiberado(jugador_actual);
                    break;
                case 2:
                    if (juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO))
                        vista.jugadorLiberado(jugador_actual);
                    break;
            }   
        }
    }
    
    private boolean finTurno() {
        int queHacer;
        do {
            queHacer =  vista.menuFinTurno();
            switch (queHacer) {
                case 1:
                    vista.mostrarPropiedades(jugador_actual);
                    break;

                case 2:
                    gestionesInmobiliarias();
                    break;
                    
                case 3:
                    return true;
            }
        } while (queHacer != 3);
        
        return false;
    }
    
    private void updateGame() {
        jugador_actual = juego.getJugadorActual();
        casilla_actual = juego.getJugadorActual().getCasillaActual();
    }
    
    private void checkBancarrota() {
        if (jugadorEnBancarrota())
            endGame();
    }
    
    private boolean turnoJugador() {
        boolean terminar_turno = false;
        updateGame();
        vista.infoPrincipioTurno(jugador_actual, casilla_actual);
        vista.pause();
        
        if (jugadorEncarcelado()) {
            gestionCarcel();
        }
        
        if (!jugadorEncarcelado()) {
            boolean jugada = juego.jugar();
            if (jugada) {
                updateGame();
                vista.info(jugador_actual, casilla_actual);
                vista.pause();
            }
            
            if (casilla_actual.getTipoCasilla() == TipoCasilla.JUEZ) {
                jugador_actual.irACarcel(juego.getCarcel());
                updateGame();
                vista.info(jugador_actual, casilla_actual);
                vista.pause();
            }
            
            if (!jugadorEnBancarrota() && !jugadorEncarcelado()) {
                if (casilla_actual.getTipoCasilla() == TipoCasilla.CALLE) {
                    if (casilla_actual.getTituloPropiedad().getPropietario() == null) {
                        if (vista.elegirQuieroComprar()) {
                            boolean compra = juego.comprarTituloPropiedad();
                            if (compra) {
                                vista.compra(casilla_actual, jugador_actual);
                                checkBancarrota();
                                terminar_turno = finTurno();
                            } else {
                                terminar_turno = finTurno();
                            }
                            checkBancarrota();
                        } else {
                            terminar_turno = finTurno();
                        }
                    } else {
                        terminar_turno = finTurno();
                    }
                }
                
                if (casilla_actual.getTipoCasilla() == TipoCasilla.SORPRESA) {
                    boolean sorpresaAplicada = juego.aplicarSorpresa();
                    checkBancarrota();
                    if (sorpresaAplicada) {
                        if (!jugadorEncarcelado()) {
                            checkBancarrota();
                            if (!jugadorEnBancarrota()) {
                                if (casilla_actual.getTipoCasilla() == TipoCasilla.CALLE) {
                                    if (casilla_actual.getTituloPropiedad().getPropietario() == null) {
                                        if (vista.elegirQuieroComprar()) {
                                            boolean compra = juego.comprarTituloPropiedad();
                                            if (compra) {
                                                vista.compra(casilla_actual, jugador_actual);
                                                terminar_turno = finTurno();
                                            } else {
                                                terminar_turno = finTurno();
                                            }
                                            checkBancarrota();
                                        } else {
                                            terminar_turno = finTurno();
                                        }
                                    } else {
                                        terminar_turno = finTurno();
                                    }
                                } else {
                                    terminar_turno = finTurno();
                                }
                            }
                        }
                    }
                    terminar_turno = finTurno();
                }
                
                if (casilla_actual.getTipoCasilla() == TipoCasilla.CARCEL && !jugadorEncarcelado()) {
                    terminar_turno = finTurno();
                }
                
                if (casilla_actual.getTipoCasilla() == TipoCasilla.PARKING) {
                    terminar_turno = finTurno();
                }
                
                if (casilla_actual.getTipoCasilla() == TipoCasilla.IMPUESTO) {
                    terminar_turno = finTurno();
                }
                
                if (casilla_actual.getTipoCasilla() == TipoCasilla.SALIDA) {
                    terminar_turno = finTurno();
                }
                
                if (jugador_actual.tengoPropiedades() && !terminar_turno) {
                    terminar_turno = finTurno();
                }
            }
            if (jugadorEnBancarrota()) {
                return true;
            }
        }
        
        return false;
    }
    
    private void desarrolloJuego() {
        boolean fin = false;
        
        while(!fin) {
            if (turnoJugador())
                fin = true;
            pasarTurno();
        }
        endGame();
    }
    
    public Casilla elegirPropiedad(ArrayList<Casilla> propiedades) {
        vista.mostrar("\tCasilla\tTitulo");
        int seleccion;
        ArrayList<String> listaPropiedades= new ArrayList();
        for (Casilla casilla: propiedades) {
            listaPropiedades.add("\t" + casilla.getNumeroCasilla() + "\t" + casilla.getTituloPropiedad().getNombre()); 
        }
        seleccion = vista.menuElegirPropiedad(listaPropiedades);  
        
        return propiedades.get(seleccion);
    }
    
    private void inicializacionJuego() {
        juego = Qytetet.getQytetet();
        vista = new VistaTextualQytetet();
        
        vista.welcome();
        juego.inicializarJuego(vista.obtenerNombreJugadores());
        jugador_actual = juego.getJugadorActual();
        casilla_actual = jugador_actual.getCasillaActual();
        
        vista.showGame(juego, jugador_actual, casilla_actual);
    }
    
    public void executeGame() {
        inicializacionJuego();
        desarrolloJuego();
    }
}
