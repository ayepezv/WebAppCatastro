package catastro.presentacion.beans;

import catastro.coeficientes.entidades.CoefAguaPotable;
import catastro.coeficientes.entidades.CoefAlcantarillado;
import catastro.coeficientes.entidades.CoefEnergiaElectrica;
import catastro.coeficientes.entidades.CoefLocalizacion;
import catastro.coeficientes.entidades.CoefMaterialVial;
import catastro.coeficientes.entidades.CoefTopografia;
import catastro.coeficientes.entidades.CoefUsoVias;
import catastro.coeficientes.entidades.SectorHomogeneo;
import catastro.coeficientes.funciones.FCoefAguaPotable;
import catastro.coeficientes.funciones.FCoefAlcantarillado;
import catastro.coeficientes.funciones.FCoefEnergiaElectrica;
import catastro.coeficientes.funciones.FCoefLocalizacion;
import catastro.coeficientes.funciones.FCoefMaterialVial;
import catastro.coeficientes.funciones.FCoefTopografia;
import catastro.coeficientes.funciones.FCoefUsoVias;
import catastro.logica.entidades.Barrio;
import catastro.logica.entidades.Calle;
import catastro.logica.entidades.Dominio;
import catastro.logica.entidades.ExencionEspecial;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.logica.servicios.ServiciosBarrios;
import catastro.logica.servicios.ServiciosCalles;
import catastro.logica.servicios.ServiciosDominio;
import catastro.logica.servicios.ServiciosExenciones;
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
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorEditarPredio implements Serializable {

    private String claveCatastral;
    private PredioV2 objPredio;
    private ArrayList<Barrio> lstBarrios;
    private ArrayList<Calle> lstCalles;
    private ArrayList<ExencionEspecial> lstExcenciones;
    private ArrayList<Dominio> lstDominios;
    private List<CoefLocalizacion> lstLocalizacion;
    private List<CoefTopografia> lstTopografia;
    private List<CoefUsoVias> lstUsoVias;
    private List<CoefMaterialVial> lstMaterialVial;
    private List<CoefAguaPotable> lstRedesAguaPotable;
    private List<CoefAlcantarillado> lstAlcantarilaldo;
    private List<CoefEnergiaElectrica> lstEnergElectrica;
    private List<SectorHomogeneo> lstSectoresHomogeneos;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    //<editor-fold defaultstate="collapsed" desc="Constructor del ManagedBean">      
    public ControladorEditarPredio() {
        objPredio = new PredioV2();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    //</editor-fold>

    @PostConstruct
    public void init() {
        obtenerSession();
        seleccionarBarrios();
        seleccionarCalles();
        seleccionarExcenEspeciales();
        seleccionarDominios();
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

    //<editor-fold defaultstate="collapsed" desc="Método para obtener un predio específico">   
    public void obtenerPredio() {
        try {
            setObjPredio(FuncionesPredio.obtenerPredioDadoClaveCatastral(getClaveCatastral()));
        } catch (Exception e) {
            //  Util.addErrorMessage("public void obtenerPredio() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
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

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar las exceciones especiales">    
    public void seleccionarExcenEspeciales() {
        try {
            this.setLstExcenciones(ServiciosExenciones.obtenerExenciones());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para obtener los coeficientes">    
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
    //</editor-fold>

    public void actualizar() {
        try {
            objPredio.setSessionUsuario(sessionUsuario);
            String msg = FuncionesPredio.actualizarPredio(objPredio);
            Util.addSuccessMessage(msg);
            objPredio=new PredioV2();
        } catch (Exception e) {
            System.out.println("public void actualizar() dice: " + e.getMessage());
            Util.addErrorMessage("public void actualizar() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Métodos getters y setters">       
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
     * @return the objPredio
     */
    public PredioV2 getObjPredio() {
        return objPredio;
    }

    /**
     * @param objPredio the objPredio to set
     */
    public void setObjPredio(PredioV2 objPredio) {
        this.objPredio = objPredio;
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
     * @return the lstSectoresHomogeneos
     */
    public List<SectorHomogeneo> getLstSectoresHomogeneos() {
        return lstSectoresHomogeneos;
    }

    /**
     * @param lstSectoresHomogeneos the lstSectoresHomogeneos to set
     */
    public void setLstSectoresHomogeneos(List<SectorHomogeneo> lstSectoresHomogeneos) {
        this.lstSectoresHomogeneos = lstSectoresHomogeneos;
    }
    //</editor-fold>

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
}
