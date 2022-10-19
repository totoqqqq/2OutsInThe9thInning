package twoout.miniweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import twoout.miniweb.dao.MemberDAO;

public class IdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IdCheck() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id=request.getParameter("id");
		PrintWriter out=response.getWriter();
			out.print(MemberDAO.getMemberDAO().IDchecked(id));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
