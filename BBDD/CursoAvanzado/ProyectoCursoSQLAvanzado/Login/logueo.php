<?php

include('db.php');

$usuario= $_POST['usuario'];
$contrasena= $_POST['contrasena'];

session_start();
$_SESSION['usuario']=$usuario;

$logueo= "SELECT * 
          FROM usuarios
          WHERE Usuario='$usuario' AND Contrasena= '$contrasena'";


$validacion= mysqli_query($conexion, $logueo);
$filas= mysqli_fetch_row($validacion);

if($filas!=null){
    header('location:inicio.php');
}else{
    echo "Ha ocurrido un error al loguearte, vuelve a intentarlo más tarde";
}
mysqli_free_result($validacion);
?>