package twoout.miniweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import twoout.miniweb.dao.BoardDAO;
import twoout.miniweb.dto.Board;
import twoout.miniweb.dto.BoardFile;

@WebServlet("*.board")
@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*5,
		maxRequestSize=1024*1024*10,
		location="D:\\resource\\"
		)
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
			Collection<Part> parts=request.getParts();
			if(hs.getAttribute("memberID")!=null&&parts!=null) {
				for(Part p:parts) {
					if(p.getHeader("Content-Disposition").contains("filename=")) {
						if(p.getSize()>0) {
							String vmName=UUID.randomUUID().toString();
							String filepath="D:\\resource\\"+vmName+"_"+p.getSubmittedFileName();
							if(BoardDAO.createBoard(new Board(hs.getAttribute("memberID").toString(),request.getParameter("title"),request.getParameter("contents")),new BoardFile(p.getSubmittedFileName(), vmName,hs.getAttribute("memberID").toString())))
								p.write(filepath);
						}
					}
				}
			}
			else if(hs.getAttribute("memberID")!=null)
				BoardDAO.createBoard(new Board(hs.getAttribute("memberID").toString(),request.getParameter("title"),request.getParameter("contents")));
			response.sendRedirect("board.jsp");
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
		case "fileupload":
			Collection<Part> partss=request.getParts();
			for(Part p:partss) {
				if(p.getHeader("Content-Disposition").contains("filename=")) {
					if(p.getSize()>0) {
						String vmName=UUID.randomUUID().toString();
						String filepath="D:\\resource\\"+vmName+"_"+p.getSubmittedFileName();
						if(BoardDAO.createBoard(new Board(hs.getAttribute("memberID").toString(),request.getParameter("title"),request.getParameter("contents")),new BoardFile(p.getSubmittedFileName(), vmName,null)))
							p.write(filepath);
					}
				}
			}
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
