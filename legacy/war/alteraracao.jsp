<%@page import="java.text.SimpleDateFormat"%>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="jquery/jquery-1.6.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="usuario.jsp"%>
<%@ include file="estilo.html"%>
<script src="JavaScript/menu.js"></script>

<title>Ver Ra&ccedil;&atilde;o</title>
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
					<li><a href="visualizaracao.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	


		
<div id="main">				
	<div class="post">
						<%   						
						EntityManager em = EMF.get().createEntityManager();
						
						Key id = KeyFactory.stringToKey(request.getParameter("idRacao"));
						Racao varRacao = em.find(Racao.class, id);
						
						Query q = em.createQuery("SELECT o FROM RacaoInsumo AS o WHERE o.racao = :racao").setParameter("racao", varRacao.getId());
						List<RacaoInsumo> listaInsumo = q.getResultList();
						
						Query qMetodo = em.createQuery("SELECT o FROM Metodo AS o WHERE o.racao = :racao").setParameter("racao", varRacao.getId());
						List<Metodo> listaMetodo = qMetodo.getResultList();
						
						Query qInsumo = em.createQuery("SELECT o FROM Insumo AS o WHERE o.cliente = :cliente").setParameter("cliente", usuario.getId());
						List<Insumo> listaInsumoSelect = qInsumo.getResultList();
						
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
			conteudo += "<td class='first'><select required name='idInsumo"+ cont +"'><% for(Insumo varInsumo: listaInsumoSelect){ %><option value='<%=KeyFactory.keyToString(varInsumo.getId())%>'><%=varInsumo.getNomeInsumo()%></option><%} %></select></td>";
			conteudo += "<td><input required type='text' size='20' maxlength='15' style="text-align: right;" name='qtdInsumo" + cont + "' /></td>";
			conteudo += "<td></td>";
			conteudo += "</tr>";
			jQuery("tr.row-b:last").after(conteudo);
		});
	});
