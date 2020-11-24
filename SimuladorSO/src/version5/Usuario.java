package version5;

import java.io.Serializable;

public class Usuario implements Serializable{

    private int uid;
    private String nombre;

    public Usuario(int uid, String nombre) {
        this.uid = uid;
        this.nombre = nombre;
    }

    public int getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
