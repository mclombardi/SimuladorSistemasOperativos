 package version5;

import interfaz.Menu;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SistemaOperativo so = new SistemaOperativo();
        Menu menu = new Menu(so);
        menu.setVisible(true);
    }
/*
    public static void prueba5() {
        SistemaOperativo so = new SistemaOperativo();
        so.crearRecurso("escalera");
        Recurso escalera = so.getRecursos().get(0);
        so.crearInstruccionSincronica("Alice usa la escalera", escalera); // 0
        so.crearInstruccionSincronica("Bob usa la escalera", escalera); // 1
        so.crearInstruccionSincronica("Charlie usa la escalera", escalera); // 2
        so.crearRecurso("lampara");
        Recurso lampara = so.getRecursos().get(1);
        so.crearInstruccionSincronica("Alice usa la lampara", lampara); // 3
        so.crearInstruccionSincronica("Bob usa la lampara", lampara); // 4
        so.crearInstruccionSincronica("Charlie usa la lampara", lampara); // 5
        so.crearRecurso("mesa");
        Recurso mesa = so.getRecursos().get(2);
        so.crearInstruccionSincronica("Alice usa la mesa", mesa); // 6
        so.crearInstruccionSincronica("Bob usa la mesa", mesa); // 7
        so.crearInstruccionSincronica("Charlie usa la mesa", mesa); // 8

        Instruccion a1 = so.getInstrucciones().get(0); // escalera // |||||
        Instruccion a2 = so.getInstrucciones().get(6);  // mesa // |||||
        Instruccion a3 = so.getInstrucciones().get(3); // lampara // |||||

        Instruccion b1 = so.getInstrucciones().get(4); // lampara // |||||
        Instruccion b2 = so.getInstrucciones().get(1); // escalera // |||||
        Instruccion b3 = so.getInstrucciones().get(7); // mesa // |||||

        Instruccion c1 = so.getInstrucciones().get(8); // mesa // |||||
        Instruccion c2 = so.getInstrucciones().get(5); // lampara
        Instruccion c3 = so.getInstrucciones().get(2); // escalera

        ArrayList<Instruccion> arrAlice = new ArrayList<>();
        arrAlice.add(a1);
        arrAlice.add(a2);
        //arrAlice.add(a3);
        ArrayList<Instruccion> arrBob = new ArrayList<>();
        arrBob.add(b1);
        /*arrBob.add(b2);
        arrBob.add(b3);
        ArrayList<Instruccion> arrCharlie = new ArrayList<>();
        //arrCharlie.add(c1);
        arrCharlie.add(c2);
        //arrCharlie.add(c3);

        so.crearProceso("Alice", arrAlice, 1);
        so.crearProceso("Bob", arrBob, 2);
        so.crearProceso("Charlie", arrCharlie, 2);

        Proceso alice = so.getProcesos().get(0);
        Proceso bob = so.getProcesos().get(1);
        Proceso charlie = so.getProcesos().get(2);

        ArrayList<Proceso> procesosAEjecutar = new ArrayList<>();
        procesosAEjecutar.add(alice);
        procesosAEjecutar.add(bob);
        procesosAEjecutar.add(charlie);

        so.crearUsuario("vale");

        Usuario vale = so.getUsuarios().get(0);
        so.modificarPermisosRecursos(vale, escalera);
        so.modificarPermisosRecursos(vale, mesa);
        so.modificarPermisosRecursos(vale, lampara);

        so.modificarPermisosProgramas(vale, alice);
        so.modificarPermisosProgramas(vale, bob);
        so.modificarPermisosProgramas(vale, charlie);
        //so.validarAutorizacionEjecucion(vale, procesosAEjecutar);
        so.correrProcesos(procesosAEjecutar, vale);

    }

    public static void prueba4() {
        SistemaOperativo so = new SistemaOperativo();
        so.crearRecurso("escalera");
        Recurso escalera = so.getRecursos().get(0);
        so.crearInstruccionSincronica("Alice usa la escalera", escalera); // 0
        so.crearInstruccionSincronica("Bob usa la escalera", escalera); // 1
        so.crearInstruccionSincronica("Charlie usa la escalera", escalera); // 2
        so.crearRecurso("lampara");
        Recurso lampara = so.getRecursos().get(1);
        so.crearInstruccionSincronica("Alice usa la lampara", lampara); // 3
        so.crearInstruccionSincronica("Bob usa la lampara", lampara); // 4
        so.crearInstruccionSincronica("Charlie usa la lampara", lampara); // 5
        so.crearRecurso("mesa");
        Recurso mesa = so.getRecursos().get(2);
        so.crearInstruccionSincronica("Alice usa la mesa", mesa); // 6
        so.crearInstruccionSincronica("Bob usa la mesa", mesa); // 7
        so.crearInstruccionSincronica("Charlie usa la mesa", mesa); // 8

        Instruccion a1 = so.getInstrucciones().get(0); // escalera // |||||
        Instruccion a2 = so.getInstrucciones().get(6);  // mesa // |||||
        Instruccion a3 = so.getInstrucciones().get(3); // lampara // |||||

        Instruccion b1 = so.getInstrucciones().get(4); // lampara // |||||
        Instruccion b2 = so.getInstrucciones().get(1); // escalera // |||||
        Instruccion b3 = so.getInstrucciones().get(7); // mesa // |||||

        Instruccion c1 = so.getInstrucciones().get(8); // mesa // |||||
        Instruccion c2 = so.getInstrucciones().get(5); // lampara
        Instruccion c3 = so.getInstrucciones().get(2); // escalera

        ArrayList<Instruccion> arrAlice = new ArrayList<>();
        arrAlice.add(a1);
        arrAlice.add(a2);
        arrAlice.add(a3);
        ArrayList<Instruccion> arrBob = new ArrayList<>();
        arrBob.add(b1);
        arrBob.add(b2);
        arrBob.add(b3);
        ArrayList<Instruccion> arrCharlie = new ArrayList<>();
        arrCharlie.add(c1);
        arrCharlie.add(c2);
        arrCharlie.add(c3);

        so.crearProceso("Alice", arrAlice, 1);
        so.crearProceso("Bob", arrBob, 2);
        so.crearProceso("Charlie", arrCharlie, 3);

        Proceso alice = so.getProcesos().get(0);
        Proceso bob = so.getProcesos().get(1);
        Proceso charlie = so.getProcesos().get(2);

        ArrayList<Proceso> procesosAEjecutar = new ArrayList<>();
        procesosAEjecutar.add(alice);
        procesosAEjecutar.add(bob);
        procesosAEjecutar.add(charlie);

        so.crearUsuario("vale");

        Usuario vale = so.getUsuarios().get(0);
        so.modificarPermisosRecursos(vale, escalera);
        so.modificarPermisosRecursos(vale, mesa);
        so.modificarPermisosRecursos(vale, lampara);

        so.modificarPermisosProgramas(vale, alice);
        so.modificarPermisosProgramas(vale, bob);
        so.modificarPermisosProgramas(vale, charlie);
        //so.validarAutorizacionEjecucion(vale, procesosAEjecutar);
        so.correrProcesos(procesosAEjecutar, vale);

    }

    public static void prueba3() {
        SistemaOperativo so = new SistemaOperativo();
        so.crearRecurso("escalera");
        Recurso escalera = so.getRecursos().get(0);
        so.crearInstruccionSincronica("Alice usa la escalera", escalera); // 0
        so.crearInstruccionSincronica("Bob usa la escalera", escalera); // 1
        so.crearInstruccionAsincronica("Bob salta"); // 2
        so.crearInstruccionAsincronica("Bob mira el techo"); // 3
        so.crearInstruccionAsincronica("Alice mira el techo"); // 4
        so.crearInstruccionAsincronica("Charlie camina"); // 5

        Instruccion a1 = so.getInstrucciones().get(0);
        Instruccion b2 = so.getInstrucciones().get(1);
        Instruccion b1 = so.getInstrucciones().get(2);
        Instruccion b3 = so.getInstrucciones().get(3);
        Instruccion a2 = so.getInstrucciones().get(4);
        Instruccion c1 = so.getInstrucciones().get(5);

        ArrayList<Instruccion> arrAlice = new ArrayList<>();
        arrAlice.add(a1);
        arrAlice.add(a2);
        ArrayList<Instruccion> arrBob = new ArrayList<>();
        arrBob.add(b1);
        arrBob.add(b2);
        arrBob.add(b3);
        ArrayList<Instruccion> arrCharlie = new ArrayList<>();
        arrCharlie.add(c1);

        so.crearProceso("Alice", arrAlice, 1);
        so.crearProceso("Bob", arrBob, 2);
        so.crearProceso("Charlie", arrCharlie, 3);

        Proceso alice = so.getProcesos().get(0);
        Proceso bob = so.getProcesos().get(1);
        Proceso charlie = so.getProcesos().get(2);

        ArrayList<Proceso> procesosAEjecutar = new ArrayList<>();
        procesosAEjecutar.add(alice);
        procesosAEjecutar.add(bob);
        procesosAEjecutar.add(charlie);

        so.crearUsuario("vale");

        Usuario vale = so.getUsuarios().get(0);
        so.modificarPermisosRecursos(vale, escalera);
        so.modificarPermisosProgramas(vale, alice);
        so.modificarPermisosProgramas(vale, bob);
        so.modificarPermisosProgramas(vale, charlie);
        //so.validarAutorizacionEjecucion(vale, procesosAEjecutar);
        so.correrProcesos(procesosAEjecutar, vale);

    }

    public static void prueba2() {
        SistemaOperativo so = new SistemaOperativo();
        so.crearRecurso("sec");
        Recurso secador = so.getRecursos().get(0);
        so.crearInstruccionSincronica("alice secando el pelo...", secador);
        so.crearInstruccionAsincronica("alice hace cosas");

        Instruccion a1 = so.getInstrucciones().get(0);
        Instruccion a2 = so.getInstrucciones().get(1);

        ArrayList<Instruccion> arr1 = new ArrayList<>();
        arr1.add(a1);
        arr1.add(a2);

        so.crearInstruccionSincronica("charlie secando el pelo...", secador);
        Instruccion c1 = so.getInstrucciones().get(2);
        ArrayList<Instruccion> arr3 = new ArrayList<>();
        arr3.add(c1);

        so.crearProceso("Alice", arr1, 1);
        so.crearProceso("Charlie", arr3, 2);

        ArrayList<Proceso> procesosAEjecutar = new ArrayList<>();
        Proceso alice = so.getProcesos().get(0);
        Proceso charlie = so.getProcesos().get(1);

        procesosAEjecutar.add(alice);
        procesosAEjecutar.add(charlie);

        so.crearUsuario("vale");

        Usuario vale = so.getUsuarios().get(0);
        so.modificarPermisosRecursos(vale, secador);
        so.modificarPermisosProgramas(vale, alice);
        so.modificarPermisosProgramas(vale, charlie);
        //so.validarAutorizacionEjecucion(vale, procesosAEjecutar);
        so.correrProcesos(procesosAEjecutar, vale);
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

        so.crearProceso("Alice", arr1, 1);
        so.crearProceso("Bob", arr2, 2);
        so.crearProceso("Charlie", arr3, 3);

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

        //so.validarAutorizacionEjecucion(vale, procesosAEjecutar);
        so.correrProcesos(procesosAEjecutar, vale);
    }*/

}
