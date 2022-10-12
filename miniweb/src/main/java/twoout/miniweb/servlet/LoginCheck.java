package twoout.miniweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
		Member login = MemberDAO.getMemberDAO().Login(new Member(request.getParameter("id"),request.getParameter("pw")));
		if(login!=null) {
			hs.setAttribute("memberID", login.getMemberID());
			hs.setAttribute("memberPW", login.getMemberID());
			Cookie cid=new Cookie("id",login.getMemberID());
			Cookie cnick=new Cookie("nickname",login.getNickName());
			
			response.addCookie(cid);
			response.addCookie(cnick);
			response.sendRedirect("/miniweb");
		}
		else {
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		        for (int i = 0; i < cookies.length; i++) {
		            cookies[i].setMaxAge(0);
		            response.addCookie(cookies[i]);
		        }
		    }
			response.sendRedirect("/miniweb");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}