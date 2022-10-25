package twoout.miniweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import twoout.miniweb.dto.Board;
import twoout.miniweb.dto.BoardFile;

public class BoardDAO {
	public static BoardDAO bd=null;
	private BoardDAO() {
	}
	synchronized public static BoardDAO getBoardDAO() {
		if(bd==null)
			bd=new BoardDAO();
		return bd;
	}
	synchronized public static boolean createBoard(Board board) {
		String sql="insert into board values(?,board_id_seq.nextval,?,?,systimestamp,default)";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
			ps.setString(1, board.getMemberID());
			ps.setString(2, board.getBoardTitle());
			ps.setString(3, board.getBoardContent());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	synchronized public static boolean createBoard(Board board,BoardFile file) {
		String sql="insert into board values(?,board_id_seq.nextval,?,?,systimestamp,default)";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
			ps.setString(1, board.getMemberID());
			ps.setString(2, board.getBoardTitle());
			ps.setString(3, board.getBoardContent());
			if(ps.executeUpdate()==1) {
				sql="insert into boardfile values(?,?,board_id_seq.currval)";
			try(PreparedStatement innerps=con.prepareStatement(sql);){
				innerps.setString(1, file.getRealName());
				innerps.setString(2, file.getVmName());
				if(innerps.executeUpdate()==1)
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	synchronized public static ArrayList<Board> listBoard(){
		String sql="select * from board order by createdate";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
			ArrayList<Board> list = new ArrayList<Board>();
			while(rs.next())
				list.add(new Board(rs.getString(1),rs.getString(2),rs.getString(3),"",rs.getTimestamp(5),Integer.parseInt(rs.getString(6))));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	synchronized public static Board viewBoard(String boardID) {
		String sql="update board set viewcount = viewcount + 1 where boardid='"+boardID+"'";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql="select * from board where boardid='"+boardID+"'";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
			if(rs.next())
				return new Board(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),Integer.parseInt(rs.getString(6)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	synchronized public static boolean deleteBoard(String boardID,String memberID) {
		String sql="delete from board where boardid='"+boardID+"' and memberid='"+memberID+"'";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public static boolean updateBoard(String boardID,String memberID, String boardTitle, String boardContent) {
		String sql="update board set boardtitle='"+boardTitle+"', boardcontent='"+boardContent+"' where boardid='"+boardID+"' and memberid='"+memberID+"'";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public static boolean uploadFile(BoardFile file) {
		String sql="insert into bfile values(?,?,?)";
		try(Connection con=Connect.getInstance(); PreparedStatement ps=con.prepareStatement(sql);){
			ps.setString(1, file.getRealName());
			ps.setString(2, file.getVmName());
			ps.setString(3, file.getBoardID());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
