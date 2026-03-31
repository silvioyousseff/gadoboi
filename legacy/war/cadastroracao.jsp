<%@page import="br.com.gadoboi.bean.RacaoInsumo"%>
<%@page import="br.com.gadoboi.bean.Cliente"%>
<%@page import="br.com.gadoboi.bean.Regiao"%>
<%@page import="br.com.gadoboi.bean.Manejo"%>
<%@page import="br.com.gadoboi.bean.Metodo"%>
<%@page import="br.com.gadoboi.bean.Insumo"%>
<%@page import="br.com.gadoboi.bean.Gado"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="br.com.gadoboi.bean.Racao"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="br.com.gadoboi.dao.EMF"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="jquery/jquery-1.6.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="usuario.jsp"%>
<%@ include file="estilo.html"%>
<script src="JavaScript/menu.js"></script>
<title>Cadastro Ra&ccedil;&atilde;o</title>
</head>
<body>
<%@ include file="cabecalho.html"%>				
	<!-- content-wrap starts here -->
	<div id="content-wrap">

		<div id="content">		
		
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
					<li><a href="menu.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	


		
<div id="main">				
	<div class="post">
						<%
						
						EntityManager em = EMF.get().createEntityManager();
						
							
							Query qInsumo = em.createQuery("SELECT o FROM Insumo AS o WHERE o.cliente = :cliente").setParameter("cliente", usuario.getId());
							List<Insumo> listaInsumo = qInsumo.getResultList();
							
							Query qGado = em.createQuery("SELECT o FROM Gado AS o WHERE o.cliente = :cliente").setParameter("cliente", usuario.getId());
							List<Gado> listaGado = qGado.getResultList();
							
							Query qManejo = em.createQuery("SELECT o FROM Manejo AS o WHERE o.cliente = :cliente").setParameter("cliente", usuario.getId());
							List<Manejo> listaManejo = qManejo.getResultList();
							
							Query qRegiao = em.createQuery("SELECT o FROM Regiao AS o WHERE o.cliente = :cliente").setParameter("cliente", usuario.getId());
							List<Regiao> listaRegiao = qRegiao.getResultList();
						%>
<!-- ----------------------------------------------ADD LINHA----------------------------------------------------------------------  -->
<script type="text/javascript">
	jQuery(document).ready(function() {
		var contMetodo = 1;
		jQuery("#adicionarMetodo").click(function() {
			cont++;
			var conteudo = "<tr class='row-b-metodo'>";
			conteudo += "<td><input type='hidden' name='idMetodo"+ contMetodo +"'/><input required type='text' size='45' maxlength='40' name='nomeMetodo" + contMetodo + "' /></td>";
			conteudo += "</tr>";
			jQuery("tr.row-b-metodo:last").after(conteudo);
		});
	});
</script>
<!-- -----------------------------------------------FIM ADD LINHA---------------------------------------------------------------------  -->						
<!-- ----------------------------------------------ADD LINHA----------------------------------------------------------------------  -->
<script type="text/javascript">
	jQuery(document).ready(function() {
		var cont = 1;
		jQuery("#adicionar").click(function() {
			cont++;
			var conteudo = "<tr class='row-b'>";
			conteudo += "<td class='first'><select required name='idInsumo"+ cont +"'><% for(Insumo varInsumo: listaInsumo){ %><option value='<%=KeyFactory.keyToString(varInsumo.getId())%>'><%=varInsumo.getNomeInsumo()%></option><%} %></select></td>";
			conteudo += "<td style='width: 200px;'><input required type='text' size='20' maxlength='15' name='qtdInsumo" + cont + "' /></td>";
			conteudo += "<td></td>";
			conteudo += "</tr>";
			jQuery("tr.row-b:last").after(conteudo);
		});
	});
