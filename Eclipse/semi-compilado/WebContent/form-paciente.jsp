<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Lista de Prontuários</title>
	  <meta http-equiv="Content-Language" content="pt-br" 
  	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8">
	<style><%@include file="/WEB-INF/css/homePage.css"%></style>
	<style><%@include file="/WEB-INF/css/formsStyle.css"%></style>
</head>
<header>
  <svg width="41" height="41" viewBox="0 0 41 41" fill="none" xmlns="http://www.w3.org/2000/svg">
    <circle cx="20.5" cy="20.5" r="20.5" fill="#458FF6" />
    <path
      d="M23.4 31.234C21.58 31.234 19.9853 30.844 18.616 30.064C17.264 29.284 16.224 28.1833 15.496 26.762C14.768 25.3233 14.404 23.642 14.404 21.718C14.404 19.8113 14.768 18.1473 15.496 16.726C16.224 15.2873 17.264 14.1867 18.616 13.424C19.9853 12.644 21.58 12.254 23.4 12.254C24.648 12.254 25.818 12.4533 26.91 12.852C28.0193 13.2333 28.938 13.7707 29.666 14.464L28.574 16.908C27.742 16.232 26.91 15.7467 26.078 15.452C25.2633 15.14 24.388 14.984 23.452 14.984C21.6667 14.984 20.28 15.5647 19.292 16.726C18.3213 17.8873 17.836 19.5513 17.836 21.718C17.836 23.902 18.3213 25.5833 19.292 26.762C20.2627 27.9233 21.6493 28.504 23.452 28.504C24.388 28.504 25.2633 28.3567 26.078 28.062C26.91 27.75 27.742 27.256 28.574 26.58L29.666 29.024C28.938 29.7173 28.0193 30.2633 26.91 30.662C25.818 31.0433 24.648 31.234 23.4 31.234Z"
      fill="white" />
  </svg>

  <a href="${pageContext.request.contextPath}">CoronaViewer</a>
  <div class="right">
    <a href="${pageContext.request.contextPath}">Tela Inicial</a>
    <a href="${pageContext.request.contextPath}/relatorios-main-page.jsp">Relatórios</a>
    <a href="${pageContext.request.contextPath}/leitos">Vagas</a>
    <a href="${pageContext.request.contextPath}/prontuarios">Prontuários</a>
  </div>
</header>
<body>
	<center>
		<h1>Adicionar Paciente</h1>
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
            			Editando paciente
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${paciente != null}">
        			<input type="hidden" name="id_paciente" value="<c:out value='${paciente.id}' />" />
        		</c:if> 
        		<tr>
                <th>CPF: </th>
                <td>
					<input type="text" name="cpf" 
						class="${incorrectCPF == null ? 'form-correct' : 'form-incorrect'}"
						value="${incorrectCPF == null ? paciente.cpf : ''}"/>
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
            		<input type="submit" value="${paciente == null ? 'Adicionar Paciente' : 'Editar Paciente'}" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
