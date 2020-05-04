/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.logica.entidades.Bloque2;
import catastro.logica.servicios.FuncionesBloque;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class Test0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<Bloque2> lst = FuncionesBloque.obtenerBloquesDadoClaveCatastral("01022444000000");
            for(int i=0;i<lst.size();i++){
                System.out.println("Clave Catastral: "+lst.get(i).getBloque());
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
