------MANEJO DE CICLO SECTOR RUTA, BASE DE DATOS MODULO AGUA POTABLE
-----	Se debe crear en el modulo de agua potable, el Parametros de Agua POtable, y tengo un subitem denominado Control de Redes, armar para poder gestrionar todo ciclos sectores y rutas
-----/WebAppCatastro/seguridad/agua_potable/frmListarCiclos.sigcat, URL; el ITEM Control de Redes
----crear tabla CICLO
CREATE TABLE agua_potable.t_ciclo
(
    sr_id_ciclo integer NOT NULL,
    chv_nombre character varying(30) COLLATE pg_catalog."default",
    ch_estado_logico character(1) COLLATE pg_catalog."default",
    ts_fecha_registro timestamp without time zone,
    ts_fecha_actualizacion timestamp without time zone,
    ts_fecha_baja timestamp without time zone,
    id_usuario integer,
    CONSTRAINT t_ciclo_pkey PRIMARY KEY (sr_id_ciclo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-------------------------------------------
---------crear vista del ciclo para listar

CREATE OR REPLACE VIEW agua_potable.v_buscar_ciclo AS
 SELECT
agua_potable.t_ciclo.sr_id_ciclo,
agua_potable.t_ciclo.chv_nombre,
agua_potable.t_ciclo.ch_estado_logico,
agua_potable.t_ciclo.ts_fecha_registro,
agua_potable.t_ciclo.ts_fecha_actualizacion,
agua_potable.t_ciclo.ts_fecha_baja,
agua_potable.t_ciclo.id_usuario
FROM
agua_potable.t_ciclo
ORDER BY
agua_potable.t_ciclo.sr_id_ciclo
-----------------------------------------
-----crear funcion para listar los ciclos activos
CREATE OR REPLACE FUNCTION agua_potable.f_seleccionar_ciclos(
	)
    RETURNS SETOF agua_potable.v_buscar_ciclos 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE
AS $BODY$

begin
    return query
    select *
    from agua_potable.v_buscar_ciclos   
    where ch_estado_logico = 'A'; 
end;

$BODY$;
-----------------------------------------
-----------TABLA CATEGORIA

CREATE TABLE agua_potable.t_categoria
(
    sr_int_categoria integer NOT NULL,
    chv_descripcion_categoria character varying(20) COLLATE pg_catalog."default",
    db_multa_categoria double precision,
    ch_estado_logico character(1) COLLATE pg_catalog."default",
    ts_fecha_registro timestamp without time zone,
    ts_fecha_actualizacion timestamp without time zone,
    ts_fecha_baja timestamp without time zone,
    session_usuario integer,
    CONSTRAINT t_pliego_categoria_pkey PRIMARY KEY (sr_int_categoria)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

------------------------------------
----TABLA CICLO

CREATE TABLE agua_potable.t_ciclo
(
    sr_id_ciclo integer NOT NULL,
    chv_nombre character varying(30) COLLATE pg_catalog."default",
    ch_estado_logico character(1) COLLATE pg_catalog."default",
    ts_fecha_registro timestamp without time zone,
    ts_fecha_actualizacion timestamp without time zone,
    ts_fecha_baja timestamp without time zone,
    id_usuario integer,
    int_num_ciclo integer,
    int_mes_ciclo integer,
    int_anio_ciclo integer,
    int_paso_ciclo integer,
    ts_fecha_facturacion timestamp without time zone,
    ts_fecha_digitacion timestamp without time zone,
    ts_fecha_validacion timestamp without time zone,
    ts_fecha_actualiza_datos timestamp without time zone,
    ts_fecha_fin_prefacturacion timestamp without time zone,
    ts_fecha_validar_promedios timestamp without time zone,
    ts_fecha_validar_esferas timestamp without time zone,
    ts_fecha_validar_negativos timestamp without time zone,
    ts_fecha_validar_puerta_cerrada timestamp without time zone,
    CONSTRAINT t_ciclo_pkey PRIMARY KEY (sr_id_ciclo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-------------------------------------
-----TABLA LECTURA ARREGLADA LA TABLA

CREATE TABLE agua_potable.t_lectura
(
    sr_id_lectura integer NOT NULL DEFAULT nextval('agua_potable.t_lectura_sr_id_lectura_seq'::regclass),
    int_id_medidor integer NOT NULL,
    fecha_lectura date,
    db_lectura_anterior double precision,
    db_lectura_actual double precision,
    db_consumo double precision,
    ch_estado_logico character(1) COLLATE pg_catalog."default" DEFAULT 'A'::bpchar,
    ts_fecha_registro timestamp without time zone DEFAULT now(),
    ts_fecha_actualizacion timestamp without time zone,
    ts_fecha_baja timestamp without time zone,
    session_usuario integer,
    inicio_periodo date,
    fin_periodo date,
    id_lector integer,
    int_proceso_mes integer,
    int_proceso_anio integer,
    ts_fecha_digitada timestamp without time zone,
    ts_fecha_validacion timestamp without time zone,
    int_novedad integer,
    int_lectura_aplicada integer,
    int_lectura_accion integer,
    int_lectura_verificada integer,
    int_lectura_final integer,
    int_usuario_verificador integer,
    int_usuario_digitador integer,
    int_lectura_calculada integer,
    chv_observacion character varying(250) COLLATE pg_catalog."default",
    by_medidor_lectura bytea,
    by_medidor bytea,
    by_observacion_1 bytea,
    by_observacion_2 bytea,
    by_fachada bytea,
    db_coordenada_x double precision,
    db_coordenada_y double precision,
    CONSTRAINT pk_lectura PRIMARY KEY (sr_id_lectura),
    CONSTRAINT fk_lectura FOREIGN KEY (id_lector)
        REFERENCES master.t_usuario (int_id_usuario) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_medidor FOREIGN KEY (int_id_medidor)
        REFERENCES agua_potable.t_medidor (sr_id_medidor) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_session_usuario FOREIGN KEY (session_usuario)
        REFERENCES master.t_usuario (int_id_usuario) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

--------------------------
--------TABLA MEDIDOR

CREATE TABLE agua_potable.t_medidor
(
    sr_id_medidor integer NOT NULL DEFAULT nextval('agua_potable.t_medidor_sr_id_medidor_seq'::regclass),
    int_num_medidor bigint,
    chv_marca character varying COLLATE pg_catalog."default",
    chv_num_serie character varying COLLATE pg_catalog."default",
    int_lectura_inicial bigint,
    chv_estado character varying COLLATE pg_catalog."default",
    chv_tipo character varying COLLATE pg_catalog."default",
    ch_estado_logico character(1) COLLATE pg_catalog."default" DEFAULT 'A'::bpchar,
    ts_fecha_registro timestamp without time zone DEFAULT now(),
    ts_fecha_actualizacion timestamp without time zone,
    ts_fecha_baja timestamp without time zone,
    session_usuario integer,
    int_medidor_activo integer,
    int_numero_esferas integer,
    ts_fecha_instalacion timestamp without time zone,
    chv_medidor_diametro character varying(10) COLLATE pg_catalog."default",
    chv_medidor_ubicacion character varying COLLATE pg_catalog."default",
    chv_sello_medidor character varying COLLATE pg_catalog."default",
    int_sello_numero integer,
    int_id_instalador integer,
    int_num_cuenta bigint,
    CONSTRAINT pk_medidor PRIMARY KEY (sr_id_medidor),
    CONSTRAINT fk_session_usuario FOREIGN KEY (session_usuario)
        REFERENCES master.t_usuario (int_id_usuario) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-------------------
--TABLA RUTA

CREATE TABLE agua_potable.t_ruta
(
    sr_id_ruta integer NOT NULL,
    int_id_sector integer,
    chv_nombre_ruta character varying COLLATE pg_catalog."default",
    int_numero_ruta integer,
    ts_fecha_digitacion_ruta timestamp without time zone,
    ts_fecha_verificar_ruta timestamp without time zone,
    ts_fecha_validar_ruta timestamp without time zone,
    bl_imprimir_ruta boolean,
    bl_lectura_ruta boolean,
    ch_estado_logico character(1) COLLATE pg_catalog."default",
    ts_fecha_registro timestamp without time zone,
    ts_fecha_actualizacion timestamp without time zone,
    ts_fecha_baja timestamp without time zone,
    session_usuario integer,
    CONSTRAINT t_ruta_pkey PRIMARY KEY (sr_id_ruta)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

------------------
----------TABLA SECTOR
CREATE TABLE agua_potable.t_sector
(
    sr_id_sector integer NOT NULL,
    int_id_ciclo integer,
    chv_nombre character varying COLLATE pg_catalog."default",
    int_secuencia_sector integer,
    ch_estado_logico character(1) COLLATE pg_catalog."default",
    ts_fecha_logico timestamp without time zone,
    ts_fecha_registro timestamp without time zone,
    ts_fecha_actualizacion timestamp without time zone,
    ts_fecha_baja timestamp without time zone,
    session_usuario integer,
    CONSTRAINT t_sector_pkey PRIMARY KEY (sr_id_sector)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;



--------------FUNCION SELECCIONAR CICLOS
CREATE OR REPLACE FUNCTION agua_potable.f_seleccionar_ciclos(
	)
    RETURNS SETOF agua_potable.v_buscar_ciclos 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

begin
    return query
    select *
    from agua_potable.v_buscar_ciclos   
    where ch_estado_logico = 'A'; 
end;

$BODY$;



