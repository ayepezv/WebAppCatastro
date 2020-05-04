/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.presentacion.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Menu;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosMenu;
import master.logica.servicios.ServiciosRolUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class IndexMaster implements Serializable{

    private ArrayList<Menu> submenus;
    private ArrayList<RolUsuario> lstRoles;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private RolUsuario rolUsuario;

    public IndexMaster() {
        rolUsuario=new RolUsuario();
        submenus = new ArrayList<>();
        lstRoles = new ArrayList<>();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

    }

    @PostConstruct
    public void init(){
        cargarRoles();
    }
    public void cargarRoles() {
        try {
            Usuario us = new Usuario();
            us = (Usuario) httpServletRequest.getSession().getAttribute("usLogueado");
            lstRoles = ServiciosRolUsuario.listarRolesUsuario(us.getIdPersona());
            Util.addSuccessMessage("total de roles: "+lstRoles.size());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public ArrayList<Menu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(ArrayList<Menu> submenus) {
        this.submenus = submenus;
    }

    public ArrayList<RolUsuario> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(ArrayList<RolUsuario> lstRoles) {
        this.lstRoles = lstRoles;
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

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

}
