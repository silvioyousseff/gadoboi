package br.com.gadoboi.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gadoboi.dao.EMF;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {
	public enum Acao {
		INSERIR, ATUALIZAR, REMOVER
	}

	public Acao acao;
	public EntityManager em;
	protected Vector<Object> registros;

	public abstract void povoarValidarBean(HttpServletRequest req);

	public abstract void redirecionar(HttpServletResponse resp)
			throws IOException;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			
			if (req.getParameter("acao").equals("Inserir"))
				acao = Acao.INSERIR;
			if (req.getParameter("acao").equals("Atualizar"))
				acao = Acao.ATUALIZAR;
			if (req.getParameter("acao").equals("Remover"))
				acao = Acao.REMOVER;

			em = EMF.get().createEntityManager();
			
			povoarValidarBean(req);
			persistirBeanLista();
			redirecionar(resp);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	private void persistirBeanLista() {
		if (registros == null || registros.size() == 0) {
			return;
		}
		for (int i = 0; i < registros.size(); i++) {
			em.getTransaction().begin();

			switch (acao) {
			case INSERIR:
				em.persist(registros.get(i));
				break;
			case ATUALIZAR:
				em.merge(registros.get(i));
				break;
			case REMOVER:
				em.remove(registros.get(i));
				break;
			}

			em.getTransaction().commit();
		}

	}
}
