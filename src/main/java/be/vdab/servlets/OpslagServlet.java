package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Werknemer;
import be.vdab.services.WerknemerService;

@WebServlet("/opslag.htm")
public class OpslagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/opslag.jsp";
	private final WerknemerService werknemerService = new WerknemerService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			long werknemerID = Long.parseLong(request
					.getParameter("werknemerID"));

			Werknemer werknemer = werknemerService.read(werknemerID);
			request.setAttribute("werknemer", werknemer);
			request.getRequestDispatcher(VIEW).forward(request, response);
		} catch (NumberFormatException | NullPointerException ex) {
			response.sendRedirect(response.encodeRedirectURL("index.jsp"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BigDecimal bedrag = new BigDecimal(request.getParameter("bedrag"));
			long werknemerID = Long.parseLong(request
					.getParameter("werknemerID"));
			Werknemer werknemer = werknemerService.opslag(werknemerID, bedrag);
			request.setAttribute("werknemer", werknemer);
			request.getRequestDispatcher(VIEW).forward(request, response);
		} catch (NumberFormatException | NullPointerException ex) {
			response.sendRedirect(response.encodeRedirectURL("opslag.htm?werknemerID=" + request
					.getParameter("werknemerID")));
		}
	}
}
