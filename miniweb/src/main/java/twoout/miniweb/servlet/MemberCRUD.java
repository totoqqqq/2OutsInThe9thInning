package twoout.miniweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import twoout.miniweb.dao.MemberDAO;
import twoout.miniweb.dto.Member;

public class MemberCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberCRUD() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String order;
		HttpSession hs=request.getSession();
		if(request.getParameter("order")==null)
			order="create";
		else
			order=request.getParameter("order");
		switch(order) {
			case "create":
				boolean signup = MemberDAO.getMemberDAO().SignUp(new Member(request.getParameter("id"),request.getParameter("pw"),request.getParameter("name")
						,request.getParameter("phone"),request.getParameter("email"),request.getParameter("zipcode"),request.getParameter("address")
						,request.getParameter("building"),null));
				if(signup)
					response.sendRedirect("/miniweb");
				else
					response.sendRedirect("/miniweb/signUp.jsp");
				break;
			case "update":
				break;
			case "delete":
				MemberDAO.getMemberDAO().deleteID(hs.getAttribute("memberID").toString());
				hs.setAttribute("memberID", null);
				hs.invalidate();
				break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
