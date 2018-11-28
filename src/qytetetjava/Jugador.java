/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qytetetjava;

import java.util.ArrayList;

/**
 * 
 * @author Angel
 */
public class Jugador {
    protected boolean encarcelado = false;
    protected String nombre;
    protected int saldo = 7500;
    protected int factorEspeculador = 1;
    protected Casilla casilla_actual = null;
    protected ArrayList<TituloPropiedad> propiedades = new ArrayList();
    protected Sorpresa cartaLibertad = null;
    
    public Jugador () {
    }
    
    public String getEncarceladoString() {
        if (encarcelado)
            return "Sí";
        else
            return "No";
    }
    
    public String getSaldoString() {
        return "" + saldo;
    }
    
    public ArrayList<TituloPropiedad> getArrayProps() {
        return propiedades;
    }
    
    public String getFactorString() {
        return "" + factorEspeculador;
    }
    
    public String getCasillaString() {
        if (casilla_actual.getTipoCasilla() == TipoCasilla.CALLE)
            return "" + casilla_actual.getNumeroCasilla();
        else
            return "" + casilla_actual.getTipoCasilla();
    }
    
    public String getNumPropiedadesString() {
        return "" + propiedades.size();
    }
    
    public String getCartaString() {
        if (cartaLibertad != null)
            return "Sí";
        else
            return "No";
    }
    
    public Jugador(Jugador otro) {
        encarcelado = otro.getEncarcelado();
        nombre = otro.getNombre();
        saldo = otro.getSaldo();
        factorEspeculador = otro.getFactorEspeculador();
        casilla_actual = otro.getCasillaActual();
        propiedades = otro.propiedades;
        cartaLibertad = otro.getCartaLibertad();
    }
    
    public Jugador (String nombre) {
        this.nombre = nombre;
    }

    public int getFactorEspeculador() {
        return factorEspeculador;
    }
    
    public Casilla getCasillaActual() {
        return casilla_actual;
    }
    
    public Sorpresa getCartaLibertad() {
        return cartaLibertad;
    }
    
    public boolean getEncarcelado() {
        return encarcelado;
    }
    
    public boolean tengoPropiedades() {
        return propiedades.size() > 0;
    }

    public String getNombre() {
        return nombre;
    }
    
    boolean actualizarPosicion(Casilla casilla) {
        if (casilla.getNumeroCasilla() < casilla_actual.getNumeroCasilla()) {
            modificarSaldo(Qytetet.saldo_salida);
            System.out.println("\n[i] Pasas por la salida y recibes 1000 euros.");
            System.out.println("\n[i] Saldo actual: " + saldo);
        }            
        
        setCasillaActual(casilla);
        
        if (casilla.soyEdificable()) {
            if (casilla.tengoPropietario()) {
                encarcelado = casilla.propietarioEncarcelado();
                if (!encarcelado) {
                    int costeAlquiler = casilla.cobrarAlquiler();
                    modificarSaldo(-costeAlquiler);
                    System.out.println("\n[i] " + nombre + " paga a " + casilla.getTituloPropiedad().getPropietario().getNombre() + " " + costeAlquiler + " de alquiler.");
                }
            }
            
            return true;
        }
        else if (casilla.getTipoCasilla() == TipoCasilla.IMPUESTO) {
            int coste = casilla.getCoste();
            pagarImpuestos(coste);
            System.out.println("\n\n[!!] ¡¡Impuesto!! \n[i] Tienes que pagar: " + coste);
            System.out.println("\n[i] Saldo actual: " + saldo);
            return true;
        } else if (casilla.getTipoCasilla() == TipoCasilla.PARKING) {
            return true;
        } else if (casilla.getTipoCasilla() == TipoCasilla.SORPRESA) {
            return true;
        } else if (casilla.getTipoCasilla() == TipoCasilla.SALIDA) {
            return true;
        } else if (casilla.getTipoCasilla() == TipoCasilla.JUEZ) {
            return true;
        } else if (casilla.getTipoCasilla() == TipoCasilla.CARCEL) {
            return true;
        }
        
        return false;
    }
    
    public boolean pagarFianza() {
        boolean libre = pagarLibertad(Qytetet.precio_libertad);
        if (libre) {
            System.out.println("[i] Pagas " + Qytetet.precio_libertad + " para conseguir la libertad.");
        }
        return libre;
    }
    
    boolean comprarTitulo() {
        if (casilla_actual.soyEdificable()) {
            boolean tengoPropietario = casilla_actual.tengoPropietario();
            if (!tengoPropietario) {
                int costeCompra = casilla_actual.getCoste();
                if (tengoSaldo(costeCompra)) {
                    TituloPropiedad aux = casilla_actual.setPropietario(this);
                    propiedades.add(aux);
                    modificarSaldo(-costeCompra);
                    return true;
                } else {
                    System.out.println("\n[i] No tienes suficiente dinero para comprar el título.");
                }
            }
        }
            
        return false;
    }
    
    public int getSaldo() {
        return saldo;
    }
    
    Sorpresa devolverCartaLibertad() {
        Sorpresa aux = cartaLibertad;
        cartaLibertad = null;
        return aux;
    }
    
    public void irACarcel (Casilla toGo) {
        setCasillaActual(toGo);
        setEncarcelado(true);
    }
    
    void modificarSaldo(int cantidad) {
        saldo += cantidad;        
    }
    
