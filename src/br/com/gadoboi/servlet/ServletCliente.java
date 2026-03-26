package br.com.gadoboi.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gadoboi.bean.Cliente;
import br.com.gadoboi.bean.Endereco;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class ServletCliente extends BaseServlet{
	
	private Cliente cliente;
	private Endereco endereco;
	
	@Override
	public void povoarValidarBean(HttpServletRequest req) {	
		registros = new Vector<Object>();
		
		endereco = new Endereco();
		if (req.getParameter("idEndereco") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idEndereco"));
			endereco = em.find(Endereco.class, id);
			
		}
		
		
		cliente = new Cliente();
		if (req.getParameter("idCliente") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idCliente"));
			cliente = em.find(Cliente.class, id);
			endereco = em.find(Endereco.class, cliente.getEndereco().getId());
		}
	
		endereco.setBairro(req.getParameter("bairro"));
		endereco.setComplemento(req.getParameter("complemento"));
		endereco.setNumero(req.getParameter("numero"));
		endereco.setCidade(req.getParameter("nomeCidade"));
		endereco.setEstado(req.getParameter("nomeEstado"));

		cliente.setEndereco(endereco);
		cliente.setNome(req.getParameter("nome"));
		cliente.setCpf(req.getParameter("cpf"));
		cliente.setRg(req.getParameter("rg"));
		cliente.setSexo(req.getParameter("sexo"));
		cliente.setSobrenome(req.getParameter("sobrenome"));
		cliente.setCelular(req.getParameter("celular"));
		cliente.setTelefone(req.getParameter("telefone"));
		cliente.setEmail(req.getParameter("email"));
		if (req.getParameter("dataNasc") != null)
			cliente.setDataNasc(new Date(req.getParameter("dataNasc")));
		cliente.setUsuario(req.getParameter("usuario"));
		
		registros.add(endereco);
		registros.add(cliente);
	}



	@Override
	public void redirecionar(HttpServletResponse resp) throws IOException{		
		switch (acao) {
		case INSERIR:
			resp.sendRedirect("menu.jsp");;
		case ATUALIZAR:
			resp.sendRedirect("visualizar.jsp");
		case REMOVER:
			resp.sendRedirect("visualizar.jsp");
		} 
	}

}
