<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="br.com.gadoboi.bean.Cliente"%>
<%@page import="br.com.gadoboi.dao.EMF"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="estilo.html"%>
<title>Alterar / Excluir</title>
</head>
<body>


					<%
						/* conexao com o banco */
						Key id = KeyFactory.stringToKey(request.getParameter("idCliente"));
						EntityManager em = EMF.get().createEntityManager();
						Cliente varCliente = em.find(Cliente.class, id);
					%>
					
<%@ include file="cabecalho.html"%>	
	<div id="all" style="width: 600px; margin-left: -300px; position: relative; left: 50%;">
		<form id="frmCliente" action="../ServletCliente" method="get">	
		<input type="hidden" name="acao" value="Atualizar"/>
		<input type="hidden" name="idCliente" value="<%= KeyFactory.keyToString(varCliente.getId())%>" />	
		<input type="hidden" name="idEndereco" value="<%= KeyFactory.keyToString(varCliente.getEndereco().getId())%>" />
			
			<div class="form_left">
					<label>Nome:</label>
					<input type="text" name="nome" value="<%=varCliente.getNome()%>" style="" />
					
					<label>Sobrenome:</label>
					<input type="text" name="sobrenome" value="<%=varCliente.getSobrenome()%>" style="" />

					<label style="">Sexo:</label>
					<span class="jqTransformRadioWrapper" style="">
						<input type="radio" name="sexo" value="M" <%=varCliente.getSexo().equals("M") ? "checked=checked" : ""%>/>Masc /
					</span>
					<span class="jqTransformRadioWrapper" style="">
						<input type="radio" name="sexo" value="F" <%=varCliente.getSexo().equals("F") ? "checked=checked" : ""%>/>Fem
					</span>
					
					
					<label>CPF:</label>
					<input type="text" name="cpf" value="<%=varCliente.getCpf()%>" style="" />
					
					<label>RG:</label>
					<input type="text" name="rg" value="<%=varCliente.getRg()%>" style=""/>
					

					<label>E-Mail:</label>
					<input type="text" name="email" value="<%=varCliente.getEmail()%>"/>

					<label>Telefone:</label>
					<input type="text" name="telefone" value="<%=varCliente.getTelefone()%>"/>
					
					<label>Celular:</label>
					<input type="text" name="celular" value="<%=varCliente.getCelular()%>"/>

					<label>Data Nascimento:</label>
					<input type="text" name="dataNasc" value="<%=varCliente.getDataNasc().getDay() + "/" + varCliente.getDataNasc().getMonth() + "/" + varCliente.getDataNasc().getYear()%>"/>
					
			</div>
			<div class="form_right">	

					<label>Estado:</label>
					<input type="text" name="nomeEstado" value="<%=varCliente.getEndereco().getEstado()%>"/>


					<label>Cidade:</label>
					<input type="text" name="nomeCidade" value="<%=varCliente.getEndereco().getCidade()%>" />
					

					<label>Bairro:</label>
					<input type="text" name="bairro" value="<%=varCliente.getEndereco().getBairro()%>" />


					<label>N&uacute;mero:</label>
					<input type="text" name="numero" value="<%=varCliente.getEndereco().getNumero()%>"/>
					

					<label>Complemento:</label>
					<input type="text" name="complemento" value="<%=varCliente.getEndereco().getComplemento()%>"/>
					
								
					<label>Usuario:</label>
					<input type="text" name="usuario" readonly="readonly" value="<%=varCliente.getUsuario()%>"/>

				</div>
				<div class="form_center_altera">
				<table>
					<tr>
						<td><input type="submit" name="enviar" value="" /></td>
						<td><p class="buttonDel"><a href="../ServletCliente?acao=Remover&idCliente=<%= KeyFactory.keyToString(varCliente.getId())%>"></a></p></td>
					</tr>			
				</table>
				</div>
		</form>
	</div>
	<%
		em.close();
	%>
<%@ include file="rodape.html"%>
</body>
</html>