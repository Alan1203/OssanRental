package com.web.store.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.web.store.dao.OssanDao;
import com.web.store.model.OssanBean;

import _00_init.util.SystemUtils2018;

// 本類別使用純JDBC的技術來存取資料庫。
// 所有SQLException都以catch區塊捕捉，然後一律再次丟出RuntimeException。
// 對SQLException而言，即使catch下來，程式依然無法正常執行，所以捕捉SQLException，再次丟出
// RuntimeException。
@Repository
@Scope("prototype")
public class OssanDaoImpl_Hibernate implements Serializable, OssanDao {
	private static final long serialVersionUID = 1L;
	private int ossanNo = 0; 	// 查詢單筆商品會用到此代號
	private int pageNo = 0;		// 存放目前顯示之頁面的編號
	private int recordsPerPage = 6; // 預設值：每頁三筆
	private int totalPages = -1;
	
	@Autowired
	SessionFactory factory;

	public OssanDaoImpl_Hibernate() {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OssanBean> getPageOssans() {
		
		List<OssanBean> list = new ArrayList<OssanBean>();
		String hql = "FROM OssanBean a WHERE a.privilege = :privilege ";
		
		Session session = factory.getCurrentSession();

		int startRecordNo = (pageNo - 1) * recordsPerPage;
		
		list = session.createQuery(hql)
					  .setParameter("privilege","Ossan")
					  .setFirstResult(startRecordNo)
					  .setMaxResults(recordsPerPage)
					  .list();
		return list;
	}
	
	@Override
	public int getPageNo() {
		return pageNo;
	}

	@Override
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	
	@Override
	public boolean idExists(String id) {
		
		Session session = factory.getCurrentSession();
		boolean exist = false;
		String hql = "FROM OssanBean WHERE email = :email";
		try {
			OssanBean ob = (OssanBean) session.createQuery(hql)
												.setParameter("email", id)
												.uniqueResult();
			if (ob != null) {
				exist = true;
			}
			
		} catch (NoResultException ex) {
			exist = false;
		} catch (NonUniqueResultException ex) {
			exist = true;
		}
		
		return exist;
	}

	@Override
	public int updateOssan(OssanBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.update(bean);
		return n++;
	}

	@Override
	public OssanBean queryOssan(int ossanNo) {
		OssanBean bean = null;
		Session session = factory.getCurrentSession();
		bean = session.get(OssanBean.class, ossanNo);
		
		if (bean.getIntro() != null) {
			
			bean.setsIntro(SystemUtils2018.clobToStringWindows(bean.getIntro()));
		}
		
		return bean;
	}


	@Override
	public int deleteOssan(int ossanNo) {
		int n = 0;
		Session session = factory.getCurrentSession();
		String hql = "FROM OssanBean o WHERE o.ossanNo = :id";
		OssanBean bb = (OssanBean) session.createQuery(hql).setParameter("id", ossanNo).uniqueResult();
		session.delete(bb);
		n++;
		return n;
	}

	@Override
	public void setOssanNo(int ossanNo) {
		this.ossanNo = ossanNo;
		
	}

	@Override
	public OssanBean getOssan() {
		OssanBean bean = queryOssan(this.ossanNo);
		return bean;
	}

	@Override
	public int saveOssan(OssanBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		
		java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
		bean.setRegisterTime(now);
		bean.setPrivilege("Ossan");
		
		//地區之後修正
		if(bean.getCity().equals("新北市")) {
			bean.setTwNorth(true);
		} else if (bean.getCity().equals("台中市")) {
			bean.setTwMiddle(true);
		} else if (bean.getCity().equals("台南市")) {
			bean.setTwSouth(true);
		} else {
			bean.setTwOther(true);
		}

		session.save(bean);
		int a = bean.getOssanNo();
		
		System.out.println("產生第"+a+"號大叔 : 個人資料Bean");
		
		
		
		n++;
		return n;
	}

	@Override
	public OssanBean checkIDPassword(String ossanId, String password) {
		OssanBean ob = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OssanBean WHERE email = :mid and password = :pswd";
		try {
			ob = (OssanBean) session.createQuery(hql)
							.setParameter("mid", ossanId)
							.setParameter("pswd", password)
							.uniqueResult();
		} catch (NoResultException ex) {
			ob = null;
		}
		return ob;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCountsArea(String area) {
		long count = 0; // 必須使用 long 型態
		
		String hql;
		String hql2 = " and a.twNorth+a.twMiddle+a.twSouth+a.twOther>0";
		
		if (!area.equals("twAll") && area != null && area!="") {
			hql = "SELECT count(*) FROM OssanBean a "
					+ "WHERE a.privilege = :privilege and a."+ area +" = 1";
		} else {
			hql = "SELECT count(*) FROM OssanBean a WHERE a.privilege = :privilege";
		}
		
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql+hql2).setParameter("privilege","Ossan").list();
		System.out.println("執行完Count語法");
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OssanBean> getPageOssansArea(String area) {
		List<OssanBean> list = new ArrayList<OssanBean>();
		String hql;
		String hql2 = " and a.twNorth+a.twMiddle+a.twSouth+a.twOther>0";
		
		if (!area.equals("twAll") && area != null && area!="" ) {
			hql = "FROM OssanBean a "
					+ "WHERE a.privilege = :privilege and a."+ area +" = 1";
		} else {
			hql = "FROM OssanBean a WHERE a.privilege = :privilege";
		}
		
		Session session = factory.getCurrentSession();

		int startRecordNo = (pageNo - 1) * recordsPerPage;

		list = session.createQuery(hql+hql2)
					  .setParameter("privilege","Ossan")
					  .setFirstResult(startRecordNo)
					  .setMaxResults(recordsPerPage)
					  .list();
		
		System.out.println("執行完Query語法");
		return list;
	}

	@Override
	public int getTotalPagesArea(String area) {
		totalPages = (int) (Math.ceil(getRecordCountsArea(area) / (double) recordsPerPage));
		return totalPages;
	}

	@Override
	public int updateOssanArea(OssanBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		
		String hql = "UPDATE OssanBean SET " +
		          "twNorth = :twNorth," +
		          "twMiddle = :twMiddle," +
		          "twSouth = :twSouth," +
		          "twOther = :twOther "+
		          
		          "where ossanNo = :ossanNo";
		
		n = session.createQuery(hql)
				   .setParameter("twNorth", bean.isTwNorth())
				   .setParameter("twMiddle",bean.isTwMiddle())
				   .setParameter("twSouth", bean.isTwSouth())
				   .setParameter("twOther",bean.isTwOther())
				   .setParameter("ossanNo", bean.getOssanNo())
				   .executeUpdate();
		
		return n;
	}
	
}