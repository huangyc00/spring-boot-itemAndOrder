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
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tocean.base.baseObject.BaseEntity;
import com.tocean.entity.addByMyself.Article;
import com.tocean.entity.addByMyself.OrderEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SP_C_T_TAG")
public class Tag extends OrderEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String TUuid;
	private String TName;
	private indexType TType;
	private String TIcon;
	private String TMemo;
	
	private Boolean TIsSystem;
	
	private Boolean TIsUseed;
	
	private String TModelId;
	
	private Set<Article> articles = new HashSet<Article>();
	private Set<Product> products = new HashSet<Product>();
	
	/**
	 * 
	 * @author alan.yan
	 * article 文章
	 * product 产品
	 * 
	 */
	public enum indexType {
        index, ecIndex , microIndex;
	}

	@Id
	@Column(name = "T_UUID", unique = true, nullable = false, length = 44)
	public String getTUuid() {
		return this.TUuid;
	}

	public void setTUuid(String TUuid) {
		this.TUuid = TUuid;
	}


	@NotEmpty
	@Length(max = 255)
	@Column(name = "T_NAME",nullable = false)
	public String getTName() {
		return this.TName;
	}

	public void setTName(String TName) {
		this.TName = TName;
	}


	@NotNull(groups = { BaseEntity.Save.class })
	@Column(name = "T_TYPE", nullable = false)
	public indexType getTType() {
		return TType;
	}

	public void setTType(indexType tType) {
		TType = tType;
	}
	
	
	@Length(max=255)
	@Column(name = "T_ICON")
	public String getTIcon() {
		return this.TIcon;
	}


	public void setTIcon(String TIcon) {
		this.TIcon = TIcon;
	}

	@Length(max=255)
	@Column(name = "T_MEMO")
	public String getTMemo() {
		return this.TMemo;
	}

	public void setTMemo(String TMemo) {
		this.TMemo = TMemo;
	}

	
	@Column(name = "T_ISSYSTEM")
	public Boolean getTIsSystem() {
		return TIsSystem;
	}

	public void setTIsSystem(Boolean tIsSystem) {
		TIsSystem = tIsSystem;
	}

	
	
	@Column(name = "T_ISUSEED")
	public Boolean getTIsUseed() {
		return TIsUseed;
	}

	public void setTIsUseed(Boolean tIsUseed) {
		TIsUseed = tIsUseed;
	}

	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
	@Column(name = "T_MODELID")
	public String getTModelId() {
		return TModelId;
	}

	public void setTModelId(String tModelId) {
		TModelId = tModelId;
	}

	@PreRemove
	public void preRemove() {
		Set<Article> _set1 = getArticles();
		if (_set1 != null) {
			Iterator<Article> _iterator = _set1.iterator();
			while (_iterator.hasNext()) {
				Article _article = _iterator.next();
				_article.getTags().remove(this);
			}
		}
		
		Set<Product> _set2 = getProducts();
		if (_set2 != null) {
			Iterator<Product> _iterator = _set2.iterator();
			while (_iterator.hasNext()) {
				Product _product = _iterator.next();
				_product.getTags().remove(this);
			}
		}
	}
}