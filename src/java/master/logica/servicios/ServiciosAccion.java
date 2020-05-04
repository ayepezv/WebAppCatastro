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
import master.logica.entidades.Accion;

/**
 *
 * @author Geovanny
 */
public class ServiciosAccion {

    public static ArrayList<Accion> obtenerAcciones() throws Exception {
        ArrayList<Accion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Accion accion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from master.f_seleccionar_acciones();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                accion = new Accion();
                accion.setIdAccion(resultSet.getInt("sr_id_accion"));
                accion.setUrl(resultSet.getString("chv_url"));
                accion.setTitulo(resultSet.getString("chv_titulo"));
                accion.setDescripcion(resultSet.getString("chv_descripcion"));
                accion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                accion.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                accion.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                accion.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                lst.add(accion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Accion> obtenerAccionesDadoEstado(String estado) throws Exception {
        ArrayList<Accion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Accion accion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_acciones_dado_estado(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                accion = new Accion();
                accion.setIdAccion(resultSet.getInt("sr_id_accion"));
                accion.setUrl(resultSet.getString("chv_url"));
                accion.setTitulo(resultSet.getString("chv_titulo"));
                accion.setDescripcion(resultSet.getString("chv_descripcion"));
                accion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                accion.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                accion.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                accion.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                lst.add(accion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarAlerta(Accion accion) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_accion(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, accion.getUrl());
            prstm.setString(2, accion.getTitulo());
            prstm.setString(3, accion.getDescripcion());
            prstm.setInt(4, accion.getSessionUsuario().getIdPersona());
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

    public static String actualizarAlerta(Accion accion) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_acciones(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, accion.getUrl());
            prstm.setString(2, accion.getTitulo());
            prstm.setString(3, accion.getDescripcion());
            prstm.setInt(4, accion.getIdAccion());
            prstm.setInt(5, accion.getSessionUsuario().getIdPersona());
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

    public static String eliminarAccion(Accion accion) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_eliminar_accion(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, accion.getIdAccion());
            prstm.setInt(2, accion.getSessionUsuario().getIdPersona());
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
