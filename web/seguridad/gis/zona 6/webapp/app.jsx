import React from 'react';
import ReactDOM from 'react-dom';
import ol from 'openlayers';
import {IntlProvider} from 'react-intl';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import Button from '@boundlessgeo/sdk/components/Button';
import Header from '@boundlessgeo/sdk/components/Header';
import enMessages from '@boundlessgeo/sdk/locale/en';
import InfoPopup from '@boundlessgeo/sdk/components/InfoPopup';
import MapPanel from '@boundlessgeo/sdk/components/MapPanel';
import {ToolbarGroup, ToolbarSeparator} from 'material-ui/Toolbar';

import FeatureTable from '@boundlessgeo/sdk/components/FeatureTable';
import LayerList from '@boundlessgeo/sdk/components/LayerList';
import Zoom from '@boundlessgeo/sdk/components/Zoom';
import DrawFeature from '@boundlessgeo/sdk/components/DrawFeature';
import EditPopup from '@boundlessgeo/sdk/components/EditPopup';
import Navigation from '@boundlessgeo/sdk/components/Navigation';
import injectTapEventPlugin from 'react-tap-event-plugin';

// Needed for onTouchTap
// Can go away when react 1.0 release
// Check this repo:
// https://github.com/zilverline/react-tap-event-plugin
injectTapEventPlugin();

var defaultFill = new ol.style.Fill({
   color: 'rgba(255,255,255,0.4)'
 });
 var defaultStroke = new ol.style.Stroke({
   color: '#3399CC',
   width: 1.25
 });
 var defaultSelectionFill = new ol.style.Fill({
   color: 'rgba(255,255,0,0.4)'
 });
 var defaultSelectionStroke = new ol.style.Stroke({
   color: '#FFFF00',
   width: 1.25
 });



                    var textStyleCache_predios={}
                    var clusterStyleCache_predios={}
                    var style_predios = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_predios'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                             stroke: new ol.style.Stroke({color: "rgba(128,152,72,0.678431372549)", lineDash: null, width: pixelsFromMm(0.26)}),
                        fill: new ol.style.Fill({color: "rgba(186,221,105,0.678431372549)"}),
zIndex: 0
                        })
                        ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_predios = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_predios'
        };
                        var value = "";
                        var style = [ new ol.style.Style({
                             stroke: new ol.style.Stroke({color: "rgba(255, 204, 0, 1)", lineDash: null, width: pixelsFromMm(0.26)}),
                        fill: new ol.style.Fill({color: "rgba(255, 204, 0, 1)"}),
zIndex: 0
                        })
                        ]
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };

                    var textStyleCache_piso_edificacion1={}
                    var clusterStyleCache_piso_edificacion1={}
                    var style_piso_edificacion1 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso_edificacion1'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                             stroke: new ol.style.Stroke({color: "rgba(58,20,4,0.945098039216)", lineDash: null, width: pixelsFromMm(0.2)}),
                        fill: new ol.style.Fill({color: "rgba(255,127,0,0.945098039216)"}),
zIndex: 0
                        })
                        ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_piso_edificacion1 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso_edificacion1'
        };
                        var value = "";
                        var style = [ new ol.style.Style({
                             stroke: new ol.style.Stroke({color: "rgba(255, 204, 0, 1)", lineDash: null, width: pixelsFromMm(0.2)}),
                        fill: new ol.style.Fill({color: "rgba(255, 204, 0, 1)"}),
zIndex: 0
                        })
                        ]
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
var baseLayers = [new ol.layer.Tile({
    type: 'base',
    title: 'ESRI world imagery',
    source: new ol.source.XYZ({
        crossOrigin: 'anonymous',
        attributions: [new ol.Attribution({ html: 'Tiles &copy; Esri &mdash; Source: Esri, i-cubed, USDA, USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, UPR-EGP, and the GIS User Community'})],
        url: 'http://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}'
    }),
    projection: 'EPSG:3857'
})
];var baseLayersGroup = new ol.layer.Group({showContent: true,
                    'isGroupExpanded': false, 'type': 'base-group',
                    'title': 'Base maps', layers: baseLayers});
