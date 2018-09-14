package cn.hnust.mybatis.mapper;

import java.util.List;

import cn.hnust.mybatis.po.Orderdetail;
import cn.hnust.mybatis.po.Orders;
import cn.hnust.mybatis.po.OrdersCustomer;

/**
 * OrdersMapperCustomer接口
 * @author 龙伟
 * 2018年8月1日
 */
public interface OrdersMapperCustomer {
	//查询order和customer的user，sex，address
	public List<OrdersCustomer> findOrderUser() throws Exception;
	
	//用resultMap实现一对一映射查询
	public  List<Orders>  findOrderUserResultMap() throws Exception;
	
	//用resultMap实现一对多映射查询
	public  List<Orderdetail>  findOrdersAndOrderdetailResultMap() throws Exception;
}
