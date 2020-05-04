/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Parroquia;
import catastro.logica.entidades.Predio;
import catastro.logica.entidades.Prestamo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.servicios.ServiciosFuncion;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Geovanny
 */
public class ServiciosPredio {

    public static String registrarPredio(Predio predio) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_predio(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, predio.getParroquia().getIdParroquia());
            prstm.setString(2, predio.getDivPolitica());
            prstm.setInt(3, predio.getManzana().getIdManzana());
            prstm.setInt(4, predio.getNumPredio());
            prstm.setInt(5, predio.getTipoLote().getIdTipoLote());
            prstm.setString(6, predio.getClaveCatastral());
            prstm.setString(7, predio.getClaveCatAnterior());
            prstm.setInt(8, predio.getBarrio().getIdBarrio());
            prstm.setInt(9, predio.getCallePrincipal().getIdCalle());
            prstm.setInt(10, predio.getCalleSecundaria().getIdCalle());
            prstm.setString(11, predio.getNombreEdificio());
            prstm.setInt(12, predio.getTipoPersoneria().getIdTipoPersoneria());
            prstm.setInt(13, predio.getPropietario().getIdPersona());
            prstm.setInt(14, predio.getExcenEspecial().getIdExccencion());
            prstm.setInt(15, predio.getFormaAdq().getIdFormaAdq());
            prstm.setDate(16, new java.sql.Date(predio.getFechaEscrituracion().getTime()));
            prstm.setDate(17, new java.sql.Date(predio.getFechaRegistroPredio().getTime()));
            prstm.setDouble(18, predio.getCuantia());
            prstm.setInt(19, predio.getPrestamo().getIdPrestamo());
            prstm.setString(20, predio.getCondPedio());
            prstm.setString(21, predio.getPatio());
            prstm.setString(22, predio.getCerramiento());
            prstm.setString(23, predio.getMuros());
            prstm.setString(24, predio.getPiscina());
            prstm.setString(25, predio.getCisterna());
            prstm.setInt(26, predio.getNumBanios());
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

