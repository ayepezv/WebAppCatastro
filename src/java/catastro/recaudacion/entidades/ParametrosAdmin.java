package catastro.recaudacion.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class ParametrosAdmin
{
  private int idParametro;
  private String codigo;
  private String descripcion;
  private double valor;
  private String valorTextual;
  private String estadoLogico;
  private Timestamp fechaRegistro;
  private Timestamp fechaActualizacion;
  private Timestamp fechaBaja;
  private Usuario sessionUsuario;
  
  public ParametrosAdmin()
  {
    this.sessionUsuario = new Usuario();
  }
  
  public int getIdParametro()
  {
    return this.idParametro;
  }
  
  public void setIdParametro(int idParametro)
  {
    this.idParametro = idParametro;
  }
  
  public String getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(String codigo)
  {
    this.codigo = codigo;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public double getValor()
  {
    return this.valor;
  }
  
  public void setValor(double valor)
  {
    this.valor = valor;
  }
  
  public String getValorTextual()
  {
    return this.valorTextual;
  }
  
  public void setValorTextual(String valorTextual)
  {
    this.valorTextual = valorTextual;
  }
  
  public String getEstadoLogico()
  {
    return this.estadoLogico;
  }
  
  public void setEstadoLogico(String estadoLogico)
  {
    this.estadoLogico = estadoLogico;
  }
  
  public Timestamp getFechaRegistro()
  {
    return this.fechaRegistro;
  }
  
  public void setFechaRegistro(Timestamp fechaRegistro)
  {
    this.fechaRegistro = fechaRegistro;
  }
  
  public Timestamp getFechaActualizacion()
  {
    return this.fechaActualizacion;
  }
  
  public void setFechaActualizacion(Timestamp fechaActualizacion)
  {
    this.fechaActualizacion = fechaActualizacion;
  }
  
  public Timestamp getFechaBaja()
  {
    return this.fechaBaja;
  }
  
  public void setFechaBaja(Timestamp fechaBaja)
  {
    this.fechaBaja = fechaBaja;
  }
  
  public Usuario getSessionUsuario()
  {
    return this.sessionUsuario;
  }
  
  public void setSessionUsuario(Usuario sessionUsuario)
  {
    this.sessionUsuario = sessionUsuario;
  }
}
