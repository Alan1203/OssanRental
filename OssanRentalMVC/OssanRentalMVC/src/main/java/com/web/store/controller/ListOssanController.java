package com.web.store.controller;

import java.io.File;
import java.io.InputStream;
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
import com.web.store.model.OssanBean;
import com.web.store.service.ArticleService;
import com.web.store.service.OssanService;

import _00_init.util.SystemUtils2018;

@Controller
public class ListOssanController {
	
	@Autowired
	OssanService service;
	@Autowired
	ArticleService aService;
	@Autowired
	ServletContext context ;
	
	//大叔列表
	@RequestMapping("/")
	public String listOssan(Model model ,
			@RequestParam(value = "pageNo" ,defaultValue = "1") int pageNo,
			@RequestParam(value = "requestArea" , defaultValue = "twAll") String requestArea) {
		
		if(pageNo == service.getTotalPagesArea(requestArea)) {
			service.setPageNo(service.getTotalPagesArea(requestArea));
		}else {
			service.setPageNo(pageNo);
		}
		List<OssanBean> list = service.getPageOssansArea(requestArea);
		model.addAttribute("OssanBean", list);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("totalPage", service.getTotalPagesArea(requestArea));
		
		return "index";
	}
	
	//個人頁面
	@RequestMapping("/personalPage")
	public String listOneOssan(Model model,@RequestParam(value = "ossanId") int ossanId) {
		List<ArticleBean> list = null ;
		OssanBean ob = service.getOssan(ossanId);
		if(ob.getIntro() != null)
			ob.setsIntro(SystemUtils2018.clobToStringWindows(ob.getIntro()));
		list = aService.getPageArticles(ossanId);
		model.addAttribute("articleBean", list);
		model.addAttribute("OssanBean", ob);
		return "personalPage";
	}
	
	@RequestMapping(value = "/getPicture/{ossanNo}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse res , @PathVariable int ossanNo){
		String filePath = "resources/images/NoImage.jpg";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		
		//圖片會亂抓
		OssanBean ob = service.getOssan(ossanNo);
		if(ossanNo != ob.getOssanNo()) {
			ob = service.getOssan(ossanNo);
		}
			Blob blob = ob.getOssanImage();
			filename = ob.getFileName();
			if(blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				}catch (Exception e) {
					throw new RuntimeException("ListOssanController的getPicture()發生問題:" + e.getMessage());
				}
			}else {
				media = toByteArray(filePath);
				filename = filePath ;
			}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mediaType = " + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
	
	public byte[] toByteArray(String filePath) {
		byte[] b = null;
		try {
			File file = new File(filePath);
			long size = file.length();
			b = new byte[(int) size];
			InputStream is = context.getResourceAsStream(filePath);
			is.read(b);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return b ;
	}
}
