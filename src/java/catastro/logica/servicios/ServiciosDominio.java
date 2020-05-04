package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Dominio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiciosDominio {

    public static Dominio obtenerDominioDadoCodigo(int codigo) throws Exception {
        Dominio dominio = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_dominio_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                dominio = new Dominio();
                dominio.setIdDominio(resultSet.getInt("sr_id_dominio"));
                dominio.setDominio(resultSet.getString("chv_dominio"));
                dominio.setDescripcion(resultSet.getString("chv_descripcion"));
                dominio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                dominio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                dominio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                dominio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return dominio;
    }

    public static ArrayList<Dominio> seleccionarDominios() throws Exception {
        ArrayList<Dominio> lst = new ArrayList<>();
        Dominio dominio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_dominio()";
            prstm = accesoDatos.creaPreparedSmt(sql);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                dominio = new Dominio();
                dominio.setIdDominio(resultSet.getInt("sr_id_dominio"));
                dominio.setDominio(resultSet.getString("chv_dominio"));
                dominio.setDescripcion(resultSet.getString("chv_descripcion"));
                dominio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                dominio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                dominio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                dominio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(dominio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
