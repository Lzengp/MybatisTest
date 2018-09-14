package cn.hnust.mybatis.mapper;

import java.util.List;

import cn.hnust.mybatis.first.User;
import cn.hnust.mybatis.first.UserCustomer;
import cn.hnust.mybatis.first.UserQueryVo;

/**
 * mapper接口
 * @author 龙伟
 * 2018年7月29日
 */
public interface UserMapper {
	
	//多条件查询
	public List<UserCustomer> findUserList(UserQueryVo userQueryVo) throws Exception;
	//查询所有
	public List<User> findAllUser() throws Exception;
	
	//根据id查询所有用户
	public User findUserById(int id) throws Exception;
	
	//通过名字模糊查询
	public List<User> findUserByUsername(String name) throws Exception;
	
	//插入用户
	public void  insertUser(User user) throws Exception;
	
	//更新用户
	public void updateUser(User user) throws Exception;
	
	//根据id删除用户
	public void deleteUser(int id) throws Exception;
}
