package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.RepositorioDocumentos;
import digitales.logica.funciones.FDocumento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FRepositorioDocumentos {

    public static List<RepositorioDocumentos> obtenerDocumentosDadoPredio(int codigoPredio) throws Exception {
        List<RepositorioDocumentos> lst = new ArrayList<>();
        RepositorioDocumentos repositorio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_documentos_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                repositorio = new RepositorioDocumentos();
                repositorio.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                repositorio.setDocumento(FDocumento.obtenerDocumentoDadoCodigo(resultSet.getInt("id_documento")));
                repositorio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                repositorio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                repositorio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                repositorio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                repositorio.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                lst.add(repositorio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarDocumentoDadoPredio(RepositorioDocumentos repositorio) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_registrar_documento_dado_predio(?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, repositorio.getPredio().getIdPredio());
            prstm.setString(2, repositorio.getDocumento().getTitulo());
            prstm.setString(3, repositorio.getDocumento().getPath());
            prstm.setString(4, repositorio.getDocumento().getTipo());
            prstm.setString(5, repositorio.getDocumento().getKeywords());
            prstm.setInt(6, repositorio.getSessionUsuario().getIdPersona());
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
    
    public static String eliminarDocumento(RepositorioDocumentos repositorio) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_documento_repositorio(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, repositorio.getPredio().getIdPredio());
            prstm.setInt(2, repositorio.getDocumento().getCodigo());
            prstm.setInt(3, repositorio.getSessionUsuario().getIdPersona());
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
