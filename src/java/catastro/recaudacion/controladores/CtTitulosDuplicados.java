package catastro.recaudacion.controladores;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import catastro.reportes.entidades.ServiciosReportes;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtTitulosDuplicados implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private ArrayList<PredioV2> lstPredios;
    private PredioV2 objPredio;
    private List<Titulo> lstTitulos;
    private Titulo objTitulo;

    public void buscarPredios() {
        try {
            //String strClave = String.valueOf(zona) + String.valueOf(sector) + String.valueOf(manzana) + String.valueOf(numPredio);
            String strClave = "";
            String strZona = "";
            String strSector = "";
            String strManzana = "";
            String strPredio = "";

            if ((getZona() / 10) < 1) {
                strZona = "0" + String.valueOf(getZona());
            } else {
                strZona = String.valueOf(getZona());
            }

            if ((getSector() / 10) < 1) {
                strSector = "0" + String.valueOf(getSector());
            } else {
                strSector = String.valueOf(getSector());
            }

            if ((getManzana() / 10) < 1) {
                strManzana = "00" + String.valueOf(getManzana());
            } else {
                strManzana = "0" + String.valueOf(getManzana());
            }

            if ((getNumPredio() / 10) < 1) {
                strPredio = "00" + String.valueOf(getNumPredio());
            } else {
                strPredio = "0" + String.valueOf(getNumPredio());
            }

            strClave = strZona + strSector + strManzana + strPredio;

            this.setLstPredios(FuncionesPredio.encontrarPredio(strClave));

            Util.addSuccessMessage(strClave);
            Util.addSuccessMessage("Predios encontrados: " + getLstPredios().size());
            System.out.println("Clave catastral: " + strClave);
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            this.setLstPredios(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void obtenerTitulos() {
        try {
            setLstTitulos(FTitulos.consultarTitulosPagados(objPredio.getIdPredio()));
        } catch (Exception e) {
            System.out.println("public void obtenerTitulos() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerTitulos() dice: " + e.getMessage());
        }
    }

    public void verReporteDuplicado() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//ReporteTituloDuplicadoEspecifico.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getReporteTituloDuplicado(ruta, objTitulo.getIdTitulo());
            reporte = null;
        } catch (Exception e) {
            System.out.println("public void verReporteLibroPredial() dice: " + e.getMessage());
            Util.addErrorMessage("public void verReporteLibroPredial() dice: " + e.getMessage());
        }

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
     * @return the lstTitulos
     */
    public List<Titulo> getLstTitulos() {
        return lstTitulos;
    }

    /**
     * @param lstTitulos the lstTitulos to set
     */
    public void setLstTitulos(List<Titulo> lstTitulos) {
        this.lstTitulos = lstTitulos;
    }

    /**
     * @return the objTitulo
     */
    public Titulo getObjTitulo() {
        return objTitulo;
    }

    /**
     * @param objTitulo the objTitulo to set
     */
    public void setObjTitulo(Titulo objTitulo) {
        this.objTitulo = objTitulo;
    }

}
