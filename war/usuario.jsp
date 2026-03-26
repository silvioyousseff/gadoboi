<%@page import="javax.persistence.NoResultException"%>
<%@page import="br.com.gadoboi.bean.Cliente"%>
<%@page import="br.com.gadoboi.dao.EMF"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%
	UserService user = UserServiceFactory.getUserService();
	Cliente usuario = null;

	if (request.getUserPrincipal() == null) {	
		response.sendRedirect(user.createLoginURL(request.getRequestURI()));
	} else {
			/* conexao com o banco */
		EntityManager em = EMF.get().createEntityManager();
		Query q = em.createQuery("SELECT o FROM Cliente AS o WHERE o.email = :email").setParameter("email", request.getUserPrincipal().toString());
		
		try {
			usuario = (Cliente) q.getSingleResult();
		} catch (NoResultException e) {
			response.sendRedirect("cadastro.jsp?user=" + request.getUserPrincipal());
		}
		
		//if (q.getResultList().isEmpty())
		//	response.sendRedirect("cadastro.jsp?user=" + request.getUserPrincipal());
	}
%>