    public static ArrayList<Predio> obtenerPredios() throws Exception {
        ArrayList<Predio> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Predio predio;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_predios();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                predio = new Predio();
                predio.setIdPredio(resultSet.getInt("sr_id_predio"));
                predio.setParroquia(new Parroquia());
                predio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(resultSet.getInt("int_id_parroquia")));
                predio.setDivPolitica(resultSet.getString("chv_division_politica"));
                predio.setManzana(ServiciosManzana.obtenerManzanaDadoCodigo(resultSet.getInt("int_id_manzana")));
                predio.setNumPredio(resultSet.getInt("int_numero_predio"));
                predio.setTipoLote(ServiciosTipoLote.obtenerTipoLoteDadoCodigo(resultSet.getInt("int_tipo_lote")));
                predio.setPropHorizontal(resultSet.getDouble("db_prop_horizontal"));
                predio.setDerechosAcciones(resultSet.getDouble("db_derechos_acciones"));
                predio.setClaveCatastral(resultSet.getString("txt_clave_catastral"));
                predio.setClaveCatAnterior(resultSet.getString("txt_clave_cat_anterior"));
                predio.setBarrio(ServiciosBarrios.obtenerSectorDadoCodigo(resultSet.getInt("int_id_barrio")));
                predio.setCallePrincipal(ServiciosCalles.obtenerCalleDadoCodigo(resultSet.getInt("int_calle_principal")));
                predio.setCalleSecundaria(ServiciosCalles.obtenerCalleDadoCodigo(resultSet.getInt("int_calle_secundaria")));
                predio.setNombreEdificio(resultSet.getString("chv_nombre_edificio"));
                predio.setTipoPersoneria(ServiciosTipoPersoneria.obtenerTipoPersoneriaDadoCodigo(resultSet.getInt("int_tipo_personeria")));
                predio.setPropietario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_propietario")));
                predio.setExcenEspecial(ServiciosExenciones.obtenerExencionDadoCodigo(resultSet.getInt("int_id_tipo_excencion")));
                predio.setFormaAdq(ServFormaAdquisicion.obtenerFormaAdqDadoCodigo(resultSet.getInt("int_id_forma_adq")));
                predio.setFechaEscrituracion(resultSet.getDate("dt_fecha_escrituracion"));
                predio.setFechaRegistroPredio(resultSet.getDate("dt_fecha_registro"));
                predio.setCuantia(resultSet.getDouble("db_cuantia"));
                predio.getPrestamo().setIdPrestamo(resultSet.getInt("int_id_prestamo"));
                predio.setCondPedio(resultSet.getString("chv_condicion_perdio"));
                predio.setPatio(resultSet.getString("chv_patio"));
                predio.setCerramiento(resultSet.getString("chv_cerramiento"));
                predio.setMuros(resultSet.getString("chv_muros"));
                predio.setPiscina(resultSet.getString("chv_piscina"));
                predio.setCisterna(resultSet.getString("chv_cisterna"));
                predio.setNumBanios(resultSet.getInt("int_numero_banios"));
                predio.setPathCroquis(resultSet.getString("chv_path_croquis"));
                predio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                predio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro_predio"));
                predio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                predio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(predio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Predio> encontrarPredios(String parametro) throws Exception {
        ArrayList<Predio> lst = new ArrayList<>();
        Predio predio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_find_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, parametro);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                predio = new Predio();
                predio.setIdPredio(resultSet.getInt("sr_id_predio"));
                predio.setParroquia(new Parroquia());
                predio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(resultSet.getInt("int_id_parroquia")));
                predio.setDivPolitica(resultSet.getString("chv_division_politica"));
                predio.setManzana(ServiciosManzana.obtenerManzanaDadoCodigo(resultSet.getInt("int_id_manzana")));
                predio.setNumPredio(resultSet.getInt("int_numero_predio"));
                predio.setTipoLote(ServiciosTipoLote.obtenerTipoLoteDadoCodigo(resultSet.getInt("int_tipo_lote")));
                predio.setPropHorizontal(resultSet.getDouble("db_prop_horizontal"));
                predio.setDerechosAcciones(resultSet.getDouble("db_derechos_acciones"));
                predio.setClaveCatastral(resultSet.getString("txt_clave_catastral"));
                predio.setClaveCatAnterior(resultSet.getString("txt_clave_cat_anterior"));
                predio.setBarrio(ServiciosBarrios.obtenerSectorDadoCodigo(resultSet.getInt("int_id_barrio")));
                predio.setCallePrincipal(ServiciosCalles.obtenerCalleDadoCodigo(resultSet.getInt("int_calle_principal")));
                predio.setCalleSecundaria(ServiciosCalles.obtenerCalleDadoCodigo(resultSet.getInt("int_calle_secundaria")));
                predio.setNombreEdificio(resultSet.getString("chv_nombre_edificio"));
                predio.setTipoPersoneria(ServiciosTipoPersoneria.obtenerTipoPersoneriaDadoCodigo(resultSet.getInt("int_tipo_personeria")));
                predio.setPropietario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_propietario")));
                predio.setExcenEspecial(ServiciosExenciones.obtenerExencionDadoCodigo(resultSet.getInt("int_id_tipo_excencion")));
                predio.setFormaAdq(ServFormaAdquisicion.obtenerFormaAdqDadoCodigo(resultSet.getInt("int_id_forma_adq")));
                predio.setFechaEscrituracion(resultSet.getDate("dt_fecha_escrituracion"));
                predio.setFechaRegistroPredio(resultSet.getDate("dt_fecha_registro"));
                predio.setCuantia(resultSet.getDouble("db_cuantia"));
                predio.getPrestamo().setIdPrestamo(resultSet.getInt("int_id_prestamo"));
                predio.setCondPedio(resultSet.getString("chv_condicion_perdio"));
                predio.setPatio(resultSet.getString("chv_patio"));
                predio.setCerramiento(resultSet.getString("chv_cerramiento"));
                predio.setMuros(resultSet.getString("chv_muros"));
                predio.setPiscina(resultSet.getString("chv_piscina"));
                predio.setCisterna(resultSet.getString("chv_cisterna"));
                predio.setNumBanios(resultSet.getInt("int_numero_banios"));
                predio.setPathCroquis(resultSet.getString("chv_path_croquis"));
                predio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                predio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro_predio"));
                predio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                predio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(predio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Predio obtenerPredioDadoCodigo(int codigo) throws Exception {
        Predio predio = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_predio_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                predio = new Predio();
                predio.setIdPredio(resultSet.getInt("sr_id_predio"));
                predio.setParroquia(new Parroquia());
                predio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(resultSet.getInt("int_id_parroquia")));
                predio.setDivPolitica(resultSet.getString("chv_division_politica"));
                predio.setManzana(ServiciosManzana.obtenerManzanaDadoCodigo(resultSet.getInt("int_id_manzana")));
                predio.setNumPredio(resultSet.getInt("int_numero_predio"));
                predio.setTipoLote(ServiciosTipoLote.obtenerTipoLoteDadoCodigo(resultSet.getInt("int_tipo_lote")));
                predio.setPropHorizontal(resultSet.getDouble("db_prop_horizontal"));
                predio.setDerechosAcciones(resultSet.getDouble("db_derechos_acciones"));
                predio.setClaveCatastral(resultSet.getString("txt_clave_catastral"));
                predio.setClaveCatAnterior(resultSet.getString("txt_clave_cat_anterior"));
                predio.setBarrio(ServiciosBarrios.obtenerSectorDadoCodigo(resultSet.getInt("int_id_barrio")));
                predio.setCallePrincipal(ServiciosCalles.obtenerCalleDadoCodigo(resultSet.getInt("int_calle_principal")));
                predio.setCalleSecundaria(ServiciosCalles.obtenerCalleDadoCodigo(resultSet.getInt("int_calle_secundaria")));
                predio.setNombreEdificio(resultSet.getString("chv_nombre_edificio"));
                predio.setTipoPersoneria(ServiciosTipoPersoneria.obtenerTipoPersoneriaDadoCodigo(resultSet.getInt("int_tipo_personeria")));
                predio.setPropietario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_propietario")));
                predio.setExcenEspecial(ServiciosExenciones.obtenerExencionDadoCodigo(resultSet.getInt("int_id_tipo_excencion")));
                predio.setFormaAdq(ServFormaAdquisicion.obtenerFormaAdqDadoCodigo(resultSet.getInt("int_id_forma_adq")));
                predio.setFechaEscrituracion(resultSet.getDate("dt_fecha_escrituracion"));
                predio.setFechaRegistroPredio(resultSet.getDate("dt_fecha_registro"));
                predio.setCuantia(resultSet.getDouble("db_cuantia"));
                predio.getPrestamo().setIdPrestamo(resultSet.getInt("int_id_prestamo"));
                predio.setCondPedio(resultSet.getString("chv_condicion_perdio"));
                predio.setPatio(resultSet.getString("chv_patio"));
                predio.setCerramiento(resultSet.getString("chv_cerramiento"));
                predio.setMuros(resultSet.getString("chv_muros"));
                predio.setPiscina(resultSet.getString("chv_piscina"));
                predio.setCisterna(resultSet.getString("chv_cisterna"));
                predio.setNumBanios(resultSet.getInt("int_numero_banios"));
                predio.setPathCroquis(resultSet.getString("chv_path_croquis"));
                predio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                predio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro_predio"));
                predio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                predio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return predio;
    }

    public static String reevaluarConstruccioGlobal(int codigoUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_reevaluar_valor_construccion_dado_usuario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoUsuario);
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

    public static String reevaluarConstruccionDadoPredio(int idPredio, int codigoUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_reevaluar_valor_construccion_dado_predio(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idPredio);
            prstm.setInt(2, codigoUsuario);
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
    
    
    public static String reevaluarUsoSueloGlobal(int codigoUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_reevaluar_detalle_uso_suelo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoUsuario);
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

    public static String reevaluarUsoSueloDadoPredio(int idPredio, int codigoUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_reevaluar_detalle_uso_suelo_dado_predio(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idPredio);
            prstm.setInt(2, codigoUsuario);
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
}
