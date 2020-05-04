/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.DetalleUsoSuelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Geovanny Cudco
 */
public class FDetalleUsoSuelo {
    
    public static DetalleUsoSuelo obtenerDetalleDadoCodigo(int codigo) throws Exception {
        DetalleUsoSuelo detalle = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_detalle_uso_suelo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleUsoSuelo();
                detalle.setIdDetalle(resultSet.getInt("sr_id_detalle"));
                detalle.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
                
                detalle.setAreaMapInfo(resultSet.getDouble("db_area_map_info"));
                detalle.setAreaParcialMapInfo(resultSet.getDouble("db_area_parcial_map_info"));
                
                detalle.setValorMinimo(resultSet.getDouble("db_valor_minimo"));
                detalle.setFrente1(resultSet.getDouble("db_frente_1"));
                detalle.setFondoRelativo(resultSet.getDouble("db_fondo_relativo"));
                detalle.setFrenteFondo(resultSet.getDouble("db_frente_fondo"));
                
                detalle.setSumaAlicuota(resultSet.getDouble("db_suma_alicuota"));
                detalle.setFactMatVial(resultSet.getDouble("db_fact_mat_vial"));
                detalle.setFactAguaPot(resultSet.getDouble("db_fact_agua_pot"));
                detalle.setFactEnergElect(resultSet.getDouble("db_fact_energ_elect"));
                detalle.setFactAlcant(resultSet.getDouble("db_fact_alcant"));
                detalle.setFactAceras(resultSet.getDouble("db_fact_aceras"));
                detalle.setFactBordillos(resultSet.getDouble("db_fact_bordillos"));
                detalle.setFactLocalizacion(resultSet.getDouble("db_fact_localizacion"));
                detalle.setFactTopografia(resultSet.getDouble("db_fact_topografia"));
                detalle.setValorMetro(resultSet.getDouble("db_valor_metro"));
                detalle.setValorDepresMetro(resultSet.getDouble("db_valor_depres_metro"));
                
                detalle.setProcesada(resultSet.getString("ch_procesada"));
                detalle.setFactconstruccion(resultSet.getDouble("db_fact_construccion"));
                detalle.setTotal1(resultSet.getDouble("db_total_1"));
                detalle.setTotal2(resultSet.getDouble("db_total_2"));
                
                detalle.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                detalle.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                detalle.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                detalle.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                detalle.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return detalle;
    }
}
