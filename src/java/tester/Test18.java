/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import com.estadisticas.entidades.Estadisticas;
import com.estadisticas.servicios.ServiciosEstadisticas;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

/**
 *
 * @author Geovanny
 */
public class Test18 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);

            String json = "[";
            List<Estadisticas> lst = ServiciosEstadisticas.titulosEmitidosPendientesRecaudados();
            for (int i = 0; i < lst.size(); i++) {
                json = json + "{'Año':'" + lst.get(i).getIndice() + "',";
                json = json + "'Recaudados':'" + lst.get(i).getIndice3() + "',";
                json = json + "'Pendientes':'" + lst.get(i).getIndice2() + "',";
                json = json + "'Total':'" + lst.get(i).getIndice1() + "'";
                if (i != lst.size() - 1) {
                    json = json + "},";
                } else {
                    json = json + "}";
                }
            }
            json = json + "]";

            String json2 = "[";
            for (int i = 0; i < lst.size(); i++) {
                json2 = json2 + "{'Año':'" + lst.get(i).getIndice() + "',";
                json2 = json2 + "'Recaudados':'" + formateador.format(lst.get(i).getTotal1()) + "',";
                json2 = json2 + "'Pendientes':'" + formateador.format(lst.get(i).getTotal2()) + "',";
                json2 = json2 + "'Total':'" + formateador.format(lst.get(i).getTotal3()) + "'";
                if (i != lst.size() - 1) {
                    json2 = json2 + "},";
                } else {
                    json2 = json2 + "}";
                }
            }
            json2 = json2 + "]";

            System.out.println("json1: " + json);
            System.out.println("json2: " + json2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
