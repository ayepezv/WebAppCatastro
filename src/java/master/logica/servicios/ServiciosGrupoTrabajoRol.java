/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.GrupoTrabajo;
import master.logica.entidades.GrupoTrabajoRol;
import master.logica.entidades.Rol;
import master.logica.entidades.Usuario;

public class ServiciosGrupoTrabajoRol {

    public static ArrayList<GrupoTrabajoRol> obtenerGruposRoles() throws Exception {
        ArrayList<GrupoTrabajoRol> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        GrupoTrabajoRol grupoRol;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from master.f_seleccionar_grupos_roles();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                grupoRol = new GrupoTrabajoRol();
                grupoRol.setSessionUsuario(new Usuario());
                grupoRol.setRol(new Rol());
                grupoRol.setGrupo(new GrupoTrabajo());
                grupoRol.setGrupo(ServiciosGrupoTrabajo.obtenerGrupoDadoCodigo(resultSet.getInt("id_grupo_trabajo")));
                grupoRol.setRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("id_rol")));
                grupoRol.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                grupoRol.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                grupoRol.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                grupoRol.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                lst.add(grupoRol);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String asignarRolGrupo(GrupoTrabajoRol grupoRol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_asignar_rol_grupo(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, grupoRol.getRol().getIdRol());
            prstm.setInt(2, grupoRol.getGrupo().getIdGrupoTrabajo());
            prstm.setInt(3, grupoRol.getSessionUsuario().getIdPersona());
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getString(1);
                return respuesta;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
