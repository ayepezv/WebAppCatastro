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
public class TestReporteExcencionesFechas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse("2017-12-1");
            Date parsed1 = format.parse("2018-07-1");
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            java.sql.Date sql1 = new java.sql.Date(parsed1.getTime());

            List<PredioExcencion> lst = FPredioExcencion.obtenerExcecnionesDadoFecha(sql, sql1);
            System.out.println("Total obtenido: "+lst.size());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

}
