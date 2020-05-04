/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.cem.entidades.Obra;
import catastro.cem.funciones.FObra;
import java.util.List;

public class Test13 {

    public static void main(String[] args) throws Exception {
        try {
            List<Obra> obras = FObra.obtenerTiposObras();
            String json = "[";
            for (int i = 0; i < obras.size(); i++) {
                json = json + "{'Tipo de obra':'" + obras.get(i).getTipoObra().getDescripcion() + "',";
                json = json + "'Total':'" + FObra.obtenerObraDadoTipo(obras.get(i).getTipoObra().getIdTipoObra()).size() + "'";
                if (i != obras.size() - 1) {
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
