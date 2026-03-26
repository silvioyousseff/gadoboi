<%@page import="br.com.gadoboi.bean.ProteinatoChuva"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<title>Resultado Proteinato Chuva</title>
</head>
<body>
<%@ include file="cabecalho.html"%>
<%@ include file="usuario.jsp"%>
<% ProteinatoChuva protc = new ProteinatoChuva();	
	protc.setPrecoFareloSoja(Double.valueOf(request.getParameter("PrecoSoja")));
	protc.setPrecoMilho(Double.valueOf(request.getParameter("PrecoMilho")));
	protc.setPrecoMisturaMineral(Double.valueOf(request.getParameter("PrecoMistura")));
	protc.setPrecoSulfatoAmonio(Double.valueOf(request.getParameter("PrecoAmonio")));
	protc.setPrecoUreia(Double.valueOf(request.getParameter("PrecoUreia")));
	protc.setPrecoSalBranco(Double.valueOf(request.getParameter("PrecoSal")));
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
				
				<h1>Proteinato Chuva</h1>
				
				<ul>																											
		<table>
				<tr>
					<th class="first"><strong>Produtos</strong></th>
					<th>Quantidade</th>
					<th>Pre&ccedil;o</th>				
				</tr>
				<tr class="row-a">
					<td class="first">Milho</td>
					<td><%=protc.getQtdMilho()%> Kg</td>
					<td>R$ <%=protc.getPrecoMilho()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Farelo Soja</td>
					<td><%=protc.getQtdFareloSoja()%> Kg</td>
					<td>R$ <%=protc.getPrecoFareloSoja()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Ur&eacute;ia</td>
					<td><%=protc.getQtdUreia()%> Kg</td>
					<td>R$ <%=protc.getPrecoUreia()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Am&ocirc;nio</td>
					<td><%=protc.getPrecoSulfatoAmonio()%> Kg</td>
					<td>R$ <%=protc.getPrecoSulfatoAmonio()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Mistura Mineral</td>
					<td><%=protc.getQtdMisturaMineral()%> Kg</td>
					<td>R$ <%=protc.getPrecoMisturaMineral()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Sal Branco</td>
					<td><%=protc.getQtdSalBranco()%> Kg</td>
					<td>R$ <%=protc.getPrecoSalBranco()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">TOTAL</td>
					<td>-</td>
					<td>R$ <%=protc.calculaTotal() %></td>
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