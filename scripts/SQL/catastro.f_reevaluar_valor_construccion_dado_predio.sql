CREATE OR REPLACE FUNCTION catastro.f_reevaluar_valor_construccion_dado_predio(
	predio integer,
	session_usuario integer)
    RETURNS text
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE 
AS $BODY$

declare
      msg text;
      COEF_CIMIENTOS double precision; ---cimientos
      COEF_CADENAS double precision;   ---cadenas -FALTABA creado en t_piso_2
      ANIO_CONSTRUCCION integer;
      COEF_ESTADO double precision;
      COEF_COLUMNAS double precision; ---columnas
      COEF_VIGAS double precision; -- vigas
      COEF_ENTREPISOS double precision;  -- entrepisos
      COEF_PAREDES double precision; -- paredes
      COEF_CUBIERTA double precision; -- cubierta tipo PARA BUSCAR DEL ANIO UTIL
      COEF_ESCALERAS double precision; ---escaleras
      COEF_ACAB_PISOS double precision;  --- pisos tipo
      COEF_ACAB_PUERTAS double precision; ---p_exter_tipo
      COEF_ACAB_PUERTAS_INTERIOR double precision; ---p_inter_tipo PUERTAS INTERIOR creado en t_piso_2
      COEF_ACAB_VENTANAS double precision;  --- ventanas
      COEF_ACAB_VIDRIOS double precision;  --- vidrios creado en t_piso_2
      COEF_ACAB_CUBREVENTANAS double precision; ---p_ventana_tipo puerta venta
      COEF_ACAB_ENLUCIDOS double precision; ---enlucidos creado en t_piso_2
      COEF_ACAB_TUMBADOS double precision;  --- tumbados
      COEF_ACAB_CUBIERTA double precision; --- cubierta_acabados 
      COEF_ACAB_BANIOS double precision; --- piezas_sanitarias
      COEF_ACAB_COCINA double precision; --- cocina
      COEF_ACAB_CLOSETS double precision; --- closets
      COEF_ACAB_REV_INT double precision; ---pintura
      COEF_ACAB_REV_EXT double precision; --- fachada
      COEF_ENERGIA_ELECTRICA_INSTALADA double precision; --- e_electrica creado en t_piso_2
      COEF_SANITARIAS double precision; --- sanitarias creado en t_piso_2
      COEF_ESPECIALES double precision; --- especiales creado en t_piso_2
      COEF_CONTRA_INCENDIO double precision; --- contra incendio creado en t_piso_2
	  AREA double precision;
      FDE double precision :=1; ---REVISAR LUEGO
      TOTAL_1 double precision;  --- SUMATORIA DE COEFICIENTES
      TOTAL_2 double precision;  
      TOTAL_3 double precision :=1; -- REVISAR LUEGO
      ID_BLOQUE INTEGER;
      VIDA_UTIL_COLUMNAS integer;
      VIDA_UTIL_PAREDES integer;
      VIDA_UTIL_CUBIERTA integer;
      DEPRECIACION double precision;
      EDAD integer;
      ANIO_ACTUAL integer;
      _ID_PREDIO INTEGER;
      PORCENTAJE double precision; -- OBTIENE EL PROMEDIO ENTRE ESTADO Y FDE
     REC RECORD;
     
      ---VALORES A INSERTAR EN LA TABLA LIQUIDACION
      
      _TOTAL_CONSTRUCCION double precision;
      _AVALUO double precision;
      _IMPUESTO_PREDIAL double precision;
      _TOTAL_USO_SUELO double precision;     
