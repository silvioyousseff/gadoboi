<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="estilo.html"%>
<title>Cadastro Insumo</title>
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
					<li><a href="manejo.jsp">Cadastrar Manejo</a></li>
					<li><a href="regiao.jsp">Cadastrar Regi&atilde;o</a></li>
					<li><a href="gado.jsp">Cadastrar Ra&ccedil;a de Gado</a></li>
					<li><a href="menu.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	


		
<div id="main">				
	<div class="post">
		
		<h1>Cadastro Insumo</h1>
				
		<ul>					
			<p>A primeira letra Mai&uacute;scula</p>																									
			<form id="milho" action="../ServletInsumo" method="get">
			<input type="hidden" name="acao" value="Inserir">
				<table>
					<tr>
						<th class="first"><strong>Nome Insumo</strong></th>
						<th><p class="buttonConf"><input type="submit" name="enviar" value="" title="Cadastrar" /></p></th>			
					</tr>
					<tr class="row-a">
						<td class="first"><input required type="text" size="20" maxlength="20" name="nomeInsumo" /></td>
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