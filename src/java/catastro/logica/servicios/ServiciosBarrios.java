/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Barrio;
import catastro.logica.entidades.Parroquia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class ServiciosBarrios {

    public static ArrayList<Barrio> obtenerBarriosDadoParroquia(int parroquia) throws Exception {
        ArrayList<Barrio> lst = new ArrayList<Barrio>();
        Barrio barrio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_seleccionar_barrio_dado_parroquia(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, parroquia);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                barrio = new Barrio();
                barrio.setParroquia(new Parroquia());
                barrio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(resultSet.getInt("int_id_parroquia")));
                barrio.setIdBarrio(resultSet.getInt("sr_id_barrio"));
                barrio.setNombre(resultSet.getString("chv_nombre"));
                barrio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                barrio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                barrio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                barrio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(barrio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Barrio obtenerSectorDadoCodigo(int codigo) throws Exception {
        Barrio barrio = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_barrio_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                barrio = new Barrio();
                barrio.setParroquia(new Parroquia());
                barrio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(resultSet.getInt("int_id_parroquia")));
                barrio.setIdBarrio(resultSet.getInt("sr_id_barrio"));
                barrio.setNombre(resultSet.getString("chv_nombre"));
                barrio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                barrio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                barrio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                barrio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return barrio;
    }

    public static ArrayList<Barrio> seleccionarBarrios() throws Exception {
        ArrayList<Barrio> lst = new ArrayList<>();
        Barrio barrio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_seleccionar_barrios_all()";
            prstm = accesoDatos.creaPreparedSmt(sql);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                barrio = new Barrio();
                barrio.setParroquia(new Parroquia());
                barrio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(resultSet.getInt("int_id_parroquia")));
                barrio.setIdBarrio(resultSet.getInt("sr_id_barrio"));
                barrio.setNombre(resultSet.getString("chv_nombre"));
                barrio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                barrio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                barrio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                barrio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(barrio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
