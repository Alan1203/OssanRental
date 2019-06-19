package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.dao.OrderDao;
import com.web.store.model.OrderBean;
import com.web.store.model.OrderItemBean;
import com.web.store.service.OrderService;

@Service
public class OrderServiceImplHibernate implements OrderService {

	@Autowired
	private OrderDao odao;

	public OrderServiceImplHibernate() {
		
	}
	@Transactional
	@Override 
	// 這是一個交易
	public void persistOrder(OrderBean ob) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			odao.insertOrder(ob);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) tx.rollback();
//			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());
//			throw new RuntimeException(e);
//		} 

	}
	
	@Transactional
//	@Override
	// 本方法為過渡版本
	public OrderBean getOrder(int orderNo) {
		OrderBean  bean = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		    bean = odao.getOrder(orderNo);
//		    tx.commit();
//		} catch (Exception e) {
//			if (tx != null) tx.rollback();
//			throw new RuntimeException(e);
//		} 
		return bean;
	}


	@Transactional
	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			list = odao.getAllOrders();
//		    tx.commit();
//		} catch (Exception e) {
//			if (tx != null) tx.rollback();
//			throw new RuntimeException(e);
//		} 
		return list;
	}
	
	@Transactional
	@Override
	public List<OrderItemBean> getOssanOrder(int ossanNo) {
		List<OrderItemBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			list = odao.getOssanOrder(ossanNo);
//		    tx.commit();
//		} catch (Exception e) {
//			if (tx != null) tx.rollback();
//			throw new RuntimeException(e);
//		} 
		return list;
	}

}
