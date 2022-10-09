package twoout.miniweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SignUp() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿 모듈 제작시 최상단에 utf-8선언 필수
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("서블릿 작동함 / servlet is Actived").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
