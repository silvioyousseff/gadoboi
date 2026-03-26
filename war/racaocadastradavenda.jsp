<%@page import="br.com.gadoboi.bean.VendaRacao"%>
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
					<li><a href="vendaracao.jsp">Voltar</a></li>								
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
						
						Query f = em.createQuery("SELECT o FROM Metodo AS o WHERE o.racao = :racao").setParameter("racao", varRacao.getId());
						List<Metodo> listaMetodo = f.getResultList();
						%>
						
		<h1>Ra&ccedil;&atilde;o Cadastrada</h1>	
		
			
		<ul>		
					<li>Entre em contato com o vendedor para comprar esta ra&ccedil;&atilde;o.</li>	
					<br />																	
				<code>
					Contato para Compra:<input type="text"  readonly="readonly" name="emailCliente" maxlength="25" size="25" style="float: right; text-align: right;" value="<% Cliente cliente = em.find(Cliente.class, varRacao.getCliente().getId()); 
																																				out.print(cliente.getEmail()); %>" />																															
					<br />
					<br />
					Nome do vendedor:<input type="text"  readonly="readonly" name="emailCliente" maxlength="25" size="25" style="float: right; text-align: right;" value="<% Cliente clientenome = em.find(Cliente.class, varRacao.getCliente().getId()); 
																																				out.print(cliente.getNome()); %>" />																															
					<br />
					<br />
					<br />
					<br />
					Ra&ccedil;a do Gado:<input type="text"  readonly="readonly" name="idGado" maxlength="15" style="float: right; text-align: right;" value="<% Gado gado = em.find(Gado.class, varRacao.getGado().getId()); 
																																				out.print(gado.getNomeGado()); %>" />
																																				
					<br />
					<br />					
					Regi&atilde;o de manejo: <input type="text" readonly="readonly"  name="idRegiao" maxlength="15" style="float: right; text-align: right;" value="<% Regiao regiao = em.find(Regiao.class, varRacao.getRegiao().getId()); 
																																				out.print(regiao.getNomeRegiao()); %>" />
					<br />
					<br />		
					Taxa de Rendimento em Kg: <input type="text" readonly="readonly"  name="taxaRendimento" maxlength="15" style="float: right;  width: 100px; text-align: right;"  value="<%=varRacao.getTaxaRendimento()%>" /> 
					<br />
					<br />		
					Tipo de manejo: <input type="text" readonly="readonly"  name="idManejo" maxlength="15" style="float: right; text-align: right;" value="<% Manejo manejo = em.find(Manejo.class, varRacao.getManejo().getId()); 
																																				out.print(manejo.getNomeManejo()); %>" />
					<br />
					<br />
																			<%SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");%>
					Data: <input type="text" readonly="readonly" name="data" maxlength="15" style="float: right; text-align: right;" value="<%=formataData.format(varRacao.getData())%>" />
					<br />
					<br />
					Hor&aacute;rio do manejo: <input type="text" readonly="readonly" name="horario" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%=varRacao.getHorario()%>" />
					<br />
					<br />
					Qquantidade de cabe&ccedil;as: <input type="text" readonly="readonly" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%=varRacao.getQtdGado()%>" />
					<br />
					<br />
					Peso m&eacute;dio inicial: <input type="text" readonly="readonly" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%=varRacao.getPesoInicial()%>" />
					<br />
					<br />
					Tempo de tratamento(dia): <input type="text" readonly="readonly" maxlength="15" style="float: right; width: 100px; text-align: right;" value="<%=varRacao.getQtdTratamentoDia()%>" />
					<br />
					<br />
					Nome da Ra&ccedil;&atilde;o: <input type="text" readonly="readonly" name="nomeRacao" maxlength="15" style="float: right; text-align: right;" value="<%=varRacao.getNomeRacao()%>" />
					<br />
					<br />	
					
				</code>
				<br />
				<br />
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