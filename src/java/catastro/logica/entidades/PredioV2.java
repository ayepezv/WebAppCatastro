package catastro.logica.entidades;

import catastro.coeficientes.entidades.SectorHomogeneo;
import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

public class PredioV2 {

    private int idPredio;
    private int numFicha;
    private Parroquia idParroquia;
    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private int propHorBloque;
    private int propHorPiso;
    private int propHorUnidad;
    private String claveCatastral;
    private String claveCatastralAnt;
    private String barrio;
    private String callePrinc;
    private String calleSecund;
    private String numCasa;
    private String nombreEdificio;
    private Usuario propietario;
    private Usuario repLegal;
    private Dominio dominio;
    private String condicion;
    private String formaAdq;
    private String notaria;
    private Date fechaTituloProp;
    private Date fechaRegistroPredio;
    private double areaEscritura;
    private String propAnterior;
    private double avaluoProp;
    private String usoSueloGestion;
    private String usoSueloConsumo;
    private String usoSuelIntercambio;
    private String usoSuelProduc;
    private String ocupacionPredio;
    private String localizacionPredio;
    private String topografiaPredio;
    private String formaPredio;
    private String caracteristSuelo;
    private String usoVias;
    private String materialVias;
    private String redAgua;
    private String alcantarillado;
    private String energElectric;
    private String redTelefon;
    private String recBasura;
    private String transpPublico;
    private String internet;
    private String otrosServicios;
    private double areaConstruc;
    private double areaTotalConst;
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
    private String topografiaRefTopog;
    private String fotoAerea;
    private String otraRefTopog;
    private double coordenadaE;
    private double coordenadaN;
    private String colindantNorte;
    private String colindantEste;
    private String colindantSur;
    private String colindantOeste;
    private String fuenteInformante;
    private String telfInformante;
    private String fichaAdjunta;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private String estadoLogico;
    private double porcentajeExcencion;
    private String excencionEspecial;
    private String observaciones;
    private SectorHomogeneo sectorHomogeneo;
    private Usuario sessionUsuario;
    private Boolean legalizado;
    private String observacionesGenerales;
    private String planimetria;
    private double fondoRelativo;
    private String sustentoUpdate;
    private String sustentoDelete;

