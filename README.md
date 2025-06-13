# Pelixy
Postman Pelixy
# 🎬 Pelixy - App de Recomendaciones de Películas

Aplicación Android nativa desarrollada con Java y SQLite, que permite ver películas, buscar, y dejar comentarios.

Integrantes del grupo

CESAR ALEXANDER MAYANGA MINAYA
JAIME CCAPACCA MERINO
JEAN CARLOS FASABI OROSCO
OSMAR MAURICIO MARCA PEÑA
DEYSI ARACELY QUINTANA JUAREZ

## 🧠 Estructura de la Base de Datos
-- Crear base de datos
CREATE DATABASE PelixyDB;
GO

USE PelixyDB;
GO

-- Tabla de géneros
CREATE TABLE Generos (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL
);
GO

-- Tabla de películas
CREATE TABLE Peliculas (
    id INT PRIMARY KEY IDENTITY(1,1),
    titulo NVARCHAR(200) NOT NULL,
    descripcion NVARCHAR(MAX),
    imagen_url NVARCHAR(500),
    genero_id INT NOT NULL,
    FOREIGN KEY (genero_id) REFERENCES Generos(id)
);
GO

-- Tabla de usuarios (opcional, para comentarios)
CREATE TABLE Usuarios (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) UNIQUE
);
GO

-- Tabla de comentarios
CREATE TABLE Comentarios (
    id INT PRIMARY KEY IDENTITY(1,1),
    contenido NVARCHAR(MAX) NOT NULL,
    fecha DATETIME DEFAULT GETDATE(),
    autor NVARCHAR(100),
    pelicula_id INT NOT NULL,
    usuario_id INT,
    FOREIGN KEY (pelicula_id) REFERENCES Peliculas(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);
GO

## 🗂 Interfaces del APP

- `MainActivity` → Lista principal de películas.
- `DetallePeliculaActivity` → Muestra detalles + comentarios.
- `RegistrarComentarioActivity` → Agrega un nuevo comentario.
- `BuscarPeliculasActivity` → Búsqueda por título.


# 🖥️ Backend - Pelixy API

## 📌 Tecnología
- Node.js
- Express
- SQLite

## 📁 Estructura

- `config/database.js`: Conexión a SQLite
- `controllers/`: Controladores de lógica
- `models/`: Modelos de entidades
- `routes/`: Rutas para API
- `database/pelixy.db`: Base de datos local

## 📡 Endpoints del Backend

- `GET /api/v1/peliculas` → Lista películas
- `POST /api/v1/comentarios` → Guarda comentario

## 📬 Documentación API con Postman

https://documenter.getpostman.com/view/45688757/2sB2x6kXBH


## ▶️ Ejecución

# ▶️ Ejecución

1. Instalar dependencias:
   ```
   npm install
   ```

2. Iniciar el servidor:
   ```
   node app.js
   ```

3. Verificar en Postman:
   - `http://localhost:3000/api/v1/peliculas`
   - `http://localhost:3000/api/v1/comentarios`

4. Abrir el proyecto en Android Studio.
5. Asegurarse que el backend esté corriendo localmente (`http://10.0.2.2:3000`).
6. Ejecutar la app en un emulador o dispositivo físico.


