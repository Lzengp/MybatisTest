package cn.hnust.mybatis.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.hnust.mybatis.first.User;
import cn.hnust.mybatis.first.UserCustomer;
import cn.hnust.mybatis.first.UserQueryVo;

class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;
	@BeforeEach
	public void setUp() throws Exception {
		/*
		 * 创建SesssionFactory
		 */
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}
	/*
	 * 多条件查询
	 */
	@Test
	public void testfindUserList() throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mabatis自动生成mapper代理对象
		UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
		//创建包装对象，设置查询条件
		
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustomer userCustomer = new UserCustomer();
		ArrayList<Integer> ids =  new ArrayList<Integer>();
		//添加多个id
		ids.add(2);
		ids.add(3);
		ids.add(23);
		userCustomer.setSex("男");
		//userCustomer.setAddress("上海");
		userQueryVo.setIds(ids);
		userQueryVo.setUserCustomer(userCustomer);
		//调用方法
		List<UserCustomer> list = userMapper.findUserList(userQueryVo);
		System.out.println(list);
		sqlSession.close();
	}
	/*
	 * 查询所有
	 */
	@Test
	public void testfindAllUser() throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mabatis自动生成mapper代理对象
		UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
		//调用方法
		List<User> user = userMapper.findAllUser();
		System.out.println(user);
		sqlSession.close();
	}
	/*
	 * 根据id查询
	 */
	@Test
	public void testfindUserById() throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mabatis自动生成mapper代理对象
		UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
		//调用方法
		User user = userMapper.findUserById(2);
		System.out.println(user);
		sqlSession.close();
	}
	/*
	 * 通过名字模糊查询
	 */
	@Test
	public void testfindUserByUsername() throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mabatis自动生成mapper代理对象
		UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
		//调用方法
		List<User> user = userMapper.findUserByUsername("李");
		
		System.out.println(user);
		sqlSession.close();
	}
	/*
	 * 插入用户
	 */
	@Test
	public void testInsertUser() throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mabatis自动生成mapper代理对象
		UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
		//添加新用户信息
		User user = new User();
		user.setUsername("王五");
		user.setSex("男");
		user.setAddress("上海");
		//调用方法
		 userMapper.insertUser(user);
		 sqlSession.commit();
		 System.out.println(user.getId());
		 sqlSession.close();

	}
	/*
	 * 更新用户
	 */
	@Test
	public void testUpdateUser() throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mabatis自动生成mapper代理对象
		UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
		//添加新用户信息
		User user = new User();
		user.setId(15);
		user.setUsername("王五");
		user.setSex("男");
		user.setAddress("上海");
		//调用方法
		 userMapper.updateUser(user);
		 sqlSession.commit();
		 sqlSession.close();

	}
	/*
	 * 根据id删除用户
	 */
	@Test
	public void testDeleteUser() throws Exception {
		SqlSession sqlSession =sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		userMapper.deleteUser(16);
		sqlSession.commit();
		sqlSession.close();
	}
}
