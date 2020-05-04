/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.TipoLote;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class ServiciosTipoLote {

    public static ArrayList<TipoLote> obtenerTiposLotes() throws Exception {
        ArrayList<TipoLote> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        TipoLote tipoLote;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_tipos_lote();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                tipoLote = new TipoLote();
                tipoLote.setIdTipoLote(resultSet.getInt("sr_id_tipo_lote"));
                tipoLote.setNombre(resultSet.getString("chv_nombre"));
                tipoLote.setCodigo(resultSet.getString("chv_codigo"));
                tipoLote.setDescripcion(resultSet.getString("chv_descripcion"));
                tipoLote.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tipoLote.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipoLote.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                tipoLote.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));

                lst.add(tipoLote);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static TipoLote obtenerTipoLoteDadoCodigo(int codigo) throws Exception {
        TipoLote tipoLote = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_tipo_lote_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                tipoLote = new TipoLote();
                tipoLote.setIdTipoLote(resultSet.getInt("sr_id_tipo_lote"));
                tipoLote.setNombre(resultSet.getString("chv_nombre"));
                tipoLote.setCodigo(resultSet.getString("chv_codigo"));
                tipoLote.setDescripcion(resultSet.getString("chv_descripcion"));
                tipoLote.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tipoLote.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipoLote.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                tipoLote.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return tipoLote;
    }

}
