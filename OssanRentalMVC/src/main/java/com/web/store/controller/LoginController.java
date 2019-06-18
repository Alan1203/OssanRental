package com.web.store.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model.OssanBean;
import com.web.store.service.LoginService;
import com.web.store.service.OssanService;

import _00_init.util.GlobalService;

@Controller
public class LoginController {

	@Autowired
	OssanService service;
	@Autowired
	LoginService lservice;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {

		OssanBean ob = new OssanBean();
		model.addAttribute("ossanBean", ob);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegister(@ModelAttribute("ossanBean") OssanBean ob) {
		ob.setPassword(GlobalService.getMD5Endocing(GlobalService.encryptString(ob.getPassword())));
		service.saveOssan(ob);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "email" ,defaultValue = "") String email, @RequestParam(value = "password" , defaultValue = "") String password,
			@RequestParam(value = "rememberMe", defaultValue = "") String rememberMe) {
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		if (email == null || email.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
		if (!errorMsgMap.isEmpty()) {
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "login";
		}
		// **********Remember Me****************************
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
		if (rememberMe.trim().length() != 0) {
			cookieUser = new Cookie("user", email);
			cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
			cookieUser.setPath(request.getContextPath());
			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());
			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		} else { // 使用者沒有對 RememberMe 打勾
			cookieUser = new Cookie("user", email);
			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
			cookieUser.setPath(request.getContextPath());
			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());
			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
		
		request.setAttribute("rememberMe", rememberMe);
		request.setAttribute("user", email);
		request.setAttribute("password", password);
		// 進行帳號密碼驗證
		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));

		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			OssanBean mb = lservice.checkIDPassword(email, password);
			if(mb != null) {
				if (mb.getEmail().equalsIgnoreCase("admin@gmail.com")) {
					// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
					session.setAttribute("AdminLoginOK", mb);
				} else {
					session.setAttribute("LoginOK", mb);
				} 
			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
			}
		} catch (RuntimeException ex) {
			errorMsgMap.put("LoginError", ex.getMessage());
		}
		if (!errorMsgMap.isEmpty()) {
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "login";
		}
		return "redirect:/";
	}
	
}
