
package aguapotable.logica.funciones;

import accesoDatos.AccesoDatos;
import aguapotable.logica.entidades.Ciclo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Andres
 */
public class FCiclo {
    
    public static List<Ciclo> obtenerCiclos() throws Exception {
        List<Ciclo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Ciclo ciclo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from agua_potable.f_seleccionar_ciclos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                ciclo = new Ciclo();
                ciclo.setSr_id_ciclo(resultSet.getInt("sr_id_ciclo"));
                ciclo.setChv_nombre(resultSet.getString("chv_nombre"));
                ciclo.setCh_estado_logico(resultSet.getString("ch_estado_logico"));
                ciclo.setTs_fecha_registro(resultSet.getTimestamp("ts_fecha_registro"));
                ciclo.setTs_fecha_actualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                ciclo.setTs_fecha_baja(resultSet.getTimestamp("ts_fecha_baja"));
                ciclo.setId_usuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_usuario")));
                ciclo.setInt_num_ciclo(resultSet.getInt("int_num_ciclo"));
                ciclo.setInt_mes_ciclo(resultSet.getInt("int_mes_ciclo"));
                ciclo.setInt_anio_ciclo(resultSet.getInt("int_anio_ciclo"));
                ciclo.setInt_paso_ciclo(resultSet.getInt("int_paso_ciclo"));
                ciclo.setTs_fecha_facturacion(resultSet.getTimestamp("ts_fecha_facturacion"));
                ciclo.setTs_fecha_digitacion(resultSet.getTimestamp("ts_fecha_digitacion"));
                ciclo.setTs_fecha_validacion(resultSet.getTimestamp("ts_fecha_validacion"));
                ciclo.setTs_fecha_actualiza_datos(resultSet.getTimestamp("ts_fecha_actualiza_datos"));
                ciclo.setTs_fecha_fin_prefacturacion(resultSet.getTimestamp("ts_fecha_fin_prefacturacion"));
                ciclo.setTs_fecha_validar_promedios(resultSet.getTimestamp("ts_fecha_validar_promedios"));
                ciclo.setTs_fecha_validar_esferas(resultSet.getTimestamp("ts_fecha_validar_esferas"));
                ciclo.setTs_fecha_validar_negativos(resultSet.getTimestamp("ts_fecha_validar_negativos"));
                ciclo.setTs_fecha_validar_puerta_cerrada(resultSet.getTimestamp("ts_fecha_validar_puerta_cerrada"));
                lst.add(ciclo);
            }
        }catch (Exception e) {
            throw e;
        }
            
      
        return lst;
    }
}
