const db = require('../config/database');

const Pelicula = {
  getAll: (callback) => {
    db.all('SELECT * FROM peliculas', [], callback);
  },

  getById: (id, callback) => {
    db.get('SELECT * FROM peliculas WHERE id = ?', [id], callback);
  },

  create: (data, callback) => {
    const query = 'INSERT INTO peliculas (titulo, descripcion, genero, director) VALUES (?, ?, ?, ?)';
    const params = [data.titulo, data.descripcion, data.genero, data.director];
    db.run(query, params, function (err) {
      callback(err, { id: this.lastID, ...data });
    });
  },

  update: (id, data, callback) => {
    const query = 'UPDATE peliculas SET titulo = ?, descripcion = ?, genero = ?, director = ? WHERE id = ?';
    const params = [data.titulo, data.descripcion, data.genero, data.director, id];
    db.run(query, params, function (err) {
      callback(err, { id, ...data });
    });
  },

  delete: (id, callback) => {
    db.run('DELETE FROM peliculas WHERE id = ?', [id], callback);
  }
};

module.exports = Pelicula;