<%-- 
    Document   : muro
    Created on : 10-mar-2014, 14:54:45
    Author     : Administrador
--%>

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
        <title>Su Muro</title>
    </head>
    <body>
        <div class="head">
            <h1>Bienvenido al Muro de : ${usuario.nombre} ${usuario.apellido}</h1>
            <a href="javascript:cerrar();">Salir</a> 
        </div>
        <div class="left">
            <h3>Mis Amigos :   <img class="imag1" src="../imagenes/emoticonosfinal.jpg" alt="Amigos"/> </h3>            
            <c:if test="${vacioAmigo == true}">
                <h4>No tengo amigos</h4>
            </c:if>
            <c:if test="${vacioAmigo == false}">
                <ul>
                    <c:forEach  items="${amigos}" var="amigos">
                        <li>
                            <a href="muroAmigo.curso?idUsuario=${usuario.id}&idAmigo=${amigos.id}"><h4> ${amigos.nombre} ${amigos.apellido}</h4></a>                            
                            <a class="borrar" href="borrarAmigo.curso?idUsuario=${usuario.id}&idAmigo=${amigos.id}"><h5>Eliminar</h5></a>                                            
                        </li>                                                                    
                    </c:forEach>                                            
                </ul>
            </c:if>           
            <h4 id="hueco"></h4>  
        </div>
        <div class="middle">
            <form:form commandName="contenidoDto" action="muro.curso?ident=${usuario.id}">
                <table>                    
                    <h3>Escriba su mensaje<img src="../imagenes/Comment.ico" alt="Comentario"/></h3>                           
                    <tr>
                        <td>                                   
                            <form:textarea path="texto" rows="5" cols="35"/>
                        </td>
                        <td>                         
                            <form:errors path="texto"/>                    
                        </td>
                    </tr>                        
                    <tr>
                        <td><input type="submit" value="Compartir" name="btnCompartir" /></td>                                
                    </tr>
                </table>            
            </form:form> 
            <br><br><br><br><br><br><br>

            <c:if test="${vacio == true}">
                <h4>No existen contenidos</h4>
            </c:if>
            <c:if test="${vacio == false}">
                <ul>
                    <c:forEach  items="${contenidos}" var="contenido">
                        <li>
                            <h4>Contenido: </h4>${contenido.texto}
                        </li>                                                        
                        <a href="mostrarComentario.curso?id=${contenido.id}&idUsuario=${usuario.id}&idUsuarioAmigo=${usuario.id}"><h5>Añadir Comentario</h5></a>                                                                                                                            
                        <a class="borrar" href="borrarContenido.curso?id=${contenido.id}&idUsuario=${usuario.id}"><h5>Eliminar</h5></a>   
                    </c:forEach>
                </ul>
            </c:if>
            <br>                            
        </div>
        <div class="right">
            <h3>Usuarios de la Red :<img src="../imagenes/Profile.ico" alt="Usuarios"/></h3> 
            <c:if test="${vacioUsuarios == true}">
                <h4>No hay usuarios</h4>
            </c:if>
            <c:if test="${vacioUsuarios == false}">
                <ul>
                    <c:forEach  items="${usuarios}" var="otroUsuario">
                        <li>
                            <a href="altaAmigo.curso?idUsuario=${usuario.id}&idAmigo=${otroUsuario.id}"><h4>${otroUsuario.nombre} ${otroUsuario.apellido}</h4></a>                                            
                        </li>                                                            
                    </c:forEach>
                </ul>
            </c:if>
        </div>                        
    </body>
</html>
