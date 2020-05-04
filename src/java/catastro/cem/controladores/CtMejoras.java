/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.cem.controladores;

import catastro.cem.entidades.Mejora;
import catastro.cem.entidades.Obra;
import catastro.cem.funciones.FMejora;
import catastro.cem.funciones.FObra;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Geovanny Cudco
 */
@ManagedBean
@ViewScoped
public class CtMejoras implements Serializable {

    private String txtCriterio;
    private Mejora mejoraSel;
    private Obra objObra;
    private Obra obraSel;
    private List<Mejora> lstMejoras;
    private List<Obra> lstObras;
    private String visible;

    public CtMejoras() {
        visible = "hidden";
        mejoraSel = new Mejora();
    }

    public void obtenerMejoras() {
        try {
            setLstMejoras(FMejora.encontrarMejoraDadoParametro(getTxtCriterio()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(getLstMejoras().size()), " coincidencias encontradas."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));

        }
    }

    public void obtenerObras() {
        try {
            setLstObras(FObra.obtenerObraDadoMejora(getMejoraSel().getIdMejora()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(getLstObras().size()), " obras encontradas."));
            if (getLstObras().size() > 0) {
                setVisible("visible");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    /**
     * @return the txtCriterio
     */
    public String getTxtCriterio() {
        return txtCriterio;
    }

    /**
     * @param txtCriterio the txtCriterio to set
     */
    public void setTxtCriterio(String txtCriterio) {
        this.txtCriterio = txtCriterio;
    }

    /**
     * @return the lstMejoras
     */
    public List<Mejora> getLstMejoras() {
        return lstMejoras;
    }

    /**
     * @param lstMejoras the lstMejoras to set
     */
    public void setLstMejoras(List<Mejora> lstMejoras) {
        this.lstMejoras = lstMejoras;
    }

    /**
     * @return the mejoraSel
     */
    public Mejora getMejoraSel() {
        return mejoraSel;
    }

    /**
     * @param mejoraSel the mejoraSel to set
     */
    public void setMejoraSel(Mejora mejoraSel) {
        this.mejoraSel = mejoraSel;
    }

    /**
     * @return the objObra
     */
    public Obra getObjObra() {
        return objObra;
    }

    /**
     * @param objObra the objObra to set
     */
    public void setObjObra(Obra objObra) {
        this.objObra = objObra;
    }

    /**
     * @return the obraSel
     */
    public Obra getObraSel() {
        return obraSel;
    }

    /**
     * @param obraSel the obraSel to set
     */
    public void setObraSel(Obra obraSel) {
        this.obraSel = obraSel;
    }

    /**
     * @return the lstObras
     */
    public List<Obra> getLstObras() {
        return lstObras;
    }

    /**
     * @param lstObras the lstObras to set
     */
    public void setLstObras(List<Obra> lstObras) {
        this.lstObras = lstObras;
    }

    /**
     * @return the visible
     */
    public String getVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(String visible) {
        this.visible = visible;
    }

    
    
    
}
