/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.ExencionEspecial;
import catastro.logica.entidades.PredioExcencion;
import catastro.logica.entidades.PredioExcencionTer;
import catastro.logica.entidades.PredioExcencionTerCumplir;
import catastro.logica.entidades.PredioExcencionTerACumplir;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FPredioExcencion;
import catastro.logica.servicios.FuncionesPredio;
import catastro.logica.servicios.ServiciosExenciones;
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
 * @author Geovanny
 */
@ManagedBean
@ViewScoped
public class CtPrediosExcenciones implements Serializable {

    private int idExencion;
    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private PredioV2 objPredio;
    private PredioExcencion objExcencion;
    private PredioExcencion excencionSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private int codigo;
    private String msg;
    private int band;
    private String parametro;
    private String detalleExcencion;
    private String formaUso;
    private List<PredioExcencion> lstExcenciones;
    private List<ExencionEspecial> lstExencionesEsp;
    private List<PredioExcencionTer> lstExcencionTer;
    private List<PredioExcencionTer> lstExcencionTotal;
    private List<PredioExcencionTerCumplir> lstExcencionTerCumplir;
    private List<PredioExcencionTerACumplir> lstExcencionTerACumplir;
    /*PARA LISTAR LOS QUE VAN A CUMPLIR TERCERA EDAD Y SIN TENER PUESTA LA EXCENCION TERCERA EDAD */
    private ArrayList<PredioV2> lstPredios;

