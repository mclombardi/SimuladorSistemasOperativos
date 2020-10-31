/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version3;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Usuario
 */
public abstract class Instruccion {

    protected String aImprimir;
    protected boolean sincronica;
    protected Recurso CPU;

    public void run() {
        int randomNumero = ThreadLocalRandom.current().nextInt(0, 51);
        try {
            for (int i = 0; i < 50; i++) {
                this.CPU.adquirir();
                try {
                    System.out.println(this.aImprimir);
                    Thread.sleep(randomNumero);
                } finally {
                    this.CPU.liberar();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
