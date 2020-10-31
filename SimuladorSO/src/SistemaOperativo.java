import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class SistemaOperativo {
  Recurso patio = new Recurso(1, "patio");

  Proceso Alice = new Proceso(patio, 1, "Alice");
  Proceso Bob = new Proceso(patio, 2, "Bob");
  Proceso Charlie = new Proceso(patio, 3, "Charlie");

  Function<Void, Void> ejecturableAlice = (Void v) -> {
    int randomNumero = ThreadLocalRandom.current().nextInt(0, 50+1);
    try {
      for (int j=0; j<10; ++j) {
        patio.adquirir();
        try {
          System.out.println("Pasear perro - Alice");
          Thread.sleep(randomNumero);
        } finally {
          patio.liberar();
        }
      }
    } catch(InterruptedException e) {
      System.out.println(e.getMessage());
    }
    return null;
  };

  Function<Void, Void> ejecturableBob = (Void v) -> {
    int randomNumero = ThreadLocalRandom.current().nextInt(0, 50+1);
    try {
      for (int j=0; j<10; ++j) {
        patio.adquirir();
        try {
          System.out.println("Pasear perro - Bob");
          Thread.sleep(randomNumero);
        } finally {
          patio.liberar();
        }
      }
    } catch(InterruptedException e) {
      System.out.println(e.getMessage());
    }
    return null;
  };

  Function<Void, Void> ejecturableCharlie = (Void v) -> {
    int randomNumero = ThreadLocalRandom.current().nextInt(0, 50+1);
    try {
      for (int j=0; j<10; ++j) {
        patio.adquirir();
        try {
          System.out.println("Pasear perro - Charlie");
          Thread.sleep(randomNumero);
        } finally {
          patio.liberar();
        }
      }
    } catch(InterruptedException e) {
      System.out.println(e.getMessage());
    }
    return null;
  };

   public void ejecutarProcesos() throws InterruptedException {
     Alice.setEjectuable(ejecturableAlice);
     Bob.setEjectuable(ejecturableBob);
     Charlie.setEjectuable(ejecturableCharlie);

     Alice.start();
     Bob.start();
     Charlie.start();

     Charlie.join();
     Alice.join();
     Bob.join();
   }
}

