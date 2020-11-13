<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Lista de leitos</title>
	<style><%@include file="/WEB-INF/css/main.css"%></style>
	<style><%@include file="/WEB-INF/css/util.css"%></style>
</head>
<body>
	<center>
		<h1>Gerenciamento de leitos</h1>
        <h2>
        	<a href="leitos/new">Adicionar leito</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="leitos/list">Listar leitos</a>
        	
        </h2>
	</center>
    <div align="center">
        <h2>Lista de leitos</h2>
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Medico</th>
                <th>Enfermeiro</th>
                <th>Paciente</th>
                <th>Acoes</th>
            </tr>
            <c:forEach var="leito" items="${listLeito}">
                <tr>
                    <td><c:out value="${leito.id}" /></td>
                    <td><c:out value="${leito.medico}" /></td>
                    <td><c:out value="${leito.enfermeiro}" /></td>
                    <td><c:out value="${leito.paciente}" /></td>
                    <td>
                    	<a href="leitos/edit?id=<c:out value='${leito.id}' />">Editar</a>
                    	<!--&nbsp;&nbsp;&nbsp;&nbsp;
                    	<!--a href="delete?id=<c:out value='${leito.id}' />">Deletar</a-->                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>