/*
 *     Copyright 2002-2014 All Rights Reserved.
 *
 *     版权属于恒微软件公司,未经授权,任何单位或者个人都不可以使用或者分发本代码
 *
 *     公司网址:http://www.henwey.com
 *     
 */
package com.tocean.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tocean.entity.addByMyself.OrderEntity;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SP_P_PD_PARAMETERDTL")
public class ParameterDtl extends OrderEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pdUuid;
	private String pdName;
	
	
	private Parameter parameter;
	
	@JsonProperty
	@DocumentId
	@Id
	@Column(name = "PD_UUID", unique = true, nullable = false, length = 44)
	public String getPdUuid() {
		return this.pdUuid;
	}

	public void setPdUuid(String pdUuid) {
		this.pdUuid = pdUuid;
	}

	@JsonProperty
	@NotEmpty
	@Length(max = 200)
	@Column(name = "PD_NAME",nullable = false)
	public String getPdName() {
		return this.pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="P_UUID",nullable = false)
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	
}