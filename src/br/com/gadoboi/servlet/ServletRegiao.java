package br.com.gadoboi.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gadoboi.bean.Cliente;
import br.com.gadoboi.bean.Regiao;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ServletRegiao extends BaseServlet{

	private Regiao cadRegiao;
	
	@Override
	public void povoarValidarBean(HttpServletRequest req) {
		registros = new Vector<Object>();
		
		cadRegiao = new Regiao();
		
		Query q = em.createQuery("SELECT o FROM Cliente AS o WHERE o.email = :email").setParameter("email", req.getUserPrincipal().toString());
		Cliente usuario = (Cliente) q.getSingleResult();
		cadRegiao.setCliente(usuario);
		
		if (req.getParameter("idRegiao") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idRegiao"));
			cadRegiao = em.find(Regiao.class, id);
		}
			cadRegiao.setNomeRegiao(req.getParameter("nomeRegiao"));
			
			registros.add(cadRegiao);
	}



	@Override
	public void redirecionar(HttpServletResponse resp) throws IOException {
			resp.sendRedirect("visualizaregiao.jsp");
	}

}
