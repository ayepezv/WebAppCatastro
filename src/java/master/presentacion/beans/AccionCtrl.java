package master.presentacion.beans;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Accion;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosAccion;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class AccionCtrl {

    private ArrayList<Accion> lstAcciones;
    private Accion accion;
    private Accion accionSel;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public AccionCtrl() {
        accion = new Accion();
        accionSel = new Accion();
        lstAcciones = new ArrayList<>();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerAcciones();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerAcciones() {
        try {
            //lstAcciones = ServiciosAccion.obtenerAcciones();
            lstAcciones = ServiciosAccion.obtenerAccionesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerAcciones() dice: " + e.getMessage());
        }
    }

    public void obtenerAccionesActivas() {
        try {
            lstAcciones = ServiciosAccion.obtenerAccionesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerAcciones() dice: " + e.getMessage());
        }
    }

    public void registrar() {
        try {
            accion.setSessionUsuario(sessionUsuario);

            String respuesta = ServiciosAccion.registrarAlerta(accion);
            Util.addSuccessMessage(respuesta);
            obtenerAcciones();
            resetearFitrosTabla("frmAcciones:tblAcciones");
            accion = new Accion();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }

    public void actualizar() {
        try {
            accionSel.setSessionUsuario(sessionUsuario);
            String respuesta = ServiciosAccion.actualizarAlerta(accionSel);
            Util.addSuccessMessage(respuesta);
            obtenerAcciones();
            resetearFitrosTabla("frmAcciones:tblAcciones");
            accionSel = new Accion();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizar() dice: " + e.getMessage());
        }
    }

    public void eliminar() {
        try {
            accionSel.setSessionUsuario(sessionUsuario);
            String respuesta = ServiciosAccion.eliminarAccion(accionSel);
            Util.addSuccessMessage(respuesta);
            obtenerAcciones();
            resetearFitrosTabla("frmAcciones:tblAcciones");
            accionSel = new Accion();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminar() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos Get y Set"> 
    public ArrayList<Accion> getLstAcciones() {
        return lstAcciones;
    }

    public void setLstAcciones(ArrayList<Accion> lstAcciones) {
        this.lstAcciones = lstAcciones;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Accion getAccionSel() {
        return accionSel;
    }

    public void setAccionSel(Accion accionSel) {
        this.accionSel = accionSel;
    }
    //</editor-fold> 

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
}
