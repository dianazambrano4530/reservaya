ReservaYa – Sistema de Reservas Hoteleras
Aplicación web desarrollada en Java Spring Boot y Thymeleaf que permite consultar la disponibilidad de habitaciones, ver detalles e imágenes, y realizar reservas en el Hotel El Bosque de manera fácil, intuitiva y segura.

Tabla de Contenidos

•	Descripción
•	Características
•	Tecnologías
•	Instalación y Ejecución
•	Estructura del Proyecto
•	Funcionalidades Implementadas
•	Créditos

Descripción

ReservaYa es un prototipo funcional de sistema de reservas hoteleras orientado a la gestión de habitaciones y reservas para el Hotel El Bosque. Permite a los usuarios consultar disponibilidad, ver imágenes y detalles de las habitaciones, reservar y recibir confirmación inmediata, todo desde una interfaz responsiva y moderna.

Características

•	Consulta de disponibilidad de habitaciones según fecha y cantidad de huéspedes.
•	Visualización de detalles e imágenes de cada tipo de habitación.
•	Proceso de reserva con formulario y confirmación.
•	Almacenamiento de reservas y datos de clientes.
•	Interfaz amigable y diseño responsivo.
•	Imagen de fondo personalizada en todas las vistas principales.
•	Control de versiones y documentación completa.
Tecnologías
•	Backend: Java 17+, Spring Boot
•	Frontend: Thymeleaf, HTML5, CSS3, Bootstrap 4
•	Base de datos: H2 (en memoria, para pruebas y prototipo)
•	Gestión de dependencias: Maven
•	Control de versiones: Git, GitHub
Instalación y Ejecución

1.	Clona el repositorio:

bash
git clone https://github.com/dianazambrano4530/prototipo-reservaya.git

cd prototipo-reservaya

3.	Compila y ejecuta la aplicación:

bash
mvn clean package
mvn spring-boot:run

5.	Abre tu navegador en:
text

http://localhost:8080
Estructura del Proyecto
text
prototipo-reservaya/
│
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/hotelbosque/reservaya/
│   │   │   ├── controllers/
│   │   │   ├── models/
│   │   │   ├── repositories/
│   │   │   ├── services/
│   │   │   ├── enums/
│   │   │   └── ReservayaApplication.java
│   │   └── resources/
│   │       ├── static/img/
│   │       ├── static/css/
│   │       ├── templates/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
│       └── java/com/hotelbosque/reservaya/
└── .gitignore
Funcionalidades Implementadas
•	Formulario de consulta de disponibilidad de habitaciones.
•	Listado de habitaciones disponibles con imagen, tipo, descripción, capacidad y precio.
•	Visualización de la imagen de cada habitación junto a la información.
•	Formulario de reserva con validación de datos del cliente.
•	Confirmación de la reserva con detalles.
•	Diseño responsivo y fondo personalizado en todas las pantallas.
•	Carga automática de datos y rutas de imágenes desde la base de datos.
•	Control de versiones con Git y repositorio en GitHub.
•	Documentación técnica y video demostrativo del funcionamiento.
Créditos
Desarrollado por Diana Marcela Zambrano Méndez
Corporación Universitaria Iberoamericana
Curso: Ingeniería de Software – Proyecto de Software
Docente: Tatiana Cabrera
¡Gracias por usar ReservaYa!

