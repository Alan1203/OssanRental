package com.web.store.dao;

import java.util.List;

import com.web.store.model.ArticleBean;

public interface ArticleDao {
	
	int getTotalPages();

	List<ArticleBean> getPageArticles(int ossanIds);
	
	List<ArticleBean> getPageArticles();

	void setPageNo(int pageNo);

	// 計算紀錄總筆數
	long getRecordCounts();

	void setArticleId(int aId);

	ArticleBean getArticle();

	int updateArticle(ArticleBean article, long sizeInBytes) ;
	
	int updateArticle(ArticleBean article) ;

	// 依OssanID來查詢單筆記錄
	ArticleBean queryArticle(int aId);

	// 依artNo來刪除單筆記錄
	int deleteArticle(int artNo);

	int getTotalPages(int seqNo);
	
	long getRecordCounts(int seqNo);

	long getOssanArticles(int ossanId);

	int saveArticle(ArticleBean art, Integer seqNo);

}