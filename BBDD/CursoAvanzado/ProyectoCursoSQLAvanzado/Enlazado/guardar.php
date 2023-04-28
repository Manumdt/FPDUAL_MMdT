<?php

include('db_conection.php');

$usuario=$_POST['usuario'];
$correo=$_POST['email'];
$contrasena=$_POST['contrasena'];

$guardar= "INSERT INTO usuarios (NombreUsuario, Correo, Contrasena) VALUES('$usuario', '$correo', '$contrasena')";
$validacion=mysqli_query($conexion, $guardar);

if($validacion){
    header('location:login.php');
}