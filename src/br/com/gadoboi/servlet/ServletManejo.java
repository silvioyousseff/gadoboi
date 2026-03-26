package br.com.gadoboi.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gadoboi.bean.Cliente;
import br.com.gadoboi.bean.Manejo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ServletManejo extends BaseServlet{

	private Manejo cadManejo;
	@Override
	public void povoarValidarBean(HttpServletRequest req) {
		registros = new Vector<Object>();
		
		cadManejo = new Manejo();
		
		Query q = em.createQuery("SELECT o FROM Cliente AS o WHERE o.email = :email").setParameter("email", req.getUserPrincipal().toString());
		Cliente usuario = (Cliente) q.getSingleResult();
		cadManejo.setCliente(usuario);
		
		if (req.getParameter("idManejo") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idManejo"));
			cadManejo = em.find(Manejo.class, id);
		}
			cadManejo.setNomeManejo(req.getParameter("tipoManejo"));
			
			registros.add(cadManejo);
	}



	@Override
	public void redirecionar(HttpServletResponse resp) throws IOException {
			resp.sendRedirect("visualizamanejo.jsp");
	}

}
