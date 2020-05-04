/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.recaudacion.funciones.FTitulos;

/**
 *
 * @author Geovanny
 */
public class Test6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Total de predios: "+FTitulos.consultarTitulosPagados(30).size());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
