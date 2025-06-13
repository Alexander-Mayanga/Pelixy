const db = require('../config/database');

const Comentario = {
  getAll: (callback) => {
    db.all('SELECT * FROM comentarios', [], callback);
  },

  getById: (id, callback) => {
    db.get('SELECT * FROM comentarios WHERE id = ?', [id], callback);
  },

  create: (data, callback) => {
    const query = 'INSERT INTO comentarios (pelicula_id, usuario, texto, puntuacion) VALUES (?, ?, ?, ?)';
    const params = [data.pelicula_id, data.usuario, data.texto, data.puntuacion];
    db.run(query, params, function (err) {
      callback(err, { id: this.lastID, ...data });
    });
  },

  update: (id, data, callback) => {
    const query = 'UPDATE comentarios SET pelicula_id = ?, usuario = ?, texto = ?, puntuacion = ? WHERE id = ?';
    const params = [data.pelicula_id, data.usuario, data.texto, data.puntuacion, id];
    db.run(query, params, function (err) {
      callback(err, { id, ...data });
    });
  },

  delete: (id, callback) => {
    db.run('DELETE FROM comentarios WHERE id = ?', [id], callback);
  }
};

module.exports = Comentario;