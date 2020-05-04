/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import catastro.reportes.entidades.ReporteTitulo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlConsultarTitulosCredito implements Serializable {

    private int band;
    private int zona;
    private int sector;
    private int manzana;
    private int predio;
    private int band2;
    private Usuario contribuyente;
    private String parametro;
    private Titulo objTitulo;
    private List<Titulo> lstTitulos;
    private ArrayList<Usuario> lstContribuyentes;
    private String txtValor;

    public CtrlConsultarTitulosCredito() {
        objTitulo = new Titulo();
        contribuyente = new Usuario();
        txtValor = "";
    }

    public void consultarCanceladosPorClave() {
        try {
            setLstTitulos(FTitulos.consultarTitulosPagadosDadoZonaSectorManzanaPredio(getZona(), getSector(), getManzana(), getPredio()));
            Util.addSuccessMessage("Se encontaron " + getLstTitulos().size() + " títulos cancelados.");
            setTxtValor("Cancelados");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void consultarPorClave() dice: " + e.getMessage());
        }
    }

    public void consultarPendientesPorClave() {
        try {
            setLstTitulos(FTitulos.consultarValoresPagarDadoZonaSectorManzanaPredio(getZona(), getSector(), getManzana(), getPredio()));
            Util.addSuccessMessage("Se encontaron " + getLstTitulos().size() + " títulos pendientes.");
            setTxtValor("Pendientes");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void consultarPorClave() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            setLstTitulos(new ArrayList<>());
            zona = 0;
            sector = 0;
            manzana = 0;
            predio = 0;
            lstContribuyentes = new ArrayList<>();
            parametro = "";
            contribuyente = new Usuario();
            band = 0;
            band2 = 0;
            txtValor = "";
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void consultarTitulos() {
        try {
            switch (getBand2()) {
                case 1:
                    consultarCanceladosPorClave();
                    break;
                case 2:
                    consultarPendientesPorClave();
                    break;

                case 3:
                    limpiar();
                    break;

                default:
                    Util.addErrorMessage("Opción incorrecta");
                    break;
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerContribuyentes() {
        try {
            java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.VariablesGlobales");
            int rolContribuyente = Integer.valueOf(Configuracion.getString("rolPropietario"));
            setLstContribuyentes(ServiciosUsuario.encontrarUsuarioDadoRol(getParametro(), rolContribuyente));

            setLstTitulos(new ArrayList<>());
            resetearFitrosTabla("frmTitulos:tblTitulos");
        } catch (Exception e) {
            System.out.println("public void obtenerContribuyentes() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void consultarPagadosPorCedula() {
        try {
            setLstTitulos(FTitulos.consultarTitulosPagadosPropietario(getContribuyente().getIdPersona()));
            Util.addSuccessMessage("Se encontaron " + getLstTitulos().size() + " títulos cancelados.");
            setTxtValor("Cancelados");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void consultarPendientesPorCedula() {
        try {
            setLstTitulos(FTitulos.consultarValoresPagarDadoPropietario(getContribuyente().getIdPersona()));
            Util.addSuccessMessage("Se encontaron " + getLstTitulos().size() + " títulos pendientes.");
            setTxtValor("Pendientes");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void verReporteDuplicado() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            System.out.println("=========>>>>>>>>>>> Id Titulo: " + objTitulo.getIdTitulo());
            ReporteTitulo reporte = new ReporteTitulo();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/reportes/ReporteTituloDuplicado.jasper");
            reporte.getReportePdfPorId(ruta, objTitulo.getIdTitulo());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("=========>>>>>>>>>>> Id Titulo: " + objTitulo.getIdTitulo());
            System.out.println("Error: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    /**
     * @return the band
     */
    public int getBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(int band) {
        this.band = band;
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
     * @return the predio
     */
    public int getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(int predio) {
        this.predio = predio;
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

    /**
     * @return the band2
     */
    public int getBand2() {
        return band2;
    }

    /**
     * @param band2 the band2 to set
     */
    public void setBand2(int band2) {
        this.band2 = band2;
    }

    /**
     * @return the parametro
     */
    public String getParametro() {
        return parametro;
    }

    /**
     * @param parametro the parametro to set
     */
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    /**
     * @return the lstContribuyentes
     */
    public ArrayList<Usuario> getLstContribuyentes() {
        return lstContribuyentes;
    }

    /**
     * @param lstContribuyentes the lstContribuyentes to set
     */
    public void setLstContribuyentes(ArrayList<Usuario> lstContribuyentes) {
        this.lstContribuyentes = lstContribuyentes;
    }

    /**
     * @return the contribuyente
     */
    public Usuario getContribuyente() {
        return contribuyente;
    }

    /**
     * @param contribuyente the contribuyente to set
     */
    public void setContribuyente(Usuario contribuyente) {
        this.contribuyente = contribuyente;
    }

    /**
     * @return the txtValor
     */
    public String getTxtValor() {
        return txtValor;
    }

    /**
     * @param txtValor the txtValor to set
     */
    public void setTxtValor(String txtValor) {
        this.txtValor = txtValor;
    }

}