    public PredioV2() {
        idParroquia = new Parroquia();
        dominio = new Dominio();
        propietario = new Usuario();
        repLegal = new Usuario();
        sectorHomogeneo = new SectorHomogeneo();
        sessionUsuario = new Usuario();
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
    public Parroquia getIdParroquia() {
        return idParroquia;
    }

    /**
     * @param idParroquia the idParroquia to set
     */
    public void setIdParroquia(Parroquia idParroquia) {
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
     * @return the propHorBloque
     */
    public int getPropHorBloque() {
        return propHorBloque;
    }

    /**
     * @param propHorBloque the propHorBloque to set
     */
    public void setPropHorBloque(int propHorBloque) {
        this.propHorBloque = propHorBloque;
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
     * @return the callePrinc
     */
    public String getCallePrinc() {
        return callePrinc;
    }

    /**
     * @param callePrinc the callePrinc to set
     */
    public void setCallePrinc(String callePrinc) {
        this.callePrinc = callePrinc;
    }

    /**
     * @return the calleSecund
     */
    public String getCalleSecund() {
        return calleSecund;
    }

    /**
     * @param calleSecund the calleSecund to set
     */
    public void setCalleSecund(String calleSecund) {
        this.calleSecund = calleSecund;
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
     * @return the propietario
     */
    public Usuario getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    /**
     * @return the repLegal
     */
    public Usuario getRepLegal() {
        return repLegal;
    }

    /**
     * @param repLegal the repLegal to set
     */
    public void setRepLegal(Usuario repLegal) {
        this.repLegal = repLegal;
    }

    /**
     * @return the dominio
     */
    public Dominio getDominio() {
        return dominio;
    }

    /**
     * @param dominio the dominio to set
     */
    public void setDominio(Dominio dominio) {
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
     * @return the formaAdq
     */
    public String getFormaAdq() {
        return formaAdq;
    }

    /**
     * @param formaAdq the formaAdq to set
     */
    public void setFormaAdq(String formaAdq) {
        this.formaAdq = formaAdq;
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
     * @return the fechaRegistroPredio
     */
    public Date getFechaRegistroPredio() {
        return fechaRegistroPredio;
    }

    /**
     * @param fechaRegistroPredio the fechaRegistroPredio to set
     */
    public void setFechaRegistroPredio(Date fechaRegistroPredio) {
        this.fechaRegistroPredio = fechaRegistroPredio;
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
     * @return the propAnterior
     */
    public String getPropAnterior() {
        return propAnterior;
    }

    /**
     * @param propAnterior the propAnterior to set
     */
    public void setPropAnterior(String propAnterior) {
        this.propAnterior = propAnterior;
    }

    /**
     * @return the avaluoProp
     */
    public double getAvaluoProp() {
        return avaluoProp;
    }

    /**
     * @param avaluoProp the avaluoProp to set
     */
    public void setAvaluoProp(double avaluoProp) {
        this.avaluoProp = avaluoProp;
    }

    /**
     * @return the usoSueloGestion
     */
    public String getUsoSueloGestion() {
        return usoSueloGestion;
    }

    /**
     * @param usoSueloGestion the usoSueloGestion to set
     */
    public void setUsoSueloGestion(String usoSueloGestion) {
        this.usoSueloGestion = usoSueloGestion;
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
     * @return the usoSuelIntercambio
     */
    public String getUsoSuelIntercambio() {
        return usoSuelIntercambio;
    }

    /**
     * @param usoSuelIntercambio the usoSuelIntercambio to set
     */
    public void setUsoSuelIntercambio(String usoSuelIntercambio) {
        this.usoSuelIntercambio = usoSuelIntercambio;
    }

    /**
     * @return the usoSuelProduc
     */
    public String getUsoSuelProduc() {
        return usoSuelProduc;
    }

    /**
     * @param usoSuelProduc the usoSuelProduc to set
     */
    public void setUsoSuelProduc(String usoSuelProduc) {
        this.usoSuelProduc = usoSuelProduc;
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
     * @return the caracteristSuelo
     */
    public String getCaracteristSuelo() {
        return caracteristSuelo;
    }

    /**
     * @param caracteristSuelo the caracteristSuelo to set
     */
    public void setCaracteristSuelo(String caracteristSuelo) {
        this.caracteristSuelo = caracteristSuelo;
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
     * @return the materialVias
     */
    public String getMaterialVias() {
        return materialVias;
    }

    /**
     * @param materialVias the materialVias to set
     */
    public void setMaterialVias(String materialVias) {
        this.materialVias = materialVias;
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
     * @return the energElectric
     */
    public String getEnergElectric() {
        return energElectric;
    }

    /**
     * @param energElectric the energElectric to set
     */
    public void setEnergElectric(String energElectric) {
        this.energElectric = energElectric;
    }

    /**
     * @return the redTelefon
     */
    public String getRedTelefon() {
        return redTelefon;
    }

    /**
     * @param redTelefon the redTelefon to set
     */
    public void setRedTelefon(String redTelefon) {
        this.redTelefon = redTelefon;
    }

    /**
     * @return the recBasura
     */
    public String getRecBasura() {
        return recBasura;
    }

    /**
     * @param recBasura the recBasura to set
     */
    public void setRecBasura(String recBasura) {
        this.recBasura = recBasura;
    }

    /**
     * @return the transpPublico
     */
    public String getTranspPublico() {
        return transpPublico;
    }

    /**
     * @param transpPublico the transpPublico to set
     */
    public void setTranspPublico(String transpPublico) {
        this.transpPublico = transpPublico;
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
     * @return the areaConstruc
     */
    public double getAreaConstruc() {
        return areaConstruc;
    }

    /**
     * @param areaConstruc the areaConstruc to set
     */
    public void setAreaConstruc(double areaConstruc) {
        this.areaConstruc = areaConstruc;
    }

    /**
     * @return the areaTotalConst
     */
    public double getAreaTotalConst() {
        return areaTotalConst;
    }

    /**
     * @param areaTotalConst the areaTotalConst to set
     */
    public void setAreaTotalConst(double areaTotalConst) {
        this.areaTotalConst = areaTotalConst;
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
     * @return the topografiaRefTopog
     */
    public String getTopografiaRefTopog() {
        return topografiaRefTopog;
    }

    /**
     * @param topografiaRefTopog the topografiaRefTopog to set
     */
    public void setTopografiaRefTopog(String topografiaRefTopog) {
        this.topografiaRefTopog = topografiaRefTopog;
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
     * @return the otraRefTopog
     */
    public String getOtraRefTopog() {
        return otraRefTopog;
    }

    /**
     * @param otraRefTopog the otraRefTopog to set
     */
    public void setOtraRefTopog(String otraRefTopog) {
        this.otraRefTopog = otraRefTopog;
    }

    /**
     * @return the coordenadaE
     */
    public double getCoordenadaE() {
        return coordenadaE;
    }

    /**
     * @param coordenadaE the coordenadaE to set
     */
    public void setCoordenadaE(double coordenadaE) {
        this.coordenadaE = coordenadaE;
    }

    /**
     * @return the coordenadaN
     */
    public double getCoordenadaN() {
        return coordenadaN;
    }

    /**
     * @param coordenadaN the coordenadaN to set
     */
    public void setCoordenadaN(double coordenadaN) {
        this.coordenadaN = coordenadaN;
    }

    /**
     * @return the colindantNorte
     */
    public String getColindantNorte() {
        return colindantNorte;
    }

    /**
     * @param colindantNorte the colindantNorte to set
     */
    public void setColindantNorte(String colindantNorte) {
        this.colindantNorte = colindantNorte;
    }

    /**
     * @return the colindantEste
     */
    public String getColindantEste() {
        return colindantEste;
    }

    /**
     * @param colindantEste the colindantEste to set
     */
    public void setColindantEste(String colindantEste) {
        this.colindantEste = colindantEste;
    }

    /**
     * @return the colindantSur
     */
    public String getColindantSur() {
        return colindantSur;
    }

    /**
     * @param colindantSur the colindantSur to set
     */
    public void setColindantSur(String colindantSur) {
        this.colindantSur = colindantSur;
    }

    /**
     * @return the colindantOeste
     */
    public String getColindantOeste() {
        return colindantOeste;
    }

    /**
     * @param colindantOeste the colindantOeste to set
     */
    public void setColindantOeste(String colindantOeste) {
        this.colindantOeste = colindantOeste;
    }

    /**
     * @return the fuenteInformante
     */
    public String getFuenteInformante() {
        return fuenteInformante;
    }

    /**
     * @param fuenteInformante the fuenteInformante to set
     */
    public void setFuenteInformante(String fuenteInformante) {
        this.fuenteInformante = fuenteInformante;
    }

    /**
     * @return the telfInformante
     */
    public String getTelfInformante() {
        return telfInformante;
    }

    /**
     * @param telfInformante the telfInformante to set
     */
    public void setTelfInformante(String telfInformante) {
        this.telfInformante = telfInformante;
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
     * @return the fechaRegistro
     */
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
     * @return the porcentajeExcencion
     */
    public double getPorcentajeExcencion() {
        return porcentajeExcencion;
    }

    /**
     * @param porcentajeExcencion the porcentajeExcencion to set
     */
    public void setPorcentajeExcencion(double porcentajeExcencion) {
        this.porcentajeExcencion = porcentajeExcencion;
    }

    /**
     * @return the excencionEspecial
     */
    public String getExcencionEspecial() {
        return excencionEspecial;
    }

    /**
     * @param excencionEspecial the excencionEspecial to set
     */
    public void setExcencionEspecial(String excencionEspecial) {
        this.excencionEspecial = excencionEspecial;
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
    public SectorHomogeneo getSectorHomogeneo() {
        return sectorHomogeneo;
    }

    /**
     * @param sectorHomogeneo the sectorHomogeneo to set
     */
    public void setSectorHomogeneo(SectorHomogeneo sectorHomogeneo) {
        this.sectorHomogeneo = sectorHomogeneo;
    }

    /**
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the legalizado
     */
    public Boolean getLegalizado() {
        return legalizado;
    }

    /**
     * @param legalizado the legalizado to set
     */
    public void setLegalizado(Boolean legalizado) {
        this.legalizado = legalizado;
    }

    /**
     * @return the observacionesGenerales
     */
    public String getObservacionesGenerales() {
        return observacionesGenerales;
    }

    /**
     * @param observacionesGenerales the observacionesGenerales to set
     */
    public void setObservacionesGenerales(String observacionesGenerales) {
        this.observacionesGenerales = observacionesGenerales;
    }

    /**
     * @return the planimetria
     */
    public String getPlanimetria() {
        return planimetria;
    }

    /**
     * @param planimetria the planimetria to set
     */
    public void setPlanimetria(String planimetria) {
        this.planimetria = planimetria;
    }

    /**
     * @return the fondoRelativo
     */
    public double getFondoRelativo() {
        return fondoRelativo;
    }

    /**
     * @param fondoRelativo the fondoRelativo to set
     */
    public void setFondoRelativo(double fondoRelativo) {
        this.fondoRelativo = fondoRelativo;
    }

    /**
     * @return the sustentoUpdate
     */
    public String getSustentoUpdate() {
        return sustentoUpdate;
    }

    /**
     * @param sustentoUpdate the sustentoUpdate to set
     */
    public void setSustentoUpdate(String sustentoUpdate) {
        this.sustentoUpdate = sustentoUpdate;
    }

    /**
     * @return the sustentoDelete
     */
    public String getSustentoDelete() {
        return sustentoDelete;
    }

    /**
     * @param sustentoDelete the sustentoDelete to set
     */
    public void setSustentoDelete(String sustentoDelete) {
        this.sustentoDelete = sustentoDelete;
    }

}
