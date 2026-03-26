package br.com.gadoboi.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContato extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody =

		"Nome: " + req.getParameter("idnome") + "\n" +
		"Email: " + req.getParameter("idemail") + "\n" +
		"Texto: " + req.getParameter("idtext");
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("admin@gadoeboi.appspotmail.com","Contato GadoBoi"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("silvio_yousseff@hotmail.com", "Sr. Silvio"));
			msg.setSubject("Contato GadoBoi");
			msg.setText(msgBody);
			Transport.send(msg);

		} catch (AddressException e) {
			e.printStackTrace(resp.getWriter());
		} catch (MessagingException e) {
			e.printStackTrace(resp.getWriter());
		}
		resp.sendRedirect("index.html");
	}
}
