package mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Mybatis {
	public final static String RESOUCE="mybatis/Mybatis-config.xml";
	private static SqlSessionFactory ssf=null;
	public synchronized static SqlSessionFactory getSqlSessionFactory() {
		if(ssf==null) {
			try(InputStream is=Resources.getResourceAsStream(RESOUCE);){
				ssf=new SqlSessionFactoryBuilder().build(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ssf;
	}
}