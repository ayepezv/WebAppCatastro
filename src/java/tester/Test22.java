/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geovanny Cudco
 */
public class Test22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse("2018-06-01");
            Date parsed1 = format.parse("2018-08-07");
            
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            java.sql.Date sql1 = new java.sql.Date(parsed1.getTime());
            
            List<Titulo> lstTitulos = FTitulos.consultarTitulosIndividualesEntreFechas(sql, sql1);
            System.out.println("Fecha inicio " + sql + " fecha fin " + sql1+" total "+lstTitulos.size());
                        
            System.out.println("Total pendientes: "+FTitulos.titulosPendientes(7415));
            
            System.out.println("Total pagados: "+FTitulos.titulosPendientes(7415));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
