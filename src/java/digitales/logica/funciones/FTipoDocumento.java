/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitales.logica.funciones;

import accesoDatos.AccesoDatos;
import digitales.logica.entidades.TipoDocumento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Geovanny Cudco
 */
public class FTipoDocumento {

    public static List<TipoDocumento> obtenerTiposDocumentos() throws Exception {
        List<TipoDocumento> lst = new ArrayList<>();
        TipoDocumento tipo;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from digitales.f_obtener_tipos_documentos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                tipo = new TipoDocumento();
                tipo.setIdTipo(resultSet.getInt("sr_id_tipo"));
                tipo.setTipoDocumento(resultSet.getString("chv_tipo_documento"));
                tipo.setDescripcion(resultSet.getString("chv_descripcion"));
                tipo.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipo.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(tipo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static TipoDocumento obtenerTipoDadoCodigo(int codigo) throws Exception {
        TipoDocumento documento = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM digitales.f_obtener_tipos_documentos(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                documento = new TipoDocumento();
                documento.setIdTipo(resultSet.getInt("sr_id_tipo"));
                documento.setTipoDocumento(resultSet.getString("chv_tipo_documento"));
                documento.setDescripcion(resultSet.getString("chv_descripcion"));
                documento.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                documento.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                documento.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                documento.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                documento.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return documento;
    }
}
