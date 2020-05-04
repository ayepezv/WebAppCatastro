package catastro.logica.entidades;

import digitales.logica.entidades.Documento;
import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class PredioHistorico {

    private int idEliminacion;
    private PredioV2 predio;
    private Documento documento;
    private Usuario sessionUsuario;
    private String razonEliminacion;
    private String observaciones;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;

    public PredioHistorico() {
        predio = new PredioV2();
        documento = new Documento();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the predio
     */
    public PredioV2 getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(PredioV2 predio) {
        this.predio = predio;
    }

    /**
     * @return the documento
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
     * @return the razonEliminacion
     */
    public String getRazonEliminacion() {
        return razonEliminacion;
    }

    /**
     * @param razonEliminacion the razonEliminacion to set
     */
    public void setRazonEliminacion(String razonEliminacion) {
        this.razonEliminacion = razonEliminacion;
    }

    /**
     * @return the idEliminacion
     */
    public int getIdEliminacion() {
        return idEliminacion;
    }

    /**
     * @param idEliminacion the idEliminacion to set
     */
    public void setIdEliminacion(int idEliminacion) {
        this.idEliminacion = idEliminacion;
    }

}
