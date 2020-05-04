package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.RepositorioImagenes;
import digitales.logica.funciones.FImagen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FRepositorioImagenes {

    public static List<RepositorioImagenes> obtenerImagenesDadoPredio(int codigoPredio) throws Exception {
        List<RepositorioImagenes> lst = new ArrayList<>();
        RepositorioImagenes repositorio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_seleccionar_imagenes_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                repositorio = new RepositorioImagenes();
                repositorio.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                repositorio.setImagen(FImagen.obtenerImagenDadoCodigo(resultSet.getInt("id_imagen")));
                repositorio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                repositorio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                repositorio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                repositorio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                repositorio.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                repositorio.setCategoria(resultSet.getString("chv_categoria"));
                lst.add(repositorio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarImagenDadoPredio(RepositorioImagenes repositorio) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_registrar_imagen_dado_predio(?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, repositorio.getPredio().getIdPredio());
            prstm.setString(2, repositorio.getImagen().getTitulo());
            prstm.setString(3, repositorio.getImagen().getPath());
            prstm.setString(4, repositorio.getImagen().getTipo());
            prstm.setString(5, repositorio.getImagen().getKeywords());
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

    public static String eliminarImagen(RepositorioImagenes repositorio) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_imagen_repositorio(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, repositorio.getPredio().getIdPredio());
            prstm.setInt(2, repositorio.getImagen().getCodigo());
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

    public static String registrarImagenDadoPredioV2(RepositorioImagenes repositorio) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_registrar_imagen_dado_predio_v2(?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, repositorio.getPredio().getIdPredio());
            prstm.setString(2, repositorio.getImagen().getTitulo());
            prstm.setString(3, repositorio.getImagen().getPath());
            prstm.setString(4, repositorio.getImagen().getTipo());
            prstm.setString(5, repositorio.getImagen().getKeywords());
            prstm.setString(6, repositorio.getImagen().getCategoria());
            prstm.setInt(7, repositorio.getSessionUsuario().getIdPersona());
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
