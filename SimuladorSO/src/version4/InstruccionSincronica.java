package version4;

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

    public Integer getProgreso() {
        return this.progreso;
    }

    public boolean tengoElRecurso() {
        return this.tengoElRecurso;
    }

    public int run(int iterDisponibles) {
        if (!this.recurso.estaBloqueado() || this.tengoElRecurso) {
            if (!this.tengoElRecurso) {
//                this.recurso.adquirir();
                this.tengoElRecurso = true;
            }
            int progresoAux = 0;
            for (int i = progreso; i < 5; i++) {
                if (progresoAux < iterDisponibles) {
                    System.out.println(super.aImprimir);
                    progresoAux++;
                    progreso++;
                }
            }
            if (progreso == 5) {
//                this.recurso.liberar();
                this.tengoElRecurso = false;
            }
            return iterDisponibles - progresoAux;
        }
        return iterDisponibles;
    }

}
