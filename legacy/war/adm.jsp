<%@page import="br.com.gadoboi.dao.EMF"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%
	UserService user = UserServiceFactory.getUserService();
	
	if (request.getUserPrincipal() == null) {
		response.sendRedirect(user.createLoginURL(request.getRequestURI()));
	}
	else if (request.getUserPrincipal().toString().equals("silvioyousseff@gmail.com")) {
		if (!request.getRequestURI().equals("/visualizar.jsp"))
			response.sendRedirect("visualizar.jsp");
	}
	else {
	  	response.sendRedirect("index.html");
	}
%>