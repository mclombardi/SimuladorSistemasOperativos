/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version3;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class InstruccionAsincronica extends Instruccion {

    public InstruccionAsincronica(String aImprimir, Recurso CPU) {
        super.aImprimir = aImprimir;
        super.CPU = CPU;
        super.log = new ArrayList<>();
    }

}
