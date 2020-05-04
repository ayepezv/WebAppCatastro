
package catastro.presentacion.beans;

import catastro.logica.entidades.ExcencionNoEdificado;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.logica.servicios.FExcencionNoEdificado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author gecudcop
 */

@ManagedBean
@ViewScoped

public class CtPrediosNoEdificaciones implements Serializable {
    
    private String razonNoEdificado;
    private int predioNoEdificado;
    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private PredioV2 objPredio;
    private ExcencionNoEdificado excencionSel;
    private List<ExcencionNoEdificado> lstExcPredioNoEdificado;
    private ExcencionNoEdificado objPredioNoEdificado;
    private Usuario sessionUsuario;
    private int codigo;
    private String msg;
    private int band;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String parametro;
    private ArrayList<PredioV2> lstPredios;
    
    public CtPrediosNoEdificaciones() {
        objPredio = new PredioV2();
        excencionSel = new ExcencionNoEdificado();
        objPredioNoEdificado = new ExcencionNoEdificado();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        obtenerPrediosNoEdificados();
       

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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    
    public void obtenerPrediosNoEdificados() {
        try {
            setLstExcPredioNoEdificado(FExcencionNoEdificado.obtenerExEspNoEdificado());
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerExEspNoEdificado() dice: " + e.getMessage());
        }
    }
    
    public void obtenerPrediosNoEdificadosUsuario() {
        try {
            setLstExcPredioNoEdificado(FExcencionNoEdificado.obtenerNoEdificadoDadoCriterio(getParametro()));
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: obtenerPrediosNoEdificadosUsuario dice: ",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void obtenerExcencionesClaveCat() {
        try {
            setLstExcPredioNoEdificado(FExcencionNoEdificado.obtenerNoEdificadosDadoZonaSectorManzanaPredio(getZona(), getSector(), getManzana(), getNumPredio()));
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: obtenerNoEdificadosDadoZonaSectorManzanaPredio dice: ",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void eliminar() {
        try {
            // objExcencion.getExcencion().setIdExccencion(idExencion);
            getExcencionSel().setSessionUsuario(getSessionUsuario());
            //objExcencion.setPredio(objPredio);
            setMsg(FExcencionNoEdificado.eliminarExEspNoEdificado(getExcencionSel()));
            Util.addSuccessMessage(getMsg());
            obtenerPrediosNoEdificados();
            resetearFitrosTabla("frmPrincipal:tblNoEdificados");
            setExcencionSel(new ExcencionNoEdificado());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void editar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    
     public void buscarPrediosPropietario() {
        try {
            setLstPredios(FuncionesPredio.encontrarPredio(getParametro()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    String.valueOf(getLstPredios().size()),
                    "Predios asociados"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Error: buscarPrediosPropietario dice: ",
                    e.getMessage()));
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

            strClave = strZona + strSector + strManzana + strPredio;

            this.setLstPredios(FuncionesPredio.encontrarPredio(strClave));

            Util.addSuccessMessage(strClave);
            Util.addSuccessMessage("Predios encontrados: " + getLstPredios().size());
            System.out.println("Clave catastral: " + strClave);
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }
    
     public void insertar() {
        try {
            
            getObjPredioNoEdificado().setSessionUsuario(getSessionUsuario());
            getObjPredioNoEdificado().setPredio(getObjPredio());
            setMsg(FExcencionNoEdificado.registrarExEspNoEdificado(getObjPredioNoEdificado()));
            Util.addSuccessMessage(getMsg());
            obtenerPrediosNoEdificados();
            resetearFitrosTabla("frmPrincipal:tblNoEdificados");
            setObjPredioNoEdificado(new ExcencionNoEdificado());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage().replace("ERROR:", "").replace("Hint:", ""));
        }
    }
     
    public void limpiar() {
        try {
            this.lstExcPredioNoEdificado(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
            setObjPredio(new PredioV2());
            setLstExcPredioNoEdificado(new ArrayList<>());
            setParametro("");
            setBand(0);
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }
    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
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
     * @return the lstExcPredioNoEdificado
     */
    public List<ExcencionNoEdificado> getLstExcPredioNoEdificado() {
        return lstExcPredioNoEdificado;
    }

    /**
     * @param lstExcPredioNoEdificado the lstExcPredioNoEdificado to set
     */
    public void setLstExcPredioNoEdificado(List<ExcencionNoEdificado> lstExcPredioNoEdificado) {
        this.lstExcPredioNoEdificado = lstExcPredioNoEdificado;
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

    private void lstExcPredioNoEdificado(ArrayList<Object> arrayList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the predioNoEdificado
     */
    public int getPredioNoEdificado() {
        return predioNoEdificado;
    }

    /**
     * @param predioNoEdificado the predioNoEdificado to set
     */
    public void setPredioNoEdificado(int predioNoEdificado) {
        this.predioNoEdificado = predioNoEdificado;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
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
     * @return the excencionSel
     */
    public ExcencionNoEdificado getExcencionSel() {
        return excencionSel;
    }

    /**
     * @param excencionSel the excencionSel to set
     */
    public void setExcencionSel(ExcencionNoEdificado excencionSel) {
        this.excencionSel = excencionSel;
    }

    /**
     * @return the razonNoEdificado
     */
    public String getRazonNoEdificado() {
        return razonNoEdificado;
    }

    /**
     * @param razonNoEdificado the razonNoEdificado to set
     */
    public void setRazonNoEdificado(String razonNoEdificado) {
        this.razonNoEdificado = razonNoEdificado;
    }

    
    /**
     * @return the objPredioNoEdificado
     */
    public ExcencionNoEdificado getObjPredioNoEdificado() {
        return objPredioNoEdificado;
    }

    /**
     * @param objPredioNoEdificado the objPredioNoEdificado to set
     */
    public void setObjPredioNoEdificado(ExcencionNoEdificado objPredioNoEdificado) {
        this.objPredioNoEdificado = objPredioNoEdificado;
    }
    
}
