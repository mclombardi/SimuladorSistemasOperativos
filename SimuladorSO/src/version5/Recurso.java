package version5;

import java.io.Serializable;

public class Recurso implements Serializable{

    private int rid;
    private String nombre;
    private int bloqueado;

    public Recurso(int rid, int capacidad, String nombre) {
        this.rid = rid;
        this.nombre = nombre;
        this.bloqueado = 0;
    }

    public int getRid() {
        return rid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String e) {
        this.nombre = e;
    }

    public boolean estaBloqueado() {
        return this.bloqueado == 1;
    }

    public void setBloqueado(int value) {
        this.bloqueado = value;
    }

    public void adquirir() {
        this.bloqueado = 1;
    }

    public void liberar() {
        this.bloqueado = 0;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
