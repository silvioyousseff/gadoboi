<%@page import="br.com.gadoboi.bean.ProteinatoEstiagem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<title>Resultado Proteinato Estiagem</title>
</head>
<body>
<%@ include file="cabecalho.html"%>
<%@ include file="usuario.jsp"%>
<% ProteinatoEstiagem prote = new ProteinatoEstiagem();	
	prote.setPrecoFareloSoja(Double.valueOf(request.getParameter("PrecoSoja")));
	prote.setPrecoMilho(Double.valueOf(request.getParameter("PrecoMilho")));
	prote.setPrecoMisturaMineral(Double.valueOf(request.getParameter("PrecoMistura")));
	prote.setPrecoSulfatoAmonio(Double.valueOf(request.getParameter("PrecoAmonio")));
	prote.setPrecoUreia(Double.valueOf(request.getParameter("PrecoUreia")));
	prote.setPrecoSalBranco(Double.valueOf(request.getParameter("PrecoSal")));
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
				
				<h1>Proteinato Estiagem</h1>
				
				<ul>																											
		<table>
				<tr>
					<th class="first"><strong>Produtos</strong></th>
					<th>Quantidade</th>
					<th>Pre&ccedil;o</th>				
				</tr>
				<tr class="row-a">
					<td class="first">Milho</td>
					<td><%=prote.getQtdMilho()%> Kg</td>
					<td>R$ <%=prote.getPrecoMilho()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Farelo Soja</td>
					<td><%=prote.getQtdFareloSoja()%> Kg</td>
					<td>R$ <%=prote.getPrecoFareloSoja()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Ur&eacute;ia</td>
					<td><%=prote.getQtdUreia()%> Kg</td>
					<td>R$ <%=prote.getPrecoUreia()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Am&ocirc;nio</td>
					<td><%=prote.getQtdSultafoAmonio()%> Kg</td>
					<td>R$ <%=prote.getPrecoSulfatoAmonio()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Mistura Mineral</td>
					<td><%=prote.getQtdMisturaMineral()%> Kg</td>
					<td>R$ <%=prote.getPrecoMisturaMineral()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Sal Branco</td>
					<td><%=prote.getQtdSalBranco()%> Kg</td>
					<td>R$ <%=prote.getPrecoSalBranco()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">TOTAL</td>
					<td>-</td>
					<td>R$ <%=prote.calculaTotal() %></td>
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