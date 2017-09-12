package com.tocean.entity.item;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.tocean.base.baseObject.BaseEntity;
import com.tocean.entity.addByMyself.Member;
import com.tocean.entity.addByMyself.Order;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 评论贴子
 * 
 * @author alan.yan
 * 
 */
@Entity
@Table(name = "SP_V_R_REVIEW")
public class Review extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * positive 好评  moderate 中评 negative 差评
	 * 
	 */
	public enum Type {
		positive, moderate, negative;
	}
	
	/**
	 * 评价终端设备
	 * @author alan.yan
	 * 主力网
	 * 微商城
	 * 手机应用
	 *
	 */
	public enum fromType {
		web, microWeb, mobileApp;
	}
	
	/**
	 * 
	 * admin 来自官方
	 * member 来自个人
	 *
	 */
	public enum forType {
		 member,admin;
	}
	
	@JsonProperty
	@Id
	@Column(name = "R_UUID", unique = true, nullable = false, length = 44)
	private String RUuid;

	@JsonProperty
	@NotEmpty
	@Length(max = 200)
	@Column(name = "R_CONTENT", nullable = false, updatable = false)
	private String RContent;

	@Column(name = "R_IP", nullable = false, updatable = false)
	private String RIp;
	
	@Column(name = "R_FROMTYPE", nullable = false)
	private Review.fromType fromType;
	
	
	@JsonProperty
	@Column(name = "R_ISSHOW", nullable = false)
	private Boolean RIsshow;

	@JsonProperty
	@NotNull
	@Min(1L)
	@Max(5L)
	@Column(name = "R_SSCORE", nullable = false, updatable = false)
	private Integer RSScore;
	
	@JsonProperty
	@NotNull
	@Min(1L)
	@Max(5L)
	@Column(name = "R_LSCORE", nullable = false, updatable = false)
	private Integer RLScore;
	
	@JsonProperty
	@NotNull
	@Min(1L)
	@Max(5L)
	@Column(name = "R_QSCORE", nullable = false, updatable = false)
	private Integer RQScore;
	
	@JsonProperty
	@Min(1L)
	@Max(5L)
	@Column(name = "R_SCORE", nullable = false, updatable = false)
	private Integer RScore;
	
	@JsonProperty
	@Min(1L)
	@Max(5L)
	@Column(name = "R_USEDNUM", nullable = false)
	private Integer RUsedNum;
	
	@JsonProperty
	@Min(1L)
	@Max(5L)
	@Column(name = "R_NOTUSERNUM", nullable = false)
	private Integer RNotUserNum;
	
	@Column(name = "R_FORTYPE", nullable = false)
	private Review.forType forType; //回复类型0其它用户回复1官方回复	
	
	@Column(name = "R_FORUUID", updatable = false)
	private String RForUuid; //评论回复对应的评论uuid
	
	@OneToMany(mappedBy = "RForUuid", 
			fetch = FetchType.LAZY,
			cascade = { javax.persistence.CascadeType.REMOVE })
	@OrderBy("forType desc")
	private Set<Review> replyReviews = new HashSet<Review>();

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "R_FORMUUID")
	private Member forMember; //回复用户的UUID,针对其它用户有效果
	
	// ------------

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "M_UUID")
	private Member member;

	public Review.forType getForType() {
		return forType;
	}

	public void setForType(Review.forType forType) {
		this.forType = forType;
	}


	

	public String getRForUuid() {
		return RForUuid;
	}

	public void setRForUuid(String rForUuid) {
		RForUuid = rForUuid;
	}

	public Set<Review> getReplyReviews() {
		return replyReviews;
	}

	public void setReplyReviews(Set<Review> replyReviews) {
		this.replyReviews = replyReviews;
	}

	public Member getForMember() {
		return forMember;
	}

	public void setForMember(Member forMember) {
		this.forMember = forMember;
	}

	@JsonProperty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_UUID")
	private Product product;
	
	@JsonProperty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "O_UUID")
	private Order order;
	//
	
	//-------------------
	public Review() {
	}

	public String getRUuid() {
		return this.RUuid;
	}

	public void setRUuid(String rUuid) {
		this.RUuid = rUuid;
	}

	public String getRContent() {
		return this.RContent;
	}

	public void setRContent(String rContent) {
		this.RContent = rContent;
	}

	public String getRIp() {
		return this.RIp;
	}

	public void setRIp(String rIp) {
		this.RIp = rIp;
	}

	public Boolean getRIsshow() {
		return this.RIsshow;
	}

	public void setRIsshow(Boolean rIsshow) {
		this.RIsshow = rIsshow;
	}

	public Integer getRScore() {
		return this.RScore;
	}

	public void setRScore(Integer rScore) {
		this.RScore = rScore;
	}

	// -------------

	// 该列指向列的列名（建表时该列作为外键列指向关系另一端的指定列）

	public Review.fromType getFromType() {
		return fromType;
	}

	public void setFromType(Review.fromType fromType) {
		this.fromType = fromType;
	}

	public Integer getRSScore() {
		return RSScore;
	}

	public void setRSScore(Integer rSScore) {
		RSScore = rSScore;
	}

	public Integer getRLScore() {
		return RLScore;
	}

	public void setRLScore(Integer rLScore) {
		RLScore = rLScore;
	}

	public Integer getRQScore() {
		return RQScore;
	}

	public void setRQScore(Integer rQScore) {
		RQScore = rQScore;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Transient
	public String getPath() {
		if ((getProduct() != null) && (getProduct().getPUuid()!= null)) {
			return "/cstore/review/reviewAllContent/" + getProduct().getPUuid() + ".jhtml";
		}
		return null;
	}
	
	@JsonProperty
	@Transient
	public String getOuuidAndPuuid() {
		if ((getProduct() != null) && (getProduct().getPUuid()!= null)) {
			return getOrder().getOUuid()+"_"+getProduct().getPUuid();
		}
		return null;
	}
	
	public Integer getRUsedNum() {
		return RUsedNum;
	}

	public void setRUsedNum(Integer rUsedNum) {
		RUsedNum = rUsedNum;
	}

	public Integer getRNotUserNum() {
		return RNotUserNum;
	}

	public void setRNotUserNum(Integer rNotUserNum) {
		RNotUserNum = rNotUserNum;
	}

	/**
	 * 新增评价时,更新产品表里的最后评论记录
	 */
	@PrePersist
	public void updateProductLastComment() {
		getProduct().setPLastComment(getRContent());
	}
	
	
	@Transient
	public BigDecimal getUsePercent() {
		BigDecimal useNum=new BigDecimal(0);
		BigDecimal unUseNum=new BigDecimal(0);
		BigDecimal sumUseNum=new BigDecimal(0);
		if(null!=this.getRUsedNum()){
			useNum=new BigDecimal(this.getRUsedNum());
		}
		if(null!=this.getRNotUserNum()){
			unUseNum=new BigDecimal(this.getRNotUserNum());
		}
		sumUseNum=unUseNum.add(useNum);
		
		if(sumUseNum.compareTo(BigDecimal.valueOf(0))>0){
			MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
			BigDecimal r=useNum.divide(sumUseNum,mc);
			return  r;
		}else{
			return BigDecimal.valueOf(0);
		}
			
	}
	
	
}
