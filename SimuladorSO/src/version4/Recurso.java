package version4;

import java.util.concurrent.Semaphore;

public class Recurso {
    private int rid;
    private Semaphore semaforo;
    private String nombre;
    private boolean bloqueado;
    
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
    
    public boolean estaBloqueado() {
        return this.bloqueado;
    }

    public void adquirir() {
        try {
            this.semaforo.acquire();
            this.bloqueado = true;
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    }

    public void liberar() {
        this.semaforo.release();
        this.bloqueado = false;
    } 
}
