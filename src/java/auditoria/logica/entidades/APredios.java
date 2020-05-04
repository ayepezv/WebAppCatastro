package auditoria.logica.entidades;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;



public class APredios {

    private int idAudPredios;
    private int idPredio;
    private int numFicha;
    private int idParroquia;
    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private int proHorBloque;
    private int propHorPiso;
    private int propHorUnidad;
    private String claveCatastral;
    private String claveCatastralAnt;
    private String barrio;
    private String callePrincipal;
    private String calleSecundaria;
    private String numCasa;
    private String nombreEdificio;
    private Usuario titularDomi;
    private Usuario repreLegal;
    private int dominio;
    private String condicion;
    private String formaAdquisicion;
    private String notaria;
    private Date fechaTituloProp;
    private Date fechaRegistro;
    private double areaEscritura;
    private String proAnterior;
    private double avaluoPropi;
    private String gestUsoSuelo;
    private String usoSueloConsumo;
    private String usoSueloIntercambio;
    private String usoSueloProduc;
    private String ocupacionPredio;
    private String localizacionPredio;
    private String topografiaPredio;
    private String formaPredio;
    private String caracteristicasSuelo;
    private String usoVias;
    private String materialVia;
    private String redAgua;
    private String alcantarillado;
    private String energiaElectrica;
    private String redTelefono;
    private String recoleBasura;
    private String transportePublico;
    private String internet;
    private String otrosServicios;
    private double areaConstruccion;
    private double areaTotalConstruccion;
    private double areaTotalTerreno;
    private String patio;
    private String piscina;
    private String cerramiento;
    private String cisterna;
    private String muros;
    private String ascensor;
    private int numBanios;
    private double linderoFrente1;
    private double linderoFrente2;
    private double linderoFrente3;
    private double linderoFrente4;
    private String linderoCalle1;
    private String linderoCalle2;
    private String linderoCalle3;
    private String linderoCalle4;
    private double linderoAlicuota1;
    private double linderoAlicuota2;
    private double linderoAlicuota3;
    private double linderoAlicuota4;
    private double derechosAcciones;
    private String croquis;
    private String emplazamiento;
    private String fotografia;
    private String topografiaRefTop;
    private String fotoAerea;
    private String otraRefTopo;
    private double coordE;
    private double coordN;
    private String colindanteNorte;
    private String colindanteEste;
    private String colindanteSur;
    private String colindanteOeste;
    private String fuenteInformacion;
    private String telefoInformacion;
    private String fichaAdjunta;
    private Timestamp fechaRegistrada;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private String estadoLogico;
    private double porcentaExcepsion;
    private String excepsionEsp;
    private String observaciones;
    private int sectorHomogeneo;
    private Usuario usuarioSistema;
    private Timestamp fechaModificacion;
    private Time horaModificacion;
    private String accionRealizada;

    public APredios() {
        titularDomi = new Usuario();
        repreLegal = new Usuario();
        usuarioSistema = new Usuario();
    }

    /**
     * @return the idAudPredios
     */
    public int getIdAudPredios() {
        return idAudPredios;
    }

    /**
     * @param idAudPredios the idAudPredios to set
     */
    public void setIdAudPredios(int idAudPredios) {
        this.idAudPredios = idAudPredios;
    }

    /**
     * @return the idPredio
     */
    public int getIdPredio() {
        return idPredio;
    }

    /**
     * @param idPredio the idPredio to set
     */
    public void setIdPredio(int idPredio) {
        this.idPredio = idPredio;
    }

    /**
     * @return the numFicha
     */
    public int getNumFicha() {
        return numFicha;
    }

    /**
     * @param numFicha the numFicha to set
     */
    public void setNumFicha(int numFicha) {
        this.numFicha = numFicha;
    }

    /**
     * @return the idParroquia
     */
    public int getIdParroquia() {
        return idParroquia;
    }

    /**
     * @param idParroquia the idParroquia to set
     */
    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

    /**
     * @return the zona
     */
    public int getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(int zona) {
        this.zona = zona;
    }

    /**
     * @return the sector
     */
    public int getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(int sector) {
        this.sector = sector;
    }

