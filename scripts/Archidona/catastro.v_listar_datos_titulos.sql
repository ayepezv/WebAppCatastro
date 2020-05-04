-- View: catastro.v_listar_datos_titulos

-- DROP VIEW catastro.v_listar_datos_titulos;

CREATE OR REPLACE VIEW catastro.v_listar_datos_titulos AS
 SELECT predio.int_id_predio,
    liquidacion.id_predio,
    predio.chv_clave_catastral,
    predio.chv_clave_catastral_ant,
    predio.int_id_titular_dom,
    predio.int_id_rep_legal,
    predio.int_id_dominio,
    predio.chv_notaria,
    predio.db_area_escritura,
    predio.chv_prop_anterior,
    predio.db_avaluo_prop,
    predio.db_area_construc,
    predio.db_area_total_const,
    predio.db_area_total_terreno,
    predio.ch_estado_logico,
    predio.db_porcent_excep,
    predio.chv_excencion_esp,
    predio.id_session_usuario,
    predio.id_predio_espacial,
    predio.bl_legalizado,
    liquidacion.sr_id_liquidacion,
    liquidacion.int_anio,
    liquidacion.db_total_costruccion,
    liquidacion.db_total_uso_suelo,
    liquidacion.db_impuesto_predial,
    liquidacion.db_avaluo,
    prop.chv_cedula AS cedula_prop,
    prop.chv_nombres AS nombres_prop,
    prop.chv_apellidos AS apellidos_prop,
    pe.id_predio AS id_predio_excen,
    pe.db_avaluo_predio,
    pe.db_descuento_excencion,
    pe.db_avaluo_imponible
   FROM catastro.t_predio2 predio
     JOIN catastro.t_liquidacion liquidacion ON predio.int_id_predio = liquidacion.id_predio
     JOIN master.t_persona prop ON predio.int_id_titular_dom = prop.sr_id_persona
     LEFT JOIN catastro.t_predio_excencion pe ON predio.int_id_predio = pe.id_predio
  WHERE predio.bl_legalizado = true AND predio.ch_estado_logico = 'A'::bpchar;

ALTER TABLE catastro.v_listar_datos_titulos
    OWNER TO postgres;


