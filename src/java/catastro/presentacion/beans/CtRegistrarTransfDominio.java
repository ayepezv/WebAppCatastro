package catastro.presentacion.beans;

import catastro.logica.entidades.PredioV2;
import catastro.logica.entidades.TransferenciaDominio;
import catastro.logica.servicios.FuncionesPredio;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;


@ManagedBean
@ViewScoped
public class CtRegistrarTransfDominio implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private PredioV2 objPredio;
    private TransferenciaDominio objTransferencia;
    private ArrayList<PredioV2> lstPredios;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public CtRegistrarTransfDominio() {
        objPredio = new PredioV2();
        objTransferencia = new TransferenciaDominio();
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
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
            System.out.println("public void buscarPredios() dice: " + e.getMessage());
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            this.setLstPredios(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
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
     * @return the objTransferencia
     */
    public TransferenciaDominio getObjTransferencia() {
        return objTransferencia;
    }

    /**
     * @param objTransferencia the objTransferencia to set
     */
    public void setObjTransferencia(TransferenciaDominio objTransferencia) {
        this.objTransferencia = objTransferencia;
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

   
}
