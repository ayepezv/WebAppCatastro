/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.logica.entidades.Parroquia;
import catastro.logica.servicios.ServiciosParroquia;
import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geovanny
 */
public class Test20 {

    public static void main(String[] args) {
        try {
            ArrayList<Parroquia> lst = ServiciosParroquia.obtenerParroquiasArchidona();
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(lst.get(i).getIdParroquia() + " - " + lst.get(i).getNombre());
            }
            System.out.println("-----------------------------------------------------------------------");
            List<Titulo> lstAnios = FTitulos.obtenerAniosTitulos();
            for (int i = 0; i < lstAnios.size(); i++) {
                System.out.println(lstAnios.get(i).getAnioTitulo());
            }
            
             List<Titulo> lst2 = FTitulos.obtenerTituloDadoAnio(2008);
            System.out.println("Total "+lst2.size());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
