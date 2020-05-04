package auditoria.presentacion.beans;

import auditoria.logica.entidades.AccesoSistema;
import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@ViewScoped
public class EstadisticasAccesos implements Serializable {

    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createModel();
    }

    private void createModel() {
        barModel = initBarModel();
        barModel.setTitle("Accesos al sistema");
        barModel.setLegendPosition("ne");
        barModel.setShowPointLabels(true);
        barModel.setAnimate(true);
        Axis xAxis = barModel.getAxis(AxisType.X);        
        xAxis.setTickAngle(315);
        
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Accesos");
        yAxis.setMin(0);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ArrayList<AccesoSistema> usuarios = new ArrayList<>();
        ArrayList<AccesoSistema> logins = new ArrayList<>();
        try {
            usuarios = ServiciosAccesosUsuario.obtenerTotalAccesosSistema();
            ChartSeries accesos = new ChartSeries();
            accesos.setLabel("Usuarios");
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println("Usuario: " + usuarios.get(i).getUsuario().getApellidos());
                //String us = usuarios.get(i).getUsuario().getNombres() + " " + usuarios.get(i).getUsuario().getApellidos();
                String us = usuarios.get(i).getUsuario().getNick();
                logins = ServiciosAccesosUsuario.obtenerAccesosDadoUsuario(usuarios.get(i).getUsuario().getIdPersona());
                accesos.set(us, logins.size());
            }

            model.addSeries(accesos);
        } catch (Exception e) {
            System.out.println(" private BarChartModel initBarModel() dice: " + e.getMessage());
        }
        return model;
    }

    /**
     * @return the barModel
     */
    public BarChartModel getBarModel() {
        return barModel;
    }

    /**
     * @param barModel the barModel to set
     */
    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
