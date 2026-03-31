package br.com.gadoboi.servlet;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.Vector;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gadoboi.bean.Cliente;
import br.com.gadoboi.bean.Insumo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

public class ServletInsumo extends BaseServlet{

	private Insumo cadInsumo;
	@Override
	public void povoarValidarBean(HttpServletRequest req) {
		registros = new Vector<Object>();
		
		cadInsumo = new Insumo();
		
		Query q = em.createQuery("SELECT o FROM Cliente AS o WHERE o.email = :email").setParameter("email", req.getUserPrincipal().toString());
		Cliente usuario = (Cliente) q.getSingleResult();
		cadInsumo.setCliente(usuario);
	
		if (req.getParameter("idInsumo") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idInsumo"));
			cadInsumo = em.find(Insumo.class, id);
		}
			cadInsumo.setNomeInsumo(req.getParameter("nomeInsumo"));
			
			registros.add(cadInsumo);
	}

	@Override
	public void redirecionar(HttpServletResponse resp) throws IOException {
			resp.sendRedirect("visualizainsumo.jsp");
	}
 
}
