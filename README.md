README.md

# Proyecto Maven echo con Spring.

# Idea de la App: 

Una demo de Red Social al estilo "Forum" donde un usuario (con username, pass e imgURL) puede crear posts.

Al Crear posts le invierte Titulo y Contenido. Al crearse recibe un primer comentario con el contenido dado.

Desde la pagina se ve una lista de los posts con imagen, titulo, fecha y primer comentario. 

Cualquier usuario logeado puede comentar en los posts.


# Especificaciones: use mysql para la BBDD, por ende esta configurado de esta manera

spring.datasource.url = jdbc:mysql://localhost:3307/redSocial?useSSL=false

spring.datasource.username = root

spring.datasource.password = 1234

# Para empezar el proyecto, correr PostsApplicatin.java en un interprete de Java y abrir un navegador web en "localhost:8080"

# Creado por Esteban Bedecarats en el transcurso de un curso de Java.