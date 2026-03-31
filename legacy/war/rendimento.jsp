<%@page import="javax.persistence.Query"%>
<%@page import="br.com.gadoboi.dao.EMF"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<%@ include file="usuario.jsp"%>
<title>Rendimento</title>
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
					<li><a href="racao.jsp">C&aacute;lculo para Ra&ccedil;&atilde;o</a></li>
					<li><a href="extras.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	


		
<div id="main">				
	<div class="post">
		
		<h1>Rendimento</h1>
				
		<ul>																														
			<form id="milho" action="formrendimento.jsp" method="get">
				<input type="hidden" name="acao" value="Inserir">
				<table>
					<tr>
						<th class="first"><strong>Perguntas</strong></th>
						<th>Informe o Pedido</th>				
					</tr>
					<tr class="row-a">
						<td class="first">Quantidade de animais:</td>
						<td><input required type="text" size="10" maxlength="10" name="qtdAnimais" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Porcentagem (%) peso vivo de concentrado (1 a 1.5):</td>
						<td><input required type="text" size="10" maxlength="10" name="porPeso" /></td>
					</tr>
					<tr class="row-a">
						<td class="first">Quantidade do tratamento em dias:</td>
						<td><input required type="text" size="10" maxlength="10" name="qtdDias" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Tamanho de sua pastagem em hectares:</td>
						<td><input required type="text" size="10" maxlength="10" name="tamanhoHa" /></td>
					</tr>					
					<tr class="row-a">
						<td class="first">Peso m&eacute;dio inicial em (Kg) de seu animal:</td>
						<td><input required type="text" size="10" maxlength="10" name="pesoInicial" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Ganho de peso esperado em (Kg) por dia :</td>
						<td><input required type="text" size="10" maxlength="10" name="ganhoPesoDia" /></td>
					</tr>
					<tr class="row-a">
						<td class="first">Pre&ccedil;o por (Kg) de concentrado :</td>
						<td><input required type="text" size="10" maxlength="10" name="precoKgCon" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Pre&ccedil;o da Arroba ( @ ) para venda :</td>
						<td><input required type="text" size="10" maxlength="10" name="precoArroba" /></td>
					</tr>
					<tr class="row-a">
						<td class="first">Rendimento da carca&ccedil;a esperado em (%) :</td>
						<td><input required type="text" size="10" maxlength="10" name="rendimentoCarcaca" /></td>
					</tr>
					<tr class="row-b">
						<td class="first"></td>
						<td><input type="submit" name="enviar" title="Calcular "value="" /></td>
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