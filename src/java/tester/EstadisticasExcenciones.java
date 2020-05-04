/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.logica.entidades.PredioExcencion;
import catastro.logica.servicios.FPredioExcencion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geovanny Cudco
 */
public class EstadisticasExcenciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse("2018-06-1");
            Date parsed1 = format.parse("2018-07-1");
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            java.sql.Date sql1 = new java.sql.Date(parsed1.getTime());

            List<PredioExcencion> lst = FPredioExcencion.reporteTotalDescuentosExcenciones(sql, sql1);
            System.out.println("Total obtenido: " + lst.size());
            String json = "[";

            for (int i = 0; i < lst.size(); i++) {
                json = json + "{'Excencion':'" + lst.get(i).getExcencion().getExcencion() + "',";
                json = json + "'Total':'" + lst.get(i).getDsctoExcencion() + "'";
                if (i != lst.size() - 1) {
                    json = json + "},";
                } else {
                    json = json + "}";
                }
            }
            json = json + "]";
            System.out.println("Json Data: " + json);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
