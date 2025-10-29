# 📚 DataSoft - REST API con Spring Boot

API REST desarrollada con Java Spring Boot para prueba dataSoft

## 🚀 Configuración Rápida


### Opción 2: Configuración Manual
```bash
# 1. Levantar contenedores
docker-compose up -d

# 3. Ejecutar la aplicación Spring Boot
cd dataSoft
mvn spring-boot:run
```

## 🗄️ Base de Datos

### PostgreSQL
- **Host:** localhost
- **Puerto:** 5432
- **Base de datos:** pruebadatasoft
- **Usuario:** postgres
- **Contraseña:** postgres

### pgAdmin
- **URL:** http://localhost:8080
- **Email:** admin@admin.com
- **Contraseña:** admin

## 📋 Estructura de Tablas

### Tabla `generes`
- `id` UUID - Clave primaria (auto-generado)
- `name` VARCHAR(30) - Nombre del género

### Tabla `users`
- `id` UUID - Clave primaria (auto-generado)
- `full_name` VARCHAR(30) - Nombre completo
- `username` VARCHAR(20) - Usuario único
- `passwd` VARCHAR(100) - Contraseña encriptada
- `state` VARCHAR(3) - Estado (ACT/INA)

### Tabla `books`
- `id` UUID - Clave primaria (auto-generado)
- `name` VARCHAR(50) - Nombre del libro
- `summary` VARCHAR(500) - Resumen
- `price` NUMERIC(6,2) - Precio
- `state` VARCHAR(10) - Estado (AVAILABLE/SOLD)
- `image` VARCHAR(500) - Ruta de imagen
- `gen_id` UUID - FK a generes
- `usr_id` UUID - FK a users

## 🔗 Endpoints de la API

### Autenticación
```http
POST /api/auth/signup
Content-Type: application/json

{
  "fullName": "Usuario Nuevo",
  "username": "nuevousuario",
  "password": "password123"
}
```

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "jperez",
  "password": "password123"
}
```

### Géneros
```http
GET    /api/genres          # Lista todos los géneros
GET    /api/genres/{id}     # Obtener género por ID
POST   /api/genres          # Crear nuevo género
PUT    /api/genres/{id}     # Actualizar género
DELETE /api/genres/{id}     # Eliminar género
```

### Libros
```http
GET    /api/books                    # Lista todos los libros
GET    /api/books/{id}              # Obtener libro por ID
GET    /api/books/genre/{genreId}   # Libros por género
GET    /api/books/state/{state}     # Libros por estado
POST   /api/books                   # Crear nuevo libro
PUT    /api/books/{id}              # Actualizar libro
DELETE /api/books/{id}              # Eliminar libro
```

## 🛠️ Tecnologías Utilizadas

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Spring Security** - Seguridad y autenticación
- **JWT** - Tokens de autenticación
- **PostgreSQL 16** - Base de datos
- **Docker** - Containerización
- **Maven** - Gestión de dependencias

## 📦 Estructura del Proyecto

```
dataSoft/
├── src/main/java/com/datasoft/
│   ├── config/          # Configuraciones (JWT, Security, Jackson)
│   ├── controller/      # Controladores REST
│   ├── dto/            # Data Transfer Objects
│   ├── entity/         # Entidades JPA
│   ├── repository/     # Repositorios JPA
│   ├── service/        # Lógica de negocio
│   └── PruebaDataSoftApplication.java
├── src/main/resources/
│   └── application.yml  # Configuración de la aplicación
└── pom.xml            # Dependencias Maven
```

## 🔧 Configuración de Desarrollo

### Requisitos
- Java 21
- Maven 3.6+
- Docker y Docker Compose

### Variables de Entorno
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/pruebadatasoft
    username: postgres
    password: postgres
  
  security:
    jwt:
      secret: mySecretKey123456789012345678901234567890123456789012345678901234567890
      expiration: 86400000

server:
  port: 8081
```

## 🧪 Ejemplos de Uso

### Registro de Usuario
```bash
curl -X POST http://localhost:8081/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "María García",
    "username": "mgarcia2",
    "password": "password456"
  }'
```

### Obtener Todos los Libros
```bash
curl http://localhost:8081/api/books
```

### Obtener Libros por Género
```bash
curl http://localhost:8081/api/books/genre/{uuid-del-genero}
```

### Puerto en Uso
```bash
# Cambiar puerto en application.yml si ya esta usando el 8080
server:
  port: 8082  
```

## 📝 Notas Importantes

- **UUIDs**: Todos los IDs son UUIDs para mayor seguridad
- **Contraseñas**: Encriptadas con BCrypt
- **JWT**: Tokens con expiración de 24 horas
- **CORS**: Habilitado para todas las rutas
- **Validación**: Campos validados con Bean Validation


## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.