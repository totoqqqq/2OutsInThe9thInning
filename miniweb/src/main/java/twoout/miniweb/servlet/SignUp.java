package twoout.miniweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import twoout.miniweb.dao.MemberDAO;
import twoout.miniweb.model.Member;

import java.io.IOException;
import java.net.URLEncoder;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SignUp() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿 모듈 제작시 최상단에 utf-8선언 필수
		response.setContentType("text/html;charset=UTF-8");
		boolean signup = new MemberDAO().SignUp(new Member(request.getParameter("id"),request.getParameter("pw"),request.getParameter("name")
				,request.getParameter("phone"),request.getParameter("email"),request.getParameter("zipcode"),request.getParameter("address")
				,request.getParameter("building"),null));
//		
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
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}

}
