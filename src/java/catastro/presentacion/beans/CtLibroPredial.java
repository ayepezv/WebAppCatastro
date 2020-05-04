package catastro.presentacion.beans;

import catastro.logica.entidades.Parroquia;
import catastro.logica.servicios.ServiciosParroquia;
import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
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

@ManagedBean
@ViewScoped
public class CtLibroPredial implements Serializable {

    private List<Titulo> lstAnios;
    private List<Titulo> lstTitulos;
    private ArrayList<Parroquia> lstParroquias;
    private int numZona;
    private int numSector;
    private int numManzana;
    private int numPredio;
    private int anio;
    private int idParroquia;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtLibroPredial() {
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerAnios();
        obtenerParroquias();
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

    public void obtenerAnios() {
        try {
            setLstAnios(FTitulos.obtenerAniosTitulos());
        } catch (Exception e) {
            System.out.println("public void obtenerAnios() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerParroquias() {
        try {
            setLstParroquias(ServiciosParroquia.obtenerParroquiasArchidona());
        } catch (Exception e) {
            System.out.println("public void obtenerAnios() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void libroPredialPorAnio() {
        try {
            System.out.println("Año: " + getAnio());
            setLstTitulos(FTitulos.obtenerTituloDadoAnio(getAnio()));
        } catch (Exception e) {
            System.out.println("public void libroPredialPorAnio() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void libroPredialPorAnioVencida() {
        try {
            System.out.println("Año: " + getAnio());
            setLstTitulos(FTitulos.obtenerTituloDadoAnioVencida(getAnio()));
        } catch (Exception e) {
            System.out.println("public void libroPredialPorAnioVencida() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    
    
    public void libroPredialPorAnioAnulado() {
        try {
            System.out.println("Año: " + getAnio());
            setLstTitulos(FTitulos.obtenerTituloDadoAnioAnulados(getAnio()));
        } catch (Exception e) {
            System.out.println("public void libroPredialPorAnio() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void libroPredialPorParroquia() {
        try {
            System.out.println("Año: " + getAnio() + " Parroquia: " + getIdParroquia());
            setLstTitulos(FTitulos.obtenerTitulodadoAnioParroquia(getAnio(), getIdParroquia()));
        } catch (Exception e) {
            System.out.println("public void libroPredialPorParroquia() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void libroPredialPorZona() {
        try {
            setLstTitulos(FTitulos.obtenerTitulodadoAnioZonaSectorManzanaPredio(anio, numZona, numSector, numManzana, numPredio));
        } catch (Exception e) {
            System.out.println("public void libroPredialPorZona() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reportePorAnio() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ReporteLibroPredial reporte = new ReporteLibroPredial();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//LibroCatastralPorAnio.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getLibroPredialPorAnio(ruta, sessionUsuario.getIdPersona(), anio);
            reporte = null;
        } catch (Exception e) {
            System.out.println("public void reportePorAnio() dice: " + e.getMessage());
        }
    }
    
     public void reportePorAnioVencida() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ReporteLibroPredial reporte = new ReporteLibroPredial();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//LibroCatastralPorAnioVencida.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getLibroPredialPorAnio(ruta, sessionUsuario.getIdPersona(), anio);
            reporte = null;
        } catch (Exception e) {
            System.out.println("public void reportePorAnio() dice: " + e.getMessage());
        }
    }
    
    public void reportePorAnioAnulado() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ReporteLibroPredial reporte = new ReporteLibroPredial();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//LibroCatastralPorAnioAnulado.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getLibroPredialPorAnio(ruta, sessionUsuario.getIdPersona(), anio);
            reporte = null;
        } catch (Exception e) {
            System.out.println("public void reportePorAnio() dice: " + e.getMessage());
        }
    }

    public void reportePorParroquia() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ReporteLibroPredial reporte = new ReporteLibroPredial();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//LibroCatastralPorParroquia.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getLibroPredialPorParroquia(ruta, sessionUsuario.getIdPersona(), anio, idParroquia);
            reporte = null;
        } catch (Exception e) {
            System.out.println("public void reportePorAnio() dice: " + e.getMessage());
        }
    }

    public void reportePorZona() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ReporteLibroPredial reporte = new ReporteLibroPredial();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//LibroCatastralPorZona.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getLibroPredialPorZona(ruta, sessionUsuario.getIdPersona(), anio, numZona, numSector, numManzana, numPredio);
            reporte = null;
        } catch (Exception e) {
            System.out.println("public void reportePorAnio() dice: " + e.getMessage());
        }
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
     * @return the lstParroquias
     */
    public ArrayList<Parroquia> getLstParroquias() {
        return lstParroquias;
    }

    /**
     * @param lstParroquias the lstParroquias to set
     */
    public void setLstParroquias(ArrayList<Parroquia> lstParroquias) {
        this.lstParroquias = lstParroquias;
    }

    /**
     * @return the numZona
     */
    public int getNumZona() {
        return numZona;
    }

    /**
     * @param numZona the numZona to set
     */
    public void setNumZona(int numZona) {
        this.numZona = numZona;
    }

    /**
     * @return the numSector
     */
    public int getNumSector() {
        return numSector;
    }

    /**
     * @param numSector the numSector to set
     */
    public void setNumSector(int numSector) {
        this.numSector = numSector;
    }

    /**
     * @return the numManzana
     */
    public int getNumManzana() {
        return numManzana;
    }

    /**
     * @param numManzana the numManzana to set
     */
    public void setNumManzana(int numManzana) {
        this.numManzana = numManzana;
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
     * @return the idParroquia
     */
    public int getIdParroquia() {
        return idParroquia;
    }

    /**
     * @param idParroquia the idParroquia to set
     */
    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

}
