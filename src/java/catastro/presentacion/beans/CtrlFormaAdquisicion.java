/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.FormaAdquisicion;
import catastro.logica.servicios.FFormaAdquisicion;
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
public class CtrlFormaAdquisicion implements Serializable {

    private List<FormaAdquisicion> lstFAdquisicion;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private FormaAdquisicion objFormaAdquisicion;
    private FormaAdquisicion formaadquisicionSel;
    private String msgBD;

    public CtrlFormaAdquisicion() {
        objFormaAdquisicion = new FormaAdquisicion();
        formaadquisicionSel = new FormaAdquisicion();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
        obtenerCoeficientes();
    }

    public void obtenerCoeficientes() {
        try {
            lstFAdquisicion = FFormaAdquisicion.obtenerFAdquisicion();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FFormaAdquisicion.registrarFAdquisicion(objFormaAdquisicion);
            objFormaAdquisicion = new FormaAdquisicion();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaFAdquisicion:tblListaFAdquisicion");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarFAdquisicion').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FFormaAdquisicion.actualizarFAdquisicion(formaadquisicionSel);
            formaadquisicionSel = new FormaAdquisicion();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaFAdquisicion:tblListaFAdquisicion");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarFAdquisicion').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FFormaAdquisicion.eliminarFAdquisicion(formaadquisicionSel);
            formaadquisicionSel = new FormaAdquisicion();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaFAdquisicion:tblListaFAdquisicion");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarFAdquisicion').hide()");
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

    public List<FormaAdquisicion> getLstFAdquisicion() {
        return lstFAdquisicion;
    }

    public void setLstFAdquisicion(List<FormaAdquisicion> lstFAdquisicion) {
        this.lstFAdquisicion = lstFAdquisicion;
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

    public FormaAdquisicion getObjFormaAdquisicion() {
        return objFormaAdquisicion;
    }

    public void setObjFormaAdquisicion(FormaAdquisicion objFormaAdquisicion) {
        this.objFormaAdquisicion = objFormaAdquisicion;
    }

    public FormaAdquisicion getFormaadquisicionSel() {
        return formaadquisicionSel;
    }

    public void setFormaadquisicionSel(FormaAdquisicion formaadquisicionSel) {
        this.formaadquisicionSel = formaadquisicionSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
