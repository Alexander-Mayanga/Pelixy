const express = require('express');
const router = express.Router();
const controller = require('../controllers/peliculaController');

router.get('/', controller.listarPeliculas);
router.get('/:id', controller.obtenerPelicula);
router.post('/', controller.crearPelicula);
router.put('/:id', controller.editarPelicula);
router.delete('/:id', controller.eliminarPelicula);

module.exports = router;
