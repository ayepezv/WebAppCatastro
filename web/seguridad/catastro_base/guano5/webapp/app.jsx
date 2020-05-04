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

import ZoomToLatLon from '@boundlessgeo/sdk/components/ZoomToLatLon';
import Geocoding from '@boundlessgeo/sdk/components/Geocoding';
import GeocodingResults from '@boundlessgeo/sdk/components/GeocodingResults';
import FeatureTable from '@boundlessgeo/sdk/components/FeatureTable';
import QueryBuilder from '@boundlessgeo/sdk/components/QueryBuilder';
import Geolocation from '@boundlessgeo/sdk/components/Geolocation';
import LoadingPanel from '@boundlessgeo/sdk/components/LoadingPanel';
import Fullscreen from '@boundlessgeo/sdk/components/Fullscreen';
import DrawFeature from '@boundlessgeo/sdk/components/DrawFeature';
import EditPopup from '@boundlessgeo/sdk/components/EditPopup';
import Navigation from '@boundlessgeo/sdk/components/Navigation';
import ImageExport from '@boundlessgeo/sdk/components/ImageExport';
import Rotate from '@boundlessgeo/sdk/components/Rotate';
import Zoom from '@boundlessgeo/sdk/components/Zoom';
import Select from '@boundlessgeo/sdk/components/Select';
import HomeButton from '@boundlessgeo/sdk/components/HomeButton';
import LayerList from '@boundlessgeo/sdk/components/LayerList';
import Measure from '@boundlessgeo/sdk/components/Measure';
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


var patternFill_2 = new ol.style.Fill({});
                        var patternImg_2 = new Image();
                        patternImg_2.src = './data/styles/pointPattern_2.png';
                        patternImg_2.onload = function(){
                          var canvas = document.createElement('canvas');
                          var context = canvas.getContext('2d');
                          var pattern = context.createPattern(patternImg_2, 'repeat');
                          patternFill_2 = new ol.style.Fill({
                                color: pattern
                              });
                          lyr_manzana.changed()
                        };

                    var textStyleCache_manzana={}
                    var clusterStyleCache_manzana={}
                    var style_manzana = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_manzana'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(128,152,72,0.54902)", lineDash: null, width: pixelsFromMm(0.26)}),
                            fill: new ol.style.Fill({color: "rgba(186,221,105,0.54902)"}),
zIndex: 0
                            })
                            ,new ol.style.Style({
                                fill: patternFill_2,
zIndex: 1
                            })
                            ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_manzana = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_manzana'
        };
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(255, 204, 0, 1)", lineDash: null, width: pixelsFromMm(0.26)}),
                            fill: new ol.style.Fill({color: "rgba(255, 204, 0, 1)"}),
zIndex: 0
                            })
                            ,new ol.style.Style({
                                
                     fill: defaultSelectionFill,
                     stroke: defaultSelectionStroke
                     ,
zIndex: 1
                            })
                            ]
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };

                    var textStyleCache_predio={}
                    var clusterStyleCache_predio={}
                    var style_predio = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_predio'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(0,0,0,0.541176)", lineDash: null, width: pixelsFromMm(0.26)}),
                            fill: new ol.style.Fill({color: "rgba(253,191,111,0.541176)"}),
zIndex: 0
                            })
                            ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_predio = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_predio'
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

                    var textStyleCache_piso1={}
                    var clusterStyleCache_piso1={}
                    var style_piso1 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso1'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(0,0,0,0.458824)", lineDash: null, width: pixelsFromMm(0.26)}),
                            fill: new ol.style.Fill({color: "rgba(233,133,150,0.458824)"}),
zIndex: 0
                            })
                            ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_piso1 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso1'
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

                    var textStyleCache_piso2={}
                    var clusterStyleCache_piso2={}
                    var style_piso2 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso2'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(0,0,0,0.505882)", lineDash: null, width: pixelsFromMm(0.26)}),
                            fill: new ol.style.Fill({color: "rgba(99,184,248,0.505882)"}),
