package com.web.store.model;


import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
@Entity
public class ArticleBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer articleNo;
	private String title;
	private Clob content;
	private Date updateTime;
	private Blob articleImage;
	private String fileName;
	private String sContent;
	private OssanBean ossanBean;
	private MultipartFile articleImg ;
	public ArticleBean(Integer articleNo, String title, Clob content, Date updateTime, Blob articleImage,
			String fileName) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
		this.updateTime = updateTime;
		this.articleImage = articleImage;
		this.fileName = fileName;
	}
	public ArticleBean() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Clob getContent() {
		return content;
	}
	public void setContent(Clob content) {
		this.content = content;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Blob getArticleImage() {
		return articleImage;
	}
	public void setArticleImage(Blob articleImage) {
		this.articleImage = articleImage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Transient
	public String getsContent() {
		return sContent;
	}
	public void setsContent(String sContent) {
		this.sContent = sContent;
	}
	
	@ManyToOne
	@JoinColumn(name="FK_ossanNo")
	public OssanBean getOssanBean() {
		return ossanBean;
	}
	public void setOssanBean(OssanBean ossanBean) {
		this.ossanBean = ossanBean;
	}
	
	@Transient
	public MultipartFile getArticleImg() {
		return articleImg;
	}
	public void setArticleImg(MultipartFile articleImg) {
		this.articleImg = articleImg;
	}	
	
	
	
}
