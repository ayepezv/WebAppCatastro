/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.util.ArrayList;
import master.logica.entidades.NodoMenu;
import master.logica.servicios.ServiciosNodoMenu;
import recursos.ObtenerIPs;

/**
 *
 * @author Geovanny
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<NodoMenu> lst;
            lst = ServiciosNodoMenu.generarNodoSubmenu();
            for (int i = 0; i < lst.size(); i++) {
                System.out.println("SUBMENU: " + lst.get(i).getSubmenu().getNombre());
                System.out.println("ITEMS: ");
                for (int j = 0; j < lst.get(i).getItems().size(); j++) {
                    System.out.println("item: " + lst.get(i).getItems().get(j).getNombre());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
