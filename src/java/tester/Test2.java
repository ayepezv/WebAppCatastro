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
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            List<PredioGis> lst = FPredioGis.obtenerPredioGisDadoClaveCatastral("01022445000000");
            System.out.println("total de predios: "+lst.size());
            
            System.out.println("Parroquia: "+lst.get(0).getParroquia().getDescripcion());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
