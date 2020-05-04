package aguapotable.presentacion.controladores;

import aguapotable.logica.entidades.Ciclo;
import aguapotable.logica.funciones.FCiclo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorCicloAP implements Serializable {

    private List<Ciclo> lstCiclos;
    private Ciclo objCiclo;
    private Ciclo cicloSel;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public ControladorCicloAP() throws Exception {
        objCiclo = new Ciclo();
        obtenerCiclos();
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
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }
    
    
    public void obtenerCiclos() {
        try {
            setLstCiclos(FCiclo.obtenerCiclos());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void obtenerCiclos() dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstCiclos
     */
    public List<Ciclo> getLstCiclos() {
        return lstCiclos;
    }

    /**
     * @param lstCiclos the lstCiclos to set
     */
    public void setLstCiclos(List<Ciclo> lstCiclos) {
        this.lstCiclos = lstCiclos;
    }

    /**
     * @return the objCiclo
     */
    public Ciclo getObjCiclo() {
        return objCiclo;
    }

    /**
     * @param objCiclo the objCiclo to set
     */
    public void setObjCiclo(Ciclo objCiclo) {
        this.objCiclo = objCiclo;
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

    /**
     * @return the cicloSel
     */
    public Ciclo getCicloSel() {
        return cicloSel;
    }

    /**
     * @param cicloSel the cicloSel to set
     */
    public void setCicloSel(Ciclo cicloSel) {
        this.cicloSel = cicloSel;
    }
    
    

}
