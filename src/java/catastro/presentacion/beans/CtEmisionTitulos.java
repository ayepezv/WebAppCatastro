package catastro.presentacion.beans;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtEmisionTitulos implements Serializable {

    private List<Titulo> lstTitulosSimulados;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private int zona;
    private int sector;
    private int manzana;
    private int predio;
    private int zonaHasta;
    private int sectorHasta;
    private int manzanaHasta;
    private int predioHasta;
    private int band = 0;
    private int cont = 0;

    public CtEmisionTitulos() {
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void simularEmision() {
        try {
            String msg = FTitulos.simularTitulo(getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msg);
            obtenerTitulosSimulacion();
            resetearFitrosTabla("frmPrincipal:tblTitulos");
        } catch (Exception e) {
            System.out.println("public void simularEmision() dice: " + e.getMessage());
            Util.addErrorMessage("public void simularEmision() dice: " + e.getMessage());
        }
    }

    public void activarBusqueda() {
        try {
            if (getCont() > 0) {
                setBand(1);
                RequestContext.getCurrentInstance().update("frmPrincipal:pnlBuscarPorClave");
                RequestContext.getCurrentInstance().update("frmPrincipal:pnlNotificaciones");
            } else {
                Util.addErrorMessage("No se puede consultar los valores, sin haber realizado previamiento la simulación de los títulos. Por favor realice la emisión de prueba y vuelva a intentarlo.");
            }
        } catch (Exception e) {
            System.out.println("public void chequear() dice: " + e.getMessage());
            Util.addErrorMessage("public void chequear() dice: " + e.getMessage());
        }
    }

    public void activarNotificaciones() {
        try {
            if (getCont() > 0) {
                setBand(2);
                RequestContext.getCurrentInstance().update("frmPrincipal:pnlBuscarPorClave");
                RequestContext.getCurrentInstance().update("frmPrincipal:pnlNotificaciones");
            } else {
                Util.addErrorMessage("No se puede emitir las notificaciones, sin haber realizado previamiento la simulación de los títulos. Por favor realice la emisión de prueba y vuelva a intentarlo.");
            }
        } catch (Exception e) {
            System.out.println("public void activarNotificaciones() dice: " + e.getMessage());
            Util.addErrorMessage("public void activarNotificaciones() dice: " + e.getMessage());
        }
    }

    public void obtenerTitulosSimulacion() {
        try {
            setLstTitulosSimulados(FTitulos.consultarTitulosSimuladosV2());
            Util.addSuccessMessage(getLstTitulosSimulados().size() + " Títulos emitidos.");
            resetearFitrosTabla("frmPrincipal:tblTitulos");
            setCont(getLstTitulosSimulados().size());
        } catch (Exception e) {
            System.out.println("public void obtenerTitulosSimulacion() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerTitulosSimulacion() dice: " + e.getMessage());
        }
    }

    public void obtenerPorZonaSectorManzanaPredio() {
        try {
            setLstTitulosSimulados(FTitulos.obtenerTitulosPreviosDadoZonaSectorManzanaPredio(getZona(), getSector(), getManzana(), getPredio()));
            Util.addSuccessMessage(getLstTitulosSimulados().size() + " Título(s) Encontrado(s).");
        } catch (Exception e) {
            System.out.println("public void obtenerPorZonaSectorManzanaPredio() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPorZonaSectorManzanaPredio() dice: " + e.getMessage());
        }
    }

    public void obtenerNotificacionesZonaSectorManzanaPredio() {
        try {
            setLstTitulosSimulados(FTitulos.obtenerNotificacionesDesdeHasta(getZona(), getZonaHasta(), getSector(), getSectorHasta(), getManzana(), getManzanaHasta(), getPredio(), getPredioHasta()));
            Util.addSuccessMessage(getLstTitulosSimulados().size() + " Notificacion(es) generada(s).");
        } catch (Exception e) {
            System.out.println("public void obtenerNotificaciones() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerNotificaciones() dice: " + e.getMessage());
        }
    }

    public void limpiarTable() {
        try {
            setLstTitulosSimulados(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setPredio(0);
            setZonaHasta(0);
            setSectorHasta(0);
            setManzanaHasta(0);
            setPredioHasta(0);
            resetearFitrosTabla("frmPrincipal:tblTitulos");
            RequestContext.getCurrentInstance().update("frmPrincipal:pnlBuscarPorClave");
            RequestContext.getCurrentInstance().update("frmPrincipal:pnlNotificaciones");
        } catch (Exception e) {
            System.out.println("public void limpiarTable() dice: " + e.getMessage());
            Util.addErrorMessage("public void limpiarTable() dice: " + e.getMessage());
        }
    }

    public void limpiarGlobal() {
        try {
            limpiarTable();
            setBand(0);
            RequestContext.getCurrentInstance().update("frmPrincipal");
        } catch (Exception e) {
            System.out.println("public void limpiarGlobal() dice: " + e.getMessage());
            Util.addErrorMessage("public void limpiarGlobal() dice: " + e.getMessage());
        }
    }

    public void eliminarSimulacion() {
        try {

            if (getCont() > 0) {
                String msg = FTitulos.eliminarSimulacion();
                Util.addSuccessMessage(msg);
                setLstTitulosSimulados(new ArrayList<>());
                resetearFitrosTabla("frmPrincipal:tblTitulos");
                limpiarGlobal();
            } else {
                Util.addErrorMessage("No se han emitido títulos de prueba.");
            }
        } catch (Exception e) {
            System.out.println("public void obtenerTitulosSimulacion() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerTitulosSimulacion() dice: " + e.getMessage());
        }
    }

    public void emitirTitulos() {
        try {
            String msg = FTitulos.generarTitulo(getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msg);
        } catch (Exception e) {
            System.out.println("public void emitirTitulos() dice: " + e.getMessage());
            Util.addErrorMessage("public void emitirTitulos() dice: " + e.getMessage());
        }
    }

    public void verReporteNotificaciones() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/reportes/Notificaciones.jasper");
            reporte.listaNotificaciones(ruta, getZona(), getZonaHasta(), getSector(), getSectorHasta(), getManzana(), getManzanaHasta(), getPredio(), getPredioHasta(), getSessionUsuario().getIdPersona());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("public void verReporteNotificaciones() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    /**
     * @return the lstTitulosSimulados
     */
    public List<Titulo> getLstTitulosSimulados() {
        return lstTitulosSimulados;
    }

    /**
     * @param lstTitulosSimulados the lstTitulosSimulados to set
     */
    public void setLstTitulosSimulados(List<Titulo> lstTitulosSimulados) {
        this.lstTitulosSimulados = lstTitulosSimulados;
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
     * @return the cont
     */
    public int getCont() {
        return cont;
    }

    /**
     * @param cont the cont to set
     */
    public void setCont(int cont) {
        this.cont = cont;
    }

}
