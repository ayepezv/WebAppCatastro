package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.RecaudacionPredial;
import catastro.recaudacion.funciones.FRecaudacionPredial;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ControladorRecaudacionPredial implements Serializable {

    private List<RecaudacionPredial> lstRecaudaciones;
    private RecaudacionPredial rp;

    public ControladorRecaudacionPredial() {
        rp=new RecaudacionPredial();
        obtenerRecaudaciones();
    }

    public void obtenerRecaudaciones() {
        try {
            this.setLstRecaudaciones(FRecaudacionPredial.obtenerRecaudaciones());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * @return the lstRecaudaciones
     */
    public List<RecaudacionPredial> getLstRecaudaciones() {
        return lstRecaudaciones;
    }

    /**
     * @param lstRecaudaciones the lstRecaudaciones to set
     */
    public void setLstRecaudaciones(List<RecaudacionPredial> lstRecaudaciones) {
        this.lstRecaudaciones = lstRecaudaciones;
    }

    /**
     * @return the rp
     */
    public RecaudacionPredial getRp() {
        return rp;
    }

    /**
     * @param rp the rp to set
     */
    public void setRp(RecaudacionPredial rp) {
        this.rp = rp;
    }
}
