<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Prontuarios</title>
	  <meta http-equiv="Content-Language" content="pt-br" 
  	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8">
	<style><%@include file="/WEB-INF/css/homePage.css"%></style>
</head>
<body>
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
	
	<input type="hidden" name="cpf" value="<%= request.getAttribute("cpf") %>" />
	
    <div align="center">
    
    <h1>Dados do Prontuário</h1>
    <h3 id="id_prontuario">ID: ${prontuario.id}</h3>
    <h3>Nome do paciente: ${paciente.nome}</h3>
    <h3>Data de nascimento: ${paciente.dataDeNascimento}</h3>
    <h3>CPF: <%= request.getAttribute("cpf") %></h3>
    <h3>Data: ${prontuario.data}</h3>
    <h3> Estado do Paciente: ${prontuario.estadoDoPaciente}</h3>
    <h3>Diagnostico: ${prontuario.diagnostico}</h3>
    <h3>Teste de Covid: ${prontuario.testeCovid}</h3>
    <h3>Oximetria: ${prontuario.oximetria}</h3>
    
    <form action="${pageContext.request.contextPath}/pacientes/edit" method="post">
		<input type="hidden" name="cpf" value="<%= request.getAttribute("cpf") %>" />
		<input type="submit" name="search_prontuario" value="Editar" /> 
	</form>
    
    <h1>Questionário</h1>
    
    <h3>Tem doença respiratória? 
	    <c:if test="${prontuario.doencaRespiratoria == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.doencaRespiratoria == false}">
	 		Não
		</c:if>
    </h3>
    <h3>Batimento cardiaco normal? 
 		<c:if test="${prontuario.batimentoCardiacoNormal == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.batimentoCardiacoNormal == false}">
	 		Não
		</c:if>
    
    </h3>
    <h3>Tem hipertensão?  
    	<c:if test="${prontuario.hipertensao == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.hipertensao == false}">
	 		Não
		</c:if>
    </h3>
    
    <h3>Tem radiomentria de torax normal?  
    	<c:if test="${prontuario.radiometriaToraxNormal == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.radiometriaToraxNormal == false}">
	 		Não
		</c:if>    
    </h3>
    
    <h3>Tem tomografia de torax normal?
    	<c:if test="${prontuario.tomografiaToraxNormal == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.tomografiaToraxNormal == false}">
	 		Não
		</c:if>
    </h3>
    
    <h3>Precisa de ventilação mecânica? 
    	<c:if test="${prontuario.ventilacaoMecanica == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.ventilacaoMecanica == false}">
	 		Não
		</c:if>
    </h3>
    
    <h3>Tem diabetes? 
     	<c:if test="${prontuario.diabetes == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.diabetes == false}">
	 		Não
		</c:if>   
    </h3>
    
    <h3>É obeso? 
     	<c:if test="${prontuario.obesidade == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.obesidade == false}">
	 		Não
		</c:if>      
    </h3>
    
    <h3>É ativo fisicamente? 
      	<c:if test="${prontuario.ativo == true}">
			Sim
		</c:if>
		<c:if test="${prontuario.ativo == false}">
	 		Não
		</c:if>    
		<%= request.getAttribute("prontuario") %>
    </h3>
    
     <form action="${pageContext.request.contextPath}/prontuarios/edit" method="post">
		<input type="hidden" name="cpf" value="<%= request.getAttribute("cpf") %>" />
		<input type="submit" name="search_prontuario" value="Editar" />  
	</form>
    
    <form action="${pageContext.request.contextPath}/prontuarios/close" method="post">
		<div  class="center">
			<!--div><c:out value='%= request.getAttribute("prontuario") %>'></c:out></div>
			<div><c:out value="${cidade.id}"/></div-->
			<input type="text" name="id_prontuario" value="${prontuario.id}"/>
	
			<input type="submit" name="close_prontuario" value="Fechar prontuário" />
		</div>
	</form>    
	
    <form action="${pageContext.request.contextPath}/prontuarios/solicitar_uti" method="post">
		<div  class="center">
			<input type="text" name="id_prontuario2" value="${prontuario.id}"/>
	 
			<input type="submit" name="solicitar_uti" value="Solicitar UTI" />  
		</div>
	</form>

    </div>	
</body>
</html>
