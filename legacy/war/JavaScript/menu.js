// JavaScript Document
function login(){
	document.write("<head><style type=text/css>.menu{color:white;}.a{text-align:center;}.a:hover{cursor:pointer;color:black;text-align:center}</style></head>");
	document.write("<fieldset><table><tr><td>Login: <input type=text style=width:100px; /></td></tr><tr><td>Senha: <input type=password style=width:100px;></td></tr></table></fieldset>");	
}

function menue(){
	document.write("<table border=0 width=100% class=menu>");
		document.write("<tr height=30>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center top;font-size:19px;font-weight:bold' >Menu:</td>");
		document.write("</tr>");
		document.write("<tr>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center'>Inicio</td>");
		document.write("</tr>");
	document.write("</table>");

}

function menud(){
	document.write("<table border=0 width=100% class=menu>");
		document.write("<tr height=30>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center top;font-size:19px;font-weight:bold' >Sistema:</td>");
		document.write("</tr>");
		document.write("<tr>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center' class=a onclick=location.href='../funcionario/index.jsp'>Funcion&aacute;rio</td>");
		document.write("</tr>");
		document.write("<tr>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center' class=a onclick=location.href='../barracao/cadastrobar.jsp'>Barrac&atilde;o</td>");
		document.write("</tr>");
		document.write("<tr>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center' class=a>Cama</td>");
		document.write("</tr>");
		document.write("<tr>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center' class=a>Bosques</td>");
		document.write("</tr>");
		document.write("<tr>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center' class=a>&Aacute;rea de Amora</td>");
		document.write("</tr>");
		document.write("<tr>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center' class=a>Controle Di&aacute;rio</td>");
		document.write("</tr>");
		document.write("<tr>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center' class=a>Controle Mensal</td>");
		document.write("</tr>");
		document.write("<tr height=30>");
			document.write("<td width=100% align=center style='background:url(../../imagens/menuiten.png) no-repeat center bottom;font-size:19px;font-weight:bold' ></td>");
		document.write("</tr>");
	document.write("</table>");

}

function mascaraTexto(evento, mascara){  
    
    var campo, valor, i, tam, caracter;  
      
    if (document.all) // Internet Explorer  
       campo = evento.srcElement;  
    else // Nestcape, Mozzila  
        campo= evento.target;  
          
    valor = campo.value;  
    tam = valor.length;  
      
    for(i=0;i<mascara.length;i++){  
       caracter = mascara.charAt(i);  
       if(caracter!="9")   
          if(i<tam & caracter!=valor.charAt(i))  
             campo.value = valor.substring(0,i) + caracter + valor.substring(i,tam);  
                  
    }  
   
 } 

function valida(){
	if(!valida_cpf(document.getElementById('cpf').value)){
		alert('CPF Invalido');
		return true;
	}
}

function valida_cpf(cpf){
	  var numeros, digitos, soma, i, resultado, digitos_iguais, a, b, c, d, e;
	  a = cpf.substr(0,3);
	  b = cpf.substr(4,3);
	  c = cpf.substr(8,3);
	  d = cpf.substr(12,2);
	  e = a + b + c + d;
	  digitos_iguais = 1;
	  if (e.length < 11)
			return false;
	  for (i = 0; i < e.length - 1; i++)
			if (e.charAt(i) != e.charAt(i + 1))
				  {
				  digitos_iguais = 0;
				  break;
				  }
	  if (!digitos_iguais)
			{
			numeros = e.substring(0,9);
			digitos = e.substring(9);
			soma = 0;
			for (i = 10; i > 1; i--)
				  soma += numeros.charAt(10 - i) * i;
			resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
			if (resultado != digitos.charAt(0))
				  return false;
			numeros = e.substring(0,10);
			soma = 0;
			for (i = 11; i > 1; i--)
				  soma += numeros.charAt(11 - i) * i;
			resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
			if (resultado != digitos.charAt(1))
				  return false;
			return true;
			}
	  else
			return false;
}