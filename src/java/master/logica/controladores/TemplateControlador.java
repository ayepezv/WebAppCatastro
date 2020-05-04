package master.logica.controladores;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import master.logica.entidades.Usuario;

@ManagedBean
@ViewScoped
public class TemplateControlador implements Serializable{
    
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("sessionUsuario");
            if(us == null){
                context.getExternalContext().redirect("/WebAppCatastro/faces/permisos.xhtml");
            }
        } catch (Exception e) {
            
        }
    }
}
