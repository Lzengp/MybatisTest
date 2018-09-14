package cn.hnust.mybatis.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.hnust.mybatis.first.User;
import cn.hnust.mybatis.mapper.UserMapper;


/**
 * Dao层的测试类
 * @author 龙伟
 * 2018年7月28日
 */
public class UserDaoImplTest {
	
	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp()  throws Exception{
		/*
		 * 创建SesssionFactory
		 */
		//mybatis配置文件
		String resource="SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}
	
	
	@Test
	public void findUserByIdTest() throws Exception{
		//创建UserDao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		
		//调用UserDao的方法
		User user = userDao.findUserById(2);	
		
		System.out.println(user);
	}
	
	//一级缓存测试
	@Test
	public void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();//创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//动态代理对象
		
		//使用一个sqlSession
		//第一次发起请求，查询id为1的用户
		User user1 =  userMapper.findUserById(1);
		System.out.println("user1："+user1);
		/*
		 * 如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存
		 * 这样做的目的是为了让缓存中的数据是最新的，避免脏读
		 */
		//更新用户
		User user = new User();
		user.setId(1);
		user.setUsername("张四");
		userMapper.updateUser(user);
		//提交事务，清空缓存
		sqlSession.commit();
		//第二次发起请求，查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println("user2："+user2);
		
		sqlSession.close();
		
	}
	
	//二级级缓存测试
	@Test
	public void testCache2() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();//创建代理对象
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);//动态代理对象
		//sqlSession1
		//第一次发起请求，查询id为1的用户
		User user1 =  userMapper1.findUserById(1);
		System.out.println("user1："+user1);
		sqlSession1.close();
		//sqlSession3
		//更新用户
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		User user = userMapper3.findUserById(1);
		user.setUsername("张三");
		userMapper3.updateUser(user);
		//提交事务，清空缓存
		sqlSession3.commit();
		//第二次发起请求，查询id为1的用户
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = userMapper2.findUserById(1);
		System.out.println("user2："+user2);	
		sqlSession2.close();
		
	}
	

	
}
