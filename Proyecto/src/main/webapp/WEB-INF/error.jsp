<%-- 
    Document   : error
    Created on : 13-mar-2014, 18:16:19
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="../css/estilo.css"/>
        <title>Error</title>
    </head>
    <body>
        <h1 class="error">Cielos!!! Un Error  <img class="imag4" src="../imagenes/error.jpg" alt="Volver"/></h1>
        <h2>Excepci&oacute;n: ${excepcion}</h2>
         <a href="principal.curso">Volver <img class="imag3" src="../imagenes/back.jpg" alt="Volver"/></a>
    </body>
</html>
