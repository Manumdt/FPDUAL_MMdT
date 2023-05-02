<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link rel="stylesheet" href="registro.css">
</head>
<body>
    <div class="contenedor">
        <div class="formulario">

            <form action="guardar.php" method="post">

                <h1 class="titulo">Registra tus datos</h1>

                <label for="">Ingresa un usuario</label>
                <input type="text" name="usuario">
                <label for="Correo">Ingresa un correo</label>
                <input type="email" name="email">
                <label for="">Ingresa una contraseña</label>
                <input type="password" name="contrasena">
                <button>Registrate</button>

            </form>

        </div>
    </div>
</body>
</html>