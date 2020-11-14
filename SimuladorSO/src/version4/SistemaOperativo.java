package version4;

import java.util.Random;
import java.util.ArrayList;

public class SistemaOperativo {
    private boolean[][] permisosRecursos; // [usuarios][recursos]
    private boolean[][] permisosProgramas; // [usuarios][programas]
    // falta la otra matriz
    private ArrayList<Usuario> usuarios;
    private ArrayList<Recurso> recursos;
    private ArrayList<Proceso> procesos; // necesito id?
    private ArrayList<Proceso> procesosCorriendo;
    private ArrayList<Instruccion> instrucciones; // necesito id?
    private int indiceUsuarios;
    private int indiceRecursos;
    private int indiceProcesos;
    
    public SistemaOperativo() {
        this.indiceUsuarios = 0; // los indices indican donde voy a agregar el prox usuario/recurso/proceso
        this.indiceRecursos = 0;
        this.indiceProcesos = 0;
        this.permisosRecursos = new boolean[0][0]; // ver si no se puede hacer de [0][0] ?
        this.permisosProgramas = new boolean[0][0]; // ver si no se puede hacer de [0][0] ?
        this.usuarios = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.procesos = new ArrayList<>();
        this.instrucciones = new ArrayList<>();
        this.procesosCorriendo = new ArrayList<>();
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

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }
    
     public void crearInstruccionAsincronica(String aImprimir) {
        InstruccionAsincronica ia = new InstruccionAsincronica(aImprimir);
        instrucciones.add(ia);
    }

    public void crearInstruccionSincronica(String aImprimir, Recurso recurso) {
        InstruccionSincronica is = new InstruccionSincronica(aImprimir, recurso);
        instrucciones.add(is);
    }

    public void crearProceso(String nombre, ArrayList<Instruccion> instrucciones) {
        ArrayList<Recurso> recursos = obtenerRecursos(instrucciones);
        Proceso proceso = new Proceso(this.indiceProcesos, nombre, instrucciones, recursos);
        this.procesos.add(proceso);
        this.indiceProcesos++;
        expandirPermisosProgramas(0,1);
    }
    
     public ArrayList<Recurso> obtenerRecursos(ArrayList<Instruccion> instrucciones){
        ArrayList<Recurso> recursos = new ArrayList<>();
        for(Instruccion ins : instrucciones){
            try{
                InstruccionSincronica is = (InstruccionSincronica) ins;
                recursos.add(is.getRecurso());
            }catch(Exception e){
                continue; // rompe todo?
            }
        }
        return recursos;
        
    }

    public void crearRecurso(String nombre) {
        Recurso recurso = new Recurso(this.indiceRecursos, 1, nombre);
        this.recursos.add(recurso);
        this.indiceRecursos++;
        expandirPermisosRecursos(0,1); // al sumarle el indice, la nueva matriz va a quedar mas grande
    }

    public void crearUsuario(String nombre) {
        Usuario usuario = new Usuario(this.indiceUsuarios, nombre);
        this.usuarios.add(usuario);
        this.indiceUsuarios++;
        expandirPermisosRecursos(1,0);
        expandirPermisosProgramas(1,0); // al sumarle el indice, la nueva matriz va a quedar mas grande
    }

    public void modificarPermisosRecursos(Usuario usuario, Recurso recurso) { // siempre agrega, al menos por ahora    
        this.permisosRecursos[usuario.getUid()][recurso.getRid()] = true;
    }
    
     public void modificarPermisosProgramas(Usuario usuario, Proceso proceso) { // siempre agrega, al menos por ahora    
        this.permisosProgramas[usuario.getUid()][proceso.getPid()] = true;
    }

    public void expandirPermisosRecursos(int expU, int expR) { // siempre aumenta 1
        boolean[][] aux = this.permisosRecursos;
        this.permisosRecursos = new boolean[this.indiceUsuarios][this.indiceRecursos];
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux.length; j++) {
                this.permisosRecursos[i][j] = aux[i][j];
            }
        }
    }
    
     public void expandirPermisosProgramas(int expU, int expR) { // siempre aumenta 1
        boolean[][] aux = this.permisosProgramas;
        this.permisosProgramas = new boolean[this.indiceUsuarios][this.indiceProcesos];
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux.length; j++) {
                this.permisosProgramas[i][j] = aux[i][j];
            }
        }
    }

    public void validarAutorizacionEjecucion(Usuario usuario, ArrayList<Proceso> procesosAEjecutar) {
        for(Proceso proceso : procesosAEjecutar) {
            if (permisoAPrograma(usuario, proceso)) {
                procesosCorriendo.add(proceso.copiar());
            }
        }
    }
    
    public void correrProcesos(Usuario usuario) {
        Random rand = new Random();
        while(procesosCorriendo.size() > 0) {
            int randomIndex = rand.nextInt(procesosCorriendo.size());     
            Proceso procActual = procesosCorriendo.get(randomIndex);
            boolean matarProc = procActual.run(permisosRecursos, usuario);
            
            if (matarProc) procesosCorriendo.remove(randomIndex);            
        }
    }
    
    public boolean permisoAPrograma(Usuario usuario, Proceso proceso) {
        return this.permisosProgramas[usuario.getUid()][proceso.getPid()];
    }

}
