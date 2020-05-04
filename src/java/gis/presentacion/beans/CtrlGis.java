package gis.presentacion.beans;

import catastro.gis.entidades.PredioGis;
import catastro.gis.funciones.FPredioGis;
import com.gisfaces.event.MapClickEvent;
import com.gisfaces.event.MapExtentEvent;
import com.gisfaces.event.MapGeoLocationEvent;
import com.gisfaces.event.MapGraphicDragEvent;
import com.gisfaces.event.MapGraphicSelectEvent;
import com.gisfaces.event.MapGraphicViewEvent;
import com.gisfaces.model.graphic.Graphic;
import com.gisfaces.model.graphic.GraphicsModel;
import com.gisfaces.model.graphic.LineStyle;
import com.gisfaces.model.graphic.MarkerGraphic;
import com.gisfaces.model.graphic.PolygonGraphic;
import com.gisfaces.model.map.Background;
import com.gisfaces.model.map.Coordinate;
import com.gisfaces.utilities.JSFUtilities;
import gis.logica.entidades.GisZona;
import gis.logica.funciones.FZonaGis;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlGis implements Serializable {

    private String background;
    private double opacity;
    private double latitude;
    private double longitude;
    private int zoom;
    private boolean radar;
    private List<GisZona> lstCoordenadas;
    private List<GisZona> lstZonas;
    private GraphicsModel geoipGraphicsModel;
    private GraphicsModel graphicsModel;
    private GraphicsModel starbucksGraphicsModel;
    private GraphicsModel modelPredios;
    private List<PredioGis> lstPredios;
    private List<PredioGis> lstCoordenasPredios;
    private boolean zonas;
    private boolean predios;
    private int numZona;
    private int numSector;
    private int numManzana;
    private int numPredio;

    public CtrlGis() {
        init();
    }

    public void init() {
        this.setBackground("streets");
        this.setLatitude(-0.9095000);
        this.setLongitude(-77.8077200);
        this.setZoom(12);
        this.setOpacity(1.0);
        this.setRadar(false);
        this.predios = true;
        this.zonas = true;
        this.geoipGraphicsModel = new GraphicsModel();
        this.geoipGraphicsModel.setName("Geocoded IP Addresses");
        this.graficarPredios();
        this.buildGraphicsModel2();        
        //this.buildStarbucksGraphicsModel();
    }

    public List<SelectItem> getBackgrounds() {
        return Background.getSelectItems();
    }

    private void graficarPredios() {
        try {
            this.modelPredios = new GraphicsModel();
            this.modelPredios.setName("Predios Urbanos");
            lstPredios = FPredioGis.obtenerPredios();
            for (int p = 0; p < lstPredios.size(); p++) {
                System.out.println("predio : " + lstPredios.get(p).getClaveCatastral());

                PolygonGraphic polygon = new PolygonGraphic();
                polygon.setId(String.valueOf(lstPredios.get(p).getGid())); //agrego el id
                polygon.setType("polygon");
                lstCoordenasPredios = FPredioGis.obtenerCoordenadasDadoCodigo(lstPredios.get(p).getGid());
                for (int i = 0; i < lstCoordenasPredios.size(); i++) {
                    System.out.println("coordenadas==>>> x: " + lstCoordenasPredios.get(i).getX() + " Y: " + lstCoordenasPredios.get(i).getY());

                    polygon.getCoordinates().add(new Coordinate(lstCoordenasPredios.get(i).getY(),
                            lstCoordenasPredios.get(i).getX()));
                }
                polygon.setFillColor("#DF3A01");
                polygon.setFillOpacity(0.56);
                polygon.setOutlineStyle(LineStyle.SHORTDASHDOT);
                polygon.setOutlineColor("#DF3A01");
                polygon.getAttributes().put("Zona", lstPredios.get(p).getZona());
                polygon.getAttributes().put("Sector", lstPredios.get(p).getSector());
                polygon.getAttributes().put("Manzana", lstPredios.get(p).getManzana());
                polygon.getAttributes().put("Predio", lstPredios.get(p).getNumPredio());
                polygon.getAttributes().put("Clave Catastral", lstPredios.get(p).getClaveCatastral());
                polygon.getAttributes().put("Propietario", lstPredios.get(p).getPropietario());
                polygon.getAttributes().put("Area", lstPredios.get(p).getArea());
                polygon.getAttributes().put("Perímetro", lstPredios.get(p).getPerimetro());
                this.modelPredios.getGraphics().add(polygon);
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error: " + e.getMessage());
        }

    }

    private void buildGraphicsModel2() {
        try {
            this.graphicsModel = new GraphicsModel();
            this.graphicsModel.setName("Zonas");
            lstZonas = FZonaGis.obtenerZonas();
            for (int z = 0; z < lstZonas.size(); z++) {
                PolygonGraphic polygon = new PolygonGraphic();
                polygon.setId(String.valueOf(lstZonas.get(z).getId())); //agrego el id
                polygon.setType("polygon");
                lstCoordenadas = FZonaGis.obtenerCoordenadasDadoCodigoZona(lstZonas.get(z).getId());
                for (int c = 0; c < lstCoordenadas.size(); c++) {
                    System.out.println("lat " + lstCoordenadas.get(c).getCoordX()
                            + " , long: " + lstCoordenadas.get(c).getCoordY());

                    polygon.getCoordinates().add(new Coordinate(lstCoordenadas.get(c).getCoordY(),
                            lstCoordenadas.get(c).getCoordX()));
                }
                polygon.setFillColor("#FF0000");
                polygon.setFillOpacity(0.24);
                polygon.setOutlineStyle(LineStyle.SHORTDASHDOT);
                polygon.setOutlineColor("#FF0000");
                polygon.getAttributes().put("Zona:", lstZonas.get(z).getZona());
                polygon.getAttributes().put("Area", lstZonas.get(z).getArea());
                this.graphicsModel.getGraphics().add(polygon);
            }

        } catch (Exception e) {
            Util.addErrorMessage("Error: " + e.getMessage());
        }
    }

    private void buildGraphicsModel() {
        try {
            PolygonGraphic polygon = new PolygonGraphic();

            //polygon.setId("666");
            polygon.setType("polygon");

            lstCoordenadas = FZonaGis.obtenerCoordenadas();
            for (int i = 0; i < lstCoordenadas.size(); i++) {
                System.out.println("lat " + lstCoordenadas.get(i).getCoordX()
                        + " , long: " + lstCoordenadas.get(i).getCoordY());

                polygon.getCoordinates().add(new Coordinate(lstCoordenadas.get(i).getCoordY(),
                        lstCoordenadas.get(i).getCoordX()));
            }
            polygon.setFillColor("#FF0000");
            polygon.setFillOpacity(0.54);
            polygon.setOutlineStyle(LineStyle.SHORTDASHDOT);
            polygon.setOutlineColor("#FF0000");
            polygon.getAttributes().put("Location", "The Jacksonville Landing");
            polygon.getAttributes().put("Polylgon Setting", "Value");
            polygon.getAttributes().put("setFillColor()", polygon.getFillColor());
            polygon.getAttributes().put("setFillOpacity()", polygon.getFillOpacity());
            polygon.getAttributes().put("setOutlineColor()", polygon.getOutlineColor());
            polygon.getAttributes().put("setOutlineOpacity()", polygon.getOutlineOpacity());
            polygon.getAttributes().put("setOutlineStyle()", "LineStyle.SHORTDASHDOT");

            this.graphicsModel = new GraphicsModel();
            this.graphicsModel.setName("Sample Graphics");
            this.graphicsModel.getGraphics().add(polygon);
        } catch (Exception e) {
            Util.addErrorMessage("Error: " + e.getMessage());
        }
    }

    private void buildStarbucksGraphicsModel() {
        try {
            this.starbucksGraphicsModel = new GraphicsModel();
            this.starbucksGraphicsModel.setName("Starbucks");

            List<Graphic> graphics = this.starbucksGraphicsModel.getGraphics();

            lstCoordenadas = FZonaGis.obtenerCoordenadas();
            for (int i = 0; i < lstCoordenadas.size(); i++) {
                graphics.add(buildStarbucksMarker(lstCoordenadas.get(i).getCoordY(),
                        lstCoordenadas.get(i).getCoordX(),
                        String.valueOf(i),
                        String.valueOf(i)));
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error: " + e.getMessage());
        }

    }

    private MarkerGraphic buildStarbucksMarker(double latitude, double longitude, String store, String address) {
        MarkerGraphic marker = new MarkerGraphic();
        marker.setCoordinate(new Coordinate(latitude, longitude));
        marker.setId(store);
        marker.setType("Coffee Shop");
        marker.getAttributes().put("Address", address);
        marker.setImage("http://www.brandinsightblog.com/wp-content/uploads/2008/04/starbucks-logo.png");
        marker.setHeight(2);
        marker.setWidth(2);
        marker.setDraggable(true);

        return marker;
    }

    public void doMapClickListener(AjaxBehaviorEvent event) {
        MapClickEvent e = (MapClickEvent) event;
        String summary = "Map Click Event";
        String detail = String.format("Latitude='%s', Longitude='%s', Zoom='%s', Scale='%s', XMin='%s', YMin='%s', XMax='%s', YMax='%s', Height='%s', Width='%s', X='%s', Y='%s'", e.getLatitude(), e.getLongitude(), e.getZoom(), e.getScale(), e.getExtent().getXmin(), e.getExtent().getYmin(), e.getExtent().getXmax(), e.getExtent().getYmax(), e.getScreen().getHeight(), e.getScreen().getWidth(), e.getScreen().getX(), e.getScreen().getY());
        System.out.println(String.format("%s: %s", summary, detail));
        JSFUtilities.addInfoMessage(summary, detail);
    }

    public void doMapExtentListener(AjaxBehaviorEvent event) {
        MapExtentEvent e = (MapExtentEvent) event;

        String summary = "Map Extent Update Event";
        String detail = String.format("Latitude='%s', Longitude='%s', Zoom='%s', Scale='%s', XMin='%s', YMin='%s', XMax='%s', YMax='%s', Height='%s', Width='%s'", e.getLatitude(), e.getLongitude(), e.getZoom(), e.getScale(), e.getExtent().getXmin(), e.getExtent().getYmin(), e.getExtent().getXmax(), e.getExtent().getYmax(), e.getScreen().getHeight(), e.getScreen().getWidth());

        System.out.println(String.format("%s: %s", summary, detail));
        JSFUtilities.addInfoMessage(summary, detail);
    }

    public void doMapGraphicViewListener(AjaxBehaviorEvent event) {
        MapGraphicViewEvent e = (MapGraphicViewEvent) event;

        String summary = "Map Graphic View Event";
        String detail = String.format("Service ID='%s', Layer ID='%s', Graphics ID='%s', Attributes='%s'", e.getServiceId(), e.getLayerId(), e.getGraphicId(), e.getAttributesJson());

        System.out.println(String.format("%s: %s", summary, detail));
        JSFUtilities.addInfoMessage(summary, detail);
    }

    public void doMapGraphicSelectListener(AjaxBehaviorEvent event) {
        MapGraphicSelectEvent e = (MapGraphicSelectEvent) event;

        String summary = "Map Graphic Select Event";
        String detail = String.format("Service ID='%s', Layer ID='%s', Graphics ID='%s', Attributes='%s'", e.getServiceId(), e.getLayerId(), e.getGraphicId(), e.getAttributesJson());

        System.out.println(String.format("%s: %s", summary, detail));
        JSFUtilities.addInfoMessage(summary, detail);
    }

    public void doMapGraphicDragListener(AjaxBehaviorEvent event) {
        MapGraphicDragEvent e = (MapGraphicDragEvent) event;

        String summary = "Map Graphic Drag Event";
        String detail = String.format("Service ID='%s', Graphic ID='%s', Type='%s', Latitude='%s', Longitude='%s', Attributes='%s'", e.getServiceId(), e.getGraphicId(), e.getType(), e.getLatitude(), e.getLongitude(), e.getAttributesJson());

        System.out.println(String.format("%s: %s", summary, detail));
        JSFUtilities.addInfoMessage(summary, detail);
    }

    public void doMapGeoLocationListener(AjaxBehaviorEvent event) {
        MapGeoLocationEvent e = (MapGeoLocationEvent) event;

        String summary = "Map Geo-Location Event";
        String detail = String.format("Latitude='%s', Longitude='%s', Zoom='%s', Heading='%s', Speed='%s', Altitude='%s', Timestamp='%s'", e.getLatitude(), e.getLongitude(), e.getZoom(), e.getHeading(), e.getSpeed(), e.getAltitude(), e.getTimestamp());

        System.out.println(String.format("%s: %s", summary, detail));
        JSFUtilities.addInfoMessage(summary, detail);
    }

    /**
     * @return the background
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * @return the opacity
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the zoom
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * @param zoom the zoom to set
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    /**
     * @return the radar
     */
    public boolean isRadar() {
        return radar;
    }

    /**
     * @param radar the radar to set
     */
    public void setRadar(boolean radar) {
        this.radar = radar;
    }

    public List<GisZona> getLstCoordenadas() {
        return lstCoordenadas;
    }

    public void setLstCoordenadas(List<GisZona> lstCoordenadas) {
        this.lstCoordenadas = lstCoordenadas;
    }

    public GraphicsModel getGeoipGraphicsModel() {
        return geoipGraphicsModel;
    }

    public void setGeoipGraphicsModel(GraphicsModel geoipGraphicsModel) {
        this.geoipGraphicsModel = geoipGraphicsModel;
    }

    public GraphicsModel getGraphicsModel() {
        return graphicsModel;
    }

    public void setGraphicsModel(GraphicsModel graphicsModel) {
        this.graphicsModel = graphicsModel;
    }

    public GraphicsModel getStarbucksGraphicsModel() {
        return starbucksGraphicsModel;
    }

    public void setStarbucksGraphicsModel(GraphicsModel starbucksGraphicsModel) {
        this.starbucksGraphicsModel = starbucksGraphicsModel;
    }

    public List<GisZona> getLstZonas() {
        return lstZonas;
    }

    public void setLstZonas(List<GisZona> lstZonas) {
        this.lstZonas = lstZonas;
    }

    public GraphicsModel getModelPredios() {
        return modelPredios;
    }

    public void setModelPredios(GraphicsModel modelPredios) {
        this.modelPredios = modelPredios;
    }

    public List<PredioGis> getLstPredios() {
        return lstPredios;
    }

    public void setLstPredios(List<PredioGis> lstPredios) {
        this.lstPredios = lstPredios;
    }

    public List<PredioGis> getLstCoordenasPredios() {
        return lstCoordenasPredios;
    }

    public void setLstCoordenasPredios(List<PredioGis> lstCoordenasPredios) {
        this.lstCoordenasPredios = lstCoordenasPredios;
    }

    /**
     * @return the zonas
     */
    public boolean isZonas() {
        return zonas;
    }

    /**
     * @param zonas the zonas to set
     */
    public void setZonas(boolean zonas) {
        this.zonas = zonas;
    }

    /**
     * @return the predios
     */
    public boolean isPredios() {
        return predios;
    }

    /**
     * @param predios the predios to set
     */
    public void setPredios(boolean predios) {
        this.predios = predios;
    }

    public int getNumZona() {
        return numZona;
    }

    public void setNumZona(int numZona) {
        this.numZona = numZona;
    }

    public int getNumSector() {
        return numSector;
    }

    public void setNumSector(int numSector) {
        this.numSector = numSector;
    }

    public int getNumManzana() {
        return numManzana;
    }

    public void setNumManzana(int numManzana) {
        this.numManzana = numManzana;
    }

    public int getNumPredio() {
        return numPredio;
    }

    public void setNumPredio(int numPredio) {
        this.numPredio = numPredio;
    }

}
