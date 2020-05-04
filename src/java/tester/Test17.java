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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geovanny
 */
public class Test17 {

    public static void main(String[] args) {
        try {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            //borrar luego
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse("2017-09-03");
            Date parsed1 = format.parse("2017-07-12");
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            java.sql.Date sql1 = new java.sql.Date(parsed1.getTime());
            //// hasta aqui
            System.out.println("Fecha inicio: " + sql);
            System.out.println("Fecha fin: " + sql1);

            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);

            List<Transanccion> lst = FTransaccion.transaccionesDiariasPorCaja(sqlDate);

            String json = "[";
            for (int i = 0; i < lst.size(); i++) {
                json = json + "{'caja': '";
                json = json + lst.get(i).getRecaudador().getNombres() + " " + lst.get(i).getRecaudador().getApellidos() + "',";
                json = json + "'total':'";
                json = json + formateador.format(lst.get(i).getValorTransaccion()) + "'";
                if (i != lst.size() - 1) {
                    json = json + "},";
                } else {
                    json = json + "}";
                }
            }
            json = json + "]";
            System.out.println("json: " + json);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
