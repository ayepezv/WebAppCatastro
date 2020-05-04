package catastro.cem.funciones;

import accesoDatos.AccesoDatos;
import catastro.cem.entidades.Obra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FObra {

    public static List<Obra> obtenerObras() throws Exception {
        List<Obra> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Obra obra;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_obras();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                obra = new Obra();
                obra.setIdObra(resultSet.getInt("sr_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorctMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorctBenefDirect(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirect(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvaluoBenefDirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorctBenefIndirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setFrentBenefIndirect(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvaluoBenefIndirect(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                obra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                lst.add(obra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Obra> obtenerObraDadoMejora(int codigo) throws Exception {
        List<Obra> lst = new ArrayList<>();
        Obra obra;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_obra_dado_mejora(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                obra = new Obra();
                obra.setIdObra(resultSet.getInt("sr_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorctMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorctBenefDirect(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirect(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvaluoBenefDirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorctBenefIndirect(resultSet.getDouble("db_porct_benef_indirect"));
                obra.setFrentBenefIndirect(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvaluoBenefIndirect(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                obra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                obra.setRangoAfectacion(resultSet.getInt("int_rango_afectacion"));
                obra.setFechaInicialPago(resultSet.getDate("fecha_inicial_pago"));
                obra.setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(resultSet.getInt("int_id_forma_pago")));
                obra.setPlazo(resultSet.getInt("int_plazo"));
                obra.setValorMetroDirecto(resultSet.getDouble("db_metro_lineal_direct"));
                obra.setRangoAfectInd(resultSet.getInt("int_rango_afect_ind"));
                obra.setCalleAfectacion1(resultSet.getString("chv_calle_afectacion_1"));
                obra.setCalleAfectacion2(resultSet.getString("chv_calle_afectacion_2"));
                obra.setCalleAfectacion3(resultSet.getString("chv_calle_afectacion_3"));
                obra.setCalleAfectacion4(resultSet.getString("chv_calle_afectacion_4"));
                
                lst.add(obra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String insertarObra(Obra obra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_obra(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, obra.getNombreObra());
            prstm.setInt(2, obra.getTipoObra().getIdTipoObra());
            prstm.setBoolean(3, obra.isExcluyente());
            prstm.setDouble(4, obra.getValorObra());
            prstm.setDouble(5, obra.getValorDirecto());
            prstm.setInt(6, obra.getMejora().getIdMejora());
            prstm.setDouble(7, obra.getPorctMunicipio());
            prstm.setDouble(8, obra.getPorctBenefDirect());
            prstm.setDouble(9, obra.getFrenteBenefDirect());
            prstm.setDouble(10, obra.getAvaluoBenefDirect());
            prstm.setDouble(11, obra.getPorctBenefIndirect());
            prstm.setDouble(12, obra.getFrentBenefIndirect());
            prstm.setDouble(13, obra.getAvaluoBenefIndirect());
            prstm.setDouble(14, obra.getAreaLoteMedio());
            prstm.setDouble(15, obra.getAreaConstrucMedia());
            prstm.setInt(16, obra.getSessionUsuario().getIdPersona());
            prstm.setInt(17, obra.getRangoAfectacion());
            prstm.setDouble(18, obra.getValorMetroDirecto());
            prstm.setDouble(19, obra.getValorMetroIndirecto());
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

    public static String registrarObra(Obra obra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_obra_cem_3(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, obra.getNombreObra());
            prstm.setInt(2, obra.getTipoObra().getIdTipoObra());
            prstm.setDouble(3, obra.getValorObra());
            prstm.setInt(4, obra.getMejora().getIdMejora());
            prstm.setDouble(5, obra.getPorctMunicipio());
            prstm.setDouble(6, obra.getPorctBenefDirect());
            prstm.setDouble(7, obra.getFrenteBenefDirect());
            prstm.setDouble(8, obra.getAvaluoBenefDirect());
            prstm.setDouble(9, obra.getPorctBenefIndirect());
            prstm.setDouble(10, obra.getFrentBenefIndirect());
            prstm.setDouble(11, obra.getAvaluoBenefIndirect());
            prstm.setInt(12, obra.getSessionUsuario().getIdPersona());
            prstm.setInt(13, obra.getRangoAfectacion());
            prstm.setDouble(14, obra.getValorMetroDirecto());
            prstm.setDouble(15, obra.getValorMetroIndirecto());
            prstm.setInt(16, obra.getPlazo());
            prstm.setInt(17, obra.getFormaPago().getIdFormaPago());
            prstm.setDate(18, new java.sql.Date(obra.getFechaInicialPago().getTime()));
            prstm.setString(19, "NN");
            prstm.setString(20, "NN");
            prstm.setString(21, "NN");
            prstm.setString(22, "NN");
            prstm.setInt(23, obra.getRangoAfectInd());
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

    public static String editarObra(Obra obra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_editar_obra_cem_3(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, obra.getNombreObra());
            prstm.setInt(2, obra.getTipoObra().getIdTipoObra());
            prstm.setDouble(3, obra.getValorObra());
            prstm.setInt(4, obra.getMejora().getIdMejora());
            prstm.setDouble(5, obra.getPorctMunicipio());
            prstm.setDouble(6, obra.getPorctBenefDirect());
            prstm.setDouble(7, obra.getFrenteBenefDirect());
            prstm.setDouble(8, obra.getAvaluoBenefDirect());
            prstm.setDouble(9, obra.getPorctBenefIndirect());
            prstm.setDouble(10, obra.getFrentBenefIndirect());
            prstm.setDouble(11, obra.getAvaluoBenefIndirect());
            prstm.setInt(12, obra.getSessionUsuario().getIdPersona());
            prstm.setInt(13, obra.getRangoAfectacion());
            prstm.setDouble(14, obra.getValorMetroDirecto());
            prstm.setDouble(15, obra.getValorMetroIndirecto());
            prstm.setInt(16, obra.getPlazo());
            prstm.setInt(17, obra.getFormaPago().getIdFormaPago());
            prstm.setDate(18, new java.sql.Date(obra.getFechaInicialPago().getTime()));
            prstm.setString(19, obra.getCalleAfectacion1());
            prstm.setString(20, obra.getCalleAfectacion2());
            prstm.setString(21, obra.getCalleAfectacion3());
            prstm.setString(22, obra.getCalleAfectacion4());
            prstm.setInt(23, obra.getRangoAfectInd());
            prstm.setInt(24, obra.getIdObra());
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

    public static String actualizarObra(Obra obra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_obra(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, obra.getNombreObra());
            prstm.setInt(2, obra.getTipoObra().getIdTipoObra());
            prstm.setBoolean(3, obra.isExcluyente());
            prstm.setDouble(4, obra.getValorObra());
            prstm.setDouble(5, obra.getValorDirecto());
            prstm.setInt(6, obra.getMejora().getIdMejora());
            prstm.setDouble(7, obra.getPorctMunicipio());
            prstm.setDouble(8, obra.getPorctBenefDirect());
            prstm.setDouble(9, obra.getFrenteBenefDirect());
            prstm.setDouble(10, obra.getAvaluoBenefDirect());
            prstm.setDouble(11, obra.getPorctBenefIndirect());
            prstm.setDouble(12, obra.getFrentBenefIndirect());
            prstm.setDouble(13, obra.getAvaluoBenefIndirect());
            prstm.setDouble(14, obra.getAreaLoteMedio());
            prstm.setDouble(15, obra.getAreaConstrucMedia());
            prstm.setInt(16, obra.getSessionUsuario().getIdPersona());
            prstm.setInt(17, obra.getIdObra());
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

    //<editor-fold defaultstate="collapsed" desc="Eliminar obra">   
    public static String eliminarObra(Obra obra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_obra(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra.getIdObra());
            prstm.setInt(2, obra.getSessionUsuario().getIdPersona());
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtener obra dado codigo">       
    public static Obra obtenerObraDadoCodigo(int codigo) throws Exception {
        Obra obra = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_obra_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                obra = new Obra();
                obra.setIdObra(resultSet.getInt("sr_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorctMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorctBenefDirect(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirect(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvaluoBenefDirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorctBenefIndirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setFrentBenefIndirect(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvaluoBenefIndirect(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                //obra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                obra.setRangoAfectacion(resultSet.getInt("int_rango_afectacion"));
                obra.setValorMetroDirecto(resultSet.getDouble("db_metro_lineal_direct"));
                obra.setValorMetroIndirecto(resultSet.getDouble("db_metro_lineal_indirect"));
                obra.setRangoAfectInd(resultSet.getInt("int_rango_afect_ind"));
                obra.setPlazo(resultSet.getInt("int_plazo"));
                obra.setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(resultSet.getInt("int_id_forma_pago")));
                obra.setFechaInicialPago(resultSet.getDate("fecha_inicial_pago"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return obra;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtener tipos de obras">  
    public static List<Obra> obtenerTiposObras() throws Exception {
        List<Obra> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Obra obra;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_tipos_obras();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                obra = new Obra();
                obra.setIdObra(resultSet.getInt("sr_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorctMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorctBenefDirect(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirect(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvaluoBenefDirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorctBenefIndirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setFrentBenefIndirect(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvaluoBenefIndirect(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                obra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                lst.add(obra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtener obras dado tipo">
    public static List<Obra> obtenerObraDadoTipo(int tipoObra) throws Exception {
        List<Obra> lst = new ArrayList<>();
        Obra obra;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_obras_dado_tipo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, tipoObra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                obra = new Obra();
                obra.setIdObra(resultSet.getInt("sr_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorctMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorctBenefDirect(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirect(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvaluoBenefDirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorctBenefIndirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setFrentBenefIndirect(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvaluoBenefIndirect(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                obra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                obra.setRangoAfectacion(resultSet.getInt("int_rango_afectacion"));
                lst.add(obra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    public static List<Obra> encontrarObras(String codigo) throws Exception {
        List<Obra> lst = new ArrayList<>();
        Obra obra;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_buscar_obras_dado_criterio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                obra = new Obra();
                obra.setIdObra(resultSet.getInt("sr_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorctMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorctBenefDirect(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirect(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvaluoBenefDirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorctBenefIndirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setFrentBenefIndirect(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvaluoBenefIndirect(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                obra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                obra.setRangoAfectacion(resultSet.getInt("int_rango_afectacion"));
                obra.setFechaInicialPago(resultSet.getDate("fecha_inicial_pago"));
                obra.setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(resultSet.getInt("int_id_forma_pago")));
                obra.setPlazo(resultSet.getInt("int_plazo"));
                lst.add(obra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Obra> encontrarObrasDadoCriterio(String criterio) throws Exception {
        List<Obra> lst = new ArrayList<>();
        Obra obra;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_buscar_obras_dado_criterio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, criterio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                obra = new Obra();
                obra.setIdObra(resultSet.getInt("sr_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorctMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorctBenefDirect(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirect(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvaluoBenefDirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorctBenefIndirect(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setFrentBenefIndirect(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvaluoBenefIndirect(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                obra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                obra.setRangoAfectacion(resultSet.getInt("int_rango_afectacion"));
                obra.setFechaInicialPago(resultSet.getDate("fecha_inicial_pago"));
                obra.setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(resultSet.getInt("int_id_forma_pago")));
                obra.setPlazo(resultSet.getInt("int_plazo"));
                obra.setValorMetroDirecto(resultSet.getDouble("db_metro_lineal_direct"));
                obra.setRangoAfectInd(resultSet.getInt("int_rango_afect_ind"));
                lst.add(obra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
