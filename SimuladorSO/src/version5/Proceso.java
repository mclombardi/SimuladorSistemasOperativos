package version5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Proceso implements Serializable{

    private int pid;
    private String nombre;
    private ArrayList<Instruccion> instrucciones;
    private ArrayList<Recurso> recursos;
    private String estado;
    private int progreso;
    private int totalIns;
    private int particion;
    final static private int QUANTUM = 3;
    private String logProc;

    private ArrayList<Instruccion> insEnEjec;

    public Proceso(int pid, String nombre, ArrayList<Instruccion> instrucciones, ArrayList<Recurso> recursos, int particion) {
        this.pid = pid;
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.recursos = recursos;
        this.estado = "listo";
        this.progreso = 0;
        this.totalIns = 0;
        this.particion = particion;
        this.logProc = "";

        this.insEnEjec = new ArrayList<>();
        calcularTotalInstrucciones();
    }
    
    private void calcularTotalInstrucciones() {
        for (int i = 0; i < instrucciones.size(); i++) {
           Instruccion instruccion = instrucciones.get(i);
           this.totalIns = this.totalIns + instruccion.tiempoEsperado();
        }
        System.out.println(totalIns);
    }

    public int getParticion() {
        return particion;
    }

    public String getNombre() {
        return nombre;
    }
    
    public Recurso proximoRecursoNecesario() {
        Instruccion proximIns = insEnEjec.get(0);
        if (proximIns.sincronica) {
            InstruccionSincronica sinc = (InstruccionSincronica) proximIns;
            return sinc.getRecurso();
        }
        
        return null;
    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }

    public int getPid() {
        return this.pid;
    }

    public String getEstado() {
        return estado;
    }

    public boolean terminado() {
        return this.progreso == this.totalIns;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogProc() {
        return logProc;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public void setInsEnEjec(ArrayList<Instruccion> insEnEjec) {
        this.insEnEjec = insEnEjec;
    }

    public void run(boolean[][] permisosRecursos, Usuario usuario) { 
        this.logProc = "";

        int iterDisponibles = QUANTUM;

        if (this.progreso == 0) {
            this.insEnEjec = (ArrayList<Instruccion>) this.instrucciones.clone();
        }

        while (this.insEnEjec.size() > 0 && (this.progreso < totalIns) && iterDisponibles > 0) {
            Instruccion insActual = this.insEnEjec.get(0);
            if (insActual.isSincronica()) {
                InstruccionSincronica insSinc = (InstruccionSincronica) insActual;
                if (!permisoARecurso(permisosRecursos, usuario, insSinc.getRecurso())) {
                    setEstado("no permite");
                    this.progreso = 0;
                    return;
                }
            }
            
            int iterAux = iterDisponibles;
            iterDisponibles = insActual.run(iterDisponibles);

            this.logProc += insActual.getLogUnaEjec();

            progreso = progreso + (iterAux - iterDisponibles);

            if (iterDisponibles == iterAux) {
                setEstado("bloqueado");
                return;
            }

            if (insActual.estaTerminado()) {
                insActual.setProgreso(0);
                this.insEnEjec.remove(0);
            }
        }

        if (this.progreso >= totalIns) {
            setEstado("terminado");
            progreso = 0;
            return;
        }
        setEstado("esperando CPU");

    }

    public Proceso copiar() {
        ArrayList<Instruccion> instruccionesCopia = (ArrayList<Instruccion>) instrucciones.clone();
        ArrayList<Recurso> recursosCopia = (ArrayList<Recurso>) recursos.clone();

        return new Proceso(pid, nombre, instruccionesCopia, recursosCopia, particion);
    }

    private boolean permisoARecurso(boolean[][] permisosRecursos, Usuario usuario, Recurso recurso) {
        return permisosRecursos[usuario.getUid()][recurso.getRid()];
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