zIndex: 0
                            })
                            ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_piso2 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso2'
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

                    var textStyleCache_piso3={}
                    var clusterStyleCache_piso3={}
                    var style_piso3 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso3'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(0,0,0,0.494118)", lineDash: null, width: pixelsFromMm(0.26)}),
                            fill: new ol.style.Fill({color: "rgba(30,236,236,0.494118)"}),
zIndex: 0
                            })
                            ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_piso3 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso3'
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

                    var textStyleCache_piso4={}
                    var clusterStyleCache_piso4={}
                    var style_piso4 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso4'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(0,0,0,0.505882)", lineDash: null, width: pixelsFromMm(0.26)}),
                            fill: new ol.style.Fill({color: "rgba(119,120,209,0.505882)"}),
zIndex: 0
                            })
                            ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_piso4 = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_piso4'
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

                    var textStyleCache_calles_osm={}
                    var clusterStyleCache_calles_osm={}
                    var style_calles_osm = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_calles_osm'
        };
                        
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(86,172,29,0.0)", lineDash: null, width: pixelsFromMm(0.26)}),
zIndex: 0
                            })
                            ];
                        var allStyles = [];
                        
                        allStyles.push.apply(allStyles, style);
                        return allStyles;
                    };
                    var selectionStyle_calles_osm = function(feature, resolution){
                        var context = {
            feature: feature,
            variables: {},
            layer: 'lyr_calles_osm'
        };
                        var value = "";
                        var style = [ new ol.style.Style({
                                stroke: new ol.style.Stroke({color: "rgba(255, 204, 0, 1)", lineDash: null, width: pixelsFromMm(0.26)}),
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
                    'title': 'Base maps', layers: baseLayers});var overviewMapBaseLayer = baseLayersGroup;
var overlayLayers = [];var overlaysGroup = new ol.layer.Group({showContent: true,
                        'isGroupExpanded': false, 'title': 'Overlays', layers: overlayLayers});
var src_oiiie3 = new ol.source.ImageStatic({
                            url: "./data/oiiie3.png",
                            projection: "EPSG:32718",
                            alwaysInRange: true,
                            imageSize: [55736, 36940],
                            imageExtent: [166054.500000, 9889315.500000, 193922.500000, 9907785.500000]
                      });

                      var raster_oiiie3 = new ol.source.Raster({
                            sources: [src_oiiie3],
                            operation: function(pixels, data) {
                                var pixel = pixels[0];
                                if (pixel[0] === 0 && pixel[1] === 0 && pixel[2] === 0) {
                                    pixel[3] = 0;
                                }
                                return pixel;
                              }
                      });

                      var lyr_oiiie3 = new ol.layer.Image({
                            opacity: 1.0,
                            
minResolution:-1.30385392925e-13,
 maxResolution:14.0000336001,

                            title: "OIII-E3",
                            id: "OIII_E320170713211147091",
                            timeInfo: null,
                            source: raster_oiiie3
                        });
var lyr_oiiie3_overview = new ol.layer.Image({
                            opacity: 1.0,
                            
minResolution:-1.30385392925e-13,
 maxResolution:14.0000336001,

                            title: "OIII-E3",
                            id: "OIII_E320170713211147091",
                            source: raster_oiiie3
                        });
var lyr_manzana = new ol.layer.Vector({
                    opacity: 1.0,
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_manzana.json'
                            }),
                    
minResolution:2.80000672002,
 maxResolution:70.0001680004,

                    style: style_manzana,
                    selectedStyle: selectionStyle_manzana,
                    title: "manzana",
                    id: "manzana20170604192531185",
                    filters: [],
                    timeInfo: null,
                    isSelectable: true,
                    popupInfo: "",
                    attributes: ["gid", "zona", "sector", "manzana", "area", "parroquia"],
                    geometryType: "Polygon"
                });
var lyr_manzana_overview = new ol.layer.Vector({
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_manzana.json'
                            }),
                    
