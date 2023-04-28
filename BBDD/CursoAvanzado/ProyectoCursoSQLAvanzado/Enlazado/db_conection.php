<?php

$conexion=mysqli_connect('localhost','root','','pflogin'); // Conexión con la base de datos pflogin.

if($conexion){
    echo "La conexión ha sido un éxito";
}else{
    echo "La conexión ha fallado";
}