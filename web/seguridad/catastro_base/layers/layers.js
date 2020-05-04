var baseLayer = new ol.layer.Group({
    'title': 'Mapas Base',
    layers: [
new ol.layer.Tile({
    'title': 'Open Sreet Map',
    'type': 'base',
    source: new ol.source.OSM()
})
]
});
var lyr_FotodelcantnArchidona = new ol.layer.Tile({
                        source: new ol.source.TileWMS(({
                          url: "http://186.46.130.53:8084/geoserver/Archidona/wms?service=WMS&version=1.3.0&request=GetMap",
                          params: {"LAYERS": "ortofoto", "TILED": "true"},
                        })),
                        title: "Ortofotos",
                        
minResolution:0.140022330761,

                        maxResolution:70.0111653807,

                      })
var lyr_ManzanasdelcantnArchidona = new ol.layer.Tile({
                        source: new ol.source.TileWMS(({
                          url: "http://186.46.130.53:8084/geoserver/Archidona/wms?service=WMS&version=1.3.0&request=GetMap",
                          params: {"LAYERS": "manzanas", "TILED": "true"},
                        })),
                        title: "Manzanas del canton Archidona",
                        opacity:0.5,
minResolution:2.80044661523,

                        maxResolution:70.0111653807,

                      });var lyr_PrediosUrbanosdeArchidona = new ol.layer.Tile({
                        source: new ol.source.TileWMS(({
                          url: "http://186.46.130.53:8084/geoserver/Archidona/wms?service=WMS&version=1.3.0&request=GetMap",
                          params: {"LAYERS": "predios", "TILED": "true"},
                        })),
                        title: "Predios Urbanos de Archidona",
                        opacity:0.4,
                        
minResolution:0.140022330761,

                        maxResolution:2.80044661523,

                      });var lyr_Edificacionesdeunpisourbano = new ol.layer.Tile({
                        source: new ol.source.TileWMS(({
                          url: "http://186.46.130.53:8084/geoserver/Archidona/wms?service=WMS&version=1.3.0&request=GetMap",
                          params: {"LAYERS": "piso_edificacion1", "TILED": "true"},
                        })),
                        title: "Edificaciones de un piso urbano",
                        opacity:0.4,
minResolution:0.140022330761,

                        maxResolution:0.700111653807,

                      });var lyr_Edificacionesdetrespisosurbano = new ol.layer.Tile({
                        source: new ol.source.TileWMS(({
                          url: "http://186.46.130.53:8084/geoserver/Archidona/wms?service=WMS&version=1.3.0&request=GetMap",
                          params: {"LAYERS": "piso_edificacion3", "TILED": "true"},
                        })),
                        title: "Edificaciones de tres pisos urbano",
                        opacity:0.4,
minResolution:0.140022330761,

                        maxResolution:0.700111653807,

                      });var lyr_Edificacionesdedospisosurbano = new ol.layer.Tile({
                        source: new ol.source.TileWMS(({
                          url: "http://186.46.130.53:8084/geoserver/Archidona/wms?service=WMS&version=1.3.0&request=GetMap",
                          params: {"LAYERS": "piso_edificacion2", "TILED": "true"},
                        })),
                        title: "Edificaciones de dos pisos urbano",
                        opacity:0.4,
minResolution:0.140022330761,

                        maxResolution:0.700111653807,

                      });var lyr_Edificacionesdecuatropisosurbano = new ol.layer.Tile({
                        source: new ol.source.TileWMS(({
                          url: "http://186.46.130.53:8084/geoserver/Archidona/wms?service=WMS&version=1.3.0&request=GetMap",
                          params: {"LAYERS": "piso_edificacion4", "TILED": "true"},
                        })),
                        title: "Edificaciones de cuatro pisos urbano",
                        opacity:0.4,
minResolution:0.140022330761,

                        maxResolution:0.700111653807,

                      });

lyr_FotodelcantnArchidona.setVisible(true);lyr_ManzanasdelcantnArchidona.setVisible(true);lyr_PrediosUrbanosdeArchidona.setVisible(true);lyr_Edificacionesdeunpisourbano.setVisible(true);lyr_Edificacionesdetrespisosurbano.setVisible(true);lyr_Edificacionesdedospisosurbano.setVisible(true);lyr_Edificacionesdecuatropisosurbano.setVisible(true);
var layersList = [baseLayer,lyr_FotodelcantnArchidona,lyr_ManzanasdelcantnArchidona,lyr_PrediosUrbanosdeArchidona,lyr_Edificacionesdeunpisourbano,lyr_Edificacionesdetrespisosurbano,lyr_Edificacionesdedospisosurbano,lyr_Edificacionesdecuatropisosurbano];
