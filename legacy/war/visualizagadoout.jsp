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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<%@ include file="usuario.jsp"%>
<title>Altera / Exclui - Ra&ccedil;a de Gado</title>
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
					<li><a href="alteracao.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	
	
					<%
						/* conexao com o banco */
						EntityManager em = EMF.get().createEntityManager();
						Query qGado = em.createQuery("SELECT o FROM Gado AS o WHERE o.cliente = :cliente").setParameter("Gado", usuario.getId());
						List<Gado> varGado = qGado.getResultList();
					%>

		
<div id="main">				
	<div class="post">
		
		<h1>Ra&ccedil;a de Gado</h1>
				
		<ul>	
		<p>Lista de todas as ra&ccedil;as de gado cadastradas</p>																												
			<form id="milho" action="../ServletGado" method="get">																											
			<table>
							<tr>
								<th class="first"><strong>Nome item</strong></th>
								<th><p class="buttonSalva"><a title="Cadastrar" href="gado.jsp"></a></p></th>		
							</tr>
								<%
									int a = 0;
									for (Gado chamaGado : varGado) {
								%>
									<tr class="row-<%=(a % 2 == 0) ? "a" : "b" %>">
										<td class="first"><%=chamaGado.getNomeGado()%></td>
										<td></td>
										<td><p class="buttonAlt"><a href="alteragado.jsp?idGado=<%=KeyFactory.keyToString(chamaGado.getId()) %>" title="Alterar"></a></p></td>
										<td><p class="buttonDel"><a href="../ServletGado?acao=Remover&idGado=<%= KeyFactory.keyToString(chamaGado.getId())%>" title="Apagar"></a></p></td>
									</tr>
								<%
										a++;
									}
								%>		
			</table>
		</form>					
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