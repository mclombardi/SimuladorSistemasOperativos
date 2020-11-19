/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version3;

import interfaz.Menu;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
       /* SistemaOperativo sistema = new SistemaOperativo();
        Menu menu = new Menu(sistema);
        menu.setVisible(true);*/
        //prueba2();
    }

    public static void prueba2() {
        SistemaOperativo so = new SistemaOperativo();

        so.crearRecurso("impresora");
        Recurso impresora = so.getRecursos().get(0);
        so.crearInstruccionSincronica("imprimiendo...", impresora);
        Instruccion insImprimir = so.getInstrucciones().get(0);

        so.crearInstruccionAsincronica("hola hola");
        Instruccion insHola = so.getInstrucciones().get(1);

        so.crearRecurso("secador");
        Recurso secador = so.getRecursos().get(1);
        so.crearInstruccionSincronica("secar pelo...", secador);
        Instruccion insSecador = so.getInstrucciones().get(2);

        ArrayList<Instruccion> arrIns1 = new ArrayList<>();
        arrIns1.add(insImprimir);
        arrIns1.add(insHola);

        ArrayList<Instruccion> arrIns2 = new ArrayList<>();
        arrIns2.add(insSecador);
        arrIns2.add(insHola);

        so.crearProceso("procVale", arrIns1);
        so.crearProceso("procClari", arrIns2);
        Proceso procV = so.getProcesos().get(0);
        Proceso procC = so.getProcesos().get(1);

        ArrayList<Proceso> procsV = new ArrayList<>();
        ArrayList<Proceso> procsC = new ArrayList<>();
        procsV.add(procV);
        procsC.add(procC);

        so.crearUsuario("vale");
        so.crearUsuario("clari");
        Usuario vale = so.getUsuarios().get(0);
        Usuario clari = so.getUsuarios().get(1);

        so.modificarPermisos(clari, secador);
        so.modificarPermisos(vale, impresora);

        so.correrProcesos(vale, procsV);
        so.correrProcesos(clari, procsC);

    }

    public static void prueba1() {
        SistemaOperativo so = new SistemaOperativo();

        so.crearRecurso("impresora");
        Recurso impresora = so.getRecursos().get(0);

        so.crearInstruccionSincronica("imprimiendo 1...", impresora);
        so.crearInstruccionSincronica("imprimiendo 2...", impresora);
        Instruccion insImprimir1 = so.getInstrucciones().get(0);
        Instruccion insImprimir2 = so.getInstrucciones().get(1);

        so.crearInstruccionAsincronica("hola hola");
        Instruccion insHola = so.getInstrucciones().get(2);

        so.crearInstruccionAsincronica("chau chau");
        Instruccion insChau = so.getInstrucciones().get(3);

        ArrayList<Instruccion> arrIns1 = new ArrayList<>();
        arrIns1.add(insImprimir1);
        ArrayList<Instruccion> arrIns2 = new ArrayList<>();
        arrIns2.add(insImprimir2);
        arrIns2.add(insChau);
        arrIns1.add(insHola);

        so.crearProceso("procImprimir", arrIns1);
        so.crearProceso("procImprimir2", arrIns2);
        so.crearUsuario("user");
        so.correrProcesos(so.getUsuarios().get(0), so.getProcesos());

        so.crearRecurso("secador");
        Recurso secador = so.getRecursos().get(1);
        so.crearInstruccionSincronica("imprimiendo 3...", impresora);
        so.crearInstruccionSincronica("secar pelo 1...", secador);
        so.crearInstruccionSincronica("secar pelo 2...", secador);

        Instruccion insImprimir3 = so.getInstrucciones().get(4);
        Instruccion secarPelo1 = so.getInstrucciones().get(5);
        Instruccion secarPelo2 = so.getInstrucciones().get(6);

        so.crearInstruccionAsincronica("como andas");
        Instruccion comoAndas = so.getInstrucciones().get(7);

        ArrayList<Instruccion> arrIns3 = new ArrayList<>();
        ArrayList<Instruccion> arrIns4 = new ArrayList<>();
        arrIns3.add(insImprimir3);
        arrIns4.add(insImprimir1);
        arrIns3.add(secarPelo1);
        arrIns4.add(secarPelo2);
        arrIns3.add(insHola);
        arrIns4.add(comoAndas);

        so.crearProceso("proc3", arrIns3);
        so.crearProceso("proc4", arrIns4);

        Proceso p3 = so.getProcesos().get(2);
        Proceso p4 = so.getProcesos().get(3);
        ArrayList<Proceso> procesos = new ArrayList<>();
        procesos.add(p3);
        procesos.add(p4);
        so.correrProcesos(so.getUsuarios().get(0), procesos);
    }

}