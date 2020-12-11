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
		<form action="${pageContext.request.contextPath}/login/signin" method="post">
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
	            	</td>
	            </tr>
	        </table>
        </form>
    </div>	
</body>
</html>