package com.web.store.controller;

import java.io.File;
import java.sql.Blob;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.model.OssanBean;
import com.web.store.service.OssanService;

import _00_init.util.SystemUtils2018;

@Controller
public class PersonalMaintainController {
	
	@Autowired
	OssanService service;
	@Autowired
	ServletContext context ;
	
	@RequestMapping(value = "/personalUpdate", method = RequestMethod.GET)
	public String personalUpdate(Model model , HttpSession session) {
		
		OssanBean ob = (OssanBean)session.getAttribute("LoginOK");
		if(ob.getIntro() != null)
			ob.setsIntro(SystemUtils2018.clobToStringWindows(ob.getIntro()));
		model.addAttribute("OssanBean", ob);
		
		return "personalUpdate";
	}
	
	@RequestMapping(value = "/personalUpdate", method = RequestMethod.POST)
	public String processPersonalUpdate(@ModelAttribute("OssanBean") OssanBean ob) {
		//取出原本的OssanBean
		OssanBean osb = service.getOssan(ob.getOssanNo());
		ob.setArticleBean(osb.getArticleBean());
		ob.setIntro(SystemUtils2018.stringToClob(ob.getsIntro()));
		
		String ext = "" ;
		String rootDirectory = "";
		MultipartFile ossanImg = null ;
		if(ob.getOssanImg() != null && !ob.getOssanImg().isEmpty()) {
			ossanImg = ob.getOssanImg();
			String originalFilename = ossanImg.getOriginalFilename();
			ob.setFileName(originalFilename);
			ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			rootDirectory = context.getRealPath("/");
			try{
				byte[] b = ossanImg.getBytes();
				Blob blob = new SerialBlob(b);
				ob.setOssanImage(blob);
				File imageFolder = new File(rootDirectory ,"images");
				if(!imageFolder.exists()) imageFolder.mkdirs();
				File file = new File(imageFolder , ob.getOssanNo() + ext);
				ossanImg.transferTo(file);
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("圖片上傳發生異常" + e.getMessage());
			}
		}else {
			ob.setOssanImage(osb.getOssanImage());
		}
		
		service.updateOssan(ob);

		return "personalUpdate";
	}
}
