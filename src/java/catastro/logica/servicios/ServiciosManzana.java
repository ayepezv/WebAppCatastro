/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Manzana;
import catastro.logica.entidades.Sector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class ServiciosManzana {

    public static ArrayList<Manzana> obtenerManzanasDadoSector(int sector) throws Exception {
        ArrayList<Manzana> lst = new ArrayList<Manzana>();
        Manzana manzana;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_selecionar_manzana_dado_sector(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, sector);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                manzana = new Manzana();
                manzana.setSector(new Sector());
                manzana.setSector(ServiciosSector.obtenerSectorDadoCodigo(resultSet.getInt("int_id_sector")));
                manzana.setIdManzana(resultSet.getInt("sr_id_manzana"));
                manzana.setNombre(resultSet.getString("chv_nombre"));
                manzana.setNumManzana(resultSet.getString("chv_numero_manza"));
                manzana.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                manzana.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                manzana.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                manzana.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(manzana);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Manzana obtenerManzanaDadoCodigo(int codigo) throws Exception {
        Manzana manzana = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_selecionar_manzana_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                manzana = new Manzana();
                manzana.setSector(new Sector());
                manzana.setSector(ServiciosSector.obtenerSectorDadoCodigo(resultSet.getInt("int_id_sector")));
                manzana.setIdManzana(resultSet.getInt("sr_id_manzana"));
                manzana.setNombre(resultSet.getString("chv_nombre"));
                manzana.setNumManzana(resultSet.getString("chv_numero_manza"));
                manzana.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                manzana.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                manzana.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                manzana.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return manzana;
    }

}
