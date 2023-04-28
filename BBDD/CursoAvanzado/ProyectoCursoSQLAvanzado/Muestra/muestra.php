<?php

Include('db.php');

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Muestra</title>
    <link rel="stylesheet" href="muestra.css">
</head>
<body>
    <div class="principal">

        <div class="tabla">

        <div class="titulo">
            <h1>Usuarios registrados</h1>
        </div>

            <table>
                <tr class="cabecera ">
                    <td>Usuario</td>
                    <td>Descripción</td>
                    <td>Rango</td>
                </tr>

            <?php

                $sql = "SELECT * FROM usuarios";
                $validacion = mysqli_query($conexion, $sql);

                while ($muestra = mysqli_fetch_array($validacion)) {

            ?>
                
                <tr>
                    <td><?php echo $muestra['Usuario'] ?></td>
                    <td><?php echo $muestra['Descripcion'] ?></td>
                    <td><?php echo $muestra['Rango'] ?></td>
                <?php
                }
                ?>
                </tr>
                
            </table>

        </div>
    </div>
</body>

</html>