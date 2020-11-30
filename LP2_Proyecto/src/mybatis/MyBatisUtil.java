package mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	protected static final SqlSessionFactory FACTORY;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
			FACTORY = new SqlSessionFactoryBuilder().build(reader,"mysql5");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error: " + e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return FACTORY;
	}
}
