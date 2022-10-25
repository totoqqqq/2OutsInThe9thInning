package twoout.miniweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import twoout.miniweb.dao.BoardDAO;
import twoout.miniweb.dto.Board;

@WebServlet("*.board")
public class BoardCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardCRUD() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession();
		String order=request.getRequestURI().toString().substring(request.getRequestURI().toString().lastIndexOf("/")+1,request.getRequestURI().toString().indexOf("."));;
		switch(order) {
		case "create":
			if(hs.getAttribute("memberID")!=null)
				BoardDAO.createBoard(new Board(hs.getAttribute("memberID").toString(),request.getParameter("title"),request.getParameter("contents")));
			break;
		case "list":
			ArrayList<Board> list = BoardDAO.listBoard();
			try(PrintWriter pw=response.getWriter();){
				pw.print(new Gson().toJson(list));
			}
			break;
		case "view":
			Board board=BoardDAO.viewBoard(request.getParameter("boardIDs"));
			request.setAttribute("boardData", board);
			request.getRequestDispatcher("view.jsp").forward(request, response);
			break;
		case "delete":
			BoardDAO.deleteBoard(request.getParameter("boardIDs"),hs.getAttribute("memberID").toString());
			break;
		case "update":
			BoardDAO.updateBoard(request.getParameter("boardIDs"),hs.getAttribute("memberID").toString(),request.getParameter("boardTitle"),request.getParameter("boardContent"));
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
