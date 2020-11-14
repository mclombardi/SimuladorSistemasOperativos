package version1;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static Semaphore resource_1 = new Semaphore(1);
//    static Semaphore resource_2 = new Semaphore(1);
//    static Semaphore resource_3 = new Semaphore(1);

    static class Proceso extends Thread {
        String c;

        Proceso(String c) {
            this.c = c;
        }

        public void run() {
            try {
                int randomNbr = ThreadLocalRandom.current().nextInt(0, 50 + 1);

                for (int j=0; j<30; ++j) {
                    //System.out.println("Solicitando el R1. Por <" + c +">");
                    resource_1.acquire();
                    try {
                       // System.out.println("Usando el R1. Por <" + c +">");
                        System.out.println(c);
                        Thread.sleep(randomNbr);
                    } finally {
                       // System.out.println("El recurso R1 ha sido usado Por <" + c +">");
                        resource_1.release();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Proceso th1 = new Proceso("Paseo el perro - Alice");
        

        Proceso th2 = new Proceso("Paseo el perro - Bob");
        th1.start();
        th2.start();
        
        Proceso th3 = new Proceso("Paseo el perro - CHARLIE");

//        Proceso th3 = new Proceso('V');
        th3.start();

       // th1.join();
       // th2.join();
//        th3.join();

        System.out.println('\n');
    }
}