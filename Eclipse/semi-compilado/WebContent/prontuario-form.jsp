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
			<form action="${pageContext.request.contextPath}/prontuarios/insert" method="post">
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
                <th>Estado do paciente: </th>
                <td>
                	<input type="text" name="estado_do_paciente" size="45"
                			value="<c:out value='${prontuario.estado_do_paciente}' />"
                		/>
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
                	<input type="text" name="teste_covid" size="45"
                			value="<c:out value='${prontuario.teste_covid}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Doença Respiratória: </th>
                <td>
                	<input type="text" name="doenca_respiratoria" size="45"
                			value="<c:out value='${prontuario.doenca_respiratoria}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Batimento Cardiaco: </th>
                <td>
                	<input type="text" name="batimento_cardiaco" size="45"
                			value="<c:out value='${prontuario.batimento_cardiaco}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Hipertensão: </th>
                <td>
                	<input type="text" name="hipertensao" size="45"
                			value="<c:out value='${prontuario.hipertensao}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Oximetria: </th>
                <td>
                	<input type="text" name="oximetria" size="45"
                			value="<c:out value='${prontuario.oximetria}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Radiometria Torax Normal: </th>
                <td>
                	<input type="text" name="radiometria_torax_normal" size="45"
                			value="<c:out value='${prontuario.radiometria_torax_normal}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Tomografia Torax Normal: </th>
                <td>
                	<input type="text" name="tomografia_torax_normal" size="45"
                			value="<c:out value='${prontuario.tomografia_torax_normal}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Ventilação Mecânica: </th>
                <td>
                	<input type="text" name="ventilacao_mecanica" size="45"
                			value="<c:out value='${prontuario.ventilacao_mecanica}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Diabetes: </th>
                <td>
                	<input type="text" name="diabetes" size="45"
                			value="<c:out value='${prontuario.diabetes}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Obesidade: </th>
                <td>
                	<input type="text" name="obesidade" size="45"
                			value="<c:out value='${prontuario.obesidade}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Ativo: </th>
                <td>
                	<input type="text" name="ativo" size="45"
                			value="<c:out value='${prontuario.ativo}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Data: </th>
                <td>
                	<input type="text" name="data" size="45"
                			value="<c:out value='${prontuario.data}' />"
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
