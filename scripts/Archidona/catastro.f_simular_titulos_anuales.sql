-- FUNCTION: catastro.f_simular_titulos_anuales(integer)

-- DROP FUNCTION catastro.f_simular_titulos_anuales(integer);

CREATE OR REPLACE FUNCTION catastro.f_simular_titulos_anuales(
	session_usuario integer)
    RETURNS text
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
AS $BODY$

declare
	msg text;
	hint text;
	rec record;
	VALOR_USO_SUELO double precision;
	VALOR_CONSTRUCCION double precision;
	cont int:=0; -- contador de los predios
	TXT_SECUENCIA text;
	ANIO_TITULO integer;
	EXCENCIONES_ESPECIALES double precision;
	RAZON_EXCENCION character varying;
	_AVALUO_IMPONIBLE double precision;
	_IMPUESTO_PREDIAL double precision;
	_APORTE_REC_BASURA double precision;
	_APORTE_BOMBEROS double precision;	
	_TOTAL_TITULO double precision;	
	_TITULO_EXENCION text;
	_AVALUO double precision;
    _PROCESAMIENTO double precision;
    _LOTE_NO_EDIFICADO double precision;
    _COEFICIENTE_NO_EDIFICADO double precision;
    _COEFICIENTE_BOMBEROS double precision;
    _COEFICIENTE_IMPUESTO_PREDIAL double precision;
    _SALARIOBASICO double precision;
    _SALARIOS_EXCENTOS double precision;
    _VALOR_EXCENTO_PREDIO double precision;
    
