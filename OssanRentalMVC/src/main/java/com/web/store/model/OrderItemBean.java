package com.web.store.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class OrderItemBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer seqNo;
	private int quantity;
	private int unitPrice;
	private OssanBean ossanBean ;
	private OrderBean orderBean ;
	public OrderItemBean(Integer seqNo, int quantity, int unitPrice) {
		super();
		this.seqNo = seqNo;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		
	}
	public OrderItemBean() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	@ManyToOne
	@JoinColumn(name="FK_ossanNo")
	public OssanBean getOssanBean() {
		return ossanBean;
	}
	public void setOssanBean(OssanBean ossanBean) {
		this.ossanBean = ossanBean;
	}
	@ManyToOne
	@JoinColumn(name="FK_orderNo")
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	
	
}
