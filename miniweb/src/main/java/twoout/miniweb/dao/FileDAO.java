package twoout.miniweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import twoout.miniweb.dto.File;

public class FileDAO {
	public static FileDAO fd=null;
	private FileDAO() {
	}
	synchronized public static FileDAO getBoardDAO() {
		if(fd==null)
			fd=new FileDAO();
		return fd;
	}
	synchronized public static boolean uploadFile(File file) {
		String sql="insert into examplefile values(?,?)";
		try(Connection con=Connect.getInstance(); PreparedStatement ps=con.prepareStatement(sql);){
			ps.setString(1, file.getRealName());
			ps.setString(2, file.getVmName());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public static ArrayList<File> viewfile() {
		String sql="select * from examplefile";
		try(Connection con=Connect.getInstance(); PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
			ArrayList<File> file=new ArrayList<File>();
			while(rs.next())
				file.add(new File(rs.getString(1),rs.getString(2)));
			return file;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
