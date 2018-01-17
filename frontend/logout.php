<?php 
session_start();
error_reporting(E_ALL);
ini_set('display_errors', 1);

reset($_SESSION);
session_destroy();
header("Location: /abc-banking-group-coursework/home.php");
?>