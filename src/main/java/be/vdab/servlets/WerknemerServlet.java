package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Werknemer;
import be.vdab.services.WerknemerService;

@WebServlet("/werknemershierarchy.htm")
public class WerknemerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/werknemershierarchy.jsp";
	private final WerknemerService werknemerService = new WerknemerService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("werknemerID") != null) {
				Werknemer werknemer = werknemerService.read(Long
						.parseLong(request.getParameter("werknemerID")));
				request.setAttribute("werknemer", werknemer);
				request.getRequestDispatcher(VIEW).forward(request, response);
			} else {
				Werknemer werknemer = werknemerService.findPresident();
				request.setAttribute("werknemer", werknemer);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		} catch (NumberFormatException ex) {
			response.sendRedirect(response.encodeRedirectURL("index.jsp"));
		}
	}
}