<%@page import="br.com.gadoboi.bean.Cliente"%>
<%@page import="br.com.gadoboi.dao.EMF"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="jquery/jquery-latest.js"></script>
<link rel="stylesheet" href="css.css" type="text/css" />
<head>
<%@ include file="estilo.html"%>
<script src="JavaScript/menu.js" ></script>
<title>Cadastro</title>
</head>
<body>
<%@ include file="cabecalho.html"%>	

<!-- 
<script>
function check_date(DATA).value {
        var expReg = /^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[1-2][0-9]\d{2})$/;
        var msgErro = 'Formato inválido de data.';
        var vdt = new Date();
        var vdia = vdt.getDay();
        var vmes = vdt.getMonth();
        var vano = vdt.getYear();
        if ((DATA.value.match(expReg)) && (DATA.value!='')){
                var dia = DATA.value.substring(0,2);
                var mes = DATA.value.substring(3,5);
                var ano = DATA.value.substring(6,10);
                if((mes==04 && dia > 30) || (mes==06 && dia > 30) || (mes==09 && dia > 30) || (mes==11 && dia > 30)){
                        alert("Dia incorreto !!! O mês especificado contém no máximo 30 dias.");
                        DATA.focus();
                        return false;
                } else{ //1
                                if(ano%4!=0 && mes==2 && dia>28){
                                        alert("Data incorreta!! O mês especificado contém no máximo 28 dias.");
                                        DATA.focus();
                                        return false;
                                } else{ //2
                                                if(ano%4==0 && mes==2 && dia>29){
                                                                alert("Data incorreta!! O mês especificado contém no máximo 29 dias.");
                                                                DATA.focus();
                                                                return false;
                                                } else{ //3
                                                                if (ano > vano) {
                                                                                alert("Data incorreta!! Ano informado maior que ano atual.");
                                                                                DATA.focus();
                                                                                return false;
                                                                }else{ //4
                                                                        //alert ("Data correta!");
                                                                        return true;
                                                                } //4-else
                                                } //3-else
                                }//2-else
                }//1-else                       
        } else { //5
                        alert(msgErro);
                        DATA.focus();
                        return false;
        } //5-else
}
</script>

 -->
 	
	<div id="all" style="width: 600px; margin-left: -300px; position: relative; left: 50%;">
		<form id="frmCliente" action="../ServletCliente" method="get" >	
		<input type="hidden" name="acao" value="Inserir">
		<input type="hidden" name="usuario" value="<%= request.getUserPrincipal()%>">
				<div class="form_left">			
					<label  id="labelLogradouro" style="">Nome:</label>
					<input required type="text" name="nome" />
				
					<label  id="labelLogradouro" style="">Sobrenome:</label>
					<input required type="text" name="sobrenome" />

					<label id="labelDisponivel" style="">Sexo:</label>
					<span class="jqTransformRadioWrapper">
						<input required type="radio" name="sexo" value="M" />Masc /
					</span>
					<span class="jqTransformRadioWrapper">
						<input required type="radio" name="sexo" value="F" />Fem
					</span>

					<label  id="labelLogradouro" style="">CPF:</label>
					<input required type="text" name="cpf" id="cpf" maxlength="14" onKeyUp="mascaraTexto(event,'999.999.999-99')" />


					<label  id="labelLogradouro" style="">RG:</label>
					<input required type="text" name="rg" />


					<label  id="labelLogradouro" style="">E-Mail:</label>
					<input required type="text" name="email" />

				
					<label  id="labelLogradouro" style="">Telefone:</label>
					<input required type="text" name="telefone" maxlength="13" onKeyUp="mascaraTexto(event,'(99)9999-9999')" />
				</div>	
				<div class="form_right">

					<label  id="labelLogradouro" style="">Celular:</label>
					<input required type="text" name="celular" maxlength="13" onKeyUp="mascaraTexto(event,'(99)9999-9999')"/>

				
					<label  id="labelLogradouro" style="">Data de Nascimento:</label>
					<input required type="text" name="dataNasc" maxlength="10" onKeyUp="mascaraTexto(event,'99/99/9999')"/>
				
					<label  id="labelLogradouro" style="">Estado:</label>
					<input required type="text" name="nomeEstado" />
				
					
					<label  id="labelLogradouro" style="">Cidade:</label>
					<input required type="text" name="nomeCidade" />
				
				
					<!--
					<div class="rowElem">
						<label id="labelEstado" style="">Selecione o Estado:</label>
						<select name="nomeEstado" id="estados">
							<option value=""></option>
						</select>
					</div>					
					<div class="rowElem">
						<label id="labelTipoMaterial" style="">Selecione a Cidade:</label>
						<select name="nomeCidade" id="cidades">
							<option value=""></option>
						</select>
					-->
				
				
					<label  id="labelLogradouro" style="">Bairro:</label>
					<input required type="text" name="bairro" />


					<label  id="labelLogradouro" style="">N&uacute;mero:</label>
					<input required type="text" name="numero" />


					<label  id="labelLogradouro" style="">Complemento:</label>
					<input required type="text" name="complemento" />
					
			</div>
			<div class="form_center">
					<input type="submit" name="enviar" value="" title="Cadastrar" />
			</div>		
		</form>
	</div>
	<%@ include file="rodape.html"%>
</body>
</html>