# 1. Instrucciones para ejecutar el proyecto.
Requisitos:
- Instalar o tener instalado JAVA (versión usada: JDK-21: https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
- Configurar o tener configurado JAVA_HOME en las variables de entorno del sistema con la ruta donde se ha instalado el JDK.

Para ejecutar el proyecto se deben seguir los siguientes pasos:

Dentro de la carpeta en la que se encuentra el archivo pom.xml (raíz del proyecto) ejecutar los siguientes comandos:
- `.\mvnw.cmd spring-boot:run` -> Para ejecutar la aplicación.
- Acceder a la url http://localhost:8080/api/requests

# 2. Breve descripción de la arquitectura y decisiones técnicas tomadas.

En la carpeta src encontramos la estructura del proyecto. En esta encontramos las siguientes carpetas:

**/src/main/java/com/swebackend/swebackend**
- **controller/**: En esta carpeta se encuentran las definiciones de los endpoints de la API.
- **dto/**:  En esta carpeta se encuentran los objetos que se utilizan para mover los datos entre el cliente y el servidor sin exponer las entidades de las bases de datos. En este caso solo se encuentra definido el objeto ErrorResponse que establece el formato que deben tener los errores que debe manejar la aplicación para mostrar información controlada al usuario.
- **exception/**: Se encarga de contener los archivos que van a manejar los errores que se produzca. Existe un único fichero llamado GlobalExceptionHandler que se encarga de controlar los errores de conflicto con la lógica de negocio (como puede ser un cambio de estado fuera del flujo definido), ids no localizados en DB y errores de validación entre otros.
- **model/**: Contiene los archivos donde se define la estructura de los objetos que van a ser almacenados en la memoria. En este caso solo tenemos el objeto Request y su enumerado asociado RequestState. 
- **repository/**: En esta carpeta se encuentran los archivos que definen la capa de acceso de datos. En este caso existe un fichero con la interfaz RequestRepository que extiende a JpaRepository permitiéndonos almacenar la información en memoria.
- **service/**: Se encuentran los archivos que definen la lógica del negocio. En el archivo RequestService.java encontramos la definición de la creación, listado, lectura y modificación del estado de solicitudes de préstamo.
- **SwebackendApplication.java**: Es la clase principal del proyecto que permite iniciar el servidor.

Para la definición de clases, se ha hecho uso de las anotaciones de Lombok (@Data) que nos ofrece los getters, setters, toString, constructor por defecto sin la necesidad escribirlos en el fichero quedando así el código más legible.

Se ha mantenido la separación entre Controller, Service y Model para facilitar la manteniblidad y organización del código yy favorecer la posible futura reutilización.

Se ha optado por implementar la gestión de errores aunque no se requiriera para aportar más información al usuario en caso de incumplir una regla de negocio como puede ser no seguir el flujo de cambios de estado.

# 3. Indica qué mejoras o extensiones implementarías con más tiempo (tanto funcionales como técnicas /arquitecturales).

**Algunas mejoras funcionales serían:**
- Añadir validaciones a los campos del modelo Request:
-- Implementación de filtrado de préstamos por diferentes parámetros: Fecha de creación, NIF, Importe y Estado.
-- Implementación de modificación de valores de préstammo siempre y cuando este se encuentre en estado PENDIENTE.
-- Añadir gestión de usuarios y permitir la creación y modificación solo a ciertos usuarios con un rol concreto.
-- Creación de un objeto Empleado al que se le vincule los préstamos de los que se hace cargo.
-- Implementación de filtrado de préstamos por este usuario.

**En cuanto a mejoras técnicas y arquitecturales:**
- Eliminar las anotaciones de la definición de los modelos de dto cambiandolos por record simplificando así el código.
- Añadir una base de datos persistente como PostgresSQL para no perder la información almacenada al acabar con la ejecución del proyecto.
-- Actualmente los campos pueden venir vacíos o con cantidades negativas o NIF inválidos. Se deberían añadir validaciones para mantener la seguridad y veracidad de los préstamos.
-- Implementación de paginación para que la obtención de los préstamos sea escalable y no perjudicar en el rendimiento de la aplicación.