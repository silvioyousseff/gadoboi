<%@page import="br.com.gadoboi.bean.Racao"%>
<%@page import="br.com.gadoboi.bean.Manejo"%>
<%@page import="br.com.gadoboi.bean.Regiao"%>
<%@page import="br.com.gadoboi.bean.Insumo"%>
<%@page import="br.com.gadoboi.bean.Gado"%>
<%@page import="javax.persistence.Query"%>
<%@page import="java.util.List"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="br.com.gadoboi.dao.EMF"%>
<%@page import="br.com.gadoboi.bean.Cliente"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="estilo.html"%>
<%@ include file="usuario.jsp"%>
<title>Visualiza - Ra&ccedil;&atilde;o</title>
</head>
<body>
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
					<li><a href="menu.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	
	
					<%
						/* conexao com o banco */
						EntityManager em = EMF.get().createEntityManager();
						Query qRacao = em.createQuery("SELECT o FROM Racao AS o WHERE o.cliente = :cliente").setParameter("cliente", usuario.getId());
						List<Racao> varRacao = qRacao.getResultList();
					%>

		
<div id="main">				
	<div class="post">
		
		<h1>Ra&ccedil;&atilde;o</h1>
				
		<ul>	
		<p>Lista de todas as ra&ccedil;&otilde;es j&aacute; cadastradas</p>
		<p>Clique sobre a ra&ccedil;&atilde;o para visualiz&aacute;-la</p>																																																					
			<table>
							<tr>
								<th class="first"><strong>Nome item</strong></th>
								<th style="width: 55px;"><p class="buttonSalva" style="width: 10px;"><a title="Cadastrar" href="cadastroracao.jsp"></a></p></th>
							</tr>
								<%
									int a = 0;
									for (Racao chamaRacao : varRacao) {
								%>
									<tr class="row-<%=(a % 2 == 0) ? "a" : "b" %>">
										<td class="first" onclick="location.href='racaocadastrada.jsp?idRacao=<%=KeyFactory.keyToString(chamaRacao.getId()) %>'"><%=chamaRacao.getNomeRacao()%></td>
										<td><p class="buttonAlt"><a title="Alterar" href="alteraracao.jsp?idRacao=<%=KeyFactory.keyToString(chamaRacao.getId()) %>"></a></p></td>
										<td><p class="buttonDel"><a title="Apagar" href="../ServletRacao?acao=Remover&idRacao=<%= KeyFactory.keyToString(chamaRacao.getId())%>" title="Apagar"></a></p></td>
									</tr>
								<%
										a++;
									}
								%>		
			</table>			
		</ul>
		<%
			em.close();
		%>										
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