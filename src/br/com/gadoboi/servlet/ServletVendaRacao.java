package br.com.gadoboi.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gadoboi.bean.Cliente;
import br.com.gadoboi.bean.Gado;
import br.com.gadoboi.bean.Insumo;
import br.com.gadoboi.bean.Manejo;
import br.com.gadoboi.bean.Metodo;
import br.com.gadoboi.bean.Racao;
import br.com.gadoboi.bean.RacaoInsumo;
import br.com.gadoboi.bean.Regiao;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ServletVendaRacao extends BaseServlet {

	private Racao cadastro;
	private Manejo manejo;
	private Gado gado;
	private Regiao regiao;

	@Override
	public void povoarValidarBean(HttpServletRequest req) {

		registros = new Vector<Object>();

		cadastro = new Racao();

		Query q = em.createQuery(
				"SELECT o FROM Cliente AS o WHERE o.email = :email")
				.setParameter("email", req.getUserPrincipal().toString());
		Cliente usuario = (Cliente) q.getSingleResult();
		cadastro.setCliente(usuario);

		cadastro.setTaxaRendimento(req.getParameter("taxaRendimento"));
		cadastro.setHorario(req.getParameter("horario"));

		cadastro.setNomeRacao(req.getParameter("nomeRacao"));

		cadastro.setQtdGado(req.getParameter("qtdGado"));
		cadastro.setQtdTratamentoDia(req.getParameter("qtdTratamentoDia"));
		cadastro.setPesoInicial(req.getParameter("pesoInicial"));
		if (req.getParameter("data") != null)
			cadastro.setData(new Date(req.getParameter("data")));

		if (req.getParameter("idManejo") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idManejo"));
			manejo = em.find(Manejo.class, id);
		}

		if (manejo != null) {
			cadastro.setManejo(manejo);

		}

		if (req.getParameter("idRegiao") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idRegiao"));
			regiao = em.find(Regiao.class, id);
		}
		if (regiao != null) {
			cadastro.setRegiao(regiao);
		}
		if (req.getParameter("idGado") != null) {
			Key id = KeyFactory.stringToKey(req.getParameter("idGado"));
			gado = em.find(Gado.class, id);
		}
		if (gado != null) {
			cadastro.setGado(gado);
		}
		
		registros.add(cadastro);

		cadastro.setId(KeyFactory.createKey(Racao.class.getSimpleName(),
				(int) (Math.random() * 100000)));

		for (int i = 1; i < 60; i++) {
			String varInsumo = ("idInsumo" + i);
						
			if (req.getParameter(varInsumo) == null)
				continue;
			
				Key id = KeyFactory.stringToKey(req.getParameter(varInsumo));				
				Insumo insumo = em.find(Insumo.class, id);
			
			if (insumo != null) {
				RacaoInsumo racaoInsumo = new RacaoInsumo();
				
				racaoInsumo.setInsumo(insumo);
				
				racaoInsumo.setRacao(cadastro);
				racaoInsumo.setQtdInsumo(Double.valueOf(req.getParameter("qtdInsumo" + i)));
				
				registros.add(racaoInsumo);
			}

		}
		
		  for (int i = 1; i < 60; i++) {
			  String varMetodo = ("nomeMetodo" + i);
			  
		  if (req.getParameter(varMetodo) == null) 
			  continue;
		  
		  Metodo listametodo = new Metodo();
		  
		  listametodo.setRacao(cadastro);
		  listametodo.setNomeMetodo(String.valueOf(req.getParameter(varMetodo)));
		  
		  registros.add(listametodo);
		  
		  }
		 
	}

	@Override
	public void redirecionar(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("vendaracao.jsp");
	}

}
