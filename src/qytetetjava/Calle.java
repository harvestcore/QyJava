package qytetetjava;

/**
 * 
 * @author Angel
 */
public class Calle extends Casilla{
    private int numHoteles = 0;
    private int numCasas = 0;
    private TituloPropiedad titulo = null;
    
    public Calle(int numero, int costec, TituloPropiedad titulop) {
        coste = costec;
        numeroCasilla = numero;
        titulo = titulop;
        tipo = TipoCasilla.CALLE;
        titulo.setCalle(this);
    }
    
    @Override
    public TituloPropiedad getTituloPropiedad() {
        return titulo;
    }
    
    @Override
    TituloPropiedad setPropietario(Jugador jugador) {
        titulo.setPropietario(jugador);
        return titulo;
    }
    
    @Override
    public TipoCasilla getTipoCasilla() {
        return tipo;
    }
    
    @Override
    int calcularValorHipoteca() {
        return titulo.getHipotecaBase() + (int) (numCasas * 0.5 * titulo.getHipotecaBase() + numHoteles * titulo.getHipotecaBase());
    }
    
    @Override
    int cancelarHipoteca() {
        titulo.setHipotecada(false);
        return getCosteHipoteca();
    }
    
    @Override
    int cobrarAlquiler() {
        int costeAlquilerBase = titulo.getAlquilerBase();
        int costeAlquiler = costeAlquilerBase + (int)(numCasas*0.5 + numHoteles*2);
        titulo.cobrarAlquiler(costeAlquiler);
        
        return costeAlquiler;
    }
    
    @Override
    int edificarCasa() {
        setNumCasas(numCasas + 1);
        return getPrecioEdificar();
    }
    
    @Override
    int edificarHotel() {
        setNumHoteles(numHoteles + 1);
        return getPrecioEdificar();
    }
    
    @Override
    boolean estaHipotecada() {
        return titulo.isHipotecada();
    }
    
    @Override
    public int getCoste() {
        return coste;
    }
    
    @Override
    int getCosteHipoteca() {
        return (int)(calcularValorHipoteca() + (calcularValorHipoteca()*0.1));
    }
    
    @Override
    public int getNumeroCasilla() {
        return numeroCasilla;
    }
    
    @Override
    public int getNumCasas() {
        return numCasas;
    }
    
    @Override
    public int getNumHoteles() {
        return numHoteles;
    }
    
    @Override
    int getPrecioEdificar() {
        return titulo.getPrecioEdificar();
    }
    
    @Override
    int hipotecar() {
        titulo.setHipotecada(true);
        return calcularValorHipoteca();
    }
    
    @Override
    boolean propietarioEncarcelado() {
        return titulo.propietarioEncarcelado();
    }
    
    @Override
    boolean sePuedeEdificarCasa(int factor) {
        return numCasas < (4 * factor);
    }
    
    @Override
    boolean sePuedeEdificarHotel(int factor) {
        return numHoteles < (4 * factor);
    }
    
    @Override
    void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }

    @Override
    void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }
    
    @Override
    boolean soyEdificable() {
        return tipo == TipoCasilla.CALLE;
    }
    
    @Override
    boolean tengoPropietario() {
        return titulo.tengoPropietario();
    }
    
    @Override
    int venderTitulo() {
        titulo.setPropietario(null);
        setNumCasas(0);
        setNumHoteles(0);
        int precioCompra = (int) (coste + (numCasas + numHoteles) * titulo.getFactorRevalorizacion());
        return (int) (precioCompra + titulo.getFactorRevalorizacion() * precioCompra);
    }

    @Override
    public void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "\nCasilla:" + "\n\tNumero Casilla = " + numeroCasilla + "\n\tCoste = " + coste + "\n\tNumero Hoteles = " + numHoteles + "\n\tNumero Casas = " + numCasas + "\n\tTipo = " + tipo + titulo;
    }
}
