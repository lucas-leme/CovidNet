<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Prontuarios Management Application</title>
</head>
<body>
	<center>
		<h1>Prontuarios Management</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/prontuarios/new">Adicionar prontuario</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="${pageContext.request.contextPath}">Página inicial</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de prontuarios</h2></caption>
            <tr>
            	<th>ID</th>
                <th>CPF</th>
                <th>Nome</th>
                <th>Data de Nascimento</th>
                <th>Data de Entrada</th>
                <th>Exame</th>
                <th>Descrição</th>
                <th>Data</th>
                <th>Resultado</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="prontuario" items="${listProntuarios}">
                <tr>
                	<td><c:out value="${prontuario.id}" /></td>
                    <td><c:out value="${prontuario.cpf}" /></td>
                    <td><c:out value="${prontuario.nome}" /></td>
                    <td><c:out value="${prontuario.dataDeNascimento}" /></td>
                    <td><c:out value="${prontuario.dataDeEntrada}" /></td>
                    <td><c:out value="${prontuario.nomeDoExame}" /></td>
                    <td><c:out value="${prontuario.descricaoExame}" /></td>
                    <td><c:out value="${prontuario.dataExame}" /></td>
                    <td><c:out value="${prontuario.resultadoExame}" /></td>
                    <td>
                    	<a href="${pageContext.request.contextPath}/prontuarios/edit?id=<c:out value='${prontuario.id}' />">Editar</a>                   	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
