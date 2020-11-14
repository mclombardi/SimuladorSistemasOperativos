package version4;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        prueba1();
    }

    public static void prueba1() {
        SistemaOperativo so = new SistemaOperativo();
        so.crearRecurso("patio");
        so.crearRecurso("secador");
        Recurso patio = so.getRecursos().get(0);
        Recurso secador = so.getRecursos().get(1);

        so.crearInstruccionSincronica("alice secando el pelo...", secador);
        so.crearInstruccionAsincronica("alice hace cosas");
        so.crearInstruccionSincronica("alice pasea el perro...", patio);
        Instruccion a1 = so.getInstrucciones().get(0);
        Instruccion a2 = so.getInstrucciones().get(1);
        Instruccion a3 = so.getInstrucciones().get(2);

        ArrayList<Instruccion> arr1 = new ArrayList<>();
        arr1.add(a1);
        arr1.add(a2);
        arr1.add(a3);

        so.crearInstruccionSincronica("paseando perro bob...", patio);
        so.crearInstruccionAsincronica("bob hace cosas");
        Instruccion b1 = so.getInstrucciones().get(3);
        Instruccion b2 = so.getInstrucciones().get(4);
        ArrayList<Instruccion> arr2 = new ArrayList<>();
        arr2.add(b1);
        arr2.add(b2);

        so.crearInstruccionSincronica("charlie secando el pelo...", secador);
        so.crearInstruccionSincronica("charlie pasea el perro...", patio);
        Instruccion c1 = so.getInstrucciones().get(5);
        Instruccion c2 = so.getInstrucciones().get(6);

        ArrayList<Instruccion> arr3 = new ArrayList<>();
        arr3.add(c1);
        arr3.add(c2);

        so.crearProceso("Alice", arr1);
        so.crearProceso("Bob", arr2);
        so.crearProceso("Charlie", arr3);

        ArrayList<Proceso> procesosAEjecutar = new ArrayList<>();
        Proceso alice = so.getProcesos().get(0);
        Proceso bob = so.getProcesos().get(1);
        Proceso charlie = so.getProcesos().get(2);

        procesosAEjecutar.add(alice);
        procesosAEjecutar.add(bob);
        procesosAEjecutar.add(charlie);

        so.crearUsuario("vale");

        Usuario vale = so.getUsuarios().get(0);
        so.modificarPermisosRecursos(vale, patio);
        so.modificarPermisosRecursos(vale, secador);
        so.modificarPermisosProgramas(vale, alice);
        so.modificarPermisosProgramas(vale, bob);
        so.modificarPermisosProgramas(vale, charlie);

        so.validarAutorizacionEjecucion(vale, procesosAEjecutar);
        so.correrProcesos(vale);
    }

}
