package catastro.presentacion.beans;

import catastro.logica.entidades.Barrio;
import catastro.logica.entidades.Calle;
import catastro.logica.entidades.FormaAdquisicion;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FFormaAdquisicion;
import catastro.logica.servicios.FuncionesPredio;
import catastro.logica.servicios.ServiciosBarrios;
import catastro.logica.servicios.ServiciosCalles;
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
import recursos.Util;

@ManagedBean
@ViewScoped
public class EditarPredioCtrl implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private PredioV2 objPredio;
    private Usuario propietarioSel;
    private Usuario repLegal;
    private ArrayList<PredioV2> lstPredios;
    private ArrayList<Barrio> lstBarrios;
    private ArrayList<Calle> lstCalles;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private int band;
    private String cedula;
    private String estadoForm;
    private List<FormaAdquisicion> lstFormasAdquisicion;

    public EditarPredioCtrl() {
        estadoForm = "hidden";
        objPredio = new PredioV2();
        sessionUsuario = new Usuario();
        propietarioSel = new Usuario();
        repLegal = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

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

    public void cambiarEstadoBand() {
        setEstadoForm("visible");
        obtenerFormasAdquisicion();
        seleccionarBarrios();
        seleccionarCalles();
    }

    public void buscarPredios() {
        try {
            //String strClave = String.valueOf(zona) + String.valueOf(sector) + String.valueOf(manzana) + String.valueOf(numPredio);
            String strClave = "";
            String strZona = "";
            String strSector = "";
            String strManzana = "";
            String strPredio = "";

            if ((getZona() / 10) < 1) {
                strZona = "0" + String.valueOf(getZona());
            } else {
                strZona = String.valueOf(getZona());
            }

            if ((getSector() / 10) < 1) {
                strSector = "0" + String.valueOf(getSector());
            } else {
                strSector = String.valueOf(getSector());
            }

            if ((getManzana() / 10) < 1) {
                strManzana = "00" + String.valueOf(getManzana());
            } else {
                strManzana = "0" + String.valueOf(getManzana());
            }

            if ((getNumPredio() / 10) < 1) {
                strPredio = "00" + String.valueOf(getNumPredio());
            } else {
                strPredio = "0" + String.valueOf(getNumPredio());
            }

            strClave = strZona + strSector + strManzana + strPredio;

            //this.setLstPredios(FuncionesPredio.encontrarPredio(strClave));
            lstPredios = FuncionesPredio.encontrarPrediosDadoZonaSectorManzana(zona, sector, manzana, numPredio);

            Util.addSuccessMessage(strClave);
            Util.addSuccessMessage("Predios encontrados: " + getLstPredios().size());
            System.out.println("Clave catastral: " + strClave);
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void obtenerPredioPorCedula() {
        try {
            setLstPredios(FuncionesPredio.encontrarPredio(getCedula()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(getLstPredios().size()), " predios obtenidos."));
        } catch (Exception e) {
            System.out.println("public void obtenerPredioPorCedula() dice: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", e.getMessage()));
        }
    }

    public void limpiar() {
        try {
            this.setLstPredios(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
            setEstadoForm("hidden");
            objPredio = new PredioV2();
            //band = 0;
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
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

    public void editarPredio() {
        try {
            //objPredio.setPropietario(propietarioSel);
            // objPredio.setRepLegal(repLegal);
            System.out.println("Propietario: " + getObjPredio().getPropietario().getApellidos());
            System.out.println("Rep legal: " + getObjPredio().getRepLegal().getApellidos());

            getObjPredio().setSessionUsuario(getSessionUsuario());

            String msg = FuncionesPredio.editarPredio(getObjPredio());
            Util.addSuccessMessage(msg);

            setObjPredio(new PredioV2());
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "public void editarPredio() dice: " + e.getMessage()));
            System.out.println("public void editarPredio() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Método para registrar un nuevo propietario ">    
    public void insertarPropietario() {
        try {
            getObjPredio().getPropietario().setPassword(getObjPredio().getPropietario().getCedula());
            String txtCedula = getObjPredio().getPropietario().getCedula();

            String msg = ServiciosPropietario.registrarPropietario(getObjPredio().getPropietario(), getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msg);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoPropietario').hide()");

            setPropietarioSel(ServiciosUsuario.buscarUsuarioDadoCedula(txtCedula));
            getObjPredio().setPropietario(getPropietarioSel());
        } catch (Exception e) {
            System.out.println(" public void insertarPropietario() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarPropietario() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para registrar un nuevo representante legal ">    
    public void insertarRepLegal() {
        try {
            getObjPredio().getRepLegal().setPassword(getObjPredio().getRepLegal().getCedula());
            String strCedula = getObjPredio().getRepLegal().getCedula();

            String msg = ServiciosPropietario.registrarPropietario(getObjPredio().getRepLegal(), getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msg);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoRepLegal').hide()");

            setRepLegal(ServiciosUsuario.buscarUsuarioDadoCedula(strCedula));

            getObjPredio().setRepLegal(getRepLegal());
        } catch (Exception e) {
            System.out.println(" public void insertarRepLegal() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarRepLegal() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

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
     * @return the lstPredios
     */
    public ArrayList<PredioV2> getLstPredios() {
        return lstPredios;
    }

    /**
     * @param lstPredios the lstPredios to set
     */
    public void setLstPredios(ArrayList<PredioV2> lstPredios) {
        this.lstPredios = lstPredios;
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
     * @return the band
     */
    public int getBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(int band) {
        this.band = band;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the estadoForm
     */
    public String getEstadoForm() {
        return estadoForm;
    }

    /**
     * @param estadoForm the estadoForm to set
     */
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
