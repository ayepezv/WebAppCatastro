-- FUNCTION: catastro.f_asignar_excencion_predio(integer, integer, date, date, double precision, integer, character varying, double precision)

-- DROP FUNCTION catastro.f_asignar_excencion_predio(integer, integer, date, date, double precision, integer, character varying, double precision);

CREATE OR REPLACE FUNCTION catastro.f_asignar_excencion_predio(
	_id_predio integer,
	_id_excencion integer,
	_dt_fecha_inicio date,
	_dt_fecha_fin date,
	_db_porcentaje double precision,
	_id_session_usuario integer,
	_chv_observaciones character varying,
	_db_capital_biess double precision)
RETURNS text
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
    ROWS 0
AS $BODY$

    
declare
    msg text;
    sugerencia text;
    AVALUO_PREDIO_CALC double precision;
    DESCUENTO double precision;
    _AVALUO_IMPONIBLE double precision;
    --_ID_EXCENCION integer;
	_EXCENCION TEXT;
begin
     --- validaciones 
	 begin
        if exists (SELECT id_predio FROM catastro.t_predio_excencion WHERE id_predio=_id_predio and ch_estado_logico='A') then                    
            msg = 'El predio ya tiene una excepción asociada.';
			sugerencia = 'Verifique los datos ingresados.';
			raise exception '%', msg
			using hint = sugerencia;
        end if;
    end;
    --- fin validaciones
    begin
       
        SELECT chv_excencion INTO _EXCENCION
        FROM catastro.t_excenciones_esp
        WHERE sr_id_excencion=_id_excencion;
        
        select db_avaluo into AVALUO_PREDIO_CALC
        from catastro.t_liquidacion
        where id_predio=_id_predio;
        --_ID_EXCENCION=_id_excencion;
        
        CASE _id_excencion
 			WHEN 1 THEN   --EXCENCION TERCERA EDAD
            	DESCUENTO = AVALUO_PREDIO_CALC*(NEW.db_porcentaje/100);
 			WHEN 2 THEN   --EXCENCION DISCAPACIDAD 100% CONTROLAR AQUI LOS PREDIOS
            	DESCUENTO = AVALUO_PREDIO_CALC*(NEW.db_porcentaje/100);
 			WHEN 3 THEN   --EXCENCION INSTITUCION PUBLICA 100%
            	DESCUENTO = AVALUO_PREDIO_CALC;
            WHEN 4 THEN   --EXCENCION REBAJAS/DESCUENTOS BIESS PORCENTUAL REVISAR
            	DESCUENTO = AVALUO_PREDIO_CALC*(NEW.db_porcentaje/100);
 			WHEN 5 THEN   --EXCENCION OTROS  SIN DEFINICION ESTA PORCENTUAL
            	DESCUENTO = AVALUO_PREDIO_CALC*(NEW.db_porcentaje/100);
            WHEN 6 THEN   --NINGUNA EXCENCION 0%
            	DESCUENTO = 0;
            WHEN 13 THEN  --EXCENCION PREDIO MUNICIPAL 100%
            	DESCUENTO = AVALUO_PREDIO_CALC;
            WHEN 15 THEN  --EXCENCION PREDIO EXENCIÓN TEMPORAL BIESS TEMPORAL BASADO EN EL CAPITAL
            	DESCUENTO=AVALUO_PREDIO_CALC* (NEW.db_capital_Biess*NEW.db_porcentaje/100);
                if DESCUENTO>AVALUO_PREDIO_CALC/2 then
					DESCUENTO=AVALUO_PREDIO_CALC/2;
				end if;
            WHEN 16 THEN  --EXCENCION PREDIO NO EDIFICADO
            	DESCUENTO = 0;
            WHEN 17 THEN  --EXCENCION PREDIO LEY DE CULTOS
            	DESCUENTO = AVALUO_PREDIO_CALC*(NEW.db_porcentaje/100);
            ELSE
     			DESCUENTO = 0;
 		END CASE;
    
    	_AVALUO_IMPONIBLE=AVALUO_PREDIO_CALC-DESCUENTO;
        insert into catastro.t_predio_excencion(id_predio,id_excencion,dt_fecha_inicio,dt_fecha_fin,db_porcentaje,id_session_usuario,chv_observaciones,db_avaluo_predio,db_descuento_excencion,db_avaluo_imponible,db_capital_Biess)
        values(_id_predio,_id_excencion,_dt_fecha_inicio,_dt_fecha_fin,_db_porcentaje,_id_session_usuario,_chv_observaciones,AVALUO_PREDIO_CALC,DESCUENTO,_AVALUO_IMPONIBLE,_db_capital_Biess);
        
        UPDATE catastro.t_predio2
        SET db_porcent_excep=_db_porcentaje,
            chv_excencion_esp=_EXCENCION
        WHERE int_id_predio=_id_predio;
        
        
        msg ='Excencion añadida exitosamente.';
    end;
    return msg;
end;

$BODY$;

ALTER FUNCTION catastro.f_asignar_excencion_predio(integer, integer, date, date, double precision, integer, character varying, double precision)
    OWNER TO postgres;

