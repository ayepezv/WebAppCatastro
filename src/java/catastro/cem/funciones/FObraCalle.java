package catastro.cem.funciones;

import accesoDatos.AccesoDatos;
import catastro.cem.entidades.ObraCalle;
import catastro.logica.servicios.ServiciosCalles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FObraCalle {

    public static List<ObraCalle> obtenerCallesDadoObra(int codigo) throws Exception {
        List<ObraCalle> lst = new ArrayList<>();
        ObraCalle obra;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_calles_afectada_dada_obra(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                obra = new ObraCalle();
                obra.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("int_id_obra")));
                obra.setCalle(ServiciosCalles.obtenerCalleDadoCodigo(resultSet.getInt("int_id_calle")));
                obra.setDescripcion(resultSet.getString("chv_descripcion"));
                obra.setObservaciones(resultSet.getString("chv_observaciones"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                lst.add(obra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String insertarObra(ObraCalle obra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_asignar_obra_calle(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra.getObra().getIdObra());
            prstm.setInt(2, obra.getCalle().getIdCalle());
            prstm.setString(3, obra.getDescripcion());
            prstm.setString(4, obra.getObservaciones());
            prstm.setInt(5, obra.getSessionUsuario().getIdPersona());
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
    
    
    public static String eliminarObra(ObraCalle obra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_obra_calle(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra.getObra().getIdObra());
            prstm.setInt(2, obra.getCalle().getIdCalle());            
            prstm.setInt(3, obra.getSessionUsuario().getIdPersona());
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
