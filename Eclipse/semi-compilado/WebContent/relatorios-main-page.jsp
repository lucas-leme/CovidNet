<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Relatorios</title>
  <meta http-equiv="Content-Language" content="pt-br" 
  	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8">
	<style><%@include file="/WEB-INF/css/homePage.css"%></style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
</head>
<body>
<header>
  <svg width="41" height="41" viewBox="0 0 41 41" fill="none" xmlns="http://www.w3.org/2000/svg">
    <circle cx="20.5" cy="20.5" r="20.5" fill="#458FF6" />
    <path
      d="M23.4 31.234C21.58 31.234 19.9853 30.844 18.616 30.064C17.264 29.284 16.224 28.1833 15.496 26.762C14.768 25.3233 14.404 23.642 14.404 21.718C14.404 19.8113 14.768 18.1473 15.496 16.726C16.224 15.2873 17.264 14.1867 18.616 13.424C19.9853 12.644 21.58 12.254 23.4 12.254C24.648 12.254 25.818 12.4533 26.91 12.852C28.0193 13.2333 28.938 13.7707 29.666 14.464L28.574 16.908C27.742 16.232 26.91 15.7467 26.078 15.452C25.2633 15.14 24.388 14.984 23.452 14.984C21.6667 14.984 20.28 15.5647 19.292 16.726C18.3213 17.8873 17.836 19.5513 17.836 21.718C17.836 23.902 18.3213 25.5833 19.292 26.762C20.2627 27.9233 21.6493 28.504 23.452 28.504C24.388 28.504 25.2633 28.3567 26.078 28.062C26.91 27.75 27.742 27.256 28.574 26.58L29.666 29.024C28.938 29.7173 28.0193 30.2633 26.91 30.662C25.818 31.0433 24.648 31.234 23.4 31.234Z"
      fill="white" />
  </svg>

  <a href="${pageContext.request.contextPath}">CoronaViewer</a>
  <div class="right">
    <a href="${pageContext.request.contextPath}">Tela Inicial</a>
    <a href="${pageContext.request.contextPath}/relatorios-main-page.jsp">Relatórios</a>
    <a href="${pageContext.request.contextPath}/leitos">Vagas</a>
    <a href="${pageContext.request.contextPath}/prontuarios">Prontuários</a>
  </div>
</header>
<center>
	<h1>Relatorios</h1>
	
	<img src="https://i.imgur.com/xEVDzdk.png" alt="" />
	
	<form action="${pageContext.request.contextPath}/relatorios/new" method="post">
        

			        <h2>Data de inicio</h2>
			        <input class="ui-dispatcher" type="date" name="data_inicio">
			        <br/>

		        

			        <h2>Data de fim</h2>
			        <input class="ui-dispatcher"  type="date" name="data_fim">
			        <br/>

 
        		<h2>Tipo de relatório</h2>
        			<select id="relatorio_id" name="tipo_relatorio" onchange="changeOptions(this)" onshow="changeOptions(this)">
        				<option id="estadual" value="rel_estadual">Estadual</option>		
						<option id="hospitalar" value="rel_hospitalar">Hospitalar</option>
						<option id="municipal" value="rel_municipal">Municipal</option>
			        </select>
			     <br/>

	        	<div id="extra">
	        			<div id="hospitais">
		       	 			<c:forEach var="hospital" items="${hospitais}">
		       	 				<div>
					                <input type="hidden" name="nome" value="${hospital.nome}"/>
					                <input type="hidden" name="id" value="${hospital.id}"/>
		       	 				</div>
				            </c:forEach>
				        </div>
	        			<div id="municipios">
		       	 			<c:forEach var="municipio" items="${municipios}">
		       	 				<div>
					                <input type="hidden" name="nome" value="${municipio.nome}"/>
					                <input type="hidden" name="id" value="${municipio.id}"/>
		       	 				</div>
				            </c:forEach>
				        </div>
	        			<select id="rel_options"></select>
	        	</div>
	        	
	        	<br/>
	        	<br/>
        	
		        	<input type="submit" value="Gerar relatório">
	</form>
	
</center>
   
</body>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
		<script>
			function changeOptions(obj)
			{
				console.log("Teste");
				
			    let selectBox = obj;
			    let selected = selectBox.options[selectBox.selectedIndex].value;
			    
			    let select = document.getElementById("rel_options");
			    let conditionalShow = document.getElementById("extra");
		    	select.name = "which_element";
			    
			    select.textContent = '';

			    console.log(selected);
			    
			    if(selected === 'rel_hospitalar' || selected === 'rel_municipal')
			    {
			    	let elements;
			    	
			    	if(selected === 'rel_hospitalar') elements = document.getElementById("hospitais");
			    	else elements = document.getElementById("municipios");
			    	
			    	let inputs = elements.getElementsByTagName('input');
			    	let list_inputs = [];
			    	
			    	for (var i = 0; i < inputs.length; i += 2) {
				    	
				        let opt = document.createElement('option');
				        opt.value = inputs[i + 1].value;
				        opt.innerHTML = inputs[i].value;
				        opt.name = "which_element";
				        
				    	list_inputs.push(opt);	
				    	select.appendChild(opt);
			    	}
			    	
			    	console.log(list_inputs);
			    	
			    	//console.log(divArray);
			    	
			    	//console.log(elements);
			        
			        console.log("SELECTED hospitalar");
			        
			        //select.appendChild(list_inputs);
			        
			        conditionalShow.display = "block";
			        $('#extra').show();
			    }

			    
			    if(selected === 'rel_estadual'){
			        console.log("SELECTED estadual");
			        conditionalShow.display = "none";
			        $('#extra').hide();
			    }
			    else{
			        //textarea.style.display = "none";
			        console.log("OTHER");
			    }
			}

		</script>
</html>