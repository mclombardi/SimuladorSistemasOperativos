import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class Proceso extends Thread {
  int pid;
  Recurso recurso;
  Function<Void, Void> ejectuable;

  public Proceso(Recurso recurso, int numeroProceso, String nombre) {
    this.pid = numeroProceso;
    this.recurso = recurso;
  }

  public void setEjectuable(Function<Void, Void> ejectuable) {
    this.ejectuable = ejectuable;
  }

  public void run() {
    this.ejectuable.apply(null);
  }

}
