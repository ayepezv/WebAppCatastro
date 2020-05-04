/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import com.estadisticas.entidades.Estadisticas;
import com.estadisticas.servicios.ServiciosEstadisticas;
import java.util.List;

public class Test14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            List<Estadisticas> est = ServiciosEstadisticas.estadisticasTitulosPorAnio();
            String json = "[";
            for (int i = 0; i < est.size(); i++) {
                json = json + "{'Año':'" + est.get(i).getIndice() + "',";
                json = json + "'Total':'" + est.get(i).getTotalInt() + "'";
                if (i != est.size() - 1) {
                    json = json + "},";
                } else {
                    json = json + "}";
                }
            }
            json = json + "]";
            System.out.println("Json Data: "+json);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
