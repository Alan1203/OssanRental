package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.dao.OssanDao;
import com.web.store.model.OssanBean;
import com.web.store.service.OssanService;

@Service
@Scope("prototype")
public class OssanServiceImplHibernate implements OssanService {

	@Autowired
	OssanDao dao;

	public OssanServiceImplHibernate() {

	}

	@Transactional
	@Override
	public List<OssanBean> getPageOssans() {
		List<OssanBean> list = null;
		list = dao.getPageOssans();
		return list;
	}

	@Override
	public int getPageNo() {
		return dao.getPageNo();
	}

	@Override
	public void setPageNo(int pageNo) {
		dao.setPageNo(pageNo);
	}

	@Override
	public int getRecordsPerPage() {
		return dao.getRecordsPerPage();
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		dao.setRecordsPerPage(recordsPerPage);
	}

	@Transactional
	@Override
	public OssanBean getOssan(int ossanNo) {
		OssanBean bean = null;

		dao.setOssanNo(ossanNo);
		bean = dao.getOssan();

		return bean;
	}

	@Transactional
	@Override
	public int updateOssan(OssanBean bean) {
		int n = 0;
		n = dao.updateOssan(bean);
		return n;
	}

	@Transactional
	@Override
	public OssanBean queryOssan(int OssanId) {
		OssanBean bean = null;

		bean = dao.queryOssan(OssanId);

		return bean;
	}

	@Transactional
	@Override
	public int deleteOssan(int no) {
		int n = 0;
		n = dao.deleteOssan(no);
		return n;
	}

	@Transactional
	@Override
	public int saveOssan(OssanBean bean) {
		int n = 0;
		n = dao.saveOssan(bean);
		return n;
	}

	@Transactional
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		exist = dao.idExists(id);
		return exist;
	}
	@Override
	public long getRecordCountsArea(String area) {
		return dao.getRecordCountsArea(area);

	}

	@Transactional
	@Override
	public List<OssanBean> getPageOssansArea(String area) {
		List<OssanBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		list = dao.getPageOssansArea(area);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		return list;
	}

	@Transactional
	@Override
	public int getTotalPagesArea(String area) {
		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		n = dao.getTotalPagesArea(area);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		return n;
	}

	@Transactional
	@Override
	public int updateOssanArea(OssanBean bean) {
		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		n = dao.updateOssanArea(bean);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		return n;
	}

}
