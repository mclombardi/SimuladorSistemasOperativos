package version5;

import java.util.ArrayList;
import java.util.Iterator;

public class Proceso {

    private int pid;
    private String nombre;
    private ArrayList<Instruccion> instrucciones;
    private ArrayList<Recurso> recursos;
    private String estado;
    private int progreso;
    private int totalIns;
    private int particion;
    final static private int QUANTUM = 3;

    public Proceso(int pid, String nombre, ArrayList<Instruccion> instrucciones, ArrayList<Recurso> recursos, int particion) {
        this.pid = pid;
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.recursos = recursos;
        this.estado = "listo";
        this.progreso = 0;
        this.totalIns = instrucciones.size() * 5;
        this.particion = particion;
    }

    public int getParticion() {
        return particion;
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

    public void run(boolean[][] permisosRecursos, Usuario usuario) {
        int iterDisponibles = QUANTUM;
        ArrayList<Instruccion> lista = this.getInstrucciones();

        while (lista.size() > 0 && (this.progreso < totalIns) && iterDisponibles > 0) {
            Instruccion insActual = lista.get(0);
            if (insActual.isSincronica()) {
                InstruccionSincronica insSinc = (InstruccionSincronica) insActual;
                if (!permisoARecurso(permisosRecursos, usuario, insSinc.getRecurso())) {
                    setEstado("no permite");
                    return;
                }
            }
            int iterAux = iterDisponibles;
            iterDisponibles = insActual.run(iterDisponibles);
            progreso = progreso + (iterAux - iterDisponibles);

            if (iterDisponibles == iterAux) {
                setEstado("bloqueado");
                return;
            }

            if (progreso % 5 == 0) {
                lista.remove(0);     
            }
        }

        if (this.progreso >= totalIns) {
            setEstado("terminado");
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

}
