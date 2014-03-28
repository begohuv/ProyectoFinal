<%-- 
    Document   : principal
    Created on : 10-mar-2014, 14:05:23
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="../css/estilo.css"/>
        <title>Proyecto Fin de Curso Java</title>        
    </head>
    <body>
        <h1>Bienvenido a JACU su Red Social</h1>  
        <div class="inicio" >
            <form:form commandName="usuario" action="login.curso">
                <table>
                    <h3>Acceder a su cuenta</h3>                      
                    <tr>
                        <td>Usuario:</td>
                        <td>                            
                            <form:input path="nombre" autofocus="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:errors path="nombre"/>
                        </td>
                    </tr>                   
                    <tr>
                        <td>Password:</td>
                        <td>
                            <form:input path="password" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:errors path="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="submit" value="Acceder"/>                          
                        </td>
                    </tr>                
                </table>
            </form:form>  
            <br><br><br>
            <form:form commandName="usuario2" action="crearcuenta.curso">
                <table>
                    <br><br><br>
                    <h3>Registrese</h3>
                    <tr>
                        <td>Nombre:</td>
                        <td>
                            <form:input path="nombre"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:errors path="nombre"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Apellido:</td>
                        <td>
                            <form:input path="apellido"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:errors path="apellido"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td>
                            <form:input path="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:errors path="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="submit" value="Enviar"/>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
        <div>
            <img   src="../imagenes/grupocursojava.jpg" width="640" height="480" alt="Grupo Curso Java1"/>
        </div>    
    </body>
</html>
