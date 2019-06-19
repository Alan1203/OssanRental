package com.web.store.controller;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.model.ArticleBean;
import com.web.store.model.OssanBean;
import com.web.store.service.ArticleService;

import _00_init.util.SystemUtils2018;

@Controller
public class ArticleMaintainController{
	
	@Autowired
	ArticleService service;
	@Autowired
	ServletContext context;
	
	OssanBean mob = null ;
	
	@RequestMapping(value="/articleConfirm" , method=RequestMethod.GET)
	public String listArticle(Model model , HttpSession session) {
		List<ArticleBean> list = null;
		mob = (OssanBean) session.getAttribute("LoginOK");
		list = service.getPageArticles(mob.getOssanNo());
		model.addAttribute("articleBean", list);
		return "articleConfirm";
	}

	@RequestMapping(value="/deleteArticle", method=RequestMethod.POST)
	public String deleteArticle(Model model, 
			@RequestParam(value="artNo") int articleNo) {
			
			service.deleteArticle(articleNo);
			return "redirect:/articleConfirm";
	}
	
	@RequestMapping(value = "/articleUpdate/{artNo}", method = RequestMethod.GET)
	public String articleUpdate(Model model , @PathVariable("artNo") Integer artNo) {
		ArticleBean ab = null;
		if(artNo != 0) {
			ab = service.getArticle(artNo);
		}else {
			ab = new ArticleBean();
		}
		model.addAttribute("article", ab);
		return "articleModify";
	}
	
	@RequestMapping(value = "/articleUpdate/{artNo}", method = RequestMethod.POST)
	public String processUpdate(Model model ,@ModelAttribute("article") ArticleBean ab) {
		
		Clob clob = SystemUtils2018.stringToClob(ab.getsContent());
		String ext = "" ;
		String rootDirectory = "";
		MultipartFile articleImg = null ;
		
		if(ab.getArticleNo() != null) {
			if(ab.getArticleImg() != null && !ab.getArticleImg().isEmpty()) {
				//取出舊的
				ArticleBean oab = service.getArticle(ab.getArticleNo());
				articleImg = ab.getArticleImg();
				String originalFilename = articleImg.getOriginalFilename();
				oab.setFileName(originalFilename);
				ext = originalFilename.substring(originalFilename.lastIndexOf("."));
				rootDirectory = context.getRealPath("/");
				try{
					byte[] b = articleImg.getBytes();
					Blob blob = new SerialBlob(b);
					oab.setArticleImage(blob);
					File imageFolder = new File(rootDirectory ,"articleImages");
					if(!imageFolder.exists()) imageFolder.mkdirs();
					File file = new File(imageFolder , oab.getArticleNo() + ext);
					articleImg.transferTo(file);
				}catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("圖片上傳發生異常" + e.getMessage());
				}
				oab.setTitle(ab.getTitle());
				oab.setContent(clob);
				service.updateArticle(oab);
				return "redirect:/articleConfirm";
			}
		}else {
			
			//把其他東西補進新文章
			ab.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			ab.setContent(clob);
			
			if(ab.getArticleImg() != null && !ab.getArticleImg().isEmpty()) {
				
				articleImg = ab.getArticleImg();
				String originalFilename = articleImg.getOriginalFilename();
				ab.setFileName(originalFilename);
				ext = originalFilename.substring(originalFilename.lastIndexOf("."));
				rootDirectory = context.getRealPath("/");
				
				try{
					byte[] b = articleImg.getBytes();
					Blob blob = new SerialBlob(b);
					ab.setArticleImage(blob);
					File imageFolder = new File(rootDirectory ,"articleImages");
					if(!imageFolder.exists()) imageFolder.mkdirs();
					File file = new File(imageFolder , ab.getArticleNo() + ext);
					articleImg.transferTo(file);
				}catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("圖片上傳發生異常" + e.getMessage());
				}
			}else {
				try {
					 String filePath = "/resources/images/null.jpg";
			        byte[] media = new byte[819200];
			        InputStream is = context.getResourceAsStream(filePath);
			        is.read(media);
					Blob blob = new SerialBlob(media);
					ab.setArticleImage(blob);
					ab.setFileName(filePath.substring(filePath.lastIndexOf("/")));
				}catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			service.saveArticle( ab, mob.getOssanNo());
		}
		return "redirect:/articleConfirm";
	}
}
