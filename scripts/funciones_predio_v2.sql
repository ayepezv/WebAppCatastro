create or replace function catastro.f_seleccionar_predios_v2()
returns setof catastro.t_predio2 as
$body$
begin
	return query
	select *
	from catastro.t_predio2
	order by int_id_predio;
end;
$body$
   language plpgsql volatile 



select * from catastro.f_seleccionar_predios_v2()


create or replace function catastro.f_seleccionar_dominio()
returns setof catastro.t_dominio as
$body$
begin
	return query
	select *
	from catastro.t_dominio
	order by sr_id_dominio;
end;
$body$
   language plpgsql volatile 


select * from catastro.f_seleccionar_dominio()


create or replace function catastro.f_seleccionar_dominio_dado_codigo(codigo int)
returns setof catastro.t_dominio as
$body$
begin
	return query
	select *
	from catastro.t_dominio
	where sr_id_dominio = codigo
	order by sr_id_dominio;
end;
$body$
   language plpgsql volatile 


select * from catastro.f_seleccionar_dominio_dado_codigo(2)


-- insertar pedio v2
create or replace function catastro.f_insertar_predio_v2(in_int_num_ficha integer,
							in_int_id_parroquia integer,
							in_int_zona integer,
							in_int_sector integer,
							in_int_manzana integer,
							in_int_num_predio integer,
							in_int_prop_hor_bloque integer,
							in_int_prop_hor_piso integer,
							in_int_prop_hor_unidad integer,
							in_chv_clave_catastral character varying,
							in_chv_clave_catastral_ant character varying,
							in_chv_barrio character varying,
							in_chv_calle_princ character varying,
							in_chv_calle_secund character varying,
							in_chv_num_casa character varying,
							in_chv_nombre_edificio character varying,
							in_int_id_titular_dom integer,
							in_int_id_rep_legal integer,
							in_int_id_dominio integer,
							in_chv_condicion character varying,
							in_chv_forma_adq character varying,
							in_chv_notaria character varying,
							in_dt_fecha_titulo_prop date,
							in_dt_fecha_registro date,
							in_db_area_escritura double precision,
							in_chv_prop_anterior character varying,
							in_db_avaluo_prop double precision,
							in_chv_gest_uso_suelo character varying,
							in_chv_uso_suelo_consumo character varying,
							in_chv_us_suel_intercambio character varying,
							in_chv_us_suel_produc character varying,
							in_chv_ocupacion_predio character varying,
							in_chv_localizacion_predio character varying,
							in_chv_topografia_pedio character varying,
							in_chv_forma_predio character varying,
							in_chv_caract_suelo character varying,
							in_chv_uso_vias character varying,
							in_chv_material_vias character varying,
							in_chv_red_agua character varying,
							in_chv_alcantarillado character varying,
							in_chv_energ_electric character varying,
							in_chv_red_telefon character varying,
							in_chv_rec_basura character varying,
							in_chv_transp_publico character varying,
							in_chv_internet character varying,
							in_chv_otros_servicios character varying,
							in_db_area_construc double precision,
							in_db_area_total_const double precision,
							in_db_area_total_terreno double precision,
							in_chv_patio character varying,
							in_chv_piscina character varying,
							in_chv_cerramiento character varying,
							in_chv_cisterna character varying,
							in_chv_muros character varying,
							in_chv_ascensor character varying,
							in_int_num_banios integer,
							in_db_lindero_frente1 double precision,
							in_db_lindero_frente2 double precision,
							in_db_lindero_frente3 double precision,
							in_db_lindero_frente4 double precision,
							in_chv_lindero_calle1 character varying,
							in_chv_lindero_calle2 character varying,
							in_chv_lindero_calle3 character varying,
							in_chv_lindero_calle4 character varying,
							in_db_lindero_alicuota1 double precision,
							in_db_lindero_alicuota2 double precision,
							in_db_lindero_alicuota3 double precision,
							in_db_lindero_alicuota4 double precision,
							in_db_derechos_acciones double precision,
							in_chv_croquis character varying,
							in_chv_emplazamiento character varying,
							in_chv_fotografia character varying,
							in_chv_topografia_ref_top character varying,
							in_chv_foto_aerea character varying,
							in_chv_otra_ref_top character varying,
							in_db_coord_e double precision,
							in_db_coord_n double precision,
							in_chv_colindant_nort character varying,
							in_chv_colindant_este character varying,
							in_chv_colindant_sur character varying,
							in_chv_colindant_oest character varying,
							in_chv_fuente_informant character varying,
							in_chv_telf_informant character varying,
							in_chv_ficha_adjunta character varying)
returns text as
$body$
declare
    band text;
