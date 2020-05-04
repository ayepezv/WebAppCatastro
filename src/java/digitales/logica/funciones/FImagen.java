/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitales.logica.funciones;

import accesoDatos.AccesoDatos;
import digitales.logica.entidades.Imagen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Geovanny
 */
public class FImagen {

    public static String registrarDocumento(Imagen imagen) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from digitales.f_insertar_imagen(?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, imagen.getTitulo());
            prstm.setString(2, imagen.getPath());
            prstm.setString(3, imagen.getTipo());
            prstm.setString(4, imagen.getKeywords());
            prstm.setInt(5, imagen.getSessionUsuario().getIdPersona());
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

    public static String actualizarDocumento(Imagen documento) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from digitales.f_actualizar_imagen(?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, documento.getCodigo());
            prstm.setString(2, documento.getTitulo());
            prstm.setString(3, documento.getPath());
            prstm.setString(4, documento.getTipo());
            prstm.setString(5, documento.getKeywords());
            prstm.setInt(6, documento.getSessionUsuario().getIdPersona());
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

    public static String eliminarDocumento(Imagen documento) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from digitales.f_eliminar_imagen(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, documento.getCodigo());
            prstm.setInt(2, documento.getSessionUsuario().getIdPersona());
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

    public static Imagen obtenerImagenDadoCodigo(int codigo) throws Exception {
        Imagen imagen = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM digitales.f_obtener_imagen_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                imagen = new Imagen();
                imagen.setCodigo(resultSet.getInt("int_codigo"));
                imagen.setTitulo(resultSet.getString("chv_titulo"));
                imagen.setPath(resultSet.getString("chv_path"));
                imagen.setTipo(resultSet.getString("chv_tipo"));
                imagen.setKeywords(resultSet.getString("chv_keywords"));
                imagen.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                imagen.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                imagen.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                imagen.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                imagen.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                imagen.setCategoria(resultSet.getString("chv_categoria"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return imagen;
    }
}
