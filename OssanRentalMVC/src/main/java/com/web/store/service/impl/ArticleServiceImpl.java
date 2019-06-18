package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.dao.ArticleDao;
import com.web.store.model.ArticleBean;
import com.web.store.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDao dao;

	public ArticleServiceImpl() {

	}

	@Transactional
	@Override
	public int getTotalPages() {
		int n = 0;
		n = dao.getTotalPages();
		return n;
	}

	@Transactional
	@Override
	public int getTotalPages(int seqNo) {
		int n = 0;
		n = dao.getTotalPages(seqNo);
		return n;
	}

	@Transactional
	@Override
	public List<ArticleBean> getPageArticles(int ossanId) {
		List<ArticleBean> list = null;
		list = dao.getPageArticles(ossanId);
		return list;
	}

	// for visitor and admin
	@Transactional
	@Override
	public List<ArticleBean> getPageArticles() {
		List<ArticleBean> list = null;
		list = dao.getPageArticles();
		return list;
	}

	@Override
	public void setPageNo(int pageNo) {
		dao.setPageNo(pageNo);
	}

	@Transactional
	@Override
	public int updateArticle(ArticleBean article, long sizeInBytes) {
		int n = 0;
		n = dao.updateArticle(article, sizeInBytes);
		return n;
	}

	@Transactional
	@Override
	public int updateArticle(ArticleBean article) {
		int n = 0;
		n = dao.updateArticle(article);
		return n;
	}

	@Transactional
	@Override
	public int deleteArticle(int artNo) {

		int n = 0;
		n = dao.deleteArticle(artNo);
		return n;
	}

	@Transactional
	@Override
	public int saveArticle(ArticleBean art, Integer seqNo) {

		int n = 0;
		n = dao.saveArticle(art, seqNo);
		return n;
	}

	@Transactional
	public ArticleBean getArticle(int aId) {
		ArticleBean article = null;
		article = dao.queryArticle(aId);
		return article;
	}

	@Transactional
	@Override
	public long getOssanArticles(int ossanId) {

		long n = 0;
		n = dao.getOssanArticles(ossanId);
		return n;
	}
}
