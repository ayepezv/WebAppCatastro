/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.PredioHistorico;
import catastro.logica.servicios.FPrediosHistoricos;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlPrediosHistoricos implements Serializable {

    private List<PredioHistorico> lstpredios;
    private PredioHistorico predioSel;

    public CtrlPrediosHistoricos() {
        predioSel = new PredioHistorico();
        obtenerPredios();
    }

    public void obtenerPredios() {
        try {
            setLstpredios(FPrediosHistoricos.obtenerPredios());
        } catch (Exception e) {
            System.out.println("public void obtenerPredios() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPredios() dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstpredios
     */
    public List<PredioHistorico> getLstpredios() {
        return lstpredios;
    }

    /**
     * @param lstpredios the lstpredios to set
     */
    public void setLstpredios(List<PredioHistorico> lstpredios) {
        this.lstpredios = lstpredios;
    }

    /**
     * @return the predioSel
     */
    public PredioHistorico getPredioSel() {
        return predioSel;
    }

    /**
     * @param predioSel the predioSel to set
     */
    public void setPredioSel(PredioHistorico predioSel) {
        this.predioSel = predioSel;
    }

}
