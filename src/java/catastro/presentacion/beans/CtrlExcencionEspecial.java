
package catastro.presentacion.beans;

import catastro.logica.entidades.ExencionEspecial;
import catastro.logica.servicios.FExencionEspecial;
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
public class CtrlExcencionEspecial implements Serializable {
      private List<ExencionEspecial> lstExcencionEspecial;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private ExencionEspecial objExcencionEspecial;
    private ExencionEspecial excencionespecialSel;
    private String msgBD;

    public CtrlExcencionEspecial() {
        obtenerCoeficientes();
        objExcencionEspecial = new ExencionEspecial();
        excencionespecialSel = new ExencionEspecial();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstExcencionEspecial = FExencionEspecial.obtenerExEspecial();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FExencionEspecial.registrarExEspecial(objExcencionEspecial);
            objExcencionEspecial = new ExencionEspecial();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefExEspecial:tblListaExEspecial");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarExEspecial').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FExencionEspecial.actualizarExEspecial(excencionespecialSel);
            excencionespecialSel = new ExencionEspecial();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaExEspecial:tblListaExEspecial");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarExEspecial').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FExencionEspecial.eliminarExEspecial(excencionespecialSel);
            excencionespecialSel = new ExencionEspecial();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaExEspecial:tblListaExEspecial");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarExEspecial').hide()");
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

    public List<ExencionEspecial> getLstExcencionEspecial() {
        return lstExcencionEspecial;
    }

    public void setLstExcencionEspecial(List<ExencionEspecial> lstExcencionEspecial) {
        this.lstExcencionEspecial = lstExcencionEspecial;
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

    public ExencionEspecial getObjExcencionEspecial() {
        return objExcencionEspecial;
    }

    public void setObjExcencionEspecial(ExencionEspecial objExcencionEspecial) {
        this.objExcencionEspecial = objExcencionEspecial;
    }

    public ExencionEspecial getExcencionespecialSel() {
        return excencionespecialSel;
    }

    public void setExcencionespecialSel(ExencionEspecial excencionespecialSel) {
        this.excencionespecialSel = excencionespecialSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
