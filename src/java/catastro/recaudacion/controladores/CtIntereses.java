package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Intereses;
import catastro.recaudacion.funciones.FIntereses;
import catastro.reportes.entidades.ServiciosReportes;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtIntereses implements Serializable {

    private Intereses objInteres;
    private Intereses interesSel;
    private List<Intereses> lstIntereses;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private int anio;
    private int anioReporte;
    private int trimestre;
    private List<Intereses> lstAnios;
    private List<Intereses> lstTrimestres;
    private boolean habilitado;
    private boolean band;

    public CtIntereses() {
        objInteres = new Intereses();
        interesSel = new Intereses();
        sessionUsuario = new Usuario();
        habilitado = true;
        band = false;
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        ///

        obtenerAnios();;
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void buscarInteres() {
        try {
            if (getAnio() == 0) {
                obtenerIntereses();
            } else {
                setLstIntereses(FIntereses.obtenerInteresDadoAnio(getAnio()));
            }
        } catch (Exception e) {
            System.out.println("public void buscarInteres() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerIntereses() {
        try {
            setLstIntereses(FIntereses.obtenerIntereses());
        } catch (Exception e) {
            System.out.println("public void obtenerIntereses() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "public void obtenerIntereses() dice: ", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void habilitarReportes() {
        try {
            setBand(true);
            RequestContext.getCurrentInstance().update("frmPrincipal:pnlReportes");
        } catch (Exception e) {
            System.out.println("  public void habilitarReportes() dice: " + e.getMessage());
            Util.addErrorMessage("  public void habilitarReportes() dice: " + e.getMessage());
        }
    }

    public void obtenerAnios() {
        try {
            setLstAnios(FIntereses.obtenerAniosIntereses());
        } catch (Exception e) {
            System.out.println("Obtener anios dice: " + e.getMessage());
            Util.addErrorMessage("Obtener anios dice: " + e.getMessage());
        }
    }

    public void obtenerTrimestres() {
        try {
            Intereses inter = new Intereses("Todos los trimestres", 0);
            setLstTrimestres(FIntereses.obtenerTrimestresDadoAnio(getAnioReporte()));
            getLstTrimestres().add(inter);
            setHabilitado(false);
        } catch (Exception e) {
            System.out.println("Obtener trimestres dice: " + e.getMessage());
            Util.addErrorMessage("Obtener trimestres dice: " + e.getMessage());
        }
    }

    public void insertarIntereses() {
        try {
            Calendar fecha = Calendar.getInstance();
            int anioInt = fecha.get(Calendar.YEAR);
            getObjInteres().setAnio(anioInt);
            getObjInteres().setSessionUsuario(getSessionUsuario());
            String respuesta = FIntereses.insertarInteres(getObjInteres());
            Util.addSuccessMessage(respuesta);
            buscarInteres();
            resetearFitrosTabla("frmPrincipal:tblIntereses");
            setObjInteres(new Intereses());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertarIntereses() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void actualizarInteres() {
        try {
            getInteresSel().setSessionUsuario(getSessionUsuario());
            String msg = FIntereses.editarInteres(getInteresSel());
            Util.addSuccessMessage(msg);
            buscarInteres();
            resetearFitrosTabla("frmPrincipal:tblIntereses");
            setInteresSel(new Intereses());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
        } catch (Exception e) {
            System.out.println("public void actualizarInteres() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void imprimirReporte() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            if (getAnioReporte() == 0) {
                System.out.println("Reporte global");
                String ruta = servletContext.getRealPath("/reportes/ReporteInteres.jasper");
                reporte.tasaInteresGlobal(ruta);
                FacesContext.getCurrentInstance().responseComplete();
            } else if ((getAnioReporte() != 0) && (getTrimestre() == 0)) {
                System.out.println("reporte de todos los trimestres");
                String ruta = servletContext.getRealPath("/reportes/ReporteInteresV3.jasper");
                reporte.tasaInteresAnio(ruta, getAnioReporte(), getSessionUsuario().getIdPersona());
                FacesContext.getCurrentInstance().responseComplete();
            } else if ((getAnioReporte() != 0) && (getTrimestre() != 0)) {
                System.out.println("reporte año y trimestre");
                String ruta = servletContext.getRealPath("/reportes/ReporteInteresV2.jasper");
                reporte.tasaInteresAnioTrimestre(ruta, getAnioReporte(), getTrimestre(), getSessionUsuario().getIdPersona());
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            lstIntereses = new ArrayList<>();
            resetearFitrosTabla("frmPrincipal:tblIntereses");
            anio = 0;
            anioReporte = 0;
            trimestre = 0;
        } catch (Exception e) {
            System.out.println("public void limpiar() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

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
     * @return the objInteres
     */
    public Intereses getObjInteres() {
        return objInteres;
    }

    /**
     * @param objInteres the objInteres to set
     */
    public void setObjInteres(Intereses objInteres) {
        this.objInteres = objInteres;
    }

    /**
     * @return the interesSel
     */
    public Intereses getInteresSel() {
        return interesSel;
    }

    /**
     * @param interesSel the interesSel to set
     */
    public void setInteresSel(Intereses interesSel) {
        this.interesSel = interesSel;
    }

    /**
     * @return the lstIntereses
     */
    public List<Intereses> getLstIntereses() {
        return lstIntereses;
    }

    /**
     * @param lstIntereses the lstIntereses to set
     */
    public void setLstIntereses(List<Intereses> lstIntereses) {
        this.lstIntereses = lstIntereses;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnioReporte() {
        return anioReporte;
    }

    public void setAnioReporte(int anioReporte) {
        this.anioReporte = anioReporte;
    }

    public int getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }

    public List<Intereses> getLstAnios() {
        return lstAnios;
    }

    public void setLstAnios(List<Intereses> lstAnios) {
        this.lstAnios = lstAnios;
    }

    public List<Intereses> getLstTrimestres() {
        return lstTrimestres;
    }

    public void setLstTrimestres(List<Intereses> lstTrimestres) {
        this.lstTrimestres = lstTrimestres;
    }

    /**
     * @return the habilitado
     */
    public boolean isHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * @return the band
     */
    public boolean isBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(boolean band) {
        this.band = band;
    }

}
