package cn.hnust.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

public class MybatisFirst {
	
	/**
	 * 通过id查询
	 * @throws IOException
	 */
	@Test
	public void findUserByIdTest() throws IOException {
		//mybatis配置文件
		String resource="SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//通过SqlSession操作数据库
		//第一个参数：映射文件中statement的id，等于namespace+"."+statement的id
		//第二个参数：指定和映射文件中匹配的parameterType类型的参数
		//sqlSession.selectOne结果是与映射文件中所匹配的resultType类型的对象,
		//selectOne只查询一条
		User user = sqlSession.selectOne("test.findUserById", 1);
		
		System.out.println(user);
		
		//释放资源
		sqlSession.close();
	}
	
	/**
	 * 通过名字模糊查询
	 * @throws IOException
	 */
	@Test
	public void findUserByUsernameTest() throws IOException {
		//mybatis配置文件
		String resource="SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//通过SqlSession操作数据库
		//第一个参数：映射文件中statement的id，等于namespace+"."+statement的id
		//第二个参数：指定和映射文件中匹配的parameterType类型的参数
		//查询多条用selectlist
		List<User> list  = sqlSession.selectList("test.findUserByUsername", "张");
		
		System.out.println(list);
		
		//释放资源
		sqlSession.close();
	}
	
	/**
	 * 插入用户
	 * @throws IOException
	 */
	@Test
	public void insertUserTest() throws IOException {
		//mybatis配置文件
		String resource="SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user =new User();
		user.setUsername("李棋");
		user.setSex("男");
		user.setBirthday(new Date());
		user.setAddress("长沙");
		//通过SqlSession操作数据库
		sqlSession.insert("test.insertUser", user);
		
		//提交事务
		sqlSession.commit();
		System.out.println("ID:"+user.getId());
		
		//释放资源
		sqlSession.close();
		
	}

	/**
	 * 删除用户
	 * @throws IOException
	 */
	@Test
	public void deleteUserTest() throws IOException {
		//mybatis配置文件
		String resource="SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//根据id删除用户
		sqlSession.delete("test.updateUser", 12);
		//提交事务
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	/**
	 * 更新用户
	 * @throws IOException
	 */
	@Test
	public void updateUserTest() throws IOException {
		//mybatis配置文件
		String resource="SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user =new User();
		user.setId(15);
		user.setUsername("李琪琪");
		user.setSex("女");
		user.setBirthday(new Date());
		user.setAddress("湘潭");
		//通过SqlSession操作数据库
		sqlSession.update("test.updateUser", user);
		
		//提交事务
		sqlSession.commit();
		
		//释放资源
		sqlSession.close();
		
	}
}