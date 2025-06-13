const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
require('./database'); // Crear tablas

const app = express();
const port = 3000;

app.use(cors());
app.use(bodyParser.json());

app.use('/api/v1/peliculas', require('./routes/peliculas'));
app.use('/api/v1/comentarios', require('./routes/comentarios'));

app.listen(port, () => {
  console.log(`Servidor corriendo en http://localhost:${port}`);
});
