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
public class Test21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            List<PredioGis> lst = FPredioGis.obtenerPredios();
            for(int i=0;i<lst.size();i++){
                System.out.println("Parroquia: "+lst.get(i).getGid());
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
