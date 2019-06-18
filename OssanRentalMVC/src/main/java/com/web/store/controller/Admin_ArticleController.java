package com.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model.ArticleBean;
import com.web.store.service.ArticleService;

@Controller
public class Admin_ArticleController {
	
	@Autowired
	ArticleService as ;
	
	@RequestMapping("/manageArticle")
	public String manageArticle(Model model ,
			@RequestParam(value="pageNo" , defaultValue="1") int pageNo) {
		
		as.setPageNo(pageNo);
		List<ArticleBean> list = as.getPageArticles();
		model.addAttribute("articleBean", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPage", as.getTotalPages());		
		
		
		return "manageArticle"; 
	}
	@RequestMapping("/removeArticle")
	public String deleteArticle(Model model, 
			@RequestParam(value="articleNo") int articleNo,@RequestParam(value="pageNo") int pageNo) {
			
			as.deleteArticle(articleNo);
			model.addAttribute("pageNo", pageNo);
			return "redirect:/manageArticle";
	}
}
