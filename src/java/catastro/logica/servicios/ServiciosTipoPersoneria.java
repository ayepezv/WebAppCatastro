package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.TipoPersoneria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiciosTipoPersoneria {

    public static ArrayList<TipoPersoneria> obtenerTiposPersoneria() throws Exception {
        ArrayList<TipoPersoneria> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        TipoPersoneria tipoPersoneria;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_tipos_personeria();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                tipoPersoneria = new TipoPersoneria();
                tipoPersoneria.setIdTipoPersoneria(resultSet.getInt("sr_id_tipo_personeria"));
                tipoPersoneria.setNombre(resultSet.getString("chv_nombre"));
                tipoPersoneria.setDescripcion(resultSet.getString("chv_descripcion"));
                tipoPersoneria.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tipoPersoneria.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipoPersoneria.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                tipoPersoneria.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(tipoPersoneria);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public static TipoPersoneria obtenerTipoPersoneriaDadoCodigo(int codigo) throws Exception {
       TipoPersoneria tipoPersoneria = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_tipo_personeria_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                tipoPersoneria = new TipoPersoneria();
                tipoPersoneria.setIdTipoPersoneria(resultSet.getInt("sr_id_tipo_personeria"));
                tipoPersoneria.setNombre(resultSet.getString("chv_nombre"));
                tipoPersoneria.setDescripcion(resultSet.getString("chv_descripcion"));
                tipoPersoneria.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tipoPersoneria.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipoPersoneria.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                tipoPersoneria.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return tipoPersoneria;
    }
}
