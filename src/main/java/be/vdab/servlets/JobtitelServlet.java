package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Jobtitel;
import be.vdab.services.JobtitelService;

@WebServlet("/jobtitels.htm")
public class JobtitelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/jobtitels.jsp";
	private final JobtitelService jobtitelService = new JobtitelService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Iterable<Jobtitel> jobtitels = jobtitelService.findAll();
		request.setAttribute("jobtitels", jobtitels);
		try {
			long jobtitelID = Long
					.parseLong(request.getParameter("jobtitelID"));
			Jobtitel jobtitel=jobtitelService.read(jobtitelID);
			request.setAttribute("jobtitel", jobtitel);
		} catch (NumberFormatException | NullPointerException ex) {
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
