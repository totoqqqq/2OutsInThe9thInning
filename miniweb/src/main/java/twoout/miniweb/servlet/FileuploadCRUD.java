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
import jakarta.servlet.http.Part;
import twoout.miniweb.dao.FileDAO;
import twoout.miniweb.dto.File;

@WebServlet("*.file")
@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*5,
		maxRequestSize=1024*1024*10,
		location="D:\\resource\\"
		)
public class FileuploadCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FileuploadCRUD() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String order=request.getRequestURI().toString().substring(request.getRequestURI().toString().lastIndexOf("/")+1,request.getRequestURI().toString().indexOf("."));;
		switch(order) {
		case "upload":
			Collection<Part> parts=request.getParts();
			for(Part p:parts) {
				if(p.getHeader("Content-Disposition").contains("filename=")) {
					if(p.getSize()>0) {
						String vmName=UUID.randomUUID().toString();
						String filepath="D:\\resource\\"+vmName+"_"+p.getSubmittedFileName();
						if(FileDAO.uploadFile(new File(p.getSubmittedFileName(), vmName)))
							p.write(filepath);
					}
				}
			}
			response.sendRedirect("/miniweb/uploadtest.jsp");
			break;
		case "view":
			ArrayList<File> file=FileDAO.viewfile();
			try(PrintWriter pw=response.getWriter();){
				pw.print(new Gson().toJson(file));
			}
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
