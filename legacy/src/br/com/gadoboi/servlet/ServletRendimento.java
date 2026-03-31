package br.com.gadoboi.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gadoboi.bean.Rendimento;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ServletRendimento extends BaseServlet{

	private Rendimento rendimento;
	
	@Override
	public void povoarValidarBean(HttpServletRequest req) {
		registros = new Vector<Object>();
		
		rendimento = new Rendimento();
		if(req.getAttribute("idRendimento") !=null){
			Key id = KeyFactory.stringToKey(req.getParameter("idRendimento"));
			rendimento = em.find(Rendimento.class, id);
		}
		rendimento.setGanhoDePesoEsperado(Double.valueOf(req.getParameter("ganhoPesoDia")));
		rendimento.setPeriodoDeTratamento(Double.valueOf(req.getParameter("qtdDias")));
		rendimento.setPesoInicial(Double.valueOf(req.getParameter("pesoInicial")));
		rendimento.setPorcentagemPorPeso(Double.valueOf(req.getParameter("porPeso")));
		rendimento.setPrecoArroba(Double.valueOf(req.getParameter("precoArroba")));
		rendimento.setPrecoPorQuiloCon(Double.valueOf(req.getParameter("precoKgCon")));
		rendimento.setQtdAnimais(Double.valueOf(req.getParameter("qtdAnimais")));
		rendimento.setRendimentoCarcaca(Double.valueOf(req.getParameter("rendimentoCarcaca")));
		rendimento.setTamanhoDaPastagem(Double.valueOf(req.getParameter("tamanhoHa")));
		rendimento.calcular();
		
		registros.add(rendimento);
	}


	@Override
	public void redirecionar(HttpServletResponse resp) throws IOException{
		resp.sendRedirect("menu.jsp");
		
	}

}