    int obtenerCapital() {
        int capital = saldo;
        
        for(TituloPropiedad t : propiedades) {
            if (t.getCalle().getNumCasas() > 0)
                capital += (t.getCalle().getNumCasas() * t.getCalle().getPrecioEdificar());
            
            if (t.getCalle().getNumHoteles() > 0)
                capital += (t.getCalle().getNumHoteles() * t.getCalle().getPrecioEdificar());
            
            if (t.isHipotecada())
                capital -= t.getHipotecaBase();
        }
        
        return capital;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada) {
        ArrayList<TituloPropiedad> aux = new ArrayList();
        
        if (hipotecada) {
            for(TituloPropiedad t : propiedades)
                if (t.isHipotecada())
                    aux.add(t);
        }
        
        if (!hipotecada) {
            for(TituloPropiedad t : propiedades)
                if (!t.isHipotecada())
                    aux.add(t);
        }
        
        return aux;
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad) {
        int numeroTotal = cuantasCasasHotelesTengo();
        modificarSaldo(cantidad * numeroTotal);
    }   
    
    public boolean pagarLibertad(int cantidad) {
        boolean tengoSaldo = tengoSaldo(cantidad);
        if (tengoSaldo) {
            modificarSaldo(-cantidad);
            return true;
        }
        
        return false;
    }
    
    boolean puedoEdificarCasa(Casilla casilla) {
        boolean esMia = esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        
        if (esMia) {
            int costeEdificarCasa = casilla.getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificarCasa);
        }
        
        return tengoSaldo;
    }
    
    boolean puedoEdificarHotel(Casilla casilla) {
        boolean esMia = esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        
        if (esMia) {
            int costeEdificarHotel = casilla.getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificarHotel);
        }
        
        return tengoSaldo;
    }

    boolean puedoHipotecar(Casilla casilla) {
        return esDeMiPropiedad(casilla);
    }
    
    boolean puedoPagarHipoteca(Casilla casilla) {
        return saldo >= (int)(casilla.getCosteHipoteca() + casilla.calcularValorHipoteca() * 0.1);
    }
 
    boolean puedoVenderPropiedad(Casilla casilla) {
        return esDeMiPropiedad(casilla) && !casilla.estaHipotecada();
    }
    
    void setCartaLibertad(Sorpresa carta) {
        cartaLibertad = carta;
    }
    
    void setCasillaActual(Casilla casilla) {
        casilla_actual = casilla;
    }
    
    void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }
    
    boolean tengoCartaLibertad() {
        return cartaLibertad != null;
    }
    
    void venderPropiedad(Casilla casilla) {
        int precioVenta = casilla.venderTitulo();
        modificarSaldo(precioVenta);
        eliminarDeMisPropiedades(casilla);
    }
    
    protected int cuantasCasasHotelesTengo() {
        int counter = 0;
        for (TituloPropiedad t : propiedades) {
           counter += t.getCalle().getNumCasas();
           counter += t.getCalle().getNumHoteles();
       }
        
        return counter;
    }
    
    protected void eliminarDeMisPropiedades(Casilla casilla) {
        for (int i = 0; i < propiedades.size(); ++i)
            if (casilla.getTituloPropiedad() == propiedades.get(i))
                propiedades.remove(i);
    }
    
    public boolean esDeMiPropiedad(Casilla casilla) {        
         for(TituloPropiedad t : propiedades)
             if (t.getCalle() == casilla)
                 return true;
        
        return false;
    }
    
    public boolean tengoSaldo(int cantidad) {
        return saldo >= cantidad;
    }
    
    public boolean bancarrota() {
        return saldo < 0;
    }
    
    public ArrayList<String> getPropiedades (int  opcion) {
        ArrayList<String> props = new ArrayList();
        
        switch (opcion) {                                    
            case 1:
                for (TituloPropiedad tit: propiedades) {
                    if (tit.getCalle().getNumCasas() < (4*factorEspeculador))
                        props.add(tit.getNombre());
                }
                break;

            case 2:
                for (TituloPropiedad tit: propiedades) {
                    if (tit.getCalle().getNumHoteles() < (4*factorEspeculador))
                        props.add(tit.getNombre());
                }
                break;

            case 3:
                for (TituloPropiedad tit: propiedades) {
                    props.add(tit.getNombre());
                }
                break;

            case 4:
                ArrayList<TituloPropiedad> props_not_hipo = obtenerPropiedadesHipotecadas(false);
                for (TituloPropiedad tit: props_not_hipo) {
                    props.add(tit.getNombre());
                }
                break;

            case 5:
                ArrayList<TituloPropiedad> props_hipo = obtenerPropiedadesHipotecadas(true);
                for (TituloPropiedad tit: props_hipo) {
                    props.add(tit.getNombre());
                }
                break;
        }

        return props;
    }
    
    public Casilla searchCasilla(String nombre) {
        Casilla ret = null;
        
        for (TituloPropiedad tit: propiedades) {
            if (tit.getNombre().equals(nombre))
                ret = tit.getCalle();
        }
        
        return ret;
    }
    
    protected void pagarImpuestos(int cantidad) {
        modificarSaldo(-cantidad);
    }
    
    protected Especulador convertirme(int fianza) {
        Especulador aux = new Especulador(this, fianza);
        return aux;
    }
    
    @Override
    public String toString() {
        return "Jugador:" + "\n\tNombre: " + nombre + "\n\tSaldo: " + saldo + "\n\tFactor: " + factorEspeculador + "\n";
    }

}
