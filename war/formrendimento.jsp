<%@page import="br.com.gadoboi.bean.Rendimento"%>
<%@page import="br.com.gadoboi.servlet.ServletRendimento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<title>Rendimento</title>
</head>
<body>
<%@ include file="cabecalho.html"%>	
<%@ include file="usuario.jsp"%>
<% Rendimento rend = new Rendimento();
	rend.setQtdAnimais(Double.valueOf(request.getParameter("qtdAnimais")));
	rend.setPorcentagemPorPeso(Double.valueOf(request.getParameter("porPeso")));
	rend.setTamanhoDaPastagem(Double.valueOf(request.getParameter("tamanhoHa")));
	rend.setPesoInicial(Double.valueOf(request.getParameter("pesoInicial")));
	rend.setGanhoDePesoEsperado(Double.valueOf(request.getParameter("ganhoPesoDia")));
	rend.setPrecoPorQuiloCon(Double.valueOf(request.getParameter("precoKgCon")));
	rend.setPrecoArroba(Double.valueOf(request.getParameter("precoArroba")));
	rend.setRendimentoCarcaca(Double.valueOf(request.getParameter("rendimentoCarcaca")));
	rend.setPeriodoDeTratamento(Double.valueOf(request.getParameter("qtdDias")));
	rend.calcular();
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
					<li><a href="racao.html">Cálculo para Ra&ccedil;&atilde;o</a></li>
					<li><a href="extras.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	


		
<div id="main">				
	<div class="post">
		
		<h1>Resultado Rendimento</h1>
				
		<ul>																														
			<form id="rendimento" action="../ServletRendimento" method="get">
			<input type="hidden" name="acao" value="Inserir">
				<table>
					<tr>
						<th class="first"><strong>Perguntas</strong></th>
						<th>Respostas</th>				
					</tr>
					<tr class="row-a">
						<td class="first">Quantidade de Animais</td>
						<td><input type="text" size="10" maxlength="10" name="qtdAnimais" readonly="readonly" value="<%=rend.getQtdAnimais()%>"/></td>
					</tr>
					<tr class="row-b">
						<td class="first">Porcentagem(%) peso vivo de concentrado (1 a 1.5):</td>
						<td><input type="text" size="10" maxlength="10" name="porPeso" readonly="readonly" value="<%=rend.getPorcentagemPorPeso()%>"/></td>
					</tr>
					<tr class="row-a">
						<td class="first">Quantidade do tratamento em Dias:</td>
						<td><input type="text" size="10" maxlength="10" name="qtdDias" readonly="readonly" value="<%=rend.getPeriodoDeTratamento()%>"/></td>
					</tr>
					<tr class="row-b">
						<td class="first">Tamanho de sua pastagem em Hectares:</td>
						<td><input type="text" size="10" maxlength="10" name="tamanhoHa" readonly="readonly" value="<%=rend.getTamanhoDaPastagem()%>"/></td>
					</tr>				
					<tr class="row-a">
						<td class="first">Peso medio inicial em 'Kg' de seu Animal:</td>
						<td><input type="text" size="10" maxlength="10" name="pesoInicial" readonly="readonly" value="<%=rend.getPesoInicial()%>"/></td>
					</tr>
					<tr class="row-b">
						<td class="first">Pre&ccedil;o por Kg de concentrado :</td>
						<td><input type="text" size="10" maxlength="10" name="precoKgCon" readonly="readonly" value="<%=rend.getPrecoPorQuiloCon()%>"/></td>
					</tr>
					<tr class="row-a">
						<td class="first">Pre&ccedil;o da Arroba ( @ ) para venda :</td>
						<td><input type="text" size="10" maxlength="10" name="precoArroba" readonly="readonly" value="<%=rend.getPrecoArroba()%>"/></td>
					</tr>
					<tr class="row-a">
						<td class="first">Ganho de peso esperado em 'Kg' por dia :</td>
						<td><input type="text" size="10" maxlength="10" name="ganhoPesoDia" readonly="readonly" value="<%=rend.getGanhoDePesoEsperado()%>"/></td>
					</tr>
					<tr class="row-b">
						<td class="first">Rendimento da carca&ccedil;a esperado em ' Por&ccedil;entagem % ' :</td>
						<td><input type="text" size="10" maxlength="10" name="rendimentoCarcaca" readonly="readonly" value="<%=rend.getRendimentoCarcaca()%>"/></td>
					</tr>
					<tr class="row-a">
						<td class="first"></td>
						<td></td>
					</tr>
					<tr class="row-b">
						<td class="first">Taxa de Lotacao:</td>
						<td><input type="text" size="10" maxlength="10" name="taxaLotacao" readonly="readonly" value="<%=rend.getTaxaLotacao()%>"/></td>
					</tr>
					<tr class="row-a">
						<td class="first">Peso m&eacute;dio em '@' Arroba:</td>
						<td><input type="text" size="10" maxlength="10" name="pesoMedioArroba" readonly="readonly" value="<%=rend.getPesoMedioEmArroba()%>"/></td>
					</tr>
					<tr class="row-b">
						<td class="first">Quantidade de concentrado diario por cabe&ccedil;a:</td>
						<td><input type="text" size="10" maxlength="10" name="qtdConCabecaDia" readonly="readonly" value="<%=rend.getQtdConcentradoDiarioCabeca()%>"/></td>
					</tr>
					<tr class="row-a">
						<td class="first">Quantidade de concentrado diario:</td>
						<td><input type="text" size="10" maxlength="10" name="qtdConDia" readonly="readonly" value="<%=rend.getQtdConcentradoDiariamente()%>"/></td>
					</tr>
					<tr class="row-b">
						<td class="first">Quantidade de concentrado Total</td>
						<td><input type="text" size="10" maxlength="10" name="qtdConTotal" readonly="readonly" value="<%=rend.getQtdConcentradoTotal()%>"/></td>
					</tr>
																				
					<tr class="row-b">
						<td class="first">Pre&ccedil;o por animal dia de concentrado:</td>
						<td><input type="text" size="10" maxlength="10" name="precoConDiaAnimal" readonly="readonly" value="<%=rend.getPrecoConcentradoDiaAnimal()%>"/></td>
					</tr>
					<tr class="row-a">
						<td class="first">Ganho por cabe&ccedil;a dia:</td>
						<td><input type="text" size="10" maxlength="10" name="ganhoCabecaDia" readonly="readonly" value="<%=rend.getGanhoPorCabecaDia()%>"/></td>
					</tr>
					<tr class="row-b">
						<td class="first">Custo total do tratamento:</td>
						<td><input type="text" size="10" maxlength="10" name="custoTotal" readonly="readonly" value="<%=rend.getCustoTotal()%>"/></td>
					</tr>
					<tr class="row-a">
						<td class="first">Ganho do rebanho no peridodo:</td>
						<td><input type="text" size="10" maxlength="10" name="ganhoTotal" readonly="readonly" value="<%=rend.getGanhoTotal()%>"/></td>
					</tr>
					<tr class="row-b">
						<td class="first">Total:</td>
						<td><input type="text" size="10" maxlength="10" name="resultado" readonly="readonly" value="<%=rend.getResultado()%>"/></td>
					</tr>
					<tr class="row-a">
						<!--  <td><input type="submit" name="enviar" value="" /></td>-->
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