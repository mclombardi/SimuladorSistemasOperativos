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

    public Proceso(int pid, String nombre, ArrayList<Instruccion> instrucciones, ArrayList<Recurso> recursos) {
        this.pid = pid;
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.recursos = recursos;
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
