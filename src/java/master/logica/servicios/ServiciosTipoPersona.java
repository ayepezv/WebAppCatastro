package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.entidades.TipoPersona;

public class ServiciosTipoPersona {

    public static List<TipoPersona> obtenerTiposPersona() throws Exception {
        List<TipoPersona> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        TipoPersona tipo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from master.f_obtener_tipos_personas();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                tipo = new TipoPersona();
                tipo.setIdTipoPersona(resultSet.getInt("sr_id_tipo"));
                tipo.setTipoPersona(resultSet.getString("chv_tipo_persona"));
                tipo.setDescripcion(resultSet.getString("chv_descripcion"));
                tipo.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tipo.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipo.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                tipo.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(tipo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
