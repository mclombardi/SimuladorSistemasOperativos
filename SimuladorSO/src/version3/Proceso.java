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
public class Proceso extends Thread{
    private int pid;
    private String nombre;
    private ArrayList<Instruccion> instrucciones;
    private ArrayList<Recurso> recursos;
    private String estado;
    private ArrayList<String> log;

    public Proceso(int pid, String nombre, ArrayList<Instruccion> instrucciones, ArrayList<Recurso> recursos) {
        this.pid = pid;
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.recursos = recursos;
        this.estado = "listo"; 
        this.log = new ArrayList<>();
        /*los estados pueden ser: 
          - sinCorrer --> aun no fueron seleccionados para correr concurrentemente
          - listo --> listo para ser ejecutado
          - finalzado --> terminó de correr. Puede volver a ser ejecutado
          - en ejecución
        */
        
    }

    @Override
    public String toString(){
        return this.nombre + " : " + this.estado;
    }
    
    
    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }
    
    public void run(){
      for(Instruccion ins : this.instrucciones){       
          ins.run();          
      }
    }
    
    
        
}