var overlayLayers = [];var overlaysGroup = new ol.layer.Group({showContent: true,
                        'isGroupExpanded': false, 'title': 'Overlays', layers: overlayLayers});
var wfsSource_predios = new ol.source.Vector({
                        format: new ol.format.GeoJSON(),
                        loader: function(extent, resolution, projection) {
                          var url = 'http://catastro.archidona.gob.ec:8084/geoserver/wfs?service=WFS&version=1.1.0&request=GetFeature' +
                                '&typename=Archidona:predios&outputFormat=application/json&' +
                                '&srsname=EPSG:32718&bbox=' + extent.join(',') + ',EPSG:32718';
                          var xmlhttp = new XMLHttpRequest();
                          xmlhttp.onreadystatechange = function() {
                            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                              var features = wfsSource_predios.getFormat().readFeatures(xmlhttp.responseText, {featureProjection: projection, dataProjection: "EPSG:32718"});
                              wfsSource_predios.addFeatures(features);
                            }
                          };
                          xmlhttp.open('GET', url, true);
                          xmlhttp.send();
                        },
                        strategy: ol.loadingstrategy.tile(new ol.tilegrid.createXYZ({maxZoom: 19}))
                    });var lyr_predios = new ol.layer.Vector({
                            opacity: 1.0,
                            source: wfsSource_predios,  
                            style: style_predios,
                            selectedStyle: selectionStyle_predios,
                            title: "predios",
                            id: "predios20170614215824971",
                            wfsInfo: {featureNS: '',
                    featureType: 'predios',
                    geometryType: 'MultiPolygon',
                    geometryName: 'the_geom',
                    featurePrefix: 'Archidona',
                    url: 'http://catastro.archidona.gob.ec:8084/geoserver/wfs'
                  },
                  isWFST:true,
                            filters: [],
                            timeInfo: null,
                            isSelectable: true,
                            popupInfo: "<table class='popup-table'><tr><th>Attribute</th><th>Value</th><tr><td>provincia</td><td style='text-align:right'>[provincia]</td></tr><tr><td>canton</td><td style='text-align:right'>[canton]</td></tr><tr><td>parroquia</td><td style='text-align:right'>[parroquia]</td></tr><tr><td>zona</td><td style='text-align:right'>[zona]</td></tr><tr><td>sector</td><td style='text-align:right'>[sector]</td></tr><tr><td>manzana</td><td style='text-align:right'>[manzana]</td></tr><tr><td>predio</td><td style='text-align:right'>[predio]</td></tr><tr><td>tipo_lote</td><td style='text-align:right'>[tipo_lote]</td></tr><tr><td>propiedad_</td><td style='text-align:right'>[propiedad_]</td></tr><tr><td>derechos_a</td><td style='text-align:right'>[derechos_a]</td></tr><tr><td>clave_cata</td><td style='text-align:right'>[clave_cata]</td></tr><tr><td>propietari</td><td style='text-align:right'>[propietari]</td></tr><tr><td>area</td><td style='text-align:right'>[area]</td></tr><tr><td>perimetro</td><td style='text-align:right'>[perimetro]</td></tr><tr><td>centroide_</td><td style='text-align:right'>[centroide_]</td></tr><tr><td>centroid00</td><td style='text-align:right'>[centroid00]</td></tr><tr><td>centroid01</td><td style='text-align:right'>[centroid01]</td></tr><tr><td>centroid02</td><td style='text-align:right'>[centroid02]</td></tr><tr><td>fotografia</td><td style='text-align:right'>[fotografia]</td></tr></table>"
                        });
