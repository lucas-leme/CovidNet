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
	</center>
    <div align="center">
		<c:if test="${user != null}">
	        <c:if test="${user.registered == true}">
				<form action="${pageContext.request.contextPath}/login/signin" method="post">
	        </c:if>
	        <c:if test="${user.registered == false}">
				<form action="${pageContext.request.contextPath}/login/signup" method="post">
	        </c:if>
        </c:if>
        <c:if test="${user == null}">
			<form action="${pageContext.request.contextPath}/login/signin" method="post">
        </c:if>
	        <table>
	            <tr>
	                <th>Email: </th>
	                <td>
	                	<input type="text" name="email" placeholder="Email"/>
	                </td>
	            </tr>
	            <tr>
	                <th>Senha: </th>
	                <td>
	                	<input type="text" name="psswd" placeholder="Senha"/>
	                </td>
	            </tr>
	            <tr>
	            	<td colspan="2" align="center">
	            		<input type="submit" name="signin" value="Login" />    
	            		<input type="submit" name="signup" value="Cadastrar" />          		
	            	</td>
	            </tr>
	        </table>
        </form>
    </div>	
</body>
</html>