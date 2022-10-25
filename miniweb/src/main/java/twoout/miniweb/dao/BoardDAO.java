package twoout.miniweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import twoout.miniweb.dto.Board;

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
	synchronized public static ArrayList<Board> listBoard(){
		String sql="select * from board order by createdate";
		try(Connection con=Connect.getInstance();ResultSet rs=con.prepareStatement(sql).executeQuery();){
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
		String sql="select * from board where boardid='"+boardID+"'";
		try(Connection con=Connect.getInstance();ResultSet rs=con.prepareStatement(sql).executeQuery();){
			if(rs.next())
				return new Board(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),Integer.parseInt(rs.getString(6)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	synchronized public static boolean deleteBoard(String boardID,String memberID) {
		String sql="delete from board where boardid='"+boardID+"' and memberid='"+memberID+"'";
		try(Connection con=Connect.getInstance();){
			if(con.prepareStatement(sql).executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public static boolean updateBoard(String boardID,String memberID, String boardTitle, String boardContent) {
		String sql="update board set boardtitle='"+boardTitle+"', boardcontent='"+boardContent+"' where boardid='"+boardID+"' and memberid='"+memberID+"'";
		try(Connection con=Connect.getInstance();){
			if(con.prepareStatement(sql).executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
