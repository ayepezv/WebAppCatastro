
package aguapotable.logica.funciones;

import accesoDatos.AccesoDatos;
import aguapotable.logica.entidades.Categoria;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FCategoria {
    
    
    public static List<Categoria> obtenerCategoria() throws Exception {
        List<Categoria> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Categoria categoria;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from agua_potable.f_seleccionar_categoria();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                categoria = new Categoria();
                categoria.setSr_int_categoria(resultSet.getInt("sr_id_categoria"));
                categoria.setChv_descripcion_categoria(resultSet.getString("chv_descripcion_categoria"));
                categoria.setDb_multa_categoria(resultSet.getDouble("db_multa_categoria"));
                categoria.setCh_estado_logico(resultSet.getString("ch_estado_logico"));
                categoria.setTs_fecha_registro(resultSet.getTimestamp("ts_fecha_registro"));
                categoria.setTs_fecha_actualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                categoria.setTs_fecha_baja(resultSet.getTimestamp("ts_fecha_baja"));
                categoria.setSession_usuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                lst.add(categoria);
            }
        }catch (Exception e) {
            throw e;
               
        }
        return lst;
    }
    
}
