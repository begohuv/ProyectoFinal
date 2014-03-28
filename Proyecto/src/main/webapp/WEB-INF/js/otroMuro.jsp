<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet"  type="text/css" href="../css/estilo.css"/>
     <script type="text/javascript" src="../jsc/comun.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muro Amigo</title>
    </head>
    <body>
        <div class="head">
            <h1>Bienvenido al Muro de : ${usuario.nombre} ${usuario.apellido}</h1>
            <a href="javascript:cerrar();">Salir <img class="imag3" src="../imagenes/salir.jpg" alt="Salir"/></a>             
            <a href="volverMuro.curso?idAnfitrion=${anfitrion.id}"><h4>Volver a su muro ${anfitrion.nombre} <img class="imag3" src="../imagenes/back.jpg" alt="Volver"/></h4></a>
        </div>
        <div class="left">
            <h3>Amigos :  <img class="imag1" src="../imagenes/emoticonosfinal.jpg" alt="Amigos"/></h3>            
            <c:if test="${vacioAmigo == true}">
                <h4>No tiene amigos</h4>
            </c:if>
            <c:if test="${vacioAmigo == false}">
                <ul>
                    <c:forEach  items="${amigos}" var="amigos">
                        <li>                               
                            <h4>${amigos.nombre} ${amigos.apellido}</h4>
                        </li>                                            
                    </c:forEach>                    
                </ul>
            </c:if>           
        </div>
        <div class="middle">            
            <h3> Comentarios:  <img src="../imagenes/Comment.ico" alt="Comentario"/></h3>
            <c:if test="${vacio == true}">
                <h3> Comentarios:  <img src="../imagenes/Comment.ico" alt="Comentario"/></h3>
                <h4>No existen contenidos</h4>
            </c:if>
            <c:if test="${vacio == false}">
                <ul>
                    <c:forEach  items="${contenidos}" var="contenido">
                        <li>                                        
                            <h4>Contenido: </h4>${contenido.texto}                                        
                        </li>                                     
                        <a href="mostrarComentario.curso?id=${contenido.id}&idUsuario=${anfitrion.id}&idUsuarioAmigo=${usuario.id}"><h5><img class="imag2" src="../imagenes/Add.ico" alt="Añadir"/> Añadir Comentario</h5></a>                                                       
                        <a class="borrar" href="borrarContenidoComentario.curso?id=${contenido.id}&idUsuario=${usuario.id}"><h5><img class="imag2" src="../imagenes/Delete.ico" alt="Eliminar"/> Eliminar</h5></a>                                                        
                    </c:forEach>
                </ul>
            </c:if>
            <br>                            
        </div>
        <div class="right">
            <h3>Usuarios de la Red :  <img src="../imagenes/Profile.ico" alt="Usuarios"/></h3> 
            <c:if test="${vacioUsuarios == true}">
                <h4>No hay usuarios</h4>
            </c:if>
            <c:if test="${vacioUsuarios == false}">
                <ul>
                    <c:forEach  items="${usuarios}" var="otroUsuario">
                        <li>
                            <h4>${otroUsuario.nombre} ${otroUsuario.apellido}</h4>                           
                        </li>                                                            
                    </c:forEach>
                </ul>
            </c:if>
        </div>                        
    </body>
</html>

