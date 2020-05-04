
package aguapotable.logica.funciones;

import accesoDatos.AccesoDatos;
import aguapotable.logica.entidades.Ruta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Andres
 */
public class FRuta {
    
    public static Ruta obtenerRutaDadoCodigo(int codigo) throws Exception {
        Ruta ruta = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM agua_potable.f_seleccionar_ruta_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                ruta = new Ruta();
                ruta.setInt_id_sector(resultSet.getInt("sr_id_ruta"));
                /*/ruta.setInt_id_sector(codigo)*int_id_sector---------------crear la funcion en SFSector para buscar un ID de sector*/
                ruta.setChv_nombre_ruta(resultSet.getString("chv_nombre_ruta"));
                ruta.setInt_numero_ruta(resultSet.getInt("int_numero_ruta"));
                ruta.setTs_fecha_digitacion_ruta(resultSet.getTimestamp("ts_fecha_digitacion_ruta"));
                ruta.setTs_fecha_verificar_ruta(resultSet.getTimestamp("ts_fecha_verificar_ruta"));
                ruta.setTs_fecha_validar_ruta(resultSet.getTimestamp("ts_fecha_validar_ruta"));
                ruta.setBl_imprimir_ruta(resultSet.getBoolean("bl_imprimir_ruta"));
                ruta.setBl_lectura_ruta(resultSet.getBoolean("bl_lectura_ruta"));
                ruta.setCh_estado_logico(resultSet.getString("ch_estado_logico"));
                ruta.setTs_fecha_registro(resultSet.getTimestamp("ts_fecha_registro"));
                ruta.setTs_fecha_actualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                ruta.setTs_fecha_baja(resultSet.getTimestamp("ts_fecha_baja"));
                ruta.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return ruta;
    }
    
    
    
    
    
}