    /**
     * @return the manzana
     */
    public int getManzana() {
        return manzana;
    }

    /**
     * @param manzana the manzana to set
     */
    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    /**
     * @return the numPredio
     */
    public int getNumPredio() {
        return numPredio;
    }

    /**
     * @param numPredio the numPredio to set
     */
    public void setNumPredio(int numPredio) {
        this.numPredio = numPredio;
    }

    /**
     * @return the proHorBloque
     */
    public int getProHorBloque() {
        return proHorBloque;
    }

    /**
     * @param proHorBloque the proHorBloque to set
     */
    public void setProHorBloque(int proHorBloque) {
        this.proHorBloque = proHorBloque;
    }

    /**
     * @return the propHorPiso
     */
    public int getPropHorPiso() {
        return propHorPiso;
    }

    /**
     * @param propHorPiso the propHorPiso to set
     */
    public void setPropHorPiso(int propHorPiso) {
        this.propHorPiso = propHorPiso;
    }

    /**
     * @return the propHorUnidad
     */
    public int getPropHorUnidad() {
        return propHorUnidad;
    }

    /**
     * @param propHorUnidad the propHorUnidad to set
     */
    public void setPropHorUnidad(int propHorUnidad) {
        this.propHorUnidad = propHorUnidad;
    }

    /**
     * @return the claveCatastral
     */
    public String getClaveCatastral() {
        return claveCatastral;
    }

    /**
     * @param claveCatastral the claveCatastral to set
     */
    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    /**
     * @return the claveCatastralAnt
     */
    public String getClaveCatastralAnt() {
        return claveCatastralAnt;
    }

    /**
     * @param claveCatastralAnt the claveCatastralAnt to set
     */
    public void setClaveCatastralAnt(String claveCatastralAnt) {
        this.claveCatastralAnt = claveCatastralAnt;
    }

    /**
     * @return the barrio
     */
    public String getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    /**
     * @return the callePrincipal
     */
    public String getCallePrincipal() {
        return callePrincipal;
    }

    /**
     * @param callePrincipal the callePrincipal to set
     */
    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    /**
     * @return the calleSecundaria
     */
    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    /**
     * @param calleSecundaria the calleSecundaria to set
     */
    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    /**
     * @return the numCasa
     */
    public String getNumCasa() {
        return numCasa;
    }

    /**
     * @param numCasa the numCasa to set
     */
    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    /**
     * @return the nombreEdificio
     */
    public String getNombreEdificio() {
        return nombreEdificio;
    }

    /**
     * @param nombreEdificio the nombreEdificio to set
     */
    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    /**
     * @return the titularDomi
     */
    public Usuario getTitularDomi() {
        return titularDomi;
    }

    /**
     * @param titularDomi the titularDomi to set
     */
    public void setTitularDomi(Usuario titularDomi) {
        this.titularDomi = titularDomi;
    }

    /**
     * @return the repreLegal
     */
    public Usuario getRepreLegal() {
        return repreLegal;
    }

    /**
     * @param repreLegal the repreLegal to set
     */
    public void setRepreLegal(Usuario repreLegal) {
        this.repreLegal = repreLegal;
    }

    /**
     * @return the dominio
     */
    public int getDominio() {
        return dominio;
    }

