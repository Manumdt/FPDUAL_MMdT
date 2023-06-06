# README

## Necesitas

* Java 17
* MySQL Workbench 8.0 CE
* Eclipse 2023-03 (4.27.0)
* Maven EMBEDDED 3.8.7/3.8.701.20230209-1606
* SpringDevTools 4
* Proyecto generado con Spring.io con la version 3.10 de Spring y tres dependencias, JPA, WEB y MYSQL.

## Antes de ejecutar

Para importar el proyecto, se selecciona en File -> Import -> Existing Maven Projects.

Ejecutar el script sql encontrado en PokeApi06/src/main/resources/sql para tener la base de datos que usará el proyecto.

Después hay que hacer maven clean, maven install y maven test, para a continuación ejecutar el proyecto con SprinBoot.

Para acceder, en el navegador se pondra "localhost:8080".

Es posible que en algunos equipos sea necesario instalar una extensión de navegador para evitar el CORS, yo personalmente he usado 'Allow-Cors'