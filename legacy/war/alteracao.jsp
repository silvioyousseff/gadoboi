<%@page import="org.apache.tools.ant.taskdefs.Exit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--  bean ControleRacaoEngorda, bean Racao,		 -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<%@ include file="usuario.jsp"%>
<title>Menu</title>
</head>
<%@ include file="cabecalho.html"%>	
<body>
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
					<li><a href="visualizagado.jsp">Ra&ccedil;a de Gado</a></li>
					<li><a href="visualizainsumo.jsp">Insumos</a></li>
					<li><a href="visualizaregiao.jsp">Regi&otilde;es</a></li>
					<li><a href="visualizamanejo.jsp">Manejos</a></li>
					<li><a href="menu.jsp">Voltar</a></li>								
				</ul>	
				
			</div>																			
		</div>	


		
		<div id="main">		
		
			<div class="post">
			
				
				<h1>Op&ccedil;&otilde;es</h1>
				<ul>					
					<li>F&oacute;rmulas para fabrica&ccedil;&atilde;o de ra&ccedil;&atilde;o balanciada</li>
					<li>Gerenciamento financeiro bovino</li>
					<li>Armazenamento de dados</li>
				</ul>
				<p class="post-footer align-right">					
					<br>
				</p>
				
			</div>		
				<br />										
		</div>					
		
	<!-- content-wrap ends here -->		
	</div></div>

<!-- footer starts here -->	

	<%@ include file="rodape.html"%>

</body>
</html>