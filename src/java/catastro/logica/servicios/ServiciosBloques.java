package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Bloque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiciosBloques {

    public static ArrayList<Bloque> obtenerBloquesDadoPredio(int predio) throws Exception {
        ArrayList<Bloque> lst = new ArrayList<>();
        Bloque bloque;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_seleccionar_bloques_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                bloque = new Bloque();
                bloque.setIdBloque(resultSet.getInt("sr_id_bloque"));
                bloque.setBloque(resultSet.getString("chv_bloque"));
                bloque.setDescripcion(resultSet.getString("chv_descripcion"));
                bloque.setArea(resultSet.getDouble("db_area"));
                bloque.setPredio(ServiciosPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                bloque.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                bloque.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                bloque.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                bloque.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(bloque);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarBloque(Bloque bloque) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_bloque(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, bloque.getBloque());
            prstm.setString(2, bloque.getDescripcion());
            prstm.setDouble(3, bloque.getArea());
            prstm.setInt(4, bloque.getPredio().getIdPredio());
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

    public static Bloque obtenerBloqueDadoCodigo(int codigo) throws Exception {
        Bloque bloque = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_bloque_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                bloque = new Bloque();
                bloque.setIdBloque(resultSet.getInt("sr_id_bloque"));
                bloque.setBloque(resultSet.getString("chv_bloque"));
                bloque.setDescripcion(resultSet.getString("chv_descripcion"));
                bloque.setArea(resultSet.getDouble("db_area"));
                bloque.setPredio(ServiciosPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                bloque.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                bloque.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                bloque.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                bloque.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return bloque;
    }

}
