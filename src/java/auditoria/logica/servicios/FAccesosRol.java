/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria.logica.servicios;

import accesoDatos.AccesoDatos;
import auditoria.logica.entidades.AccesosRol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosRoles;
import master.logica.servicios.ServiciosUsuario;

public class FAccesosRol {

    public static String registrarAccesoRol(AccesosRol accesRol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_registrar_login_rol(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, accesRol.getRolUsuario().getIdUsuario().getIdPersona());
            prstm.setInt(2, accesRol.getRolUsuario().getIdRol().getIdRol());
            prstm.setString(3, accesRol.getIpAcceso());
            prstm.setString(4, accesRol.getHost());
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

    public static List<AccesosRol> listarAccesosDadoRolUsuario(int idUsuario, int idRol) throws Exception {
        List<AccesosRol> lst = new ArrayList<>();
        AccesosRol accesRol;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from auditoria.f_seleccionar_accesos_dado_rol_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            prstm.setInt(2, idRol);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                accesRol = new AccesosRol();
                accesRol.setCodigo(resultSet.getInt("int_codigo"));
                accesRol.getRolUsuario().setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("int_id_rol")));
                accesRol.getRolUsuario().setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                accesRol.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                accesRol.setIpAcceso(resultSet.getString("str_ip_acceso"));
                accesRol.setHost(resultSet.getString("chv_host"));
                accesRol.setHoraIngreso(resultSet.getTime("tm_hora_ingreso"));
                accesRol.setHoraSalida(resultSet.getTime("tm_hora_salida"));
                lst.add(accesRol);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
