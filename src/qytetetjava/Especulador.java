package qytetetjava;

/**
 *
 * @author Angel
 */
public class Especulador extends Jugador {
    private int fianza;

    Especulador(Jugador jugador, int fianza) {
        this.encarcelado = jugador.getEncarcelado();
        this.nombre = jugador.getNombre();
        this.saldo = jugador.getSaldo();
        this.casilla_actual = jugador.getCasillaActual();
        this.propiedades = jugador.getArrayProps();
        this.cartaLibertad = jugador.getCartaLibertad();
        this.factorEspeculador = 2;
        this.fianza = fianza;
    }
    
    @Override
    protected void pagarImpuestos (int cantidad) {
        modificarSaldo(-cantidad/2);
    }
    
    @Override
    public void irACarcel(Casilla casilla) {
        if (!pagarFianza(fianza)) {
            setCasillaActual(casilla);
            setEncarcelado(true);
        }
    }
    
    @Override
    protected Especulador convertirme (int fianza) {
        return this;
    }
    
    public boolean pagarFianza(int cantidad) {
        if (saldo >= cantidad) {
            modificarSaldo(-cantidad);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Jugador:" + "\n\tNombre: " + nombre + "\n\tSaldo: " + saldo + "\n\tFactor: " + factorEspeculador + "\n\tFianza: " + fianza + "\n";
    }
}
