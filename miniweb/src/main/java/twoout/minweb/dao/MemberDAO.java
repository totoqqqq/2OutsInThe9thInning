package twoout.minweb.dao;

import java.util.ArrayList;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.Mybatis;
public class MemberDAO {
	private SqlSessionFactory sf=null;
	public MemberDAO() {
		this.sf=Mybatis.getSqlSessionFactory();
	}
	public ArrayList<Member> searchAll(){
		return null;
	}
	public ArrayList<Member> search(){
		return null;
	}
}
