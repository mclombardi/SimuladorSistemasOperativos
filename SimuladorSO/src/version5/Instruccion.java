package version5;

import java.io.Serializable;

public class Instruccion implements Serializable{

    protected String aImprimir;
    protected boolean sincronica;
    protected int progreso;
    protected String logUnaEjec;
    protected int tiempoEsperado;
            
    public Instruccion(String aImprimir, boolean sincronica) {
        this.aImprimir = aImprimir;
        this.sincronica = sincronica;
        this.progreso = 0;
        this.logUnaEjec = "";
        this.tiempoEsperado = (int) Math.floor(Math.random()*(6)+5);  // Valor entre 5 y 10, ambos incluidos.
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public String getLogUnaEjec() {
        return logUnaEjec;
    }

    public int getProgreso() {
        return progreso;
    }
    
    public int tiempoEsperado() {
        return this.tiempoEsperado;
    }
    
    public boolean estaTerminado() {
        return this.tiempoEsperado == progreso;
    }
    
    public int run(int iterDisponibles) {
        this.logUnaEjec = "";

        int progresoAux = 0;
        for (int i = progreso; i < tiempoEsperado; i++) {
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
