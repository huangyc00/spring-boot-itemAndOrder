/*
 *     Copyright 2002-2014 All Rights Reserved.
 *
 *     版权属于恒微软件公司,未经授权,任何单位或者个人都不可以使用或者分发本代码
 *
 *     公司网址:http://www.henwey.com
 *     
 */
package com.tocean.entity.item;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tocean.entity.addByMyself.OrderEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SP_P_SD_SPECDTL")
public class SpecificationDtl extends OrderEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sdUuid;
	private String sdName;
	private String sdImage;
	private Specification specification;
	private Set<Product> products = new HashSet<Product>();

	@Id
	@Column(name = "SD_UUID", unique = true, nullable = false, length = 44)
	public String getSdUuid() {
		return sdUuid;
	}
	public void setSdUuid(String sdUuid) {
		this.sdUuid = sdUuid;
	}
	
	@NotEmpty
	@Length(max = 200)
	@Column(name = "SD_NAME", length = 200,nullable = false)
	public String getSdName() {
		return this.sdName;
	}

	public void setSdName(String sdName) {
		this.sdName = sdName;
	}
	
	@Length(max = 200)
	@Column(name = "SD_IMAGE", length = 200)
	public String getSdImage() {
		return this.sdImage;
	}

	public void setSdImage(String sdImage) {
		this.sdImage = sdImage;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="S_UUID",nullable = false)
	public Specification getSpecification() {
		return specification;
	}
	public void setSpecification(Specification specification) {
		this.specification = specification;
	}
	@ManyToMany(mappedBy = "specificationDtls", fetch = FetchType.LAZY)
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}