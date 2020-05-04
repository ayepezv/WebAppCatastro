/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import auditoria.logica.servicios.FAccesosRol;
import java.util.ArrayList;
import master.logica.entidades.RolUsuario;
import master.logica.servicios.ServiciosRolUsuario;

/**
 *
 * @author Geovanny
 */
public class Test10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            ArrayList<RolUsuario> roles = ServiciosRolUsuario.listarUsuariosDadoGrupoTrabajoUsuario(2, 1);
            String json = "[";
            int idRol, idUsu;
            for (int i = 0; i < roles.size(); i++) {
                idRol = roles.get(i).getIdRol().getIdRol();
                idUsu = roles.get(i).getIdUsuario().getIdPersona();

                json = json + "{'Rol':'" + roles.get(i).getIdRol().getNombre() + "',";
                json = json + "'Accesos':'" + FAccesosRol.listarAccesosDadoRolUsuario(idUsu, idRol).size() + "'";

                if (i != roles.size() - 1) {
                    json = json + "},";
                } else {
                    json = json + "}";
                }
            }

            json = json + "]";

            System.out.println("json: " + json);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
