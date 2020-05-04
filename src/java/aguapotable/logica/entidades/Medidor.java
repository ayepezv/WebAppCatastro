package aguapotable.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;


public class Medidor {
    private int idMedidor;
    private int numMedidor;
    private String marca;
    private String numSerie;
    private int lecturaInicial;
    private String estado;
    private String tipo;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;
    private int int_medidor_activo;
    private int int_numero_esferas;
    private Timestamp ts_fecha_instalacion;
    private String chv_medidor_diametro;
    private String chv_medidor_ubicacion;
    private String chv_sello_medidor; 
    private int int_sello_numero;
    private int int_id_instalador;
    private int int_num_cuenta;

    public Medidor() {
        sessionUsuario = new Usuario();
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
     * @return the numMedidor
     */
    public int getNumMedidor() {
        return numMedidor;
    }

    /**
     * @param numMedidor the numMedidor to set
     */
    public void setNumMedidor(int numMedidor) {
        this.numMedidor = numMedidor;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the numSerie
     */
    public String getNumSerie() {
        return numSerie;
    }

    /**
     * @param numSerie the numSerie to set
     */
    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    /**
     * @return the lecturaInicial
     */
    public int getLecturaInicial() {
        return lecturaInicial;
    }

    /**
     * @param lecturaInicial the lecturaInicial to set
     */
    public void setLecturaInicial(int lecturaInicial) {
        this.lecturaInicial = lecturaInicial;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the estadoLogico
     */
    public String getEstadoLogico() {
        return estadoLogico;
    }

    /**
     * @param estadoLogico the estadoLogico to set
     */
    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    /**
     * @return the fechaRegistro
     */
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaActualizacion
     */
    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the fechaBaja
     */
    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
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
     * @return the int_medidor_activo
     */
    public int getInt_medidor_activo() {
        return int_medidor_activo;
    }

    /**
     * @param int_medidor_activo the int_medidor_activo to set
     */
    public void setInt_medidor_activo(int int_medidor_activo) {
        this.int_medidor_activo = int_medidor_activo;
    }

    /**
     * @return the int_numero_esferas
     */
    public int getInt_numero_esferas() {
        return int_numero_esferas;
    }

    /**
     * @param int_numero_esferas the int_numero_esferas to set
     */
    public void setInt_numero_esferas(int int_numero_esferas) {
        this.int_numero_esferas = int_numero_esferas;
    }

    /**
     * @return the ts_fecha_instalacion
     */
    public Timestamp getTs_fecha_instalacion() {
        return ts_fecha_instalacion;
    }

    /**
     * @param ts_fecha_instalacion the ts_fecha_instalacion to set
     */
    public void setTs_fecha_instalacion(Timestamp ts_fecha_instalacion) {
        this.ts_fecha_instalacion = ts_fecha_instalacion;
    }

    /**
     * @return the chv_medidor_diametro
     */
    public String getChv_medidor_diametro() {
        return chv_medidor_diametro;
    }

    /**
     * @param chv_medidor_diametro the chv_medidor_diametro to set
     */
    public void setChv_medidor_diametro(String chv_medidor_diametro) {
        this.chv_medidor_diametro = chv_medidor_diametro;
    }

    /**
     * @return the chv_medidor_ubicacion
     */
    public String getChv_medidor_ubicacion() {
        return chv_medidor_ubicacion;
    }

    /**
     * @param chv_medidor_ubicacion the chv_medidor_ubicacion to set
     */
    public void setChv_medidor_ubicacion(String chv_medidor_ubicacion) {
        this.chv_medidor_ubicacion = chv_medidor_ubicacion;
    }

    /**
     * @return the chv_sello_medidor
     */
    public String getChv_sello_medidor() {
        return chv_sello_medidor;
    }

    /**
     * @param chv_sello_medidor the chv_sello_medidor to set
     */
    public void setChv_sello_medidor(String chv_sello_medidor) {
        this.chv_sello_medidor = chv_sello_medidor;
    }

    /**
     * @return the int_sello_numero
     */
    public int getInt_sello_numero() {
        return int_sello_numero;
    }

    /**
     * @param int_sello_numero the int_sello_numero to set
     */
    public void setInt_sello_numero(int int_sello_numero) {
        this.int_sello_numero = int_sello_numero;
    }

    /**
     * @return the int_id_instalador
     */
    public int getInt_id_instalador() {
        return int_id_instalador;
    }

    /**
     * @param int_id_instalador the int_id_instalador to set
     */
    public void setInt_id_instalador(int int_id_instalador) {
        this.int_id_instalador = int_id_instalador;
    }

    /**
     * @return the int_num_cuenta
     */
    public int getInt_num_cuenta() {
        return int_num_cuenta;
    }

    /**
     * @param int_num_cuenta the int_num_cuenta to set
     */
    public void setInt_num_cuenta(int int_num_cuenta) {
        this.int_num_cuenta = int_num_cuenta;
    }

    
    
    
}
