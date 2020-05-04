package catastro.presentacion.beans;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.reportes.entidades.ReporteTitulo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class BuscarPrediosCtrl implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private ArrayList<PredioV2> lstPredios;
    private PredioV2 objPredio;

    public BuscarPrediosCtrl() {
        objPredio = new PredioV2();
    }

    public void buscarPredios() {
        try {
            //String strClave = String.valueOf(zona) + String.valueOf(sector) + String.valueOf(manzana) + String.valueOf(numPredio);
            String strClave = "";
            String strZona = "";
            String strSector = "";
            String strManzana = "";
            String strPredio = "";

            if ((zona / 10) < 1) {
                strZona = "0" + String.valueOf(zona);
            } else {
                strZona = String.valueOf(zona);
            }

            if ((sector / 10) < 1) {
                strSector = "0" + String.valueOf(sector);
            } else {
                strSector = String.valueOf(sector);
            }

            if ((manzana / 10) < 1) {
                strManzana = "00" + String.valueOf(manzana);
            } else {
                strManzana = "0" + String.valueOf(manzana);
            }

            if ((numPredio / 10) < 1) {
                strPredio = "00" + String.valueOf(numPredio);
            } else {
                strPredio = "0" + String.valueOf(numPredio);
            }

            strClave = strZona + strSector + strManzana + strPredio;

            this.setLstPredios(FuncionesPredio.encontrarPredio(strClave));

            Util.addSuccessMessage(strClave);
            Util.addSuccessMessage("Predios encontrados: " + lstPredios.size());
            System.out.println("Clave catastral: " + strClave);
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            this.lstPredios = new ArrayList<>();
            zona = 0;
            sector = 0;
            manzana = 0;
            numPredio = 0;
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void verReporteDuplicado() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ReporteTitulo reporte = new ReporteTitulo();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("//reportes//test.jasper");
        FacesContext.getCurrentInstance().responseComplete();
        reporte.getReportePrueba(ruta, 1);
    }

    /**
     * @return the zona
     */
    public int getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(int zona) {
        this.zona = zona;
    }

    /**
     * @return the sector
     */
    public int getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(int sector) {
        this.sector = sector;
    }

    /**
     * @return the manzana
     */
    public int getManzana() {
        return manzana;
    }

    /**
     * @param manzana the manzana to set
     */
    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    /**
     * @return the numPredio
     */
    public int getNumPredio() {
        return numPredio;
    }

    /**
     * @param numPredio the numPredio to set
     */
    public void setNumPredio(int numPredio) {
        this.numPredio = numPredio;
    }

    /**
     * @return the lstPredios
     */
    public ArrayList<PredioV2> getLstPredios() {
        return lstPredios;
    }

    /**
     * @param lstPredios the lstPredios to set
     */
    public void setLstPredios(ArrayList<PredioV2> lstPredios) {
        this.lstPredios = lstPredios;
    }

    /**
     * @return the objPredio
     */
    public PredioV2 getObjPredio() {
        return objPredio;
    }

    /**
     * @param objPredio the objPredio to set
     */
    public void setObjPredio(PredioV2 objPredio) {
        this.objPredio = objPredio;
    }

    /**
     * @return the fechaInicio
     */
}
