package version5;

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class SistemaOperativo {

    private boolean[][] permisosRecursos; // [usuarios][recursos]
    private boolean[][] permisosProgramas; // [usuarios][programas]

    private int indiceUsuarios;
    private int indiceRecursos;
    private int indiceProcesos;

    private ArrayList<Instruccion> instrucciones;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Recurso> recursos;
    private ArrayList<Proceso> procesos; // todos los procesos

    // SE MODIFICAN EN CADA ITER:
    private CopyOnWriteArrayList<Proceso> procesosListos;
    private CopyOnWriteArrayList<Proceso> procesosBloqueados;
    private ArrayList<Proceso>[] memoria;

    private String log;

    public SistemaOperativo() {
        this.indiceUsuarios = 0; // los indices indican donde voy a agregar el prox usuario/recurso/proceso
        this.indiceRecursos = 0;
        this.indiceProcesos = 0;
        this.permisosRecursos = new boolean[0][0];
        this.permisosProgramas = new boolean[0][0];
        this.usuarios = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.procesos = new ArrayList<>();
        this.instrucciones = new ArrayList<>();
        this.procesosListos = new CopyOnWriteArrayList<>();
        this.procesosBloqueados = new CopyOnWriteArrayList<>();

        this.memoria = new ArrayList[5];
        for (int i = 1; i < this.memoria.length; i++) {
            this.memoria[i] = new ArrayList<>();

        }
        this.log = "";
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public boolean[][] getPermisosRecursos() {
        return permisosRecursos;
    }

    public boolean[][] permisosProgramas() {
        return permisosRecursos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }

    public ArrayList<version5.Proceso> getProcesos() {
        return procesos;
    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    // -------------------------------------------------------------------------
    public void crearInstruccionAsincronica(String aImprimir) {
        InstruccionAsincronica ia = new InstruccionAsincronica(aImprimir);
        instrucciones.add(ia);
    }

    public void crearInstruccionSincronica(String aImprimir, Recurso recurso) {
        InstruccionSincronica is = new InstruccionSincronica(aImprimir, recurso);
        instrucciones.add(is);
    }

    public void crearProceso(String nombre, ArrayList<Instruccion> instrucciones, int particion) {
        ArrayList<Recurso> recursos = obtenerRecursos(instrucciones);
        Proceso proceso = new Proceso(indiceProcesos, nombre, instrucciones, recursos, particion);
        this.procesos.add(proceso);
        this.indiceProcesos++;

    }

    public ArrayList<Recurso> obtenerRecursos(ArrayList<Instruccion> instrucciones) {
        ArrayList<Recurso> recursos = new ArrayList<>();
        for (Instruccion ins : instrucciones) {
            try {
                InstruccionSincronica is = (InstruccionSincronica) ins;
                recursos.add(is.getRecurso());
            } catch (Exception e) {
                continue; // rompe todo?
            }
        }
        return recursos;
    }

    public void crearRecurso(String nombre) {
        Recurso recurso = new Recurso(this.indiceRecursos, 1, nombre);
        this.recursos.add(recurso);
        this.indiceRecursos++;
        //actualizarMatPermisosRecursos();
    }

    public void crearUsuario(String nombre) {
        Usuario usuario = new Usuario(this.indiceUsuarios, nombre);
        this.usuarios.add(usuario);
        this.indiceUsuarios++;
        //actualizarMatPermisosRecursos();
        //expandirPermisosProgramas(1, 0); // al sumarle el indice, la nueva matriz va a quedar mas grande
    }

    public void crearUsuarioYDarPermisos(String nombre, int[] indRecursos, int[] indProgramas) {
        crearUsuario(nombre);
        Usuario usuarioAgregado = this.usuarios.get(this.usuarios.size() - 1);// consigue el ultimo usuario

        if (this.recursos.size() > 0 && this.usuarios.size() > 0) {
            expandirMatrizDePermisosDeRecursos();
            //expandirMatrizDePermisosDeProgramas();

            /*for (int i = 0; i < indProgramas.length; i++) {
                Proceso programa = this.procesos.get(indProgramas[i]);
                this.permisosProgramas[usuarioAgregado.getUid()][programa.getPid()] = true;
            }*/
            for (int i = 0; i < indRecursos.length; i++) {
                Recurso recurso = this.recursos.get(indRecursos[i]);
                this.permisosRecursos[usuarioAgregado.getUid()][recurso.getRid()] = true;
            }
        }
    }

    // -------------------------------------------------------------------------
    private void expandirMatrizDePermisosDeRecursos() {
        // pre:this.recursos.size() > 0 && this.usuarios.size() > 0

        boolean[][] aux = this.permisosRecursos;
        this.permisosRecursos = new boolean[this.usuarios.size()][this.recursos.size()];
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                this.permisosRecursos[i][j] = aux[i][j];
            }
        }
    }

    private void expandirMatrizDePermisosDeProgramas() {
        // copiar el otro metodo si anda
    }

    public void revisarPermisosProgramas(Usuario usuario) {
        Iterator<Proceso> it = this.procesosListos.iterator();
        while (it.hasNext()) {
            Proceso aVerificar = it.next();
            if (!permisoAPrograma(usuario, aVerificar)) {
                this.procesosListos.remove(aVerificar); // o con id?
            }
        }
    }

    private boolean permisoAPrograma(Usuario usuario, Proceso proceso) {
        return this.permisosProgramas[usuario.getUid()][proceso.getPid()];
    }

    // -------------------------------------------------------------------------
    public void correrProcesos(ArrayList<Proceso> procesosAEjecutar, Usuario usuario) {
        cargarMemoria(procesosAEjecutar);

        while (!memoriaVacia()) {

            this.procesosListos = extraerProcesosDeMemoria();

            //revisarPermisosProgramas(usuario);
            while (this.procesosListos.size() > 0) {

                Proceso procActual = this.procesosListos.get(0);

                procActual.run(permisosRecursos, usuario);

                this.log += procActual.getLogProc(); // esto  funciona ?

                switch (procActual.getEstado()) {
                    case "esperando CPU":
                        this.procesosListos.remove(0);
                        this.procesosListos.add(procActual);
                        break;
                    case "bloqueado":
                        procesosBloqueados.add(procActual);
                        this.procesosListos.remove(0);
                        break;
                    case "terminado":
                        this.procesosListos.remove(0);
                        desencolarProcesoDeParticion(procActual.getParticion());
                        //procActual.setProgreso(0);
                        break;
                    case "no permite":
                        this.log += "PERMISO DENEGADO -- El usuario no tiene acceso a todos los recursos necesarios para ejecutar la siguiente instrucción del programa " 
                                + this.procesosListos.get(0).getNombre() + ". \n ";
                        this.procesosListos.remove(0);
                        break;
                    default:
                        break;
                }
                desbloquearProcesos();
            }

        }

        if (!this.procesosBloqueados.isEmpty()) {
            System.out.println("DEADLOCK");
            this.log += "--DEADLOCK--\n";
            this.procesosBloqueados.clear();
        }

        System.out.println(this.log += "------------");
        this.log += "------------ \n";

        // va a terminar cuando la memoria esté vacía. Esto implica que todos los procs a ejecutar fueron insertados. 
        // Por lo tanto, si no hay deadlock, procesosListos y procesosBloqueados tamb van a estar vacias
    }

    private void desencolarProcesoDeParticion(int particion) {
        if (!this.memoria[particion].isEmpty()) {
            Proceso nuevoProc = this.memoria[particion].get(0);
            this.memoria[particion].remove(0);
            this.procesosListos.add(nuevoProc);
        }
    }

    private CopyOnWriteArrayList<Proceso> extraerProcesosDeMemoria() {

        CopyOnWriteArrayList<Proceso> procesosACorrer = new CopyOnWriteArrayList<>();

        int cont = 0;
        int i = indiceAleatorio();

        while (cont < this.memoria.length) {
            int indice = i % 5;
            if (indice != 0) {
                if (!this.memoria[indice].isEmpty()) {
                    procesosACorrer.add(this.memoria[indice].get(0));
                    this.memoria[indice].remove(0);
                }
            }
            i++;
            cont++;
        }
        return procesosACorrer;
    }

    private void desbloquearProcesos() {
        for (Proceso proceso : this.procesosBloqueados) {
            boolean recursosLibres = true;
            for (Recurso recurso : proceso.getRecursos()) {
                recursosLibres = recursosLibres && (recurso.estaBloqueado() == 0);
            }
            if (recursosLibres) {
                procesosBloqueados.remove(proceso);
                procesosListos.add(proceso);
            }
        }
    }

    // -------------------------------------------------------------------------
    private boolean memoriaVacia() {
        boolean vacia = true;
        for (int i = 1; i < this.memoria.length && vacia; i++) {
            vacia = vacia && this.memoria[i].isEmpty();
        }
        return vacia;
    }

    private void cargarMemoria(ArrayList<Proceso> aCorrer) {
        for (Proceso p : aCorrer) {
            this.memoria[p.getParticion()].add(p);
        }
    }

    private int indiceAleatorio() {
        Random rand = new Random();
        int i = rand.nextInt(5);
        while (i == 0) {
            i = rand.nextInt(5);
        }
        return i;
    }

    // -------------------------------------------------------------------------
}
