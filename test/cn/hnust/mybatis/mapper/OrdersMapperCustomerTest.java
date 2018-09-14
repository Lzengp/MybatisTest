package cn.hnust.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.hnust.mybatis.po.Orderdetail;
import cn.hnust.mybatis.po.Orders;
import cn.hnust.mybatis.po.OrdersCustomer;

/**
 * OrdersUser测试类
 * @author 龙伟
 * 2018年8月1日
 */
class OrdersMapperCustomerTest {

	private SqlSessionFactory sqlSessionFactory;
	/*
	 * 创建Session工厂
	 */
	@BeforeEach
	public void setUp() throws Exception {
		//得到mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
	}

	/*
	 * resultTyep实现一对一映射查询
	 */
	@Test
	public void testOrdersCustomer() throws Exception {
		//通过工厂创建session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建OrdersMapperCustomer对象，mybatis自动生成mapper代理对象
		OrdersMapperCustomer ordersMapperCustomer = sqlSession.getMapper(OrdersMapperCustomer.class);
		//调用方法
		List<OrdersCustomer> list = ordersMapperCustomer.findOrderUser();
		System.out.println(list);
		//关闭sqlSession
		sqlSession.close();
	}
	
	/*
	 * resultMap实现一对一映射查询
	 */
	@Test
	public void testOrdersUserResultMap() throws Exception {
		//通过工厂创建session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建OrdersMapperCustomer对象，mybatis自动生成mapper代理对象
		OrdersMapperCustomer ordersMapperCustomer = sqlSession.getMapper(OrdersMapperCustomer.class);
		//调用方法
		List<Orders> list = ordersMapperCustomer.findOrderUserResultMap();
		System.out.println(list);
		//关闭sqlSession
		sqlSession.close();
	}

	/*
	 * resultMap实现一对多映射查询
	 */
	@Test
	public void testFindOrdersAndOrderdetailResultMap() throws Exception {
		//通过工厂创建session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建OrdersMapperCustomer对象，mybatis自动生成mapper代理对象
		OrdersMapperCustomer ordersMapperCustomer = sqlSession.getMapper(OrdersMapperCustomer.class);
		//调用方法
		List<Orderdetail> list = ordersMapperCustomer.findOrdersAndOrderdetailResultMap();
		System.out.println(list);
		//关闭sqlSession
		sqlSession.close();
	}
}
