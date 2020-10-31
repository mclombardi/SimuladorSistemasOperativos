import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws Exception {
        SistemaOperativo so = new SistemaOperativo();
        so.ejecutarProcesos();
    }
}