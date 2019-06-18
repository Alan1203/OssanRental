package com.web.store.controller;

import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model.ArticleBean;
import com.web.store.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	ArticleService service;
	@Autowired
	ServletContext context ;

	@RequestMapping("/articleList")
	public String listArticle(Model model ,
			@RequestParam(value="pageNo" , defaultValue="1") int pageNo) {
		
		service.setPageNo(pageNo);
		List<ArticleBean> list = service.getPageArticles();
		model.addAttribute("articleBean", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPage", service.getTotalPages());		
		
		return "articleList";
	}
	
	@RequestMapping(value = "/articleDetail/{articleNo}", method = RequestMethod.GET)
	public String articleDetail(Model model ,@PathVariable int articleNo){
		ArticleBean ab = service.getArticle(articleNo);
		model.addAttribute("ab", ab);
		return "articleDetail";
	}
	
	@RequestMapping(value = "/getArticlePicture/{articleNo}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse res , @PathVariable int articleNo){
		
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		
		//圖片會亂抓
		ArticleBean ab = service.getArticle(articleNo);
		if(articleNo != ab.getArticleNo()) {
			ab = service.getArticle(articleNo);
		}
			Blob blob = ab.getArticleImage();
			filename = ab.getFileName();
			if(blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				}catch (Exception e) {
					throw new RuntimeException("ArticleController的getArticlePicture()發生問題:" + e.getMessage());
				}
			}
		
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mediaType = " + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
	
}
