package cn.hnust.mybatis.first;


import java.util.List;

/**
 * pojo包装类
 * @author 龙伟
 * 2018年7月29日
 */
public class UserQueryVo {
	
	//定义一个list对象，用于存储多个id
	private List<Integer> ids ;
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	//自定义用户扩展类
	private UserCustomer userCustomer;

	public UserCustomer getUserCustomer() {
		return userCustomer;
	}

	public void setUserCustomer(UserCustomer userCustomer) {
		this.userCustomer = userCustomer;
	}
	
}
