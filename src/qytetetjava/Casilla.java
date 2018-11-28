package qytetetjava;

/**
 *
 * @author Angel
 */
public abstract class Casilla {
    protected int numeroCasilla = -1;
    protected TipoCasilla tipo;
    protected int coste = 1500;
  
    abstract public TipoCasilla getTipoCasilla();
    public abstract TituloPropiedad getTituloPropiedad();    
    abstract TituloPropiedad setPropietario(Jugador jugador);    
    abstract int calcularValorHipoteca();
    abstract int cancelarHipoteca();    
    abstract int cobrarAlquiler();    
    abstract int edificarCasa();    
    abstract int edificarHotel();    
    abstract boolean estaHipotecada();    
    abstract public int getCoste();    
    abstract int getCosteHipoteca();    
    abstract public int getNumeroCasilla();    
    abstract public int getNumCasas();    
    abstract public int getNumHoteles();    
    abstract int getPrecioEdificar();    
    abstract int hipotecar();    
    abstract boolean propietarioEncarcelado();    
    abstract boolean sePuedeEdificarCasa(int factor);    
    abstract boolean sePuedeEdificarHotel(int factor);    
    abstract void setNumCasas(int numCasas);
    abstract void setNumHoteles(int numHoteles);    
    abstract boolean soyEdificable();    
    abstract boolean tengoPropietario();    
    abstract int venderTitulo();
    public abstract void setTitulo(TituloPropiedad titulo);
}
