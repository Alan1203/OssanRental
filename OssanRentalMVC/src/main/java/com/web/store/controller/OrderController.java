package com.web.store.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.store.model.OrderBean;
import com.web.store.model.OrderItem;
import com.web.store.model.OrderItemBean;
import com.web.store.model.OssanBean;
import com.web.store.model.ShoppingCart;
import com.web.store.service.OrderService;
import com.web.store.service.OssanService;

@Controller
public class OrderController {
	
	@Autowired
	OssanService os ;
	@Autowired
	OrderService ors;
	
	@RequestMapping(value = "/orderConfirm", method = RequestMethod.GET )
	public String orderConfirm(Model model , HttpSession session) {
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		OrderBean ob = new OrderBean();
		model.addAttribute("cart", cart);
		model.addAttribute("orderBean", ob);

		return "orderConfirm";
	}

	@RequestMapping(value = "/orderConfirm", method = RequestMethod.POST)
	public String processOrderConfirm(Model model, @ModelAttribute("orderBean") OrderBean ob, HttpSession session) {
			
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		ob.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
		ob.setTotalAmount(cart.getSubtotal());
		Set<OrderItemBean> items = new HashSet<OrderItemBean>();
		Set<Integer> set = cart.getContent().keySet();
		
		for (Integer k : set) {
			
			
			OrderItem oi = cart.getContent().get(k);
			OssanBean ossan = os.getOssan(oi.getPkey()); //取出大叔物件放入訂單細節orderItemBean
			OrderItemBean oib = new OrderItemBean(null, oi.getQty(),oi.getPrice());
			oib.setOrderBean(ob);
			oib.setOssanBean(ossan);
			items.add(oib);
		}
		ob.setOrderItemBean(items);
		ors.persistOrder(ob);
		model.addAttribute("orderBean", ob);
		
		return "thanksForOrdering";
	}
	
	@RequestMapping("/orderList/{ossanNo}")
	public String orderList(Model model , @PathVariable("ossanNo") int ossanNo) {
		List<OrderItemBean> list = null ;
		
		list = ors.getOssanOrder(ossanNo);
		model.addAttribute("oib", list);
		
		return "orderList";
	}
}




















