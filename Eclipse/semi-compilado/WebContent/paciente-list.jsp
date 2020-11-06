<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Pacientes Management Application</title>
</head>
<body>
	<center>
		<h1>Pacientes Management</h1>
        <h2>
        	<a href="pacientes/new">Adicionar Paciente</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="pacientes/list">Lista de Pacientes</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Pacientes</h2></caption>
            <tr>
            	<th>ID</th>
                <th>CPF</th>
                <th>Nome</th>
                <th>Prontuario</th>
                <th>Data de Entrada</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="paciente" items="${listPacientes}">
                <tr>
                	<td><c:out value="${paciente.id}" /></td>
                    <td><c:out value="${paciente.cpf}" /></td>
                    <td><c:out value="${paciente.nome}" /></td>
                    <td><c:out value="${paciente.prontuario}" /></td>
                    <td><c:out value="${paciente.dataDeEntrada}" /></td>
                    <td>
                    	<a href="pacientes/edit?id=<c:out value='${paciente.id}' />">Editar</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="pacientes/delete?id=<c:out value='${paciente.id}' />">Apagar</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
