package qytetetjava;

import java.util.ArrayList;

/**
 *
 * @author Angel
 */
public class Tablero {
    private static ArrayList<Casilla> casillas = new ArrayList();
    private static Casilla carcel;
    private int MAX_CASILLAS = 20;

    public Tablero() {
        inicializar();
    }

    @Override
    public String toString() {
        String txt = "";
        for (int i = 0; i < casillas.size(); ++i)
            txt = txt + casillas.get(i).toString() + "\n";
            
        return "Tablero:\n" + txt;
    }
    
    boolean esCasillaCarcel(int numeroCasilla) {
        return numeroCasilla == carcel.getNumeroCasilla();
    }
    
    public int size() {
        return casillas.size();
    }
    
    Casilla getCarcel() {
        return carcel;
    }
    
    Casilla obtenerCasillaNumero(int numeroCasilla) {
        return casillas.get(numeroCasilla);
    }
    
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento) {
        int aux = (desplazamiento + casilla.getNumeroCasilla()) % MAX_CASILLAS;
        return casillas.get(aux);
    }   
    
    private static void inicializar() {        
        Casilla salida = new OtraCasilla(0, TipoCasilla.SALIDA);
        casillas.add(salida);
        
        TituloPropiedad aa = new TituloPropiedad("Plaza Pisaobocata.", 50, -10, 200, 250);
        Casilla a = new Calle(1, 100, aa);
        casillas.add(a);
        
        Casilla s1 = new OtraCasilla(2, TipoCasilla.SORPRESA);
        casillas.add(s1);
        
        TituloPropiedad bb = new TituloPropiedad("Santa Iglesia del Pope Emeritus IV.", 50, -10, 200, 300);
        Casilla b = new Calle(3, 150, bb);
        casillas.add(b);
        
        TituloPropiedad cc = new TituloPropiedad("Avenida Turkelo.", 50, -10, 200, 350);
        Casilla c = new Calle(4, 200, cc);
        casillas.add(c);
        
        Casilla parking = new OtraCasilla(5, TipoCasilla.PARKING);
        casillas.add(parking);
        
        TituloPropiedad dd = new TituloPropiedad("Calle Go CSGO.", 65, 10, 450, 400);
        Casilla d = new Calle(6, 300, dd);
        casillas.add(d);
        
        TituloPropiedad ee = new TituloPropiedad("Rotonda Tour de Francia.", 65, 10, 450, 450);
        Casilla e = new Calle(7, 350, ee);
        casillas.add(e);
        
        Casilla impuesto = new OtraCasilla(8, TipoCasilla.IMPUESTO);
        casillas.add(impuesto);
        
        TituloPropiedad ff = new TituloPropiedad("Calle Corta.", 65, 10, 450, 500);
        Casilla f = new Calle(9, 400, ff);
        casillas.add(f);
        
        carcel = new OtraCasilla(10, TipoCasilla.CARCEL);
        casillas.add(carcel);
        
        TituloPropiedad gg = new TituloPropiedad("Calle Larga", 80, 15, 600, 550);
        Casilla g = new Calle(11, 500, gg);
        casillas.add(g);
        
        TituloPropiedad hh = new TituloPropiedad("Plaza de los Sacos.", 80, 15, 600, 600);
        Casilla h = new Calle(12, 550, hh);
        casillas.add(h);
        
        Casilla s2 = new OtraCasilla(13, TipoCasilla.SORPRESA);
        casillas.add(s2);
        
        TituloPropiedad ii = new TituloPropiedad("Esquina de Wito.", 80, 15, 600, 650);
        Casilla i = new Calle(14, 600, ii);
        casillas.add(i);
        
        Casilla juez = new OtraCasilla(15, TipoCasilla.JUEZ);
        casillas.add(juez);
        
        TituloPropiedad jj = new TituloPropiedad("Avenida Mandingo.", 100, 20, 800, 700);
        Casilla j = new Calle(16, 700, jj);
        casillas.add(j);
        
        TituloPropiedad kk = new TituloPropiedad("Negev Square.", 100, 20, 800, 750);
        Casilla k = new Calle(17, 750, kk);
        casillas.add(k);
        
        Casilla s3 = new OtraCasilla(18, TipoCasilla.SORPRESA);
        casillas.add(s3);
        
        TituloPropiedad ll = new TituloPropiedad("Map de_dust2.", 100, 20, 800, 750);
        Casilla l = new Calle(19, 800, ll);
        casillas.add(l);  
    }
}
