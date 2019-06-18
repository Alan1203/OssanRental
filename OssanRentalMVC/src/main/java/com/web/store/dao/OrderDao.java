package com.web.store.dao;

import java.util.List;

import com.web.store.model.OrderBean;
import com.web.store.model.OrderItemBean;

public interface OrderDao {

	void insertOrder(OrderBean ob);

	OrderBean getOrder(int orderNo);

	List<OrderBean> getAllOrders();

	List<OrderItemBean> getOssanOrder(int ossanNo);

}