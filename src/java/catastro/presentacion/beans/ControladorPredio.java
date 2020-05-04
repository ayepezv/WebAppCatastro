package catastro.presentacion.beans;

import catastro.coeficientes.entidades.CoefAguaPotable;
import catastro.coeficientes.entidades.CoefAlcantarillado;
import catastro.coeficientes.entidades.CoefEnergiaElectrica;
import catastro.coeficientes.entidades.CoefLocalizacion;
import catastro.coeficientes.entidades.CoefMaterialVial;
import catastro.coeficientes.entidades.CoefTopografia;
import catastro.coeficientes.entidades.CoefUsoVias;
import catastro.coeficientes.entidades.SectorHomogeneo;
import catastro.coeficientes.funciones.FCoefAguaPotable;
import catastro.coeficientes.funciones.FCoefAlcantarillado;
import catastro.coeficientes.funciones.FCoefEnergiaElectrica;
import catastro.coeficientes.funciones.FCoefLocalizacion;
import catastro.coeficientes.funciones.FCoefMaterialVial;
import catastro.coeficientes.funciones.FCoefTopografia;
import catastro.coeficientes.funciones.FCoefUsoVias;
import catastro.coeficientes.funciones.FSectorHomogeneo;
import catastro.gis.entidades.PredioGis;
import catastro.gis.funciones.FPredioGis;
import catastro.logica.entidades.Barrio;
import catastro.logica.entidades.Calle;
import catastro.logica.entidades.Dominio;
import catastro.logica.entidades.ExencionEspecial;
import catastro.logica.entidades.Manzana;
import catastro.logica.entidades.Parroquia;
import catastro.logica.entidades.PredioV2;
import catastro.logica.entidades.Sector;
import catastro.logica.entidades.Zona;
import catastro.logica.servicios.FuncionesPredio;
import catastro.logica.servicios.ServiciosBarrios;
import catastro.logica.servicios.ServiciosCalles;
import catastro.logica.servicios.ServiciosDominio;
import catastro.logica.servicios.ServiciosExenciones;
import catastro.logica.servicios.ServiciosManzana;
import catastro.logica.servicios.ServiciosParroquia;
import catastro.logica.servicios.ServiciosPropietario;
import catastro.logica.servicios.ServiciosSector;
import catastro.logica.servicios.ServiciosZona;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorPredio implements Serializable {
    
    private PredioV2 objPredio;
    private PredioV2 predioSel;
    private ArrayList<Parroquia> lstParroquias;
    private ArrayList<Zona> lstZonas;
    private ArrayList<Sector> lstSectores;
    private ArrayList<Manzana> lstManzanas;
    private ArrayList<Barrio> lstBarrios;
    private ArrayList<Calle> lstCalles;
    private ArrayList<ExencionEspecial> lstExcenciones;
    private ArrayList<Dominio> lstDominios;
    private ArrayList<Usuario> lstUsuarios;
    private ArrayList<PredioV2> lstPredios;
    private int zonaSel;
    private int sectorSel;
    private int manzanaSel;
    private int parroquiaSel;
    private String txtCriterioPersona;
    private Usuario propietarioSel;
    private Usuario repLegal;
    //fichas adjuntas    
    private String nombreFicha;
    private UploadedFile archivoFicha;
    //croquis adjunto
    private String nombreImagenCroquis;
    private UploadedFile archivoImagenCroquis;
    //fachada adjunta
    private String nombreImagenFachada;
    private UploadedFile archivoImagenFachada;
    //emplazamiento del predio
    private String nombreEmplazamiento;
    private UploadedFile archivoEmplazamiento;
    //cargar configuracion del  path
    private java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");
    private List<CoefLocalizacion> lstLocalizacion;
    private List<CoefTopografia> lstTopografia;
    private List<CoefUsoVias> lstUsoVias;
    private List<CoefMaterialVial> lstMaterialVial;
    private List<CoefAguaPotable> lstRedesAguaPotable;
    private List<CoefAlcantarillado> lstAlcantarilaldo;
    private List<CoefEnergiaElectrica> lstEnergElectrica;
    private List<SectorHomogeneo> lstSectoresHomogeneos;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private List<PredioGis> lstPrediosGis;
    private PredioGis objPredioGis;
    private String claveCatastral;
    
    public ControladorPredio() {
        objPredio = new PredioV2();
        predioSel = new PredioV2();
        propietarioSel = new Usuario();
        repLegal = new Usuario();
        objPredioGis = new PredioGis();
        
        {//funciones que se ejecutan con el constructor
            obtenerParroquias();
            obtenerZonas();
            seleccionarBarrios();
            seleccionarCalles();
            seleccionarExcenEspeciales();
            seleccionarDominios();
            obtenerPredios();
            obtenerLocalizaciones();
            obtenerTopografias();
            obtenerUsoVias();
            obtenerMaterialVial();
            obtenerRedAgua();
            obtenerAlcantarillado();
            obtenerEnergElectrica();
            obtenerSectoresHomogeneos();
        }
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
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }
    
    public void obtenerSectoresHomogeneos() {
        try {
            this.setLstSectoresHomogeneos(FSectorHomogeneo.obtenerSectoresHomogeneos());
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerSectoresHomogeneos() dice: " + e.getMessage());
        }
    }
    
    public void buscarPredioGis() {
        try {
            Util.addSuccessMessage("Clave Catastral: " + getClaveCatastral());
            setLstPrediosGis(FPredioGis.obtenerPredioGisDadoClaveCatastral(getClaveCatastral()));
            System.out.println("total de predios " + getLstPrediosGis().size());
            Util.addSuccessMessage("total de predios " + getLstPrediosGis().size());
        } catch (Exception e) {
            System.out.println("Error: public void buscarPredioGis() dice  " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void combinarPredios() {
        try {
            objPredio.setIdParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(objPredioGis.getParroquia().getIdParroquia()));
            objPredio.setZona((int) objPredioGis.getZona());
            objPredio.setSector((int) objPredioGis.getSector());
            objPredio.setManzana((int) objPredioGis.getManzana());
            objPredio.setNumPredio(objPredioGis.getNumPredio());
            objPredio.setClaveCatastral(objPredioGis.getClaveCatastral());
            objPredio.setClaveCatastralAnt(objPredioGis.getClaveCatastral());
            objPredio.setAreaTotalTerreno(objPredioGis.getArea());
            objPredio.setSectorHomogeneo(FSectorHomogeneo.obtenerSectorHomogeneoDadoCodigo(1));
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgBuscarPredioGis').hide()");
            Util.addSuccessMessage(objPredioGis.getClaveCatastral());
        } catch (Exception e) {
            System.out.println("Error: public void combinarPredios() dice  " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Método para obtener los coeficientes">    
    public void obtenerLocalizaciones() {
        try {
            this.setLstLocalizacion(FCoefLocalizacion.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerLocalizaciones() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void obtenerTopografias() {
        try {
            setLstTopografia(FCoefTopografia.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerTopografias() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void obtenerUsoVias() {
        try {
            this.setLstUsoVias(FCoefUsoVias.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerUsoVias() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void obtenerMaterialVial() {
        try {
            this.setLstMaterialVial(FCoefMaterialVial.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerMaterialVial() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void obtenerRedAgua() {
        try {
            this.setLstRedesAguaPotable(FCoefAguaPotable.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerRedAgua() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void obtenerAlcantarillado() {
        try {
            this.setLstAlcantarilaldo(FCoefAlcantarillado.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerAlcantarillado() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void obtenerEnergElectrica() {
        try {
            this.setLstEnergElectrica(FCoefEnergiaElectrica.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerEnergElectrica() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para obtener el listado de parroquias">    
    public void obtenerParroquias() {
        try {
            this.setLstParroquias(ServiciosParroquia.obtenerParroquiasArchidona());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para obtener el listado de las zonas">    
    public void obtenerSectores() {
        try {
            this.setLstSectores(ServiciosSector.obtenerSectoresDadoZona(getZonaSel()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para obtener el listado de los sectores dado la zona">    
    public void obtenerZonas() {
        try {
            this.setLstZonas(ServiciosZona.obtenerZonas());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para obtener las manzanas dado el sector">    
    public void obtenerManzanas() {
        try {
            this.setLstManzanas(ServiciosManzana.obtenerManzanasDadoSector(getSectorSel()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para insertar un predio">    
    public void insertarPredio() {
        try {
            getObjPredio().setSessionUsuario(getSessionUsuario());

            /*
             Parroquia parroquia = ServiciosParroquia.obtenerParroquiaDadoCodigo(getParroquiaSel());
             getObjPredio().setIdParroquia(parroquia);
             System.out.println("Parroquia: " + parroquia.getNombre());
             String zona = ServiciosZona.obtenerZonaDadoCodigo(getZonaSel()).getCodigo();
             getObjPredio().setZona(Integer.parseInt(zona));
             String sector = ServiciosSector.obtenerSectorDadoCodigo(getSectorSel()).getCodigo();
             getObjPredio().setSector(Integer.parseInt(sector));
             String manzana = ServiciosManzana.obtenerManzanaDadoCodigo(getManzanaSel()).getNumManzana();
             getObjPredio().setManzana(Integer.parseInt(manzana));
             String numeroPredio;
             if (getObjPredio().getNumPredio() < 10) {
             numeroPredio = "0" + getObjPredio().getNumPredio();
             } else {
             numeroPredio = String.valueOf(getObjPredio().getNumPredio());
             }
             String bloque;
             if (getObjPredio().getPropHorBloque() < 10) {
             bloque = "0" + getObjPredio().getPropHorBloque();
             } else {
             bloque = String.valueOf(getObjPredio().getPropHorBloque());
             }
             String piso;
             if (getObjPredio().getPropHorPiso() < 10) {
             piso = "0" + getObjPredio().getPropHorPiso();
             } else {
             piso = String.valueOf(getObjPredio().getPropHorPiso());
             }
             String unidad;
             if (getObjPredio().getPropHorUnidad() < 10) {
             unidad = "0" + getObjPredio().getPropHorUnidad();
             } else {
             unidad = String.valueOf(getObjPredio().getPropHorUnidad());
             }
             String claveCatastral = "1503" + zona + sector + manzana + numeroPredio + bloque + piso + unidad;
             System.out.println("Clave Catastral: " + claveCatastral);
             */
            /*
             getObjPredio().setClaveCatastral(claveCatastral);
             getObjPredio().setClaveCatastralAnt(claveCatastral);
             */
            getObjPredio().setRepLegal(getRepLegal());
            System.out.println("Representante legal: " + getRepLegal().getNombres());
            getObjPredio().setPropietario(getPropietarioSel());
            System.out.println("Propietario: " + getPropietarioSel().getApellidos());
            System.out.println("Fotografia: " + getObjPredio().getFotografia());
            System.out.println("Ficha : " + getObjPredio().getFichaAdjunta());
            System.out.println("Croquis: " + getObjPredio().getCroquis());
            System.out.println("Emplazamiento: " + getObjPredio().getEmplazamiento());
            
            {// REGISTRO DE PREDIOS
                String msg = FuncionesPredio.insertarPredio(getObjPredio());
                Util.addSuccessMessage(msg);
                setObjPredio(new PredioV2());
            }
            
        } catch (Exception e) {
            System.out.println("Insertar predio dice: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar los dominios">    
    public void seleccionarDominios() {
        try {
            this.setLstDominios(ServiciosDominio.seleccionarDominios());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar las exceciones especiales">    
    public void seleccionarExcenEspeciales() {
        try {
            this.setLstExcenciones(ServiciosExenciones.obtenerExenciones());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar las calles">    
    public void seleccionarCalles() {
        try {
            this.setLstCalles(ServiciosCalles.obtenerCalles());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccionar Calles dice: " + e.getMessage()));
            System.out.println("Seleccionar Calles dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar todos barrios">    
    public void seleccionarBarrios() {
        try {
            this.setLstBarrios(ServiciosBarrios.seleccionarBarrios());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println("Seleccionar barrios dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para buscar un propietario dado un criterio de busqueda ">    
    public void buscarPropietarioDadoCriterio() {
        try {
            setLstUsuarios(ServiciosPropietario.encontrarPropietarios("A", getTxtCriterioPersona()));
        } catch (Exception e) {
            System.out.println(" public void buscarPropietarioDadoCriterio() dice: " + e.getMessage());
            Util.addErrorMessage(" public void buscarPropietarioDadoCriterio() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para registrar un nuevo propietario ">    
    public void insertarPropietario() {
        try {
            getPropietarioSel().setCedula(getTxtCriterioPersona());
            getPropietarioSel().setPassword(getTxtCriterioPersona());
            String msg = ServiciosPropietario.registrarPropietario(getPropietarioSel(),1);
            Util.addSuccessMessage(msg);
            setPropietarioSel(new Usuario());
            setPropietarioSel(ServiciosUsuario.buscarUsuarioDadoCedula(getTxtCriterioPersona()));
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoPropietario').hide()");
        } catch (Exception e) {
            System.out.println(" public void insertarPropietario() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarPropietario() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para registrar un nuevo representante legal ">    
    public void insertarRepLegal() {
        try {
            getRepLegal().setCedula(getTxtCriterioPersona());
            getRepLegal().setPassword(getTxtCriterioPersona());
            String msg = ServiciosPropietario.registrarPropietario(getRepLegal(),1);
            Util.addSuccessMessage(msg);
            setRepLegal(new Usuario());
            setRepLegal(ServiciosUsuario.buscarUsuarioDadoCedula(getTxtCriterioPersona()));
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoRepLegal').hide()");
        } catch (Exception e) {
            System.out.println(" public void insertarRepLegal() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarRepLegal() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar los predios">    
    public void obtenerPredios() {
        try {
            this.setLstPredios(FuncionesPredio.seleccionarPredios());
            setPredioSel(getLstPredios().get(0));
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerPredios() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para la gestión de fichas de adjuntos">   
    public void cargarFicha(FileUploadEvent e) {
        System.out.println("Entra al método cargar documento");
        UploadedFile file = e.getFile();
        this.setArchivoFicha(file);
        System.out.println(file.getContentType());
        //objDocumento.setTipo(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getFileName());
        setNombreFicha(file.getFileName());
        //byte[] contenido = file.getContents();
        byte[] contenido;
        try {
            contenido = this.getFileContents(e.getFile().getInputstream());
            if (guardarArchivo(file.getFileName(), contenido)) {
                Util.addSuccessMessage("Documento guardado con éxito!!");
            } else {
                Util.addErrorMessage("Error al cargar el Documento");
            }
        } catch (IOException ex) {
            // Logger.getLogger(ImagenControlador.class.getName()).log(Level.SEVERE, null, ex);
            Util.addErrorMessage(ex.getMessage());
        }
    }
    
    public boolean guardarArchivo(String nombre, byte[] contenido) {
        String rutaFicha = getConfiguracion().getString("rutaFichas");
        int longitudRelativa = Integer.valueOf(getConfiguracion().getString("logitudRelativa"));
        File f = new File(rutaFicha + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            //objPredio.setFichaAdjunta(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            String path = Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length());
            String ruta = path.replace("\\", "/");
            getObjPredio().setFichaAdjunta(ruta);
            //objDocumento.setTitulo(nombre);
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos Getters y Setters">  
    /**
     * @return the nombreEmplazamiento
     */
    public String getNombreEmplazamiento() {
        return nombreEmplazamiento;
    }

    /**
     * @param nombreEmplazamiento the nombreEmplazamiento to set
     */
    public void setNombreEmplazamiento(String nombreEmplazamiento) {
        this.nombreEmplazamiento = nombreEmplazamiento;
    }

    /**
     * @return the archivoEmplazamiento
     */
    public UploadedFile getArchivoEmplazamiento() {
        return archivoEmplazamiento;
    }

    /**
     * @param archivoEmplazamiento the archivoEmplazamiento to set
     */
    public void setArchivoEmplazamiento(UploadedFile archivoEmplazamiento) {
        this.archivoEmplazamiento = archivoEmplazamiento;
    }

    /**
     * @return the nombreImagenCroquis
     */
    public String getNombreImagenCroquis() {
        return nombreImagenCroquis;
    }

    /**
     * @param nombreImagenCroquis the nombreImagenCroquis to set
     */
    public void setNombreImagenCroquis(String nombreImagenCroquis) {
        this.nombreImagenCroquis = nombreImagenCroquis;
    }

    /**
     * @return the archivoImagenCroquis
     */
    public UploadedFile getArchivoImagenCroquis() {
        return archivoImagenCroquis;
    }

    /**
     * @param archivoImagenCroquis the archivoImagenCroquis to set
     */
    public void setArchivoImagenCroquis(UploadedFile archivoImagenCroquis) {
        this.archivoImagenCroquis = archivoImagenCroquis;
    }

    /**
     * @return the nombreImagenFachada
     */
    public String getNombreImagenFachada() {
        return nombreImagenFachada;
    }

    /**
     * @param nombreImagenFachada the nombreImagenFachada to set
     */
    public void setNombreImagenFachada(String nombreImagenFachada) {
        this.nombreImagenFachada = nombreImagenFachada;
    }

    /**
     * @return the archivoImagenFachada
     */
    public UploadedFile getArchivoImagenFachada() {
        return archivoImagenFachada;
    }

    /**
     * @param archivoImagenFachada the archivoImagenFachada to set
     */
    public void setArchivoImagenFachada(UploadedFile archivoImagenFachada) {
        this.archivoImagenFachada = archivoImagenFachada;
    }

    /**
     * @return the nombreFicha
     */
    public String getNombreFicha() {
        return nombreFicha;
    }

    /**
     * @param nombreFicha the nombreFicha to set
     */
    public void setNombreFicha(String nombreFicha) {
        this.nombreFicha = nombreFicha;
    }

    /**
     * @return the archivoFicha
     */
    public UploadedFile getArchivoFicha() {
        return archivoFicha;
    }

    /**
     * @param archivoFicha the archivoFicha to set
     */
    public void setArchivoFicha(UploadedFile archivoFicha) {
        this.archivoFicha = archivoFicha;
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
     * @return the predioSel
     */
    public PredioV2 getPredioSel() {
        return predioSel;
    }

    /**
     * @return the lstExcenciones
     */
    public ArrayList<ExencionEspecial> getLstExcenciones() {
        return lstExcenciones;
    }

    /**
     * @param lstExcenciones the lstExcenciones to set
     */
    public void setLstExcenciones(ArrayList<ExencionEspecial> lstExcenciones) {
        this.lstExcenciones = lstExcenciones;
    }

    /**
     * @param predioSel the predioSel to set
     */
    public void setPredioSel(PredioV2 predioSel) {
        this.predioSel = predioSel;
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
     * @return the lstZonas
     */
    public ArrayList<Zona> getLstZonas() {
        return lstZonas;
    }

    /**
     * @param lstZonas the lstZonas to set
     */
    public void setLstZonas(ArrayList<Zona> lstZonas) {
        this.lstZonas = lstZonas;
    }

    /**
     * @return the zonaSel
     */
    public int getZonaSel() {
        return zonaSel;
    }

    /**
     * @param zonaSel the zonaSel to set
     */
    public void setZonaSel(int zonaSel) {
        this.zonaSel = zonaSel;
    }

    /**
     * @return the lstSectores
     */
    public ArrayList<Sector> getLstSectores() {
        return lstSectores;
    }

    /**
     * @param lstSectores the lstSectores to set
     */
    public void setLstSectores(ArrayList<Sector> lstSectores) {
        this.lstSectores = lstSectores;
    }

    /**
     * @return the sectorSel
     */
    public int getSectorSel() {
        return sectorSel;
    }

    /**
     * @param sectorSel the sectorSel to set
     */
    public void setSectorSel(int sectorSel) {
        this.sectorSel = sectorSel;
    }

    /**
     * @return the lstManzanas
     */
    public ArrayList<Manzana> getLstManzanas() {
        return lstManzanas;
    }

    /**
     * @param lstManzanas the lstManzanas to set
     */
    public void setLstManzanas(ArrayList<Manzana> lstManzanas) {
        this.lstManzanas = lstManzanas;
    }

    /**
     * @return the manzanaSel
     */
    public int getManzanaSel() {
        return manzanaSel;
    }

    /**
     * @param manzanaSel the manzanaSel to set
     */
    public void setManzanaSel(int manzanaSel) {
        this.manzanaSel = manzanaSel;
    }

    /**
     * @return the parroquiaSel
     */
    public int getParroquiaSel() {
        return parroquiaSel;
    }

    /**
     * @param parroquiaSel the parroquiaSel to set
     */
    public void setParroquiaSel(int parroquiaSel) {
        this.parroquiaSel = parroquiaSel;
    }

    /**
     * @return the lstBarrios
     */
    public ArrayList<Barrio> getLstBarrios() {
        return lstBarrios;
    }

    /**
     * @param lstBarrios the lstBarrios to set
     */
    public void setLstBarrios(ArrayList<Barrio> lstBarrios) {
        this.lstBarrios = lstBarrios;
    }

    /**
     * @return the lstCalles
     */
    public ArrayList<Calle> getLstCalles() {
        return lstCalles;
    }

    /**
     * @param lstCalles the lstCalles to set
     */
    public void setLstCalles(ArrayList<Calle> lstCalles) {
        this.lstCalles = lstCalles;
    }

    /**
     * @return the lstDominios
     */
    public ArrayList<Dominio> getLstDominios() {
        return lstDominios;
    }

    /**
     * @param lstDominios the lstDominios to set
     */
    public void setLstDominios(ArrayList<Dominio> lstDominios) {
        this.lstDominios = lstDominios;
    }

    /**
     * @return the txtCriterioPersona
     */
    public String getTxtCriterioPersona() {
        return txtCriterioPersona;
    }

    /**
     * @param txtCriterioPersona the txtCriterioPersona to set
     */
    public void setTxtCriterioPersona(String txtCriterioPersona) {
        this.txtCriterioPersona = txtCriterioPersona;
    }

    /**
     * @return the lstUsuarios
     */
    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    /**
     * @param lstUsuarios the lstUsuarios to set
     */
    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    /**
     * @return the propietarioSel
     */
    public Usuario getPropietarioSel() {
        return propietarioSel;
    }

    /**
     * @param propietarioSel the propietarioSel to set
     */
    public void setPropietarioSel(Usuario propietarioSel) {
        this.propietarioSel = propietarioSel;
    }

    /**
     * @return the repLegal
     */
    public Usuario getRepLegal() {
        return repLegal;
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
     * @param repLegal the repLegal to set
     */
    public void setRepLegal(Usuario repLegal) {
        this.repLegal = repLegal;
    }
    //</editor-fold>

    /**
     * @return the lstLocalizacion
     */
    public List<CoefLocalizacion> getLstLocalizacion() {
        return lstLocalizacion;
    }

    /**
     * @param lstLocalizacion the lstLocalizacion to set
     */
    public void setLstLocalizacion(List<CoefLocalizacion> lstLocalizacion) {
        this.lstLocalizacion = lstLocalizacion;
    }

    /**
     * @return the lstTopografia
     */
    public List<CoefTopografia> getLstTopografia() {
        return lstTopografia;
    }

    /**
     * @param lstTopografia the lstTopografia to set
     */
    public void setLstTopografia(List<CoefTopografia> lstTopografia) {
        this.lstTopografia = lstTopografia;
    }

    /**
     * @return the lstUsoVias
     */
    public List<CoefUsoVias> getLstUsoVias() {
        return lstUsoVias;
    }

    /**
     * @param lstUsoVias the lstUsoVias to set
     */
    public void setLstUsoVias(List<CoefUsoVias> lstUsoVias) {
        this.lstUsoVias = lstUsoVias;
    }

    /**
     * @return the lstMaterialVial
     */
    public List<CoefMaterialVial> getLstMaterialVial() {
        return lstMaterialVial;
    }

    /**
     * @param lstMaterialVial the lstMaterialVial to set
     */
    public void setLstMaterialVial(List<CoefMaterialVial> lstMaterialVial) {
        this.lstMaterialVial = lstMaterialVial;
    }

    /**
     * @return the lstRedesAguaPotable
     */
    public List<CoefAguaPotable> getLstRedesAguaPotable() {
        return lstRedesAguaPotable;
    }

    /**
     * @param lstRedesAguaPotable the lstRedesAguaPotable to set
     */
    public void setLstRedesAguaPotable(List<CoefAguaPotable> lstRedesAguaPotable) {
        this.lstRedesAguaPotable = lstRedesAguaPotable;
    }

    /**
     * @return the lstAlcantarilaldo
     */
    public List<CoefAlcantarillado> getLstAlcantarilaldo() {
        return lstAlcantarilaldo;
    }

    /**
     * @param lstAlcantarilaldo the lstAlcantarilaldo to set
     */
    public void setLstAlcantarilaldo(List<CoefAlcantarillado> lstAlcantarilaldo) {
        this.lstAlcantarilaldo = lstAlcantarilaldo;
    }

    /**
     * @return the lstEnergElectrica
     */
    public List<CoefEnergiaElectrica> getLstEnergElectrica() {
        return lstEnergElectrica;
    }

    /**
     * @param lstEnergElectrica the lstEnergElectrica to set
     */
    public void setLstEnergElectrica(List<CoefEnergiaElectrica> lstEnergElectrica) {
        this.lstEnergElectrica = lstEnergElectrica;
    }

    /**
     * @return the lstSectoresHomogeneos
     */
    public List<SectorHomogeneo> getLstSectoresHomogeneos() {
        return lstSectoresHomogeneos;
    }

    /**
     * @param lstSectoresHomogeneos the lstSectoresHomogeneos to set
     */
    public void setLstSectoresHomogeneos(List<SectorHomogeneo> lstSectoresHomogeneos) {
        this.lstSectoresHomogeneos = lstSectoresHomogeneos;
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
     * @return the lstPrediosGis
     */
    public List<PredioGis> getLstPrediosGis() {
        return lstPrediosGis;
    }

    /**
     * @param lstPrediosGis the lstPrediosGis to set
     */
    public void setLstPrediosGis(List<PredioGis> lstPrediosGis) {
        this.lstPrediosGis = lstPrediosGis;
    }

    /**
     * @return the objPredioGis
     */
    public PredioGis getObjPredioGis() {
        return objPredioGis;
    }

    /**
     * @param objPredioGis the objPredioGis to set
     */
    public void setObjPredioGis(PredioGis objPredioGis) {
        this.objPredioGis = objPredioGis;
    }

    /**
     * @return the claveCatastral
     */
    public String getClaveCatastral() {
        return claveCatastral;
    }

    /**
     * @param claveCatastral the claveCatastral to set
     */
    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }
    
}
