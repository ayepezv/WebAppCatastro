package master.logica.entidades;

import java.io.Serializable;
import java.util.Date;

public class Usuario extends Persona implements Serializable {

    private String nick;
    private String mail;
    private String password;
    private Date ultimoAcceso;
    private String ultimaIp;

    public Usuario() {
    }
            
    /**
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick the nick to set
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the ultimoAcceso
     */
    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    /**
     * @param ultimoAcceso the ultimoAcceso to set
     */
    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    /**
     * @return the ultimaIp
     */
    public String getUltimaIp() {
        return ultimaIp;
    }

    /**
     * @param ultimaIp the ultimaIp to set
     */
    public void setUltimaIp(String ultimaIp) {
        this.ultimaIp = ultimaIp;
    }

    

}
