/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.ExencionEspecial;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class ServiciosExenciones {

    public static ArrayList<ExencionEspecial> obtenerExenciones() throws Exception {
        ArrayList<ExencionEspecial> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        ExencionEspecial exencion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_exenciones();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                exencion = new ExencionEspecial();
                exencion.setIdExccencion(resultSet.getInt("sr_id_excencion"));
                exencion.setExcencion(resultSet.getString("chv_excencion"));
                exencion.setDescripcion(resultSet.getString("chv_descripcion"));
                exencion.setPorcentajeTexto(resultSet.getString("chv_porcentaje"));
                exencion.setPorcentajeNumerico(resultSet.getDouble("db_porcentaje"));
                exencion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                exencion.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                exencion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                exencion.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                exencion.setFormaUso(resultSet.getString("ts_forma_uso"));
                lst.add(exencion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ExencionEspecial obtenerExencionDadoCodigo(int codigo) throws Exception {
        ExencionEspecial exencion = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_exencion_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                exencion = new ExencionEspecial();
                exencion.setIdExccencion(resultSet.getInt("sr_id_excencion"));
                exencion.setExcencion(resultSet.getString("chv_excencion"));
                exencion.setDescripcion(resultSet.getString("chv_descripcion"));
                exencion.setPorcentajeTexto(resultSet.getString("chv_porcentaje"));
                exencion.setPorcentajeNumerico(resultSet.getDouble("db_porcentaje"));
                exencion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                exencion.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                exencion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                exencion.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                exencion.setFormaUso(resultSet.getString("ts_forma_uso"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return exencion;
    }
}
