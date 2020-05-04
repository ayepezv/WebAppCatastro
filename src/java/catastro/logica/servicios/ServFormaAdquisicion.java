/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.FormaAdquisicion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class ServFormaAdquisicion {

    public static ArrayList<FormaAdquisicion> obtenerFormasAdq() throws Exception {
        ArrayList<FormaAdquisicion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        FormaAdquisicion formaAdq;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_forma_adq();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                formaAdq = new FormaAdquisicion();
                formaAdq.setIdFormaAdq(resultSet.getInt("sr_id_forma_adquision"));
                formaAdq.setFormaAdq(resultSet.getString("chv_forma_adq"));
                formaAdq.setDescripcion(resultSet.getString("chv_descripcion"));
                formaAdq.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                formaAdq.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                formaAdq.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                formaAdq.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(formaAdq);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static FormaAdquisicion obtenerFormaAdqDadoCodigo(int codigo) throws Exception {
        FormaAdquisicion formaAdq = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_forma_adq_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                formaAdq = new FormaAdquisicion();
                formaAdq.setIdFormaAdq(resultSet.getInt("sr_id_forma_adquision"));
                formaAdq.setFormaAdq(resultSet.getString("chv_forma_adq"));
                formaAdq.setDescripcion(resultSet.getString("chv_descripcion"));
                formaAdq.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                formaAdq.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                formaAdq.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                formaAdq.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return formaAdq;
    }
}
