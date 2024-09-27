# Taller JAVA 21 & SpringBoot 3

Proyecto de aprendizaje de nuevas features de Java.

## Description

Emular el Onboarding de un banco digital.

## Getting Started

### Dependencies

* Java JDK 21
* SpringBoot 3.2.5
* Apache Maven 3.9.3

### Installing and Executing program

* Requires Docker to run.
* Clone project to local repository and open project in IDE (recommended: IntelliJ Idea Community 2024).
* Open IDE Terminal and execute the following command to build application on Docker

```
docker-compose up -d --build  
```
* To shut down application in containers, execute

```
docker-compose down  
```
* If needed in case of errors, clean Docker cache with
```
.\CleanupDocker.sh   
```
* Try the Application! With Postman, send to http://localhost:8090/api/v1/usuarios/crearUsuario with POST Method the following JSON. You can Check in Docker Container terminals the logs and messaging.

```JSON
{
    "nombre": "Pablo",
    "apellido": "Marmol",
    "dni": "12345672",
    "tipoId": 1,
    "domicilio": {
        "calle": "Av Roca",
        "numero": "742",
        "provincia": "Buenos Aires",
        "localidad": "Piedra Buena"
    },
    "sueldoBruto": 700000,
    "tipoUsuario": {
        "idtipo_usuario": 1,
        "descripcion": "Cliente"
    }
}  
```


## Authors

Contributors names and contact info

* Nicolas A. Gonzalez (nicolas.a.gonzalez@ar.ey.com)

## Version History

* 0.1
    * Initial Release