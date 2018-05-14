-- Descripcion:
-- El trigger aplica la funcion initcap() a nombre
-- del cliente, esto antes de cada 'INSERT' en la tabla cliente.

CREATE FUNCTION funcion1 ()
returns trigger AS 
$BODY$
BEGIN
	NEW.nombre := initcap(NEW.nombre);
	RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger1
BEFORE INSERT
ON cliente
FOR EACH ROW
EXECUTE PROCEDURE funcion1();

-- Descripcion:
-- El trigger aplica la funcion initcap() al apellido
-- del cliente, esto antes de cada 'INSERT' en la tabla cliente.

CREATE FUNCTION funcion2 ()
returns trigger AS 
$BODY$
BEGIN
	NEW.apellido := initcap(NEW.apellido);
	RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger2
BEFORE INSERT
ON cliente
FOR EACH ROW
EXECUTE PROCEDURE funcion2();


-- Descripcion:
-- El trigger aplica la funcion initcap() a el departamento
-- del cliente, esto antes de cada 'INSERT' en la tabla cliente.

CREATE FUNCTION funcion3()
returns trigger AS 
$BODY$
BEGIN
	NEW.direccion := initcap(NEW.direccion);
	RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger3
BEFORE INSERT
ON cliente
FOR EACH ROW
EXECUTE PROCEDURE funcion3();

-- Descripcion : 
-- El trigger verifica que el correo del cliente tenga al menos el simbolo '@'
-- para considerarlo valido, esto antes de cada 'INSERT' en la tabla cliente.

CREATE FUNCTION funcion4()
returns trigger AS
$BODY$
BEGIN
	IF (position ('@' in NEW.correo ) = 0)
		THEN
		NEW.correo := NULL;
	ELSE
		NEW.correo := NEW.correo;
	END IF;
	RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger4
BEFORE INSERT
ON cliente
FOR EACH ROW
EXECUTE PROCEDURE funcion4();

-- Descripcion :
-- El trigger verifica segun la fecha de nacimiento si los usuarios tienen
-- edad (18) minima para ser ingresados, esto antes de cada 'INSERT' en la tabla cliente. 

CREATE FUNCTION funcion5()
returns trigger AS
$BODY$
DECLARE
	edad Integer;
BEGIN
	edad := extract ( year FROM age (NEW.fecha_nacimiento));
	IF (edad >= 18 )
		THEN
		new.fecha_nacimiento := NEW.fecha_nacimiento;
	ELSE
		RAISE EXCEPTION 'La fecha de nacimiento no es valida--> %', NEW.fecha_nacimiento;
	END IF;
	RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger5
BEFORE INSERT
ON cliente
FOR EACH ROW
EXECUTE PROCEDURE funcion5();

-- Descripcion:
	-- El trigger verifica si al ingresar un cliente su username_twitter contiene '@'
	-- Si este contiene, se elimina del string, esto antes de cada 'INSERT' en la tabla cliente. 

CREATE FUNCTION funcion6()
returns trigger AS
$BODY$
BEGIN
	IF (position ('@' in NEW.usarname_twitter ) = 1)
		THEN
		NEW.usarname_twitter := substring(NEW.usarname_twitter from 2 for char_length(NEW.usarname_twitter)); 
	ELSE
	NEW.usarname_twitter = NEW.usarname_twitter;
	END IF;
	RETURN new;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger6
BEFORE INSERT
ON cliente
FOR EACH ROW
EXECUTE PROCEDURE funcion6();

-- Descripcion:
	-- El trigger maneja la FK: id_nacionalidad en la tabla cliente, escribiendo 
	-- 'null' cuando se elimina el registro al que hacia referencia. 

CREATE FUNCTION funcion7()
returns trigger AS 
$BODY$
BEGIN
	UPDATE cliente
	SET id_nacionalidad = NULL
	WHERE id_nacionalidad = old.id_nacionalidad;
return old;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger7
  BEFORE DELETE
  ON nacionalidad
  FOR EACH ROW
  EXECUTE PROCEDURE funcion7();

-- Descripcion:
	-- El trigger maneja la FK: id_documento en la tabla cliente, escribiendo 
	-- 'null' cuando se elimina el registro al que hacia referencia.

CREATE FUNCTION funcion8()
returns trigger AS 
$BODY$
BEGIN
	UPDATE cliente
	SET id_documento = NULL
	WHERE id_documento = old.id_documento;
return old;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger8
  BEFORE DELETE
  ON documento
  FOR EACH ROW
  EXECUTE PROCEDURE funcion8();

-- Descripcion:
	-- El trigger maneja la FK: id_empleado en la tabla cliente, escribiendo 
	-- 'null' cuando se elimina el registro al que hacia referencia. 

 CREATE FUNCTION funcion9()
returns trigger AS 
$BODY$
BEGIN
	UPDATE cliente
	SET id_empleado = NULL
	WHERE id_empleado = old.id_empleado;
return old;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger9
  BEFORE DELETE
  ON empleado
  FOR EACH ROW
  EXECUTE PROCEDURE funcion9();

-- Descripcion:
	-- El trigger maneja la FK: id_estadoc en la tabla cliente, escribiendo 
	-- 'null' cuando se elimina el registro al que hacia referencia. 

CREATE FUNCTION funcion10()
returns trigger AS 
$BODY$
BEGIN
	UPDATE cliente
	SET id_estadoc = NULL
	WHERE id_estadoc = old.id_estadoc;
return old;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger10
  BEFORE DELETE
  ON estadoc
  FOR EACH ROW
  EXECUTE PROCEDURE funcion10();

