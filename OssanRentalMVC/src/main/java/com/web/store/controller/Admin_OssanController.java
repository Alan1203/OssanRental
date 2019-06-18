package com.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model.OssanBean;
import com.web.store.service.OssanService;

@Controller
public class Admin_OssanController {
	
	@Autowired
	OssanService os ;
	
	@RequestMapping("manageOssan")
	public String manageOssan(Model model , @RequestParam(value = "pageNo" ,defaultValue = "1") int pageNo) {
		

		os.setPageNo(pageNo);
		List<OssanBean> list = os.getPageOssansArea("twAll");
		model.addAttribute("OssanBean", list);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("totalPage", os.getTotalPagesArea("twAll"));
		
		return "manageOssan";
	}
	
	@RequestMapping("/removeOssan")
	public String deleteArticle(Model model, 
			@RequestParam(value="ossanNo") int ossanNo,@RequestParam(value="pageNo") int pageNo) {
			
			os.deleteOssan(ossanNo);
			model.addAttribute("pageNo", pageNo);
			return "redirect:/manageOssan";
	}
}