minResolution:2.80000672002,
 maxResolution:70.0001680004,

                    style: style_manzana});
var lyr_predio = new ol.layer.Vector({
                    opacity: 1.0,
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_predio.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:2.80000672002,

                    style: style_predio,
                    selectedStyle: selectionStyle_predio,
                    title: "predio",
                    id: "predio20170604192548578",
                    filters: [],
                    timeInfo: null,
                    isSelectable: true,
                    popupInfo: "<table class='popup-table'><tr><th>Attribute</th><th>Value</th><tr><td>gid</td><td style='text-align:right'>[gid]</td></tr><tr><td>provincia</td><td style='text-align:right'>[provincia]</td></tr><tr><td>canton</td><td style='text-align:right'>[canton]</td></tr><tr><td>parroquia</td><td style='text-align:right'>[parroquia]</td></tr><tr><td>zona</td><td style='text-align:right'>[zona]</td></tr><tr><td>sector</td><td style='text-align:right'>[sector]</td></tr><tr><td>manzana</td><td style='text-align:right'>[manzana]</td></tr><tr><td>predio</td><td style='text-align:right'>[predio]</td></tr><tr><td>tipo_lote</td><td style='text-align:right'>[tipo_lote]</td></tr><tr><td>propiedad_</td><td style='text-align:right'>[propiedad_]</td></tr><tr><td>derechos_a</td><td style='text-align:right'>[derechos_a]</td></tr><tr><td>clave_cata</td><td style='text-align:right'>[clave_cata]</td></tr><tr><td>propietari</td><td style='text-align:right'>[propietari]</td></tr><tr><td>area</td><td style='text-align:right'>[area]</td></tr><tr><td>perimetro</td><td style='text-align:right'>[perimetro]</td></tr><tr><td>centroide_</td><td style='text-align:right'>[centroide_]</td></tr><tr><td>centroid00</td><td style='text-align:right'>[centroid00]</td></tr><tr><td>centroid01</td><td style='text-align:right'>[centroid01]</td></tr><tr><td>centroid02</td><td style='text-align:right'>[centroid02]</td></tr><tr><td>id_predio</td><td style='text-align:right'>[id_predio]</td></tr><tr><td>id_manzsig</td><td style='text-align:right'>[id_manzsig]</td></tr><tr><td>vinculado</td><td style='text-align:right'>[vinculado]</td></tr><tr><td>ts_fecha_creacion</td><td style='text-align:right'>[ts_fecha_creacion]</td></tr><tr><td>ts_fecha_actualizacion</td><td style='text-align:right'>[ts_fecha_actualizacion]</td></tr><tr><td>id_session_usuario</td><td style='text-align:right'>[id_session_usuario]</td></tr></table>",
                    attributes: ["gid", "provincia", "canton", "parroquia", "zona", "sector", "manzana", "predio", "tipo_lote", "propiedad_", "derechos_a", "clave_cata", "propietari", "area", "perimetro", "centroide_", "centroid00", "centroid01", "centroid02", "fotografia", "html", "id_predio", "id_manzsig", "vinculado", "ts_fecha_creacion", "ts_fecha_actualizacion", "id_session_usuario"],
                    geometryType: "Polygon"
                });
var lyr_predio_overview = new ol.layer.Vector({
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_predio.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:2.80000672002,

                    style: style_predio});
var lyr_piso1 = new ol.layer.Vector({
                    opacity: 1.0,
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_piso1.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:1.40000336001,

                    style: style_piso1,
                    selectedStyle: selectionStyle_piso1,
                    title: "piso1",
                    id: "piso120170604192620945",
                    filters: [],
                    timeInfo: null,
                    isSelectable: true,
                    popupInfo: "",
                    attributes: ["gid", "idpiso1", "provincia", "canton", "parroquia", "zona", "sector", "manzana", "predio", "tipo_lote", "propiedad_", "derechos_a", "clave_cata", "propietari", "area", "perimetro", "numero_blo", "numero_pis", "centroide_", "centroid00", "centroid01", "centroid02", "actualizar", "enlazado", "ts_fecha_creacion"],
                    geometryType: "Polygon"
                });
