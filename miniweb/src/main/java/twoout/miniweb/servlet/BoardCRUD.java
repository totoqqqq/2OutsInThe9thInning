package twoout.miniweb.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
import twoout.miniweb.dto.BoardReply;

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
			System.out.println(request.getParts());
			Collection<Part> parts=request.getParts();
			if(hs.getAttribute("memberID")!=null&&parts!=null) {
				ArrayList<String> vmName= new ArrayList<String>(); 
				ArrayList<String> filepath= new ArrayList<String>();
				ArrayList<String> pname= new ArrayList<String>();
				ArrayList<Part> partslist= new ArrayList<Part>();
				for(Part p:parts) {
					if(p.getHeader("Content-Disposition").contains("filename=")) {
						if(p.getSize()>0) {
							vmName.add(UUID.randomUUID().toString());
							filepath.add("D:\\resource\\"+vmName.get(vmName.size()-1)+"_"+p.getSubmittedFileName());
							pname.add(p.getSubmittedFileName());
							partslist.add(p);
						}
					}
				}
				ArrayList<BoardFile> bfile = new ArrayList<BoardFile>();
				for(int i=0;i<partslist.size();i++)
					bfile.add(new BoardFile(pname.get(i), vmName.get(i),null));
				if(BoardDAO.createBoard(new Board(hs.getAttribute("memberID").toString(),request.getParameter("title"),request.getParameter("contents")),bfile))
					for(int i=0;i<partslist.size();i++)
						partslist.get(i).write(filepath.get(i));
			}
			else if(hs.getAttribute("memberID")!=null)
				BoardDAO.createBoard(new Board(hs.getAttribute("memberID").toString(),request.getParameter("title"),request.getParameter("contents")));
			response.sendRedirect("board.jsp?page=1");
			break;
		case "list":
			String page=request.getParameter("page");
			ArrayList<Board> list = BoardDAO.listBoard(page);
			try(PrintWriter pw=response.getWriter();){
				pw.print(new Gson().toJson(list));
			}
			break;
		case "listpage":
			try(PrintWriter pw=response.getWriter();){
				pw.print(new Gson().toJson(BoardDAO.listBoardPage()));
			}
			break;
		case "view":
			Board board=BoardDAO.viewBoard(request.getParameter("boardIDs"));
			ArrayList<BoardFile> boardFile=BoardDAO.viewBoardFile(request.getParameter("boardIDs"));
			ArrayList<BoardReply> boardReply=BoardDAO.viewBoardReply(request.getParameter("boardIDs"));
			request.setAttribute("boardData", board);
			if(boardFile!=null)
				request.setAttribute("boardFileData", boardFile);
			if(boardReply!=null)
				request.setAttribute("boardReplyData", boardReply);
			request.getRequestDispatcher("view.jsp").forward(request, response);
			break;
		case "delete":
			BoardDAO.deleteBoard(request.getParameter("boardIDs"),hs.getAttribute("memberID").toString());
			break;
		case "update":
			BoardDAO.updateBoard(request.getParameter("boardIDs"),hs.getAttribute("memberID").toString(),request.getParameter("boardTitle"),request.getParameter("boardContent"));
			break;
		case "replyinput":
			BoardDAO.createReply(new BoardReply(request.getParameter("boardreplyinput"),hs.getAttribute("memberID").toString(),request.getParameter("boardIDs")));
			break;
		case "replydelete":
			BoardDAO.deleteBoardReply(Timestamp.valueOf(request.getParameter("createdate")),hs.getAttribute("memberID").toString());
			break;
		case "replyupdate":
			BoardDAO.updateBoardReply(Timestamp.valueOf(request.getParameter("createdate")),request.getParameter("replayContent"),hs.getAttribute("memberID").toString());
			break;
		case "download":
			String downPath=BoardDAO.checkDownload(new BoardFile(request.getParameter("realName"),request.getParameter("vmName"),request.getParameter("boardIDs")));
			String name=request.getParameter("realName");
			name=new String(name.getBytes("utf8"),"ISO-8859-1");
			if(downPath!=null){
				response.setHeader("content-Disposition", "attachment;filename="+name);
				File file=new File("D:\\resource\\"+downPath);
				try(OutputStream sos = response.getOutputStream();FileInputStream fis = new FileInputStream(file);DataInputStream dis=new DataInputStream(fis)){
					byte[] fileContents= new byte[(int)file.length()];
					dis.readFully(fileContents);
					sos.write(fileContents);
					sos.flush();
				}
			}
			break;
		}
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
