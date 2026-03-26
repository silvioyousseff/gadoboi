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
<title>Altera / Exclui - Manejo</title>
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
						Query qManejo = em.createQuery("SELECT o FROM Manejo AS o WHERE o.cliente = :cliente").setParameter("cliente", usuario.getId());
						List<Manejo> varManejo = qManejo.getResultList();
					%>

		
<div id="main">				
	<div class="post">
		
		<h1>Manejo</h1>
				
		<ul>	
		<p>Lista de todas os manejos cadastrados</p>																											
			<form id="milho" action="../ServletManejo" method="get">																											
			<table>
							<tr>
								<th class="first"><strong>Nome item</strong></th>
								<th><p class="buttonSalva"><a title="Cadastrar" href="manejo.jsp"></a></p></th>
							</tr>
								<%
									int a = 0;
									for (Manejo chamaManejo : varManejo) {
								%>
									<tr class="row-<%=(a % 2 == 0) ? "a" : "b" %>">
										<td class="first"><%=chamaManejo.getNomeManejo()%></td>
										<td></td>
										<td><p class="buttonAlt"><a title="Alterar" href="alteramanejo.jsp?idManejo=<%=KeyFactory.keyToString(chamaManejo.getId()) %>"></a></p></td>
										<td><p class="buttonDel"><a title="Apagar" href="../ServletManejo?acao=Remover&idManejo=<%= KeyFactory.keyToString(chamaManejo.getId())%>"></a></p></td>
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