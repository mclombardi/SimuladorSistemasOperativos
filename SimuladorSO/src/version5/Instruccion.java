package version5;

import java.util.ArrayList;
import java.util.Iterator;

public class Instruccion {

    protected String aImprimir;
    protected boolean sincronica;
    protected int progreso;
    protected String logUnaEjec;

    public Instruccion(String aImprimir, boolean sincronica) {
        this.aImprimir = aImprimir;
        this.sincronica = sincronica;
        this.progreso = 0;
        this.logUnaEjec = "";
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public String getLogUnaEjec() {
        return logUnaEjec;
    }

    public int run(int iterDisponibles) {
        this.logUnaEjec = "";

        int progresoAux = 0;
        for (int i = progreso; i < 5; i++) {
            if (progresoAux < iterDisponibles) {

                this.logUnaEjec += this.aImprimir + "\n";

                System.out.println(this.aImprimir);
                progresoAux++;
                this.progreso++;
            }
        }
        return iterDisponibles - progresoAux;
    }

    public boolean isSincronica() {
        return sincronica;
    }

    @Override
    public String toString() {
        return this.aImprimir;
    }
}
