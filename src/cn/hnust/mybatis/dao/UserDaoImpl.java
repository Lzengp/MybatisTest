package cn.hnust.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.hnust.mybatis.first.User;

/**
 * 实现类
 * @author 龙伟
 * 2018年7月28日
 */
public class UserDaoImpl implements UserDao {

	//需要向实现类中注入SqlSessionFactory
	//这是使用构造方法
	private SqlSessionFactory sqlSessionFactory;
	public  UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	/*
	 * 根据id查询
	 */
	@Override
	public User findUserById(int id) throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//执行查询
		User user = sqlSession.selectOne("test.findUserById",id);
		//关闭连接
		sqlSession.close();
		
		return user;
	}
	/*
	 *根据id更新
	 */
	@Override
	public void insertUser(User user) throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//执行
		sqlSession.insert("test.insertUser",user);
		//关闭连接
		sqlSession.close();
		
	}
	/*
	 * 删除
	 */
	@Override
	public void  deleteUser(int id) throws Exception {
		//创建sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//执行
		sqlSession.delete("test.deleteUser",id);
		//提交事务
		sqlSession.commit();
		//关闭连接
		sqlSession.close();
	
	}
/*
 * 
 */
}
