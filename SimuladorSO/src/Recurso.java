import java.util.concurrent.Semaphore;

public class Recurso {
    String nombre;
    Semaphore semaforo;

    public Recurso(int capacidad, String nombre) {
        this.semaforo = new Semaphore(capacidad);
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void adquirir() throws InterruptedException {
        this.semaforo.acquire();
    }

    public void liberar() {
        this.semaforo.release();
    }
}
