<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Lista de Prontuários</title>
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
	<div class="center">
		<h1>Gerenciamento de Prontuários</h1>
	</div>
    <div align="center">

		<c:if test="${prontuario != null}">
			<form action="${pageContext.request.contextPath}/prontuarios/update" method="post">
        </c:if>
        <c:if test="${prontuario == null}">
			<form action="${pageContext.request.contextPath}/prontuarios/insert" method="post">
        </c:if>

		<form>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${prontuario != null}">
            			Editando prontuário
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${prontuario != null}">
        			<input type="hidden" name="id" value="<c:out value='${prontuario.id}' />" />
        		</c:if>
        		<tr>
                <th>Estado do paciente: </th>
                <td>
                	<input type="text" name="estado_paciente" size="45"
                			value="<c:out value='${prontuario.estadoDoPaciente}' />"
                		/>
                </td>
            </tr>
            <tr>
    			<th>Hospital: </th>
					<td align="center">
				    	<c:if test="${hospitais != null}">
							
								<select id="hospital_id" name="hospital_id">
						            <c:forEach var="hospital" items="${hospitais}">
						                <option value="<c:out value="${hospital.id}"/>"><c:out value="${hospital.nome}" /></option>
						                <!-- option><c:out value="${hospital}" /></option-->
						            </c:forEach>
						        </select>
				        </c:if>
			    	</td>    
            </tr>          
            <tr>
                <th>Diagnostico: </th>
                <td>
                	<input type="text" name="diagnostico" size="45"
                			value="<c:out value='${prontuario.diagnostico}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Teste de COVID-19: </th>
                <td>
                   <input type="text" name="teste_covid" size="45" value="<c:out value='${prontuario.testeCovid}' />"/>      		
                </td>
            </tr>
            <tr>
                <th>Tem doença respiratória </th>
                <td align="center">
		
                	<select id="doenca_respiratoria" name="doenca_respiratoria">
					    <c:if test="${prontuario == null || prontuario.doencaRespiratoria == true}">
							
	                		<option value="Sim">Sim</option>
	                		<option value="Não">Não</option>
						</c:if>
						<c:if test="${prontuario.doencaRespiratoria == false}">
					 		
	                		<option value="Não">Não</option>
	                		<option value="Sim">Sim</option>
						</c:if>
                	</select>
                </td>
            </tr>
            <tr>
                <th>Batimento cardiaco normal: </th>
                <td align="center">
                	<select id="batimento_cardiaco_normal" name="batimento_cardiaco_normal">
					    <c:if test="${prontuario == null || prontuario.batimentoCardiacoNormal == true}">							
	                		<option value="Sim">Sim</option>
	                		<option value="Não">Não</option>
						</c:if>
						<c:if test="${prontuario.batimentoCardiacoNormal == false}">
					 		
	                		<option value="Não">Não</option>
	                		<option value="Sim">Sim</option>
						</c:if>
                	</select>
                </td>
            </tr>
            <tr>
                <th>Tem hipertensão: </th>
                <td align="center">
                 <select id="hipertensao" name="hipertensao">
					    <c:if test="${prontuario == null || prontuario.hipertensao == true}">
							
	                		<option value="Sim">Sim</option>
	                		<option value="Não">Não</option>
						</c:if>
						<c:if test="${prontuario.hipertensao == false}">
					 		
	                		<option value="Não">Não</option>
	                		<option value="Sim">Sim</option>
						</c:if>
                	</select>
                </td>
            </tr>
            <tr>
                <th>Oximetria (%): </th>
                <td align="center">
                <input type="text" name="oximetria" size="45"
                			value="<c:out value='${prontuario.oximetria}' />"
                		/>   
                </td>
            </tr>
            <tr>
                <th>Radiometria torax normal: </th>
                <td align="center">
                 	<select id="radiometria_torax_normal" name="radiometria_torax_normal">
					    <c:if test="${prontuario == null || prontuario.radiometriaToraxNormal == true}">
	                		<option value="Sim">Sim</option>
	                		<option value="Não">Não</option>
						</c:if>
						<c:if test="${prontuario.radiometriaToraxNormal == false}">
					 		
	                		<option value="Não">Não</option>
	                		<option value="Sim">Sim</option>
						</c:if>
                	</select>
                </td>
            </tr>
            <tr>
                <th>Tomografia torax normal: </th>
                <td align="center">
                 	<select id="tomografia_torax_normal" name="tomografia_torax_normal">
					    <c:if test="${prontuario == null || prontuario.tomografiaToraxNormal == true}">
	                		<option value="Sim">Sim</option>
	                		<option value="Não">Não</option>
						</c:if>
						<c:if test="${prontuario.tomografiaToraxNormal == false}">
					 		
	                		<option value="Não">Não</option>
	                		<option value="Sim">Sim</option>
						</c:if>
                	</select>
                </td>
            </tr>
            <tr>
                <th>Precisa de ventilação mecânica: </th>
                <td align="center">
                 	<select id="ventilacao_mecanica" name="ventilacao_mecanica">
   					    <c:if test="${prontuario == null || prontuario.ventilacaoMecanica == true}">							
	                		<option value="Sim">Sim</option>
	                		<option value="Não">Não</option>
						</c:if>
						<c:if test="${prontuario.ventilacaoMecanica == false}">
					 		
	                		<option value="Não">Não</option>
	                		<option value="Sim">Sim</option>
						</c:if>
                	</select>
                </td>
            </tr>
            <tr>
                <th>Tem diabetes: </th>
                <td align="center">
                 	<select id="diabetes" name="diabetes">
					    <c:if test="${prontuario == null || prontuario.diabetes == true}">

	                		<option value="Sim">Sim</option>
	                		<option value="Não">Não</option>
						</c:if>
						<c:if test="${prontuario.diabetes == false}">
					 		
	                		<option value="Não">Não</option>
	                		<option value="Sim">Sim</option>
						</c:if>
                	</select>
                </td>
            </tr>
            <tr>
                <th>Tem obesidade: </th>
                <td align="center">
                 	<select id="obesidade" name="obesidade">
					    <c:if test="${prontuario == null || prontuario.obesidade == true}">
							
	                		<option value="Sim">Sim</option>
	                		<option value="Não">Não</option>
						</c:if>
						<c:if test="${prontuario.obesidade == false}">
					 		
	                		<option value="Não">Não</option>
	                		<option value="Sim">Sim</option>
						</c:if>
                	</select>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
					<input type="hidden" name="id_paciente" value="<%= request.getAttribute("id_paciente") %>" />
					<input type="hidden" name="id_prontuario" value="<%= request.getAttribute("id_prontuario") %>" />
            		<input type="submit" value="Salvar prontuário" />
            	</td>
            </tr>
        </table>

        </form>
    </div>	
</body>
</html>
