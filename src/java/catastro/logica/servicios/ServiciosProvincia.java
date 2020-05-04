package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Provincia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiciosProvincia {

    public static ArrayList<Provincia> obtenerProvincias() throws Exception {
        ArrayList<Provincia> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Provincia provincia;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_provincias();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                provincia = new Provincia();
                provincia.setIdProvincia(resultSet.getInt("sr_id_provincia"));
                provincia.setNombre(resultSet.getString("chv_nombre"));
                provincia.setCodigo(resultSet.getString("chv_codigo"));
                provincia.setDescripcion(resultSet.getString("chv_descripcion"));
                provincia.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                provincia.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                provincia.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                provincia.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(provincia);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Provincia obtenerProvinciaDadoCodigo(int codigo) throws Exception {
        Provincia provincia = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_provincia_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                provincia = new Provincia();
                provincia.setIdProvincia(resultSet.getInt("sr_id_provincia"));
                provincia.setNombre(resultSet.getString("chv_nombre"));
                provincia.setCodigo(resultSet.getString("chv_codigo"));
                provincia.setDescripcion(resultSet.getString("chv_descripcion"));
                provincia.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                provincia.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                provincia.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                provincia.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return provincia;
    }
}
