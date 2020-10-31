/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version3;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Usuario
 */
public class InstruccionSincronica extends Instruccion {

    private Recurso recurso;

    public InstruccionSincronica(String aImprimir, Recurso recurso, Recurso CPU) {
        super.aImprimir = aImprimir;
        this.recurso = recurso;
        super.CPU = CPU;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    @Override

    public void run() {
        int randomNumero = ThreadLocalRandom.current().nextInt(0, 51);
        try {
            for (int i = 0; i < 50; i++) {
                this.CPU.adquirir();
                this.recurso.adquirir();
                try {
                    System.out.println(this.aImprimir);
                    Thread.sleep(randomNumero);
                } finally {
                    this.recurso.liberar();
                    this.CPU.liberar();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
