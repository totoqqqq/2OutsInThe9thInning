package twoout.miniweb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		try(Connect con=new Connect();){
			String sql="select * from member where memberid='"+account.getMemberID()
			+"'and memberpw='"+account.getMemberPW()+"'";
			ResultSet rs=con.pstmt(sql).executeQuery();
			if(rs.next()==false)
				return null;
			return new Member(rs.getNString(1),rs.getString(2),rs.getNString(3),rs.getString(4),rs.getNString(5),rs.getString(6),rs.getNString(7),rs.getString(8),rs.getNString(9));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	synchronized public boolean SignUp(Member account) {
		try(Connect con=new Connect();){
			String sql="insert into member values(?,?,?,?,?,?,?,?,default)";
			PreparedStatement signUp=con.pstmt(sql);
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
}