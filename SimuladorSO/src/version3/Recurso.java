/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version3;
import java.util.concurrent.Semaphore;


/**
 *
 * @author Usuario
 */


public class Recurso {
    private int rid;
    private Semaphore semaforo;
    private String nombre;
    
    
    public Recurso(int rid, int capacidad, String nombre) {
        this.rid = rid;
        this.semaforo = new Semaphore(capacidad);
        this.nombre = nombre;
    }

    public int getRid() {
        return rid;
    }

    public String getNombre() {
        return nombre;
    }


    public void adquirir() throws InterruptedException {
        this.semaforo.acquire();
    }

    public void liberar() {
        this.semaforo.release();
    }    
    
}
