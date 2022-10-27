package twoout.miniweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import twoout.miniweb.dto.Board;
import twoout.miniweb.dto.BoardFile;
import twoout.miniweb.dto.BoardReply;

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
	synchronized public static boolean createBoard(Board board,ArrayList<BoardFile> file) {
		String sql="insert into board values(?,board_id_seq.nextval,?,?,systimestamp,default)";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
			ps.setString(1, board.getMemberID());
			ps.setString(2, board.getBoardTitle());
			ps.setString(3, board.getBoardContent());
			if(ps.executeUpdate()==1) {
				sql="insert into boardfile values(?,?,board_id_seq.currval)";
			for(int i=0;i<=file.size()+1;i++) {
				if(i==file.size())
					return true;
				try(PreparedStatement innerps=con.prepareStatement(sql);){
					innerps.setString(1, file.get(i).getRealName());
					innerps.setString(2, file.get(i).getVmName());
					if(innerps.executeUpdate()!=1)
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public static ArrayList<Board> listBoard(String page){
		String sql="select count(*) from board";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
			int boardCount=1;
			if(rs.next())
			boardCount=(Integer.parseInt(rs.getString(1)))-((Integer.parseInt(page)-1)*10);
			sql="select memberid,boardid,boardtitle,boardcontent,createdate,viewcount from(select rownum as rum, memberid,boardid,boardtitle,boardcontent,createdate,viewcount from(select * from board order by createdate))where rum>="+boardCount+"-9 and rum<="+boardCount;
			try(PreparedStatement innerPs=con.prepareStatement(sql);ResultSet innerRs=innerPs.executeQuery();){
				ArrayList<Board> list = new ArrayList<Board>();
				while(innerRs.next())
					list.add(new Board(innerRs.getString(1),innerRs.getString(2),innerRs.getString(3),"",innerRs.getTimestamp(5),Integer.parseInt(innerRs.getString(6))));
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	synchronized public static int listBoardPage() {
		String sql="select count(*) from board";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
			if(rs.next())
				return (Integer.parseInt(rs.getString(1))+9)/10;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
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
	synchronized public static ArrayList<BoardFile> viewBoardFile(String boardID){
		String sql="select * from boardfile where boardID='"+boardID+"'";
		ArrayList<BoardFile> file=new ArrayList<BoardFile>();
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
			if(rs.next()) {
				do
					file.add(new BoardFile(rs.getString(1),rs.getString(2),rs.getString(3)));
				while(rs.next());
				return file;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	synchronized public static ArrayList<BoardReply> viewBoardReply(String boardID){
		String sql="select * from boardreply where boardID='"+boardID+"' order by createdate asc";
		ArrayList<BoardReply> reply=new ArrayList<BoardReply>();
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
			if(rs.next()) {
				do
					reply.add(new BoardReply(rs.getString(1),rs.getTimestamp(2),rs.getString(3),rs.getString(4)));
				while(rs.next());
				return reply;
			}
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
	synchronized public static boolean deleteBoardReply(Timestamp createdate,String memberID) {
		String sql="delete from boardreply where createdate='"+createdate+"' and memberid='"+memberID+"'";
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
	synchronized public static boolean updateBoardReply(Timestamp createdate,String replayContent,String memberID) {
		String sql="update boardreply set replyContent='"+replayContent+"' where createdate='"+createdate+"' and memberid='"+memberID+"'";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public static boolean uploadFile(BoardFile file) {
		String sql="insert into boardfile values(?,?,?)";
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
	synchronized public static boolean createReply(BoardReply reply) {
		String sql="insert into boardreply values(?,default,?,?)";
		try(Connection con=Connect.getInstance(); PreparedStatement ps=con.prepareStatement(sql);){
			ps.setString(1, reply.getReplayContent());
			ps.setString(2, reply.getMemberID());
			ps.setString(3, reply.getBoardID());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public static String checkDownload(BoardFile boardFile) {
		String sql="select * from boardfile where realname='"+boardFile.getRealName()+"' and vmname='"+boardFile.getVmName()+"' and boardid='"+boardFile.getBoardID()+"'";
		try(Connection con=Connect.getInstance(); PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery()){
			if(rs.next())
				return boardFile.getVmName()+"_"+boardFile.getRealName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}