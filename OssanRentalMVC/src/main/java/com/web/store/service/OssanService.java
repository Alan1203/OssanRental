package com.web.store.service;

import java.util.List;

import com.web.store.model.OssanBean;


public interface OssanService {
	
	boolean idExists(String id);
	
	int getTotalPagesArea(String area);

	List<OssanBean> getPageOssans();
	
	List<OssanBean> getPageOssansArea(String area);

	int getPageNo();

	void setPageNo(int pageNo);

	int getRecordsPerPage();

	void setRecordsPerPage(int recordsPerPage);
	
	long getRecordCountsArea(String area);

	OssanBean getOssan(int ossanNo);

	int updateOssan(OssanBean bean) ;

	// 依ossanNo來查詢單筆記錄
	OssanBean queryOssan(int ossanNo);

	// 依ossanNo來刪除單筆記錄
	int deleteOssan(int no);

	// 新增一筆記錄
	int saveOssan(OssanBean bean);

	int updateOssanArea(OssanBean bean);
	
}