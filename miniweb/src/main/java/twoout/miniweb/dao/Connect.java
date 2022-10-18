package twoout.miniweb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Connect {
	private static Context ctx=null;
	private static DataSource ds=null;
	private Connect() {

	}
	public static Connection getInstance() {
		try {
			if(ds==null) {
				ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xman");
			}
			return ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}