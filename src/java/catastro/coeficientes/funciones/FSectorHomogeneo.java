package catastro.coeficientes.funciones;

import accesoDatos.AccesoDatos;
import catastro.coeficientes.entidades.SectorHomogeneo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FSectorHomogeneo {

    public static List<SectorHomogeneo> obtenerSectoresHomogeneos() throws Exception {
        List<SectorHomogeneo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        SectorHomogeneo sectores;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_factores_homogeneos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                sectores = new SectorHomogeneo();
                sectores.setIdSector(resultSet.getInt("sr_id_sector"));
                sectores.setSector(resultSet.getString("chv_sector"));
                sectores.setDescripcion(resultSet.getString("chv_descripcion"));
                sectores.setValorMetro(resultSet.getDouble("db_valor_metro"));
                sectores.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                sectores.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                sectores.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                sectores.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                sectores.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                lst.add(sectores);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static SectorHomogeneo obtenerSectorHomogeneoDadoCodigo(int codigo) throws Exception {
        SectorHomogeneo sectores = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_factor_homogeneo_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                sectores = new SectorHomogeneo();
                sectores.setIdSector(resultSet.getInt("sr_id_sector"));
                sectores.setSector(resultSet.getString("chv_sector"));
                sectores.setDescripcion(resultSet.getString("chv_descripcion"));
                sectores.setValorMetro(resultSet.getDouble("db_valor_metro"));
                sectores.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                sectores.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                sectores.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                sectores.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                sectores.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return sectores;
    }
}
