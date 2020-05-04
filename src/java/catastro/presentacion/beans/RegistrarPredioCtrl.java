package catastro.presentacion.beans;

import catastro.coeficientes.entidades.CoefAguaPotable;
import catastro.coeficientes.entidades.CoefAlcantarillado;
import catastro.coeficientes.entidades.CoefEnergiaElectrica;
import catastro.coeficientes.entidades.CoefLocalizacion;
import catastro.coeficientes.entidades.CoefMaterialVial;
import catastro.coeficientes.entidades.CoefTopografia;
import catastro.coeficientes.entidades.CoefUsoVias;
import catastro.coeficientes.funciones.FCoefAguaPotable;
import catastro.coeficientes.funciones.FCoefAlcantarillado;
import catastro.coeficientes.funciones.FCoefEnergiaElectrica;
import catastro.coeficientes.funciones.FCoefLocalizacion;
import catastro.coeficientes.funciones.FCoefMaterialVial;
import catastro.coeficientes.funciones.FCoefTopografia;
import catastro.coeficientes.funciones.FCoefUsoVias;
import catastro.coeficientes.funciones.FSectorHomogeneo;
import catastro.gis.entidades.PredioGis;
import catastro.gis.funciones.FPredioGis;
import catastro.logica.entidades.Barrio;
import catastro.logica.entidades.Calle;
import catastro.logica.entidades.Dominio;
import catastro.logica.entidades.ExencionEspecial;
import catastro.logica.entidades.FormaAdquisicion;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FFormaAdquisicion;
import catastro.logica.servicios.FuncionesPredio;
import catastro.logica.servicios.ServiciosBarrios;
import catastro.logica.servicios.ServiciosCalles;
import catastro.logica.servicios.ServiciosDominio;
import catastro.logica.servicios.ServiciosExenciones;
import catastro.logica.servicios.ServiciosParroquia;
import catastro.logica.servicios.ServiciosPropietario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class RegistrarPredioCtrl implements Serializable {

    private PredioV2 objPredio;
    private PredioGis objPredioGis;
    private String txtClaveCatastral;
    private String txtCriterioPersona;
    private Usuario sessionUsuario;
    private Usuario propietarioSel;
    private Usuario repLegal;
    private List<PredioGis> lstPrediosGis;
    private ArrayList<Barrio> lstBarrios;
    private ArrayList<Calle> lstCalles;
    private ArrayList<Usuario> lstUsuarios;
    private ArrayList<ExencionEspecial> lstExcenciones;
    private ArrayList<Dominio> lstDominios;
    private List<CoefLocalizacion> lstLocalizacion;
    private List<CoefTopografia> lstTopografia;
    private List<CoefUsoVias> lstUsoVias;
    private List<CoefMaterialVial> lstMaterialVial;
    private List<CoefAguaPotable> lstRedesAguaPotable;
    private List<CoefAlcantarillado> lstAlcantarilaldo;
    private List<CoefEnergiaElectrica> lstEnergElectrica;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String estadoForm;
    private List<FormaAdquisicion> lstFormasAdquisicion;

    public RegistrarPredioCtrl() {
        estadoForm = "hidden";
        objPredio = new PredioV2();
        objPredioGis = new PredioGis();
        propietarioSel = new Usuario();
        repLegal = new Usuario();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        seleccionarBarrios();
        seleccionarCalles();
        seleccionarExcenEspeciales();
        seleccionarDominios();
        obtenerLocalizaciones();
        obtenerTopografias();
        obtenerUsoVias();
        obtenerMaterialVial();
        obtenerRedAgua();
        obtenerAlcantarillado();
        obtenerEnergElectrica();
        obtenerFormasAdquisicion();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerFormasAdquisicion() {
        try {
            setLstFormasAdquisicion(FFormaAdquisicion.obtenerFAdquisicion());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", e.getMessage()));
        }
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void buscarPredioGis() {
        try {
            Util.addSuccessMessage("Clave Catastral: " + txtClaveCatastral);
            lstPrediosGis = FPredioGis.obtenerPredioGisDadoClaveCatastral(txtClaveCatastral);
            Util.addSuccessMessage("total de predios " + lstPrediosGis.size());
        } catch (Exception e) {
            System.out.println("Error: public void buscarPredioGis() dice  " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void combinarPredios() {
        try {
            estadoForm = "visible";
            objPredio.setIdParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(objPredioGis.getParroquia().getIdParroquia()));
            objPredio.setZona((int) objPredioGis.getZona());
            objPredio.setSector((int) objPredioGis.getSector());
            objPredio.setManzana((int) objPredioGis.getManzana());
            objPredio.setNumPredio(objPredioGis.getNumPredio());
            objPredio.setClaveCatastral(objPredioGis.getClaveCatastral());
            objPredio.setClaveCatastralAnt(objPredioGis.getClaveCatastral());
            objPredio.setAreaTotalTerreno(objPredioGis.getArea());
            objPredio.setSectorHomogeneo(FSectorHomogeneo.obtenerSectorHomogeneoDadoCodigo(1));
            System.out.println("clave catastral: " + objPredio.getClaveCatastral());
            RequestContext.getCurrentInstance().update("frmPrincipal:otxtGisPredio");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgPredioEspacial').hide()");
            Util.addSuccessMessage(objPredioGis.getClaveCatastral());
        } catch (Exception e) {
            System.out.println("Error: public void combinarPredios() dice  " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar todos barrios">    
    public void seleccionarBarrios() {
        try {
            this.setLstBarrios(ServiciosBarrios.seleccionarBarrios());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println("Seleccionar barrios dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar las calles">    
    public void seleccionarCalles() {
        try {
            this.setLstCalles(ServiciosCalles.obtenerCalles());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccionar Calles dice: " + e.getMessage()));
            System.out.println("Seleccionar Calles dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para buscar un propietario dado un criterio de busqueda ">    
    public void buscarPropietarioDadoCriterio() {
        try {
            setLstUsuarios(ServiciosPropietario.encontrarPropietarios("A", getTxtCriterioPersona()));
        } catch (Exception e) {
            System.out.println(" public void buscarPropietarioDadoCriterio() dice: " + e.getMessage());
            Util.addErrorMessage(" public void buscarPropietarioDadoCriterio() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para registrar un nuevo propietario ">    
    public void insertarPropietario() {
        try {
            //  getPropietarioSel().setCedula(getTxtCriterioPersona());
            txtCriterioPersona = propietarioSel.getCedula();
            getPropietarioSel().setPassword(propietarioSel.getCedula());
            String msg = ServiciosPropietario.registrarPropietario(getPropietarioSel(), sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msg);
            setPropietarioSel(new Usuario());
            setPropietarioSel(ServiciosUsuario.buscarUsuarioDadoCedula(getTxtCriterioPersona()));
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoPropietario').hide()");
        } catch (Exception e) {
            System.out.println(" public void insertarPropietario() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarPropietario() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para registrar un nuevo representante legal ">    
    public void insertarRepLegal() {
        try {
            //  getRepLegal().setCedula(getTxtCriterioPersona());
            txtCriterioPersona = repLegal.getCedula();
            getRepLegal().setPassword(repLegal.getCedula());
            String msg = ServiciosPropietario.registrarPropietario(getRepLegal(), sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msg);
            setRepLegal(new Usuario());
            setRepLegal(ServiciosUsuario.buscarUsuarioDadoCedula(getTxtCriterioPersona()));
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoRepLegal').hide()");
        } catch (Exception e) {
            System.out.println(" public void insertarRepLegal() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarRepLegal() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar las exceciones especiales">    
    public void seleccionarExcenEspeciales() {
        try {
            this.setLstExcenciones(ServiciosExenciones.obtenerExenciones());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar los dominios">    
    public void seleccionarDominios() {
        try {
            this.setLstDominios(ServiciosDominio.seleccionarDominios());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    public void obtenerLocalizaciones() {
        try {
            this.setLstLocalizacion(FCoefLocalizacion.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerLocalizaciones() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerTopografias() {
        try {
            setLstTopografia(FCoefTopografia.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerTopografias() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerUsoVias() {
        try {
            this.setLstUsoVias(FCoefUsoVias.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerUsoVias() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerMaterialVial() {
        try {
            this.setLstMaterialVial(FCoefMaterialVial.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerMaterialVial() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerRedAgua() {
        try {
            this.setLstRedesAguaPotable(FCoefAguaPotable.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerRedAgua() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAlcantarillado() {
        try {
            this.setLstAlcantarilaldo(FCoefAlcantarillado.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerAlcantarillado() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerEnergElectrica() {
        try {
            this.setLstEnergElectrica(FCoefEnergiaElectrica.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerEnergElectrica() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Método para insertar un predio">    
    public void insertarPredio() {
        try {
            getObjPredio().setSessionUsuario(getSessionUsuario());
            getObjPredio().setRepLegal(getRepLegal());
            System.out.println("Representante legal: " + getRepLegal().getNombres());
            getObjPredio().setPropietario(getPropietarioSel());
            System.out.println("Propietario: " + getPropietarioSel().getApellidos());
            System.out.println("Fotografia: " + getObjPredio().getFotografia());
            System.out.println("Ficha : " + getObjPredio().getFichaAdjunta());
            System.out.println("Croquis: " + getObjPredio().getCroquis());
            System.out.println("Emplazamiento: " + getObjPredio().getEmplazamiento());
            String msg = FuncionesPredio.insertarPredio(getObjPredio());
            Util.addSuccessMessage(msg);
            setObjPredio(new PredioV2());
            objPredioGis = new PredioGis();
            propietarioSel = new Usuario();
            repLegal = new Usuario();
            estadoForm = "hidden";
        } catch (Exception e) {
            System.out.println("Insertar predio dice: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    //</editor-fold>

    public PredioV2 getObjPredio() {
        return objPredio;
    }

    public void setObjPredio(PredioV2 objPredio) {
        this.objPredio = objPredio;
    }

    public PredioGis getObjPredioGis() {
        return objPredioGis;
    }

    public void setObjPredioGis(PredioGis objPredioGis) {
        this.objPredioGis = objPredioGis;
    }

    public String getTxtClaveCatastral() {
        return txtClaveCatastral;
    }

    public void setTxtClaveCatastral(String txtClaveCatastral) {
        this.txtClaveCatastral = txtClaveCatastral;
    }

    public List<PredioGis> getLstPrediosGis() {
        return lstPrediosGis;
    }

    public void setLstPrediosGis(List<PredioGis> lstPrediosGis) {
        this.lstPrediosGis = lstPrediosGis;
    }

    /**
     * @return the lstBarrios
     */
    public ArrayList<Barrio> getLstBarrios() {
        return lstBarrios;
    }

    /**
     * @param lstBarrios the lstBarrios to set
     */
    public void setLstBarrios(ArrayList<Barrio> lstBarrios) {
        this.lstBarrios = lstBarrios;
    }

    /**
     * @return the lstCalles
     */
    public ArrayList<Calle> getLstCalles() {
        return lstCalles;
    }

    /**
     * @param lstCalles the lstCalles to set
     */
    public void setLstCalles(ArrayList<Calle> lstCalles) {
        this.lstCalles = lstCalles;
    }

    /**
     * @return the txtCriterioPersona
     */
    public String getTxtCriterioPersona() {
        return txtCriterioPersona;
    }

    /**
     * @param txtCriterioPersona the txtCriterioPersona to set
     */
    public void setTxtCriterioPersona(String txtCriterioPersona) {
        this.txtCriterioPersona = txtCriterioPersona;
    }

    /**
     * @return the lstUsuarios
     */
    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    /**
     * @param lstUsuarios the lstUsuarios to set
     */
    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    /**
     * @return the propietarioSel
     */
    public Usuario getPropietarioSel() {
        return propietarioSel;
    }

    /**
     * @param propietarioSel the propietarioSel to set
     */
    public void setPropietarioSel(Usuario propietarioSel) {
        this.propietarioSel = propietarioSel;
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
     * @return the lstExcenciones
     */
    public ArrayList<ExencionEspecial> getLstExcenciones() {
        return lstExcenciones;
    }

    /**
     * @param lstExcenciones the lstExcenciones to set
     */
    public void setLstExcenciones(ArrayList<ExencionEspecial> lstExcenciones) {
        this.lstExcenciones = lstExcenciones;
    }

    /**
     * @return the lstDominios
     */
    public ArrayList<Dominio> getLstDominios() {
        return lstDominios;
    }

    /**
     * @param lstDominios the lstDominios to set
     */
    public void setLstDominios(ArrayList<Dominio> lstDominios) {
        this.lstDominios = lstDominios;
    }

    /**
     * @return the lstLocalizacion
     */
    public List<CoefLocalizacion> getLstLocalizacion() {
        return lstLocalizacion;
    }

    /**
     * @param lstLocalizacion the lstLocalizacion to set
     */
    public void setLstLocalizacion(List<CoefLocalizacion> lstLocalizacion) {
        this.lstLocalizacion = lstLocalizacion;
    }

    /**
     * @return the lstTopografia
     */
    public List<CoefTopografia> getLstTopografia() {
        return lstTopografia;
    }

    /**
     * @param lstTopografia the lstTopografia to set
     */
    public void setLstTopografia(List<CoefTopografia> lstTopografia) {
        this.lstTopografia = lstTopografia;
    }

    /**
     * @return the lstUsoVias
     */
    public List<CoefUsoVias> getLstUsoVias() {
        return lstUsoVias;
    }

    /**
     * @param lstUsoVias the lstUsoVias to set
     */
    public void setLstUsoVias(List<CoefUsoVias> lstUsoVias) {
        this.lstUsoVias = lstUsoVias;
    }

    /**
     * @return the lstMaterialVial
     */
    public List<CoefMaterialVial> getLstMaterialVial() {
        return lstMaterialVial;
    }

    /**
     * @param lstMaterialVial the lstMaterialVial to set
     */
    public void setLstMaterialVial(List<CoefMaterialVial> lstMaterialVial) {
        this.lstMaterialVial = lstMaterialVial;
    }

    /**
     * @return the lstRedesAguaPotable
     */
    public List<CoefAguaPotable> getLstRedesAguaPotable() {
        return lstRedesAguaPotable;
    }

    /**
     * @param lstRedesAguaPotable the lstRedesAguaPotable to set
     */
    public void setLstRedesAguaPotable(List<CoefAguaPotable> lstRedesAguaPotable) {
        this.lstRedesAguaPotable = lstRedesAguaPotable;
    }

    /**
     * @return the lstAlcantarilaldo
     */
    public List<CoefAlcantarillado> getLstAlcantarilaldo() {
        return lstAlcantarilaldo;
    }

    /**
     * @param lstAlcantarilaldo the lstAlcantarilaldo to set
     */
    public void setLstAlcantarilaldo(List<CoefAlcantarillado> lstAlcantarilaldo) {
        this.lstAlcantarilaldo = lstAlcantarilaldo;
    }

    /**
     * @return the lstEnergElectrica
     */
    public List<CoefEnergiaElectrica> getLstEnergElectrica() {
        return lstEnergElectrica;
    }

    /**
     * @param lstEnergElectrica the lstEnergElectrica to set
     */
    public void setLstEnergElectrica(List<CoefEnergiaElectrica> lstEnergElectrica) {
        this.lstEnergElectrica = lstEnergElectrica;
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
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @param faceContext the faceContext to set
     */
    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    public String getEstadoForm() {
        return estadoForm;
    }

    public void setEstadoForm(String estadoForm) {
        this.estadoForm = estadoForm;
    }

    /**
     * @return the lstFormasAdquisicion
     */
    public List<FormaAdquisicion> getLstFormasAdquisicion() {
        return lstFormasAdquisicion;
    }

    /**
     * @param lstFormasAdquisicion the lstFormasAdquisicion to set
     */
    public void setLstFormasAdquisicion(List<FormaAdquisicion> lstFormasAdquisicion) {
        this.lstFormasAdquisicion = lstFormasAdquisicion;
    }

}
