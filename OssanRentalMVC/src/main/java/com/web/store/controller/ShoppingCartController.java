package com.web.store.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.store.model.OrderItem;
import com.web.store.model.OssanBean;
import com.web.store.model.ShoppingCart;
import com.web.store.service.OssanService;

@Controller
public class ShoppingCartController {

	@Autowired
	OssanService service;

	@RequestMapping("/cartContent")
	public String cartContent(Model model, HttpSession session) {

		ShoppingCart cart = null;

		cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		model.addAttribute("cart", cart);

		return "shoppingCart";
	}

	@RequestMapping("/addToCart/{ossanNo}")
	public String addToCart(HttpSession session, @PathVariable("ossanNo") int ossanNo, HttpServletRequest request) {

		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			session.setAttribute("ShoppingCart", cart);
		}

		int price = 300; // 預設一小時都是300
		OssanBean ob = service.getOssan(ossanNo);
		int hour = Integer.valueOf(request.getParameter("hours"));
		OrderItem oit = new OrderItem(ossanNo, ob.getEmail(), ob.getName(), ob.getNickname(), hour, price);
		cart.addToCart(ossanNo, oit);

		return "redirect:/personalPage?ossanId=" + ossanNo;
	}
	
	@RequestMapping(value ="/updateItem/{ossanNo}/{qty}" , method = RequestMethod.POST)
	public String updateItem(@PathVariable("ossanNo") int ossanNo , HttpSession session ,@PathVariable("qty") int qty) {
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		cart.modifyQty(ossanNo, qty);
		
		return "redirect:/cartContent";
	}
	
	@RequestMapping("/deleteItem/{ossanNo}")
	public String deleteItem(@PathVariable("ossanNo") int ossanNo , HttpSession session) {
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		cart.deleteOssan(ossanNo);
		if(cart.getContent().size() == 0) {
			session.invalidate();
		}
		return "redirect:/cartContent";
	}
}
























