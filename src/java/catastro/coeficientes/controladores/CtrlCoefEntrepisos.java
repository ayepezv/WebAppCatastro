package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefEntrepisos;
import catastro.coeficientes.funciones.FCoefEntrepisos;
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
public class CtrlCoefEntrepisos implements Serializable {

    private List<CoefEntrepisos> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefEntrepisos objCoefEntrepisos;
    private CoefEntrepisos coefentrepisosSel;
    private String msgBD;

    public CtrlCoefEntrepisos() {
        obtenerCoeficientes();
        objCoefEntrepisos = new CoefEntrepisos();
        coefentrepisosSel = new CoefEntrepisos();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefEntrepisos.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefEntrepisos.registrarCoeficiente(objCoefEntrepisos);
            objCoefEntrepisos = new CoefEntrepisos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEntrepisos:tblListaCoefEntrepisos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefEntrepisos').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefEntrepisos.actualizarCoeficiente(coefentrepisosSel);
            coefentrepisosSel = new CoefEntrepisos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEntrepisos:tblListaCoefEntrepisos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefEntrepisos').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefEntrepisos.eliminarCoeficiente(coefentrepisosSel);
            coefentrepisosSel = new CoefEntrepisos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEntrepisos:tblListaCoefEntrepisos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefEntrepisos').hide()");
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

    public List<CoefEntrepisos> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefEntrepisos> lstCoeficientes) {
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

    public CoefEntrepisos getObjCoefEntrepisos() {
        return objCoefEntrepisos;
    }

    public void setObjCoefEntrepisos(CoefEntrepisos objCoefEntrepisos) {
        this.objCoefEntrepisos = objCoefEntrepisos;
    }

    public CoefEntrepisos getCoefentrepisosSel() {
        return coefentrepisosSel;
    }

    public void setCoefentrepisosSel(CoefEntrepisos coefentrepisosSel) {
        this.coefentrepisosSel = coefentrepisosSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
