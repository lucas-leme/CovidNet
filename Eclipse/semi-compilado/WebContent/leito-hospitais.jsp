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
		Hospitais pedidos
	  
        <c:forEach var="hospital" items="${hospitais}">
               <c:out value="${hospital}"/>
        </c:forEach>
    </div>	
    
</body>
</html>