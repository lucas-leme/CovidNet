<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Hospitais</title>
	<style><%@include file="/WEB-INF/css/main.css"%></style>
	<style><%@include file="/WEB-INF/css/util.css"%></style>
</head>
<body>

    <div align="center">
		Hospitais pedidos :
	  	<table>
	  		<tr>
		        <c:if test="${hospitais != null}">       
			        <c:forEach var="hospital" items="${hospitais}">
			               <td><c:out value="${hospital.nome}"/></td>
			               <td><c:out value="${hospital.telefone}"/></td>
			        </c:forEach>
		        </c:if>
		    </tr>
        </table>
    </div>	
    
</body>
</html>