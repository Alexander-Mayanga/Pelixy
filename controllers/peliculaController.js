const db = require('../config/database');

// Listar todas las películas
exports.listarPeliculas = (req, res) => {
  db.all('SELECT * FROM peliculas', (err, rows) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json(rows);
  });
};

// Obtener película por ID
exports.obtenerPelicula = (req, res) => {
  const { id } = req.params;
  db.get('SELECT * FROM peliculas WHERE id = ?', [id], (err, row) => {
    if (err) return res.status(500).json({ error: err.message });
    if (!row) return res.status(404).json({ error: 'Película no encontrada' });
    res.json(row);
  });
};

// Crear nueva película
exports.crearPelicula = (req, res) => {
  const { titulo, descripcion, director, anio } = req.body;
  const sql = 'INSERT INTO peliculas (titulo, descripcion, director, anio) VALUES (?, ?, ?, ?)';
  db.run(sql, [titulo, descripcion, director, anio], function (err) {
    if (err) return res.status(500).json({ error: err.message });
    res.status(201).json({ id: this.lastID, titulo, descripcion, director, anio });
  });
};

// Editar película
exports.editarPelicula = (req, res) => {
  const { id } = req.params;
  const { titulo, descripcion, director, anio } = req.body;
  const sql = 'UPDATE peliculas SET titulo = ?, descripcion = ?, director = ?, anio = ? WHERE id = ?';
  db.run(sql, [titulo, descripcion, director, anio, id], function (err) {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ id, titulo, descripcion, director, anio });
  });
};

// Eliminar película
exports.eliminarPelicula = (req, res) => {
  const { id } = req.params;
  db.run('DELETE FROM peliculas WHERE id = ?', [id], function (err) {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ mensaje: 'Película eliminada correctamente' });
  });
};
