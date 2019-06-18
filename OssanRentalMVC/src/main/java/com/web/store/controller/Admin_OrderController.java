package com.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.store.model.OrderBean;
import com.web.store.service.OrderService;

@Controller
public class Admin_OrderController {

	@Autowired
	OrderService os ;
	

	
	@RequestMapping("/manageOrder")
	public String manageOrder(Model model) {
		
		List<OrderBean> list = os.getAllOrders();
		
		model.addAttribute("list", list);
		
		return "manageOrder";
	}
	
	@RequestMapping("/orderDetail/{orderNo}")
	public String orderDetail(Model model , @PathVariable("orderNo") int orderNo) {
		OrderBean orderBean = os.getOrder(orderNo);
		
		model.addAttribute("list", orderBean.getOrderItemBean());
		
		return "orderDetail";
	}
}


















