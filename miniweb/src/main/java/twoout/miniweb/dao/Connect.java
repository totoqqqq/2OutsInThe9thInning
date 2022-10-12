package twoout.miniweb.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class Connect implements AutoCloseable{
	private final String ID="xman", PW="xman";
	private final String DBURL="jdbc:oracle:thin:@18.183.11.208:1521:xe",DBdriver="oracle.jdbc.driver.OracleDriver";
	private Connection con;
	private PreparedStatement ps;
	private PoolDataSource pds;
	Connect(){
		try {//UPS로 구현한 커넥션풀.
			pds=PoolDataSourceFactory.getPoolDataSource();
			pds.setConnectionFactoryClassName(DBdriver);
			pds.setURL(DBURL);
			pds.setUser(ID);
			pds.setPassword(PW);
			pds.setInitialPoolSize(1);
			pds.setMinPoolSize(1);
			pds.setMaxPoolSize(80);
			pds.setMaxConnectionReuseTime(3600);
			this.con=pds.getConnection();
		} catch (SQLException e) {
//			System.out.println("오라클 호출 실패");
			e.printStackTrace();
		}
	}
	PreparedStatement pstmt(String sql) {
		try {
			ps=this.con.prepareCall(sql);
			return ps;
		} catch (SQLException e) {
//			System.out.println("sql 구문 오류");
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void close() throws Exception {
		this.ps.close();
		this.con.close();
	}
}