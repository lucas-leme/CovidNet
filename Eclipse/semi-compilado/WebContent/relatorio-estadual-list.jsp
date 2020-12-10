<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
</head>
<body>
	<center>
		<h1>Relatórios</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/relatorioEstadual/new">Adicionar relatório estadual</a>
        	&nbsp;&nbsp;&nbsp;
        </h2>
        <h3>
        	<a href="${pageContext.request.contextPath}/relatorioEstadual/list">Lista de relatórios</a>
        </h3>
	</center>
    <div align="center">
            
            pedidos
			<c:forEach var="pedidosItem" items="${pedidos}">
				<h1>${pedidosItem.data}</h1>
			</c:forEach>
            
            Vagas
			<c:forEach var="vagasItem" items="${vagas}">
				<h1>${vagasItem.data}</h1>
			</c:forEach>

			Alocações
			<c:forEach var="alocsItem" items="${alocs}">
				<h1>${alocsItem.data}</h1>
			</c:forEach>

	    	<c:if test="${alocs == null}">     
	    		teste       
	    	</c:if>
    </div>	
</body>
</html>
