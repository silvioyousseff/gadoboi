<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<title>Confinamento II</title>
</head>
<body>
<%@ include file="usuario.jsp"%>
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
					<li><a href="rendimento.jsp">Rendimento</a></li>
					<li><a href="racao.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	


		
<div id="main">				
	<div class="post">
		
		<h1>Concentrado II</h1>
				
		<ul>																														
			<form id="milho" action="formconfinamentotwo.jsp" method="get">
				<table>
					<tr>
						<th class="first"><strong>Produtos</strong></th>
						<th>Quantidade</th>
						<th>Informe o Pre&ccedil;o</th>				
					</tr>
					<tr class="row-a">
						<td class="first">Milho</td>
						<td>75.46 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoMilho" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Farelo Soja</td>
						<td>20 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoSoja" /></td>
					</tr>
					<tr class="row-a">
						<td class="first">Ur&eacute;ia</td>
						<td>1.87 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoUreia" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Am&ocirc;nio</td>
						<td>0.33 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoAmonio" /></td>
					</tr>
					<tr class="row-a">
						<td class="first">Mistura Mineral</td>
						<td>1 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoMistura" /></td>
					</tr>
					<tr class="row-b">
						<td class="first">Ion&oacute;foro</td>
						<td>0.04 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoIonoforo" /></td>
					</tr>
					<tr class="row-a">
						<td class="first">Cal. Calcitico</td>
						<td>1.30 Kg</td>
						<td><input required type="text" size="10" maxlength="10" name="PrecoCal" /></td>
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