package catastro.presentacion.beans;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtEmisionIndividual implements Serializable {

    private int numZona;
    private int numSector;
    private int numManzana;
    private int numPredio;
    private int anio;
    private ArrayList<PredioV2> lstPredios;
    private List<Titulo> lstTitPendientes;
    private PredioV2 objPredio;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtEmisionIndividual() {
        objPredio = new PredioV2();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void buscarPredios() {
        try {
            setLstPredios(FuncionesPredio.encontrarPrediosDadoZonaSectorManzana(getNumZona(), getNumSector(), getNumManzana(), getNumPredio()));
            Util.addSuccessMessage(getLstPredios().size() + " predios obtenidos.");
        } catch (Exception e) {
            System.out.println("public void buscarPredios() dice: " + e.getMessage());
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void inicializar() {
        try {
            setAnio(0);
            setLstPredios(new ArrayList<>());
            setNumZona(0);
            setNumSector(0);
            setNumManzana(0);
            setNumPredio(0);
        } catch (Exception e) {
            System.out.println("public void inicializar() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void buscarTitulos() {
        try {
            Util.addSuccessMessage("Predio seleccionado: " + getObjPredio().getClaveCatastral());
            setLstTitPendientes(FTitulos.consultarValoresPagarDadoPredio(getObjPredio().getIdPredio()));
        } catch (Exception e) {
            System.out.println("public void seleccionar() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void emisionIndividual() {
        try {
            String msg = FTitulos.emisionIndividual(getObjPredio().getIdPredio(), getAnio(), getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msg);
            buscarTitulos();
            resetearFitrosTabla("frmPrincipal:tblTitulos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevaEmision').hide()");            
        } catch (Exception e) {
            System.out.println("public void emisionIndividual() dice: " + e.getMessage());
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El Título para este año ya ha sido emitido.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
            //Util.addErrorMessage("El Título para el año "+anio+" ya ha sido emitido.");
            Util.addWarmingMessage("El Título para el año "+anio+" ya ha sido emitido.");
        }
    }
    
    public void emisionIndividualCalcular() {
        try {
            String msg = FTitulos.emisionIndividualCalcular(getObjPredio().getIdPredio(), getAnio(), getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msg);
            buscarTitulos();
            resetearFitrosTabla("frmPrincipal:tblTitulos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevaEmision').hide()");
        } catch (Exception e) {
            /*System.out.println("public void emisionIndividual() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CALCULOS", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);*/
            Util.addErrorMessage(e.getMessage().replace("ERROR:", "Mensaje: ").replace("Hint:", ""));
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
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

    /**
     * @return the numZona
     */
    public int getNumZona() {
        return numZona;
    }

    /**
     * @param numZona the numZona to set
     */
    public void setNumZona(int numZona) {
        this.numZona = numZona;
    }

    /**
     * @return the numSector
     */
    public int getNumSector() {
        return numSector;
    }

    /**
     * @param numSector the numSector to set
     */
    public void setNumSector(int numSector) {
        this.numSector = numSector;
    }

    /**
     * @return the numManzana
     */
    public int getNumManzana() {
        return numManzana;
    }

    /**
     * @param numManzana the numManzana to set
     */
    public void setNumManzana(int numManzana) {
        this.numManzana = numManzana;
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
     * @return the lstTitPendientes
     */
    public List<Titulo> getLstTitPendientes() {
        return lstTitPendientes;
    }

    /**
     * @param lstTitPendientes the lstTitPendientes to set
     */
    public void setLstTitPendientes(List<Titulo> lstTitPendientes) {
        this.lstTitPendientes = lstTitPendientes;
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
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

}
