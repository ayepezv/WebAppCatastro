package auditoria.presentacion.beans;

import auditoria.logica.entidades.APredios;
import auditoria.logica.servicios.FAPredios;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlAuditoriaPredios implements Serializable{

    private ArrayList<APredios> lstPredios;
    private APredios predioSel;
    private String contenidoComparacion;
    
    public CtrlAuditoriaPredios() {
        predioSel = new APredios();
        obtenerPredios();
    }
    
    public void obtenerPredios() {
        try {
            setLstPredios(FAPredios.obtenerAuditoriaPredios());
        } catch (Exception e) {
            Util.addErrorMessage("Obtener mejoras dice: " + e.getMessage());
        }
    }
    
    public void compararPredios() {
        try {
            lstPredios = FAPredios.comparacionPredios(predioSel.getIdAudPredios());

            APredios predioAnt, predioActual;
            predioAnt = lstPredios.get(0);
            predioActual = lstPredios.get(1);

            contenidoComparacion = "";

            if (predioActual.getTitularDomi().equals(predioAnt.getTitularDomi())) {
            } else {
                contenidoComparacion = contenidoComparacion + "<b>Nombre de el Titular</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Nombre actual: </b>" + predioActual.getTitularDomi()+ "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Nombre anterior: </b>" + predioAnt.getTitularDomi()+ "<br><hr/>";
            }
            if (predioActual.getRepreLegal().equals(predioAnt.getRepreLegal())) {
                contenidoComparacion = contenidoComparacion + "<b>Representante Legal</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Nombre actual: </b>" + predioActual.getRepreLegal()+ "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Nombre anterior: </b>" + predioAnt.getRepreLegal() + "<br><hr/>";
            }

            if (predioActual.getNotaria()!= predioAnt.getNotaria()) {
                contenidoComparacion = contenidoComparacion + "<b>Notaria</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor actual: </b>" + predioActual.getNotaria()+ "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor anterior: </b>" + predioAnt.getNotaria()+ "<br><hr/>";
            }

        } catch (Exception e) {
            System.out.println("public void compararMejoras() diece: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    

    /**
     * @return the lstPredios
     */
    public ArrayList<APredios> getLstPredios() {
        return lstPredios;
    }

    /**
     * @param lstPredios the lstPredios to set
     */
    public void setLstPredios(ArrayList<APredios> lstPredios) {
        this.lstPredios = lstPredios;
    }

    /**
     * @return the predioSel
     */
    public APredios getPredioSel() {
        return predioSel;
    }

    /**
     * @param predioSel the predioSel to set
     */
    public void setPredioSel(APredios predioSel) {
        this.predioSel = predioSel;
    }

    /**
     * @return the contenidoComparacion
     */
    public String getContenidoComparacion() {
        return contenidoComparacion;
    }

    /**
     * @param contenidoComparacion the contenidoComparacion to set
     */
    public void setContenidoComparacion(String contenidoComparacion) {
        this.contenidoComparacion = contenidoComparacion;
    }
}
