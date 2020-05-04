package catastro.recaudacion.funciones;

import accesoDatos.AccesoDatos;
import catastro.recaudacion.entidades.TitulosHistoricos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;
import catastro.recaudacion.entidades.Titulo;

public class FTitulosHistoricos {

    public static String anularTitulo(TitulosHistoricos titulo) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_anular_titulo_dado_predio(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, titulo.getTitulo().getIdTitulo());
            prstm.setString(2, titulo.getRazonBaja());
            prstm.setString(3, titulo.getObservaciones());
            prstm.setString(4, titulo.getPathRespaldo());
            prstm.setInt(5, titulo.getSessionUsuario().getIdPersona());
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

    public static List<TitulosHistoricos> obtenerTitulosHistoricos() throws Exception {
        List<TitulosHistoricos> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        TitulosHistoricos titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_titulos_historicos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                titulo = new TitulosHistoricos();
                titulo.setIdTituloHistorico(resultSet.getInt("sr_id_titulo_historico"));
                titulo.setTitulo(FTitulos.obtenerTituloDadoId(resultSet.getInt("int_id_titulo")));
                titulo.setRazonBaja(resultSet.getString("chv_razon_baja"));
                titulo.setObservaciones(resultSet.getString("chv_observaciones"));
                titulo.setPathRespaldo(resultSet.getString("chv_path_respaldo"));
                titulo.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                titulo.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                titulo.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                titulo.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                titulo.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<TitulosHistoricos> obtenerTitulosDadoAnio(int anio) throws Exception {
        List<TitulosHistoricos> lst = new ArrayList<>();
        TitulosHistoricos titulo;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_titulos_historicos_dado_anio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new TitulosHistoricos();
                titulo.setIdTituloHistorico(resultSet.getInt("sr_id_titulo_historico"));
                titulo.setTitulo(FTitulos.obtenerTituloDadoId(resultSet.getInt("int_id_titulo")));
                titulo.setRazonBaja(resultSet.getString("chv_razon_baja"));
                titulo.setObservaciones(resultSet.getString("chv_observaciones"));
                titulo.setPathRespaldo(resultSet.getString("chv_path_respaldo"));
                titulo.setEstadoLogico(resultSet.getString("estado_logico_historico"));
                titulo.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                titulo.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                titulo.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                titulo.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Titulo> obtenerAniosTitulos() throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_anios_titulos_historicos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setAnioTitulo(resultSet.getInt("anio"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
