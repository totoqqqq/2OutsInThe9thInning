package twoout.miniweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import twoout.miniweb.dao.MemberDAO;
import twoout.miniweb.dto.Member;

@WebServlet("*.mem")
public class MemberCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberCRUD() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession();
		String order=request.getRequestURI().toString().substring(request.getRequestURI().toString().lastIndexOf("/")+1,request.getRequestURI().toString().indexOf("."));;
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
				if(hs.getAttribute("memberID").equals(request.getParameter("id"))&&hs.getAttribute("memberPW").toString().equals(request.getParameter("oldpw"))) {
					if(MemberDAO.getMemberDAO().updateID(new Member(request.getParameter("id"),request.getParameter("newpw"),request.getParameter("name")
						,request.getParameter("phone"),request.getParameter("email"),request.getParameter("zipcode"),request.getParameter("address")
						,request.getParameter("building"),hs.getAttribute("membership").toString()))) {
						Member login = MemberDAO.getMemberDAO().Login(new Member(hs.getAttribute("memberID").toString(),hs.getAttribute("memberPW").toString()));
						if(login!=null) {
							hs.setAttribute("memberID", login.getMemberID());
							hs.setAttribute("nickName",login.getNickName());
							hs.setAttribute("phone", login.getPhone());
							hs.setAttribute("memberPW", login.getMemberPW());
							hs.setAttribute("email", login.getEmail());
							hs.setAttribute("zipcode", login.getZipcode());
							hs.setAttribute("address", login.getAddress());
							hs.setAttribute("building", login.getBuilding());
							hs.setAttribute("membership", login.getMembership());
							response.sendRedirect("/miniweb/mypage.jsp");
						}else
							response.sendRedirect("/miniweb/");
					}
				}
				break;
			case "delete":
				MemberDAO.getMemberDAO().deleteID(hs.getAttribute("memberID").toString());
				hs.setAttribute("memberID", null);
				hs.invalidate();
				break;
			case "checkid":
				boolean check=MemberDAO.getMemberDAO().checkID(request.getParameter("checkid"));
				if(check)
					response.getWriter().append('t');
				else
					response.getWriter().append('f');
				break;
			case"logout":
				hs.setAttribute("order",null);
				hs.invalidate();
				response.sendRedirect("/miniweb");
				break;
			case"login":
				Member login = MemberDAO.getMemberDAO().Login(new Member(request.getParameter("id"),request.getParameter("pw")));
				if(login!=null) {
					hs.setAttribute("memberID", login.getMemberID());
					hs.setAttribute("nickName",login.getNickName());
					hs.setAttribute("phone", login.getPhone());
					hs.setAttribute("memberPW", login.getMemberPW());
					hs.setAttribute("email", login.getEmail());
					hs.setAttribute("zipcode", login.getZipcode());
					hs.setAttribute("address", login.getAddress());
					hs.setAttribute("building", login.getBuilding());
					hs.setAttribute("membership", login.getMembership());
					response.sendRedirect("/miniweb/mypage.jsp");
					return;
				}
				response.sendRedirect("/miniweb");
				break;
		}
	}
	public String idcheck(String id) {
		return id;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
