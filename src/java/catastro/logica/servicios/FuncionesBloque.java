package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Bloque2;
import catastro.logica.entidades.PredioV2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FuncionesBloque {

    public static String registrarBloque(Bloque2 bloque) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_bloque_v2(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, bloque.getPredio().getIdPredio());
            prstm.setString(2, bloque.getDescripcion());
            prstm.setDouble(3, bloque.getAreaBloque());
            prstm.setString(4, bloque.getBloque());
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

    public static ArrayList<Bloque2> obtenerBloquesDadoPredio(int predio) throws Exception {
        ArrayList<Bloque2> lst = new ArrayList<>();
        Bloque2 bloque;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_bloques_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                bloque = new Bloque2();
                bloque.setIdBloque(resultSet.getInt("int_id_bloque"));
                bloque.setBloque(resultSet.getString("chv_bloque"));
                bloque.setDescripcion(resultSet.getString("chv_descripcion"));
                bloque.setAreaBloque(resultSet.getDouble("db_area_bloque"));
                bloque.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
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

    public static Bloque2 obtenerBloqueDadoCodigo(int codigo) throws Exception {
        Bloque2 bloque = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_bloque_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                bloque = new Bloque2();
                bloque.setPredio(new PredioV2());
                bloque.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                bloque.setIdBloque(resultSet.getInt("int_id_bloque"));
                bloque.setBloque(resultSet.getString("chv_bloque"));
                bloque.setAreaBloque(resultSet.getDouble("db_area_bloque"));
                bloque.setDescripcion(resultSet.getString("chv_descripcion"));
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

    public static String actualizarBloque(Bloque2 bloque) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_bloque_v2(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, bloque.getIdBloque());
            prstm.setInt(2, bloque.getPredio().getIdPredio());
            prstm.setString(3, bloque.getDescripcion());
            prstm.setDouble(4, bloque.getAreaBloque());
            prstm.setString(5, bloque.getBloque());
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

    public static String eliminarBloque(int bloque) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_desactivar_bloque(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, bloque);
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

    public static ArrayList<Bloque2> obtenerBloquesDadoClaveCatastral(String claveCatastral) throws Exception {
        ArrayList<Bloque2> lst = new ArrayList<>();
        Bloque2 bloque;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_bloques_dado_clave_catastral(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, claveCatastral);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                bloque = new Bloque2();
                bloque.setIdBloque(resultSet.getInt("pint_id_bloque"));
                bloque.setBloque(resultSet.getString("pchv_bloque"));
                bloque.setDescripcion(resultSet.getString("pchv_descripcion"));
                bloque.setAreaBloque(resultSet.getDouble("pdb_area_bloque"));
                // bloque.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                bloque.setEstadoLogico(resultSet.getString("pch_estado_logico"));
                lst.add(bloque);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String actualizarBloqueAlfanumerico(Bloque2 bloque) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_bloque_v2_alfanumerico(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, bloque.getIdBloque());
            prstm.setString(2, bloque.getDescripcion());
            prstm.setString(3, bloque.getBloque());
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
    
    public static ArrayList<Bloque2> obtenerBloquesDadoCodigoPredio(int codigoPredio) throws Exception {
        ArrayList<Bloque2> lst = new ArrayList<>();
        Bloque2 bloque;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_bloques_dado_predio_v2(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                bloque = new Bloque2();
                bloque.setIdBloque(resultSet.getInt("pint_id_bloque"));
                bloque.setBloque(resultSet.getString("pchv_bloque"));
                bloque.setDescripcion(resultSet.getString("pchv_descripcion"));
                bloque.setAreaBloque(resultSet.getDouble("pdb_area_bloque"));
                // bloque.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                bloque.setEstadoLogico(resultSet.getString("pch_estado_logico"));
                lst.add(bloque);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
