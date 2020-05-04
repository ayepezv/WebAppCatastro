/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import auditoria.logica.entidades.AMejoras;
import auditoria.logica.servicios.FAMejoras;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class Test4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<AMejoras> lst = FAMejoras.obtenerAuditoriaMejoras();
            System.out.println("Total de mejoras: "+lst.size());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
