/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.logica.servicios.FuncionesPredio;

/**
 *
 * @author Geovanny
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        try {
            System.out.println("Predio seleccionado: "+FuncionesPredio.obtenerPredioDadoCodigo(3).getClaveCatastral());
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
    }
    
}