</script>
<!-- -----------------------------------------------FIM ADD LINHA---------------------------------------------------------------------  -->						
		<h1>Cadastro Ra&ccedil;&atilde;o</h1>
				
		<ul>
				<li>Preencha os campos abaixo para um melhor entendimento da ra&ccedil;&atilde;o desejada.</li>
				<li>Esta Ra&ccedil;&atilde;o ser&aacute; cadastrada automaticamente
					em suas ra&ccedil;&otilde;es,
					para cadastra-la para venda selecione a op&ccedil;&atilde;o. <label>"Venda"</label></li>
																							
			<form id="cadastroRacao" action="../ServletRacao" method="get">
				
				<input type="hidden" name="acao" value="Inserir"/>
				<code>
					<strong>Ra&ccedil;&atilde;o para Venda?</strong>
					SIM <input required type="checkbox" name="status" value="V" /> / N&AtildeO <input type="checkbox" name="" value="" />
					<br />
					<br />
				 	Ra&ccedil;a do Gado: <select name="idGado" style="float: right;">
											<% for(Gado varGado: listaGado){ %>
												<option required value="<%=KeyFactory.keyToString(varGado.getId())%>"><%=varGado.getNomeGado()%></option>
											<%} %>
										 </select> 
					<br /><br />
											
					Regi&atilde;o de manejo: <select name="idRegiao" style="float: right;">
												<% for(Regiao varRegiao: listaRegiao){ %>
													<option required value="<%=KeyFactory.keyToString(varRegiao.getId())%>"><%=varRegiao.getNomeRegiao()%></option>
												<%} %>
											</select> 
					<br /><br />
											
					Taxa de Rendimento em Kg: <select required name="taxaRendimento" style="float: right;">
													<option value="abaixo de 1.0">abaixo de 1.0</option>
													<option value="1.0 a 1.5">1.0 a 1.5</option>
													<option value="1.5 a 2.0">1.5 a 2.0</option>
													<option value="2.0 a 2.5">2.0 a 2.5</option>
													<option value="2.5 a 3.0">2.5 a 3.0</option>
													<option value="3.0 a 3.5">3.0 a 3.5</option>
													<option value="3.5 a 4.0">3.5 a 4.0</option>
													<option value="4.0 a 4.5">4.0 a 4.5</option>
													<option value="4.5 a 5.0">4.5 a 5.0</option>
													<option value="superior a 5.0">Superior a 5.0</option>
											  </select> 
					<br /><br />
					 						
					Tipo de manejo: <select name="idManejo" style="float: right;">
										<% for(Manejo varManejo: listaManejo){ %>
											<option required value="<%=KeyFactory.keyToString(varManejo.getId())%>"><%=varManejo.getNomeManejo()%></option>
										<%} %>
									</select> 
					<br /><br />

					Data: <input type="text" name="data" maxlength="10" size="10" style="float: right;" onKeyUp="mascaraTexto(event,'99/99/9999')" required />
					<br />
					<br />
					Hor&aacute;rio do manejo: <input type="text" name="horario" maxlength="5" size="10" style="float: right;" onKeyUp="mascaraTexto(event,'99:99:99')" required />
					<br />
					<br />			
					Quantidade de cabe&ccedil;as: <input type="text" name="qtdGado" maxlength="15" size="10" style="float: right;" required />
					<br />
					<br />
					Peso m&eacute;dio inicial: <input type="text" name="pesoInicial" maxlength="15" size="10" style="float: right;" required />
					<br />
					<br />
					Tempo de tratamento(dia): <input type="text" name="qtdTratamentoDia" maxlength="15" size="10" style="float: right;" required />
					<br />
					<br />
					Nome da Ra&ccedil;&atilde;o: <input type="text" name="nomeRacao" maxlength="25" style="float: right;" required />
					<br />
					<br />	
				</code>
			<br />
			<li>Para adicionar uma nova linha clique no ' + '</li>
			<li>Descreva passo-a-passo o modo de fabrica&ccedil;&atilde;o da ra&ccedil;&atilde;o </li>
			<br />
				<table style="float: left;">
					<tr class="row-a">
						<th class="first"><strong>M&eacute;todo</strong></th>
						<th><input id="adicionarMetodo" type="button" value="" /></th>
					</tr>
					<tr class="row-b-metodo">
						<td class="first"><input type="hidden" name="idMetodo1"/><input type="text" size="45" maxlength="40" name="nomeMetodo1" required /></td>					
					</tr>
				</table>
			<br />
			<br />
			<br />
			<br />
			<li>Para adicionar uma nova linha clique no ' + '</li>
			<li>Fa&ccedil;a sua ra&ccedil;&atilde;o com rela&ccedil;&atilde;o a: 1.000Kg </li>
			<li>Utilize ponto no pre&ccedil;o dos insumos</li>
			<li>M&aacute;ximo 50 insumos</li>
			<br />
				<table style="float: left;">
					<tr class="row-a">
						<th class="first"><strong>Produtos</strong></th>
						<th>Quantidade - Kg</th>
						<th><input id="adicionar" type="button" value="" /></th>
					</tr>
					<tr class="row-b">
						<td class="first" style="width: 200px;">
							<select name="idInsumo1"">
								<% for(Insumo varInsumo: listaInsumo){ %>
									<option required value="<%=KeyFactory.keyToString(varInsumo.getId())%>"><%=varInsumo.getNomeInsumo()%></option>
								<%} %>
							</select>
						</td>
						<td style="width: 200px;"><input type="text" size="20" maxlength="15" name="qtdInsumo1" required /></td>
						<td></td>
					</tr>
					
				</table>
				<br />
				<br />
				<input type="submit" title="Cadastrar" name="botao" value=""/>
			</form>					
		</ul>										
				<p class="post-footer align-right" >					
					<br>
				</p>			
	</div>															
</div>
</div>
</div>
<%@ include file="rodape.html"%>
</body>
</html>