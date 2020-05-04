/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.entidades.Transanccion;
import catastro.recaudacion.funciones.FTitulos;
import catastro.recaudacion.funciones.FTransaccion;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 *
 * @author Geovanny Cudco
 */
@ManagedBean
@ViewScoped
public class CtAnularCobro implements Serializable {

    private Transanccion objTransaccion;
    private List<Transanccion> lstTransacciones;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private Titulo objTitulo;

    public CtAnularCobro() {
        objTransaccion = new Transanccion();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerTransacciones();
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerTransacciones() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse("2018-06-13");
            java.sql.Date sql = new java.sql.Date(parsed.getTime());

            //
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            //
            setLstTransacciones(FTransaccion.obtenerTransaccionesGlobalDadoFecha(sql));
        } catch (Exception e) {
            System.out.println("public void obtenerTransacciones() dice: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void anularCobro() {
        try {
            Util.addSuccessMessage(FTransaccion.anularTransaccion(objTransaccion.getIdTransaccion()));
            obtenerTransacciones();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgAnulacion').hide()");
        } catch (Exception e) {
            System.out.println("public void anularCobro() dice: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void obtenerTitulo() {
        try {
            setObjTitulo(FTitulos.obtenerTituloDadoTransaccion(objTransaccion.getIdTransaccion()));
        } catch (Exception e) {
            System.out.println("public void obtenerTransaccion() dice: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
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
     * @return the lstTransacciones
     */
    public List<Transanccion> getLstTransacciones() {
        return lstTransacciones;
    }

    /**
     * @param lstTransacciones the lstTransacciones to set
     */
    public void setLstTransacciones(List<Transanccion> lstTransacciones) {
        this.lstTransacciones = lstTransacciones;
    }

    /**
     * @return the objTransaccion
     */
    public Transanccion getObjTransaccion() {
        return objTransaccion;
    }

    /**
     * @param objTransaccion the objTransaccion to set
     */
    public void setObjTransaccion(Transanccion objTransaccion) {
        this.objTransaccion = objTransaccion;
    }

    /**
     * @return the objTitulo
     */
    public Titulo getObjTitulo() {
        return objTitulo;
    }

    /**
     * @param objTitulo the objTitulo to set
     */
    public void setObjTitulo(Titulo objTitulo) {
        this.objTitulo = objTitulo;
    }

    
    
}