    public CtPrediosExcenciones() {
        objPredio = new PredioV2();
        objExcencion = new PredioExcencion(new Date());
        excencionSel = new PredioExcencion();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        obtenerPrediosExcenciones();
        obtenerExencionesEspeciales();
        obtenerPrediosExcencionesTer();
        obtenerPrediosExcencionesTerCumplir();
        obtenerPrediosExcencionesTerACumplir();

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

    public void obtenerPrediosExcenciones() {
        try {
            setLstExcenciones(FPredioExcencion.obtenerExcenciones());
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerPrediosExcenciones() dice: " + e.getMessage());
        }
    }

    public void obtenerExcencionesUsuario() {
        try {
            setLstExcenciones(FPredioExcencion.obtenerExcecnionesDadoCriterio(getParametro()));
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: obtenerExcencionesUsuario dice: ",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerExcencionesClaveCat() {
        try {
            setLstExcenciones(FPredioExcencion.obtenerExcecnionesDadoZonaSectorManzanaPredio(getZona(), getSector(), getManzana(), getNumPredio()));
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: obtenerExcencionesClaveCat dice: ",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerPrediosExcencionesTer() {
        try {
            setLstExcencionTer(FPredioExcencion.obtenerExcencionesTer());
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerPrediosExcencionesTer() dice: " + e.getMessage());
        }
    }

    /**
     * LISTA DE PERSONAS CON EXCENCION DE TERCERA EDAD QUE SUPERAN LAS
     * QUINIENTAS REMUNERACIONES UNNIFICADAS
     */
    public void obtenerPrediosExcencionesTerCumplir() {
        try {
            setLstExcencionTerCumplir(FPredioExcencion.obtenerExcencionesTerCumplir());
            for (int i = 0; i < getLstExcencionTer().size(); i++) {
                System.out.println("pos " + i + " propietario: " + getLstExcencionTer().get(i).getApellidos());
            }
        } catch (Exception e) {
            System.out.println("public void obtenerPrediosExcencionesTer() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPrediosExcencionesTer() dice: " + e.getMessage());
        }
    }

    /**
     * LISTA DE PERSONAS SIN EXCENCION DE TERCERA EDAD QUE VAN A CUMPLIR O
     * CUMPLIERON TERCERA EDAD
     */
    public void obtenerPrediosExcencionesTerACumplir() {
        try {
            setLstExcencionTerACumplir(FPredioExcencion.obtenerExcencionesTerACumplir());
            for (int i = 0; i < getLstExcencionTer().size(); i++) {
                System.out.println("pos " + i + " propietario: " + getLstExcencionTer().get(i).getApellidos());
            }
        } catch (Exception e) {
            System.out.println("public void obtenerPrediosExcencionesTer() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPrediosExcencionesTer() dice: " + e.getMessage());
        }
    }

    public void obtenerPrediosExcencionesTotal() {
        try {
            setLstExcencionTotal(FPredioExcencion.obtenerExcencionesTotal());
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerPrediosExcencionesTotal() dice: " + e.getMessage());
        }
    }

    public void obtenerExencionesEspeciales() {
        try {
            setLstExencionesEsp(ServiciosExenciones.obtenerExenciones());
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerExencionesEspeciales() dice: " + e.getMessage());
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

    public void limpiar() {
        try {
            this.setLstPredios(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
            setObjPredio(new PredioV2());
            setDetalleExcencion("");
            setIdExencion(0);
            setLstExcenciones(new ArrayList<>());
            setParametro("");
            setBand(0);
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void insertar() {
        try {
            getObjExcencion().getExcencion().setIdExccencion(getIdExencion());
            getObjExcencion().setSessionUsuario(getSessionUsuario());
            getObjExcencion().setPredio(getObjPredio());
            setMsg(FPredioExcencion.registrarExcencion(getObjExcencion()));
            Util.addSuccessMessage(getMsg());
            obtenerPrediosExcenciones();
            resetearFitrosTabla("frmPrincipal:tblExcenciones");
            setObjExcencion(new PredioExcencion());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage().replace("ERROR:", "").replace("Hint:", ""));
        }
    }

    public void editar() {
        try {
            // objExcencion.getExcencion().setIdExccencion(idExencion);
            getExcencionSel().setSessionUsuario(getSessionUsuario());
            //objExcencion.setPredio(objPredio);         
            System.out.println("Excencion Sel: " + getExcencionSel().getExcencion().getDescripcion() + " id excencion " + getExcencionSel().getExcencion().getIdExccencion());
            setMsg(FPredioExcencion.editarExcencion(getExcencionSel()));
            Util.addSuccessMessage(getMsg());
            obtenerPrediosExcenciones();
            resetearFitrosTabla("frmPrincipal:tblExcenciones");
            setExcencionSel(new PredioExcencion());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
        } catch (Exception e) {
            System.out.println("public void editar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage().replace("ERROR:", "").replace("Hint:", ""));
        }
    }

    public void eliminar() {
        try {
            // objExcencion.getExcencion().setIdExccencion(idExencion);
            getExcencionSel().setSessionUsuario(getSessionUsuario());
            //objExcencion.setPredio(objPredio);
            setMsg(FPredioExcencion.eliminarExcencion(getExcencionSel()));
            Util.addSuccessMessage(getMsg());
            obtenerPrediosExcenciones();
            resetearFitrosTabla("frmPrincipal:tblExcenciones");
            setExcencionSel(new PredioExcencion());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void editar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    public void obtenerExcencionDadoId() {
        try {
            setDetalleExcencion(ServiciosExenciones.obtenerExencionDadoCodigo(getIdExencion()).getDescripcion());
            setFormaUso(ServiciosExenciones.obtenerExencionDadoCodigo(getIdExencion()).getFormaUso());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Error: obtenerExcencionDadoId dice: ",
                    e.getMessage()));
        }
    }

    public void excenSel() {
        try {
            setDetalleExcencion(ServiciosExenciones.obtenerExencionDadoCodigo(getExcencionSel().getExcencion().getIdExccencion()).getDescripcion());
            setFormaUso(ServiciosExenciones.obtenerExencionDadoCodigo(getExcencionSel().getExcencion().getIdExccencion()).getFormaUso());
            getExcencionSel().getExcencion().setDescripcion(getDetalleExcencion());
            getExcencionSel().getExcencion().setFormaUso(getFormaUso());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("Actualizar excencion seleccionada dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstExcencionTernciones
     */
    public List<PredioExcencion> getLstExcenciones() {
        return lstExcenciones;
    }

    /**
     * @param lstExcenciones the lstExcenciones to set
     */
    public void setLstExcenciones(List<PredioExcencion> lstExcenciones) {
        this.lstExcenciones = lstExcenciones;
    }

    /**
     * @return the lstExencionesEsp
     */
    public List<ExencionEspecial> getLstExencionesEsp() {
        return lstExencionesEsp;
    }

    /**
     * @param lstExencionesEsp the lstExencionesEsp to set
     */
    public void setLstExencionesEsp(List<ExencionEspecial> lstExencionesEsp) {
        this.lstExencionesEsp = lstExencionesEsp;
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
     * @return the objExcencion
     */
    public PredioExcencion getObjExcencion() {
        return objExcencion;
    }

    /**
     * @param objExcencion the objExcencion to set
     */
    public void setObjExcencion(PredioExcencion objExcencion) {
        this.objExcencion = objExcencion;
    }

    /**
     * @return the excencionSel
     */
    public PredioExcencion getExcencionSel() {
        return excencionSel;
    }

    /**
     * @param excencionSel the excencionSel to set
     */
    public void setExcencionSel(PredioExcencion excencionSel) {
        this.excencionSel = excencionSel;
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
     * @return the idExencion
     */
    public int getIdExencion() {
        return idExencion;
    }

    /**
     * @param idExencion the idExencion to set
     */
    public void setIdExencion(int idExencion) {
        this.idExencion = idExencion;
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
     * @return the lstExcencionTer
     */
    public List<PredioExcencionTer> getLstExcencionTer() {
        return lstExcencionTer;
    }

    /**
     * @param lstExcencionTer the lstExcencionTer to set
     */
    public void setLstExcencionTer(List<PredioExcencionTer> lstExcencionTer) {
        this.lstExcencionTer = lstExcencionTer;
    }

    /**
     * @return the lstExcencionTotal
     */
    public List<PredioExcencionTer> getLstExcencionTotal() {
        return lstExcencionTotal;
    }

    /**
     * @param lstExcencionTotal the lstExcencionTotal to set
     */
    public void setLstExcencionTotal(List<PredioExcencionTer> lstExcencionTotal) {
        this.lstExcencionTotal = lstExcencionTotal;
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

    /**
     * @return the detalleExcencion
     */
    public String getDetalleExcencion() {
        return detalleExcencion;
    }

    /**
     * @param detalleExcencion the detalleExcencion to set
     */
    public void setDetalleExcencion(String detalleExcencion) {
        this.detalleExcencion = detalleExcencion;
    }

    /**
     * @return the lstExcencionTerCumplir
     */
    public List<PredioExcencionTerCumplir> getLstExcencionTerCumplir() {
        return lstExcencionTerCumplir;
    }

    /**
     * @param lstExcencionTerCumplir the lstExcencionTerCumplir to set
     */
    public void setLstExcencionTerCumplir(List<PredioExcencionTerCumplir> lstExcencionTerCumplir) {
        this.lstExcencionTerCumplir = lstExcencionTerCumplir;
    }

    /**
     * @return the lstExcencionTerACumplir
     */
    public List<PredioExcencionTerACumplir> getLstExcencionTerACumplir() {
        return lstExcencionTerACumplir;
    }

    /**
     * @param lstExcencionTerACumplir the lstExcencionTerACumplir to set
     */
    public void setLstExcencionTerACumplir(List<PredioExcencionTerACumplir> lstExcencionTerACumplir) {
        this.lstExcencionTerACumplir = lstExcencionTerACumplir;
    }

    /**
     * @return the formaUso
     */
    public String getFormaUso() {
        return formaUso;
    }

    /**
     * @param formaUso the formaUso to set
     */
    public void setFormaUso(String formaUso) {
        this.formaUso = formaUso;
    }

}
