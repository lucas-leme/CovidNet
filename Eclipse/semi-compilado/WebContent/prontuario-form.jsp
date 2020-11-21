<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Lista de Pacientes</title>
</head>
<body>
	<center>
		<h1>Gerenciamento de Pacientes</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/new">Adicionar paciente</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="${pageContext.request.contextPath}/pacientes">Listar pacientes</a>
        	
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
            			Editar Paciente
            		</c:if>
            		<c:if test="${paciente == null}">
            			Adicionar Paciente
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
            <c:if test="${paciente != null}">
        		<th>Data de Entrada: </th>
                <td>
                	<input type="text" name="dataDeEntrada" size="15"
                			value="<c:out value='${paciente.dataDeEntrada}' />"
                	/>
                </td>
        	</c:if>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
