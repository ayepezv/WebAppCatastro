/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import digitales.logica.funciones.FDocumento;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author gcudcop
 */
public class Test15 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DATE);
        int mes = c.get(Calendar.MONTH);
        int annio = c.get(Calendar.YEAR);

        int anio = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("dia: " + dia + "\nmes: " + mes + "\nanio: " + annio+"\nanio 2 "+anio);
        
        System.out.println("Documento: "+FDocumento.obtenerDocumentoDadoCodigo(2).getTitulo());
        
        
        
    }

}
