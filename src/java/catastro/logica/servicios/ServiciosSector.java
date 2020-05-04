package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Sector;
import catastro.logica.entidades.Zona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiciosSector {

    public static ArrayList<Sector> obtenerSectoresDadoZona(int zona) throws Exception {
        ArrayList<Sector> lst = new ArrayList<Sector>();
        Sector sector;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_seleccionar_sector_dado_zona(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                sector = new Sector();
                sector.setZona(new Zona());
                sector.setZona(ServiciosZona.obtenerZonaDadoCodigo(resultSet.getInt("int_id_zona")));
                sector.setIdSector(resultSet.getInt("sr_id_sector"));
                sector.setNombre(resultSet.getString("chv_nombre"));
                sector.setCodigo(resultSet.getString("chv_codigo"));
                sector.setDescripcion(resultSet.getString("chv_descripcion"));
                sector.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                sector.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                sector.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                sector.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(sector);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Sector obtenerSectorDadoCodigo(int codigo) throws Exception {
        Sector sector = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_sector_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                sector = new Sector();
                sector.setZona(new Zona());
                sector.setZona(ServiciosZona.obtenerZonaDadoCodigo(resultSet.getInt("int_id_zona")));
                sector.setIdSector(resultSet.getInt("sr_id_sector"));
                sector.setNombre(resultSet.getString("chv_nombre"));
                sector.setCodigo(resultSet.getString("chv_codigo"));
                sector.setDescripcion(resultSet.getString("chv_descripcion"));
                sector.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                sector.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                sector.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                sector.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return sector;
    }
}
