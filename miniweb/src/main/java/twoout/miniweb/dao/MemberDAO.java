package twoout.miniweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import twoout.miniweb.dto.Member;

public class MemberDAO {
	public static MemberDAO md=null;
	private MemberDAO() {
	}
	synchronized public static MemberDAO getMemberDAO() {
		if(md==null)
			md=new MemberDAO();
		return md;
	}
	synchronized public Member Login(Member account){
		String sql="select * from member where memberid='"+account.getMemberID()
		+"'and memberpw='"+account.getMemberPW()+"'";
		try(Connection con=Connect.getInstance();ResultSet rs=con.prepareStatement(sql).executeQuery();){
			if(rs.next()==false)
				return null;
			return new Member(rs.getNString(1),rs.getString(2),rs.getNString(3),rs.getString(4),rs.getNString(5),rs.getString(6),rs.getNString(7),rs.getString(8),rs.getNString(9));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	synchronized public boolean SignUp(Member account) {
		try(Connection con=Connect.getInstance();){
			String sql="insert into member values(?,?,?,?,?,?,?,?,default)";
			PreparedStatement signUp=con.prepareStatement(sql);
			signUp.setString(1, account.getMemberID());
			signUp.setString(2, account.getMemberPW());
			signUp.setString(3, account.getNickName());
			signUp.setString(4, account.getPhone());
			signUp.setString(5, account.getEmail());
			signUp.setString(6, account.getZipcode());
			signUp.setString(7, account.getAddress());
			signUp.setString(8, account.getBuliding());
			signUp.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	synchronized public ArrayList<Member> MemberView(){
		try(Connection con=Connect.getInstance();){
			String sql="select * from member";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			ArrayList<Member> members = new ArrayList<Member>();
			while(rs.next())
				members.add(new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
			return members;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	synchronized public boolean IDchecked(String id) {
		String sql="select * from member where memberid='"+id+"'";
		try(Connection con=Connect.getInstance();ResultSet rs=con.prepareStatement(sql).executeQuery()){
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public boolean deleteID(String id) {
		String sql="delete from member where memberid='"+id+"'";
		try(Connection con=Connect.getInstance();){
			if(con.prepareStatement(sql).executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}