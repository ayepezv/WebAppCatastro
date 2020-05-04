-- FUNCTION: topology.f_trigger_insert_predio_gis()

-- DROP FUNCTION topology.f_trigger_insert_predio_gis();

CREATE OR REPLACE FUNCTION topology.f_trigger_insert_predio_gis()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$

declare
   ID_PREDIO_GIS int;
   _ZONA double precision;
   _MANZANA double precision;
   _SECTOR double precision;
   _PARROQUIA integer;   
   _CLAVE_CATASTRAL character varying;
   _ZONA_STR character varying;
   _SECTOR_STR character varying;
   _MANZANA_STR character varying;
   _NUM_PREDIO integer;
   _NUM_PREDIO_STR character varying;
   CLAVE_CATASTRAL character varying;   
   _AREA double precision;
   _PERIMETRO double precision;
   _CENTROIDE_ double precision;
   _CENTROIDE_00 double precision;
   _CENTROIDE_01 double precision;
   _CENTROIDE_02 double precision;  
   _ID_MANZSIG int;
begin
    --- obtengo el id del ultimo predio insertado
    ID_PREDIO_GIS = (SELECT gid FROM topology.predio WHERE geom = NEW.geom);

    --- obtengo la zona

    _ZONA = (select man.zona
	from topology.manzana as man
	  inner join topology.predio as p
	     on ST_Intersects(p.geom, man.geom)
	WHERE p.gid = ID_PREDIO_GIS);

    _SECTOR = (select man.sector
	from topology.manzana as man
	  inner join topology.predio as p
	     on ST_Intersects(p.geom, man.geom)
	WHERE p.gid = ID_PREDIO_GIS);

    _MANZANA = (select man.manzana
	from topology.manzana as man
	  inner join topology.predio as p
	     on ST_Intersects(p.geom, man.geom)
	WHERE p.gid = ID_PREDIO_GIS);

    _PARROQUIA = (select man.parroquia
	from topology.manzana as man
	  inner join topology.predio as p
	     on ST_Intersects(p.geom, man.geom)
	WHERE p.gid = ID_PREDIO_GIS);
    
    _ID_MANZSIG = (select man.gid
	from topology.manzana as man
	  inner join topology.predio as p
	     on ST_Intersects(p.geom, man.geom)
	WHERE p.gid = ID_PREDIO_GIS);

    _NUM_PREDIO=(SELECT coalesce(MAX(predio),'0') FROM topology.predio 
	        WHERE parroquia = _PARROQUIA and zona = _ZONA and sector =_SECTOR and manzana = _MANZANA)+1;

    IF(_ZONA >=10)THEN
       _ZONA_STR = trim(to_char(_ZONA,'99'));        
    ELSE
	_ZONA_STR ='0'||trim(to_char(_ZONA,'9'));
    END IF;

    IF(_SECTOR >=10)THEN
        _SECTOR_STR = trim(to_char(_SECTOR, '99'));
    ELSE
	_SECTOR_STR ='0'||trim(to_char(_SECTOR, '9'));
    END IF;

    IF(_MANZANA >=10)THEN
        _MANZANA_STR ='0' ||trim(to_char(_MANZANA, '99'));
    ELSE
	_MANZANA_STR ='00' ||trim(to_char(_MANZANA, '9'));
    END IF;

    IF(_NUM_PREDIO >=10)THEN
        _NUM_PREDIO_STR = '0' ||trim(to_char(_NUM_PREDIO, '99'));
    ELSE
	_NUM_PREDIO_STR ='00' ||trim(to_char(_NUM_PREDIO, '9'));
    END IF;

    CLAVE_CATASTRAL = (_ZONA_STR||_SECTOR_STR||_MANZANA_STR||_NUM_PREDIO_STR||'00000000');

    ----- CALCULO DE LAS GEOMETRIAS

	--- CALCULO DEL ÁREA	
	_AREA = (SELECT st_area(geom) FROM topology.predio WHERE geom = NEW.geom);
	
	--- CALCULO DEL PERIMETRO
	_PERIMETRO = (SELECT st_perimeter(geom) FROM topology.predio WHERE geom = NEW.geom);
	
	--- CALCULO DE LOS CENTROIDES
	_CENTROIDE_ = (SELECT st_x(st_centroid(geom)) FROM topology.predio WHERE geom = NEW.geom);

	_CENTROIDE_00 = (SELECT st_y(st_centroid(geom)) FROM topology.predio WHERE geom = NEW.geom);

	_CENTROIDE_01 = (SELECT st_x(st_transform(st_centroid(geom),4326)) FROM topology.predio WHERE geom = NEW.geom);

	_CENTROIDE_02 = (SELECT st_y(st_transform(st_centroid(geom),4326)) FROM topology.predio WHERE geom = NEW.geom);    
    
    update topology.predio
    set   provincia=15,
          canton=3,
          parroquia=_PARROQUIA,
	  	  zona=_ZONA,
          manzana = _MANZANA,
          sector=_SECTOR,
          predio=_NUM_PREDIO,          
          clave_cata = CLAVE_CATASTRAL,
          propiedad_=0,
          derechos_a=0,
	  area = _AREA,
	  perimetro = _PERIMETRO,
	  centroide_ = _CENTROIDE_01,
	  centroid00 = _CENTROIDE_02,
	  centroid01 = _CENTROIDE_,
	  centroid02 = _CENTROIDE_00,
      id_manzsig = _ID_MANZSIG,
      fotografia = '1.JPG',
      html = '<a title="Imagen" href="http://catastro.archidona.gob.ec/WebAppCatastro/faces/media/fachadas/1.JPG"><img src="http://catastro.archidona.gob.ec/WebAppCatastro/faces/media/fachadas/1.JPG" width="300"/></a>'    
    where gid = ID_PREDIO_GIS;
      
    return new;
end;

$BODY$;

ALTER FUNCTION topology.f_trigger_insert_predio_gis()
    OWNER TO postgres;

