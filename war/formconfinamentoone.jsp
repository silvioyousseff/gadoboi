<%@page import="br.com.gadoboi.bean.ConfinamentoOne"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<title>Resultado Milho</title>
</head>
<body>
<%@ include file="cabecalho.html"%>
<%@ include file="usuario.jsp"%>
<% ConfinamentoOne cfm = new ConfinamentoOne();
	cfm.setPrecoCalCalcitico(Double.valueOf(request.getParameter("PrecoCal")));
	cfm.setPrecoFareloSoja(Double.valueOf(request.getParameter("PrecoSoja")));
	cfm.setPrecoIonoforo(Double.valueOf(request.getParameter("PrecoIonoforo")));
	cfm.setPrecoMilho(Double.valueOf(request.getParameter("PrecoMilho")));
	cfm.setPrecoMisturaMineral(Double.valueOf(request.getParameter("PrecoMistura")));
	cfm.setPrecoSulfatoAmonio(Double.valueOf(request.getParameter("PrecoAmonio")));
	cfm.setPrecoUreia(Double.valueOf(request.getParameter("PrecoUreia")));
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
				
				<h1>Concentrado I</h1>
				
				<ul>																											
		<table>
				<tr>
					<th class="first"><strong>Produtos</strong></th>
					<th>Quantidade</th>
					<th>Pre&ccedil;o</th>				
				</tr>
				<tr class="row-a">
					<td class="first">Milho</td>
					<td><%=cfm.getQtdMilho()%> Kg</td>
					<td>R$ <%=cfm.getPrecoMilho()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Farelo Soja</td>
					<td><%=cfm.getQtdFareloSoja()%> Kg</td>
					<td>R$ <%=cfm.getPrecoFareloSoja()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Ur&eacute;ia</td>
					<td><%=cfm.getQtdUreia()%> Kg</td>
					<td>R$ <%=cfm.getPrecoUreia()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Am&ocirc;nio</td>
					<td><%=cfm.getQtdSultafoAmonio()%> Kg</td>
					<td>R$ <%=cfm.getPrecoSulfatoAmonio()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Mistura Mineral</td>
					<td><%=cfm.getQtdMisturaMineral()%> Kg</td>
					<td>R$ <%=cfm.getPrecoMisturaMineral()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">Ion&oacute;foro</td>
					<td><%=cfm.getQtdIonoforo()%> Kg</td>
					<td>R$ <%=cfm.getPrecoIonoforo()%></td>
				</tr>
				<tr class="row-a">
					<td class="first">Cal. Calcitico</td>
					<td><%=cfm.getQtdCalCalcitico()%> Kg</td>
					<td>R$ <%=cfm.getPrecoCalCalcitico()%></td>
				</tr>
				<tr class="row-b">
					<td class="first">TOTAL</td>
					<td>-</td>
					<td>R$ <%=cfm.calculaTotal() %></td>
				</tr>
					<!--  <input type="hidden" name="qtdMilho" value="<%=cfm.getQtdMilho()%>"><%=cfm.getQtdMilho()%>Kg-->
				
				
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