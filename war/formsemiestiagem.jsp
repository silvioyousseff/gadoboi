<%@page import="br.com.gadoboi.bean.SemiConfinamentoEstiagem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<title>Resultado Semi-Confinamento</title>
</head>
<body>
<%@ include file="cabecalho.html"%>
<%@ include file="usuario.jsp"%>
<% SemiConfinamentoEstiagem semie = new SemiConfinamentoEstiagem();	
	semie.setPrecoCalCalcitico(Double.valueOf(request.getParameter("PrecoCal")));
	semie.setPrecoFareloSoja(Double.valueOf(request.getParameter("PrecoSoja")));
	semie.setPrecoIonoforo(Double.valueOf(request.getParameter("PrecoIonoforo")));
	semie.setPrecoMilho(Double.valueOf(request.getParameter("PrecoMilho")));
	semie.setPrecoMisturaMineral(Double.valueOf(request.getParameter("PrecoMistura")));
	semie.setPrecoSulfatoAmonio(Double.valueOf(request.getParameter("PrecoAmonio")));
	semie.setPrecoUreia(Double.valueOf(request.getParameter("PrecoUreia")));
	%>
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

				<ul class="sidemenu">
					<li><a href="rendimento.jsp">Rendimento</a></li>
					<li><a href="racao.jsp">Voltar</a></li>								
				</ul>
			</ul>					
		</div>																			
	</div>	
		<div id="main">		
		
			<div class="post">
				
				<h1>Semi Confinamento Estiagem</h1>
				
				<ul>																											
		<table>
				<tr>
					<th class="first"><strong>Produtos</strong></th>
					<th>Quantidade</th>
					<th>Pre&ccedil;o</th>				
				</tr>
				<tr class="row-a">
					<td class="first">Milho</td>
					<td><%=semie.getQtdMilho()%> Kg</td>
					<td>R$ <%=semie.getPrecoMilho()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Farelo Soja</td>
					<td><%=semie.getQtdFareloSoja()%> Kg</td>
					<td>R$ <%=semie.getPrecoFareloSoja()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Ur&eacute;ia</td>
					<td><%=semie.getQtdUreia()%> Kg</td>
					<td>R$ <%=semie.getPrecoUreia()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Am&ocirc;nio</td>
					<td><%=semie.getQtdSultafoAmonio()%> Kg</td>
					<td>R$ <%=semie.getPrecoSulfatoAmonio()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Mistura Mineral</td>
					<td><%=semie.getQtdMisturaMineral()%> Kg</td>
					<td>R$ <%=semie.getPrecoMisturaMineral()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Ion&oacute;foro</td>
					<td><%=semie.getQtdIonoforo()%> Kg</td>
					<td>R$ <%=semie.getPrecoIonoforo()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Cal. Calcitico</td>
					<td><%=semie.getQtdCalCalcitico()%> Kg</td>
					<td>R$ <%=semie.getPrecoCalCalcitico()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">TOTAL</td>
					<td>-</td>
					<td>R$ <%=semie.calculaTotal() %></td>
				</tr>
					
				
				
		</table>			
					
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
</body>
</html>