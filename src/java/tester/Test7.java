/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.util.ArrayList;
import master.logica.entidades.RolUsuario;
import master.logica.servicios.ServiciosRolUsuario;

/**
 *
 * @author Geovanny
 */
public class Test7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<RolUsuario> lst = ServiciosRolUsuario.listarRolesDadoUsuario(1);
            for (int i = 0; i < lst.size(); i++) {
                System.out.println("\nUsuario: " + lst.get(i).getIdUsuario().getApellidos()
                        + "\nRol: " + lst.get(i).getIdRol().getNombre()
                        + "\nPrivilegios\n"
                        + "\nSeleccionar: " + lst.get(i).getSeleccionar()
                        + "\nInsertar: " + lst.get(i).getInsertar()
                        + "\nActualizar: " + lst.get(i).getActualizar()
                        + "\nEliminar: " + lst.get(i).getEliminar());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
