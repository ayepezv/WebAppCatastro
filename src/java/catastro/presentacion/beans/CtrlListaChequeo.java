/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.reportes.entidades.ServiciosReportes;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlListaChequeo implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int predio;
    private int zonaHasta;
    private int sectorHasta;
    private int manzanaHasta;
    private int predioHasta;
    private ArrayList<PredioV2> lstPredios;
    private PredioV2 objPredio;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public CtrlListaChequeo() {
        objPredio = new PredioV2();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void verReporteFicha() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ServiciosReportes reporte = new ServiciosReportes();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/ReporteChequeo.jasper");
        reporte.listaParaChequeos(ruta, getZona(), getZonaHasta(), getSector(), getSectorHasta(), getManzana(), getManzanaHasta(), getPredio(), getPredioHasta(), getSessionUsuario().getIdPersona());
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void obtenerPredios() {
        try {
            setLstPredios(FuncionesPredio.encontrarPrediosDesdeHasta(getZona(), getZonaHasta(), getSector(), getSectorHasta(), getManzana(), getManzanaHasta(), getPredio(), getPredioHasta()));
            Util.addSuccessMessage(getLstPredios().size() + " Predio(s) Encontrado(s)");
        } catch (Exception e) {
            System.out.println("public void obtenerPredios() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void limpiar() {
        try {
            setZona(0);
            setSector(0);
            setManzana(0);
            setPredio(0);
            setZonaHasta(0);
            setSectorHasta(0);
            setManzanaHasta(0);
            setPredioHasta(0);
            setLstPredios(new ArrayList<>());
            resetearFitrosTabla("frmPrincipal:tblPredios");
        } catch (Exception e) {
            System.out.println("public void limpiar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
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
     * @return the zonaHasta
     */
    public int getZonaHasta() {
        return zonaHasta;
    }

    /**
     * @param zonaHasta the zonaHasta to set
     */
    public void setZonaHasta(int zonaHasta) {
        this.zonaHasta = zonaHasta;
    }

    /**
     * @return the sectorHasta
     */
    public int getSectorHasta() {
        return sectorHasta;
    }

    /**
     * @param sectorHasta the sectorHasta to set
     */
    public void setSectorHasta(int sectorHasta) {
        this.sectorHasta = sectorHasta;
    }

    /**
     * @return the manzanaHasta
     */
    public int getManzanaHasta() {
        return manzanaHasta;
    }

    /**
     * @param manzanaHasta the manzanaHasta to set
     */
    public void setManzanaHasta(int manzanaHasta) {
        this.manzanaHasta = manzanaHasta;
    }

    /**
     * @return the predioHasta
     */
    public int getPredioHasta() {
        return predioHasta;
    }

    /**
     * @param predioHasta the predioHasta to set
     */
    public void setPredioHasta(int predioHasta) {
        this.predioHasta = predioHasta;
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
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @param faceContext the faceContext to set
     */
    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

}
