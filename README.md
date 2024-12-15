# Proyecto: ExamenFinal

Este proyecto es una aplicación web desarrollada en Spring Boot para la gestión de comandas de un restaurante.

### Funcionalidades
- Listado y filtrado de comandas por RUT de garzón o ID de comanda.
- Se utiliza base de datos denominada "restorantPoblado", en esquema "public".

### Pruebas

Puedes probar la API en los siguientes endpoints:
- Filtrado por RUT: GET /api/inicio?garzonRut=11111111-1
- Filtrado por Comanda: GET /api/inicio?comandaId=4

Y puedes probar la aplicación web en a través de la ruta: 
- Filtrado por RUT: /inicio?garzonRut=11111111-1
- Filtrado por Comanda: /inicio?comandaId=4
