<%@page import="org.apache.tools.ant.taskdefs.Exit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--  bean ControleRacaoEngorda, bean Racao,		 -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
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
					<li><a href="menu.jsp">Entrar</a></li>
					<li><a href="index.html">Voltar</a></li>					
				</ul>	
				
			</div>																			
		</div>	


		
		<div id="main">		
		
			<div class="post">
			
				
				<h1>Como Cadastrar</h1>
				
				<ul>					
					<li>Clique no link <a href="menu.jsp">Entrar</a></li>
					<br />
					<li>Em seguida, digite um e-mail v&aacute;lido, de alguma conta Google. Ex: "Gmail"</li>
					<br />
					<li>Logo ap&oacute;s clique em "Allow" para permitir que este e-mail possa ser cadastrado em nosso sistema.</li>
					<br />
					<li>Se não estiver cadastrado em nosso sistema, efetue o cadastro.</li>
					<br />
					<li>Assim est&aacute; apto para utilizar nosso sistema.</li>
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