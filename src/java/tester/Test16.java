/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Geovanny
 */
public class Test16 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<Usuario> lst = ServiciosUsuario.obtenerUsuariosDelSistema();
            for (int i = 0; i < lst.size(); i++) {
                System.out.println("usuario: " + lst.get(i).getNick());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
