package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.entidades.TitulosHistoricos;
import catastro.recaudacion.funciones.FTitulosHistoricos;
import catastro.reportes.entidades.ReporteLibroPredial;
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
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtTitulosHistoricos implements Serializable {

    private TitulosHistoricos titulo;
    private List<TitulosHistoricos> lstTitulos;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private List<Titulo> lstAnios;
    private int anioEmision;

    public CtTitulosHistoricos() {
        titulo = new TitulosHistoricos();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
//        obtenerTitulos();
        cargarAnios();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void cargarAnios() {
        try {
            setLstAnios(FTitulosHistoricos.obtenerAniosTitulos());
        } catch (Exception e) {
            System.out.println(" public void cargarAnios() dice: " + e.getMessage());
            Util.addErrorMessage(" public void cargarAnios() dice: " + e.getMessage());
        }
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

    public void obtenerTitulos() {
        try {
            setLstTitulos(FTitulosHistoricos.obtenerTitulosHistoricos());
        } catch (Exception e) {
            System.out.println("public void obtenerTitulos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void limpiar() {
        try {
            lstTitulos = new ArrayList<>();
            anioEmision = 0;
            RequestContext.getCurrentInstance().update("frmPrincipal:tblTitulos");
            RequestContext.getCurrentInstance().update("frmPrincipal:cmbAnios");            
        } catch (Exception e) {
            System.out.println(" public void limpiar() dice: " + e.getMessage());
            Util.addErrorMessage(" public void limpiar() dice: " + e.getMessage());
        }
    }

    public void obtenerTitulosDadoAnio() {
        try {
            if (anioEmision == 0) {
                Util.addSuccessMessage("Se van a obtener todos los títulos históricos.");
                obtenerTitulos();
            } else {
                setLstTitulos(FTitulosHistoricos.obtenerTitulosDadoAnio(getAnioEmision()));
            }

        } catch (Exception e) {
            System.out.println("public void obtenerTitulosDadoAnio() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "public void obtenerTitulosDadoAnio() dice: ", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    
    
    public void reportePorAnioAnulado() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ReporteLibroPredial reporte = new ReporteLibroPredial();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//LibroCatastralPorAnioAnulado.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getLibroPredialPorAnio(ruta, sessionUsuario.getIdPersona(), anioEmision);
            reporte = null;
        } catch (Exception e) {
            System.out.println("public void reportePorAnio() dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstTitulos
     */
    public List<TitulosHistoricos> getLstTitulos() {
        return lstTitulos;
    }

    /**
     * @param lstTitulos the lstTitulos to set
     */
    public void setLstTitulos(List<TitulosHistoricos> lstTitulos) {
        this.lstTitulos = lstTitulos;
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
     * @return the titulo
     */
    public TitulosHistoricos getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(TitulosHistoricos titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the lstAnios
     */
    public List<Titulo> getLstAnios() {
        return lstAnios;
    }

    /**
     * @param lstAnios the lstAnios to set
     */
    public void setLstAnios(List<Titulo> lstAnios) {
        this.lstAnios = lstAnios;
    }

    /**
     * @return the anioEmision
     */
    public int getAnioEmision() {
        return anioEmision;
    }

    /**
     * @param anioEmision the anioEmision to set
     */
    public void setAnioEmision(int anioEmision) {
        this.anioEmision = anioEmision;
    }

}
