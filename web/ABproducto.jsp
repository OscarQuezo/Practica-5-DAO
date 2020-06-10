<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:if test="${producto.id==0}">Nuevo</c:if>
            <c:if test="${producto.id!=0}">Editar</c:if>
            producto
        </h1>
            <form action="Inicio" method="post" >
                <input type="hidden" name="id" value="${producto.id}"/>
            <label> Stock</label>
            <input type="text" name="stock" value="${producto.stock}">
            <br> <br>
            <label>descripcion</label> 
            <textarea name="descripcion">${producto.descripcion}</textarea>
            <br> <br>
            <input type="submit" value="Enviar"/>
                
            </form>
    </body>
</html>
