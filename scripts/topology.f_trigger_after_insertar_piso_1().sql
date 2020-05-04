-- FUNCTION: topology.f_trigger_after_insertar_piso_1()

-- DROP FUNCTION topology.f_trigger_after_insertar_piso_1();

CREATE FUNCTION topology.f_trigger_after_insertar_piso_1()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF 
    ROWS 0
AS $BODY$

declare
    CLAVE_CATASTRAL character varying;
    ID_PREDIO_ALFANUM integer;
    _PROVINCIA integer:=15;    
    _CANTON integer:=3;
    _PARROQUIA integer;
    _ZONA integer;
    _SECTOR integer;
    _MANZANA integer;
    _NUM_PREDIO integer;
    ID_PREDIO_GIS integer;
    ID_PISO1 integer;
    _AREA double precision;
    _PERIMETRO double precision;
    _CENTROIDE_ double precision;
    _CENTROIDE_00 double precision;
    _CENTROIDE_01 double precision;
    _CENTROIDE_02 double precision;    
    _BLOQUE_STR character varying;
    _PISO integer;
    _ID_BLOQUE integer;
    _PISO_STR character varying;
begin
    begin
        ----- ID DEL ULTIMO PISO INSERTADO
        ID_PISO1 = (SELECT gid FROM topology.piso1 WHERE geom = NEW.geom);

        ---ID DEL PREDIO AL CUAL PERTENECE
        ID_PREDIO_GIS = (select p.gid
	from topology.piso1 as bloq
	  inner join topology.predio as p
	     on ST_Intersects(bloq.geom, p.geom)
	WHERE bloq.gid = ID_PISO1);

	_ZONA = (SELECT zona FROM topology.predio WHERE gid = ID_PREDIO_GIS);

	_PARROQUIA = (SELECT parroquia FROM topology.predio WHERE gid = ID_PREDIO_GIS);

	_SECTOR= (SELECT sector FROM topology.predio WHERE gid = ID_PREDIO_GIS);

	_MANZANA= (SELECT manzana FROM topology.predio WHERE gid = ID_PREDIO_GIS);

	_NUM_PREDIO= (SELECT predio FROM topology.predio WHERE gid = ID_PREDIO_GIS);

	CLAVE_CATASTRAL = (SELECT clave_cata FROM topology.predio WHERE gid = ID_PREDIO_GIS);

	--- CALCULO DEL ÁREA	
	_AREA = (SELECT st_area(geom) FROM topology.piso1 WHERE geom = NEW.geom);
	
	--- CALCULO DEL PERIMETRO
	_PERIMETRO = (SELECT st_perimeter(geom) FROM topology.piso1 WHERE geom = NEW.geom);
	
	--- CALCULO DE LOS CENTROIDES
	_CENTROIDE_ = (SELECT st_x(st_centroid(geom)) FROM topology.piso1 WHERE geom = NEW.geom);

	_CENTROIDE_00 = (SELECT st_y(st_centroid(geom)) FROM topology.piso1 WHERE geom = NEW.geom);

	_CENTROIDE_01 = (SELECT st_x(st_transform(st_centroid(geom),4326)) FROM topology.piso1 WHERE geom = NEW.geom);

	_CENTROIDE_02 = (SELECT st_y(st_transform(st_centroid(geom),4326)) FROM topology.piso1 WHERE geom = NEW.geom);
	
	update topology.piso1
	SET idpiso1= ID_PISO1, 
	  provincia = _PROVINCIA, 
	  canton = _CANTON,
	  parroquia = _PARROQUIA, 
	  zona = _ZONA, 
	  sector = _SECTOR, 
	  manzana = _MANZANA, 
	  predio = _NUM_PREDIO, 	  
	  propiedad_ = 0,
	  derechos_a = 0, 
	  clave_cata = CLAVE_CATASTRAL, 	  
	  area = _AREA,
	  perimetro = _PERIMETRO,	  
	  centroide_ = _CENTROIDE_01,
	  centroid00 = _CENTROIDE_02,
	  centroid01 = _CENTROIDE_,
	  centroid02 = _CENTROIDE_00
	WHERE gid = ID_PISO1;
    
    ---inserciones en la tabla bloque	
	_PISO = (SELECT numero_blo FROM topology.piso1 WHERE geom = NEW.geom);
	IF _PISO IS NULL THEN
    	_PISO = (SELECT coalesce(MAX(numero_blo),'0') FROM topology.piso1 
	        WHERE parroquia = _PARROQUIA and zona = _ZONA and sector =_SECTOR and manzana = _MANZANA AND predio=_NUM_PREDIO)+1;
     END IF;
    _BLOQUE_STR ='BLOQUE '||trim(to_char(_PISO, '99'));

	---insercion en la tabla bloque
	insert into catastro.t_bloque2(chv_descripcion, db_area_bloque,chv_bloque,id_piso1_bloque_geospacial)
	values (_BLOQUE_STR,_AREA,_BLOQUE_STR,ID_PISO1);

	--insercion en la tabla piso
	_ID_BLOQUE = (SELECT MAX(int_id_bloque) FROM catastro.t_bloque2);  --- ultimo id insertado en la tabla bloque
	_PISO_STR ='PISO '||trim(to_char(_PISO, '99'));
	
	insert into catastro.t_piso_2(int_id_bloque,db_area_construcc,chv_piso,chv_descripcion)
	values(_ID_BLOQUE, _AREA, _PISO_STR,_PISO_STR);
        return new;
    end;
end;

$BODY$;

ALTER FUNCTION topology.f_trigger_after_insertar_piso_1()
    OWNER TO postgres;

