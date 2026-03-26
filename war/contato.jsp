<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="estilo.html"%>
<title>Contato</title>
</head>
<body>
<%@ include file="cabecalho.html"%>	
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
					<li><a href="index.html">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	
	
<div id="main">				
	<div class="post">
		<h1>Contato</h1>			
				<form id="frmContato" action="../ServletContato" method="get">			
					<p>			
					<label>Name</label>
					<input required name="idnome" type="text" size="30" />
					<label>Email</label>
					<input required name="idemail" type="text" size="30" />
					<label>Mensagem</label>
					<textarea required rows="5" cols="5" name="idtext"></textarea>
					<br />
					<input class="button" type="submit" value="" />		
					</p>		
				</form>
														
				<p class="post-footer align-right">					
					<br>
				</p>			
	</div>															
</div>
</div>
</div>
<%@ include file="rodape.html"%>
</body>
</html>