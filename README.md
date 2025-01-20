
# Proyecto Bancario: Gestión de Clientes, Cuentas y Movimientos
**Descripción General**

Este proyecto es un sistema que permite realizar operaciones de clientes, cuentas y movimientos bancarios mediante el uso de las siguientes tecnologias:
- Java Spring Boot
- Angular 18
- Postgre SQL
- Docker


Este repositorio contiene un sistema desarrollado para la gestión de clientes, cuentas bancarias y movimientos financieros, simulando un módulo de ahorros.
## Manual de usuario 
Se publica el manual de usuario del presente sistema:
[Ver Manual](Documentacion/MANUAL%20SISTEMA%20PICHINCHA.pdf)

## Archivo Postman
Se adjunta postman file para validacion de apis 
[Ver Archivo](Postman/PICHINCHA.postman_collection.json)
## Características
- **Clientes:**
  - Consulta, registro, modificación y eliminación de clientes.
  - Búsqueda por nombre con sensibilidad a caracteres (ignora mayúsculas).
  - Paginación para manejo eficiente de registros.

- **Cuentas:**
  - Búsqueda de clientes por identificador o cédula.
  - Detalles de cuentas asociadas al cliente (número, tipo, saldo inicial, estado).
  - Creación de cuentas.
  - Cierre (desactivación o cancelación) de cuentas.

- **Movimientos:**
  - Realización de depósitos, retiros y transferencias.
  - Registro automático de notas de débito y crédito en transferencias.
  - Actualización de saldos en tiempo real.
  - Generación de reportes de movimientos.

- **Reportes:**
  - Generación de reportes de cuentas en un rango de fechas.
  - Presentación de datos en tablas con paginación.

## Tecnologías Utilizadas
El proyecto se desarrolla utilizando las siguientes tecnologías:
- **Backend:** [Especificar tecnología/framework utilizado].
- **Frontend:** [Especificar tecnología/framework utilizado].
- **Base de Datos:** [Indicar el SGBD utilizado, por ejemplo, MySQL, PostgreSQL, etc.].

## Estructura del Proyecto
```
|-- /clientes
    |-- Consultas
    |-- Registro
    |-- Modificación y eliminación
|-- /cuentas
    |-- Detalles de cuentas
    |-- Creación
    |-- Cierre
|-- /movimientos
    |-- Depósitos
    |-- Retiros
    |-- Transferencias
|-- /reportes
    |-- Generación de reportes
```

## Pre requisitos
Se requiere contar con un ordenador que cuenta con:
- Docker 
- Docker-compose

## Instalación
1. Clona este repositorio:
   ```bash
   git clone https://github.com/fabianRBN/Pichincha.git
   ```
2. Acceder al directorio del proyecto
   ```
   cd Pichincha
  ```
3. Instalación y configuración del proyecto:
   ```bash
   # Docker Compose
   # En el directorio raiz del proyecto ejecutamos el comando
    
     docker-compose up 

   ```

## Uso
- Accede al sistema mediante el navegador en la URL http://localhost:8080/.
- Navega por los módulos para gestionar clientes, cuentas, movimientos y generar reportes.




---

**Contacto:** 
Para dudas o soporte, contacta a fabianRBN_95@hotmail.com.

