package cn.hnust.mybatis.dao;

import cn.hnust.mybatis.first.User;

/**
 * dao接口，用户管理
 * @author 龙伟
 * 2018年7月28日
 */
public interface UserDao {


	//根据id查询用户
	public User  findUserById(int id) throws Exception;
	
	//添加用户信息
	public void insertUser(User user) throws Exception;
	
	//根据id删除用户
	public void deleteUser(int id) throws Exception;
	
	
}
