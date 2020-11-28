<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Administrador de leitos</title>
	<style><%@include file="/WEB-INF/css/main.css"%></style>
	<style><%@include file="/WEB-INF/css/util.css"%></style>
</head>
<body>
	<center>
        <h1>
        	<a href="${pageContext.request.contextPath}">Pagina inicial</a>
        </h1>
		<h1>Administracao dos leitos</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/new">Adicionar leito</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="${pageContext.request.contextPath}/leitos">Listar leitos</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${leito != null}">
			<form action="${pageContext.request.contextPath}/leitos/update" method="post">
        </c:if>
        <c:if test="${leito == null}">
			<form action="${pageContext.request.contextPath}/leitos/insert" method="post">
        </c:if>
        <table>
            <caption>
            	<h2>
            		<c:if test="${leito != null}">
            			Editar leito
            		</c:if>
            		<c:if test="${leito == null}">
            			Adicionar novo leito
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${leito != null}">
        			<input type="hidden" name="id" value="<c:out value='${leito.id}' />" />
        		</c:if>            
            <tr>
            <tr>
                <th>Medico: </th>
                <td>
                	<input type="text" name="medico" size="45"
                			value="<c:out value='${leito.medico}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Enfermeiro: </th>
                <td>
                	<input type="text" name="enfermeiro" size="45"
                			value="<c:out value='${leito.enfermeiro}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Paciente: </th>
                <td>
                	<input type="text" name="paciente" size="15"
                			value="<c:out value='${leito.paciente}' />"
                	/>
                	<select id="cars" name="cars">
					  <option value="volvo">Volvo</option>
					  <option value="saab">Saab</option>
					  <option value="fiat">Fiat</option>
					  <option value="audi">Audi</option>
					</select>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" name="salvar" value="Salvar mudança" />
            		<input type="submit" name="liberar" value="Liberar leito" />           		
            	</td>
            </tr>
        </table>
    </div>	
</body>
</html>