/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.PredioV2;
import catastro.logica.entidades.RepositorioImagenes;
import catastro.logica.servicios.FRepositorioImagenes;
import catastro.logica.servicios.FuncionesPredio;
import digitales.logica.entidades.Imagen;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtRepImagenes implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private PredioV2 objPredio;
    private Imagen objImagen;
    private Imagen imagenSel;
    private RepositorioImagenes repositorioSel;
    private RepositorioImagenes objRepositorio;
    private List<RepositorioImagenes> lstImagenes;
    //manejo de imagenes
    private String nombreImagen;
    private UploadedFile archivoImagen;
    //cargar configuracion del  path
    private java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private int band;
    private String txtCedula;
    private ArrayList<PredioV2> lstPredios;
    private int bndImagen;
    private String categoriaImagen;

    public CtRepImagenes() {
        objRepositorio = new RepositorioImagenes();
        repositorioSel = new RepositorioImagenes();
        imagenSel = new Imagen();
        objImagen = new Imagen();
        objPredio = new PredioV2();
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

    public void registrar() {
        try {
            Util.addErrorMessage("Categoría: " + categoriaImagen);

            objImagen.setCategoria(categoriaImagen);
            getObjImagen().setSessionUsuario(getSessionUsuario());
            getObjRepositorio().setImagen(getObjImagen());
            getObjRepositorio().setSessionUsuario(getSessionUsuario());
            getObjRepositorio().setPredio(getObjPredio());
            String msg = FRepositorioImagenes.registrarImagenDadoPredioV2(getObjRepositorio());
            Util.addSuccessMessage(msg);
            buscarPredios();
            resetearFitrosTabla("frmPrincipal:tblImagenes");
            setObjRepositorio(new RepositorioImagenes());
            setObjImagen(new Imagen());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertarImagen').hide()");

            bndImagen = 0;
            categoriaImagen = "";
        } catch (Exception e) {
            System.out.println("public void registrar() dice: " + e.getMessage());
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }

    public void eliminarImagen() {
        try {
            String msg = FRepositorioImagenes.eliminarImagen(getRepositorioSel());
            Util.addSuccessMessage(msg);
            buscarPredios();
            resetearFitrosTabla("frmPrincipal:tblImagenes");
            setRepositorioSel(new RepositorioImagenes());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarImagen').hide()");
        } catch (Exception e) {
            System.out.println("public void eliminarImagen() dice: " + e.getMessage());
            Util.addErrorMessage("public void eliminarImagen() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            this.setLstImagenes(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
            setBand(0);
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
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

            setObjPredio(FuncionesPredio.encontrarPredio(strClave).get(0));

            System.out.println("Id predio: " + getObjPredio().getIdPredio());

            setLstImagenes(FRepositorioImagenes.obtenerImagenesDadoPredio(getObjPredio().getIdPredio()));

            if (getLstImagenes().size() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adverntencia", "No tiene imagenes asociadas."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se encontraron " + getLstImagenes().size() + " imágenes viculadas."));
            }
        } catch (Exception e) {
            System.out.println("public void buscarPredios() dice: " + e.getMessage());
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void obtenerPrediosDadoCedula() {
        try {
            setLstPredios(FuncionesPredio.encontrarPredio(getTxtCedula()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se encontraron " + lstPredios.size() + " coincidencias."));
        } catch (Exception e) {
            System.out.println("public void obtenerPrediosDadoCedula() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPrediosDadoCedula() dice: " + e.getMessage());
        }
    }

    public void obtenerImagenes() {
        try {
            setLstImagenes(FRepositorioImagenes.obtenerImagenesDadoPredio(getObjPredio().getIdPredio()));
            if (getLstImagenes().size() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adverntencia", "No tiene imagenes asociadas."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se encontraron " + getLstImagenes().size() + " imágenes viculadas."));
            }
        } catch (Exception e) {
            System.out.println("public void obtenerImagenes() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerImagenes() dice: " + e.getMessage());
        }
    }

    public void cargarArchivoImagen(FileUploadEvent e) {
        System.out.println("Entra al método cargar imagen");
        UploadedFile file = e.getFile();
        this.setArchivoImagen(file);
        System.out.println("Extensión de la imagen: " + file.getContentType());
        getObjImagen().setTipo(file.getContentType());
        System.out.println("Tamaño de la imagen: " + file.getSize());
        System.out.println("Nombre del archivo: " + file.getFileName());
        setNombreImagen(file.getFileName());
        System.out.println("Bandera de la categoria imagen: " + bndImagen);
        //byte[] contenido = file.getContents();
        byte[] contenido;
        try {
            contenido = this.getFileContents(e.getFile().getInputstream());
            if (guardarArchivo(file.getFileName(), contenido, bndImagen)) {
                Util.addSuccessMessage("Imagen guardada con éxito!!");
            } else {
                Util.addErrorMessage("Error al cargar la imagen");
            }
        } catch (IOException ex) {
            Logger.getLogger(CtRepImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean guardarArchivo(String nombre, byte[] contenido, int bandera) {
        String rutaImagenes = "";
        switch (bandera) {
            case 1:
                categoriaImagen = "FACHADA";
                rutaImagenes = getConfiguracion().getString("rutaFachadas");
                System.out.println("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                break;
            case 2:
                categoriaImagen = "CROQUIS";
                rutaImagenes = getConfiguracion().getString("rutaCroquis");
                System.out.println("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                break;
            case 3:
                categoriaImagen = "PLANIMETRIA";
                rutaImagenes = getConfiguracion().getString("rutaImagenes");
                System.out.println("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                break;
            case 4:
                categoriaImagen = "FOTO AEREA";
                rutaImagenes = getConfiguracion().getString("rutaFotosAerea");
                System.out.println("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                break;
            case 5:
                categoriaImagen = "OTRA";
                rutaImagenes = getConfiguracion().getString("rutaImagenes");
                System.out.println("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                break;
            default:
                categoriaImagen = "OTRA";
                rutaImagenes = getConfiguracion().getString("rutaImagenes");
                System.out.println("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                break;

        }

        int longitudRelativa = Integer.valueOf(getConfiguracion().getString("logitudRelativa"));
        File f = new File(rutaImagenes + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            //getObjImagen().setPath(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            String rutaTemp = Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length());
            getObjImagen().setPath(rutaTemp.replace('\\', '/'));
            getObjImagen().setTitulo(nombre);
            System.out.println("cargar objeto fos ");
            FileOutputStream fos = new FileOutputStream(f);
            System.out.println("escribir fos ");
            fos.write(contenido);

            return true;
        } catch (Exception e) {
            Util.mostrarMensaje(e.getMessage());
            return false;
        }
    }

    private byte[] getFileContents(InputStream in) {
        byte[] bytes = null;
        try {
            // write the inputStream to a FileOutputStream            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read = 0;
            bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
            bytes = bos.toByteArray();
            in.close();
            in = null;
            bos.flush();
            bos.close();
            bos = null;
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bytes;
    }

    public void categoriaSeleccionada() {
        try {
            String rutaImagenes = "";
            switch (bndImagen) {
                case 1:
                    categoriaImagen = "FACHADA";
                    rutaImagenes = getConfiguracion().getString("rutaFachadas");                    
                    Util.addSuccessMessage("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                    break;
                case 2:
                    categoriaImagen = "CROQUIS";
                    rutaImagenes = getConfiguracion().getString("rutaCroquis");
                    Util.addSuccessMessage("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                    break;
                case 3:
                    categoriaImagen = "PLANIMETRIA";
                    rutaImagenes = getConfiguracion().getString("rutaImagenes");
                    Util.addSuccessMessage("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                    break;
                case 4:
                    categoriaImagen = "FOTO AEREA";
                    rutaImagenes = getConfiguracion().getString("rutaFotosAerea");
                    Util.addSuccessMessage("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                    break;
                case 5:
                    categoriaImagen = "OTRA";
                    rutaImagenes = getConfiguracion().getString("rutaImagenes");
                    Util.addSuccessMessage("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                    break;
                default:
                    categoriaImagen = "OTRA";
                    rutaImagenes = getConfiguracion().getString("rutaImagenes");
                    Util.addSuccessMessage("Categoría: " + categoriaImagen + " ruta a guardar: " + rutaImagenes);
                    break;

            }
        } catch (Exception e) {
            Util.addSuccessMessage("Error: "+e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

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
     * @return the objImagen
     */
    public Imagen getObjImagen() {
        return objImagen;
    }

    /**
     * @param objImagen the objImagen to set
     */
    public void setObjImagen(Imagen objImagen) {
        this.objImagen = objImagen;
    }

    /**
     * @return the imagenSel
     */
    public Imagen getImagenSel() {
        return imagenSel;
    }

    /**
     * @param imagenSel the imagenSel to set
     */
    public void setImagenSel(Imagen imagenSel) {
        this.imagenSel = imagenSel;
    }

    /**
     * @return the repositorioSel
     */
    public RepositorioImagenes getRepositorioSel() {
        return repositorioSel;
    }

    /**
     * @param repositorioSel the repositorioSel to set
     */
    public void setRepositorioSel(RepositorioImagenes repositorioSel) {
        this.repositorioSel = repositorioSel;
    }

    /**
     * @return the objRepositorio
     */
    public RepositorioImagenes getObjRepositorio() {
        return objRepositorio;
    }

    /**
     * @param objRepositorio the objRepositorio to set
     */
    public void setObjRepositorio(RepositorioImagenes objRepositorio) {
        this.objRepositorio = objRepositorio;
    }

    /**
     * @return the lstImagenes
     */
    public List<RepositorioImagenes> getLstImagenes() {
        return lstImagenes;
    }

    /**
     * @param lstImagenes the lstImagenes to set
     */
    public void setLstImagenes(List<RepositorioImagenes> lstImagenes) {
        this.lstImagenes = lstImagenes;
    }

    /**
     * @return the nombreImagen
     */
    public String getNombreImagen() {
        return nombreImagen;
    }

    /**
     * @param nombreImagen the nombreImagen to set
     */
    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    /**
     * @return the archivoImagen
     */
    public UploadedFile getArchivoImagen() {
        return archivoImagen;
    }

    /**
     * @param archivoImagen the archivoImagen to set
     */
    public void setArchivoImagen(UploadedFile archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    /**
     * @return the Configuracion
     */
    public java.util.ResourceBundle getConfiguracion() {
        return Configuracion;
    }

    /**
     * @param Configuracion the Configuracion to set
     */
    public void setConfiguracion(java.util.ResourceBundle Configuracion) {
        this.Configuracion = Configuracion;
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
     * @return the txtCedula
     */
    public String getTxtCedula() {
        return txtCedula;
    }

    /**
     * @param txtCedula the txtCedula to set
     */
    public void setTxtCedula(String txtCedula) {
        this.txtCedula = txtCedula;
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
     * @return the bndImagen
     */
    public int getBndImagen() {
        return bndImagen;
    }

    /**
     * @param bndImagen the bndImagen to set
     */
    public void setBndImagen(int bndImagen) {
        this.bndImagen = bndImagen;
    }

    public String getCategoriaImagen() {
        return categoriaImagen;
    }

    public void setCategoriaImagen(String categoriaImagen) {
        this.categoriaImagen = categoriaImagen;
    }

}
