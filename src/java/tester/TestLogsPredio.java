/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import auditoria.logica.entidades.LogPredio;
import auditoria.logica.servicios.FLogPredio;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geovanny Cudco
 */
public class TestLogsPredio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse("2017-09-1");
            Date parsed1 = format.parse("2018-07-1");
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            java.sql.Date sql1 = new java.sql.Date(parsed1.getTime());
            
            List<LogPredio> lst = FLogPredio.listarLogsPredio(7118,sql,sql1);
            for(int i=0;i<lst.size();i++){
                System.out.println("fecha: "+lst.get(i).getFecha()+"\n" 
                                    +" accion: "+lst.get(i).getAccion()+"\n" 
                                    + " predio: "+lst.get(i).getPredio().getClaveCatastral());
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
