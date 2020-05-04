
package catastro.webservice.controladores;

import catastro.recaudacion.funciones.FTitulos;
import catastro.webservice.entidades.ReportePrediosClaveCatastral;
import catastro.webservice.funciones.FTitulosPagados;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import recursos.Util;


@ManagedBean
@ViewScoped
public class CtrlTitulosPagados implements Serializable {
    
    private List<ReportePrediosClaveCatastral> lstTitulosPagadosWS;
    private Date FechaIniWS;
    private Date FechaFinWS;
    
    
    
    public void obtenerTitulosPagados() {
        try {
            java.sql.Date sqlfechaInicio = Util.sqlDate(getFechaIniWS());
            java.sql.Date sqlfechaFin = Util.sqlDate(getFechaFinWS());
            setLstTitulosPagadosWS(FTitulosPagados.consultarTitulosPagadosWS(sqlfechaInicio,sqlfechaFin));
        } catch (Exception e) {
            System.out.println("public void obtenerTitulosPagadosWS() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerTitulosPagadosWS() dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstTitulosPagadosWS
     */
    public List<ReportePrediosClaveCatastral> getLstTitulosPagadosWS() {
        return lstTitulosPagadosWS;
    }

    /**
     * @param lstTitulosPagadosWS the lstTitulosPagadosWS to set
     */
    public void setLstTitulosPagadosWS(List<ReportePrediosClaveCatastral> lstTitulosPagadosWS) {
        this.lstTitulosPagadosWS = lstTitulosPagadosWS;
    }

    private Object getHttpServletRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the FechaIniWS
     */
    public Date getFechaIniWS() {
        return FechaIniWS;
    }

    /**
     * @param FechaIniWS the FechaIniWS to set
     */
    public void setFechaIniWS(Date FechaIniWS) {
        this.FechaIniWS = FechaIniWS;
    }

    /**
     * @return the FechaFinWS
     */
    public Date getFechaFinWS() {
        return FechaFinWS;
    }

    /**
     * @param FechaFinWS the FechaFinWS to set
     */
    public void setFechaFinWS(Date FechaFinWS) {
        this.FechaFinWS = FechaFinWS;
    }
    
}
