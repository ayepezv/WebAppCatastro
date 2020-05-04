package catastro.cem.controladores;

import catastro.cem.entidades.Mejora;
import catastro.cem.entidades.Obra;
import catastro.cem.entidades.TipoObra;
import catastro.cem.funciones.FMejora;
import catastro.cem.funciones.FObra;
import catastro.cem.funciones.FTipoObra;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class ControladorObras implements Serializable {

    private List<Obra> lstObras;
    private List<Mejora> lstMejoras;
    private List<TipoObra> lstTipoObras;
    private Obra objObra;
    private Obra obraSel;
    private int idMejora;
    private int idTipoObra;
    private String msgBd;
    private Mejora objMejora;
    private ArrayList<PredioV2> lstPredios;
    private PredioV2 predioSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public ControladorObras() {
        objMejora = new Mejora();
        objObra = new Obra();
        obraSel = new Obra();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerMejoras();
        obtenerTiposObras();
        obtenerPredios();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");
            sessionUsuario = ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario);
            System.out.println("Usuario Logueado: " + sessionUsuario.getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void obtenerMejoras() {
        try {
            this.setLstMejoras(FMejora.obtenerMejoras());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerMejoraDadoCodigo() {
        try {
            setObjMejora(FMejora.obtenerMejoraDadoCodigo(idMejora));
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerObrasDadoMejora() {
        try {
            System.out.println("Mejora seleccionadana: "+objMejora.getNombre());
            this.setLstObras(FObra.obtenerObraDadoMejora(getIdMejora()));
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerTiposObras() {
        try {
            this.setLstTipoObras(FTipoObra.obtenerTiposObras());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void registrarObra() {
        try {
            objObra.setSessionUsuario(sessionUsuario);
            this.getObjObra().setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(getIdTipoObra()));
            this.getObjObra().setMejora(FMejora.obtenerMejoraDadoCodigo(idMejora));
            this.setMsgBd(FObra.insertarObra(getObjObra()));
            Util.addSuccessMessage(msgBd);
            this.setObjObra(new Obra());
            obtenerObrasDadoMejora();
            resetearFitrosTabla("frmPrincipal:tblObras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarObras').hide();");
        } catch (Exception e) {
            System.out.println("Error: public void registrarObra() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void actualizarObra() {
        try {
            this.obraSel.setSessionUsuario(sessionUsuario);
            msgBd = FObra.actualizarObra(obraSel);
            Util.addErrorMessage(msgBd);
            obraSel = new Obra();
            resetearFitrosTabla("frmObras:tblObras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarObra').hide();");
            obtenerObrasDadoMejora();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void eliminarObra() {
        try {
            this.obraSel.setSessionUsuario(sessionUsuario);
            msgBd = FObra.eliminarObra(obraSel);
            Util.addErrorMessage(msgBd);
            obraSel = new Obra();
            resetearFitrosTabla("frmObras:tblObras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarObra').hide();");
            obtenerObrasDadoMejora();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerPredios() {
        try {
            this.setLstPredios(FuncionesPredio.seleccionarPredios());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    /**
     * @return the lstObras
     */
    public List<Obra> getLstObras() {
        return lstObras;
    }

    /**
     * @param lstObras the lstObras to set
     */
    public void setLstObras(List<Obra> lstObras) {
        this.lstObras = lstObras;
    }

    /**
     * @return the lstMejoras
     */
    public List<Mejora> getLstMejoras() {
        return lstMejoras;
    }

    /**
     * @param lstMejoras the lstMejoras to set
     */
    public void setLstMejoras(List<Mejora> lstMejoras) {
        this.lstMejoras = lstMejoras;
    }

    /**
     * @return the lstTipoObras
     */
    public List<TipoObra> getLstTipoObras() {
        return lstTipoObras;
    }

    /**
     * @param lstTipoObras the lstTipoObras to set
     */
    public void setLstTipoObras(List<TipoObra> lstTipoObras) {
        this.lstTipoObras = lstTipoObras;
    }

    /**
     * @return the idMejora
     */
    public int getIdMejora() {
        return idMejora;
    }

    /**
     * @param idMejora the idMejora to set
     */
    public void setIdMejora(int idMejora) {
        this.idMejora = idMejora;
    }

    /**
     * @return the obraSel
     */
    public Obra getObraSel() {
        return obraSel;
    }

    /**
     * @param obraSel the obraSel to set
     */
    public void setObraSel(Obra obraSel) {
        this.obraSel = obraSel;
    }

    /**
     * @return the objObra
     */
    public Obra getObjObra() {
        return objObra;
    }

    /**
     * @param objObra the objObra to set
     */
    public void setObjObra(Obra objObra) {
        this.objObra = objObra;
    }

    /**
     * @return the idTipoObra
     */
    public int getIdTipoObra() {
        return idTipoObra;
    }

    /**
     * @param idTipoObra the idTipoObra to set
     */
    public void setIdTipoObra(int idTipoObra) {
        this.idTipoObra = idTipoObra;
    }

    /**
     * @return the msgBd
     */
    public String getMsgBd() {
        return msgBd;
    }

    /**
     * @param msgBd the msgBd to set
     */
    public void setMsgBd(String msgBd) {
        this.msgBd = msgBd;
    }

    /**
     * @return the objMejora
     */
    public Mejora getObjMejora() {
        return objMejora;
    }

    /**
     * @param objMejora the objMejora to set
     */
    public void setObjMejora(Mejora objMejora) {
        this.objMejora = objMejora;
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

}
