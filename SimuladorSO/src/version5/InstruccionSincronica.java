package version5;

public class InstruccionSincronica extends Instruccion {

    private Recurso recurso;
    private boolean tengoElRecurso;
     
    public InstruccionSincronica(String aImprimir, Recurso recurso) {
        super(aImprimir, true);
        this.recurso = recurso;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public boolean tengoElRecurso() {
        return this.tengoElRecurso;
    }

    public int run(int iterDisponibles) {
        super.logUnaEjec = "";
        if (this.tengoElRecurso || !this.recurso.estaBloqueado()) {
            if (!this.tengoElRecurso) {
                this.recurso.adquirir();
                this.tengoElRecurso = true;
            }
            int progresoAux = 0;
            for (int i = progreso; i < this.tiempoEsperado; i++) {

                if (progresoAux < iterDisponibles) {
                    System.out.println(super.aImprimir);

                    super.logUnaEjec += aImprimir + "\n";

                    progresoAux++;
                    progreso++;
                }
            }
            if (progreso == tiempoEsperado) {
                this.recurso.liberar();
                this.tengoElRecurso = false; // false
            }
            return iterDisponibles - progresoAux;
        }
        return iterDisponibles;
    }

    @Override
    public String toString() {
        return this.aImprimir + " --- Usa " + this.recurso.getNombre();
    }

}
