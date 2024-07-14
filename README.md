# Foro Hub

Foro Hub es una aplicación de foro que permite a los usuarios crear, leer, actualizar y eliminar tópicos. Este proyecto utiliza Spring Boot para el backend y proporciona endpoints RESTful para interactuar con los datos.

## Características

- **GET**: Obtener una lista de todos los tópicos o un tópico específico.
- **POST**: Crear un nuevo tópico.
- **PUT**: Actualizar un tópico existente.
- **DELETE**: Eliminar un tópico.

## Requisitos

- Java 17
- Maven 4 o superior
- MySQL opcional
- Insomnia opcional

## Instalación

1. Clona el repositorio:
   
   git clone https://github.com/Guerfer/ForoHub.git

## Configuración del archivo “application.properties” con la base de datos.

spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

## Autenticación 

Una vez clonado en repositorio e instalada y configurada la base de datos, abre la tabla de usuarios y se registra para poder generar el “JSON Web Tokens (JWT)”

## Funcionalidad 
1. Ingresamos en insomnia y lo primero que visualizamos son estas solicitudes de tipo REST. Que tienen como dirección http://localhost:8080/
   
   ![image](https://github.com/user-attachments/assets/51d29cad-258a-4130-9af9-ceb382cc08a5)

2. Para realizar cualquier consulta REST, se requiere un token de autenticación generado con JSON Web Tokens (JWT). Escogemos la opción de “Login de Usuario” la cual lleva como dirección http://localhost:8080/login y pulsamos en botón “Send” y nos generara el token que deberemos copiar para pegar en las consultas.

   ![image](https://github.com/user-attachments/assets/5e87199d-8cfb-4ff2-bb01-f10ac45ce091)

3. Para poder registrar un tópico escogemos la opción de tipo POST “Registrar Tópico” la cual lleva como dirección http://localhost:8080/topicos y escogemos el parámetro “Auth” y la opción “Bearer Token” y pegamos el toquen copiado anteriormente.

   ![image](https://github.com/user-attachments/assets/56150f20-370c-4bf5-9f6b-b308da56f84e)

Ahora pasamos al parámetro “Body” y escogemos el formato JSON y colocamos el valor a registrar como se muestra en la imagen y finalmente pulsamos en botón “Send”.

  ![image](https://github.com/user-attachments/assets/9219d37f-9baa-48e1-a538-a7c9aac4236b)

4. Para poder mostrar todos los tópicos registrados escogemos la opción de tipo GET “Mostrar todos los Tópícos” la cual lleva como dirección http://localhost:8080/topicos y escogemos el parámetro “Auth” y la opción “Bearer Token” y pegamos el toquen copiado anteriormente. Y finalmente pulsamos en botón “Send”.

   ![image](https://github.com/user-attachments/assets/0166cc42-859a-4389-880f-234b1f655d62)

5. Para poder mostrar un tópicos en especifico, escogemos la opción de tipo GET “Mostrar un Tópícos por ID” la cual lleva como dirección http://localhost:8080/topicos/ y escogemos el parámetro “Auth” y la opción “Bearer Token” y pegamos el toquen copiado anteriormente. Antes debemos colocar un ID que deseamos consultar por ejemplo usaremos el 7 pulsamos en botón “Send”.

   ![image](https://github.com/user-attachments/assets/df1cd3f8-f271-4ca8-9c3b-58c43e943b85)

6. Para poder actualizar un tópico escogemos la opción de tipo PUT “Actualizar Tópico” la cual lleva como dirección http://localhost:8080/topicos y escogemos el parámetro “Auth” y la opción “Bearer Token” y pegamos el toquen copiado anteriormente.

   ![image](https://github.com/user-attachments/assets/f59bf046-5528-4c33-b842-e201a17abce1)

Ahora pasamos al parámetro “Body” y escogemos el formato JSON y colocamos el valor a actualizar con su respectivo ID como se muestra en la imagen. 
Como ejemplo usaremos el ID 7 que usamos en buscar un tópico en especifico y cambiaremos el nombre. Y finalmente pulsamos en botón “Send”. 

  ![image](https://github.com/user-attachments/assets/d3f39787-d9f7-448c-93b8-e3185899840a)

Ahora lo volveremos a listar para valisar.

  ![image](https://github.com/user-attachments/assets/8200f146-a3dc-45f6-8b93-172e92771b16)

7. Para poder eliminar un tópico escogemos la opción de tipo DEL “Eliminar Tópico” la cual lleva como dirección http://localhost:8080/topicos/  y escogemos el parámetro “Auth” y la opción “Bearer Token” y pegamos el toquen copiado anteriormente.
Antes debemos colocar un ID que deseamos borrar por ejemplo usaremos el 7 y pulsamos en botón “Send”. 

   ![image](https://github.com/user-attachments/assets/846297fb-41fb-4707-9c2e-3e683a263105)

Y ahora consultaremos ese tópico para validar si fue borrado.

   ![image](https://github.com/user-attachments/assets/26b89fe9-148c-4173-88f7-034edeba3876)

### Contribución
Gracias por considerar contribuir en el proyecto. Aquí hay algunas formas en las que puedes ayudar:
- Clona este repositorio en tu máquina local.
- Crea una nueva rama para tus cambios
- Realiza tus modificaciones y confirma los cambios
- Envía una solicitud de extracción (pull request) desde tu rama a la rama principal.

Espero tus contribuciones. ¡Gracias! 

### Licencia

Este proyecto está bajo la Licencia MIT.




### Fin
















