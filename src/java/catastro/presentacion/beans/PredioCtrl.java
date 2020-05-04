package catastro.presentacion.beans;

import catastro.coeficientes.entidades.SectorHomogeneo;
import catastro.coeficientes.funciones.FSectorHomogeneo;
import catastro.logica.entidades.Acabados;
import catastro.logica.entidades.Barrio;
import catastro.logica.entidades.Bloque;
import catastro.logica.entidades.Calle;
import catastro.logica.entidades.Estructura;
import catastro.logica.entidades.ExencionEspecial;
import catastro.logica.entidades.FormaAdquisicion;
import catastro.logica.entidades.Instalaciones;
import catastro.logica.entidades.LocalizTopog;
import catastro.logica.entidades.Manzana;
import catastro.logica.entidades.Parroquia;
import catastro.logica.entidades.Piso;
import catastro.logica.entidades.Predio;
import catastro.logica.entidades.Prestamo;
import catastro.logica.entidades.Sector;
import catastro.logica.entidades.ServiciosUrbanos;
import catastro.logica.entidades.TipoLote;
import catastro.logica.entidades.TipoPersoneria;
import catastro.logica.entidades.UsoSuelo;
import catastro.logica.entidades.Zona;
import catastro.logica.servicios.FuncServiciosUrbanos;
import catastro.logica.servicios.ServFormaAdquisicion;
import catastro.logica.servicios.ServiciosAcabados;
import catastro.logica.servicios.ServiciosBarrios;
import catastro.logica.servicios.ServiciosBloques;
import catastro.logica.servicios.ServiciosCalles;
import catastro.logica.servicios.ServiciosEstructura;
import catastro.logica.servicios.ServiciosExenciones;
import catastro.logica.servicios.ServiciosInstalaciones;
import catastro.logica.servicios.ServiciosLocalizacion;
import catastro.logica.servicios.ServiciosManzana;
import catastro.logica.servicios.ServiciosParroquia;
import catastro.logica.servicios.ServiciosPiso;
import catastro.logica.servicios.ServiciosPredio;
import catastro.logica.servicios.ServiciosPropietario;
import catastro.logica.servicios.ServiciosSector;
import catastro.logica.servicios.ServiciosTipoLote;
import catastro.logica.servicios.ServiciosTipoPersoneria;
import catastro.logica.servicios.ServiciosUsoSuelo;
import catastro.logica.servicios.ServiciosZona;
import catastro.reportes.entidades.ReportePredios;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class PredioCtrl implements Serializable {

    private Predio predio;
    private Predio predioSel;
    private UsoSuelo usoSuelo;
    private LocalizTopog localizacion;
    private ServiciosUrbanos servicUrbanos;
    private int idParroquiaSel;
    private int idZonaSel;
    private int idSectorSel;
    private int idManzanaSel;
    private int idTipoPredioSel;
    private int numeroPredio;
    private int idBarrioSel;
    private int idCallePrincipal;
    private int idCalleSecundaria;
    private int idPersoneria;
    private int idPropietario;
    private int idExenciones;
    private int idFormaAdq;
    private ArrayList<Parroquia> lstParroquias;
    private ArrayList<Zona> lstZonas;
    private ArrayList<Sector> lstSectores;
    private ArrayList<Manzana> lstManzanas;
    private ArrayList<TipoLote> lstTiposLotes;
    private ArrayList<Barrio> lstBarrios;
    private ArrayList<Calle> lstCalles;
    private ArrayList<TipoPersoneria> lstTiposPersonerias;
    private ArrayList<Usuario> lstUsuarios;
    private ArrayList<ExencionEspecial> lstExenciones;
    private ArrayList<FormaAdquisicion> formasAdq;
    private Date fechaEscrituracion;
    private Date fechaRegistro;
    private ArrayList<Predio> lstPredios;
    private String parametro;
    private Bloque bloque;
    private Bloque bloqueSel;
    private Piso piso;
    private ArrayList<Bloque> lstBloques;
    private ArrayList<Piso> lstPisos;
    private Estructura estructura;
    private Acabados acabados;
    private Instalaciones instalaciones;
    private Usuario propietario;
    private Usuario propietarioSel;
    private String txtCedula;
    private List<SectorHomogeneo> lstSectoresHomogeneos;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public PredioCtrl() {
        predio = new Predio();
        predioSel = new Predio();
        usoSuelo = new UsoSuelo();
        localizacion = new LocalizTopog();
        servicUrbanos = new ServiciosUrbanos();
        bloque = new Bloque();
        bloqueSel = new Bloque();
        piso = new Piso();
        estructura = new Estructura();
        acabados = new Acabados();
        instalaciones = new Instalaciones();
        propietario = new Usuario();
        propietarioSel = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerParroquias();
        obtenerZonas();
        obtenerTiposLotes();
        obtenerCalles();
        obtenerPersonerias();
        obtenerUsuarios();
        obtenerExenciones();
        obtenerFormasAdq();
        obtenerPredios();
        obtenerSectoresHomogeneos();
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

    public void obtenerSectoresHomogeneos() {
        try {
            this.lstSectoresHomogeneos = FSectorHomogeneo.obtenerSectoresHomogeneos();
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerSectoresHomogeneos() dice: " + e.getMessage());
        }

    }

    public void obtenerParroquias() {
        try {
            lstParroquias = ServiciosParroquia.obtenerParroquiasArchidona();
        } catch (Exception e) {
            Util.addErrorMessage(" public void obtenerParroquias() dice: " + e.getMessage());
        }
    }

    public void obtenerZonas() {
        try {
            lstZonas = ServiciosZona.obtenerZonas();
        } catch (Exception e) {
            System.out.println(" public void obtenerZonas() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerZonas() dice: " + e.getMessage());
        }
    }

    public void obtenerSectorDadoZona() {
        try {
            lstSectores = ServiciosSector.obtenerSectoresDadoZona(idZonaSel);
        } catch (Exception e) {
            System.out.println(" public void obtenerSectorDadoZona() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerSectorDadoZona() dice: " + e.getMessage());
        }
    }

    public void obtenerManzanasDadoSector() {
        try {
            lstManzanas = ServiciosManzana.obtenerManzanasDadoSector(idSectorSel);
        } catch (Exception e) {
            System.out.println(" public void obtenerManzanasDadoSector() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerManzanasDadoSector() dice: " + e.getMessage());
        }
    }

    public void obtenerTiposLotes() {
        try {
            lstTiposLotes = ServiciosTipoLote.obtenerTiposLotes();
        } catch (Exception e) {
            System.out.println(" public void obtenerTiposLotes() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerTiposLotes() dice: " + e.getMessage());
        }
    }

    public void obtenerBarriosDadoParroquia() {
        try {
            lstBarrios = ServiciosBarrios.obtenerBarriosDadoParroquia(idParroquiaSel);
        } catch (Exception e) {
            System.out.println(" public void obtenerBarriosDadoParroquia() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerBarriosDadoParroquia() dice: " + e.getMessage());
        }
    }

    public void obtenerCalles() {
        try {
            lstCalles = ServiciosCalles.obtenerCalles();

        } catch (Exception e) {
            System.out.println(" public void obetnerCalles() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obetnerCalles() dice: " + e.getMessage());
        }
    }

    public void obtenerPersonerias() {
        try {
            lstTiposPersonerias = ServiciosTipoPersoneria.obtenerTiposPersoneria();
        } catch (Exception e) {
            System.out.println(" public void obtenerPersonerias() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerPersonerias() dice: " + e.getMessage());
        }
    }

    public void obtenerUsuarios() {
        try {
            lstUsuarios = ServiciosUsuario.obtenerUsuariosDadoEstado("A");
        } catch (Exception e) {
            System.out.println(" public void obtenerUsuarios() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerUsuarios() dice: " + e.getMessage());
        }
    }

    public void obtenerExenciones() {
        try {
            lstExenciones = ServiciosExenciones.obtenerExenciones();
        } catch (Exception e) {
            System.out.println(" public void obtenerExenciones() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerExenciones() dice: " + e.getMessage());
        }
    }

    public void obtenerFormasAdq() {
        try {
            formasAdq = ServFormaAdquisicion.obtenerFormasAdq();
        } catch (Exception e) {
            System.out.println(" public void obtenerFormasAdq() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerFormasAdq() dice: " + e.getMessage());
        }
    }

    public void insertarPredio() {
        try {
                                    
            Parroquia parr = ServiciosParroquia.obtenerParroquiaDadoCodigo(idParroquiaSel);
            predio.setParroquia(parr);//seteo parroquia
            //division politica territorial
            String divPolitica = "1503" + parr.getCodigo();
            predio.setDivPolitica(divPolitica);
            //manzana
            Manzana manzana = ServiciosManzana.obtenerManzanaDadoCodigo(idManzanaSel);
            predio.setManzana(manzana);
            //numero de predio
            predio.setNumPredio(numeroPredio);
            //tipo de lote
            TipoLote tl = ServiciosTipoLote.obtenerTipoLoteDadoCodigo(idTipoPredioSel);
            predio.setTipoLote(tl);
            //clave catastral local
            Zona zona = ServiciosZona.obtenerZonaDadoCodigo(idZonaSel);
            Sector sector = ServiciosSector.obtenerSectorDadoCodigo(idSectorSel);
            String numPredioString;
            if (numeroPredio <= 9) {
                numPredioString = "0" + numeroPredio;
            } else {
                numPredioString = "" + numeroPredio;
            }
            String claveCatastral = divPolitica + zona.getCodigo() + sector.getCodigo() + manzana.getNumManzana() + numPredioString + tl.getCodigo() + "0000";
            predio.setClaveCatastral(claveCatastral);
            //barrio 
            Barrio barrio = ServiciosBarrios.obtenerSectorDadoCodigo(idBarrioSel);
            predio.setBarrio(barrio);
            //calles
            Calle calleP = ServiciosCalles.obtenerCalleDadoCodigo(idCallePrincipal);
            Calle calleS = ServiciosCalles.obtenerCalleDadoCodigo(idCalleSecundaria);
            predio.setCallePrincipal(calleP);
            predio.setCalleSecundaria(calleS);
            //tipo de personeria
            TipoPersoneria tp = ServiciosTipoPersoneria.obtenerTipoPersoneriaDadoCodigo(idPersoneria);
            predio.setTipoPersoneria(tp);
            //propietario
            //Usuario us = ServiciosUsuario.buscarUsuarioDadoId(idPropietario);
            System.out.println("Propietario: " + propietario.getCedula() + " Apellidos: " + propietario.getApellidos());
            predio.setPropietario(propietarioSel);
            //tipo de excencion
            ExencionEspecial ex = ServiciosExenciones.obtenerExencionDadoCodigo(idExenciones);
            predio.setExcenEspecial(ex);
            //forma de adquisicion
            FormaAdquisicion fa = ServFormaAdquisicion.obtenerFormaAdqDadoCodigo(idFormaAdq);
            predio.setFormaAdq(fa);
            //prestamo
            predio.getPrestamo().setIdPrestamo(1);

            String msg = ServiciosPredio.registrarPredio(predio);
            Util.addSuccessMessage(msg);
            predio = new Predio();

            //inserto seccion 4
            {
                insertarUsoSuelo();
                insertarLocalizac();
                insertarServiciosUrbanos();
            }
        } catch (Exception e) {
            System.out.println(" public void insertarPredio() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarPredio() dice: " + e.getMessage());
        }
    }

    public void insertarUsoSuelo() {
        try {

            String msg = ServiciosUsoSuelo.insertarUsoSuelo(usoSuelo);
            Util.addSuccessMessage(msg);
            usoSuelo = new UsoSuelo();
        } catch (Exception e) {
            System.out.println(" public void insertarUsoSuelo() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarUsoSuelo() dice: " + e.getMessage());
        }
    }

    public void insertarLocalizac() {
        try {
            String msg = ServiciosLocalizacion.insertarLocalizac(localizacion);
            Util.addSuccessMessage(msg);
            localizacion = new LocalizTopog();
        } catch (Exception e) {
            System.out.println(" public void insertarLocalizac() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarLocalizac() dice: " + e.getMessage());
        }
    }

    public void insertarServiciosUrbanos() {
        try {
            String msg = FuncServiciosUrbanos.insertarServiciosUrbanos(servicUrbanos);
            Util.addSuccessMessage(msg);
            servicUrbanos = new ServiciosUrbanos();
        } catch (Exception e) {
            System.out.println(" public void insertarLocalizac() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarLocalizac() dice: " + e.getMessage());
        }
    }

    public void obtenerPredios() {
        try {
            lstPredios = ServiciosPredio.obtenerPredios();
        } catch (Exception e) {
            System.out.println(" public void obtenerPredios() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerPredios() dice: " + e.getMessage());
        }
    }

    public void encontrarPredio() {
        try {
            lstPredios = ServiciosPredio.encontrarPredios(parametro);
            System.out.println("parámetro de busquedas: " + parametro);
            System.out.println("total de predios: " + lstPredios.size());
        } catch (Exception e) {
            System.out.println(" public void encontrarPredio() dice: " + e.getMessage());
            Util.addErrorMessage(" public void encontrarPredio() dice: " + e.getMessage());
        }
    }

    public void obtenerBloques() {
        try {
            System.out.println("Predio Seleccionado: " + predioSel.getIdPredio());
            lstBloques = ServiciosBloques.obtenerBloquesDadoPredio(predioSel.getIdPredio());
        } catch (Exception e) {
            System.out.println(" public void obtenerBloques() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerBloques() dice: " + e.getMessage());
        }
    }

    public void insertarBloque() {
        try {
            bloque.setPredio(predioSel);
            String msg = ServiciosBloques.registrarBloque(bloque);
            Util.addSuccessMessage(msg);
            obtenerBloques();
            resetearFitrosTabla("frmBloque:tblBloques");
            bloque = new Bloque();
        } catch (Exception e) {
            System.out.println(" public void insertarBloque() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarBloque() dice: " + e.getMessage());
        }
    }

    public void obtenerPisosDadoBloque() {
        try {
            System.out.println("bloque seleccionado: " + bloqueSel.getIdBloque());
            lstPisos = ServiciosPiso.obtenerPisosDadoBloque(bloqueSel.getIdBloque());
        } catch (Exception e) {
            System.out.println(" public void obtenerPisosDadoBloque() dice: " + e.getMessage());
            Util.addErrorMessage(" public void obtenerPisosDadoBloque() dice: " + e.getMessage());
        }
    }

    public void insertarPiso() {
        try {
            System.out.println("Bloque sel: " + bloqueSel.getBloque());
            piso.setBloque(bloqueSel);
            String msg = ServiciosPiso.registrarPiso(piso);
            piso = new Piso();
            Util.addSuccessMessage(msg);
            {
                insertarEstructuraPiso();
                insertarAcabadosPiso();
                insertarInstalacionesPiso();
            }
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegPiso').hide()");
        } catch (Exception e) {
            System.out.println(" public void insertarPiso() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarPiso() dice: " + e.getMessage());
        }
    }

    public void insertarEstructuraPiso() {
        try {
            String msg = ServiciosEstructura.registrarEstructuraFicha(estructura);
            Util.addSuccessMessage(msg);
            estructura = new Estructura();
        } catch (Exception e) {
            System.out.println(" public void insertarEstructuraPiso() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarEstructuraPiso() dice: " + e.getMessage());
        }
    }

    public void insertarAcabadosPiso() {
        try {
            String msg = ServiciosAcabados.registrarAcabadosFicha(acabados);
            Util.addSuccessMessage(msg);
            acabados = new Acabados();
        } catch (Exception e) {
            System.out.println(" public void insertarAcabadosPiso() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarAcabadosPiso() dice: " + e.getMessage());
        }
    }

    public void insertarInstalacionesPiso() {
        try {
            String msg = ServiciosInstalaciones.registrarInstalacionesFicha(instalaciones);
            Util.addSuccessMessage(msg);
            instalaciones = new Instalaciones();
        } catch (Exception e) {
            System.out.println(" public void insertarInstalacionesPiso() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarInstalacionesPiso() dice: " + e.getMessage());
        }
    }

    public void insertarPropietario() {
        try {
            propietario.setCedula(txtCedula);
            propietario.setPassword(txtCedula);
            String msg = ServiciosPropietario.registrarPropietario(propietario,sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msg);
            propietario = new Usuario();
            propietarioSel = ServiciosUsuario.buscarUsuarioDadoCedula(txtCedula);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoPropietario').hide()");
        } catch (Exception e) {
            System.out.println(" public void insertarPropietario() dice: " + e.getMessage());
            Util.addErrorMessage(" public void insertarPropietario() dice: " + e.getMessage());
        }
    }

    public void buscarPropietarioDadoCriterio() {
        try {
            propietarioSel = ServiciosPropietario.encontrarPropietarios("A", txtCedula).get(0);
            Util.addSuccessMessage("Se obtuvo a: " + propietarioSel.getNombres() + " " + propietarioSel.getApellidos());
        } catch (Exception e) {
            System.out.println(" public void buscarPropietarioDadoCriterio() dice: " + e.getMessage());
            Util.addErrorMessage(" public void buscarPropietarioDadoCriterio() dice: " + e.getMessage());
        }
    }

    public void buscarPropietario() {
        try {

        } catch (Exception e) {
            System.out.println(" public void buscarPropietario() dice: " + e.getMessage());
            Util.addErrorMessage(" public void buscarPropietario() dice: " + e.getMessage());
        }
    }

    public void verReportePdfPorId() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        ReportePredios reporte = new ReportePredios();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/reportePredio2.jasper");
        reporte.getReportePdfPorId(ruta, predioSel.getIdPredio());
        FacesContext.getCurrentInstance().responseComplete();
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters y Setter"> 
    public ArrayList<Parroquia> getLstParroquias() {
        return lstParroquias;
    }

    public Usuario getPropietarioSel() {
        return propietarioSel;
    }

    public void setPropietarioSel(Usuario propietarioSel) {
        this.propietarioSel = propietarioSel;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public Estructura getEstructura() {
        return estructura;
    }

    public void setEstructura(Estructura estructura) {
        this.estructura = estructura;
    }

    public Acabados getAcabados() {
        return acabados;
    }

    public void setAcabados(Acabados acabados) {
        this.acabados = acabados;
    }

    public Instalaciones getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(Instalaciones instalaciones) {
        this.instalaciones = instalaciones;
    }

    public Bloque getBloqueSel() {
        return bloqueSel;
    }

    public void setBloqueSel(Bloque bloqueSel) {
        this.bloqueSel = bloqueSel;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public Predio getPredioSel() {
        return predioSel;
    }

    public void setPredioSel(Predio predioSel) {
        this.predioSel = predioSel;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public ArrayList<Predio> getLstPredios() {
        return lstPredios;
    }

    public void setLstPredios(ArrayList<Predio> lstPredios) {
        this.lstPredios = lstPredios;
    }

    public Date getFechaEscrituracion() {
        return fechaEscrituracion;
    }

    public void setFechaEscrituracion(Date fechaEscrituracion) {
        this.fechaEscrituracion = fechaEscrituracion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ArrayList<FormaAdquisicion> getFormasAdq() {
        return formasAdq;
    }

    public void setFormasAdq(ArrayList<FormaAdquisicion> formasAdq) {
        this.formasAdq = formasAdq;
    }

    public ArrayList<ExencionEspecial> getLstExenciones() {
        return lstExenciones;
    }

    public void setLstExenciones(ArrayList<ExencionEspecial> lstExenciones) {
        this.lstExenciones = lstExenciones;
    }

    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public void setLstParroquias(ArrayList<Parroquia> lstParroquias) {
        this.lstParroquias = lstParroquias;
    }

    public int getIdParroquiaSel() {
        return idParroquiaSel;
    }

    public void setIdParroquiaSel(int idParroquiaSel) {
        this.idParroquiaSel = idParroquiaSel;
    }

    public ArrayList<Bloque> getLstBloques() {
        return lstBloques;
    }

    public void setLstBloques(ArrayList<Bloque> lstBloques) {
        this.lstBloques = lstBloques;
    }

    public ArrayList<Piso> getLstPisos() {
        return lstPisos;
    }

    public void setLstPisos(ArrayList<Piso> lstPisos) {
        this.lstPisos = lstPisos;
    }

    public int getIdZonaSel() {
        return idZonaSel;
    }

    public ArrayList<TipoPersoneria> getLstTiposPersonerias() {
        return lstTiposPersonerias;
    }

    public void setLstTiposPersonerias(ArrayList<TipoPersoneria> lstTiposPersonerias) {
        this.lstTiposPersonerias = lstTiposPersonerias;
    }

    public void setIdZonaSel(int idZonaSel) {
        this.idZonaSel = idZonaSel;
    }

    public int getIdSectorSel() {
        return idSectorSel;
    }

    public void setIdSectorSel(int idSectorSel) {
        this.idSectorSel = idSectorSel;
    }

    public int getIdManzanaSel() {
        return idManzanaSel;
    }

    public ArrayList<Barrio> getLstBarrios() {
        return lstBarrios;
    }

    public void setLstBarrios(ArrayList<Barrio> lstBarrios) {
        this.lstBarrios = lstBarrios;
    }

    public void setIdManzanaSel(int idManzanaSel) {
        this.idManzanaSel = idManzanaSel;
    }

    public int getNumeroPredio() {
        return numeroPredio;
    }

    public void setNumeroPredio(int numeroPredio) {
        this.numeroPredio = numeroPredio;
    }

    public int getIdTipoPredioSel() {
        return idTipoPredioSel;
    }

    public void setIdTipoPredioSel(int idTipoPredioSel) {
        this.idTipoPredioSel = idTipoPredioSel;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public ArrayList<TipoLote> getLstTiposLotes() {
        return lstTiposLotes;
    }

    public void setLstTiposLotes(ArrayList<TipoLote> lstTiposLotes) {
        this.lstTiposLotes = lstTiposLotes;
    }

    public int getIdBarrioSel() {
        return idBarrioSel;
    }

    public void setIdBarrioSel(int idBarrioSel) {
        this.idBarrioSel = idBarrioSel;
    }

    public int getIdCallePrincipal() {
        return idCallePrincipal;
    }

    public void setIdCallePrincipal(int idCallePrincipal) {
        this.idCallePrincipal = idCallePrincipal;
    }

    public int getIdCalleSecundaria() {
        return idCalleSecundaria;
    }

    public void setIdCalleSecundaria(int idCalleSecundaria) {
        this.idCalleSecundaria = idCalleSecundaria;
    }

    public int getIdPersoneria() {
        return idPersoneria;
    }

    public void setIdPersoneria(int idPersoneria) {
        this.idPersoneria = idPersoneria;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getIdExenciones() {
        return idExenciones;
    }

    public void setIdExenciones(int idExenciones) {
        this.idExenciones = idExenciones;
    }

    public int getIdFormaAdq() {
        return idFormaAdq;
    }

    public void setIdFormaAdq(int idFormaAdq) {
        this.idFormaAdq = idFormaAdq;
    }

    public UsoSuelo getUsoSuelo() {
        return usoSuelo;
    }

    public void setUsoSuelo(UsoSuelo usoSuelo) {
        this.usoSuelo = usoSuelo;
    }

    public LocalizTopog getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(LocalizTopog localizacion) {
        this.localizacion = localizacion;
    }

    public ServiciosUrbanos getServicUrbanos() {
        return servicUrbanos;
    }

    public void setServicUrbanos(ServiciosUrbanos servicUrbanos) {
        this.servicUrbanos = servicUrbanos;
    }

    public ArrayList<Zona> getLstZonas() {
        return lstZonas;
    }

    public void setLstZonas(ArrayList<Zona> lstZonas) {
        this.lstZonas = lstZonas;
    }

    public ArrayList<Sector> getLstSectores() {
        return lstSectores;
    }

    public void setLstSectores(ArrayList<Sector> lstSectores) {
        this.lstSectores = lstSectores;
    }

    public ArrayList<Manzana> getLstManzanas() {
        return lstManzanas;
    }

    public void setLstManzanas(ArrayList<Manzana> lstManzanas) {
        this.lstManzanas = lstManzanas;
    }

    public String getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(String txtCedula) {
        this.txtCedula = txtCedula;
    }

    public ArrayList<Calle> getLstCalles() {
        return lstCalles;
    }

    public void setLstCalles(ArrayList<Calle> lstCalles) {
        this.lstCalles = lstCalles;
    }
    //</editor-fold>
}
