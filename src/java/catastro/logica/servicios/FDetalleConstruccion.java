/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.DetalleValorConstruccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Geovanny Cudco
 */
public class FDetalleConstruccion {

    public static DetalleValorConstruccion obtenerDetalleDadoCodigo(int codigo) throws Exception {
        DetalleValorConstruccion detalle = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_parametros_construccion(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleValorConstruccion();
                detalle.setIdValConstruccion(resultSet.getInt("sr_id_val_construccion"));
                //detalle.getIdPiso().setIdPiso(resultSet.getInt("int_id_piso"));
                detalle.setIdPiso(FuncionesPiso.obtenerPisoDadoId(resultSet.getInt("int_id_piso")));
                detalle.setAnioConstruc(resultSet.getInt("int_anio_construc"));
                detalle.setAreaConstrucc(resultSet.getDouble("db_area_construcc"));
                detalle.setEstadoPiso(resultSet.getDouble("db_estado_piso"));
                detalle.setEstructCimientos(resultSet.getDouble("db_estruct_cimientos"));
                detalle.setEstructColumnas(resultSet.getDouble("db_estruct_columnas"));
                detalle.setEstructVigas(resultSet.getDouble("db_estruct_vigas"));
                detalle.setEstructEntrepisos(resultSet.getDouble("db_estruct_entrepisos"));
                detalle.setEstructParedes(resultSet.getDouble("db_estruct_paredes"));
                detalle.setEstructCubierta(resultSet.getDouble("db_estruct_cubierta"));
                detalle.setEstructEscaleras(resultSet.getDouble("db_estruct_escaleras"));
                detalle.setAcabPisos(resultSet.getDouble("db_acab_pisos"));
                detalle.setAcabPuertas(resultSet.getDouble("db_acab_puertas"));
                detalle.setAcabVentanas(resultSet.getDouble("db_acab_ventanas"));
                detalle.setAcabCubrevent(resultSet.getDouble("db_acab_cubrevent"));
                detalle.setAcabTumbados(resultSet.getDouble("db_acab_tumbados"));
                detalle.setAcabCubierta(resultSet.getDouble("db_acab_cubierta"));
                detalle.setAcabBanios(resultSet.getDouble("db_acab_banios"));
                detalle.setAcabCocina(resultSet.getDouble("db_acab_cocina"));
                detalle.setAcabClosets(resultSet.getDouble("db_acab_closets"));
                detalle.setAcabRevInterior(resultSet.getDouble("db_acab_rev_interior"));
                detalle.setAcabRevExt(resultSet.getDouble("db_acab_rev_ext"));
                detalle.setFde(resultSet.getDouble("db_fde"));
                detalle.setTotal1(resultSet.getDouble("db_total_1"));
                detalle.setTotal2(resultSet.getDouble("db_total_2"));
                detalle.setTotal3(resultSet.getDouble("db_total_3"));
                detalle.setEstructCadenas(resultSet.getDouble("db_estruct_cadenas"));
                detalle.setAcabPuertasInterior(resultSet.getDouble("db_acab_puertas_interior"));
                detalle.setAcabVidrios(resultSet.getDouble("db_acab_vidrios"));
                detalle.setAcabEnlucidos(resultSet.getDouble("db_acab_enlucidos"));
                detalle.setAcabEnergiaElectrica(resultSet.getDouble("db_acab_energia_electrica"));
                detalle.setAcabSanitarias(resultSet.getDouble("db_acab_sanitarias"));
                detalle.setAcabEspeciales(resultSet.getDouble("db_acab_especiales"));
                detalle.setAcabContraIncendio(resultSet.getDouble("db_acab_contra_incendio"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return detalle;
    }

    public static DetalleValorConstruccion obtenerDetalleDadoCodigoV2(int codigo) throws Exception {
        DetalleValorConstruccion detalle = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_piso_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleValorConstruccion();
                detalle.setIdValConstruccion(resultSet.getInt("sr_id_val_construccion"));
//detalle.getIdPiso().setIdPiso(resultSet.getInt("int_id_piso"));
                detalle.setIdPiso(FuncionesPiso.obtenerPisoDadoId(resultSet.getInt("int_id_piso")));
                detalle.setAnioConstruc(resultSet.getInt("int_anio_construc"));
                detalle.setAreaConstrucc(resultSet.getDouble("db_area_construcc"));
                detalle.setEstadoPiso(resultSet.getDouble("db_estado_piso"));
                detalle.setEstructCimientos(resultSet.getDouble("db_estruct_cimientos"));
                detalle.setEstructColumnas(resultSet.getDouble("db_estruct_columnas"));
                detalle.setEstructVigas(resultSet.getDouble("db_estruct_vigas"));
                detalle.setEstructEntrepisos(resultSet.getDouble("db_estruct_entrepisos"));
                detalle.setEstructParedes(resultSet.getDouble("db_estruct_paredes"));
                detalle.setEstructCubierta(resultSet.getDouble("db_estruct_cubierta"));
                detalle.setEstructEscaleras(resultSet.getDouble("db_estruct_escaleras"));
                detalle.setAcabPisos(resultSet.getDouble("db_acab_pisos"));
                detalle.setAcabPuertas(resultSet.getDouble("db_acab_puertas"));
                detalle.setAcabVentanas(resultSet.getDouble("db_acab_ventanas"));
                detalle.setAcabCubrevent(resultSet.getDouble("db_acab_cubrevent"));
                detalle.setAcabTumbados(resultSet.getDouble("db_acab_tumbados"));
                detalle.setAcabCubierta(resultSet.getDouble("db_acab_cubierta"));
                detalle.setAcabBanios(resultSet.getDouble("db_acab_banios"));
                detalle.setAcabCocina(resultSet.getDouble("db_acab_cocina"));
                detalle.setAcabClosets(resultSet.getDouble("db_acab_closets"));
                detalle.setAcabRevInterior(resultSet.getDouble("db_acab_rev_interior"));
                detalle.setAcabRevExt(resultSet.getDouble("db_acab_rev_ext"));
                detalle.setFde(resultSet.getDouble("db_fde"));
                detalle.setTotal1(resultSet.getDouble("db_total_1"));
                detalle.setTotal2(resultSet.getDouble("db_total_2"));
                detalle.setTotal3(resultSet.getDouble("db_total_3"));
                detalle.setEstructCadenas(resultSet.getDouble("db_estruct_cadenas"));
                detalle.setAcabPuertasInterior(resultSet.getDouble("db_acab_puertas_interior"));
                detalle.setAcabVidrios(resultSet.getDouble("db_acab_vidrios"));
                detalle.setAcabEnlucidos(resultSet.getDouble("db_acab_enlucidos"));
                detalle.setAcabEnergiaElectrica(resultSet.getDouble("db_acab_energia_electrica"));
                detalle.setAcabSanitarias(resultSet.getDouble("db_acab_sanitarias"));
                detalle.setAcabEspeciales(resultSet.getDouble("db_acab_especiales"));
                detalle.setAcabContraIncendio(resultSet.getDouble("db_acab_contra_incendio"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return detalle;
    }
}
