package twoout.miniweb;

import twoout.miniweb.dao.MemberDAO;
import twoout.miniweb.model.Member;

public class TestRun {
	public static void main(String[] args) {
		//별도의 겟터 셋터 세팅없이 바로 연동.
//		Member login = new MemberDAO().Login(new Member("admin","oracle"));
//		System.out.println(login.getMemberID()+login.getMemberPW()+login.getNickName());
		boolean signup = new MemberDAO().SignUp(new Member("guest0003","oracle","TC계정010121","010-0004-0002","ssd12d@naver.com","00002","x","x",null));
		System.out.println(signup);
		
	}
}