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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.tocean.entity.addByMyself.OrderEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SP_P_S_SPEC")
public class Specification extends OrderEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "S_UUID", unique = true, nullable = false, length = 44)
	private String SUuid;
	
	@NotEmpty
	@Length(max = 200)
	@Column(name = "S_NAME", length = 200,nullable = false)
	private String SName;
	
	@NotNull
	@Column(name = "S_TYPE", precision = 8, scale = 2,nullable = false)
	private Type SType;
	
	@Length(max = 200)
	@Column(name = "S_MEMO", length = 200)
	private String SMemo;
	
	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "specification", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.ALL }, orphanRemoval = true)
	@OrderBy("orders asc")
	private List<SpecificationDtl> specificationDtls = new ArrayList<SpecificationDtl>();
	
	@ManyToMany(mappedBy = "specifications", fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<Product>();

	public enum Type {
		text, image;
	}
	
	
	
	
	
	public String getSUuid() {
		return SUuid;
	}

	public void setSUuid(String sUuid) {
		SUuid = sUuid;
	}

	public String getSName() {
		return SName;
	}

	public void setSName(String sName) {
		SName = sName;
	}

	public Type getSType() {
		return SType;
	}

	public void setSType(Type sType) {
		SType = sType;
	}

	public String getSMemo() {
		return SMemo;
	}

	public void setSMemo(String sMemo) {
		SMemo = sMemo;
	}

	public List<SpecificationDtl> getSpecificationDtls() {
		return specificationDtls;
	}

	public void setSpecificationDtls(List<SpecificationDtl> specificationDtls) {
		this.specificationDtls = specificationDtls;
	}
	

	
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
	
	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (this == obj){
			return true;
		}
		if (!Specification.class.isAssignableFrom(obj.getClass())){
			return false;
		}
		Specification _s = (Specification) obj;
		return getSUuid() != null ? getSUuid().equals(_s.getSUuid()): false;
	}

	public int hashCode() {
		int i = 17;
		i += (getSUuid() == null ? 0 : getSUuid().hashCode() * 31);
		return i;
	}
}