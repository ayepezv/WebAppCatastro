package catastro.cem.controladores;

import catastro.cem.entidades.BeneficiariosObras;
import catastro.cem.entidades.FormaPago;
import catastro.cem.entidades.Mejora;
import catastro.cem.entidades.Obra;
import catastro.cem.entidades.TipoObra;
import catastro.cem.funciones.FBenefDirectos;
import catastro.cem.funciones.FDetalleCuotasCem;
import catastro.cem.funciones.FFormaPago;
import catastro.cem.funciones.FMejora;
import catastro.cem.funciones.FObra;
import catastro.cem.funciones.FTipoObra;
import java.io.Serializable;
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
public class ControladorMejoras implements Serializable {

    private List<Mejora> lstMejoras;
    private Mejora objMejora;
    private Mejora mejoraSel;
    private String msgBd;
    private List<FormaPago> lstFormaPago;
    private int idFormaPago;
    private int idMejora;
    private int idTipoObra;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private List<Obra> lstObras;
    private String parametro;
    private Obra obraSel;
    private Obra objObra;
    private List<TipoObra> lstTipoObras;
    private List<BeneficiariosObras> lstBenefDirectos;
    private BeneficiariosObras beneficiario;

    public ControladorMejoras() {
        beneficiario = new BeneficiariosObras();
        objMejora = new Mejora();
        mejoraSel = new Mejora();
        objObra = new Obra();
        obraSel = new Obra();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerMejoras();
        obtenerFormaPago();
        obtenerTiposObras();
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
            System.out.println("public void insertarMejora() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void encontrarMejoras() {
        try {
            this.setLstMejoras(FMejora.encontrarMejoraDadoParametro(getParametro()));
        } catch (Exception e) {
            System.out.println("public void insertarMejora() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void insertarMejora() {
        try {
            getObjMejora().setSesionUsuario(getSessionUsuario());
            getObjMejora().setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(getIdFormaPago()));
            this.setMsgBd(FMejora.registrarMejora(getObjMejora()));
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarMejora').hide();");
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMsgBd(), getMsgBd());
            FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
            this.obtenerMejoras();
            resetearFitrosTabla("tblMejoras");
            this.setObjMejora(new Mejora());
        } catch (Exception e) {
            System.out.println("public void insertarMejora() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void editarMejora() {
        try {
            getMejoraSel().setSesionUsuario(getSessionUsuario());
            setMsgBd(FMejora.actualizarMejora(getMejoraSel()));
            Util.addSuccessMessage(getMsgBd());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarMejora').hide();");
            this.obtenerMejoras();
            resetearFitrosTabla("tblMejoras");
            setMejoraSel(new Mejora());
        } catch (Exception e) {
            System.out.println("Error public void editarMejora() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void eliminarMejora() {
        try {
            setMsgBd(FMejora.eliminarMejora(getMejoraSel().getIdMejora(), getSessionUsuario().getIdPersona()));
            Util.addSuccessMessage(getMsgBd());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarMejora').hide();");
            this.obtenerMejoras();
            resetearFitrosTabla("tblMejoras");
        } catch (Exception e) {
            System.out.println("Error public void eliminarMejora() dice: " + e.getMessage());
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerObrasDadoMejora() {
        try {
            setLstObras(FObra.obtenerObraDadoMejora(getMejoraSel().getIdMejora()));
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void obtenerFormaPago() {
        try {
            this.setLstFormaPago(FFormaPago.obtenerFormasPagos());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
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
            getObjObra().setSessionUsuario(getSessionUsuario());
            this.getObjObra().setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(getIdTipoObra()));
            //this.getObjObra().setMejora(FMejora.obtenerMejoraDadoCodigo(getIdMejora()));
            this.objObra.setMejora(mejoraSel);
            this.setMsgBd(FObra.insertarObra(getObjObra()));
            Util.addSuccessMessage(getMsgBd());
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
            this.getObraSel().setSessionUsuario(getSessionUsuario());
            setMsgBd(FObra.actualizarObra(getObraSel()));
            Util.addErrorMessage(getMsgBd());
            setObraSel(new Obra());
            resetearFitrosTabla("frmPrincipal:tblObras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarObra').hide();");
            obtenerObrasDadoMejora();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void eliminarObra() {
        try {
            this.getObraSel().setSessionUsuario(getSessionUsuario());
            setMsgBd(FObra.eliminarObra(getObraSel()));
            Util.addErrorMessage(getMsgBd());
            setObraSel(new Obra());
            resetearFitrosTabla("frmPrincipal:tblObras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarObra').hide();");
            obtenerObrasDadoMejora();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerBeneficiarios() {
        try {
            this.setLstBenefDirectos(FBenefDirectos.obtenerBenefDirectosDadoObra(obraSel.getIdObra()));
            System.out.println("Id mejora: " + lstBenefDirectos.get(0).getObra().getMejora().getIdMejora());
        } catch (Exception e) {
            Util.addErrorMessage("Error: " + e.getMessage());
            System.out.println("public void obtenerBeneficiarios() dice: " + e.getMessage());
        }
    }

    public void registarBeneficiariosObra() {
        try {
            //beneficiario = new BeneficiariosObras();
            for (int i = 0; i < lstBenefDirectos.size(); i++) {
                beneficiario = lstBenefDirectos.get(i);
                String message = FBenefDirectos.registrarBeneficiarioDirecto(beneficiario);
                System.out.println("Test inserción: " + message);
            }
            Util.addSuccessMessage("Beneficiarios  registrados exitosamente.");
            {
                String msg = FDetalleCuotasCem.registrarCuotasCem(obraSel.getIdObra());
                Util.addSuccessMessage(msg);
            }

        } catch (Exception e) {
            Util.addErrorMessage("Error: " + e.getMessage());
            System.out.println("public void registarBeneficiariosObra() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

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
     * @return the mejoraSel
     */
    public Mejora getMejoraSel() {
        return mejoraSel;
    }

    /**
     * @param mejoraSel the mejoraSel to set
     */
    public void setMejoraSel(Mejora mejoraSel) {
        this.mejoraSel = mejoraSel;
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
     * @return the lstFormaPago
     */
    public List<FormaPago> getLstFormaPago() {
        return lstFormaPago;
    }

    /**
     * @param lstFormaPago the lstFormaPago to set
     */
    public void setLstFormaPago(List<FormaPago> lstFormaPago) {
        this.lstFormaPago = lstFormaPago;
    }

    /**
     * @return the idFormaPago
     */
    public int getIdFormaPago() {
        return idFormaPago;
    }

    /**
     * @param idFormaPago the idFormaPago to set
     */
    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
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
     * @return the lstBenefDirectos
     */
    public List<BeneficiariosObras> getLstBenefDirectos() {
        return lstBenefDirectos;
    }

    /**
     * @param lstBenefDirectos the lstBenefDirectos to set
     */
    public void setLstBenefDirectos(List<BeneficiariosObras> lstBenefDirectos) {
        this.lstBenefDirectos = lstBenefDirectos;
    }

    /**
     * @return the beneficiario
     */
    public BeneficiariosObras getBeneficiario() {
        return beneficiario;
    }

    /**
     * @param beneficiario the beneficiario to set
     */
    public void setBeneficiario(BeneficiariosObras beneficiario) {
        this.beneficiario = beneficiario;
    }
}
