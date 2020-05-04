/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.gis.entidades.PredioGis;
import catastro.gis.funciones.FPredioGis;
import java.util.List;

/**
 *
 * @author Geovanny
 */
public class Test3 {

    public static void main(String[] args) {
        try {
            List<PredioGis> lst = FPredioGis.obtenerPredioGisDadoClaveCatastral("0101010");
            for (PredioGis lst1 : lst) {
                System.out.println("Predio: " + lst1.getClaveCatastral());
                System.out.println("-----------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

}
