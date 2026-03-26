<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<%@ include file="usuario.jsp"%>
<title>Menu</title>
</head>
<%@ include file="cabecalho.html"%>	
<body>
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
					<li><a href="insumo.jsp">Cadastrar Insumo</a></li>
					<li><a href="manejo.jsp">Cadastrar Manejo</a></li>
					<li><a href="regiao.jsp">Cadastrar Regi&atilde;o</a></li>
					<li><a href="gado.jsp">Cadastrar Ra&ccedil;a de Gado</a></li>					
					<li><a href="cadastroracao.jsp"><b>Cadastrar Ra&ccedil;&atilde;o</b></a></li>
					<li><a href="visualizaracao.jsp">Ver Ra&ccedil;&otilde;es Cadastradas</a></li>					
					<li><a href="vendaracao.jsp">Ver Ra&ccedil;&otilde;es Para Venda</a></li>
					<li><a href="alteracao.jsp">Ver Itens Cadastrados</a></li>
					<li><a href="extras.jsp">Extras</a></li>
					<li><a href="<%=user.createLogoutURL("/index.html")%>">Sair</a></li>							
				</ul>	
				
			</div>																			
		</div>	


		
		<div id="main">		
		
			<div class="post">
			
				
				<h1>Op&ccedil;&otilde;es</h1>
				
				<ul>					
					<li>F&oacute;rmulas para fabrica&ccedil;&atilde;o de ra&ccedil;&atilde;o balanceada</li>
					<li>Gerenciamento financeiro bovino</li>
					<li>Armazenamento de dados</li>
				</ul>
										
				<p class="post-footer align-right">					
					<br>
				</p>
				
			</div>		
				<br />										
		</div>					
		
	<!-- content-wrap ends here -->		
	</div></div>

<!-- footer starts here -->	

	<%@ include file="rodape.html"%>

</body>
</html>