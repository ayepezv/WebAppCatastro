package auditoria.presentacion.beans;

import auditoria.logica.entidades.AObras;
import auditoria.logica.servicios.FAObras;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlAuditoriaObras implements Serializable {

    private ArrayList<AObras> lstObras;
    private AObras obrasSel;
    private String contenidoComparacion;

    public CtrlAuditoriaObras() {
        obrasSel = new AObras();
        obtenerObras();

    }

    public void obtenerObras() {
        try {
            setLstObras(FAObras.obtenerAuditoriaObras());
        } catch (Exception e) {
            Util.addErrorMessage("Obtener obras dice:" + e.getMessage());
        }
    }

    public void compararObras() {
        try {
            lstObras = FAObras.comparacionObras(obrasSel.getIdAudObras());

            AObras obraAnt, obraActual;
            obraAnt = lstObras.get(0);
            obraActual = lstObras.get(1);

            contenidoComparacion = "";

            if (obraActual.getNombreObra().equals(obraAnt.getNombreObra())) {
            } else {
                contenidoComparacion = contenidoComparacion + "<b>Nombre de la Mejora</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor actual: </b>" + obraActual.getNombreObra() + "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor anterior: </b>" + obraAnt.getNombreObra() + "<br><hr/>";
            }
            if (obraActual.getFechaCreacion().equals(obraAnt.getFechaCreacion())) {
                contenidoComparacion = contenidoComparacion + "<b>Fecha de Creacion</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor actual: </b>" + obraActual.getFechaCreacion() + "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor anterior: </b>" + obraAnt.getFechaCreacion() + "<br><hr/>";
            }

            if (obraActual.getValorObra() != obraAnt.getValorObra()) {
                contenidoComparacion = contenidoComparacion + "<b>Valor de la Obra</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor actual: </b>" + obraActual.getValorObra() + "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor anterior: </b>" + obraAnt.getValorObra() + "<br><hr/>";
            }

        } catch (Exception e) {
            System.out.println("Al comparar obras dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    /**
     * @return the lstObras
     */
    public ArrayList<AObras> getLstObras() {
        return lstObras;
    }

    /**
     * @param lstObras the lstObras to set
     */
    public void setLstObras(ArrayList<AObras> lstObras) {
        this.lstObras = lstObras;
    }

    /**
     * @return the obrasSel
     */
    public AObras getObrasSel() {
        return obrasSel;
    }

    /**
     * @param obrasSel the obrasSel to set
     */
    public void setObrasSel(AObras obrasSel) {
        this.obrasSel = obrasSel;
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
