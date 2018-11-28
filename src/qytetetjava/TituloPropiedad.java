package qytetetjava;

/**
 *
 * @author Angel
 */
public class TituloPropiedad {
    private String nombre = null;
    private boolean hipotecada = false;
    private int alquilerBase = -1;
    private float factorRevalorizacion = -1;
    private int hipotecaBase = -1;
    private int precioEdificar = -1;
    private Calle casilla = null;
    private Jugador propietario = null;

    public TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar) {
        this.nombre = nombre;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
    }

    void cobrarAlquiler(int coste) {
        propietario.modificarSaldo(coste);
    }

    public int getAlquilerBase() {
        return alquilerBase;
    }

    public float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    public int getHipotecaBase() {
        return hipotecaBase;
    }
    
    public boolean isHipotecada() {
        return hipotecada;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getPrecioEdificar() {
        return precioEdificar;
    }

    public boolean propietarioEncarcelado() {
        return propietario.getEncarcelado();
    }
    
    void setCalle(Calle casilla) {
        this.casilla = casilla;
    }
    
    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    
    void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }
    
    public boolean tengoPropietario() {
        return propietario != null;
    }
    
    public Jugador getPropietario() {
        return propietario;
    }
    
    Casilla getCalle() {
        return casilla;
    }

    @Override
    public String toString() {
        return "\nTitulo Propiedad:" + "\n\tNombre = " + nombre + "\n\tHipotecada = " + hipotecada + "\n\tAlquiler Base = " + alquilerBase + "\n\tFactorRevalorizacion = " + factorRevalorizacion + "\n\tHipotecaBase = " + hipotecaBase + "\n\tPrecio Edificar = " + precioEdificar;
    }
}
