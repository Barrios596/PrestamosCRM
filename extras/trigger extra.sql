-- Descripcion
-- El trigger maneja el ingreso del campo fecha_agregado, antes de 
 -- cada 'INSERT' en la tabla cliente

CREATE FUNCTION funcion11 ()
returns trigger AS 
$BODY$
BEGIN
	NEW.fecha_agregado := current_date;
	RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER trigger11
BEFORE INSERT
ON cliente
FOR EACH ROW
EXECUTE PROCEDURE funcion11();