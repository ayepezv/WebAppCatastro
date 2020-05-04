package catastro.recaudacion.funciones;

import accesoDatos.AccesoDatos;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.Titulo;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FTitulos {

    //<editor-fold defaultstate="collapsed" desc="Consultar valores a pagar dado propietario">    
    public static List<Titulo> consultarValoresPagarDadoPropietario(int codigoPropietario) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_consultar_titulos_pagar_dado_propietario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPropietario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("_int_anio_titulo"));
                titulo.setTituloNumero(resultSet.getString("_chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("_ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
                //titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("_id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("_db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("_db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("_db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("_db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("_chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("_db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("_db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("_db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("_db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("_db_recargo"));
                titulo.setDescuento(resultSet.getDouble("_db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("_db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("_db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("_db_total_pagar"));
                titulo.setIva(resultSet.getDouble("_db_iva"));
                titulo.setPagado(resultSet.getBoolean("_bl_pagado"));
                titulo.setProcesamiento(resultSet.getDouble("_procesamiento"));
                titulo.setPagoTardio(resultSet.getDouble("_recargo_pago_tardio"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar valores a pagar dado predio">  
    public static List<Titulo> consultarValoresPagarDadoPredio(int codigoPredio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_consultar_titulos_pagar_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("_int_anio_titulo"));
                titulo.setTituloNumero(resultSet.getString("_chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("_ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("_id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("_db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("_db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("_db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("_db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("_chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("_db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("_db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("_db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("_db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("_db_recargo"));
                titulo.setDescuento(resultSet.getDouble("_db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("_db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("_db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("_db_total_pagar"));
                titulo.setIva(resultSet.getDouble("_db_iva"));
                titulo.setPagado(resultSet.getBoolean("_bl_pagado"));
                titulo.setProcesamiento(resultSet.getDouble("_procesamiento"));
                titulo.setPagoTardio(resultSet.getDouble("_recargo_pago_tardio"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="Recaudar titulo">    
    public static String recaudarTitulo(Titulo titulo, int idRecaudador, int idCliente, int idFormaPago) throws Exception {
        String msg;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_recaudar_titulo(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, titulo.getIdTitulo());
            prstm.setInt(2, idRecaudador);
            prstm.setInt(3, idCliente);
            prstm.setInt(4, idFormaPago);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                msg = resultSet.getString(1);
                return msg;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar titulos anulados dado predio">  
    public static List<Titulo> consultarTitulosAnulados(int codigoPredio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_titulos_baja_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar titulos pagados dado predio">  
    public static List<Titulo> consultarTitulosPagados(int codigoPredio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obetener_titulos_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                titulo.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar titulos pagados dado propietario"> 
    public static List<Titulo> consultarTitulosPagadosPropietario(int codigoPropietario) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obetener_titulos_dado_propietario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPropietario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar años de los titulos emitidos">   
    public static List<Titulo> obtenerAniosTitulosEmitidos() throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_anios_titulos_emitidos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar titulos dado año">  
    public static List<Titulo> consultarTitulosDadoAnio(int anio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_reporte_libro_predial_2(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("_orden"));
                titulo.setAnioTitulo(resultSet.getInt("_anio"));
                titulo.setTituloNumero(resultSet.getString("_chv_titulo_numero"));
                //titulo.setFechaEmision(resultSet.getTimestamp("_ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_int_id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("_int_id_titular_dom")));
                titulo.setValorTerreno(resultSet.getDouble("_uso_suelo"));
                titulo.setValorConstruccion(resultSet.getDouble("_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("_avaluo"));
                titulo.setExcenciones(resultSet.getDouble("_excenciones"));
                //titulo.setCausaExcencion(resultSet.getString("_chv_causa_excencion"));
                //titulo.setAvaluoImponible(resultSet.getDouble("_db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("_impuesto"));
                //titulo.setRecolectBasura(resultSet.getDouble("_db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("_bomberos"));
                titulo.setRecargo(resultSet.getDouble("_recargo"));
                //titulo.setDescuento(resultSet.getDouble("_db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("_total"));
                //titulo.setInteresesMora(resultSet.getDouble("_db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("_total"));
                //titulo.setIva(resultSet.getDouble("_db_iva"));
                titulo.setPagado(resultSet.getBoolean("_bl_pagado"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtener titulos por año">    
    public static List<Titulo> obtenerTituloPorAnio(int anio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_consultar_titulos_dado_anio_emision(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Simular emision de titulos ">    
    public static String simularTitulo(int usuario) throws Exception {
        String msg;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_simular_titulos_anuales(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                msg = resultSet.getString(1);
                return msg;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Eliminar los titulos simulados">    
    public static String eliminarSimulacion() throws Exception {
        String msg;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_titulos_simulados()";
            prstm = accesoDatos.creaPreparedSmt(sql);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                msg = resultSet.getString(1);
                return msg;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar los titulos de la simulación">    
    public static List<Titulo> consultarTitulosSimulados() throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_consultar_titulos_simulados();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar los titulos de la simulación version 2">       
    public static List<Titulo> consultarTitulosSimuladosV2() throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_titulos_prueba_v2();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setProcesamiento(resultSet.getDouble("db_procesamiento"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                //titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Emision de titulos anuales">    
    public static String generarTitulo(int usuario) throws Exception {
        String msg;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_generar_titulos_anuales(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                msg = resultSet.getString(1);
                return msg;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtener titulos dado zona, sector, manzana y predio">
    public static List<Titulo> obtenerTitulosDadoZonaSectorManzanaPredio(int zona, int sector, int manzana, int predio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_titulos_dado_zona_sector_manzana(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, sector);
            prstm.setInt(3, manzana);
            prstm.setInt(4, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtener titulo dado código">  
    public static Titulo obtenerTituloDadoId(int idTitulo) throws Exception {
        Titulo titulo = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_titulo_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idTitulo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return titulo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Emision individual de titulos dado predio, año y usuario">    
    public static String emisionIndividual(int idPredio, int anio, int idSessionUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_generar_titulos_anual_individual(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idPredio);
            prstm.setInt(2, anio);
            prstm.setInt(3, idSessionUsuario);
            prstm.setInt(4, 1);
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

    //<editor-fold defaultstate="collapsed" desc="Emision individual de titulos dado predio, año y usuario">    
    public static String emisionIndividualCalcular(int idPredio, int anio, int idSessionUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_generar_titulos_anual_individual(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idPredio);
            prstm.setInt(2, anio);
            prstm.setInt(3, idSessionUsuario);
            prstm.setInt(4, 0);
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

    //<editor-fold defaultstate="collapsed" desc="Obtener los diferentes años de los titulos emitidos">  
    public static List<Titulo> obtenerAniosTitulos() throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_anios_titulos_emitidos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>     

    //<editor-fold defaultstate="collapsed" desc="Libro predial dado año - titulos dado año">    
    public static List<Titulo> obtenerTituloDadoAnioVencida(int anio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_libro_predial_dado_anio_vencida(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setProcesamiento(resultSet.getDouble("db_procesamiento"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                //// seteo los datos del usuario del sistema
                titulo.getUsuarioSistema().setIdPersona(resultSet.getInt("id_session_usuario"));
                titulo.getUsuarioSistema().setNick(resultSet.getString("chv_nick"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Libro predial dado año - titulos dado año">    
    public static List<Titulo> obtenerTituloDadoAnio(int anio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_libro_predial_dado_anio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setProcesamiento(resultSet.getDouble("db_procesamiento"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                //// seteo los datos del usuario del sistema
                titulo.getUsuarioSistema().setIdPersona(resultSet.getInt("id_session_usuario"));
                titulo.getUsuarioSistema().setNick(resultSet.getString("chv_nick"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Libro predial dado año - titulos dado año ANULADOS">  
    public static List<Titulo> obtenerTituloDadoAnioAnulados(int anio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_libro_predial_dado_anio_anulados(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setProcesamiento(resultSet.getDouble("db_procesamiento"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                //// seteo los datos del usuario del sistema
                titulo.getUsuarioSistema().setIdPersona(resultSet.getInt("id_session_usuario"));
                titulo.getUsuarioSistema().setNick(resultSet.getString("chv_nick"));

                //// seteo los datos del historico de titulos dados de baja
                titulo.setObservaciones(resultSet.getString("chv_observaciones"));
                titulo.setRazonBaja(resultSet.getString("chv_razon_baja"));
                titulo.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                titulo.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));

                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Libro predial dado año y parroquia">    
    public static List<Titulo> obtenerTitulodadoAnioParroquia(int anio, int codigoParroquia) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_libro_predial_dado_anio_parroquia(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            prstm.setInt(2, codigoParroquia);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                //// seteo los datos del usuario del sistema
                titulo.getUsuarioSistema().setIdPersona(resultSet.getInt("id_session_usuario"));
                titulo.getUsuarioSistema().setNick(resultSet.getString("chv_nick"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Libro predial dado año, zona, sector y manzana">    
    public static List<Titulo> obtenerTitulodadoAnioZonaSectorManzanaPredio(int anio, int numZona, int numSector, int numManzana, int numPredio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_libro_predial_dado_anio_zona_sector_manza_predio(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            prstm.setInt(2, numZona);
            prstm.setInt(3, numSector);
            prstm.setInt(4, numManzana);
            prstm.setInt(5, numPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                //// seteo los datos del usuario del sistema
                titulo.getUsuarioSistema().setIdPersona(resultSet.getInt("id_session_usuario"));
                titulo.getUsuarioSistema().setNick(resultSet.getString("chv_nick"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    public static List<Titulo> obtenerRecaudacionesPredialDadoFecha(Date fecha) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudacion_predial_dado_fecha(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, fecha);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
//                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
//                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                titulo.getTransacion().setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                titulo.getTransacion().setNumTransaccion(resultSet.getInt("num_transaccion"));
                titulo.getTransacion().setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.getTransacion().setRecaudacion(resultSet.getString("chv_recaudacion"));
                titulo.getTransacion().setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                titulo.getTransacion().setEstadoTransacion(resultSet.getString("estado_transacion"));
                titulo.getTransacion().getCliente().setIdPersona(resultSet.getInt("id_cliente"));
                titulo.getTransacion().getCliente().setNombres(resultSet.getString("chv_nombres"));
                titulo.getTransacion().getCliente().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getTransacion().getCliente().setCedula(resultSet.getString("chv_cedula"));
                titulo.getTransacion().getRecaudador().setNombres(resultSet.getString("chv_nombres_recaudador"));
                titulo.getTransacion().getRecaudador().setApellidos(resultSet.getString("chv_apellidos_recaudador"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Titulo> obtenerRecaudacionesPredialDadoRangoFechas(Date fechaIni, Date fechaFin) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudacion_predial_dado_rango_fechas(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, fechaIni);
            prstm.setDate(2, fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
//                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
//                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                titulo.getTransacion().setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                titulo.getTransacion().setNumTransaccion(resultSet.getInt("num_transaccion"));
                titulo.getTransacion().setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.getTransacion().setRecaudacion(resultSet.getString("chv_recaudacion"));
                titulo.getTransacion().setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                titulo.getTransacion().setEstadoTransacion(resultSet.getString("estado_transacion"));
                titulo.getTransacion().getCliente().setIdPersona(resultSet.getInt("id_cliente"));
                titulo.getTransacion().getCliente().setNombres(resultSet.getString("chv_nombres"));
                titulo.getTransacion().getCliente().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getTransacion().getCliente().setCedula(resultSet.getString("chv_cedula"));
                titulo.getTransacion().getRecaudador().setNombres(resultSet.getString("chv_nombres_recaudador"));
                titulo.getTransacion().getRecaudador().setApellidos(resultSet.getString("chv_apellidos_recaudador"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Titulo> obtenerTitulosEmitidosDadoRangoFechas(Date fechaIni, Date fechaFin) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_titulos_dado_rango_fecha(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, fechaIni);
            prstm.setDate(2, fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("total_a_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
//                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
//                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                titulo.getPredio().getPropietario().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPredio().getPropietario().setCedula(resultSet.getString("chv_cedula"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Titulo> obtenerTitulosEmitidosDadoFecha(Date fechaIni) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_titulos_dado_fecha(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, fechaIni);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                //titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));

                //// seteo los datos del predio
                titulo.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                titulo.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
//                titulo.getPredio().setCallePrinc(resultSet.getString("chv_calle_princ"));
//                titulo.getPredio().setCalleSecund(resultSet.getString("chv_calle_secund"));

                //// seteo los datos del propietario
                titulo.getPropietarioIndex().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPropietarioIndex().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPropietarioIndex().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPropietarioIndex().setCedula(resultSet.getString("chv_cedula"));

                titulo.getPredio().getPropietario().setIdPersona(resultSet.getInt("sr_id_persona"));
                titulo.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                titulo.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                titulo.getPredio().getPropietario().setCedula(resultSet.getString("chv_cedula"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Titulo obtenerTituloDadoTransaccion(int codigo) throws Exception {
        Titulo titulo = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_titulo_dato_transaccion(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("fecha_emision_titulo"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return titulo;
    }

    //<editor-fold defaultstate="collapsed" desc="Consultar valores a pagar dado zona, sector, manzana y predio">    
    public static List<Titulo> consultarValoresPagarDadoZonaSectorManzanaPredio(int zona, int sector, int manzan, int predio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_consultar_titulos_pagar_dado_zona_sector_manzana_predio(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, sector);
            prstm.setInt(3, manzan);
            prstm.setInt(4, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("_int_anio_titulo"));
                titulo.setTituloNumero(resultSet.getString("_chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("_ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("_id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("_db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("_db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("_db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("_db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("_chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("_db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("_db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("_db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("_db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("_db_recargo"));
                titulo.setDescuento(resultSet.getDouble("_db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("_db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("_db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("_db_total_pagar"));
                titulo.setIva(resultSet.getDouble("_db_iva"));
                titulo.setPagado(resultSet.getBoolean("_bl_pagado"));
                titulo.setProcesamiento(resultSet.getDouble("_procesamiento"));
                titulo.setPagoTardio(resultSet.getDouble("_recargo_pago_tardio"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar titulos pagados dado zona, sector, manzana, predio">  
    public static List<Titulo> consultarTitulosPagadosDadoZonaSectorManzanaPredio(int zona, int sector, int manzana, int predio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_titulos_pagados_zona_sector_manzana(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, sector);
            prstm.setInt(3, manzana);
            prstm.setInt(4, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                titulo.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar titulos emitidos individualmente entre fechas">  
    public static List<Titulo> consultarTitulosIndividualesEntreFechas(Date fechaInicio, Date fechaFin) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_consultar_altas_dado_rango_fechas(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, fechaInicio);
            prstm.setDate(2, fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("_int_anio_titulo"));
                titulo.setTituloNumero(resultSet.getString("_chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("_ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("_id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("_db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("_db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("_db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("_db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("_chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("_db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("_db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("_db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("_db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("_db_recargo"));
                titulo.setDescuento(resultSet.getDouble("_db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("_db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("_db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("_db_total_pagar"));
                titulo.setIva(resultSet.getDouble("_db_iva"));
                titulo.setPagado(resultSet.getBoolean("_bl_pagado"));
                titulo.setProcesamiento(resultSet.getDouble("_procesamiento"));
                titulo.setPagoTardio(resultSet.getDouble("_recargo_pago_tardio"));
                titulo.setTipoEmision(resultSet.getString("_tipo_emision"));
                titulo.setFechaEmisionIndividual(resultSet.getTimestamp("_fecha_emision_individual"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    public static int titulosPendientes(int codigoPredio) throws Exception {
        int respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obligaciones_pendientes(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
                return respuesta;
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Obtener titulos previos dado zona, sector, manzana y predio">
    public static List<Titulo> obtenerTitulosPreviosDadoZonaSectorManzanaPredio(int zona, int sector, int manzana, int predio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_titulos_prueba_v3_1(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, sector);
            prstm.setInt(3, manzana);
            prstm.setInt(4, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
//                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
//                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtener titulos previos desde - hasta dado zona, sector, manzana y predio">
    public static List<Titulo> obtenerNotificacionesDesdeHasta(int zona,
            int zonaHasta,
            int sector,
            int sectorHasta,
            int manzana,
            int manzanaHasta,
            int predio,
            int predioHasta) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_notificaciones_desde_hasta(?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, zonaHasta);
            prstm.setInt(3, sector);
            prstm.setInt(4, sectorHasta);
            prstm.setInt(5, manzana);
            prstm.setInt(6, manzanaHasta);
            prstm.setInt(7, predio);
            prstm.setInt(8, predioHasta);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                titulo.setRecargo(resultSet.getDouble("db_recargo"));
                titulo.setDescuento(resultSet.getDouble("db_descuento"));
                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                titulo.setIva(resultSet.getDouble("db_iva"));
                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
//                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
//                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    public static List<Titulo> obtenerDeudasAnterioresDadoTituloAnio(int predio, int anio) throws Exception {
        List<Titulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Titulo titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_veriicar_deudas_anteriores_dado_predio_anio(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, predio);
            prstm.setInt(2, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new Titulo();
                titulo.setIdTitulo(resultSet.getInt("sr_id_titulo"));
                titulo.setAnioTitulo(resultSet.getInt("int_anio_titulo"));
//                titulo.setNumTitulo(resultSet.getInt("int_num_titulo"));
//                titulo.setTituloNumero(resultSet.getString("chv_titulo_numero"));
//                titulo.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
//                titulo.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
//                titulo.setPropietarioIndex(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_propietario_index")));
//                titulo.setValorTerreno(resultSet.getDouble("db_valor_terreno"));
//                titulo.setValorConstruccion(resultSet.getDouble("db_valor_construccion"));
//                titulo.setAvaluoComercial(resultSet.getDouble("db_avaluo_comercial"));
//                titulo.setExcenciones(resultSet.getDouble("db_excenciones"));
//                titulo.setCausaExcencion(resultSet.getString("chv_causa_excencion"));
//                titulo.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
//                titulo.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
//                titulo.setRecolectBasura(resultSet.getDouble("db_recolect_basura"));
//                titulo.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
//                titulo.setRecargo(resultSet.getDouble("db_recargo"));
//                titulo.setDescuento(resultSet.getDouble("db_descuento"));
//                titulo.setTotalTitulo(resultSet.getDouble("db_total_titulo"));
//                titulo.setInteresesMora(resultSet.getDouble("db_intereses_mora"));
//                titulo.setTotalPagar(resultSet.getDouble("db_total_pagar"));
//                titulo.setIva(resultSet.getDouble("db_iva"));
//                titulo.setPagado(resultSet.getBoolean("bl_pagado"));
//                titulo.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("usuario_sistema")));
//                titulo.setTransacion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("int_id_transacion")));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
