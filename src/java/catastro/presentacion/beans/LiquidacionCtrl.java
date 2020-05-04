package catastro.presentacion.beans;

import catastro.logica.entidades.DetalleUsoSuelo;
import catastro.logica.entidades.Liquidacion;
import catastro.logica.servicios.FDetalleUsoSuelo;
import catastro.logica.servicios.FLiquidacion;
import catastro.logica.servicios.FuncionesPredio;
import catastro.logica.servicios.ServiciosPredio;
import catastro.reportes.entidades.ReportePredios;
import catastro.reportes.entidades.ReporteTitulo;
import catastro.reportes.entidades.ServiciosReportes;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class LiquidacionCtrl implements Serializable {

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
    private Liquidacion objLiquidacion;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private List<Liquidacion> lstLiquidaciones;
    private String txtCriterio;
    private int band;
    private int band2;
    private DetalleUsoSuelo objUsoSuelo;

    public LiquidacionCtrl() {
        objUsoSuelo = new DetalleUsoSuelo();
        objLiquidacion = new Liquidacion();
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

            strClave = strZona + strSector + strManzana + strPredio + "00000000";

            setLstLiquidaciones(FLiquidacion.obtenerLiquidacionDadoClaveCatastral(strClave));
            Util.addSuccessMessage("Predios encontrados: " + getLstLiquidaciones().size());
            System.out.println("Clave catastral: " + strClave);
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
            System.out.println("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void buscarPredios2() {
        try {
            band2 = 1; //bandera cuando la busqueda es por clave catastral
            setLstLiquidaciones(FLiquidacion.obtenerLiquidacionesDadoZonaSectorManzana(getZona(), getSector(), getManzana(), getNumPredio()));
            Util.addSuccessMessage("Predios encontrados: " + getLstLiquidaciones().size());
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPredios2() dice: " + e.getMessage());
            System.out.println("public void buscarPredios2() dice: " + e.getMessage());
        }
    }

    public void buscarPorPropietario() {
        try {
            band2 = 2;// bandera cuando la bsuqueda es por propietario
            setLstLiquidaciones(FLiquidacion.obtenerLiquidacionDadoPropietario(getTxtCriterio()));
            Util.addSuccessMessage("Predios encontrados: " + getLstLiquidaciones().size());
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPorPropietario() dice: " + e.getMessage());
            System.out.println("public void buscarPorPropietario() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            this.setLstLiquidaciones(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
            setTxtCriterio(null);
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void verReporteCertificacion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/reportes/CertificacionPredial.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getReporteCertificacion(ruta, getObjLiquidacion().getPredio().getIdPredio(), getSessionUsuario().getIdPersona());
        } catch (Exception e) {
            System.out.println("ERROR public void verReporteCertificacion() dice: " + e.getMessage());
        }

    }

    public void reporteRegulacionUrbana() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/reportes/RegulacionUrbana.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.reporteRegulacionUrbana(ruta, getObjLiquidacion().getPredio().getIdPredio(), getSessionUsuario().getIdPersona());
        } catch (Exception e) {
            System.out.println("ERROR public void reporteRegulacionUrbana() dice: " + e.getMessage());
        }

    }

    public void verReporteFicha() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ReportePredios reporte = new ReportePredios();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/predios/ReporteFicha.jasper");
        reporte.getReporteFichaPredial(ruta, getObjLiquidacion().getPredio().getIdPredio(), getSessionUsuario().getIdPersona());
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void reevaluarConstruccionDadoPredio() {
        try {
            System.out.println("Predio a reevaluar: " + objLiquidacion.getPredio().getIdPredio());
            String msg = ServiciosPredio.reevaluarConstruccionDadoPredio(getObjLiquidacion().getPredio().getIdPredio(), getSessionUsuario().getIdPersona());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", msg));
            DefaultRequestContext.getCurrentInstance().execute("PF('wldgReevaluar').hide();");
            if (band2 == 1) {
                buscarPredios2();
            } else if (band2 == 2) {
                buscarPorPropietario();
            }
            DefaultRequestContext.getCurrentInstance().update("frmPrincipal:tblPredios");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void reevaluarConstruccionGolbal() {
        try {
            String msg = ServiciosPredio.reevaluarConstruccioGlobal(getSessionUsuario().getIdPersona());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", msg));
            DefaultRequestContext.getCurrentInstance().execute("PF('wldgReevaluacionGlobal').hide();");
            ///buscarPredios2();
            DefaultRequestContext.getCurrentInstance().update("frmPrincipal:tblPredios");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void reevaluarUsoSueloDadoPredio() {
        try {
            String msg = ServiciosPredio.reevaluarUsoSueloDadoPredio(getObjLiquidacion().getPredio().getIdPredio(), getSessionUsuario().getIdPersona());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", msg));
            DefaultRequestContext.getCurrentInstance().execute("PF('wldgReevaluar').hide();");
            if (band2 == 1) {
                buscarPredios2();
            } else if (band2 == 2) {
                buscarPorPropietario();
            }
            DefaultRequestContext.getCurrentInstance().update("frmPrincipal:tblPredios");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void reevaluarUsoSueloGlobal() {
        try {
            String msg = ServiciosPredio.reevaluarUsoSueloGlobal(getSessionUsuario().getIdPersona());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", msg));
            DefaultRequestContext.getCurrentInstance().execute("PF('wldgReevaluacionGlobal').hide();");
            ///buscarPredios2();
            DefaultRequestContext.getCurrentInstance().update("frmPrincipal:tblPredios");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void obtenerUsoSuelo() {
        try {
            System.out.println("Id predio: " + getObjLiquidacion().getPredio().getIdPredio());
            setObjUsoSuelo(FDetalleUsoSuelo.obtenerDetalleDadoCodigo(getObjLiquidacion().getPredio().getIdPredio()));
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerUsoSuelo() dice: " + e.getMessage());
            System.out.println("public void obtenerUsoSuelo() dice: " + e.getMessage());
        }
    }

    public void buscarPrediosDadoClaveAnterior() {
        try {
            lstLiquidaciones = FLiquidacion.obtenerLiquidacionesDadoClaveAnterior(zonaAnt, sectorAnt, manzanaAnt, numPredioAnt, propAnt, derAcc);
            Util.addSuccessMessage("Predios encontrados: " + getLstLiquidaciones().size());
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPredios2() dice: " + e.getMessage());
            System.out.println("public void buscarPredios2() dice: " + e.getMessage());
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
     * @return the lstLiquidaciones
     */
    public List<Liquidacion> getLstLiquidaciones() {
        return lstLiquidaciones;
    }

    /**
     * @param lstLiquidaciones the lstLiquidaciones to set
     */
    public void setLstLiquidaciones(List<Liquidacion> lstLiquidaciones) {
        this.lstLiquidaciones = lstLiquidaciones;
    }

    /**
     * @return the objLiquidacion
     */
    public Liquidacion getObjLiquidacion() {
        return objLiquidacion;
    }

    /**
     * @param objLiquidacion the objLiquidacion to set
     */
    public void setObjLiquidacion(Liquidacion objLiquidacion) {
        this.objLiquidacion = objLiquidacion;
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
     * @return the objUsoSuelo
     */
    public DetalleUsoSuelo getObjUsoSuelo() {
        return objUsoSuelo;
    }

    /**
     * @param objUsoSuelo the objUsoSuelo to set
     */
    public void setObjUsoSuelo(DetalleUsoSuelo objUsoSuelo) {
        this.objUsoSuelo = objUsoSuelo;
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
