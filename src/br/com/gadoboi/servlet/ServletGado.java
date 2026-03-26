package br.com.gadoboi.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gadoboi.bean.Cliente;
import br.com.gadoboi.bean.Gado;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ServletGado extends BaseServlet {

	private Gado cadGado;

	@Override
	public void povoarValidarBean(HttpServletRequest req) {
		registros = new Vector<Object>();

		cadGado = new Gado();
		if (req.getParameter("idGado") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idGado"));
			cadGado = em.find(Gado.class, id);
		}
		
		Query q = em.createQuery(
				"SELECT o FROM Cliente AS o WHERE o.email = :email")
				.setParameter("email", req.getUserPrincipal().toString());
		Cliente usuario = (Cliente) q.getSingleResult();
		cadGado.setCliente(usuario);

		
		cadGado.setNomeGado(req.getParameter("racaGado"));

		registros.add(cadGado);
	}

	@Override
	public void redirecionar(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("visualizagado.jsp");
	}

}
