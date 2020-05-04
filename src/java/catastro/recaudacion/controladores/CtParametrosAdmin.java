package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.ParametrosAdmin;
import catastro.recaudacion.funciones.FParametrosAdmin;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtParametrosAdmin
  implements Serializable
{
  private List<ParametrosAdmin> lstParametros;
  private ParametrosAdmin objParametro;
  private ParametrosAdmin parametroSel;
  private HttpServletRequest httpServletRequest;
  private FacesContext faceContext;
  private Usuario sessionUsuario;
  
  public CtParametrosAdmin()
  {
    this.objParametro = new ParametrosAdmin();
    this.parametroSel = new ParametrosAdmin();
    this.sessionUsuario = new Usuario();
    this.faceContext = FacesContext.getCurrentInstance();
    this.httpServletRequest = ((HttpServletRequest)this.faceContext.getExternalContext().getRequest());
    
    obtenerParametros();
  }
  
  @PostConstruct
  public void init()
  {
    obtenerSession();
  }
  
  public void obtenerParametros()
  {
    try
    {
      this.lstParametros = FParametrosAdmin.obtenerParametros();
    }
    catch (Exception e)
    {
      System.out.println("public void obtenerParametros() dice: " + e.getMessage());
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
  }
  
  public void obtenerSession()
  {
    try
    {
      int intIdUsuario = ((Integer)getHttpServletRequest().getSession().getAttribute("idUsuario")).intValue();
      setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
      System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
    }
    catch (Exception e)
    {
      System.out.println("public void obtenerSession() dice: " + e.getMessage());
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
  }
  
  public void insertar()
  {
    try
    {
      this.objParametro.setSessionUsuario(this.sessionUsuario);
      String msg = FParametrosAdmin.insertarParametro(this.objParametro);
      Util.addSuccessMessage(msg);
      obtenerParametros();
      resetearFitrosTabla("frmPrincipal:tblParametros");
      this.objParametro = new ParametrosAdmin();
      DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
    }
    catch (Exception e)
    {
      System.out.println("public void insertar() dice: " + e.getMessage());
    }
  }
  
  public void editar()
  {
    try
    {
      this.parametroSel.setSessionUsuario(this.sessionUsuario);
      String msg = FParametrosAdmin.editarParametro(this.parametroSel);
      Util.addSuccessMessage(msg);
      obtenerParametros();
      resetearFitrosTabla("frmPrincipal:tblParametros");
      this.parametroSel = new ParametrosAdmin();
      DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
    }
    catch (Exception e)
    {
      System.out.println("public void insertar() dice: " + e.getMessage());
    }
  }
  
  public void eliminar()
  {
    try
    {
      this.parametroSel.setSessionUsuario(this.sessionUsuario);
      String msg = FParametrosAdmin.eliminarParametro(this.parametroSel);
      Util.addSuccessMessage(msg);
      obtenerParametros();
      resetearFitrosTabla("frmPrincipal:tblParametros");
      this.parametroSel = new ParametrosAdmin();
      DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
    }
    catch (Exception e)
    {
      System.out.println("public void insertar() dice: " + e.getMessage());
    }
  }
  
  public void resetearFitrosTabla(String id)
  {
    DataTable table = (DataTable)FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
    table.reset();
  }
  
  public List<ParametrosAdmin> getLstParametros()
  {
    return this.lstParametros;
  }
  
  public void setLstParametros(List<ParametrosAdmin> lstParametros)
  {
    this.lstParametros = lstParametros;
  }
  
  public ParametrosAdmin getObjParametro()
  {
    return this.objParametro;
  }
  
  public void setObjParametro(ParametrosAdmin objParametro)
  {
    this.objParametro = objParametro;
  }
  
  public ParametrosAdmin getParametroSel()
  {
    return this.parametroSel;
  }
  
  public void setParametroSel(ParametrosAdmin parametroSel)
  {
    this.parametroSel = parametroSel;
  }
  
  public HttpServletRequest getHttpServletRequest()
  {
    return this.httpServletRequest;
  }
  
  public void setHttpServletRequest(HttpServletRequest httpServletRequest)
  {
    this.httpServletRequest = httpServletRequest;
  }
  
  public FacesContext getFaceContext()
  {
    return this.faceContext;
  }
  
  public void setFaceContext(FacesContext faceContext)
  {
    this.faceContext = faceContext;
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