    /**
     * @param dominio the dominio to set
     */
    public void setDominio(int dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the condicion
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the formaAdquisicion
     */
    public String getFormaAdquisicion() {
        return formaAdquisicion;
    }

    /**
     * @param formaAdquisicion the formaAdquisicion to set
     */
    public void setFormaAdquisicion(String formaAdquisicion) {
        this.formaAdquisicion = formaAdquisicion;
    }

    /**
     * @return the notaria
     */
    public String getNotaria() {
        return notaria;
    }

    /**
     * @param notaria the notaria to set
     */
    public void setNotaria(String notaria) {
        this.notaria = notaria;
    }

    /**
     * @return the fechaTituloProp
     */
    public Date getFechaTituloProp() {
        return fechaTituloProp;
    }

    /**
     * @param fechaTituloProp the fechaTituloProp to set
     */
    public void setFechaTituloProp(Date fechaTituloProp) {
        this.fechaTituloProp = fechaTituloProp;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the areaEscritura
     */
    public double getAreaEscritura() {
        return areaEscritura;
    }

    /**
     * @param areaEscritura the areaEscritura to set
     */
    public void setAreaEscritura(double areaEscritura) {
        this.areaEscritura = areaEscritura;
    }

    /**
     * @return the proAnterior
     */
    public String getProAnterior() {
        return proAnterior;
    }

    /**
     * @param proAnterior the proAnterior to set
     */
    public void setProAnterior(String proAnterior) {
        this.proAnterior = proAnterior;
    }

    /**
     * @return the avaluoPropi
     */
    public double getAvaluoPropi() {
        return avaluoPropi;
    }

    /**
     * @param avaluoPropi the avaluoPropi to set
     */
    public void setAvaluoPropi(double avaluoPropi) {
        this.avaluoPropi = avaluoPropi;
    }

    /**
     * @return the gestUsoSuelo
     */
    public String getGestUsoSuelo() {
        return gestUsoSuelo;
    }

    /**
     * @param gestUsoSuelo the gestUsoSuelo to set
     */
    public void setGestUsoSuelo(String gestUsoSuelo) {
        this.gestUsoSuelo = gestUsoSuelo;
    }

    /**
     * @return the usoSueloConsumo
     */
    public String getUsoSueloConsumo() {
        return usoSueloConsumo;
    }

    /**
     * @param usoSueloConsumo the usoSueloConsumo to set
     */
    public void setUsoSueloConsumo(String usoSueloConsumo) {
        this.usoSueloConsumo = usoSueloConsumo;
    }

    /**
     * @return the usoSueloIntercambio
     */
    public String getUsoSueloIntercambio() {
        return usoSueloIntercambio;
    }

    /**
     * @param usoSueloIntercambio the usoSueloIntercambio to set
     */
    public void setUsoSueloIntercambio(String usoSueloIntercambio) {
        this.usoSueloIntercambio = usoSueloIntercambio;
    }

    /**
     * @return the usoSueloProduc
     */
    public String getUsoSueloProduc() {
        return usoSueloProduc;
    }

    /**
     * @param usoSueloProduc the usoSueloProduc to set
     */
    public void setUsoSueloProduc(String usoSueloProduc) {
        this.usoSueloProduc = usoSueloProduc;
    }

    /**
     * @return the ocupacionPredio
     */
    public String getOcupacionPredio() {
        return ocupacionPredio;
    }

    /**
     * @param ocupacionPredio the ocupacionPredio to set
     */
    public void setOcupacionPredio(String ocupacionPredio) {
        this.ocupacionPredio = ocupacionPredio;
    }

    /**
     * @return the localizacionPredio
     */
    public String getLocalizacionPredio() {
        return localizacionPredio;
    }

    /**
     * @param localizacionPredio the localizacionPredio to set
     */
    public void setLocalizacionPredio(String localizacionPredio) {
        this.localizacionPredio = localizacionPredio;
    }

    /**
     * @return the topografiaPredio
     */
    public String getTopografiaPredio() {
        return topografiaPredio;
    }

    /**
     * @param topografiaPredio the topografiaPredio to set
     */
    public void setTopografiaPredio(String topografiaPredio) {
        this.topografiaPredio = topografiaPredio;
    }

    /**
     * @return the formaPredio
     */
    public String getFormaPredio() {
        return formaPredio;
    }

    /**
     * @param formaPredio the formaPredio to set
     */
    public void setFormaPredio(String formaPredio) {
        this.formaPredio = formaPredio;
    }

    /**
     * @return the caracteristicasSuelo
     */
    public String getCaracteristicasSuelo() {
        return caracteristicasSuelo;
    }

    /**
     * @param caracteristicasSuelo the caracteristicasSuelo to set
     */
    public void setCaracteristicasSuelo(String caracteristicasSuelo) {
        this.caracteristicasSuelo = caracteristicasSuelo;
    }

    /**
     * @return the usoVias
     */
    public String getUsoVias() {
        return usoVias;
    }

    /**
     * @param usoVias the usoVias to set
     */
    public void setUsoVias(String usoVias) {
        this.usoVias = usoVias;
    }

    /**
     * @return the materialVia
     */
    public String getMaterialVia() {
        return materialVia;
    }

    /**
     * @param materialVia the materialVia to set
     */
    public void setMaterialVia(String materialVia) {
        this.materialVia = materialVia;
    }

    /**
     * @return the redAgua
     */
    public String getRedAgua() {
        return redAgua;
    }

    /**
     * @param redAgua the redAgua to set
     */
    public void setRedAgua(String redAgua) {
        this.redAgua = redAgua;
    }

    /**
     * @return the alcantarillado
     */
    public String getAlcantarillado() {
        return alcantarillado;
    }

    /**
     * @param alcantarillado the alcantarillado to set
     */
    public void setAlcantarillado(String alcantarillado) {
        this.alcantarillado = alcantarillado;
    }

    /**
     * @return the energiaElectrica
     */
    public String getEnergiaElectrica() {
        return energiaElectrica;
    }

    /**
     * @param energiaElectrica the energiaElectrica to set
     */
    public void setEnergiaElectrica(String energiaElectrica) {
        this.energiaElectrica = energiaElectrica;
    }

    /**
     * @return the redTelefono
     */
    public String getRedTelefono() {
        return redTelefono;
    }

    /**
     * @param redTelefono the redTelefono to set
     */
    public void setRedTelefono(String redTelefono) {
        this.redTelefono = redTelefono;
    }

    /**
     * @return the recoleBasura
     */
    public String getRecoleBasura() {
        return recoleBasura;
    }

    /**
     * @param recoleBasura the recoleBasura to set
     */
    public void setRecoleBasura(String recoleBasura) {
        this.recoleBasura = recoleBasura;
    }

    /**
     * @return the transportePublico
     */
    public String getTransportePublico() {
        return transportePublico;
    }

    /**
     * @param transportePublico the transportePublico to set
     */
    public void setTransportePublico(String transportePublico) {
        this.transportePublico = transportePublico;
    }

    /**
     * @return the internet
     */
    public String getInternet() {
        return internet;
    }

    /**
     * @param internet the internet to set
     */
    public void setInternet(String internet) {
        this.internet = internet;
    }

    /**
     * @return the otrosServicios
     */
    public String getOtrosServicios() {
        return otrosServicios;
    }

    /**
     * @param otrosServicios the otrosServicios to set
     */
    public void setOtrosServicios(String otrosServicios) {
        this.otrosServicios = otrosServicios;
    }

    /**
     * @return the areaConstruccion
     */
    public double getAreaConstruccion() {
        return areaConstruccion;
    }

    /**
     * @param areaConstruccion the areaConstruccion to set
     */
    public void setAreaConstruccion(double areaConstruccion) {
        this.areaConstruccion = areaConstruccion;
    }

    /**
     * @return the areaTotalConstruccion
     */
    public double getAreaTotalConstruccion() {
        return areaTotalConstruccion;
    }

    /**
     * @param areaTotalConstruccion the areaTotalConstruccion to set
     */
    public void setAreaTotalConstruccion(double areaTotalConstruccion) {
        this.areaTotalConstruccion = areaTotalConstruccion;
    }

    /**
     * @return the areaTotalTerreno
     */
    public double getAreaTotalTerreno() {
        return areaTotalTerreno;
    }

    /**
     * @param areaTotalTerreno the areaTotalTerreno to set
     */
    public void setAreaTotalTerreno(double areaTotalTerreno) {
        this.areaTotalTerreno = areaTotalTerreno;
    }

    /**
     * @return the patio
     */
    public String getPatio() {
        return patio;
    }

    /**
     * @param patio the patio to set
     */
    public void setPatio(String patio) {
        this.patio = patio;
    }

    /**
     * @return the piscina
     */
    public String getPiscina() {
        return piscina;
    }

    /**
     * @param piscina the piscina to set
     */
    public void setPiscina(String piscina) {
        this.piscina = piscina;
    }

    /**
     * @return the cerramiento
     */
    public String getCerramiento() {
        return cerramiento;
    }

    /**
     * @param cerramiento the cerramiento to set
     */
    public void setCerramiento(String cerramiento) {
        this.cerramiento = cerramiento;
    }

    /**
     * @return the cisterna
     */
    public String getCisterna() {
        return cisterna;
    }

    /**
     * @param cisterna the cisterna to set
     */
    public void setCisterna(String cisterna) {
        this.cisterna = cisterna;
    }

    /**
     * @return the muros
     */
    public String getMuros() {
        return muros;
    }

    /**
     * @param muros the muros to set
     */
    public void setMuros(String muros) {
        this.muros = muros;
    }

    /**
     * @return the ascensor
     */
    public String getAscensor() {
        return ascensor;
    }

    /**
     * @param ascensor the ascensor to set
     */
    public void setAscensor(String ascensor) {
        this.ascensor = ascensor;
    }

    /**
     * @return the numBanios
     */
    public int getNumBanios() {
        return numBanios;
    }

    /**
     * @param numBanios the numBanios to set
     */
    public void setNumBanios(int numBanios) {
        this.numBanios = numBanios;
    }

    /**
     * @return the linderoFrente1
     */
    public double getLinderoFrente1() {
        return linderoFrente1;
    }

    /**
     * @param linderoFrente1 the linderoFrente1 to set
     */
    public void setLinderoFrente1(double linderoFrente1) {
        this.linderoFrente1 = linderoFrente1;
    }

    /**
     * @return the linderoFrente2
     */
    public double getLinderoFrente2() {
        return linderoFrente2;
    }

    /**
     * @param linderoFrente2 the linderoFrente2 to set
     */
    public void setLinderoFrente2(double linderoFrente2) {
        this.linderoFrente2 = linderoFrente2;
    }

    /**
     * @return the linderoFrente3
     */
    public double getLinderoFrente3() {
        return linderoFrente3;
    }

    /**
     * @param linderoFrente3 the linderoFrente3 to set
     */
    public void setLinderoFrente3(double linderoFrente3) {
        this.linderoFrente3 = linderoFrente3;
    }

    /**
     * @return the linderoFrente4
     */
    public double getLinderoFrente4() {
        return linderoFrente4;
    }

    /**
     * @param linderoFrente4 the linderoFrente4 to set
     */
    public void setLinderoFrente4(double linderoFrente4) {
        this.linderoFrente4 = linderoFrente4;
    }

    /**
     * @return the linderoCalle1
     */
    public String getLinderoCalle1() {
        return linderoCalle1;
    }

    /**
     * @param linderoCalle1 the linderoCalle1 to set
     */
    public void setLinderoCalle1(String linderoCalle1) {
        this.linderoCalle1 = linderoCalle1;
    }

    /**
     * @return the linderoCalle2
     */
    public String getLinderoCalle2() {
        return linderoCalle2;
    }

    /**
     * @param linderoCalle2 the linderoCalle2 to set
     */
    public void setLinderoCalle2(String linderoCalle2) {
        this.linderoCalle2 = linderoCalle2;
    }

    /**
     * @return the linderoCalle3
     */
    public String getLinderoCalle3() {
        return linderoCalle3;
    }

    /**
     * @param linderoCalle3 the linderoCalle3 to set
     */
    public void setLinderoCalle3(String linderoCalle3) {
        this.linderoCalle3 = linderoCalle3;
    }

    /**
     * @return the linderoCalle4
     */
    public String getLinderoCalle4() {
        return linderoCalle4;
    }

    /**
     * @param linderoCalle4 the linderoCalle4 to set
     */
    public void setLinderoCalle4(String linderoCalle4) {
        this.linderoCalle4 = linderoCalle4;
    }

    /**
     * @return the linderoAlicuota1
     */
    public double getLinderoAlicuota1() {
        return linderoAlicuota1;
    }

    /**
     * @param linderoAlicuota1 the linderoAlicuota1 to set
     */
    public void setLinderoAlicuota1(double linderoAlicuota1) {
        this.linderoAlicuota1 = linderoAlicuota1;
    }

    /**
     * @return the linderoAlicuota2
     */
    public double getLinderoAlicuota2() {
        return linderoAlicuota2;
    }

    /**
     * @param linderoAlicuota2 the linderoAlicuota2 to set
     */
    public void setLinderoAlicuota2(double linderoAlicuota2) {
        this.linderoAlicuota2 = linderoAlicuota2;
    }

    /**
     * @return the linderoAlicuota3
     */
    public double getLinderoAlicuota3() {
        return linderoAlicuota3;
    }

    /**
     * @param linderoAlicuota3 the linderoAlicuota3 to set
     */
    public void setLinderoAlicuota3(double linderoAlicuota3) {
        this.linderoAlicuota3 = linderoAlicuota3;
    }

    /**
     * @return the linderoAlicuota4
     */
    public double getLinderoAlicuota4() {
        return linderoAlicuota4;
    }

    /**
     * @param linderoAlicuota4 the linderoAlicuota4 to set
     */
    public void setLinderoAlicuota4(double linderoAlicuota4) {
        this.linderoAlicuota4 = linderoAlicuota4;
    }

    /**
     * @return the derechosAcciones
     */
    public double getDerechosAcciones() {
        return derechosAcciones;
    }

    /**
     * @param derechosAcciones the derechosAcciones to set
     */
    public void setDerechosAcciones(double derechosAcciones) {
        this.derechosAcciones = derechosAcciones;
    }

    /**
     * @return the croquis
     */
    public String getCroquis() {
        return croquis;
    }

    /**
     * @param croquis the croquis to set
     */
    public void setCroquis(String croquis) {
        this.croquis = croquis;
    }

    /**
     * @return the emplazamiento
     */
    public String getEmplazamiento() {
        return emplazamiento;
    }

    /**
     * @param emplazamiento the emplazamiento to set
     */
    public void setEmplazamiento(String emplazamiento) {
        this.emplazamiento = emplazamiento;
    }

    /**
     * @return the fotografia
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia the fotografia to set
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * @return the topografiaRefTop
     */
    public String getTopografiaRefTop() {
        return topografiaRefTop;
    }

    /**
     * @param topografiaRefTop the topografiaRefTop to set
     */
    public void setTopografiaRefTop(String topografiaRefTop) {
        this.topografiaRefTop = topografiaRefTop;
    }

    /**
     * @return the fotoAerea
     */
    public String getFotoAerea() {
        return fotoAerea;
    }

    /**
     * @param fotoAerea the fotoAerea to set
     */
    public void setFotoAerea(String fotoAerea) {
        this.fotoAerea = fotoAerea;
    }

    /**
     * @return the otraRefTopo
     */
    public String getOtraRefTopo() {
        return otraRefTopo;
    }

    /**
     * @param otraRefTopo the otraRefTopo to set
     */
    public void setOtraRefTopo(String otraRefTopo) {
        this.otraRefTopo = otraRefTopo;
    }

    /**
     * @return the coordE
     */
    public double getCoordE() {
        return coordE;
    }

    /**
     * @param coordE the coordE to set
     */
    public void setCoordE(double coordE) {
        this.coordE = coordE;
    }

    /**
     * @return the coordN
     */
    public double getCoordN() {
        return coordN;
    }

    /**
     * @param coordN the coordN to set
     */
    public void setCoordN(double coordN) {
        this.coordN = coordN;
    }

    /**
     * @return the colindanteNorte
     */
    public String getColindanteNorte() {
        return colindanteNorte;
    }

    /**
     * @param colindanteNorte the colindanteNorte to set
     */
    public void setColindanteNorte(String colindanteNorte) {
        this.colindanteNorte = colindanteNorte;
    }

    /**
     * @return the colindanteEste
     */
    public String getColindanteEste() {
        return colindanteEste;
    }

    /**
     * @param colindanteEste the colindanteEste to set
     */
    public void setColindanteEste(String colindanteEste) {
        this.colindanteEste = colindanteEste;
    }

    /**
     * @return the colindanteSur
     */
    public String getColindanteSur() {
        return colindanteSur;
    }

    /**
     * @param colindanteSur the colindanteSur to set
     */
    public void setColindanteSur(String colindanteSur) {
        this.colindanteSur = colindanteSur;
    }

    /**
     * @return the colindanteOeste
     */
    public String getColindanteOeste() {
        return colindanteOeste;
    }

    /**
     * @param colindanteOeste the colindanteOeste to set
     */
    public void setColindanteOeste(String colindanteOeste) {
        this.colindanteOeste = colindanteOeste;
    }

    /**
     * @return the fuenteInformacion
     */
    public String getFuenteInformacion() {
        return fuenteInformacion;
    }

    /**
     * @param fuenteInformacion the fuenteInformacion to set
     */
    public void setFuenteInformacion(String fuenteInformacion) {
        this.fuenteInformacion = fuenteInformacion;
    }

    /**
     * @return the telefoInformacion
     */
    public String getTelefoInformacion() {
        return telefoInformacion;
    }

    /**
     * @param telefoInformacion the telefoInformacion to set
     */
    public void setTelefoInformacion(String telefoInformacion) {
        this.telefoInformacion = telefoInformacion;
    }

    /**
     * @return the fichaAdjunta
     */
    public String getFichaAdjunta() {
        return fichaAdjunta;
    }

    /**
     * @param fichaAdjunta the fichaAdjunta to set
     */
    public void setFichaAdjunta(String fichaAdjunta) {
        this.fichaAdjunta = fichaAdjunta;
    }

    /**
     * @return the fechaRegistrada
     */
    public Timestamp getFechaRegistrada() {
        return fechaRegistrada;
    }

    /**
     * @param fechaRegistrada the fechaRegistrada to set
     */
    public void setFechaRegistrada(Timestamp fechaRegistrada) {
        this.fechaRegistrada = fechaRegistrada;
    }

    /**
     * @return the fechaActualizacion
     */
    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the fechaBaja
     */
    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the estadoLogico
     */
    public String getEstadoLogico() {
        return estadoLogico;
    }

    /**
     * @param estadoLogico the estadoLogico to set
     */
    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    /**
     * @return the porcentaExcepsion
     */
    public double getPorcentaExcepsion() {
        return porcentaExcepsion;
    }

    /**
     * @param porcentaExcepsion the porcentaExcepsion to set
     */
    public void setPorcentaExcepsion(double porcentaExcepsion) {
        this.porcentaExcepsion = porcentaExcepsion;
    }

    /**
     * @return the excepsionEsp
     */
    public String getExcepsionEsp() {
        return excepsionEsp;
    }

    /**
     * @param excepsionEsp the excepsionEsp to set
     */
    public void setExcepsionEsp(String excepsionEsp) {
        this.excepsionEsp = excepsionEsp;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the sectorHomogeneo
     */
    public int getSectorHomogeneo() {
        return sectorHomogeneo;
    }

    /**
     * @param sectorHomogeneo the sectorHomogeneo to set
     */
    public void setSectorHomogeneo(int sectorHomogeneo) {
        this.sectorHomogeneo = sectorHomogeneo;
    }

    /**
     * @return the usuarioSistema
     */
    public Usuario getUsuarioSistema() {
        return usuarioSistema;
    }

    /**
     * @param usuarioSistema the usuarioSistema to set
     */
    public void setUsuarioSistema(Usuario usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    /**
     * @return the fechaModificacion
     */
    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the horaModificacion
     */
    public Time getHoraModificacion() {
        return horaModificacion;
    }

    /**
     * @param horaModificacion the horaModificacion to set
     */
    public void setHoraModificacion(Time horaModificacion) {
        this.horaModificacion = horaModificacion;
    }

    /**
     * @return the accionRealizada
     */
    public String getAccionRealizada() {
        return accionRealizada;
    }

    /**
     * @param accionRealizada the accionRealizada to set
     */
    public void setAccionRealizada(String accionRealizada) {
        this.accionRealizada = accionRealizada;
    }

   
    
        }
