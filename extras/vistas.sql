-- Descripcion: 
	-- Detalle: Mostrar el promedio del monto maximo de todos los clientes 
		--segun el departamento de cada una de las nacionalidades.

	-- Resumen: Mostrar el promedio del monto maximo de todos los clientes 
		--segun las nacionalidades.  

CREATE VIEW detalle1 AS
SELECT nacionalidad, direccion, avg(monto_maximo) as promedio_monto_maximo
FROM cliente cl INNER JOIN nacionalidad n ON (cl.id_nacionalidad = n.id_nacionalidad)
GROUP BY nacionalidad, direccion

CREATE VIEW resumen1 AS
SELECT nacionalidad, avg(monto_maximo) as promedio_monto_maximo
FROM cliente cl INNER JOIN nacionalidad n ON (cl.id_nacionalidad = n.id_nacionalidad)
GROUP BY nacionalidad

-- Descripcion: 
	-- Detalle: Mostrar la cantidad de clientes que tienen cuenta de Twitter 
		-- segun la edad en cada una de las nacionalidades.

	-- Resumen: Mostrar la cantidad de clientes que tienen cuenta de Twitter
		-- segun la edad.

CREATE VIEW detalle2 AS
SELECT n.nacionalidad, res.edad, count(*) as cant_cli_with
FROM (
	SELECT *, extract ( year FROM age (fecha_nacimiento)) as edad
	FROM cliente
	WHERE usarname_twitter is not NULL
	) res INNER JOIN nacionalidad n ON (n.id_nacionalidad = res.id_nacionalidad)
GROUP BY nacionalidad, edad

CREATE VIEW resumen2 AS 
SELECT res.edad, count(*) as cant_cli_with
FROM (
	SELECT *, extract ( year FROM age (fecha_nacimiento)) as edad
	FROM cliente
	WHERE usarname_twitter is not NULL
	) res INNER JOIN nacionalidad n ON (n.id_nacionalidad = res.id_nacionalidad )
GROUP BY edad


-- Descripcion
	-- Detalle: Mostrar la cantidad de prestamos realizados mayores o iguales a 10000 
		-- segun el tipo de estado civil de la persona en cada una de las nacionalidades.

	-- Resumen: Mostrar la cantidad de prestamos realizados arriba de 10000.

CREATE VIEW detalle3 AS 
SELECT nacionalidad, estado, sum(cant_prestamos) as prestamos
FROM (cliente cl INNER JOIN estadoc ec ON (cl.id_estadoc = ec.id_estadoc)) 
	INNER JOIN nacionalidad n ON (n.id_nacionalidad = cl.id_nacionalidad)
WHERE monto_maximo >= 10000 
GROUP BY estado, nacionalidad

CREATE VIEW resumen3 AS
SELECT sum(cant_prestamos) as prestamos
FROM cliente
WHERE monto_maximo >= 10000

-- Descripcion
	-- Detalle: Mostrar la cantidad de usuarios nuevos en el sistema
		-- segun la nacionalidad y el mes que se registraron. 

	-- Resumen: Mostrar la cantidad de usuarios nuevos en el sistema
		-- segun el mes. 

CREATE VIEW detalle4 AS
SELECT nacionalidad, direccion, mes, count (*)
FROM(
	(SELECT *, extract (month FROM fecha_agregado) as mes 
	FROM cliente cl) res INNER JOIN nacionalidad n 
	ON (n.id_nacionalidad = res.id_nacionalidad)
	)
WHERE (extract (year FROM (res.fecha_agregado))) = extract (year FROM (current_date))
	GROUP BY nacionalidad, direccion, mes 

CREATE VIEW resumen4 AS
SELECT  mes, count (*)
FROM(
	(SELECT *, extract (month FROM fecha_agregado) as mes 
	FROM cliente cl) 
	) res
WHERE (extract (year FROM (res.fecha_agregado))) = extract (year FROM (current_date))
	GROUP BY mes

-- Descripccion
	-- Detalle: La cantidad de las clientes atendidas por cada uno de los empleados
		-- segun todos los departamentos de la nacionalidad = Guatemala.

	-- Resumen: La cantidad de clientes segun la nacionalidad

CREATE VIEW detalle5 AS
SELECT direccion, empl_nombre, count(*) as cant_cli
FROM (
	SELECT *, concat(em.nombre,' ', em.apellido) as empl_nombre
	FROM (cliente cl INNER JOIN empleado em ON (cl.id_empleado = em.id_empleado)
	) INNER JOIN nacionalidad na ON (cl.id_nacionalidad = na.id_nacionalidad)
	WHERE na.nacionalidad = 'Guatemala'
	) res 
GROUP BY direccion, empl_nombre

CREATE VIEW resumen5 AS
SELECT nacionalidad, count(*) as cant_cli
FROM cliente cl INNER JOIN nacionalidad na 
	ON (cl.id_nacionalidad = na.id_nacionalidad)
GROUP BY nacionalidad