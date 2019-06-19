package com.web.store.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.store.model.OrderItem;
import com.web.store.model.OssanBean;
import com.web.store.model.ShoppingCart;
import com.web.store.service.OssanService;

@Controller
public class ShoppingCartController {
	
	@Autowired
	OssanService service;
	
	
	@RequestMapping("/cartContent")
	public String cartContent(Model model , HttpSession session) {
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		model.addAttribute("cart", cart.getContent());
		
		return "shoppingCart";
	}
	
	@RequestMapping("/addToCart/{ossanNo}")
	public String addToCart(Model model ,HttpSession session, @PathVariable("ossanNo") int ossanNo ,HttpServletRequest request) {
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			session.setAttribute("ShoppingCart", cart);   
		}
		
		int price = 300 ; //預設一小時都是300
		OssanBean ob = service.getOssan(ossanNo);
		int hour = Integer.valueOf(request.getParameter("hours"));
		OrderItem oit = new OrderItem(ossanNo, ob.getEmail(), ob.getName(), ob.getNickname(), hour , price);
		cart.addToCart(ossanNo, oit);
		model.addAttribute("OssanBean", ob);
		
		return "personalPage";
	}
}







