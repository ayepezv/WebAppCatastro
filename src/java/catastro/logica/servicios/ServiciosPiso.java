package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Piso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiciosPiso {

    public static ArrayList<Piso> obtenerPisosDadoBloque(int bloque) throws Exception {
        ArrayList<Piso> lst = new ArrayList<>();
        Piso piso;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_seleccionar_pisos_dado_bloque(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, bloque);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                piso = new Piso();
                piso.setIdPiso(resultSet.getInt("sr_id_piso"));
                piso.setBloque(ServiciosBloques.obtenerBloqueDadoCodigo(resultSet.getInt("int_id_bloque")));
                piso.setPiso(resultSet.getString("chv_piso"));
                piso.setDescripcion(resultSet.getString("chv_descripcion"));
                piso.setAreaPiso(resultSet.getDouble("db_area_piso"));
                piso.setCondFisica(resultSet.getString("chv_condicion_fisica"));
                piso.setValorUnidad(resultSet.getDouble("db_valor_unidad"));
                piso.setAnioEdificacion(resultSet.getInt("int_anio_edificacion"));
                piso.setAnioReconstruccion(resultSet.getInt("int_anio_reconstr"));
                piso.setEstado(resultSet.getString("chv_estado"));
                piso.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                piso.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                piso.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                piso.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(piso);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Piso obtenerPisoDadoCodigo(int codigo) throws Exception {
        Piso piso = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_piso_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                piso = new Piso();
                piso.setIdPiso(resultSet.getInt("sr_id_piso"));
                piso.setBloque(ServiciosBloques.obtenerBloqueDadoCodigo(resultSet.getInt("int_id_bloque")));
                piso.setPiso(resultSet.getString("chv_piso"));
                piso.setDescripcion(resultSet.getString("chv_descripcion"));
                piso.setAreaPiso(resultSet.getDouble("db_area_piso"));
                piso.setCondFisica(resultSet.getString("chv_condicion_fisica"));
                piso.setValorUnidad(resultSet.getDouble("db_valor_unidad"));
                piso.setAnioEdificacion(resultSet.getInt("int_anio_edificacion"));
                piso.setAnioReconstruccion(resultSet.getInt("int_anio_reconstr"));
                piso.setEstado(resultSet.getString("chv_estado"));
                piso.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                piso.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                piso.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                piso.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return piso;
    }

    
    
    public static String registrarPiso(Piso piso) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_piso(?,?,?,?,?,?,?,?,?)";            
            prstm = accesoDatos.creaPreparedSmt(sql);
            
            prstm.setInt(1,piso.getBloque().getIdBloque());
            prstm.setString(2, piso.getPiso());
            prstm.setString(3, piso.getDescripcion());
            prstm.setDouble(4, piso.getAreaPiso());
            prstm.setString(5, piso.getCondFisica());
            prstm.setDouble(6, piso.getValorUnidad());
            prstm.setInt(7,piso.getAnioEdificacion());
            prstm.setInt(8,piso.getAnioReconstruccion());
            prstm.setString(9, piso.getEstado()); 
            
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
