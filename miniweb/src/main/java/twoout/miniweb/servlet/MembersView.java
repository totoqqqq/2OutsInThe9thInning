package twoout.miniweb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import twoout.miniweb.dao.MemberDAO;
import twoout.miniweb.dto.Member;

public class MembersView extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MembersView() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Member> members=MemberDAO.getMemberDAO().MemberView();
		request.setAttribute("members",members);
		request.getRequestDispatcher("MembersView.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
