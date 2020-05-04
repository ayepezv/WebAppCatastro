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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geovanny Cudco
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Calendar cal = Calendar.getInstance();

            String json = "";
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
            List<Transanccion> trans = FTransaccion.obtenerTotalTransaccionesDiarias();
            json = "[";
            for (int i = 0; i < trans.size(); i++) {
                json = json + "{'Fecha':'";
                Date fecha = trans.get(i).getFechaEmision();
                cal.setTime(fecha);
                cal.add(Calendar.DATE, 1);

                json = json + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + "',";
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
            System.out.println("private void graficar() dice: " + e.getMessage());
        }
    }

}
