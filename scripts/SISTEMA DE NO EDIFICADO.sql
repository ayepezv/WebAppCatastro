------CREAR TABLA DE PREDIO NO EDIFICADO Y EL SERIAL PARA EL SISTEMA DE NO EDIFICADO
Table: catastro.t_predio_no_edificado

-- DROP TABLE catastro.t_predio_no_edificado;

--CREATE SEQUENCE catastro.t_predio_no_edificado_sr_id_predio_no_edificado
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


CREATE TABLE catastro.t_predio_no_edificado
(   
    sr_id_predio_no_edificado integer NOT NULL DEFAULT nextval('catastro.t_predio_no_edificado_sr_id_predio_no_edificado'::regclass),
    id_predio integer NOT NULL,
    chv_razon character varying(50) COLLATE pg_catalog."default",
    int_permanente integer,
    dt_fecha_inicio date,
    dt_fecha_fin date,
    ch_estado_logico character(1) COLLATE pg_catalog."default" DEFAULT 'A'::bpchar,
    int_activado integer,
    chv_reclamo character varying(300) COLLATE pg_catalog."default",
    chv_responsable character varying COLLATE pg_catalog."default",
    ts_fecha_registro timestamp without time zone DEFAULT now(),
    ts_fecha_baja timestamp without time zone,
    ts_fecha_actualizacion timestamp without time zone,
    id_session_usuario integer NOT NULL,
    chv_observaciones character varying COLLATE pg_catalog."default",
    db_avaluo_predio double precision,
    db_descuento_no_edificado double precision,
    db_avaluo_imponible double precision,
	CONSTRAINT pk_predio_no_edificado PRIMARY KEY (sr_id_predio_no_edificado),
    CONSTRAINT fk_predio FOREIGN KEY (id_predio)
        REFERENCES catastro.t_predio2 (int_id_predio) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_session_usuario FOREIGN KEY (id_session_usuario)
        REFERENCES master.t_usuario (int_id_usuario) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


----------------------FUNCION PARA LISTAR LOS PREDIOS CON NO EDIFICADOS


CREATE OR REPLACE FUNCTION catastro.f_seleccionar_predio_no_edificado(
	)
    RETURNS SETOF catastro.t_predio_no_edificado 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
AS $BODY$

begin
	return query
	select *
	from catastro.t_predio_no_edificado
	where ch_estado_logico = 'A' 
	order by ts_fecha_registro DESC;
end;

$BODY$;


------------------------INSERTAR Y ACTUALIZAR EXCENCION PREDIO NO EDIFICADO CON RAZONES
CREATE OR REPLACE FUNCTION catastro.f_insertar_t_excenciones_no_edificado(
	_predio integer,
	_razon character varying,
	_reclamo character varying,
	_usuario integer,
	_observacion character varying)
    RETURNS text
    LANGUAGE 'plpgsql'
	COST 100
    VOLATILE 
AS $BODY$

declare
    msg text;
	ESTADO character varying; --estado logico
	PERMANENTE integer; --CONOCER SI ES PERMANENTE O TEMPORAL EL NO EDIFICADO
	ACTIVADO integer; --si esta activa la rebaja de no edificado
	AVALUO_PREDIO double precision; --avaluo del predio para saber
	DESCUENTO_NO_EDIFICADO double precision; --conocer cuanto es el descuento
	AVALUO_IMPONIBLE double precision; --CONOCER EL AVALUO IPONIBLE DEL BIEN no es necesario
	FECHA_INICIO timestamp;
	FECHA_FIN timestamp;
	COEFICIENTE_NO_EDIFICADO double precision;
begin
--select * from catastro.t_liquidacion limit 5 ------PARA VER DATOS

SELECT db_avaluo into AVALUO_IMPONIBLE FROM catastro.t_liquidacion WHERE t_liquidacion.id_predio = _predio;
SELECT db_valor into COEFICIENTE_NO_EDIFICADO FROM catastro.t_parametros_administrativos WHERE chv_codigo='RECARGO_CONSTRUCCION';
AVALUO_PREDIO = AVALUO_IMPONIBLE;
DESCUENTO_NO_EDIFICADO = AVALUO_IMPONIBLE * COEFICIENTE_NO_EDIFICADO;
FECHA_INICIO = now();	
ESTADO = 'A';
ACTIVADO = 1;
CASE _razon
	WHEN 'AREA VERDE' THEN
		PERMANENTE=1; ---1 indica que si es permanente
		FECHA_FIN = now()+ interval '50 years';
		ACTIVADO = 1;
	WHEN 'CONSTRUCCION OBSOLETA' THEN
		PERMANENTE=0; ---0 indica que NO es permanente
		FECHA_FIN = now()+ interval '20 years';
		ACTIVADO = 1;
	WHEN 'ELIMINACION' THEN
		PERMANENTE=1; ---1 indica que si es permanente
		FECHA_FIN = now()+ interval '50 years';
		ACTIVADO = 1;
	WHEN 'ESTACIONAMIENTO' THEN
		PERMANENTE=1; ---1 indica que si es permanente
		FECHA_FIN = now()+ interval '50 years';
		ACTIVADO = 1;
	WHEN 'EXPLOTACION AGRICOLA' THEN
		PERMANENTE=0; ---0 indica que NO es permanente
		FECHA_FIN = now()+ interval '10 years';
		ACTIVADO = 1;	
	WHEN 'LOTIZACION AUTORIZADA' THEN
		PERMANENTE=0; ---0 indica que NO es permanente
		FECHA_FIN = now()+ interval '2 years';
		ACTIVADO = 1;
	WHEN 'SINIESTRO' THEN
		PERMANENTE=0; ---0 indica que NO es permanente
		FECHA_FIN = now()+ interval '2 years';
		ACTIVADO = 1;
	WHEN 'TRANSFERENCIA' THEN
		PERMANENTE=0; ---0 indica que NO es permanente
		FECHA_FIN = now()+ interval '2 years';
		ACTIVADO = 1;
	WHEN 'TRANSFERENCIA CON CONSTRUCCION' THEN
		PERMANENTE=0; ---0 indica que NO es permanente
		FECHA_FIN = now()+ interval '2 years';
		ACTIVADO = 1;
	WHEN 'VIVIENDA INTERES SOCIAL' THEN
		PERMANENTE=1; ---0 indica que NO es permanente
		FECHA_FIN = now()+ interval '20 years';
		ACTIVADO = 1;	
	
	ELSE
		PERMANENTE=0; ---0 indica que NO es permanente
		FECHA_FIN = now()+ interval '2 years';
		ACTIVADO = 1;
END CASE;

IF EXISTS (SELECT * FROM catastro.t_predio_no_edificado WHERE id_predio = _predio AND ch_estado_logico='A') THEN
	UPDATE  catastro.t_predio_no_edificado 
	SET chv_razon=_razon, int_permanente=PERMANENTE, dt_fecha_inicio =_fecha_inicio, dt_fecha_fin=_fecha_fin, ch_estado_logico=ESTADO, int_activado=ACTIVADO, chv_reclamo=_reclamo, ts_fecha_actualizacion=now(), id_session_usuario=_usuario, chv_observaciones=_observacion, db_avaluo_predio=AVALUO_PREDIO, db_descuento_no_edificado=DESCUENTO_NO_EDIFICADO,db_avaluo_imponible=AVALUO_IMPONIBLE
	WHERE id_predio = _predio;
	msg = 'Predio No Edificado actualizado correctamente.';
ELSE
	INSERT INTO catastro.t_predio_no_edificado(id_predio,chv_razon,int_permanente,dt_fecha_inicio,dt_fecha_fin,ch_estado_logico,int_activado,chv_reclamo,ts_fecha_registro,id_session_usuario,chv_observaciones,db_avaluo_predio,db_descuento_no_edificado,db_avaluo_imponible)
	VALUES (_predio,_razon,PERMANENTE,_fecha_inicio,_fecha_fin,ESTADO,ACTIVADO,_reclamo,now(),_usuario,_observacion,AVALUO_PREDIO,DESCUENTO_NO_EDIFICADO,AVALUO_IMPONIBLE);
	msg = 'Predio No Edificado insertado correctamente.';
END IF;
return msg;

end;

$BODY$;


---------------------- ELIMINAR LA EXCENCION DE PREDIO NO EDIFICADO

CREATE OR REPLACE FUNCTION catastro.f_desactivar_t_excenciones_no_edificado(
	_id_predio_no_edificado integer)
    RETURNS text
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
AS $BODY$

declare
    msg text;
begin
    update catastro.t_predio_no_edificado
    set ch_estado_logico = 'D',
    	int_activado = 0,
        ts_fecha_baja = now()
    where sr_id_predio_no_edificado = _id_predio_no_edificado;
   
    msg = 'El predio con No Edificado se eliminó correctamente.';
    return msg;
end;

$BODY$;

-------------------VISTA PARA BUSCAR UN DATO POR NO EDIFICADO DE UN PREDIO
CREATE OR REPLACE VIEW catastro.v_datos_no_edificados_predios AS
     SELECT
    pred.int_id_predio,
    pred.chv_clave_catastral,
    pred.int_id_titular_dom,
    per.sr_id_persona,
    per.chv_cedula,
    per.chv_nombres,
    per.chv_apellidos,
    pred.int_zona,
    pred.int_sector,
    pred.int_manzana,
    pred.int_num_predio,
    liq.db_avaluo,
    per.chv_ruc,
    per.ts_fecha_nacimiento::date AS ts_fecha_nacimiento,
    catastro.t_predio_no_edificado.chv_razon,
    catastro.t_predio_no_edificado.dt_fecha_inicio,
    catastro.t_predio_no_edificado.dt_fecha_fin,
    catastro.t_predio_no_edificado.chv_reclamo,
    catastro.t_predio_no_edificado.chv_observaciones,
    catastro.t_predio_no_edificado.db_descuento_no_edificado,
    catastro.t_predio_no_edificado.db_avaluo_predio,
    catastro.t_predio_no_edificado.db_avaluo_imponible,
    catastro.t_predio_no_edificado.ch_estado_logico,
    (CASE WHEN catastro.t_predio_no_edificado.int_permanente=1 THEN 'PERMANENTE' ELSE 'TEMPORAL' END) AS temporalidad,
    t_predio_no_edificado.sr_id_predio_no_edificado
    FROM
    catastro.t_predio2 AS pred
    JOIN master.t_persona AS per ON pred.int_id_titular_dom = per.sr_id_persona
    INNER JOIN catastro.t_liquidacion AS liq ON pred.int_id_predio = liq.id_predio
    INNER JOIN catastro.t_predio_no_edificado ON pred.int_id_predio = catastro.t_predio_no_edificado.id_predio
    ORDER BY
    per.chv_apellidos, per.chv_nombres ASC;



-------------- funcion para buscar no edificado predi por un criterio
CREATE OR REPLACE FUNCTION catastro.f_obtener_no_edificado_predios_dado_criterio(
	criterio character varying)
    RETURNS SETOF catastro.v_datos_no_edificados_predios 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
AS $BODY$

begin
    return query
    select * 
    from catastro.v_datos_no_edificados_predios
    where ch_estado_logico='A'
    and ((upper(chv_nombres) like ('%'||upper(criterio)||'%')) 
         or (upper(chv_apellidos) like ('%'||upper(criterio)||'%'))
         or (upper(chv_ruc) like ('%'||upper(criterio)||'%')));
end;

$BODY$;

------------------ funcion para buscar predio no edificados por zona manzana sector
CREATE OR REPLACE FUNCTION catastro.f_obtener_no_edificados_dado_zona_sector_manzana_predio(
	zona integer,
	sector integer,
	manzana integer,
	predio integer)
    RETURNS SETOF catastro.v_datos_no_edificados_predios 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
AS $BODY$

begin
    return query
    select *
    from catastro.v_datos_no_edificados_predios
    where ch_estado_logico='A' 
          AND int_zona=ZONA
          AND int_sector=SECTOR
          AND int_manzana=MANZANA
          AND int_num_predio=PREDIO
    order by chv_clave_catastral desc;
end;

$BODY$;

-------------------- funcion obtener listado de predios con no edificados
CREATE OR REPLACE FUNCTION catastro.f_obtener_no_edificados_predios(
	)
RETURNS SETOF catastro.v_datos_no_edificados_predios 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

begin
    return query
    select * 
    from catastro.v_datos_no_edificados_predios
    where ch_estado_logico='A';
end;

$BODY$;