</script>
<!-- -----------------------------------------------FIM ADD LINHA---------------------------------------------------------------------  -->						
						
		<h1>Ra&ccedil;&atilde;o Cadastrada</h1>

	<form id="milho" action="../ServletRacao" method="get">				
		<ul>				
				<input type="hidden" name="acao" value="Atualizar"/>
				<input type="hidden" name="idRacao" value="<%= request.getParameter("idRacao")%>" />																			
				<code>
					Ra&ccedil;a do Gado: <select name="idGado" style="float: right;">
											<% for(Gado varGado: listaGado){ %>
												<option selected="selected" value="<%=KeyFactory.keyToString(varRacao.getGado())%>"> <%=varGado.getNomeGado()%></option>
											<%} %>
										 </select>  
									<!--  <input type="text" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%Gado gado = em.find(Gado.class, varRacao.getGado().getId()); out.print(gado.getNomeGado()); %>" /> -->
					<br />
					<br />	
					Regi&atilde;o de manejo: <select name="idRegiao" style="float: right;">
												<% for(Regiao varRegiao: listaRegiao){ %>
													<option selected="selected" value="<%=KeyFactory.keyToString(varRacao.getRegiao())%>"><%=varRegiao.getNomeRegiao()%></option>
												<%} %>
											</select>				
					<br />
					<br />
					Taxa de Rendimento em Kg: <select name="taxaRendimento" style="float: right;">
													<option value="abaixo de 1.0">abaixo de 1.0</option>
													<option value="1.0 a 1.5">1.0 a 1.5</option>
													<option value="1.5 a 2.0">1.5 a 2.0</option>
													<option value="2.0 a 2.5">2.0 a 2.5</option>
													<option value="2.5 a 3.0">2.5 a 3.0</option>
													<option value="3.0 a 3.5">3.0 a 3.5</option>
													<option value="3.5 a 4.0">3.5 a 4.0</option>
													<option value="4.0 a 4.5">4.0 a 4.5</option>
													<option value="4.5 a 5.0">4.5 a 5.0</option>
													<option value="superior">Superior a 5.0</option>
											  </select>		
					<br />
					<br />
					Tipo de manejo: <select name="idManejo" style="float: right;">
										<% for(Manejo varManejo: listaManejo){ %>
											<option selected="selected" value="<%=KeyFactory.keyToString(varRacao.getManejo())%>"><%=varManejo.getNomeManejo()%></option>
										<%} %>
									</select> 		
					<br />
					<br />
																			<%SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");%>
					Data: <input type="text"  name="data" maxlength="15" style="float: right; text-align: right;" value="<%=formataData.format(varRacao.getData())%>" />
					<br />
					<br />
					Hor&aacute;rio do manejo: <input type="text"  name="horario" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%=varRacao.getHorario()%>" />
					<br />
					<br />
					Qquantidade de cabe&ccedil;as: <input type="text" name="qtdGado" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%=varRacao.getQtdGado()%>" />
					<br />
					<br />
					Peso m&eacute;dio inicial: <input type="text" name="pesoInicial" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%=varRacao.getPesoInicial()%>" />
					<br />
					<br />
					Tempo de tratamento(dia): <input type="text" name="qtdTratamentoDia" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%=varRacao.getQtdTratamentoDia()%>" />
					<br />
					<br />
					Nome da Ra&ccedil;&atilde;o: <input type="text" name="nomeRacao" maxlength="25" style="float: right; text-align: right;" value="<%=varRacao.getNomeRacao()%>" />
					<br />
					<br />	
				</code>
				<br />
				<li>Para adicionar uma nova linha clique no ' + '</li>
				<li>Descreva passo-a-passo o modo de fabrica&ccedil;&atilde;o da ra&ccedil;&atilde;o </li>
				<br />
				<table style="float: left;">
					<tr class="row-a">
						<th class="first" style="width: 400px;"><strong>M&eacute;todo</strong></th>
						<th><input id="adicionar" type="button" value="" /></th>
					</tr>
				  <% int e=0; for(Metodo varMetodo: listaMetodo) { e++;%>
					<tr class="row-b-metodo">
					
						<td class="first"><input type="hidden" name="idMetodo<%= e%>"/><input type="text" size="10" maxlength="10" style="width: 350px;"  
						value="<% Metodo metodo = em.find(Metodo.class, varMetodo.getNomeMetodo()); varMetodo.getId();
									out.print(varMetodo.getNomeMetodo());%>"/></td>
								
					</tr>
					<% } %>	
				</table>
			<br />
			<br />
			<br />
			<br />
			<br />
			<li>Para adicionar uma nova linha clique no ' + '</li>
			<li>Fa&ccedil;a sua ra&ccedil;&atilde;o com rela&ccedil;&atilde;o a: 1.000Kg </li>
			<li>Utilize ponto no pre&ccedil;o dos insumos</li>
			<li>M&aacute;ximo 50 insumos</li>
			<br />
				<table>
					<tr class="row-a">
						<th class="first"><strong>Produtos</strong></th>
						<th>Quantidade</th>
						<th><input id="adicionar" type="button" value="" /></th>
					</tr>
					<% int i=0; for(RacaoInsumo varRacaoInsumo: listaInsumo) { i++;%>					
					<tr class="row-b">
					<input type="hidden" name="idRacaoInsumo<%= i%>" value="<%= KeyFactory.keyToString(varRacaoInsumo.getId()) %>" />
							<td class="first">
								<select name="idInsumo<%= i%>">
									<% for(Insumo varInsumoSelect: listaInsumoSelect){ %>							
										<option <%=(varRacaoInsumo.getInsumo().getId() == varInsumoSelect.getId().getId())?"selected=selected":"" %> value="<%=KeyFactory.keyToString(varInsumoSelect.getId())%>"><%=varInsumoSelect.getNomeInsumo()%></option>
									<%}%>
								</select>
							</td>											
							<td><input type="text" size="20" maxlength="15" style="text-align: right;" name="qtdInsumo<%= i%>" value="<%=varRacaoInsumo.getQtdInsumo()%>"/></td>	
							<td></td>
					</tr>	
					<%}%>													
				</table>
				<input type="submit" name="enviar" value="" title="Salvar" />		
		</ul>				
	</form>									
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