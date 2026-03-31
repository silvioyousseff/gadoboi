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
<%@ include file="adm.jsp"%>
<title>Visualizar</title>
</head>
<body>
					
<%@ include file="cabecalho.html"%>
<!-- content-wrap starts here -->
	<div id="content-wrap"><div id="content">		
		
	<div id="sidebar" >
		<div class="sidebox">	
			
				<h1 class="clear">Menu</h1>
			<ul class="sidemenu">
				<ul class="sidemenu">				
					<li><a href="<%= user.createLogoutURL("/index.html")%>">Logout</a></li>	
				</ul>
			</ul>					
		</div>																			
	</div>	
		<div id="main">		
		
			<div class="post">
				
				<h1>Clientes</h1>
				
				<ul>																											
		<table>
				<tr>
					<th class="first"><strong>Nome</strong></th>
					<th>Telefone</th>
					<th>Email</th>				
				</tr>
					<%
						/* conexao com o banco */
						EntityManager em = EMF.get().createEntityManager();
						Query q = em.createQuery("SELECT i FROM Cliente AS i");
						
						List<Cliente> varCliente = q.getResultList();
						int i = 0;
						for (Cliente chamaCliente : varCliente) {
					%>
						<tr class="row-<%=(i % 2 == 0) ? "a" : "b" %>" onclick="location.href='alterar.jsp?idCliente=<%=KeyFactory.keyToString(chamaCliente.getId()) %>'">
							<td class="first"><%=chamaCliente.getNome()%></td>
							<td><%=chamaCliente.getTelefone()%></td>
							<td><%=chamaCliente.getEmail()%></td>
						</tr>
					<%
							i++;
						}
						em.close();
					%>
			
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