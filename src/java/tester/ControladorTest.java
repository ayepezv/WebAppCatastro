/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.io.Serializable;
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
public class ControladorTest implements Serializable {

    private int idUsuario;
    private Usuario us;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private FacesMessage facesMessage;

    public ControladorTest() {
        us = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerUsuario();
    }

    public void obtenerUsuario() {
        try {
            setIdUsuario((int) getHttpServletRequest().getSession().getAttribute("idUsuario"));
            setUs(ServiciosUsuario.buscarUsuarioDadoId(getIdUsuario()));
        } catch (Exception e) {
            System.out.println(" public void obtenerUsuario() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());

        }
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the us
     */
    public Usuario getUs() {
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(Usuario us) {
        this.us = us;
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
     * @return the facesMessage
     */
    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    /**
     * @param facesMessage the facesMessage to set
     */
    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }
}
