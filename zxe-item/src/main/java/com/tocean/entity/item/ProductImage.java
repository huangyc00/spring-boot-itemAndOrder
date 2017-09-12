/*
 *     Copyright 2002-2014 All Rights Reserved.
 *
 *     版权属于恒微软件公司,未经授权,任何单位或者个人都不可以使用或者分发本代码
 *
 *     公司网址:http://www.henwey.com
 *     
 */
package com.tocean.entity.item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Embeddable
public class ProductImage implements Serializable, Comparable<ProductImage> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ITitle;
	private String ISource;
	private String ILarge;
	private String IMedium;
	private String ISmall;
	private Double orders;
	private MultipartFile file;
	

	
	@Length(max = 255)
	@Column(name = "I_TITLE")
	public String getITitle() {
		return this.ITitle;
	}

	public void setITitle(String ITitle) {
		this.ITitle = ITitle;
	}

	@Column(name = "I_SOURCE")
	public String getISource() {
		return this.ISource;
	}

	public void setISource(String ISource) {
		this.ISource = ISource;
	}

	@Column(name = "I_LARGE")
	public String getILarge() {
		return this.ILarge;
	}

	public void setILarge(String ILarge) {
		this.ILarge = ILarge;
	}

	@Column(name = "I_MEDIUM")
	public String getIMedium() {
		return this.IMedium;
	}

	public void setIMedium(String IMedium) {
		this.IMedium = IMedium;
	}

	@Column(name = "I_SMALL")
	public String getISmall() {
		return this.ISmall;
	}

	public void setISmall(String ISmall) {
		this.ISmall = ISmall;
	}
	
	@Min(0L)
	@Column(name = "ORDERS", precision = 8)
	public Double getOrders() {
		return this.orders;
	}

	public void setOrders(Double orders) {
		this.orders = orders;
	}

	@Transient
	public MultipartFile getFile() {
		return this.file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

//	@Transient
//	public boolean isEmpty() {
//		boolean b=	(
//						(getFile() == null) ||
//						(getFile().isEmpty())
//					)
//					&&
//					(
//							(StringUtils.isEmpty(getISource())) ||
//							(StringUtils.isEmpty(getILarge()))||
//							(StringUtils.isEmpty(getIMedium())) ||
//							(StringUtils.isEmpty(getISmall()))
//					);
//		return b;
//	}
//
	public int compareTo(ProductImage productImage) {

		return new CompareToBuilder().append(getOrders(),productImage.getOrders()).toComparison();
	}
}