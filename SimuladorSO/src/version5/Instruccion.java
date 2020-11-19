package version5;

import version4.*;

public class Instruccion {
    protected String aImprimir;
    protected boolean sincronica;
    protected int progreso;
            
    public Instruccion(String aImprimir, boolean sincronica) {
        this.aImprimir = aImprimir;
        this.sincronica = sincronica;
        this.progreso = 0;
    }
    
    public int run(int iterDisponibles) {
        int progresoAux = 0;
        for (int i = progreso; i < 5; i++) {
            if (progresoAux < iterDisponibles) {
                System.out.println(this.aImprimir);
                progresoAux++;
                progreso++;
            }
        }
        return iterDisponibles - progresoAux;
    }

    public boolean isSincronica() {
        return sincronica;
    }
}
