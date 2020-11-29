<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Lista de Prontuários</title>
</head>
<body>
	<center>
		<h1>Gerenciamento de Prontuários</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/prontuarios">VOLTAR</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${prontuario != null}">
			<form action="${pageContext.request.contextPath}/prontuarios/update" method="post">
        </c:if>
        <c:if test="${prontuario == null}">
			<form action="${pageContext.request.contextPath}/prontuarios" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${prontuario != null}">
            			Editar prontuário
            		</c:if>
            		<c:if test="${prontuario == null}">
            			Adicionar prontuário
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${prontuario != null}">
        			<input type="hidden" name="id" value="<c:out value='${prontuario.id}' />" />
        		</c:if> 
        		<tr>
                <th>CPF: </th>
                <td>
                	<input type="text" name="cpf" size="45"
                			value="<c:out value='${prontuario.cpf}' />"
                		/>
                </td>
            </tr>           
            <tr>
                <th>Nome: </th>
                <td>
                	<input type="text" name="nome" size="45"
                			value="<c:out value='${prontuario.nome}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Data de Nascimento: </th>
                <td>
                	<input type="text" name="data_de_nascimento" size="45"
                			value="<c:out value='${prontuario.dataDeNascimento}' />"
                		/>
                </td>
            </tr>
            <tr>
            <c:if test="${prontuario != null}">
        		<th>Data de Entrada: </th>
                <td>
                	<input type="text" name="data_de_entrada" size="15"
                			value="<c:out value='${prontuario.dataDeEntrada}' />"
                	/>
                </td>
        	</c:if>
            </tr>
            <tr>
                <th>Exame: </th>
                <td>
                	<input type="text" name="nome_exame" size="45"
                			value="<c:out value='${prontuario.nomeDoExame}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Descrição: </th>
                <td>
                	<input type="text" name="descricao_exame" size="45"
                			value="<c:out value='${prontuario.descricaoExame}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Data: </th>
                <td>
                	<input type="text" name="data_exame" size="45"
                			value="<c:out value='${prontuario.dataExame}' />"
                		/>
                </td>
            </tr>
                     <tr>
            <th>Resultado: </th>
                <td>
                	<input type="text" name="resultado_exame" size="45"
                			value="<c:out value='${prontuario.resultadoExame}' />"
                		/>
                </td>
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