var lyr_predios_overview = new ol.layer.Vector({
                                opacity: 1.0,
                                source: wfsSource_predios,  
                                style: style_predios,
                                title: "predios",
                                id: "predios20170614215824971",
                                wfsInfo: {featureNS: '',
                    featureType: 'predios',
                    geometryType: 'MultiPolygon',
                    geometryName: 'the_geom',
                    featurePrefix: 'Archidona',
                    url: 'http://catastro.archidona.gob.ec:8084/geoserver/wfs'
                  },
                  isWFST:true,
                                filters: [],
                            });
var wfsSource_piso_edificacion1 = new ol.source.Vector({
                        format: new ol.format.GeoJSON(),
                        loader: function(extent, resolution, projection) {
                          var url = 'http://catastro.archidona.gob.ec:8084/geoserver/wfs?service=WFS&version=1.1.0&request=GetFeature' +
                                '&typename=Archidona:piso_edificacion1&outputFormat=application/json&' +
                                '&srsname=EPSG:32718&bbox=' + extent.join(',') + ',EPSG:32718';
                          var xmlhttp = new XMLHttpRequest();
                          xmlhttp.onreadystatechange = function() {
                            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                              var features = wfsSource_piso_edificacion1.getFormat().readFeatures(xmlhttp.responseText, {featureProjection: projection, dataProjection: "EPSG:32718"});
                              wfsSource_piso_edificacion1.addFeatures(features);
                            }
                          };
                          xmlhttp.open('GET', url, true);
                          xmlhttp.send();
                        },
                        strategy: ol.loadingstrategy.tile(new ol.tilegrid.createXYZ({maxZoom: 19}))
                    });var lyr_piso_edificacion1 = new ol.layer.Vector({
                            opacity: 1.0,
                            source: wfsSource_piso_edificacion1,  
                            style: style_piso_edificacion1,
                            selectedStyle: selectionStyle_piso_edificacion1,
                            title: "piso_edificacion1",
                            id: "piso_edificacion120170614215818275",
                            wfsInfo: {featureNS: '',
                    featureType: 'piso_edificacion1',
                    geometryType: 'MultiPolygon',
                    geometryName: 'the_geom',
                    featurePrefix: 'Archidona',
                    url: 'http://catastro.archidona.gob.ec:8084/geoserver/wfs'
                  },
                  isWFST:true,
                            filters: [],
                            timeInfo: null,
                            isSelectable: true,
                            popupInfo: ""
                        });
var lyr_piso_edificacion1_overview = new ol.layer.Vector({
                                opacity: 1.0,
                                source: wfsSource_piso_edificacion1,  
                                style: style_piso_edificacion1,
                                title: "piso_edificacion1",
                                id: "piso_edificacion120170614215818275",
                                wfsInfo: {featureNS: '',
                    featureType: 'piso_edificacion1',
                    geometryType: 'MultiPolygon',
                    geometryName: 'the_geom',
                    featurePrefix: 'Archidona',
                    url: 'http://catastro.archidona.gob.ec:8084/geoserver/wfs'
                  },
                  isWFST:true,
                                filters: [],
                            });

lyr_predios.setVisible(true);
lyr_piso_edificacion1.setVisible(true);
for (var i=0;i<baseLayers.length;i++){baseLayers[i].setVisible(false);}
baseLayers[0].setVisible(true);
var layersList = [lyr_predios,lyr_piso_edificacion1];layersList.unshift(baseLayersGroup);
var layersMap  = {'lyr_predios':lyr_predios,'lyr_piso_edificacion1':lyr_piso_edificacion1};
var view = new ol.View({ maxZoom: 32, minZoom: 1, projection: 'EPSG:32718'});
var originalExtent = [184625.237497, 9896314.474594, 196254.449444, 9907076.727296];
var unitsConversion = 1;

var map = new ol.Map({
  layers: layersList,
  view: view,
  controls: []
});



function pixelsFromMapUnits(size) {
    return size / map.getView().getResolution() * unitsConversion;
};

function pixelsFromMm(size) {
    return 96 / 25.4 * size;
};