begin
    begin
        insert into catastro.t_predio2(  int_num_ficha,
					  int_id_parroquia,
					  int_zona,
					  int_sector,
					  int_manzana,
					  int_num_predio,
					  int_prop_hor_bloque,
					  int_prop_hor_piso,
					  int_prop_hor_unidad,
					  chv_clave_catastral,
					  chv_clave_catastral_ant,
					  chv_barrio,
					  chv_calle_princ,
					  chv_calle_secund,
					  chv_num_casa,
					  chv_nombre_edificio,
					  int_id_titular_dom,
					  int_id_rep_legal,
					  int_id_dominio,
					  chv_condicion,
					  chv_forma_adq,
					  chv_notaria,
					  dt_fecha_titulo_prop,
					  dt_fecha_registro,
					  db_area_escritura,
					  chv_prop_anterior,
					  db_avaluo_prop,
					  chv_gest_uso_suelo,
					  chv_uso_suelo_consumo,
					  chv_us_suel_intercambio,
					  chv_us_suel_produc,
					  chv_ocupacion_predio,
					  chv_localizacion_predio,
					  chv_topografia_pedio,
					  chv_forma_predio,
					  chv_caract_suelo,
					  chv_uso_vias,
					  chv_material_vias,
					  chv_red_agua,
					  chv_alcantarillado,
					  chv_energ_electric,
					  chv_red_telefon,
					  chv_rec_basura,
					  chv_transp_publico,
					  chv_internet,
					  chv_otros_servicios,
					  db_area_construc,
					  db_area_total_const,
					  db_area_total_terreno,
					  chv_patio,
					  chv_piscina,
					  chv_cerramiento,
					  chv_cisterna,
					  chv_muros,
					  chv_ascensor,
					  int_num_banios,
					  db_lindero_frente1,
					  db_lindero_frente2,
					  db_lindero_frente3,
					  db_lindero_frente4,
					  chv_lindero_calle1,
					  chv_lindero_calle2,
					  chv_lindero_calle3,
					  chv_lindero_calle4,
					  db_lindero_alicuota1,
					  db_lindero_alicuota2,
					  db_lindero_alicuota3,
					  db_lindero_alicuota4,
					  db_derechos_acciones,
					  chv_croquis,
					  chv_emplazamiento,
					  chv_fotografia,
					  chv_topografia_ref_top,
					  chv_foto_aerea,
					  chv_otra_ref_top,
					  db_coord_e,
					  db_coord_n,
					  chv_colindant_nort,
					  chv_colindant_este,
					  chv_colindant_sur,
					  chv_colindant_oest,
					  chv_fuente_informant,
					  chv_telf_informant,
					  chv_ficha_adjunta)
        values(in_int_num_ficha,
		in_int_id_parroquia,
		in_int_zona,
		in_int_sector,
		in_int_manzana,
		in_int_num_predio,
		in_int_prop_hor_bloque,
		in_int_prop_hor_piso,
		in_int_prop_hor_unidad,
		in_chv_clave_catastral,
		in_chv_clave_catastral_ant,
		in_chv_barrio,
		in_chv_calle_princ,
		in_chv_calle_secund,
		in_chv_num_casa,
		in_chv_nombre_edificio,
		in_int_id_titular_dom,
		in_int_id_rep_legal,
		in_int_id_dominio,
		in_chv_condicion,
		in_chv_forma_adq,
		in_chv_notaria,
		in_dt_fecha_titulo_prop,
		in_dt_fecha_registro,
		in_db_area_escritura,
		in_chv_prop_anterior,
		in_db_avaluo_prop,
		in_chv_gest_uso_suelo,
		in_chv_uso_suelo_consumo,
		in_chv_us_suel_intercambio,
		in_chv_us_suel_produc,
		in_chv_ocupacion_predio,
		in_chv_localizacion_predio,
		in_chv_topografia_pedio,
		in_chv_forma_predio,
		in_chv_caract_suelo,
		in_chv_uso_vias,
		in_chv_material_vias,
		in_chv_red_agua,
		in_chv_alcantarillado,
		in_chv_energ_electric,
		in_chv_red_telefon,
		in_chv_rec_basura,
		in_chv_transp_publico,
		in_chv_internet,
		in_chv_otros_servicios,
		in_db_area_construc,
		in_db_area_total_const,
		in_db_area_total_terreno,
		in_chv_patio,
		in_chv_piscina,
		in_chv_cerramiento,
		in_chv_cisterna,
		in_chv_muros,
		in_chv_ascensor,
		in_int_num_banios,
		in_db_lindero_frente1,
		in_db_lindero_frente2,
		in_db_lindero_frente3,
		in_db_lindero_frente4,
		in_chv_lindero_calle1,
		in_chv_lindero_calle2,
		in_chv_lindero_calle3,
		in_chv_lindero_calle4,
		in_db_lindero_alicuota1,
		in_db_lindero_alicuota2,
		in_db_lindero_alicuota3,
		in_db_lindero_alicuota4,
		in_db_derechos_acciones,
		in_chv_croquis,
		in_chv_emplazamiento,
		in_chv_fotografia,
		in_chv_topografia_ref_top,
		in_chv_foto_aerea,
		in_chv_otra_ref_top,
		in_db_coord_e,
		in_db_coord_n,
		in_chv_colindant_nort,
		in_chv_colindant_este,
		in_chv_colindant_sur,
		in_chv_colindant_oest,
		in_chv_fuente_informant,
		in_chv_telf_informant,
		in_chv_ficha_adjunta);
        band='Predio registrado exitosamente.';
    end;
    return band;
end;
$body$
   language plpgsql volatile 

---- obtener barrios

create or replace function catastro.f_seleccionar_barrios_all()
returns setof catastro.t_barrio as
$body$
begin
   return query
   select * 
   from catastro.t_barrio 
   where ch_estado_logico ='A'
   order by chv_nombre;
end;
$body$
   language plpgsql volatile


   select * from catastro.f_seleccionar_barrios_all()