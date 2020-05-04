/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.reportes.entidades.ReportePredios;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorRegistroPropiedad implements Serializable {
    
    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private int zonaAnt;
    private int sectorAnt;
    private int manzanaAnt;
    private int numPredioAnt;
    private int propAnt;
    private int derAcc;
    private int band = 0;
    private PredioV2 objPredio;
    private PredioV2 predioSel;
    private String txtCriterio;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private ArrayList<PredioV2> lstPredios;
    
    public ControladorRegistroPropiedad() {
        objPredio = new PredioV2();
        predioSel = new PredioV2();
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

    //<editor-fold defaultstate="collapsed" desc="Método para obtener predios dado parámetro">    
    public void encontrarPredioDadoParametro() {
        try {
            this.setLstPredios(FuncionesPredio.encontrarPredio(getTxtCriterio()));
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Predios Encontrados", String.valueOf(getLstPredios().size()));
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Predios por clave catastral actual">
    public void obtenerPrediosClaveCatastral() {
        try {
            setLstPredios(FuncionesPredio.encontrarPrediosDadoZonaSectorManzana(getZona(), getSector(), getManzana(), getNumPredio()));
            System.out.println(getLstPredios().size() + " Predios Obtenidos");
            Util.addSuccessMessage(getLstPredios().size() + " Predio(s) Obtenido(s)");
        } catch (Exception e) {
            System.out.println("public void obtenerPrediosClaveCatastral() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPrediosClaveCatastral() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Predios por clave catastral anterior">
    public void obtenerPrediosClaveAnterior() {
        try {
            setLstPredios(FuncionesPredio.encontrarPrediosDadoClaveAnterior(getZona(), getSector(), getManzana(), getNumPredio(), getPropAnt(), getDerAcc()));
            System.out.println(getLstPredios().size() + " Predios Obtenidos");
            Util.addSuccessMessage(getLstPredios().size() + " Predio(s) Obtenido(s)");
        } catch (Exception e) {
            System.out.println("public void obtenerPrediosClaveCatastral() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPrediosClaveCatastral() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para obtener el predio dado id">
    public void obtenerPredioDadoId() {
        try {
            setPredioSel(FuncionesPredio.obtenerPredioDadoCodigo(getObjPredio().getIdPredio()));
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    //</editor-fold>

    public void verReporteFicha() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ReportePredios reporte = new ReportePredios();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/predios/ReporteFicha.jasper");
        reporte.getReporteFichaPredial(ruta, getObjPredio().getIdPredio(), getSessionUsuario().getIdPersona());
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void limpiar() {
        try {
            this.setLstPredios(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
            setTxtCriterio(null);
        } catch (Exception e) {
            System.out.println("public void limpiar() dice: " + e.getMessage());
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
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
     * @return the predioSel
     */
    public PredioV2 getPredioSel() {
        return predioSel;
    }

    /**
     * @param predioSel the predioSel to set
     */
    public void setPredioSel(PredioV2 predioSel) {
        this.predioSel = predioSel;
    }

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
     * @return the zonaAnt
     */
    public int getZonaAnt() {
        return zonaAnt;
    }

    /**
     * @param zonaAnt the zonaAnt to set
     */
    public void setZonaAnt(int zonaAnt) {
        this.zonaAnt = zonaAnt;
    }

    /**
     * @return the sectorAnt
     */
    public int getSectorAnt() {
        return sectorAnt;
    }

    /**
     * @param sectorAnt the sectorAnt to set
     */
    public void setSectorAnt(int sectorAnt) {
        this.sectorAnt = sectorAnt;
    }

    /**
     * @return the manzanaAnt
     */
    public int getManzanaAnt() {
        return manzanaAnt;
    }

    /**
     * @param manzanaAnt the manzanaAnt to set
     */
    public void setManzanaAnt(int manzanaAnt) {
        this.manzanaAnt = manzanaAnt;
    }

    /**
     * @return the numPredioAnt
     */
    public int getNumPredioAnt() {
        return numPredioAnt;
    }

    /**
     * @param numPredioAnt the numPredioAnt to set
     */
    public void setNumPredioAnt(int numPredioAnt) {
        this.numPredioAnt = numPredioAnt;
    }

    /**
     * @return the propAnt
     */
    public int getPropAnt() {
        return propAnt;
    }

    /**
     * @param propAnt the propAnt to set
     */
    public void setPropAnt(int propAnt) {
        this.propAnt = propAnt;
    }

    /**
     * @return the derAcc
     */
    public int getDerAcc() {
        return derAcc;
    }

    /**
     * @param derAcc the derAcc to set
     */
    public void setDerAcc(int derAcc) {
        this.derAcc = derAcc;
    }
    
}
