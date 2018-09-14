package cn.hnust.mybatis.po;

/**
 * Orders扩展类，通过此类映射订单和用户查询的结果，一般谁属性多就继承谁，这样少些
 * @author 龙伟
 * 2018年8月1日
 */

public class OrdersCustomer  extends Orders{
	
	/*
	 * user.username,
	 * user.sex,
	 * user.address 
	 */
	private String username;
	private String sex;
	private String address;
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
