package qytetetjava;

/**
 * 
 * @author Angel
 */
public class OtraCasilla extends Casilla{
    public OtraCasilla(int numero, TipoCasilla tipoc) {
        numeroCasilla = numero;
        tipo = tipoc;
    }
       
    @Override
    public String toString() {
        return "\nCasilla:" + "\n\tNumero Casilla = " + numeroCasilla + "\n\tTipo = " + tipo;
    } 

    @Override
    public TipoCasilla getTipoCasilla() {
        return tipo;
    }
    
    @Override
    boolean soyEdificable() {
        return tipo == TipoCasilla.CALLE;
    }
    
    @Override
    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    @Override
    public TituloPropiedad getTituloPropiedad() {
        return null;
    }

    @Override
    TituloPropiedad setPropietario(Jugador jugador) {
        return null;
    }

    @Override
    int calcularValorHipoteca() {
        return 0;
    }

    @Override
    int cancelarHipoteca() {
        return 0;
    }

    @Override
    int cobrarAlquiler() {
        return 0;
    }

    @Override
    int edificarCasa() {
        return 0;
    }

    @Override
    int edificarHotel() {
        return 0;
    }

    @Override
    boolean estaHipotecada() {
        return false;
    }

    @Override
    public int getCoste() {
        return coste;
    }

    @Override
    int getCosteHipoteca() {
        return 0;
    }

    @Override
    public int getNumCasas() {
        return 0;
    }

    @Override
    public int getNumHoteles() {
        return 0;
    }

    @Override
    int getPrecioEdificar() {
        return 0;
    }

    @Override
    int hipotecar() {
        return 0;
    }

    @Override
    boolean propietarioEncarcelado() {
        return false;
    }

    @Override
    boolean sePuedeEdificarCasa(int factor) {
        return false;
    }

    @Override
    boolean sePuedeEdificarHotel(int factor) {
        return false;
    }

    @Override
    void setNumCasas(int numCasas) {
    }

    @Override
    void setNumHoteles(int numHoteles) {
    }

    @Override
    boolean tengoPropietario() {
        return false;
    }

    @Override
    int venderTitulo() {
        return 0;
    }

    @Override
    public void setTitulo(TituloPropiedad titulo) {
    }
}
