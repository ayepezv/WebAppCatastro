/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.PredioExcencion;
import catastro.logica.entidades.PredioExcencionTer;
import catastro.logica.entidades.PredioExcencionTerCumplir;
import catastro.logica.entidades.PredioExcencionTerACumplir;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geovanny
 */
public class FPredioExcencion {

    public static List<PredioExcencion> obtenerExcenciones() throws Exception {
        List<PredioExcencion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PredioExcencion excencion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_excenciones_predios();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                excencion = new PredioExcencion();
                excencion.getPredio().setIdPredio(resultSet.getInt("id_predio"));
                excencion.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                excencion.getExcencion().setIdExccencion(resultSet.getInt("sr_id_excencion"));
                excencion.getExcencion().setExcencion(resultSet.getString("chv_excencion"));
                excencion.getExcencion().setDescripcion(resultSet.getString("chv_descripcion"));
                excencion.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                excencion.setFechaFin(resultSet.getDate("dt_fecha_fin"));
                excencion.setPorcentaje(resultSet.getDouble("db_porcentaje"));
                excencion.setObservaciones(resultSet.getString("chv_observaciones"));
                excencion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                excencion.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                excencion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                excencion.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                excencion.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                excencion.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                excencion.getPredio().getPropietario().setCedula(resultSet.getString("chv_cedula"));
                excencion.setAvaluoPredio(resultSet.getDouble("db_avaluo_predio"));
                excencion.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                excencion.setDsctoExcencion(resultSet.getDouble("db_descuento_excencion"));
                excencion.setCapitalBiess(resultSet.getDouble("db_capital_biess"));
                excencion.getPredio().getPropietario().setEdad(resultSet.getInt("edad_exacta"));
                lst.add(excencion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<PredioExcencionTer> obtenerExcencionesTer() throws Exception {
        List<PredioExcencionTer> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PredioExcencionTer excencionTer;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_listar_predios_valor_persona_excenciones(1);";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                excencionTer = new PredioExcencionTer();
                excencionTer.setId_titular(resultSet.getInt("id_titular"));
                excencionTer.setNum_predios(resultSet.getInt("num_predios"));
                excencionTer.setSuma_valor(resultSet.getDouble("suma_valor"));
                excencionTer.setSuma_construc(resultSet.getDouble("suma_construc"));
                excencionTer.setSuma_suelo(resultSet.getDouble("suma_suelo"));
                excencionTer.setNombres(resultSet.getString("nombres"));
                excencionTer.setApellidos(resultSet.getString("apellidos"));
                excencionTer.setCedula(resultSet.getString("cedula"));
                excencionTer.setChv_ruc(resultSet.getString("chv_ruc"));
                excencionTer.setSr_id_excencion(resultSet.getInt("sr_id_excencion"));
                excencionTer.setChv_excencion(resultSet.getString("chv_excencion"));
                excencionTer.setEdad(resultSet.getInt("edad"));
                excencionTer.setNacimiento(resultSet.getTimestamp("nacimiento"));
                lst.add(excencionTer);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<PredioExcencionTerCumplir> obtenerExcencionesTerCumplir() throws Exception {
        List<PredioExcencionTerCumplir> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PredioExcencionTerCumplir excencionTerCumplir;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_listar_valor_persona_tercera_edad_supera_500(1);";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                excencionTerCumplir = new PredioExcencionTerCumplir();
                excencionTerCumplir.setId_titular(resultSet.getInt("int_id_titular_dom"));
                excencionTerCumplir.setNum_predios(resultSet.getInt("num_predios"));
                excencionTerCumplir.setSuma_valor(resultSet.getDouble("suma_valor"));
                excencionTerCumplir.setSuma_excencion(resultSet.getDouble("suma_excencion"));
                excencionTerCumplir.setNombres(resultSet.getString("chv_nombres"));
                excencionTerCumplir.setApellidos(resultSet.getString("chv_apellidos"));
                excencionTerCumplir.setCedula(resultSet.getString("chv_cedula"));
                excencionTerCumplir.setRuc(resultSet.getString("chv_ruc"));
                excencionTerCumplir.setEdad(resultSet.getInt("edad"));
                excencionTerCumplir.setNacimiento(resultSet.getTimestamp("ts_fecha_nacimiento"));
                excencionTerCumplir.setExcencion(resultSet.getString("chv_excencion"));
                lst.add(excencionTerCumplir);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    /*EJECUTA DE LA BASE DE DATOS LA FUNCION PARA OBTENER ABONADOS YENDO A CUMPLIR TERCERA EDAD Y SIN TENER EXCENCION TERCERA EDAD*/
    public static List<PredioExcencionTerACumplir> obtenerExcencionesTerACumplir() throws Exception {
        List<PredioExcencionTerACumplir> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PredioExcencionTerACumplir excencionTerACumplir;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_listar_persona_a_cumplir_tercera_edad_sin_excencion(1);";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                excencionTerACumplir = new PredioExcencionTerACumplir();
                excencionTerACumplir.setId_titular(resultSet.getInt("id_persona"));
                excencionTerACumplir.setApellidos(resultSet.getString("apellidos"));
                excencionTerACumplir.setNombres(resultSet.getString("nombres"));
                excencionTerACumplir.setNacimiento(resultSet.getTimestamp("fecha_nacimiento"));
                excencionTerACumplir.setRuc(resultSet.getString("ruc"));
                excencionTerACumplir.setEdad(resultSet.getInt("edad"));
                excencionTerACumplir.setEdadCompleta(resultSet.getString("edadcompleta"));
                excencionTerACumplir.setId_predio(resultSet.getInt("id_predio"));
                excencionTerACumplir.setClave_catastral(resultSet.getString("clave_catastral"));
                excencionTerACumplir.setDireccion(resultSet.getString("direccion"));
                excencionTerACumplir.setAvaluo(resultSet.getDouble("avaluo"));
                excencionTerACumplir.setCelular(resultSet.getString("celular"));
                excencionTerACumplir.setPorcentaje_excencion(resultSet.getDouble("porcentaje_excencion"));
                excencionTerACumplir.setExcencion(resultSet.getString("excencion"));
                excencionTerACumplir.setObservacion(resultSet.getString("observacion"));
                lst.add(excencionTerACumplir);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<PredioExcencionTer> obtenerExcencionesTotal() throws Exception {
        List<PredioExcencionTer> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PredioExcencionTer PredioExcencionTer;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_listar_predios_valor_persona_excenciones_total(1);";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                PredioExcencionTer = new PredioExcencionTer();
                PredioExcencionTer.setId_titular(resultSet.getInt("id_titular"));
                PredioExcencionTer.setNum_predios(resultSet.getInt("num_predios"));
                PredioExcencionTer.setSuma_valor(resultSet.getDouble("suma_valor"));
                PredioExcencionTer.setSuma_construc(resultSet.getDouble("suma_construc"));
                PredioExcencionTer.setSuma_suelo(resultSet.getDouble("suma_suelo"));
                PredioExcencionTer.setNombres(resultSet.getString("nombres"));
                PredioExcencionTer.setApellidos(resultSet.getString("apellidos"));
                PredioExcencionTer.setCedula(resultSet.getString("cedula"));
                PredioExcencionTer.setChv_ruc(resultSet.getString("chv_ruc"));
                PredioExcencionTer.setSr_id_excencion(resultSet.getInt("sr_id_excencion"));
                PredioExcencionTer.setChv_excencion(resultSet.getString("chv_excencion"));
                PredioExcencionTer.setEdad(resultSet.getInt("edad"));
                PredioExcencionTer.setNacimiento(resultSet.getTimestamp("nacimiento"));
                PredioExcencionTer.setDb_porcentaje(resultSet.getDouble("db_porcentaje"));
                PredioExcencionTer.setId_tipo_persona(resultSet.getInt("id_tipo_persona"));
                lst.add(PredioExcencionTer);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarExcencion(PredioExcencion excencion) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_asignar_excencion_predio(?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, excencion.getPredio().getIdPredio());
            prstm.setInt(2, excencion.getExcencion().getIdExccencion());
            prstm.setDate(3, new java.sql.Date(excencion.getFechaInicio().getTime()));
            prstm.setDate(4, new java.sql.Date(excencion.getFechaFin().getTime()));
            prstm.setDouble(5, excencion.getPorcentaje());
            prstm.setInt(6, excencion.getSessionUsuario().getIdPersona());
            prstm.setString(7, excencion.getObservaciones());
            prstm.setDouble(8, excencion.getCapitalBiess());
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

    public static String editarExcencion(PredioExcencion excencion) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_editar_excencion_predio(?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, excencion.getPredio().getIdPredio());
            prstm.setInt(2, excencion.getExcencion().getIdExccencion());
            prstm.setDate(3, new java.sql.Date(excencion.getFechaInicio().getTime()));
            prstm.setDate(4, new java.sql.Date(excencion.getFechaFin().getTime()));
            prstm.setDouble(5, excencion.getPorcentaje());
            prstm.setInt(6, excencion.getSessionUsuario().getIdPersona());
            prstm.setString(7, excencion.getObservaciones());
            prstm.setDouble(8, excencion.getCapitalBiess());
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

    public static String eliminarExcencion(PredioExcencion excencion) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_excencion_especial(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, excencion.getPredio().getIdPredio());
            prstm.setInt(2, excencion.getExcencion().getIdExccencion());
            prstm.setInt(3, excencion.getSessionUsuario().getIdPersona());
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

    public static List<PredioExcencion> obtenerExcecnionesDadoFecha(Date fechaIni, Date fechaFin) throws Exception {
        List<PredioExcencion> lst = new ArrayList<>();
        PredioExcencion excencion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_excenciones_predios_rango_fechas(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, fechaIni);
            prstm.setDate(2, fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                excencion = new PredioExcencion();
                excencion.getPredio().setIdPredio(resultSet.getInt("id_predio"));
                excencion.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                excencion.getExcencion().setIdExccencion(resultSet.getInt("sr_id_excencion"));
                excencion.getExcencion().setExcencion(resultSet.getString("chv_excencion"));
                excencion.getExcencion().setDescripcion(resultSet.getString("chv_descripcion"));
                excencion.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                excencion.setFechaFin(resultSet.getDate("dt_fecha_fin"));
                excencion.setPorcentaje(resultSet.getDouble("db_porcentaje"));
                excencion.setObservaciones(resultSet.getString("chv_observaciones"));
                excencion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                excencion.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                excencion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                excencion.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                excencion.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                excencion.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                excencion.getPredio().getPropietario().setCedula(resultSet.getString("chv_cedula"));
                excencion.setAvaluoPredio(resultSet.getDouble("db_avaluo_predio"));
                excencion.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                excencion.setDsctoExcencion(resultSet.getDouble("db_descuento_excencion"));
                lst.add(excencion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<PredioExcencion> obtenerExcecnionesDadoCriterio(String criterio) throws Exception {
        List<PredioExcencion> lst = new ArrayList<>();
        PredioExcencion excencion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_excenciones_predios_dado_criterio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, criterio);

            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                excencion = new PredioExcencion();
                excencion.getPredio().setIdPredio(resultSet.getInt("id_predio"));
                excencion.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                excencion.getExcencion().setIdExccencion(resultSet.getInt("sr_id_excencion"));
                excencion.getExcencion().setExcencion(resultSet.getString("chv_excencion"));
                excencion.getExcencion().setDescripcion(resultSet.getString("chv_descripcion"));
                excencion.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                excencion.setFechaFin(resultSet.getDate("dt_fecha_fin"));
                excencion.setPorcentaje(resultSet.getDouble("db_porcentaje"));
                excencion.setObservaciones(resultSet.getString("chv_observaciones"));
                excencion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                excencion.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                excencion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                excencion.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                excencion.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                excencion.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                excencion.getPredio().getPropietario().setCedula(resultSet.getString("chv_ruc"));
                excencion.setAvaluoPredio(resultSet.getDouble("db_avaluo_predio"));
                excencion.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                excencion.setDsctoExcencion(resultSet.getDouble("db_descuento_excencion"));
                excencion.setCapitalBiess(resultSet.getDouble("db_capital_biess"));
                excencion.getPredio().getPropietario().setEdad(resultSet.getInt("edad_exacta"));
                lst.add(excencion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<PredioExcencion> obtenerExcecnionesDadoZonaSectorManzanaPredio(int zona, int sector, int manzana, int predio) throws Exception {
        List<PredioExcencion> lst = new ArrayList<>();
        PredioExcencion excencion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_excenciones_dado_zona_sector_manzana_predio(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, sector);
            prstm.setInt(3, manzana);
            prstm.setInt(4, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                excencion = new PredioExcencion();
                excencion.getPredio().setIdPredio(resultSet.getInt("id_predio"));
                excencion.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                excencion.getExcencion().setIdExccencion(resultSet.getInt("sr_id_excencion"));
                excencion.getExcencion().setExcencion(resultSet.getString("chv_excencion"));
                excencion.getExcencion().setDescripcion(resultSet.getString("chv_descripcion"));
                excencion.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                excencion.setFechaFin(resultSet.getDate("dt_fecha_fin"));
                excencion.setPorcentaje(resultSet.getDouble("db_porcentaje"));
                excencion.setObservaciones(resultSet.getString("chv_observaciones"));
                excencion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                excencion.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                excencion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                excencion.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                excencion.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                excencion.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                excencion.getPredio().getPropietario().setCedula(resultSet.getString("chv_ruc"));
                excencion.setAvaluoPredio(resultSet.getDouble("db_avaluo_predio"));
                excencion.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                excencion.setDsctoExcencion(resultSet.getDouble("db_descuento_excencion"));
                excencion.setCapitalBiess(resultSet.getDouble("db_capital_biess"));
                excencion.getPredio().getPropietario().setEdad(resultSet.getInt("edad_exacta"));
                lst.add(excencion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    ///REPORTE ESTADISTICO DE LAS EXCENCIONES APLICADAS
    public static List<PredioExcencion> reporteDescuentosExcenciones(Date fechaIni, Date fechaFin) throws Exception {
        List<PredioExcencion> lst = new ArrayList<>();
        PredioExcencion excencion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_reporte_dsctos_excenc_aplicadas(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, fechaIni);
            prstm.setDate(2, fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                excencion = new PredioExcencion();
                excencion.getExcencion().setExcencion(resultSet.getString("excencion"));
                excencion.setDsctoExcencion(resultSet.getDouble("suma"));
                lst.add(excencion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    ///REPORTE ESTADISTICO DE LAS EXCENCIONES APLICADAS y TOTAL APLICADAS
    public static List<PredioExcencion> reporteTotalDescuentosExcenciones(Date fechaIni, Date fechaFin) throws Exception {
        List<PredioExcencion> lst = new ArrayList<>();
        PredioExcencion excencion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_reporte_dsctos_excenc_aplicadas_total(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, fechaIni);
            prstm.setDate(2, fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                excencion = new PredioExcencion();
                excencion.getExcencion().setExcencion(resultSet.getString("excencion"));
                excencion.setDsctoExcencion(resultSet.getDouble("suma"));
                excencion.setPorcentaje(resultSet.getDouble("total_aplicadas"));
                lst.add(excencion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
