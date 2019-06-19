package com.web.store.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.dao.OrderDao;
import com.web.store.model.OrderBean;
import com.web.store.model.OrderItemBean;

// 本類別
//   1.新增一筆訂單到orders表格
//   2.查詢orders表格內的單筆訂單
@Repository
public class OrderDaoImpl_Hibernate implements OrderDao {

	@Autowired
	SessionFactory factory;
	int orderNo = 0;

	public OrderDaoImpl_Hibernate() {
		
	}

	@Override
	public void insertOrder(OrderBean ob) {
		Session session = factory.getCurrentSession();
		session.save(ob);
	}

	public OrderBean getOrder(int orderNo) {
		OrderBean ob = null;
		Session session = factory.getCurrentSession();
		ob = session.get(OrderBean.class, orderNo);
		return ob;
	}

//	public String getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(String memberId) {
//		this.memberId = memberId;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
		String hql = "FROM OrderBean";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).list();
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItemBean> getOssanOrder(int ossanNo) {
		List<OrderItemBean> list= null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderItemBean oib WHERE oib.ossanBean.ossanNo = :mid";
		list = session.createQuery(hql).setParameter("mid", ossanNo).list();
		return list;
	}

}