begin
	begin
        --- borro los datos de la tabla prueba
        delete from catastro.t_titulos_prueba;
		-- genero el año del titulo
		select into ANIO_TITULO (extract(year from now()))+1;
        --obtener el valor de impuesto predial de parametros administrativos
        SELECT db_valor into _COEFICIENTE_IMPUESTO_PREDIAL	FROM catastro.t_parametros_administrativos WHERE chv_codigo='COEFIMPPREDIAL';
        -- obtener el valor de no edificado de parametros administrativos
        SELECT db_valor into _COEFICIENTE_NO_EDIFICADO FROM catastro.t_parametros_administrativos WHERE chv_codigo='RECARGO_CONSTRUCCION';
		-- obtener el valor de bomberos de parametros administrativos
        SELECT db_valor	into _COEFICIENTE_BOMBEROS FROM catastro.t_parametros_administrativos WHERE chv_codigo='APBOMBEROS';
        -- obtener el valor del salario basico
        SELECT db_valor	into _SALARIOBASICO FROM catastro.t_parametros_administrativos WHERE chv_codigo='SALARIOBASICO';
        -- obtener el valor del salario basico
        SELECT db_valor	into _SALARIOS_EXCENTOS FROM catastro.t_parametros_administrativos WHERE chv_codigo='NUMERO_SALARIOS_EXCENTOS';
        _VALOR_EXCENTO_PREDIO=_SALARIOS_EXCENTOS*_SALARIOBASICO;
        -- obtener el valor del coeficiente de procesamiento dos dolares originalmente
		SELECT db_valor INTO _PROCESAMIENTO FROM catastro.t_parametros_administrativos WHERE chv_codigo='PROCESAMIENTO_TITULO';
        
        
		for rec in (select * from catastro.v_listar_datos_titulos order by chv_excencion_esp)
		
		loop
        	
			select db_avaluo into _AVALUO from catastro.v_listar_datos_titulos where id_predio = rec.int_id_predio;
			
			cont = cont + 1;
            
            RAISE NOTICE 'Numero de titulo: (%)', cont;
			select catastro.f_generar_secuencia_titulo(cont) into TXT_SECUENCIA;
			
			----------------------------------------------------
			----INICIO VALORES CALCULADOS
			----------------------------------------------------

			--- calculo de las exenciones especiales 
	
         
			if exists (select chv_excencion_esp from catastro.v_listar_datos_titulos where id_predio = rec.int_id_predio) then 
				EXCENCIONES_ESPECIALES = rec.db_descuento_excencion; ----DA EL VALOR EN PORCENTAJE DE LA EXCENCION
				-- select chv_excencion_esp into RAZON_EXCENCION from catastro.v_listar_datos_titulos where id_predio = rec.int_id_predio;
                select chv_excencion into RAZON_EXCENCION -------PONGO EL NOMBRE DE LA EXCENCION BUSCANDO EN LA TABLA EXCENCION
                from catastro.t_excenciones_esp 
                where sr_id_excencion=(select id_excencion
                                      from catastro.t_predio_excencion
                                      where id_predio=rec.int_id_predio
                                            and ch_estado_logico='A');
                RAISE NOTICE 'Excención especial: (%)', RAZON_EXCENCION; --------notifica el mensaje en consola
				
			else 
				EXCENCIONES_ESPECIALES = 0;
				RAZON_EXCENCION = 'NINGUNA';
                
                RAISE NOTICE 'Excención especial: (%)', RAZON_EXCENCION;
			end if;
            

			--- fin de las exenciones especiales
            ---COMPROBAR QUE VALOR DE EXCENCION ESPECIAL NO ESTE EN NULL
			if EXCENCIONES_ESPECIALES IS NULL then
				EXCENCIONES_ESPECIALES=0;
			end if;
			if RAZON_EXCENCION='NO EDIFICADO' then
					EXCENCIONES_ESPECIALES=0;
			end if;
            
            --- calculo de las exenciones especiales CUANDO NO SUPERE SU VALOR LOS 25 SALARIOS BASICOS, PONE CERO IMPUESTO PREDIAL
            if (_AVALUO<=_VALOR_EXCENTO_PREDIO) and RAZON_EXCENCION<>'PREDIO MUNICIPAL' then
            	EXCENCIONES_ESPECIALES=_AVALUO;
            	RAZON_EXCENCION = 'PREDIO VALOR MINIMO';
            end if;
            --- fin de las exenciones especiales SALARIOS BASICOS 25

			--- CALCULO DEL AVALUO IMPONIBLE 
			_AVALUO_IMPONIBLE = _AVALUO - EXCENCIONES_ESPECIALES;
			

			--- calculo del impuesto predial
			_IMPUESTO_PREDIAL = _AVALUO_IMPONIBLE * _COEFICIENTE_IMPUESTO_PREDIAL;

			--- calculo del aporte de la recoleccion de basura
			_APORTE_REC_BASURA = 0; -- (_AVALUO_IMPONIBLE * 0.01)/1000;

			--- calculo del aporte de los bomberos
			_APORTE_BOMBEROS = _AVALUO_IMPONIBLE * _COEFICIENTE_BOMBEROS; 
			
            --- RECARGO POR NO EDIFICAR
            /*
            IF(((SELECT db_total_costruccion FROM catastro.t_liquidacion WHERE id_predio = rec.int_id_predio) = 0)
                OR ((SELECT db_total_costruccion FROM catastro.t_liquidacion WHERE id_predio = rec.int_id_predio) IS NULL))THEN
                _LOTE_NO_EDIFICADO = (_IMPUESTO_PREDIAL * 0.01);
            ELSE
                _LOTE_NO_EDIFICADO=0;
            END IF;    
            */
            if exists (select sr_id_piso from catastro.t_piso_2 where int_id_predio = rec.int_id_predio) then
                _LOTE_NO_EDIFICADO = 0;
            else if RAZON_EXCENCION='NO EDIFICADO' then   -----VERIFICAR EXCENCION DE ESTAR EN NO EDIFICADO RECIEN VENDIDO
					_LOTE_NO_EDIFICADO = 0;
				else
                	_LOTE_NO_EDIFICADO = (_AVALUO_IMPONIBLE * _COEFICIENTE_NO_EDIFICADO);
				end if;
            end if;
            
            --VERIFICO PREDIOS MUNICIPALES E INSTITUCIONES PUBLICAS
            IF(RAZON_EXCENCION='PREDIO MUNICIPAL') THEN
                ---- TOTAL TITULO Y TOTAL A PAGAR
                _IMPUESTO_PREDIAL=0;
                _APORTE_REC_BASURA=0;
                _APORTE_BOMBEROS=0;
                _LOTE_NO_EDIFICADO=0;
                _PROCESAMIENTO=0;
                _TOTAL_TITULO=0;     
                _TOTAL_TITULO=_IMPUESTO_PREDIAL+_APORTE_REC_BASURA+_APORTE_BOMBEROS+_LOTE_NO_EDIFICADO+_PROCESAMIENTO; 
            ELSIF(RAZON_EXCENCION='INSTITUCIÓN PUBLICA') THEN
                ---- TOTAL TITULO Y TOTAL A PAGAR
                _IMPUESTO_PREDIAL=0;
                _APORTE_REC_BASURA=0;
                _APORTE_BOMBEROS=0;
                _LOTE_NO_EDIFICADO=0;                                
                _TOTAL_TITULO=_IMPUESTO_PREDIAL+_APORTE_REC_BASURA+_APORTE_BOMBEROS+_LOTE_NO_EDIFICADO+_PROCESAMIENTO;                           
            ELSE
                ---- TOTAL TITULO Y TOTAL A PAGAR
                _TOTAL_TITULO=_IMPUESTO_PREDIAL+_APORTE_REC_BASURA+_APORTE_BOMBEROS+_LOTE_NO_EDIFICADO+_PROCESAMIENTO;                
            END IF;
            
			----------------------------------------------------
			----FIN VALORES CALCULADOS
			----------------------------------------------------				
            --- valores a insertar -----------------------------
            RAISE NOTICE 'VALORES A INSERTAR'; 
            RAISE NOTICE 'Numero de titulo: (%)', cont;   
            RAISE NOTICE 'Secuencia: (%)', TXT_SECUENCIA;
            RAISE NOTICE 'Id_predio: (%)', rec.int_id_predio;
            RAISE NOTICE 'Id propietario: (%)', rec.int_id_titular_dom;
            RAISE NOTICE 'Uso suelo: (%)', rec.db_total_uso_suelo;
            RAISE NOTICE 'Total construccion: (%)', rec.db_total_costruccion;
            RAISE NOTICE 'Avaluo: (%)', rec.db_avaluo;
            RAISE NOTICE 'EWxcenciones especiales: (%)', EXCENCIONES_ESPECIALES;
            RAISE NOTICE 'Razón Excención: (%)', RAZON_EXCENCION;
            RAISE NOTICE 'Avaluo imponible: (%)', _AVALUO_IMPONIBLE;
            RAISE NOTICE '_IMPUESTO_PREDIAL: (%)', _IMPUESTO_PREDIAL;
            RAISE NOTICE '_APORTE_REC_BASURA: (%)', _APORTE_REC_BASURA;
            RAISE NOTICE '_APORTE_BOMBEROS: (%)', _APORTE_BOMBEROS;
            RAISE NOTICE '_LOTE_NO_EDIFICADO: (%)', _LOTE_NO_EDIFICADO;
            RAISE NOTICE '_TOTAL_TITULOl: (%)', _TOTAL_TITULO;
            RAISE NOTICE '_PROCESAMIENTO: (%)', _PROCESAMIENTO;

			insert into catastro.t_titulos_prueba(  int_anio_titulo,
							  int_num_titulo,
							  chv_titulo_numero,
							  ts_fecha_emision,
							  id_predio,
							  id_propietario_index,
							  db_valor_terreno,
							  db_valor_construccion,
							  db_avaluo_comercial,
							  db_excenciones,
							  chv_causa_excencion,
							  db_avaluo_imponible,
							  db_impuesto_predial,
							  db_recolect_basura,
							  db_cuerpo_bomberos,
                              db_recargo, ---LOTE NO EDIFICADO
                              db_total_titulo,
                              db_total_pagar,
							  db_procesamiento)
			values(ANIO_TITULO,
				cont,
				TXT_SECUENCIA,
				now(),
				rec.int_id_predio,
				rec.int_id_titular_dom,				
				rec.db_total_uso_suelo,
				rec.db_total_costruccion,
				rec.db_avaluo,
				EXCENCIONES_ESPECIALES,
				RAZON_EXCENCION,
				_AVALUO_IMPONIBLE,
				_IMPUESTO_PREDIAL,
				_APORTE_REC_BASURA,
				_APORTE_BOMBEROS,
                _LOTE_NO_EDIFICADO,
                _TOTAL_TITULO,
                _TOTAL_TITULO,
                _PROCESAMIENTO);						
			-- return next;			
		end loop;
		
		-----------------------------------------------------------------------------
		----- INSERTO LOS DATOS EN LA TABLA AUDITORIA
		-----------------------------------------------------------------------------
		
		-- insert into auditoria.aud_generacion_titulos(anio,usuario_sistema)
		-- values (ANIO_TITULO,session_usuario);

		msg ='LOS TITULOS SE HAN GENERADO EXITOSAMENTE.';
	end;
	return msg;
end;

$BODY$;

ALTER FUNCTION catastro.f_simular_titulos_anuales(integer)
    OWNER TO postgres;

