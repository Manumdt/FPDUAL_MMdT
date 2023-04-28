<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="principal">
        <div class="formulario">
            
            <div class="titulo">
                <h1>Registrate</h1>
            </div>
            <form action="guardado.php" method="post">

                <label for="">Ingresa tu nombre</label>
                <input type="text" name="nombre">
                <label for="Correo">Ingresa un correo</label>
                <input type="email" name="email">
                <label for="">Ingresa un usuario</label>
                <input type="text" name="usuario">
                <label for="">Ingresa una contraseña</label>
                <input type="password" name="contrasena">
                <button>Registrate</button>
            </form>

        </div>
    </div>
</body>
</html>