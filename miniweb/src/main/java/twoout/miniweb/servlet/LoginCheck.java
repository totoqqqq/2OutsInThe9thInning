package twoout.miniweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import twoout.miniweb.dao.MemberDAO;
import twoout.miniweb.dto.Member;

public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LoginCheck() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession hs=request.getSession();
		String order="";
		if(request.getParameter("order")==null)
			order="login";
		else
			order=request.getParameter("order");
		switch(order) {
			case"logout":
				hs.setAttribute("order",null);
				hs.invalidate();
				break;
			case"login":
				Member login = MemberDAO.getMemberDAO().Login(new Member(request.getParameter("id"),request.getParameter("pw")));
				if(login!=null) {
					hs.setAttribute("memberID", login.getMemberID());
					hs.setAttribute("memberPW", login.getMemberID());
					hs.setAttribute("nickname",login.getNickName());
					response.sendRedirect("/miniweb/mypage.jsp");
					return;
				}
				break;
		}
			response.sendRedirect("/miniweb");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}