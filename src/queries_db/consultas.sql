--Se requiere listar las mesas disponibles que se encuentran en el momento de la consulta,
--mostrar las columnas correspondientes al número de la mesa y la cantidad de personas que permite dicha mesa.
SELECT numero_mesa, cantidad_persona
FROM mesas
WHERE disponible = true;

--Generar un reporte con cada comanda, considerando el total a pagar por cada comanda existente
--en la base de datos ordenadas de la que tiene más consumo a la que tiene menos consumo.
SELECT c.id_comanda AS comanda, COALESCE(SUM(dc.cantidad_producto * dc.precio_unitario), 0) AS total_pagar
FROM comandas c
LEFT JOIN detalles_comandas dc ON c.id_comanda = dc.id_comanda
GROUP BY c.id_comanda
ORDER BY total_pagar DESC;

--Generar un reporte que permita consultar toda la información de una comanda, mostrar por pantalla la mesa
--a la cual se encuentra asociada y el garzón que gestionó dicho pedido, se debe mostrar rut,
--nombre y apellido del garzón, fecha de emisión y la propina sugerida de la comanda y el número de la mesa.
SELECT
    c.id_comanda AS comanda,
	m.numero_mesa,
	g.rut AS rut_garzon,
    g.nombre AS nombre_garzon,
    g.apellido AS apellido_garzon,
    c.fecha_emision,
    c.propina_sugerida
FROM comandas c
JOIN garzones g ON c.id_garzon = g.id_garzon
JOIN mesas m ON c.id_mesa = m.id_mesa;