var lyr_piso1_overview = new ol.layer.Vector({
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_piso1.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:1.40000336001,

                    style: style_piso1});
var lyr_piso2 = new ol.layer.Vector({
                    opacity: 1.0,
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_piso2.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:1.40000336001,

                    style: style_piso2,
                    selectedStyle: selectionStyle_piso2,
                    title: "piso2",
                    id: "piso220170604192622926",
                    filters: [],
                    timeInfo: null,
                    isSelectable: true,
                    popupInfo: "",
                    attributes: ["gid", "idpiso2", "provincia", "canton", "parroquia", "zona", "sector", "manzana", "predio", "tipo_lote", "propiedad_", "derechos_a", "clave_cata", "propietari", "area", "perimetro", "numero_blo", "numero_pis", "centroide_", "centroid00", "centroid01", "centroid02", "actualizar", "id_piso_alfanum", "ts_fecha_creacion"],
                    geometryType: "Polygon"
                });
var lyr_piso2_overview = new ol.layer.Vector({
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_piso2.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:1.40000336001,

                    style: style_piso2});
var lyr_piso3 = new ol.layer.Vector({
                    opacity: 1.0,
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_piso3.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:1.40000336001,

                    style: style_piso3,
                    selectedStyle: selectionStyle_piso3,
                    title: "piso3",
                    id: "piso320170604192625606",
                    filters: [],
                    timeInfo: null,
                    isSelectable: true,
                    popupInfo: "",
                    attributes: ["gid", "idpiso3", "provincia", "canton", "parroquia", "zona", "sector", "manzana", "predio", "tipo_lote", "propiedad_", "derechos_a", "clave_cata", "propietari", "area", "perimetro", "numero_blo", "numero_pis", "centroide_", "centroid00", "centroid01", "centroid02", "actualizar", "id_piso_alfanum"],
                    geometryType: "Polygon"
                });
var lyr_piso3_overview = new ol.layer.Vector({
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_piso3.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:1.40000336001,

                    style: style_piso3});
var lyr_piso4 = new ol.layer.Vector({
                    opacity: 1.0,
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_piso4.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:1.40000336001,

                    style: style_piso4,
                    selectedStyle: selectionStyle_piso4,
                    title: "piso4",
                    id: "piso420170604192626973",
                    filters: [],
                    timeInfo: null,
                    isSelectable: true,
                    popupInfo: "",
                    attributes: ["gid", "idpiso4", "provincia", "canton", "parroquia", "zona", "sector", "manzana", "predio", "tipo_lote", "propiedad_", "derechos_a", "clave_cata", "propietari", "area", "perimetro", "numero_blo", "numero_pis", "centroide_", "centroid00", "centroid01", "centroid02", "actualizar", "id_piso_alfanum"],
                    geometryType: "Polygon"
                });
var lyr_piso4_overview = new ol.layer.Vector({
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_piso4.json'
                            }),
                    
minResolution:0.000280000672002,
 maxResolution:1.40000336001,

                    style: style_piso4});
var lyr_calles_osm = new ol.layer.Vector({
                    opacity: 1.0,
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_calles_osm.json'
                            }),
                    
minResolution:0.140000336001,
 maxResolution:0.700001680004,

                    style: style_calles_osm,
                    selectedStyle: selectionStyle_calles_osm,
                    title: "calles_osm",
                    id: "calles_osm20170604192657307",
                    filters: [],
                    timeInfo: null,
                    isSelectable: true,
                    popupInfo: "",
                    attributes: ["gid", "osm_id", "name", "highway", "waterway", "aerialway", "barrier", "man_made", "z_order", "other_tags"],
                    geometryType: "Line"
                });
