package twoout.miniweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import twoout.miniweb.dao.MemberDAO;
import twoout.miniweb.dto.Member;

import java.io.IOException;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SignUp() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean signup = MemberDAO.getMemberDAO().SignUp(new Member(request.getParameter("id"),request.getParameter("pw"),request.getParameter("name")
				,request.getParameter("phone"),request.getParameter("email"),request.getParameter("zipcode"),request.getParameter("address")
				,request.getParameter("building"),null));
//		동작 테스트용 쿠키생성코드
//		String cok=new Member(request.getParameter("id"),request.getParameter("pw"),request.getParameter("name")
//					,request.getParameter("phone"),request.getParameter("email"),request.getParameter("zipcode"),request.getParameter("address")
//					,request.getParameter("building"),null).toString();
//		cok=URLEncoder.encode(cok,"utf-8");
//		response.addCookie(new Cookie("toString",cok));
		if(signup)
			response.sendRedirect("/miniweb");
		else
			response.sendRedirect("/miniweb/signUp.html");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
