const db = require('./config/database');

const crearTablas = () => {
  db.run(`CREATE TABLE IF NOT EXISTS peliculas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT,
    descripcion TEXT,
    director TEXT,
    anio INTEGER
  )`);

  db.run(`CREATE TABLE IF NOT EXISTS comentarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    pelicula_id INTEGER,
    usuario TEXT,
    comentario TEXT,
    puntuacion INTEGER,
    FOREIGN KEY(pelicula_id) REFERENCES peliculas(id)
  )`);
};

crearTablas();
