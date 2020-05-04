package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.coeficientes.funciones.FSectorHomogeneo;
import catastro.logica.entidades.Dominio;
import catastro.logica.entidades.Parroquia;
import catastro.logica.entidades.PredioV2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;

public class FBuscarPredios {

    public static ArrayList<PredioV2> obtenerPrediosFechas(Date fi, Date ff) throws Exception {
        ArrayList<PredioV2> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        PredioV2 predio;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_consultar_predios_entre_fechas_registrado(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fi);
            prstm.setDate(2, (java.sql.Date) ff);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                predio = new PredioV2();
                predio.setIdParroquia(new Parroquia());
                predio.setPropietario(new Usuario());
                predio.setRepLegal(new Usuario());
                predio.setDominio(new Dominio());
                predio.setIdPredio(resultSet.getInt("int_id_predio"));
                predio.setZona(resultSet.getInt("int_zona"));
                predio.setSector(resultSet.getInt("int_sector"));
                predio.setManzana(resultSet.getInt("int_manzana"));
                predio.setNumPredio(resultSet.getInt("int_num_predio"));
                predio.setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                predio.setBarrio(resultSet.getString("chv_barrio"));
                predio.setCallePrinc(resultSet.getString("chv_calle_princ"));
                predio.setCalleSecund(resultSet.getString("chv_calle_secund"));
                predio.setNumCasa(resultSet.getString("chv_num_casa"));
                predio.setPropietario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_titular_dom")));
                predio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                predio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                predio.getPropietario().setNombres(resultSet.getString("nombre"));
                predio.getPropietario().setCedula(resultSet.getString("chv_cedula"));
                predio.getPropietario().setRuc(resultSet.getString("chv_ruc"));
                lst.add(predio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<PredioV2> seleccionarPrediosFechas() throws Exception {
        ArrayList<PredioV2> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PredioV2 predio;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_predios_v2();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                predio = new PredioV2();
                predio.setIdParroquia(new Parroquia());
                predio.setPropietario(new Usuario());
                predio.setRepLegal(new Usuario());
                predio.setDominio(new Dominio());
                predio.setIdPredio(resultSet.getInt("int_id_predio"));
                predio.setNumFicha(resultSet.getInt("int_num_ficha"));
                predio.setIdParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(resultSet.getInt("int_id_parroquia")));
                predio.setZona(resultSet.getInt("int_zona"));
                predio.setSector(resultSet.getInt("int_sector"));
                predio.setManzana(resultSet.getInt("int_manzana"));
                predio.setNumPredio(resultSet.getInt("int_num_predio"));
                predio.setPropHorBloque(resultSet.getInt("int_prop_hor_bloque"));
                predio.setPropHorPiso(resultSet.getInt("int_prop_hor_piso"));
                predio.setPropHorUnidad(resultSet.getInt("int_prop_hor_unidad"));
                predio.setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                predio.setClaveCatastralAnt(resultSet.getString("chv_clave_catastral_ant"));
                predio.setBarrio(resultSet.getString("chv_barrio"));
                predio.setCallePrinc(resultSet.getString("chv_calle_princ"));
                predio.setCalleSecund(resultSet.getString("chv_calle_secund"));
                predio.setNumCasa(resultSet.getString("chv_num_casa"));
                predio.setNombreEdificio(resultSet.getString("chv_nombre_edificio"));
                predio.setPropietario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_titular_dom")));
                predio.setRepLegal(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_rep_legal")));
                predio.setDominio(ServiciosDominio.obtenerDominioDadoCodigo(resultSet.getInt("int_id_dominio")));
                predio.setCondicion(resultSet.getString("chv_condicion"));
                predio.setFormaAdq(resultSet.getString("chv_forma_adq"));
                predio.setNotaria(resultSet.getString("chv_notaria"));
                predio.setFechaTituloProp(resultSet.getDate("dt_fecha_titulo_prop"));
                predio.setFechaRegistroPredio(resultSet.getDate("dt_fecha_registro"));
                predio.setAreaEscritura(resultSet.getDouble("db_area_escritura"));
                predio.setPropAnterior(resultSet.getString("chv_prop_anterior"));
                predio.setAvaluoProp(resultSet.getDouble("db_avaluo_prop"));
                predio.setUsoSueloGestion(resultSet.getString("chv_gest_uso_suelo"));
                predio.setUsoSueloConsumo(resultSet.getString("chv_uso_suelo_consumo"));
                predio.setUsoSuelIntercambio(resultSet.getString("chv_us_suel_intercambio"));
                predio.setUsoSuelProduc(resultSet.getString("chv_us_suel_produc"));
                predio.setOcupacionPredio(resultSet.getString("chv_ocupacion_predio"));
                predio.setLocalizacionPredio(resultSet.getString("chv_localizacion_predio"));
                predio.setTopografiaPredio(resultSet.getString("chv_topografia_pedio"));
                predio.setFormaPredio(resultSet.getString("chv_forma_predio"));
                predio.setCaracteristSuelo(resultSet.getString("chv_caract_suelo"));
                predio.setUsoVias(resultSet.getString("chv_uso_vias"));
                predio.setMaterialVias(resultSet.getString("chv_material_vias"));
                predio.setRedAgua(resultSet.getString("chv_red_agua"));
                predio.setAlcantarillado(resultSet.getString("chv_alcantarillado"));
                predio.setEnergElectric(resultSet.getString("chv_energ_electric"));
                predio.setRedTelefon(resultSet.getString("chv_red_telefon"));
                predio.setRecBasura(resultSet.getString("chv_rec_basura"));
                predio.setTranspPublico(resultSet.getString("chv_transp_publico"));
                predio.setInternet(resultSet.getString("chv_internet"));
                predio.setOtrosServicios(resultSet.getString("chv_otros_servicios"));
                predio.setAreaConstruc(resultSet.getDouble("db_area_construc"));
                predio.setAreaTotalConst(resultSet.getDouble("db_area_total_const"));
                predio.setAreaTotalTerreno(resultSet.getDouble("db_area_total_terreno"));
                predio.setPatio(resultSet.getString("chv_patio"));
                predio.setPiscina(resultSet.getString("chv_piscina"));
                predio.setCerramiento(resultSet.getString("chv_cerramiento"));
                predio.setCisterna(resultSet.getString("chv_cisterna"));
                predio.setMuros(resultSet.getString("chv_muros"));
                predio.setAscensor(resultSet.getString("chv_ascensor"));
                predio.setNumBanios(resultSet.getInt("int_num_banios"));
                predio.setLinderoFrente1(resultSet.getDouble("db_lindero_frente1"));
                predio.setLinderoFrente2(resultSet.getDouble("db_lindero_frente2"));
                predio.setLinderoFrente3(resultSet.getDouble("db_lindero_frente3"));
                predio.setLinderoFrente4(resultSet.getDouble("db_lindero_frente4"));
                predio.setLinderoCalle1(resultSet.getString("chv_lindero_calle1"));
                predio.setLinderoCalle2(resultSet.getString("chv_lindero_calle2"));
                predio.setLinderoCalle3(resultSet.getString("chv_lindero_calle3"));
                predio.setLinderoCalle4(resultSet.getString("chv_lindero_calle4"));
                predio.setLinderoAlicuota1(resultSet.getDouble("db_lindero_alicuota1"));
                predio.setLinderoAlicuota2(resultSet.getDouble("db_lindero_alicuota2"));
                predio.setLinderoAlicuota3(resultSet.getDouble("db_lindero_alicuota3"));
                predio.setLinderoAlicuota4(resultSet.getDouble("db_lindero_alicuota4"));
                predio.setCroquis(resultSet.getString("chv_croquis"));
                predio.setEmplazamiento(resultSet.getString("chv_emplazamiento"));
                predio.setFotografia(resultSet.getString("chv_fotografia"));
                predio.setTopografiaRefTopog(resultSet.getString("chv_topografia_ref_top"));
                predio.setFotoAerea(resultSet.getString("chv_foto_aerea"));
                predio.setOtraRefTopog(resultSet.getString("chv_otra_ref_top"));
                predio.setCoordenadaE(resultSet.getDouble("db_coord_e"));
                predio.setCoordenadaN(resultSet.getDouble("db_coord_n"));
                predio.setColindantNorte(resultSet.getString("chv_colindant_nort"));
                predio.setColindantEste(resultSet.getString("chv_colindant_este"));
                predio.setColindantSur(resultSet.getString("chv_colindant_sur"));
                predio.setColindantOeste(resultSet.getString("chv_colindant_oest"));
                predio.setFuenteInformante(resultSet.getString("chv_fuente_informant"));
                predio.setTelfInformante(resultSet.getString("chv_telf_informant"));
                predio.setFichaAdjunta(resultSet.getString("chv_ficha_adjunta"));
                predio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                predio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                predio.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                predio.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                predio.setPorcentajeExcencion(resultSet.getDouble("db_porcent_excep"));
                predio.setExcencionEspecial(resultSet.getString("chv_excencion_esp"));
                predio.setSectorHomogeneo(FSectorHomogeneo.obtenerSectorHomogeneoDadoCodigo(resultSet.getInt("id_sector_homogeneo")));
                predio.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                predio.setPlanimetria(resultSet.getString("planimetria"));
                lst.add(predio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
