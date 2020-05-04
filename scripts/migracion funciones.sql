------------MIGRACION FUNCIONES


CREATE OR REPLACE FUNCTION migracion.f_migracion_poner_clave_completa(
	)
    RETURNS text
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
AS $BODY$

declare
	msg text;
	hint text;
	rec record;
	---------------------------REALES USADOS
		MANZANAO text;
		PREDIOO text;
		CLAVE_COMPLETA text;
    
begin
	begin
     
		for rec in (select * from migracion.liquidacion_20180629 order by titulo_numero)
		
				loop
					 
					 if rec.manzana >=10 then
							MANZANAO='0' || rec.manzana;
					 ELSE
							MANZANAO='00' || rec.manzana;
					 END IF;
					 
					 if rec.predio >=10 then
							PREDIOO='0' || rec.predio;
					 ELSE
							PREDIOO='00' || rec.predio;
					 END IF;
					 
					 CLAVE_COMPLETA= '0' || rec.zona || '0' || rec.sector || MANZANAO || PREDIOO || '00000000';
					 
				UPDATE migracion.liquidacion_20180629 SET clave_catastral_bien = CLAVE_COMPLETA WHERE titulo_numero=rec.titulo_numero;
					
				end loop;
				
				msg ='LOS CLAVES HAN SIDO INGRESADAS';
			end;
	return msg;
end;

$BODY$;

------------------poner id del predio segun la clave catasral
CREATE OR REPLACE FUNCTION migracion.f_migracion_poner_id_predio_x_clave(
	)
    RETURNS text
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
AS $BODY$

declare
	msg text;
	hint text;
	rec record;
	---------------------------REALES USADOS
		ID_PREDIO_NEW integer;
		CONT integer;
    
begin
	begin
     CONT=0;
		for rec in (SELECT migracion.liquidacion_20180629.titulo_numero, migracion.liquidacion_20180629.strcedula_ruc, migracion.liquidacion_20180629.clave_catastral_bien, migracion.liquidacion_20180629.id_propietario FROM migracion.liquidacion_20180629)
		
				loop
					 select COALESCE (int_id_predio,0) INTO ID_PREDIO_NEW from catastro.t_predio2 where chv_clave_catastral = rec.clave_catastral_bien;
					
					 UPDATE migracion.liquidacion_20180629 SET id_predio = ID_PREDIO_NEW WHERE titulo_numero=rec.titulo_numero;
					 CONT = CONT +1;
				end loop;
				
				msg ='LOS ID HAN SIDO INGRESADOS EN UN TOTAL DE: ' || CONT;
			end;
	return msg;
end;

$BODY$;