const express = require('express');
const router = express.Router();
const controller = require('../controllers/comentarioController');

router.get('/', controller.listarComentarios);
router.post('/', controller.crearComentario);
router.put('/:id', controller.editarComentario);
router.delete('/:id', controller.eliminarComentario);

module.exports = router;
