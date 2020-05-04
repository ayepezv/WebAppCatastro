package digitales.logica.funciones;

import accesoDatos.AccesoDatos;
import digitales.logica.entidades.Documento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import master.logica.servicios.ServiciosUsuario;

public class FDocumento {
    
    public static String registrarDocumento(Documento documento) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from digitales.f_insertar_documento(?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, documento.getTitulo());
            prstm.setString(2, documento.getPath());
            prstm.setString(3, documento.getTipo());
            prstm.setString(4, documento.getKeywords());
            prstm.setInt(5, documento.getSessionUsuario().getIdPersona());
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
    
    public static String actualizarDocumento(Documento documento) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from digitales.f_actualizar_documento(?,?,?,?,?,?,?)";
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
    
    public static String eliminarDocumento(Documento documento) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from digitales.f_eliminar_documento(?,?)";
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
    
    public static Documento obtenerDocumentoDadoCodigo(int codigo) throws Exception {
        Documento documento = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM digitales.f_obtener_documento_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                documento = new Documento();
                documento.setCodigo(resultSet.getInt("int_codigo"));
                documento.setTitulo(resultSet.getString("chv_titulo"));
                documento.setPath(resultSet.getString("chv_path"));
                documento.setTipo(resultSet.getString("chv_tipo"));
                documento.setKeywords(resultSet.getString("chv_keywords"));
                documento.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                documento.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                documento.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                documento.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                documento.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                documento.setTipoDocumento(FTipoDocumento.obtenerTipoDadoCodigo(resultSet.getInt("int_id_tipo")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return documento;
    }
    
}
