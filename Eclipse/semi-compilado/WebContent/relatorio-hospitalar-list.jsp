<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
</head>
<body>
	<center>
		<h1>Relatórios</h1>
        <h2>
        	<a href="new">Adicionar relatório hospitalar</a>
        	&nbsp;&nbsp;&nbsp;
        </h2>
        <h3>
        	<a href="list">Lista de relatórios</a>
        </h3>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de relatórios hospitalares</h2></caption>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="relatorio" items="${listRelatorioHospitalar}">
                <tr>
                    <td><c:out value="${relatorio.id}" /></td>
                    <td><c:out value="${relatorio.nomeHospital}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${relatorio.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${relatorio.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
