<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Administrador de leitos</title>
</head>
<body>
	<center>
		<h1>Administracao dos leitos</h1>
        <h2>
        	<a href="new">Adicionar leito</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">Listar leitos</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${leito != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${leito == null}">
			<form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
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
                <th>Enfermeiro: </th>
                <td>
                	<input type="text" name="paciente" size="45"
                			value="<c:out value='${leito.enfermeiro}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Medico: </th>
                <td>
                	<input type="text" name="medico" size="45"
                			value="<c:out value='${leito.medico}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Paciente: </th>
                <td>
                	<input type="text" name="enfermeiro" size="15"
                			value="<c:out value='${leito.paciente}' />"
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
