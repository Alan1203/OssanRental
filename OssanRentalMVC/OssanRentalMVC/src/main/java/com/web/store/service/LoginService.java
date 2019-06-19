package com.web.store.service;


import com.web.store.model.OssanBean;
// 定義進行登入時系統必須執行的功能
public interface LoginService {
	public OssanBean checkIDPassword(String ossanId, String password) ;
}
