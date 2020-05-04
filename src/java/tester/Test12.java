/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.recaudacion.entidades.Transanccion;
import catastro.recaudacion.funciones.FTransaccion;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Geovanny
 */
public class Test12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);

            List<Transanccion> trans = FTransaccion.obtenerTotalTransaccionesDiarias();

            String json = "[";
            for (int i = 0; i < trans.size(); i++) {              
                json = json + "{'Fecha':'";
                json = json + new SimpleDateFormat("yyyy-MM-dd").format(trans.get(i).getFechaEmision()) + "',";
                json = json + "'Total':'" + formateador.format(trans.get(i).getValorTransaccion()) + "'";
                if (i != trans.size() - 1) {
                    json = json + "},";
                } else {
                    json = json + "}";
                }
            }
            json = json + "]";
            System.out.println("Json data: " + json);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
