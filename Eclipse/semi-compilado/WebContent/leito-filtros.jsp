<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Filtro de leitos</title>
	<style><%@include file="/WEB-INF/css/main.css"%></style>
	<style><%@include file="/WEB-INF/css/util.css"%></style>
</head>
<body>

    <div align="center">
        <a href="${pageContext.request.contextPath}/leitos/list_hospitais">Hospitais</a>
        
        <c:forEach var="cidade" items="${cidades}">
               <tr>
                    <td>
                    	<a href="${pageContext.request.contextPath}/leitos/list_hospitais?cidade=<c:out value="${cidade}"/>"><c:out value="${cidade}"/></a>              	
                    </td>
                </tr>
        </c:forEach>
    </div>	
    
</body>
</html>