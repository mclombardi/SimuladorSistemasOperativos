 package version5;

import interfaz.Menu;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SistemaOperativo s = new SistemaOperativo();
        try {
            ObjectInputStream arch = new ObjectInputStream(Files.newInputStream(Paths.get("DATOS")));
            s = (SistemaOperativo) arch.readObject();
            arch.close();
        } catch (ClassNotFoundException e) {

        } catch (IOException e) {

        }
        Menu menu = new Menu(s);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}
