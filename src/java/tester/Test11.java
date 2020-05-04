/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.cem.entidades.DetalleCuotasCem;
import catastro.cem.funciones.FDetalleCuotasCem;
import catastro.recaudacion.entidades.Transanccion;
import catastro.recaudacion.funciones.FTransaccion;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import static java.time.LocalDate.now;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geovanny
 */
public class Test11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            //borrar luego
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse("2017-06-01");
            Date parsed1 = format.parse("2017-07-12");
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            java.sql.Date sql1 = new java.sql.Date(parsed1.getTime());
            //// hasta aqui
            System.out.println("Fecha inicio: " + sql);
            System.out.println("Fecha fin: " + sql1);

            List<Transanccion> lstTransacciones = FTransaccion.transPorDiaUsuarioRangoFecha(1, sql, sql1);

            System.out.println("total de elementos: " + lstTransacciones.size());

            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);

// Esto sale en pantalla con dos decimales, es decir, 3,43
            System.out.println(formateador.format(3.43242383));

//            String json = "[";
//            for (int i = 0; i < lstTransacciones.size(); i++) {
//                json = json + "['" + lstTransacciones.get(i).getRecaudacion() + "'," + formateador.format(lstTransacciones.get(i).getValorTransaccion()) + "]";
//                if (i != lstTransacciones.size() - 1) {
//                    json = json + ",";
//                }
//            }
//            json = json + "]";
            String json2 = "";
            for (int i = 0; i < lstTransacciones.size(); i++) {
                json2 = json2 + "{'rubro':'"
                        + lstTransacciones.get(i).getRecaudacion()
                        + "','total':'"
                        + formateador.format(lstTransacciones.get(i).getValorTransaccion())
                        + "'}";
                if (i != lstTransacciones.size() - 1) {
                    json2 = json2 + ",";
                }
            }

            String json3 = "";
            for (int i = 0; i < lstTransacciones.size(); i++) {
                json3 = json3
                        + "{'dia':'"
                        + lstTransacciones.get(i).getObservaciones() + "'"
                        + ","
                        + "'" + lstTransacciones.get(i).getRecaudacion() + "':"
                        + "'" + formateador.format(lstTransacciones.get(i).getValorTransaccion()) + "'"
                        + "}";
                if (i != lstTransacciones.size() - 1) {
                    json3 = json3 + ",";
                }
            }

            List<String> semana = new ArrayList<>();
            semana.add("Lunes");
            semana.add("Martes");
            semana.add("Miercoles");
            semana.add("Jueves");
            semana.add("Viernes");
            semana.add("Sabado");
            semana.add("Domingo");

            List<Transanccion> transacciones = new ArrayList<>();

            String json4 = "";
            for (int i = 0; i < semana.size(); i++) {
                transacciones = FTransaccion.transPorDiaUsuarioRangoFechaDia(1, sql, sql1, semana.get(i));
                json4 = json4 + "{'dia':'"
                        + semana.get(i) + "'";

                for (int j = 0; j < transacciones.size(); j++) {
                    System.out.println(transacciones.get(j).getRecaudacion() + ":" + transacciones.get(j).getValorTransaccion());
                    json4 = json4 + ",'"
                            + transacciones.get(j).getRecaudacion() + "':'"
                            + formateador.format(lstTransacciones.get(i).getValorTransaccion()) + "'";
                }

                json4 = json4 + "}";
                if (i != semana.size() - 1) {
                    json4 = json4 + ",";
                }
            }

            String json = "";

            List<Transanccion> dias = FTransaccion.obtenerDiasTransDadoUsuarioRangoFecha(1, sql, sql1);

            for (int i = 0; i < dias.size(); i++) {
                transacciones = FTransaccion.transPorDiaUsuarioRangoFechaDia(1, sql, sql1, dias.get(i).getObservaciones());
                json = json + "{'dia':'"
                        + dias.get(i).getObservaciones() + "'";

                for (int j = 0; j < transacciones.size(); j++) {
                    json = json + ",'"
                            + transacciones.get(j).getRecaudacion() + "':'"
                            + formateador.format(transacciones.get(j).getValorTransaccion()) + "'";
                }
                json = json + "}";
                if (i != dias.size() - 1) {
                    json = json + ",";
                }
            }

            String estilo = "";
            List<Transanccion> trans = FTransaccion.obtenerTiposTransDadoUsuarioRangoFecha(1, sql, sql1);
            for (int i = 0; i < trans.size(); i++) {
                if (i == 0) {
                    estilo = estilo
                            + "{'balloonText':'GDP grow in [[category]] ("
                            + trans.get(i).getRecaudacion()
                            + "): <b>[[value]]</b>',";
                    estilo = estilo + "'fillAlphas': 0.9,"
                            + "'lineAlpha': 0.2,"
                            +"'title':'"
                            + trans.get(i).getRecaudacion()+"',"
                            +"'type':'column',"
                            +"'valueField':'"
                            + trans.get(i).getRecaudacion()+"'}";
                } else {
                    estilo = estilo
                            + "{'balloonText':'GDP grow in [[category]] ("
                            + trans.get(i).getRecaudacion()
                            + "): <b>[[value]]</b>',";
                    estilo = estilo + "'fillAlphas': 0.9,"
                            + "'lineAlpha': 0.2,"
                            +"'title':'"
                            + trans.get(i).getRecaudacion()+"',"
                            +"'type':'column',"
                            +"'clustered':false,"
                            +"'columnWidth':0.5,"
                            +"'valueField':'"
                            + trans.get(i).getRecaudacion()+"'}";
                }
                 if (i != trans.size() - 1) {
                    estilo = estilo + ",";
                }
            }

            System.out.println("json: ");
            System.out.println(json);
            
              System.out.println("estilo: ");
            System.out.println(estilo);
            
            System.out.println("json2: ");
            System.out.println(json2);
            System.out.println("json3: ");
            System.out.println(json3);

            System.out.println("json4: ");
            System.out.println(json4);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
