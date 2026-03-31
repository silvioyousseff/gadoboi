<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="estilo.html"%>
<%@ include file="usuario.jsp"%>
<title>Rac&atilde;o</title>
</head>

<body>
<%@ include file="cabecalho.html"%>	
<!-- wrap starts here -->
<div id="wrap">	
	<!-- content-wrap starts here -->
	<div id="content-wrap"><div id="content">		
		
		<div id="sidebar" >
		
		<!-- barrinha do lado na parte de cima pequininho -->
			<div class="sidebox">
			
				<h1>GadoBoi</h1>
				
				<p>Melhorando o rendimento de seu rebanho</p>	
						
			</div>			
		<!-- barrinha do lado na parte de cima pequininho -->
		
			<div class="sidebox">	
			
				<h1 class="clear">Menu</h1>
				<ul class="sidemenu">	
					<li><a href="rendimento.jsp">Rendimento</a></li>
					<li><a href="extras.jsp">Voltar</a></li>							
				</ul>	
				
			</div>																			
		</div>	


		
		<div id="main">		
		
			<div class="post">
			
				
				<h1>Rendimento</h1>
				
				<ul>
					<p class="posted"></p>
					<h4>Proteinado</h4>
					
					<br>
					<h4><a href="proteinatoestiagem.jsp">Estiagem</a></h4>
					<br>
					<h4><a href="proteinatochuva.jsp">Chuva</a></h4>					
					<br><br>
					
					<h4>Concentrado Semi-confinamento</h4>
					
					<br>
					<h4><a href="semiestiagem.jsp">Estiagem</a></h4>
					<br>
					<h4><a href="semichuva.jsp">Chuva</a></h4>					
					<br><br>
					
					<h4>Concentrado confinamento</h4>
					
					<br>
					<h4><a href="confinamentoone.jsp">Concentrado I</a></h4>
					<br>
					<h4><a href="confinamentotwo.jsp">Concentrado II</a></h4>											
		</ul>
										
				<p class="post-footer align-right">					
					<br>
				</p>
				
			</div>		
				<br />				
										
		</div>					
		
	<!-- content-wrap ends here -->		
	</div></div>

<%@ include file="rodape.html"%>
</div>
</body>
</html>