var lyr_calles_osm_overview = new ol.layer.Vector({
                    source: new ol.source.Vector({
                            format: new ol.format.GeoJSON(),
                            url: './data/lyr_calles_osm.json'
                            }),
                    
minResolution:0.140000336001,
 maxResolution:0.700001680004,

                    style: style_calles_osm});

lyr_oiiie3.setVisible(true);
lyr_manzana.setVisible(true);
lyr_predio.setVisible(true);
lyr_piso1.setVisible(true);
lyr_piso2.setVisible(true);
lyr_piso3.setVisible(true);
lyr_piso4.setVisible(true);
lyr_calles_osm.setVisible(true);
for (var i=0;i<baseLayers.length;i++){baseLayers[i].setVisible(false);}
baseLayers[0].setVisible(true);
var layersList = [lyr_oiiie3,lyr_manzana,lyr_predio,lyr_piso1,lyr_piso2,lyr_piso3,lyr_piso4,lyr_calles_osm];layersList.unshift(baseLayersGroup);
var layersMap  = {'lyr_oiiie3':lyr_oiiie3,'lyr_manzana':lyr_manzana,'lyr_predio':lyr_predio,'lyr_piso1':lyr_piso1,'lyr_piso2':lyr_piso2,'lyr_piso3':lyr_piso3,'lyr_piso4':lyr_piso4,'lyr_calles_osm':lyr_calles_osm};
var view = new ol.View({ maxZoom: 32, minZoom: 1, projection: 'EPSG:32718'});
var originalExtent = [181948.245239, 9896154.643015, 197206.087741, 9903607.898316];
var unitsConversion = 1;

var map = new ol.Map({
  layers: layersList,
  view: view,
  controls: [new ol.control.MousePosition({"projection": "EPSG:4326", "undefinedHTML": "&nbsp;", "coordinateFormat": ol.coordinate.createStringXY(4)}),
new ol.control.Attribution({collapsible: false}),
new ol.control.ScaleLine({"minWidth": 64, "units": "metric"})]
});

function setTextPathStyle_calles_osm(){
                setTextPathStyle(lyr_calles_osm, function (feature){
                    var labelContext = {
                          feature: feature,
                          variables: {},
                          layer: 'lyr_$(layerName)s'
                      };
                    if (getFeatureAttribute(feature, "name") !== null) {
                        var labelText = String(getFeatureAttribute(feature, "name"));
                    } else {
                        var labelText = " ";
                    }
                    if (labelText == ""){
                        labelText = " ";
                    }
                    labelText = String(labelText);
                    var size = 8.25 * 2;
                    var font = 'normal normal ' + String(size) + 'px "MS Shell Dlg 2",sans-serif'
                    return [ new ol.style.Style({
                        text: new ol.style.Text({
                            font: font,
                            text: labelText,
                            fill: new ol.style.Fill({
                                color: "rgba(0, 0, 0, 255)"
                            }),
                            textBaseline: "middle",
                            textAlign: "center",
                            rotation: -0.0,
                            offsetX: 0.0,
                            offsetY: 0.0 
                        }),
                      })];
                });
              }
              setTextPathStyle_calles_osm();

