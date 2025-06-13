const db = require('../config/database');

// Listar comentarios
exports.listarComentarios = (req, res) => {
  db.all('SELECT * FROM comentarios', (err, rows) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json(rows);
  });
};

// Crear comentario
exports.crearComentario = (req, res) => {
  const { pelicula_id, usuario, comentario, puntuacion } = req.body;
  const sql = 'INSERT INTO comentarios (pelicula_id, usuario, comentario, puntuacion) VALUES (?, ?, ?, ?)';
  db.run(sql, [pelicula_id, usuario, comentario, puntuacion], function (err) {
    if (err) return res.status(500).json({ error: err.message });
    res.status(201).json({ id: this.lastID, pelicula_id, usuario, comentario, puntuacion });
  });
};

// Editar comentario
exports.editarComentario = (req, res) => {
  const { id } = req.params;
  const { usuario, comentario, puntuacion } = req.body;
  const sql = 'UPDATE comentarios SET usuario = ?, comentario = ?, puntuacion = ? WHERE id = ?';
  db.run(sql, [usuario, comentario, puntuacion, id], function (err) {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ id, usuario, comentario, puntuacion });
  });
};

// Eliminar comentario
exports.eliminarComentario = (req, res) => {
  const { id } = req.params;
  db.run('DELETE FROM comentarios WHERE id = ?', [id], function (err) {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ mensaje: 'Comentario eliminado correctamente' });
  });
};
