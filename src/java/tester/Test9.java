/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.util.ArrayList;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Geovanny
 */
public class Test9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            String json = "[";
            ArrayList<Usuario> usuarios = ServiciosUsuario.obtenerUsuariosDadoGrupoTrabajo(2);
            for (int i = 0; i < usuarios.size(); i++) {
                json = json + "{'Usuario':'" + usuarios.get(i).getNick() + "',";
                json = json + "'Accesos':";
                json = json + "'" + ServiciosAccesosUsuario.obtenerAccesosDadoUsuario(usuarios.get(i).getIdPersona()).size() + "'";
                if (i != usuarios.size() - 1) {
                    json = json + "},";
                } else {
                    json = json + "}";
                }
            }
            json = json + "]";
            System.out.println("Json: " + json);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
