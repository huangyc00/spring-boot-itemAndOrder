/*
 *     Copyright 2002-2014 All Rights Reserved.
 *
 *     版权属于恒微软件公司,未经授权,任何单位或者个人都不可以使用或者分发本代码
 *
 *     公司网址:http://www.henwey.com
 *     
 */
package com.tocean.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.tocean.entity.addByMyself.Category;
import com.tocean.entity.addByMyself.OrderEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SP_P_P_PARAMETER")
public class Parameter extends OrderEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	@Id
	@Column(name = "P_UUID", unique = true, nullable = false, length = 44)
	private String PUuid;
	
	@JsonProperty
	@NotEmpty
	@Length(max=255)
	@Column(name = "P_NAME")
	private String PName;
	
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="C_UUID", nullable = false)
	private Category category;
	
	@JsonProperty
	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "parameter", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.ALL }, orphanRemoval = true)
	@OrderBy("orders asc")
	private List<ParameterDtl> parameterDtls = new ArrayList<ParameterDtl>();
	
	
	
	
	
	
	public String getPUuid() {
		return PUuid;
	}

	public void setPUuid(String pUuid) {
		PUuid = pUuid;
	}

	public String getPName() {
		return PName;
	}

	public void setPName(String pName) {
		PName = pName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	public List<ParameterDtl> getParameterDtls() {
		return parameterDtls;
	}

	public void setParameterDtls(List<ParameterDtl> parameterDtls) {
		this.parameterDtls = parameterDtls;
	}
	
}