package aguapotable.presentacion.controladores;

import aguapotable.logica.entidades.Lectura;
import aguapotable.logica.entidades.Medidor;
import aguapotable.logica.funciones.FLectura;
import aguapotable.logica.funciones.FMedidor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorLecturasAP implements Serializable {

    private List<Lectura> lstLecturas;
    private List<Medidor> lstMedidores;
    private Lectura objLectura;
    private Lectura lecturaSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private int idMedidor;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaLectura;

    public ControladorLecturasAP() {
        objLectura = new Lectura();
        lecturaSel = new Lectura();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerLecturas();
        obtenerMedidores();
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

    public void obtenerMedidores() {
        try {
            this.setLstMedidores(FMedidor.obtenerMedidores());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void obtenerMedidores() dice: " + e.getMessage());
        }
    }

    public void obtenerLecturas() {
        try {
            setLstLecturas(FLectura.obtenerLecturas());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void obtenerLecturas() dice: " + e.getMessage());
        }
    }

    public void registrarLectura() {
        try {
            java.sql.Date sqlFechaLectura = Util.sqlDate(getFechaLectura());
            java.sql.Date sqlFechaInicio = Util.sqlDate(getFechaInicio());
            java.sql.Date sqlFechaFin = Util.sqlDate(getFechaFin());

            objLectura.setFechaLectura(sqlFechaLectura);
            objLectura.setInicioPeriodo(sqlFechaInicio);
            objLectura.setFinPeriodo(sqlFechaFin);

            objLectura.getMedidor().setIdMedidor(idMedidor);
            objLectura.setSessionUsuario(sessionUsuario);
            String msg = FLectura.insertarLecturaV1(objLectura);       
            
            objLectura = new Lectura();
            idMedidor = 0;
            fechaLectura = null;
            fechaInicio = null;
            fechaFin = null;
            
            obtenerLecturas();
            resetearFitrosTabla("frmLecturas:tblLecturas");
            Util.addSuccessMessage(msg);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarLectura').hide()");            
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void registrarLectura() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    /**
     * @return the lstLecturas
     */
    public List<Lectura> getLstLecturas() {
        return lstLecturas;
    }

    /**
     * @param lstLecturas the lstLecturas to set
     */
    public void setLstLecturas(List<Lectura> lstLecturas) {
        this.lstLecturas = lstLecturas;
    }

    /**
     * @return the objLectura
     */
    public Lectura getObjLectura() {
        return objLectura;
    }

    /**
     * @param objLectura the objLectura to set
     */
    public void setObjLectura(Lectura objLectura) {
        this.objLectura = objLectura;
    }

    /**
     * @return the lecturaSel
     */
    public Lectura getLecturaSel() {
        return lecturaSel;
    }

    /**
     * @param lecturaSel the lecturaSel to set
     */
    public void setLecturaSel(Lectura lecturaSel) {
        this.lecturaSel = lecturaSel;
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
     * @return the lstMedidores
     */
    public List<Medidor> getLstMedidores() {
        return lstMedidores;
    }

    /**
     * @param lstMedidores the lstMedidores to set
     */
    public void setLstMedidores(List<Medidor> lstMedidores) {
        this.lstMedidores = lstMedidores;
    }

    /**
     * @return the idMedidor
     */
    public int getIdMedidor() {
        return idMedidor;
    }

    /**
     * @param idMedidor the idMedidor to set
     */
    public void setIdMedidor(int idMedidor) {
        this.idMedidor = idMedidor;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the fechaLectura
     */
    public Date getFechaLectura() {
        return fechaLectura;
    }

    /**
     * @param fechaLectura the fechaLectura to set
     */
    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }
}
