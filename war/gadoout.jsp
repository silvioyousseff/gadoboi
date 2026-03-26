<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="estilo.html"%>
<title>Cadastro Gado</title>
</head>
<body>
<%@ include file="cabecalho.html"%>	
<%@ include file="usuario.jsp"%>			
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
					<li><a href="insumo.jsp">Cadastrar Insumo</a></li>
					<li><a href="manejo.jsp">Cadastrar Manejo</a></li>
					<li><a href="regiao.jsp">Cadastrar Regi&atilde;o</a></li>
					<li><a href="menu.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	


		
<div id="main">				
	<div class="post">
		
		<h1>Cadastro de Ra&ccedil;a de Gado</h1>
				
		<ul>		
		<p>A primeira letra Mai&uacute;scula</p>																												
			<form id="milho" action="../ServletGado" method="get">
			<input type="hidden" name="acao" value="Inserir">
			<input type="hidden" name="usuarioout" value="">
				<table>
					<tr>
						<th class="first"><strong>Nome Ra&ccedil;a</strong></th>
						<th><p class="buttonConf"><input type="submit" name="enviar" value="" title="Cadastrar" /></p></th>			
					</tr>
					<tr class="row-a">
						<td class="first"><input type="text" size="20" maxlength="20" name="racaGado" /></td>
						<td></td>
					</tr>
				</table>
			</form>					
		</ul>										
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