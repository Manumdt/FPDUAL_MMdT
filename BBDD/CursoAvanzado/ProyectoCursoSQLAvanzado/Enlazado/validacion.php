<?php

include('db_conection.php');

$correo=$_POST['correo'];
$contrasena=$_POST['contrasena'];

session_start();
$_SESSION['correo']=$correo;

$peticion= "SELECT * FROM usuarios WHERE Correo='$correo' AND Contrasena='$contrasena'";

$validacion=mysqli_query($conexion,$peticion);

$array=mysqli_fetch_array($validacion);

if(mysqli_num_rows($validacion)!=1){
    echo "El usuario y la contraseña introducidos no son correctos, intentelo de nuevo más tarde";
}else{
    header('location:index.php');
}