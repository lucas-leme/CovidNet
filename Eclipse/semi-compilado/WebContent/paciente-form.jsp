<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Cadastrar paciente</title>
</head>
<body>
	<center>
		<h1>Cadastro de paciente</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/prontuarios">VOLTAR</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${paciente != null}">
			<form action="${pageContext.request.contextPath}/pacientes/update" method="post">
        </c:if>
        <c:if test="${paciente == null}">
			<form action="${pageContext.request.contextPath}/pacientes/insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${paciente != null}">
            			Editar prontuário
            		</c:if>
            		<c:if test="${paciente == null}">
            			Adicionar prontuário
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${paciente != null}">
        			<input type="hidden" name="id" value="<c:out value='${paciente.id}' />" />
        		</c:if> 
        		<tr>
                <th>CPF: </th>
                <td>
                	<input type="text" name="cpf" size="45"
                			value="<c:out value='${paciente.cpf}' />"
                		/>
                </td>
            </tr>           
            <tr>
                <th>Nome: </th>
                <td>
                	<input type="text" name="nome" size="45"
                			value="<c:out value='${paciente.nome}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Data de Nascimento: </th>
                <td>
                	<input type="text" name="data_de_nascimento" size="45"
                			value="<c:out value='${paciente.dataDeNascimento}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Endereço: </th>
                <td>
                	<input type="text" name="endereco" size="45"
                			value="<c:out value='${paciente.endereco}' />"
                		/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" name="add_paciente" value="Adicionar paciente" />         		
            	</td>
            </tr>
        </table>
    </div>	
</body>
</html>