function textPath(ctx, text, path){

    function dist2D(x1,y1,x2,y2){   
        var dx = x2-x1;
        var dy = y2-y1;
        return Math.sqrt(dx*dx+dy*dy);
    }

    var di, dpos=0;
    var pos=2;
    function getPoint(path, dl){
        if (!di || dpos+di<dl){
            for (; pos<path.length; ){
                di = dist2D(path[pos-2],path[pos-1],path[pos],path[pos+1]);
                if (dpos+di>dl) break;
                pos += 2;
                if (pos>=path.length) break;
                dpos += di;
            }
        }

        var x, y, a, dt = dl-dpos;
        if (pos>=path.length){
            pos = path.length-2;
        }

        if (!dt){
            x = path[pos-2];
            y = path[pos-1];
            a = Math.atan2(path[pos+1]-path[pos-1], path[pos]-path[pos-2]);
        }
        else{
            x = path[pos-2]+ (path[pos]-path[pos-2])*dt/di;
            y = path[pos-1]+(path[pos+1]-path[pos-1])*dt/di;
            a = Math.atan2(path[pos+1]-path[pos-1], path[pos]-path[pos-2]);
        }
        return [x,y,a];
    }

    var letterPadding = ctx.measureText(" ").width *0.25;

    var start = 0;

    var d = 0;
    for (var i=2; i<path.length; i+=2){
        d += dist2D(path[i-2],path[i-1],path[i],path[i+1]);
    }
    var nbspace = text.split(" ").length -1;

    if (d < ctx.measureText(text).width + (text.length-1 + nbspace) * letterPadding) return;

    switch (ctx.textAlign){
        case "center":
        case "end":
        case "right":{
            start = d - ctx.measureText(text).width - (text.length + nbspace) * letterPadding;
            if (ctx.textAlign == "center") start /= 2;
            }
            break;
        default: break;
    }

    for (var t=0; t<text.length; t++){   
        var letter = text[t];
        var wl = ctx.measureText(letter).width;

        var p = getPoint(path, start+wl/2);

        ctx.save();
        ctx.textAlign = "center";
        ctx.translate(p[0], p[1]);
        ctx.rotate(p[2]);
        if (ctx.lineWidth) ctx.strokeText(letter,0,0);
        ctx.fillText(letter,0,0);
        ctx.restore();
        start += wl+letterPadding*(letter==" "?2:1);
    }

}

function drawTextPath (e){ 
    var extent = e.frameState.extent;
    var c2p = e.frameState.coordinateToPixelTransform;

    // Get pixel path with coordinates
    function getPath(c, readable){   
        var path1 = [];
        for (var k=0; k<c.length; k++){   
            path1.push(c2p[0]*c[k][0]+c2p[1]*c[k][1]+c2p[4]);
            path1.push(c2p[2]*c[k][0]+c2p[3]*c[k][1]+c2p[5]);
        }
        // Revert line ?
        if (readable && path1[0]>path1[path1.length-2]){   
            var path2 = [];
            for (var k=path1.length-2; k>=0; k-=2){   
                path2.push(path1[k]);
                path2.push(path1[k+1]);
            }
            return path2;
        }
        else return path1;
    }

    var ctx = e.context;
    ctx.save();
    ctx.scale(e.frameState.pixelRatio,e.frameState.pixelRatio);

    var features = this.getSource().getFeaturesInExtent(extent);
    for (var i=0, f; f=features[i]; i++){
        var style = this.textPathStyle_(f,e.frameState.viewState.resolution);
        for (var s,j=0; s=style[j]; j++){
            var g = s.getGeometry() || f.getGeometry();
            var c;
            switch (g.getType()){   
                case "LineString": c = g.getCoordinates(); break;
                case "MultiLineString": c = g.getLineString(0).getCoordinates(); break;
                default: continue;
            }

            var st = s.getText();
            var path = getPath(c, true);

            ctx.font = st.getFont();
            ctx.textBaseline = st.getTextBaseline();
            ctx.textAlign = st.getTextAlign();
            ctx.lineWidth = st.getStroke() ? (st.getStroke().getWidth()||0) : 0;
            ctx.strokeStyle = st.getStroke() ? (st.getStroke().getColor()||"#fff") : "#fff";
            ctx.fillStyle = st.getFill() ? st.getFill().getColor()||"#000" : "#000";
            // Draw textpath
            textPath(ctx, st.getText()||f.get("name"), path);
        }
    }

    ctx.restore();
}

