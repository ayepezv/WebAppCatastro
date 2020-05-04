
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefPisos;
import catastro.coeficientes.funciones.FCoefAcabPisos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class CtrlCoefAcabPisos implements Serializable{
    private List<CoefPisos> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefPisos objCoefPisos;
    private CoefPisos coefpisosSel;
    private String msgBD;

    public CtrlCoefAcabPisos() {
        obtenerCoeficientes();
        objCoefPisos = new CoefPisos();
        coefpisosSel = new CoefPisos();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAcabPisos.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAcabPisos.registrarCoeficiente(objCoefPisos);
            objCoefPisos = new CoefPisos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefPisos:tblListaCoefPisos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefPisos').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefAcabPisos.actualizarCoeficiente(coefpisosSel);
            coefpisosSel = new CoefPisos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefPisos:tblListaCoefPisos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefPisos').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefAcabPisos.eliminarCoeficiente(coefpisosSel);
            coefpisosSel = new CoefPisos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefPisos:tblListaCoefPisos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefPisos').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
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

    public List<CoefPisos> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefPisos> lstCoeficientes) {
        this.lstCoeficientes = lstCoeficientes;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFaceContext() {
        return faceContext;
    }

    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    public CoefPisos getObjCoefPisos() {
        return objCoefPisos;
    }

    public void setObjCoefPisos(CoefPisos objCoefPisos) {
        this.objCoefPisos = objCoefPisos;
    }

    public CoefPisos getCoefpisosSel() {
        return coefpisosSel;
    }

    public void setCoefpisosSel(CoefPisos coefpisosSel) {
        this.coefpisosSel = coefpisosSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
    
}
