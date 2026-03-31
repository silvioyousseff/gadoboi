<%@page import="br.com.gadoboi.bean.Manejo"%>
<%@page import="br.com.gadoboi.dao.EMF"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
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
					<li><a href="visualizamanejo.jsp">Voltar</a></li>								
				</ul>				
			</div>																			
		</div>	
	
					<%
						/* conexao com o banco */
						Key id = KeyFactory.stringToKey(request.getParameter("idManejo"));
						EntityManager em = EMF.get().createEntityManager();
						Manejo varManejo = em.find(Manejo.class, id);
					%>

		
<div id="main">				
	<div class="post">
		
		<h1>Alterar Manejo</h1>
				
		<ul>	
		<p>A primeira letra Mai&uacute;scula</p>
		<p>Ex: Confinamento</p>																												
			<form id="milho" action="../ServletManejo" method="get">
			<input type="hidden" name="acao" value="Atualizar"/>
			<input type="hidden" name="idManejo" value="<%= KeyFactory.keyToString(varManejo.getId())%>" />
				<table>
					<tr>
						<th class="first"><strong>Tipo de Manejo</strong></th>
						<th><p class="buttonConf"><input title="Salvar" type="submit" name="enviar" value="" title="" /></p></th>
									
					</tr>
					<tr class="row-a">
						<td class="first"><input required type="text" size="20" maxlength="20" name="tipoManejo" value="<%=varManejo.getNomeManejo()%>"/></td>
						<td></td>
					</tr>
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