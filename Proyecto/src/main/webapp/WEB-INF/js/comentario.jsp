<%-- 
    Document   : comentario
    Created on : 24-mar-2014, 13:58:35
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet"  type="text/css" href="../css/estilo.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentarios</title>       
    </head>
     <body>                
        <h1>Comentario del Muro de :  ${usuarioAmigo.nombre}</h1>                 
        <div class="middlec">
            <c:if test="${usuario.id == usuarioAmigo.id}">
                <a href="volverMuro.curso?idAnfitrion=${usuario.id}">Volver <img class="imag3" src="../imagenes/back.jpg" alt="Volver"/></a> 
            </c:if>
            <c:if test="${usuario.id != usuarioAmigo.id}">
                <a href="muroAmigo.curso?idUsuario=${usuario.id}&idAmigo=${usuarioAmigo.id}">Volver <img class="imag3" src="../imagenes/back.jpg" alt="Volver"/></a> 
            </c:if>
            <form:form commandName="comentarioDto" action="comentario.curso?idUsuario=${usuario.id}&idUsuarioAmigo=${usuarioAmigo.id}&idContenido=${contenido.id}">                
                <h3>Escriba un Comentario :  ${usuario.nombre} <img src="../imagenes/Comment.ico" alt="Comentario"/></h3> 
                <table>                    
                    <tr>
                        <td>
                            <form:textarea path="texto" rows="5" cols="35" autofocus="true"/>
                        </td>
                        <td>
                            <form:errors path="texto"/>
                        </td>
                    </tr>                        
                    <tr>
                        <td>
                            <input type="submit" value="Compartir" name="btnCompartir" />
                        </td>                       
                    </tr>
                </table>            
            </form:form>  
                <br><br><br><br><br><br>
            <h3>Contenido :</h3>
            <ul>
                <li>
                    ${contenido.texto} 
                </li>  
            </ul>   
            <c:if test="${vacioComentario == true}">
                <h4>No existen comentarios</h4>
            </c:if>
            <c:if test="${vacioComentario == false}">
                <h3>Comentarios :</h3>
                <ul>
                    <c:forEach  items="${comentarios}" var="comentario">
                        <li>
                            <span>Comentario: </span> ${comentario.texto}<br> 
                            <span>Fecha Comentario: </span></h5> ${comentario.fecha}<br>
                            <span>Comentado por: </span> ${comentario.nombreUsuario} 
                        </li>                                                                                            
                    </c:forEach>
                </ul>
            </c:if>            
            <br>                            
        </div>
    </body>
</html>
