<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Relatório Estadual</title>
</head>
<body>
	<center>
		<h1>Relatório Estadual</h1>
	       <h2>
	       		<a href="list">Lista de relatórios</a>
	       </h2>
	</center>
    <div align="center">
		<c:if test="${user != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
			<form action="insert" method="post">
        </c:if>
        <table border="1">
            <caption>
            	<h2>
            		<c:if test="${relatorio != null}">
            			Edit User
            		</c:if>
            		<c:if test="${relatorio == null}">
            			Add New User
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${relatorio != null}">
        			<input type="hidden" name="id" value="<c:out value='${relatorio.id}' />" />
        		</c:if>            
            <tr>
                <th>Nome Estado: </th>
                <td>
                	<input type="text" name="nomeEstado" size="45"
                			value="<c:out value='${relatorioEstadual.nomeEstado}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Número de Municipios: </th>
                <td>
                	<input type="text" name="numeroMunicipios" size="45"
                			value="<c:out value='${relatorioEstadual.numeroMunicipios}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Número de Hospitais: </th>
                <td>
                	<input type="text" name="numeroHospitais" size="15"
                			value="<c:out value='${relatorio.numeroHospitais}' />"
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
