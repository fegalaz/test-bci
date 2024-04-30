
# Test Muruna
Proyecto el cual expone una API RESTful de creación de usuario , todos los endpoints deben aceptar y retornar solo JSON.

## Tecnologías ocupadas en el desarrollo:

* Spring Framework
* Spring WEB
* Hibernate + JPA
* Lombok
* Maven
* H2 (database)
* Java 17
* JWT como token
* Mockito
* Swagger

## JSON de entrada para la creacion de usuarios

```
{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2024",
"phones": [
{
"number": "1234567",
"cityCode": "1",
"countryCode": "57"
}
] }
```

## DataBase

La base de datos se crea al momento de correr la aplicacion , la estructura tambien se crea al momento de correr la aplicacion , la estructura la cual va a ser utilizada por la api de obtencion de lista de precios es la siguiente:

`DROP TABLE IF EXISTS users;

`DROP TABLE IF EXISTS users;`

`DROP TABLE IF EXISTS phones;`

`DROP TABLE IF EXISTS users_phones;`



## Despliegue

* Desde IDE
  Para poder desplegar el proyecto a traves del IDE , si ya tenemos importado el proyecto solo debemos hacer click en *run*

## APIs expuestas por el microservicios

| Type Method | Method                | URL                                       |
|-------------|-----------------------|-------------------------------------------|
| POST        | create()              | localhost:8080/api/users/create           |

## CURL

```curl --location 'localhost:8080/api/users/create' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2024",
"phones": [
{
"number": "1234567",
"cityCode": "1",
"countryCode": "57"
}
] }'
```

*Autor*
***Felipe Galaz***