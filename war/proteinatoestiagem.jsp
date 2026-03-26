<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<title>Proteinato  Estiagem</title>
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
					<li><a href="rendimento.jsp">Rendimento</a></li>
					<li><a href="racao.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	


		
<div id="main">				
	<div class="post">
		
		<h1>Proteinato Estiagem</h1>
				
		<ul>																														
			<form id="milho" action="formproteinatoestiagem.jsp" method="get">
				<table>
					<tr>
						<th class="first"><strong>Produtos</strong></th>
						<th>Quantidade</th>
						<th>Informe o Pre&ccedil;o</th>				
					</tr>
					<tr class="row-a">
						<td class="first">Milho</td>
						<td>22 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoMilho" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Farelo Soja</td>
						<td>28 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoSoja" /></td>
					</tr>
					<tr class="row-a">
						<td class="first">Ur&eacute;ia</td>
						<td>12.8 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoUreia" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Am&ocirc;nio</td>
						<td>2.2 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoAmonio" /></td>
					</tr>
					<tr class="row-a">
						<td class="first">Mistura Mineral</td>
						<td>15 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoMistura" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Sal Branco</td>
						<td>20 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoSal" /></td>
					</tr>
					<tr class="row-b">
						<td class="first"></td>
						<td>100 Kg</td>
						<td><input type="submit" name="enviar" value="" title="Calcular" /></td>
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