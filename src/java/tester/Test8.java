/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class Test8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            String json = "[";
            String oc;
            ArrayList<PredioV2> lstOcupaciones = FuncionesPredio.seleccionarOcupacionesPredios();
            for (int i = 0; i < lstOcupaciones.size(); i++) {
                oc = lstOcupaciones.get(i).getOcupacionPredio();
                json = json + "{'Ocupación': ";
                json = json + "'" + lstOcupaciones.get(i).getOcupacionPredio() + "'";
                json = json + ",'Total':'" + FuncionesPredio.seleccionarPrediosDadoOcupacion(oc).size() + "'";
                if (i != lstOcupaciones.size() - 1) {
                    json = json + "},";
                } else {
                    json = json + "}";
                }
            }
            json = json + "]";
            System.out.println("Json: " + json);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
