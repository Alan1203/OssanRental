package com.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.dao.OssanDao;
import com.web.store.model.OssanBean;
import com.web.store.service.LoginService;

// 登入時系統必須執行的checkIDPassword()功能交由 MemberDao來完成 
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	OssanDao dao;

	public LoginServiceImpl() {

	}

	@Transactional
	public OssanBean checkIDPassword(String userId, String password) {
		OssanBean ob = null;

		ob = dao.checkIDPassword(userId, password);

		return ob;
	}
}