class BasicApp extends React.Component {
  getChildContext() {
    return {
      muiTheme: getMuiTheme()
    };
  }
  componentDidMount() {
    map.addControl(new ol.control.OverviewMap({collapsed: true, layers: [overviewMapBaseLayer, lyr_oiiie3_overview,lyr_manzana_overview,lyr_predio_overview,lyr_piso1_overview,lyr_piso2_overview,lyr_piso3_overview,lyr_piso4_overview,lyr_calles_osm_overview]}));
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
    var toolbarOptions = {title:"Visor Web Catastro"};
    return React.createElement("article", null,
      React.createElement(Header, toolbarOptions ,
React.createElement(ZoomToLatLon, {map:map}),
React.createElement("div", {id:'geocoding'},
                                        React.createElement(Geocoding, {maxResults:5})),
React.createElement(Button, {buttonType: 'Icon', iconClassName: 'headerIcons ms ms-table', tooltip: 'Table', onTouchTap: this._toggleTable.bind(this)}),
React.createElement(Button, {buttonType: 'Icon', iconClassName: 'headerIcons fa fa-filter', tooltip: 'Query', onTouchTap: this._toggleQuery.bind(this)}),
React.createElement(Button, {buttonType: 'Icon', iconClassName: 'headerIcons ms ms-help', tooltip: 'Help', onTouchTap: function(){window.open('help/help.html','_blank')}}),
React.createElement(DrawFeature, {toggleGroup: 'navigation', map: map}),
React.createElement(Navigation, {toggleGroup: 'navigation', secondary: true}),
React.createElement(ImageExport, {map:map}),
React.createElement(Select, {toggleGroup: 'navigation', map:map}),
React.createElement(Measure, {toggleGroup:'navigation', map:map, geodesic:true})),
      React.createElement("div", {id: 'content'},
        React.createElement(MapPanel, {id: 'map', map: map, extent: originalExtent, useHistory: true}
          ,
React.createElement("div", {id: 'about-panel', className:'about-panel'},
                                        React.createElement("a", {href:'#', id:'about-panel-closer',
                                            className:'about-panel-closer', onClick:this._hideAboutPanel.bind(this)},
                                              "X"
                                        ),
                                        React.createElement("div", {dangerouslySetInnerHTML:{__html: '<h1>Visor Web Catastro</h1><br><p>Esta es la aplicacion Web del Visor d catastro</p><br><p>de la ciudad de Archidona, se encuentra</p><br><p>información de Manzanas, predios, construcciones</p>'}})
                                    ),
React.createElement("div", {id:'geocoding-results', className:'geocoding-results-panel'},
                                    React.createElement(GeocodingResults, {map:map, zoom:10})
                                  ),
React.createElement("div", {id: 'query-panel', className:'query-panel'},
                                          React.createElement(QueryBuilder, {map: map})
                                        ),
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
React.createElement("div", {id:'geolocation-control'},
                                    React.createElement(Geolocation, {tooltipPosition: 'bottom-right', map:map})
                                  ),
React.createElement(LoadingPanel, {map:map}),
React.createElement("div", {id:'fullscreen-button'},
                                    React.createElement(Fullscreen, {tooltipPosition: 'bottom-right', map:map})
                                  ),
React.createElement("div", {id: 'editpopup', className: 'ol-popup'},
                                React.createElement(EditPopup, {toggleGroup: 'navigation', map: map})
                            ),
React.createElement("div", {id:'rotate-button'},
                                    React.createElement(Rotate, {
                                    autoHide:false,
                                    duration:250,
                                    map: map,
                                    tooltipPosition: 'bottom-right'})
                                  ),
React.createElement("div", {id:'zoom-buttons'},
                                    React.createElement(Zoom, {
                                    duration:250,
                                    zoomInTipLabel: 'Zoom in',
                                    zoomOutTipLabel: 'Zoom out',
                                    delta: 1.2,
                                    map: map,
                                    tooltipPosition: 'bottom-right'})
                                  ),
React.createElement("div", {id:'home-button'},
                                    React.createElement(HomeButton, {tooltipPosition: 'bottom-right', map:map})
                                  ),
React.createElement("div",{id: "layerlist"},
                                    React.createElement(LayerList, {showOpacity:false, showDownload:false,
                                        showZoomTo:false, allowReordering:false,
                                        allowFiltering:true, tipLabel:'layers',
                                        downloadFormat:'GeoJSON', showUpload:true, map:map,
                                        includeLegend:true, allowStyling:true, showTable:true}))
      )
    );
  }
}

BasicApp.childContextTypes = {
  muiTheme: React.PropTypes.object
};

ReactDOM.render(<IntlProvider locale='en' messages={enMessages}><BasicApp /></IntlProvider>, document.getElementById('main'));