function setTextPathStyle (layer, style){
    if (!layer.textPath_){   
        layer.textPath_ = layer.on('postcompose', drawTextPath, layer);
    }
    layer.textPathStyle_ = style;
    layer.changed();
}

class BasicApp extends React.Component {
  getChildContext() {
    return {
      muiTheme: getMuiTheme()
    };
  }
  componentDidMount() {
    
  }
  _toggle(el) {
    if (el.style.display === 'block') {
      el.style.display = 'none';
    } else {
      el.style.display = 'block';
    }
  }
  _toggleTable() {
    this._toggle(document.getElementById('table-panel'));
    this.refs.table.getWrappedInstance().setDimensionsOnState();
  }
  _toggleWFST() {
    this._toggle(document.getElementById('wfst'));
  }
  _toggleQuery() {
    this._toggle(document.getElementById('query-panel'));
  }
  _toggleEdit() {
    this._toggle(document.getElementById('edit-tool-panel'));
  }
  _hideAboutPanel(evt) {
    evt.preventDefault();
    document.getElementById('about-panel').style.display = 'none';
  }
  _toggleChartPanel(evt) {
    evt.preventDefault();
    this._toggle(document.getElementById('chart-panel'));
  }
  render() {
    var toolbarOptions = {title:"Catastro"};
    return React.createElement("article", null,
      React.createElement(Header, toolbarOptions ,
React.createElement(Button, {buttonType: 'Icon', iconClassName: 'headerIcons ms ms-table', tooltip: 'Table', onTouchTap: this._toggleTable.bind(this)}),
React.createElement(DrawFeature, {toggleGroup: 'navigation', map: map}),
React.createElement(Navigation, {toggleGroup: 'navigation', secondary: true})),
      React.createElement("div", {id: 'content'},
        React.createElement(MapPanel, {id: 'map', map: map, extent: originalExtent, useHistory: true}
          ,
React.createElement("div", {id: 'popup', className: 'ol-popup'},
                                    React.createElement(InfoPopup, {toggleGroup: 'navigation', map: map, hover: false})
                                  )
        )
        ,
 React.createElement("div", {id: 'table-panel', className: 'attributes-table'},
                                          React.createElement(FeatureTable, {allowEdit:true, toggleGroup: 'navigation',
                                                              ref: 'table', pointZoom:16, map: map,
                                                              sortable:true, pageSize:20})
                                    ),
React.createElement("div",{id: "layerlist"},
                                    React.createElement(LayerList, {showOpacity:false, showDownload:false,
                                        showZoomTo:false, allowReordering:false,
                                        allowFiltering:true, tipLabel:'layers',
                                        downloadFormat:'GeoJSON', showUpload:true, map:map,
                                        includeLegend:true, allowStyling:true, showTable:true})),
React.createElement("div", {id:'zoom-buttons'},
                                    React.createElement(Zoom, {
                                    duration:250,
                                    zoomInTipLabel: 'Zoom in',
                                    zoomOutTipLabel: 'Zoom out',
                                    delta: 1.2,
                                    map: map,
                                    tooltipPosition: 'bottom-right'})
                                  ),
React.createElement("div", {id: 'editpopup', className: 'ol-popup'},
                                React.createElement(EditPopup, {toggleGroup: 'navigation', map: map})
                            )
      )
    );
  }
}

BasicApp.childContextTypes = {
  muiTheme: React.PropTypes.object
};

ReactDOM.render(<IntlProvider locale='en' messages={enMessages}><BasicApp /></IntlProvider>, document.getElementById('main'));
