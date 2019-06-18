package com.web.store.dao;

import java.util.List;

import com.web.store.model.OssanBean;

public interface OssanDao {
	
	boolean idExists(String ossanId);
	
	int getTotalPagesArea(String area);

	List<OssanBean> getPageOssans();
	
	List<OssanBean> getPageOssansArea(String area);

	int getPageNo();

	void setPageNo(int pageNo);

	int getRecordsPerPage();

	void setRecordsPerPage(int recordsPerPage);
	
	long getRecordCountsArea(String area);

	void setOssanNo(int ossanNo);

	OssanBean getOssan();

	int updateOssan(OssanBean bean) ;
	
	int updateOssanArea(OssanBean bean);

	// 依ossanNo來查詢單筆記錄
	OssanBean queryOssan(int ossanNo);

	// 依ossanNo來刪除單筆記錄
	int deleteOssan(int ossanNo);

	// 新增一筆記錄
	int saveOssan(OssanBean bean);
	
	public OssanBean checkIDPassword(String ossanId, String password);	
	

}