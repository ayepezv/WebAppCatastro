package auditoria.logica.entidades;

import java.sql.Time;
import java.sql.Timestamp;
import master.logica.entidades.RolUsuario;

public class AccesosRol {

    private int codigo;
    private RolUsuario rolUsuario;
    private Timestamp fechaRegistro;
    private String ipAcceso;
    private String host;
    private Time horaIngreso;
    private Time horaSalida;

    public AccesosRol() {
        rolUsuario = new RolUsuario();
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
     * @return the rolUsuario
     */
    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    /**
     * @param rolUsuario the rolUsuario to set
     */
    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
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
     * @return the ipAcceso
     */
    public String getIpAcceso() {
        return ipAcceso;
    }

    /**
     * @param ipAcceso the ipAcceso to set
     */
    public void setIpAcceso(String ipAcceso) {
        this.ipAcceso = ipAcceso;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the horaIngreso
     */
    public Time getHoraIngreso() {
        return horaIngreso;
    }

    /**
     * @param horaIngreso the horaIngreso to set
     */
    public void setHoraIngreso(Time horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    /**
     * @return the horaSalida
     */
    public Time getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida the horaSalida to set
     */
    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

}
