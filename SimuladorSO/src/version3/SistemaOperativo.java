/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version3;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class SistemaOperativo {

    private boolean[][] permisos; // [usuarios][recursos]
    private ArrayList<Usuario> usuarios;
    private ArrayList<Recurso> recursos;
    private ArrayList<Proceso> procesos; // necesito id?
    private ArrayList<Instruccion> instrucciones; // necesito id?
    private int indiceUsuarios;
    private int indiceRecursos;
    private int indiceProcesos;
    private Recurso CPU;

    public SistemaOperativo() {
        this.indiceUsuarios = 0; // los indices indican donde voy a agregar el prox usuario/recurso/proceso
        this.indiceRecursos = 0;
        this.indiceProcesos = 0;
        this.permisos = new boolean[0][0]; // ver si no se puede hacer de [0][0] ?
        this.CPU = new Recurso(-1, 1, "CPU"); // no preciso el pid ya que todos los usuarios tienen acceso a la CPU
        this.usuarios = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.procesos = new ArrayList<>();
        this.instrucciones = new ArrayList<>();
    }

    public boolean[][] getPermisos() {
        return permisos;
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

    public Recurso getCPU() {
        return CPU;
    }
    
    

    public void crearInstruccionAsincronica(String aImprimir) {
        InstruccionAsincronica ia = new InstruccionAsincronica(aImprimir, this.CPU);
        instrucciones.add(ia);
    }

    public void crearInstruccionSincronica(String aImprimir, Recurso recurso) {
        InstruccionSincronica is = new InstruccionSincronica(aImprimir, recurso, this.CPU);
        instrucciones.add(is);
    }

    public void crearProceso(String nombre, ArrayList<Instruccion> instrucciones) {
        ArrayList<Recurso> recursos = obtenerRecursos(instrucciones);
        Proceso proceso = new Proceso(this.indiceProcesos, nombre, instrucciones, recursos);
        this.procesos.add(proceso);
        this.indiceProcesos++;
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
        expandirPermisos(0,1); // al sumarle el indice, la nueva matriz va a quedar mas grande
    }

    public void crearUsuario(String nombre) {
        Usuario usuario = new Usuario(this.indiceUsuarios, nombre);
        this.usuarios.add(usuario);
        this.indiceUsuarios++;
        expandirPermisos(1,0);

    }

    public void modificarPermisos(Usuario usuario, Recurso recurso) { // siempre agrega, al menos por ahora    
        this.permisos[usuario.getUid()][recurso.getRid()] = true;
    }

    public void expandirPermisos(int expU, int expR) { // siempre aumenta 1
        boolean[][] aux = this.permisos;
        this.permisos = new boolean[this.indiceUsuarios][this.indiceRecursos];
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux.length; j++) {
                this.permisos[i][j] = aux[i][j];
            }
        }
    }

    public void correrProcesos(Usuario usuario, ArrayList<Proceso> procesosACorrer) {
        try {
            if (permisosValidos(usuario, procesosACorrer)) {
                for (Proceso proceso : procesosACorrer) {
                    proceso.start();
                }
                for (Proceso proceso : procesosACorrer) {
                    proceso.join();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean permisosValidos(Usuario usuario, ArrayList<Proceso> procesosACorrer) {
        for (Proceso proceso : procesosACorrer) {
            for (Recurso recurso : proceso.getRecursos()) {
                if (!this.permisos[usuario.getUid()][recurso.getRid()]) {
                    return false;
                }
            }
        }
        return true;
    }

}
