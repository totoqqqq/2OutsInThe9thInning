package pause.twoout.miniweb.dao;

import java.util.ArrayList;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.session.SqlSessionFactory;

//import mybatis.pause.Mybatis;
public class MemberDAO extends DefaultObjectFactory {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory sf=null;
	MemberDAO() {
//		if(sf==null)
//			sf=Mybatis.getSqlSessionFactory();
	}
	synchronized public ArrayList<Member> LoginCheck(twoout.miniweb.dto.Member member){
		
		return null;
	}
}