begin
    begin
	------EN CASO DE QUE YA NO TENGA CONSTRUCCION QUE EVALUAR SE BORRO
	IF EXISTS (select * from catastro.t_piso_2  where ch_estado_logico = 'A' and int_id_predio = predio) THEN
        for REC in(select *
                  from catastro.t_piso_2
                  where ch_estado_logico = 'A'
                    and int_id_predio = predio)
        loop
            --- OBTENGO LOS COEFICIENTES DE LA CONSTRUCCION DE CADA PISO
             SELECT db_valor_coef INTO COEF_ESTADO FROM  catastro.coef_estado_construc WHERE chv_coeficiente = REC.chv_estado_piso;
             SELECT db_valor_coef INTO COEF_CADENAS FROM  catastro.coef_cadenas WHERE chv_coeficiente = REC.chv_estruct_cadenas;
             SELECT db_valor_coef INTO COEF_CIMIENTOS FROM  catastro.coef_cimientos WHERE chv_coeficiente = REC.chv_estruct_cimientos;
             SELECT db_valor_coef INTO COEF_COLUMNAS FROM  catastro.coef_columnas WHERE chv_coeficiente = REC.chv_estruct_columnas;
             SELECT db_valor_coef INTO COEF_VIGAS FROM  catastro.coef_vigas WHERE chv_coeficiente = REC.chv_estruct_vigas;
             SELECT db_valor_coef INTO COEF_ENTREPISOS FROM  catastro.coef_entrepisos WHERE chv_coeficiente = REC.chv_estruct_entrepisos;
             SELECT db_valor_coef INTO COEF_PAREDES FROM  catastro.coef_paredes WHERE chv_coeficiente = REC.chv_estruct_paredes;
             SELECT db_valor_coef INTO COEF_CUBIERTA FROM  catastro.coef_cubierta WHERE chv_coeficiente = REC.chv_estruct_cubierta;
             SELECT db_valor_coef INTO COEF_ESCALERAS FROM  catastro.coef_escaleras WHERE chv_coeficiente = REC.chv_estruct_escaleras;
             SELECT db_valor_coef INTO COEF_ACAB_PISOS FROM  catastro.coef_acab_pisos WHERE chv_coeficiente = REC.chv_acab_pisos;
             SELECT db_valor_coef INTO COEF_ACAB_PUERTAS FROM  catastro.coef_acab_puerta WHERE chv_coeficiente = REC.chv_acab_puertas;    
             SELECT db_valor_coef INTO COEF_ACAB_PUERTAS_INTERIOR FROM  catastro.coef_acab_puerta_interior WHERE chv_coeficiente = REC.chv_acab_puertas_interior;   
             SELECT db_valor_coef INTO COEF_ACAB_VENTANAS FROM  catastro.coef_acab_ventana WHERE chv_coeficiente = REC.chv_acab_ventanas;   
             SELECT db_valor_coef INTO COEF_ACAB_VIDRIOS FROM  catastro.coef_acab_vidrios WHERE chv_coeficiente = REC.chv_acab_vidrios;   
             SELECT db_valor_coef INTO COEF_ACAB_CUBREVENTANAS FROM  catastro.coef_acab_cubrevent WHERE chv_coeficiente = REC.chv_acab_cubrevent;   
             SELECT db_valor_coef INTO COEF_ACAB_ENLUCIDOS FROM  catastro.coef_acab_enlucidos WHERE chv_coeficiente = REC.chv_acab_enlucidos;   
             SELECT db_valor_coef INTO COEF_ACAB_TUMBADOS FROM  catastro.coef_acab_tumbado WHERE chv_coeficiente = REC.chv_acab_tumbados;
             SELECT db_valor_coef INTO COEF_ACAB_CUBIERTA FROM  catastro.coef_acab_cubierta WHERE chv_coeficiente = REC.chv_acab_cubierta;
             SELECT db_valor_coef INTO COEF_ACAB_BANIOS FROM  catastro.coef_acab_banio WHERE chv_coeficiente = REC.chv_acab_banios;
             SELECT db_valor_coef INTO COEF_ACAB_COCINA FROM  catastro.coef_acab_cocina WHERE chv_coeficiente = REC.chv_acab_cocina;
             SELECT db_valor_coef INTO COEF_ACAB_CLOSETS FROM  catastro.coef_acab_closet WHERE chv_coeficiente = REC.chv_acab_closets;
             SELECT db_valor_coef INTO COEF_ACAB_REV_INT FROM  catastro.coef_acab_rev_int WHERE chv_coeficiente = REC.chv_acab_rev_interior;
             SELECT db_valor_coef INTO COEF_ACAB_REV_EXT FROM  catastro.coef_acab_rev_ext WHERE chv_coeficiente = REC.chv_acab_rev_ext;
             SELECT db_valor_coef INTO COEF_ENERGIA_ELECTRICA_INSTALADA FROM  catastro.coef_energia_electrica_instalada WHERE chv_coeficiente = REC.chv_energia_electrica;
             SELECT db_valor_coef INTO COEF_SANITARIAS FROM  catastro.coef_sanitarias WHERE chv_coeficiente = REC.chv_sanitarias;
             SELECT db_valor_coef INTO COEF_ESPECIALES FROM  catastro.coef_especiales WHERE chv_coeficiente = REC.chv_especiales;
             SELECT db_valor_coef INTO COEF_CONTRA_INCENDIO FROM  catastro.coef_contra_incendio WHERE chv_coeficiente = REC.chv_contra_incendio;
             --SELECT db_fde INTO FDE FROM  catastro.t_detalle_val_constr WHERE int_id_piso= REC.sr_id_piso;
             ANIO_CONSTRUCCION = REC.int_anio_construc; 

             --OBTENER DATOS VIDA COLUMNA Y CALCULAR FDE
            SELECT int_vida_util INTO VIDA_UTIL_COLUMNAS FROM  catastro.coef_vida_util WHERE chv_uso = 'COLUMNAS' AND chv_material = REC.chv_estruct_columnas;
            SELECT int_vida_util INTO VIDA_UTIL_PAREDES FROM  catastro.coef_vida_util WHERE chv_uso = 'PAREDES' AND chv_material = REC.chv_estruct_paredes;
            SELECT int_vida_util INTO VIDA_UTIL_CUBIERTA FROM  catastro.coef_vida_util WHERE chv_uso = 'CUBIERTA_ACABADO' AND chv_material = REC.chv_acab_cubierta;
            DEPRECIACION = (VIDA_UTIL_COLUMNAS*0.6) + (VIDA_UTIL_PAREDES*0.35) + (VIDA_UTIL_CUBIERTA*0.05);
            IF DEPRECIACION <= 0 then
				DEPRECIACION=2;
			END IF;
            ANIO_ACTUAL = extract(YEAR from  NOW());
            EDAD = ANIO_ACTUAL - ANIO_CONSTRUCCION-1;
			IF EDAD < 0 then
				EDAD=1;
			END IF;
            FDE = power((1-(1/DEPRECIACION)),EDAD);
			IF FDE < 0 then
				FDE=0.36;
			END IF;
             
             ---ID DEL BLOQUE Y DEL PISO
             SELECT int_id_bloque INTO ID_BLOQUE FROM  catastro.t_piso_2 WHERE sr_id_piso = REC.sr_id_piso;
             SELECT int_id_predio INTO _ID_PREDIO FROM  catastro.t_piso_2 WHERE sr_id_piso = REC.sr_id_piso;
             
             ---- SUMATORIAS
             --- TOTAL DE COEFICIENTES 
		    TOTAL_1=(COEF_CIMIENTOS+COEF_COLUMNAS+COEF_VIGAS+COEF_ENTREPISOS+COEF_PAREDES+COEF_CUBIERTA+COEF_ESCALERAS+
             COEF_ACAB_PISOS+COEF_ACAB_PUERTAS+COEF_ACAB_VENTANAS+COEF_ACAB_CUBREVENTANAS+COEF_ACAB_TUMBADOS+COEF_ACAB_CUBIERTA+
             COEF_ACAB_BANIOS+COEF_ACAB_COCINA+COEF_ACAB_CLOSETS+COEF_ACAB_REV_INT+COEF_ACAB_REV_EXT+COEF_CADENAS+COEF_ACAB_PUERTAS_INTERIOR+
             COEF_ACAB_VIDRIOS+COEF_ACAB_ENLUCIDOS+COEF_ENERGIA_ELECTRICA_INSTALADA+COEF_SANITARIAS+COEF_ESPECIALES+COEF_CONTRA_INCENDIO);
        	PORCENTAJE=(COEF_ESTADO+FDE)/2;
            ---- SUMATORIA DEL TOTAL DE LOS COEFICIENTES POR EL AREA 
			AREA = REC.db_area_construcc;
        	TOTAL_2=(AREA*TOTAL_1*PORCENTAJE);   
            
            ------- ACTUALIZACION DE LOS DATOS DE LA TABLA
            UPDATE catastro.t_detalle_val_constr
            SET int_anio_construc  = ANIO_CONSTRUCCION,		 
                db_estado_piso  = COEF_ESTADO,
                db_estruct_cimientos  = COEF_CIMIENTOS,
				db_estruct_cadenas = COEF_CADENAS,
                db_estruct_columnas  = COEF_COLUMNAS,
                db_estruct_vigas  = COEF_VIGAS,
                db_estruct_entrepisos  = COEF_ENTREPISOS,
                db_estruct_paredes  = COEF_PAREDES,
                db_estruct_cubierta  = COEF_CUBIERTA,
                db_estruct_escaleras  = COEF_ESCALERAS,
                db_acab_pisos  = COEF_ACAB_PISOS,
                db_acab_puertas  = COEF_ACAB_PUERTAS,
				        db_acab_puertas_interior = COEF_ACAB_PUERTAS_INTERIOR,
                db_acab_ventanas  = COEF_ACAB_VENTANAS,
                db_acab_cubrevent  = COEF_ACAB_CUBREVENTANAS,
				        db_acab_vidrios = COEF_ACAB_VIDRIOS,
				        db_acab_enlucidos = COEF_ACAB_ENLUCIDOS,
                db_acab_tumbados  = COEF_ACAB_TUMBADOS,
                db_acab_cubierta  = COEF_ACAB_CUBIERTA,
                db_acab_banios  = COEF_ACAB_BANIOS,
                db_acab_cocina  = COEF_ACAB_COCINA,
                db_acab_closets  = COEF_ACAB_CLOSETS,
                db_acab_rev_interior  = COEF_ACAB_REV_INT,
                db_acab_rev_ext  = COEF_ACAB_REV_EXT,		
        				db_acab_energia_electrica = COEF_ENERGIA_ELECTRICA_INSTALADA,
        				db_acab_sanitarias = COEF_SANITARIAS,
        				db_acab_especiales = COEF_ESPECIALES,
        				db_acab_contra_incendio = COEF_CONTRA_INCENDIO,
                db_fde = FDE,
                db_total_1  = TOTAL_1,
                db_total_2  = TOTAL_2,
                int_id_predio = REC.int_id_predio,
				       db_area_construcc = AREA,
                ts_fecha_actualizacion = now()	  
            WHERE int_id_piso = REC.sr_id_piso
				and ch_estado_logico='A';       
            
            ----ACTUALIZACIÓN DE LOS DATOS DE LA TABLA LIQUIDACIÓN
            select COALESCE (sum(db_total_2),0) into _TOTAL_CONSTRUCCION FROM  catastro.t_detalle_val_constr where int_id_predio = REC.int_id_predio and ch_estado_logico='A'; 

            select db_total_uso_suelo into _TOTAL_USO_SUELO FROM  catastro.t_liquidacion where id_predio = REC.int_id_predio; 
            
            _AVALUO = _TOTAL_CONSTRUCCION + _TOTAL_USO_SUELO;

            _IMPUESTO_PREDIAL = (_AVALUO*1.25)/1000;

            update catastro.t_liquidacion
            set db_total_costruccion = _TOTAL_CONSTRUCCION, 
                db_impuesto_predial = _IMPUESTO_PREDIAL,
                db_avaluo = _AVALUO,
                ts_fecha_actualizacion = now(),
                id_session_usuario = session_usuario
            where id_predio = REC.int_id_predio;             
        end loop;
		
		ELSE
			select COALESCE (sum(db_total_2),0) into _TOTAL_CONSTRUCCION FROM  catastro.t_detalle_val_constr where int_id_predio=predio and ch_estado_logico='A'; 

            select db_total_uso_suelo into _TOTAL_USO_SUELO FROM  catastro.t_liquidacion where id_predio = predio; 
            
            _AVALUO = _TOTAL_CONSTRUCCION + _TOTAL_USO_SUELO;

            _IMPUESTO_PREDIAL = (_AVALUO*1.25)/1000;

            update catastro.t_liquidacion
            set db_total_costruccion = _TOTAL_CONSTRUCCION, 
                db_impuesto_predial = _IMPUESTO_PREDIAL,
                db_avaluo = _AVALUO,
                ts_fecha_actualizacion = now(),
                id_session_usuario = session_usuario
            where id_predio = predio;             
		END IF;
		
        msg = 'Reevaluación finalizada';
    end;
    return msg;
end;

$BODY$;