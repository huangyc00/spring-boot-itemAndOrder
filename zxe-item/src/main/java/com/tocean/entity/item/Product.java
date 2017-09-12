/*
 *     Copyright 2002-2014 All Rights Reserved.
 *
 *     版权属于恒微软件公司,未经授权,任何单位或者个人都不可以使用或者分发本代码
 *
 *     公司网址:http://www.henwey.com
 *     
 */
package com.tocean.entity.item;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.tocean.base.baseObject.BaseEntity;
import com.tocean.entity.addByMyself.Category;
import com.tocean.entity.addByMyself.Goods;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
//import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 产品实体bean
 * 
 * @author alan.yan
 * 
 */
@Indexed
@Entity
@Table(name = "SP_P_P_PRODUCT")
public class Product extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String HITS_CACHE_NAME = "productHits";
	public static final int HITS_CACHE_INTERVAL = 600000;
	public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 20;
	public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";
	public static final String FULL_NAME_SPECIFICATION_PREFIX = "[";
	public static final String FULL_NAME_SPECIFICATION_SUFFIX = "]";
	public static final String FULL_NAME_SPECIFICATION_SEPARATOR = " ";
	private static String staticPath;
	private static String staticMobilePath;

	private String PUuid; //产品uuid

	private String PSn;		//产品编号
	
	private String PHgSn;	//海关商品HS编码

	private String PName; //产品名称
	private String PWxIntroduction; //产品简单介绍，用在微信首页展示
	
	private String PFullName; //产品全称

	private BigDecimal PPrice; //销售价格
	
	private BigDecimal PAddBuyPrice; //用户在购物车上面加价购买的价格
	

	private BigDecimal PCost; //成本价格

	private BigDecimal PMarketPrice; //市场价格

	private String PImage; //商品的略缩图
	
	private String PAdImage; //商品首页导航620*310广告图
	
	private String PMobileImage; //280*160手机微信主图广告图

	private String PUnit;	//商品计量单位

	private Double PWeight;	//商品重量

	private Double PStock; //库存

	private Double PAllOcatedStock; //预分配库存

	private String PStockMemo; //库存备注

	private Double PPoint;	//赠送积分 

	private Double PIsMarketable; //是否上架

	private Double PIsList; //是否列出

	private Double PIsTop;	//是否置顶

	private Double PIsGift; //是否为赠品
	
	private Double PIsReview; //是否可以评价
	
	private String PMemo; //备注

	private String PKeyword; //关键词

	private String PSeoTitle; //页面标题:

	private String PSeoKeywords; //页面关键词:

	private String PSeoDescription; //页面描述:

	private String PIntroduction; //商品介绍
	
	private String PMobileIntroduction; //手机商品介绍
	
	
	private String PLastComment; //商品最后一次评价,为了加快前端访问的速度,存在这里
	

	private Double PScore;		 	//用户评价分

	private Double PTotalScore;  	//用户总评价分

	private Double PScoreCount;		//用户评价次数
	private Double PScoreCount1;		//用户1分评价次数
	private Double PScoreCount2;		//用户2分评价次数
	private Double PScoreCount3;		//用户3分评价次数
	private Double PScoreCount4;		//用户4分评价次数
	private Double PScoreCount5;		//用户5分评价次数

	private Double PHits;			//用户点击次数,用户的浏览数
	
	private Double PBaseHits;		//基础浏览数
	
	private Boolean PIsHot;			//是否是热销产品
	
	private Double POrder;			//推荐排序,跟其它的排序区分开
	
	private Double PWeekHits;		//

	private Double PMonthHits;		//

	private Double PSales;	//产品销量

	private Double PWeekSales;		//

	private Double PMonthSales;		//

	private Date PWeekHitsDate;

	private Date PMonthHitsDate;

	private Date PWeekSalesDate;

	private Date PMonthSalesDate;
	
	private Double PCountReview;
	
	private Double PCountCollect;	//这个商品总共收藏数据
	
	private Double PCountShare;		//这个商品总共分享的数量

	private String PAttributeValue0;

	private String PAttributeValue1;

	private String PAttributeValue2;

	private String PAttributeValue3;

	private String PAttributeValue4;

	private String PAttributeValue5;

	private String PAttributeValue6;

	private String PAttributeValue7;

	private String PAttributeValue8;

	private String PAttributeValue9;

	private String PAttributeValue10;

	private String PAttributeValue11;

	private String PAttributeValue12;

	private String PAttributeValue13;

	private String PAttributeValue14;

	private String PAttributeValue15;

	private String PAttributeValue16;

	private String PAttributeValue17;

	private String PAttributeValue18;

	private String PAttributeValue19;
	
	private String POtherLink1;
	
	private String POtherLink2;

	private Goods goods;

	private Category category;
	
	private SysCategory sysCategory;
	
	private Ppatax ppatax;  //海关税编码

	private Brand brand;
	
	private String PErpCode;  //商品ERP统一编码
	
	private Boolean PIsFinishFax; //是否完税产品0非完税（跨境默认是非完税），1完税（普通默认是完税） (默认值setting配置值)
	
	//分销佣金-----------------------------------------------------------
			//以下每一个二个
	private Boolean PIsGetSubTraderMoney;	//*如果没有下级分销商，上级分销商领取下级分销商佣金：1是  0否(默认是) (默认值setting配置值)
	private Boolean PIsNoTraderMoney;		//*分销商返佣：0开启 1 关闭 关闭后，将不给分销商返佣 (默认值setting配置值)
	private BigDecimal	PTraderFirstMoney;		//直属上级能拿到的佣金 元   ： 金额和比例都为0.00或空表示采用分销商等级或系统设置的提成比例计算佣金
	private Double	PTraderFirstPea;		//直属上级能拿到的佣金  比例% ： 金额和比例都为0.00或空表示采用分销商等级或系统设置的提成比例计算佣金
	
	private BigDecimal	PTraderSecondMoney;		//二级上级能拿到的佣金 元   ： 金额和比例都为0.00或空表示采用分销商等级或系统设置的提成比例计算佣金
	private Double		PTraderSecondPea;	//直属上级能拿到的佣金  比例% ： 金额和比例都为0.00或空表示采用分销商等级或系统设置的提成比例计算佣金
	
	private BigDecimal		PTraderThreeMoney;	//三级上级能拿到的佣金元 ： 金额和比例都为0.00或空表示采用分销商等级或系统设置的提成比例计算佣金
	private Double PTraderThreePea;	//三级上级能拿到的佣金 比例% 
	
	private Boolean PIsGetSubAgentMoney;	//*如果没有下级代理商，上级代理商领取下级代理商佣金：1是  0否(默认是) (默认值setting配置值)
	private Boolean PIsNoAgentMoney;	//代理商返佣：0开启 1 关闭 关闭后，将不给代理商返佣。 (默认值setting配置值)
	
	private BigDecimal	PAgentFirstMoney;	//一级代理商能拿到的佣金：0.00 元  或    0.000 % 金额和比例都为0.00或空表示采用代理商的提成比例计算佣金
	private Double PAgentFirstPea; //一级代理商能拿到的佣金比例
	
	private BigDecimal	PAgentSecondMoney;	//二级代理商能拿到的佣金：0.00 元  或    0.000 % 金额和比例都为0.00或空表示采用代理商的提成比例计算佣金
	private Double PAgentSecondPea;//二级代理商能拿到的佣金比例
	
	private BigDecimal	PAgentThreeMoney;	//三级代理商能拿到的佣金：0.00 元  或    0.000 % 金额和比例都为0.00或空表示采用代理商的提成比例计算佣金
	private Double	PAgentThreePea;//三级代理商能拿到的佣金比例
			

	
	
		
		//物流及其它-------------------------
	private Boolean PIsNoLogistics; //免物流：1是  0否	会员购买免物流商品时，不需要填写收货地址。(默认值setting配置值)
	private Boolean PIsShowStock; //库存显示：1是  0否  默认否 (默认值setting配置值)
	private Double	PFirstPoint;//直属上级能拿到积分：
	private Double	PSecondPoint;//二级上级能拿到积分：
	private Double	PThreePoint;//三级上级能拿到积分：
	private Double	PBaseSales;//基础销量：	//在前端显示的实际销售量，=基础销量，加后面的销量
	private Double  PPraiseNum;//赞数：	//实际顶数=基础点赞数+赞数
	private Double  PBasePraiseNum;//基础点赞数：	//实际顶数=基础点赞数+赞数
	
	
	private Double	PVolume;//体积：	0.0000	 m³(最多四位小数)
		

	
	
	private Set<Tag> tags = new HashSet<Tag>();

	private Set<Specification> specifications = new HashSet<Specification>();

	private Set<SpecificationDtl> specificationDtls = new HashSet<SpecificationDtl>();

	private Map<ParameterDtl, String> parameterDtls = new HashMap<ParameterDtl, String>();

	private List<ProductImage> productImages = new ArrayList<ProductImage>();

	private Set<Review> reviews = new HashSet<Review>();

	private Set<Consultation> consultations = new HashSet<Consultation>();

	private Set<Member> collectMembers = new HashSet<Member>();

	private Set<Promotion> promotions = new HashSet<Promotion>();

	private Set<CartItem> cartItems = new HashSet<CartItem>();

	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	private Set<GiftItem> giftItems = new HashSet<GiftItem>();

	private Set<Notify> notifys = new HashSet<Notify>();
	
	
	
	
	private Map<MemberRank, BigDecimal> memberPrice = new HashMap<MemberRank, BigDecimal>();

	/**
	 * topDesc 置顶
	 * priceAsc 价格升
	 * priceDesc 价格降
	 * salesDesc 销量升
	 * scoreDesc 积分
	 * dateDesc 日期
	 * */
	public enum OrderType {
		topDesc, priceAsc, priceDesc, salesDesc, scoreDesc, dateDesc;
	}

	
	
	static {
		try {
			File _file = new File(SettingUtils.get().getTemplatePath());// 
			Document _document = new SAXReader().read(_file);
			Element _elementMobile = (Element) _document.selectSingleNode(SettingUtils.NODES_TEMPLATE+ "[@id='wxproductContent']");
			staticMobilePath= _elementMobile.attributeValue("staticPath");
			Element _element = (Element) _document.selectSingleNode(SettingUtils.NODES_TEMPLATE+ "[@id='productContent']");
			staticPath = _element.attributeValue("staticPath");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@JsonProperty
	@Id
	@Column(name = "P_UUID", unique = true, nullable = false, length = 44)
	public String getPUuid() {
		return this.PUuid;
	}

	public void setPUuid(String PUuid) {
		this.PUuid = PUuid;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Pattern(regexp = "^[0-9a-zA-Z_-]+$")
	@Length(max = 200)
	@Column(name = "P_SN", nullable = false, unique = true)
	public String getPSn() {
		return this.PSn;
	}

	public void setPSn(String PSn) {
		this.PSn = PSn;
	}
	
	
	//海关商品HS编码
	@JsonProperty
	@Length(max = 200)
	@Column(name = "P_HGSN", nullable = false, unique = true)
	public String getPHgSn() {
		return PHgSn;
	}

	public void setPHgSn(String pHgSn) {
		PHgSn = pHgSn;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
	@NotEmpty
	@Length(max = 200)
	@Column(name = "P_NAME", nullable = false)
	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	
	@JsonProperty
	@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
	@Length(max = 200)
	@Column(name = "P_WXINTRODUCTION", nullable = false)
	public String getPWxIntroduction() {
		return PWxIntroduction;
	}

	public void setPWxIntroduction(String pWxIntroduction) {
		PWxIntroduction = pWxIntroduction;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
	@Column(name = "P_FULLNAME", nullable = false)
	public String getPFullName() {
		String _PFullName=null;
		if(null!=this.PFullName && this.PFullName.length()>0){
			_PFullName=this.PFullName.replace("[无规格]", "");
		}
		
		return _PFullName;
	}

	public void setPFullName(String PFullName) {
		this.PFullName = PFullName;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@FieldBridge(impl = hwfw.cweb.baseobject.BigDecimalNumericFieldBridge.class)
	@NotNull
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_PRICE", nullable = false, precision = 21, scale = 6)
	public BigDecimal getPPrice() {
		return this.PPrice;
	}

	public void setPPrice(BigDecimal PPrice) {
		this.PPrice = PPrice;
	}

	
	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@FieldBridge(impl = hwfw.cweb.baseobject.BigDecimalNumericFieldBridge.class)
	@NotNull
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_ADDBUYPRICE", nullable = false, precision = 21, scale = 6)
	public BigDecimal getPAddBuyPrice() {
		return PAddBuyPrice;
	}

	public void setPAddBuyPrice(BigDecimal pAddBuyPrice) {
		PAddBuyPrice = pAddBuyPrice;
	}

	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_COST", precision = 21, scale = 6)
	public BigDecimal getPCost() {
		return this.PCost;
	}

	public void setPCost(BigDecimal PCost) {
		this.PCost = PCost;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_MARKETPRICE", precision = 21, scale = 6, nullable = false)
	public BigDecimal getPMarketPrice() {
		return this.PMarketPrice;
	}

	public void setPMarketPrice(BigDecimal PMarketPrice) {
		this.PMarketPrice = PMarketPrice;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.NO)
	@Length(max = 255)
	@Column(name = "P_IMAGE")
	public String getPImage() {
		return this.PImage;
	}

	public void setPImage(String PImage) {
		this.PImage = PImage;
	}
	
	@JsonProperty
	@Field(store = Store.YES, index = Index.NO)
	@Length(max = 255)
	@Column(name = "P_ADIMAGE")
	public String getPAdImage() {
		return PAdImage;
	}

	public void setPAdImage(String pAdImage) {
		PAdImage = pAdImage;
	}
	
	
	@Column(name = "P_MOBILEIMAGE")
	public String getPMobileImage() {
		return PMobileImage;
	}

	public void setPMobileImage(String pMobileImage) {
		PMobileImage = pMobileImage;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.NO)
	@Length(max = 255)
	@Column(name = "P_UNIT")
	public String getPUnit() {
		return this.PUnit;
	}

	public void setPUnit(String PUnit) {
		this.PUnit = PUnit;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Column(name = "P_WEIGHT", precision = 15)
	public Double getPWeight() {
		if (null == this.PWeight) {
			this.PWeight = 0D;
		}
		return this.PWeight;
	}

	public void setPWeight(Double PWeight) {
		this.PWeight = PWeight;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Column(name = "P_STOCK", precision = 15)
	public Double getPStock() {
		return this.PStock;
	}

	public void setPStock(Double PStock) {
		this.PStock = PStock;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Column(name = "P_ALLOCATEDSTOCK", precision = 15, nullable = false)
	public Double getPAllOcatedStock() {
		return this.PAllOcatedStock;
	}

	public void setPAllOcatedStock(Double PAllOcatedStock) {
		this.PAllOcatedStock = PAllOcatedStock;
	}

	@Length(max = 255)
	@Column(name = "P_STOCKMEMO")
	public String getPStockMemo() {
		return this.PStockMemo;
	}

	public void setPStockMemo(String PStockMemo) {
		this.PStockMemo = PStockMemo;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Column(name = "P_POINT", precision = 15, nullable = false)
	public Double getPPoint() {
		return this.PPoint;
	}

	public void setPPoint(Double PPoint) {
		this.PPoint = PPoint;
	}

	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@NotNull
	@Column(name = "P_ISMARKETABLE", precision = 4, nullable = false)
	public Double getPIsMarketable() {
		return this.PIsMarketable;
	}

	public void setPIsMarketable(Double PIsMarketable) {
		this.PIsMarketable = PIsMarketable;
	}

	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@NotNull
	@Column(name = "P_ISLIST", precision = 4, nullable = false)
	public Double getPIsList() {
		return this.PIsList;
	}

	public void setPIsList(Double PIsList) {
		this.PIsList = PIsList;
	}

	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@NotNull
	@Column(name = "P_ISTOP", precision = 4, nullable = false)
	public Double getPIsTop() {
		return this.PIsTop;
	}

	public void setPIsTop(Double PIsTop) {
		this.PIsTop = PIsTop;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NotNull
	@NumericField
	@Column(name = "P_ISGIFT", precision = 4, nullable = false)
	public Double getPIsGift() {
		return this.PIsGift;
	}

	public void setPIsGift(Double PIsGift) {
		this.PIsGift = PIsGift;
	}

	
	

	@Column(name = "P_ISREVIEW", precision = 4, nullable = false)
	public Double getPIsReview() {
		return PIsReview;
	}

	public void setPIsReview(Double pIsReview) {
		PIsReview = pIsReview;
	}

	@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
	@Lob
	@Column(name = "P_INTRODUCTION")
	public String getPIntroduction() {
		return this.PIntroduction;
	}
	

	public void setPIntroduction(String PIntroduction) {
		this.PIntroduction = PIntroduction;
	}

	@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
	@Lob
	@Column(name = "P_MOBILEINTRODUCTION")
	public String getPMobileIntroduction() {
		if(null==PMobileIntroduction){
			PMobileIntroduction="";
		}
		return PMobileIntroduction;
	}

	public void setPMobileIntroduction(String pMobileIntroduction) {
		PMobileIntroduction = pMobileIntroduction;
	}
	
	
	@Lob
	@Column(name = "P_LASTCOMMENT")
	public String getPLastComment() {
		return PLastComment;
	}

	

	

	public void setPLastComment(String pLastComment) {
		PLastComment = pLastComment;
	}

	@Length(max = 255)
	@Column(name = "P_MEMO")
	public String getPMemo() {
		return this.PMemo;
	}

	public void setPMemo(String PMemo) {
		this.PMemo = PMemo;
	}

	@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
	@Length(max = 200)
	@Column(name = "P_KEYWORD")
	public String getPKeyword() {
		return this.PKeyword;
	}

	public void setPKeyword(String PKeyword) {
		if (PKeyword != null) {
			PKeyword = PKeyword.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll(
					"^,|,$", "");
		}
		this.PKeyword = PKeyword;
	}

	@Length(max = 255)
	@Column(name = "P_SEOTITLE")
	public String getPSeoTitle() {
		return this.PSeoTitle;
	}

	public void setPSeoTitle(String PSeoTitle) {
		this.PSeoTitle = PSeoTitle;
	}

	@Length(max = 255)
	@Column(name = "P_SEOKEYWORDS")
	public String getPSeoKeywords() {
		return this.PSeoKeywords;
	}

	public void setPSeoKeywords(String PSeoKeywords) {
		if (PSeoKeywords != null) {
			PSeoKeywords = PSeoKeywords.replaceAll("[,\\s]*,[,\\s]*", ",")
					.replaceAll("^,|,$", "");
		}
		this.PSeoKeywords = PSeoKeywords;
	}

	@Column(name = "P_SEODESCRIPTION")
	public String getPSeoDescription() {
		return this.PSeoDescription;
	}

	public void setPSeoDescription(String PSeoDescription) {
		this.PSeoDescription = PSeoDescription;
	}
	
	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@Column(name = "P_SCORE", precision = 21, scale = 6, nullable = false)
	public Double getPScore() {
		return this.PScore;
	}

	public void setPScore(Double PScore) {
		this.PScore = PScore;
	}

	@Column(name = "P_TOTALSCORE", precision = 15, nullable = false)
	public Double getPTotalScore() {
		return this.PTotalScore;
	}

	public void setPTotalScore(Double PTotalScore) {
		this.PTotalScore = PTotalScore;
	}

	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_SCORECOUNT", precision = 15, nullable = false)
	public Double getPScoreCount() {
		return this.PScoreCount;
	}

	public void setPScoreCount(Double PScoreCount) {
		this.PScoreCount = PScoreCount;
	}
	
	
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_SCORECOUNT1", precision = 15, nullable = false)
	public Double getPScoreCount1() {
		return PScoreCount1;
	}

	public void setPScoreCount1(Double pScoreCount1) {
		PScoreCount1 = pScoreCount1;
	}
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_SCORECOUNT2", precision = 15, nullable = false)
	public Double getPScoreCount2() {
		return PScoreCount2;
	}

	public void setPScoreCount2(Double pScoreCount2) {
		PScoreCount2 = pScoreCount2;
	}
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_SCORECOUNT3", precision = 15, nullable = false)
	public Double getPScoreCount3() {
		return PScoreCount3;
	}

	public void setPScoreCount3(Double pScoreCount3) {
		PScoreCount3 = pScoreCount3;
	}
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_SCORECOUNT4", precision = 15, nullable = false)
	public Double getPScoreCount4() {
		return PScoreCount4;
	}

	public void setPScoreCount4(Double pScoreCount4) {
		PScoreCount4 = pScoreCount4;
	}
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_SCORECOUNT5", precision = 15, nullable = false)
	public Double getPScoreCount5() {
		return PScoreCount5;
	}

	public void setPScoreCount5(Double pScoreCount5) {
		PScoreCount5 = pScoreCount5;
	}

	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_COUNTREVIEW", precision = 15, nullable = false)
	public Double getPCountReview() {
		return PCountReview;
	}

	public void setPCountReview(Double pCountReview) {
		PCountReview = pCountReview;
	}

	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_HITS", precision = 15, nullable = false)
	public Double getPHits() {
		return this.PHits;
	}

	public void setPHits(Double PHits) {
		this.PHits = PHits;
	}

	@JsonProperty
	@Column(name = "P_ISHOT", precision = 15, nullable = false)
	public Boolean getPIsHot() {
		return PIsHot;
	}

	public void setPIsHot(Boolean pIsHot) {
		PIsHot = pIsHot;
	}
	
	@Field(store = Store.YES, index = Index.NO)
	@Column(name = "P_WEEKHITS", precision = 15, nullable = false)
	public Double getPWeekHits() {
		return this.PWeekHits;
	}

	public void setPWeekHits(Double PWeekHits) {
		this.PWeekHits = PWeekHits;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Column(name = "P_MONTHHITS", precision = 15, nullable = false)
	public Double getPMonthHits() {
		return this.PMonthHits;
	}

	public void setPMonthHits(Double PMonthHits) {
		this.PMonthHits = PMonthHits;
	}

	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_SALES", precision = 15, nullable = false)
	public Double getPSales() {
		return this.PSales;
	}

	public void setPSales(Double PSales) {
		this.PSales = PSales;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Column(name = "P_WEEKSALES", precision = 15, nullable = false)
	public Double getPWeekSales() {
		return this.PWeekSales;
	}

	public void setPWeekSales(Double PWeekSales) {
		this.PWeekSales = PWeekSales;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Column(name = "P_MONTHSALES", precision = 15, nullable = false)
	public Double getPMonthSales() {
		return this.PMonthSales;
	}

	public void setPMonthSales(Double PMonthSales) {
		this.PMonthSales = PMonthSales;
	}

	@Column(name = "P_WEEKHITSDATE", length = 19, nullable = false)
	public Date getPWeekHitsDate() {
		return this.PWeekHitsDate;
	}

	public void setPWeekHitsDate(Date PWeekHitsDate) {
		this.PWeekHitsDate = PWeekHitsDate;
	}

	@Column(name = "P_MONTHHITSDATE", length = 19, nullable = false)
	public Date getPMonthHitsDate() {
		return this.PMonthHitsDate;
	}

	public void setPMonthHitsDate(Date PMonthHitsDate) {
		this.PMonthHitsDate = PMonthHitsDate;
	}

	@Column(name = "P_WEEKSALESDATE", length = 19, nullable = false)
	public Date getPWeekSalesDate() {
		return this.PWeekSalesDate;
	}

	public void setPWeekSalesDate(Date PWeekSalesDate) {
		this.PWeekSalesDate = PWeekSalesDate;
	}

	@Column(name = "P_MONTHSALESDATE", length = 19, nullable = false)
	public Date getPMonthSalesDate() {
		return this.PMonthSalesDate;
	}

	public void setPMonthSalesDate(Date PMonthSalesDate) {
		this.PMonthSalesDate = PMonthSalesDate;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE0")
	public String getPAttributeValue0() {
		return this.PAttributeValue0;
	}

	public void setPAttributeValue0(String pAttributeValue0) {
		this.PAttributeValue0 = pAttributeValue0;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE1")
	public String getPAttributeValue1() {
		return this.PAttributeValue1;
	}

	public void setPAttributeValue1(String pAttributeValue1) {
		this.PAttributeValue1 = pAttributeValue1;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE2")
	public String getPAttributeValue2() {
		return this.PAttributeValue2;
	}

	public void setPAttributeValue2(String pAttributeValue2) {
		this.PAttributeValue2 = pAttributeValue2;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE3")
	public String getPAttributeValue3() {
		return this.PAttributeValue3;
	}

	public void setPAttributeValue3(String pAttributeValue3) {
		this.PAttributeValue3 = pAttributeValue3;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE4")
	public String getPAttributeValue4() {
		return this.PAttributeValue4;
	}

	public void setPAttributeValue4(String pAttributeValue4) {
		this.PAttributeValue4 = pAttributeValue4;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE5")
	public String getPAttributeValue5() {
		return this.PAttributeValue5;
	}

	public void setPAttributeValue5(String pAttributeValue5) {
		this.PAttributeValue5 = pAttributeValue5;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE6")
	public String getPAttributeValue6() {
		return this.PAttributeValue6;
	}

	public void setPAttributeValue6(String pAttributeValue6) {
		this.PAttributeValue6 = pAttributeValue6;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE7")
	public String getPAttributeValue7() {
		return this.PAttributeValue7;
	}

	public void setPAttributeValue7(String pAttributeValue7) {
		this.PAttributeValue7 = pAttributeValue7;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE8")
	public String getPAttributeValue8() {
		return this.PAttributeValue8;
	}

	public void setPAttributeValue8(String pAttributeValue8) {
		this.PAttributeValue8 = pAttributeValue8;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE9")
	public String getPAttributeValue9() {
		return this.PAttributeValue9;
	}

	public void setPAttributeValue9(String pAttributeValue9) {
		this.PAttributeValue9 = pAttributeValue9;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE10")
	public String getPAttributeValue10() {
		return this.PAttributeValue10;
	}

	public void setPAttributeValue10(String pAttributeValue10) {
		this.PAttributeValue10 = pAttributeValue10;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE11")
	public String getPAttributeValue11() {
		return this.PAttributeValue11;
	}

	public void setPAttributeValue11(String pAttributeValue11) {
		this.PAttributeValue11 = pAttributeValue11;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE12")
	public String getPAttributeValue12() {
		return this.PAttributeValue12;
	}

	public void setPAttributeValue12(String pAttributeValue12) {
		this.PAttributeValue12 = pAttributeValue12;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE13")
	public String getPAttributeValue13() {
		return this.PAttributeValue13;
	}

	public void setPAttributeValue13(String pAttributeValue13) {
		this.PAttributeValue13 = pAttributeValue13;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE14")
	public String getPAttributeValue14() {
		return this.PAttributeValue14;
	}

	public void setPAttributeValue14(String pAttributeValue14) {
		this.PAttributeValue14 = pAttributeValue14;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE15")
	public String getPAttributeValue15() {
		return this.PAttributeValue15;
	}

	public void setPAttributeValue15(String pAttributeValue15) {
		this.PAttributeValue15 = pAttributeValue15;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE16")
	public String getPAttributeValue16() {
		return this.PAttributeValue16;
	}

	public void setPAttributeValue16(String pAttributeValue16) {
		this.PAttributeValue16 = pAttributeValue16;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE17")
	public String getPAttributeValue17() {
		return this.PAttributeValue17;
	}

	public void setPAttributeValue17(String pAttributeValue17) {
		this.PAttributeValue17 = pAttributeValue17;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE18")
	public String getPAttributeValue18() {
		return this.PAttributeValue18;
	}

	public void setPAttributeValue18(String pAttributeValue18) {
		this.PAttributeValue18 = pAttributeValue18;
	}

	@Length(max = 255)
	@Column(name = "P_ATTRIBUTEVALUE19")
	public String getPAttributeValue19() {
		return this.PAttributeValue19;
	}

	public void setPAttributeValue19(String pAttributeValue19) {
		this.PAttributeValue19 = pAttributeValue19;
	}

	
	@Length(max = 255)
	@Column(name = "P_OTHERLINK1")
	public String getPOtherLink1() {
		return POtherLink1;
	}

	public void setPOtherLink1(String pOtherLink1) {
		POtherLink1 = pOtherLink1;
	}
	
	@Length(max = 255)
	@Column(name = "P_OTHERLINK2")
	public String getPOtherLink2() {
		return POtherLink2;
	}

	public void setPOtherLink2(String pOtherLink2) {
		POtherLink2 = pOtherLink2;
	}
	
	
	@Column(name = "P_ORDER")
	public Double getPOrder() {
		return POrder;
	}

	public void setPOrder(Double pOrder) {
		POrder = pOrder;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "C_UUID", nullable = false)
	public Category getCategory() {
		return category;
	}
	

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * 
	 * @return 商品类型，一旦确认，不可以更改
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SC_UUID", nullable = false)
	public SysCategory getSysCategory(){
		return sysCategory;
	}

	public void setSysCategory(SysCategory sysCategory){
		this.sysCategory = sysCategory;
	}

	/**
	 * 
	 * @return 海关税
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PT_CODE")
	public Ppatax getPpatax() {
		
		return ppatax;
		
	}

	/**
	 * 
	 * @param ppatax 税
	 * 
	 */
	public void setPpatax(Ppatax ppatax) {
		
		this.ppatax = ppatax;
		
	}
	
	
	/**
	 * 
	 * @return 返回商品当前商品单个税=实际交易价*税率
	 */
	@JsonProperty
	@Transient
	public BigDecimal getPtTaxMoneytotal() {
		if (getPpatax() != null){
			return new BigDecimal(this.getPpatax().getPtTax()).multiply(this.getPPrice()).divide(new BigDecimal(100),2);
		}
		return new BigDecimal(0);
	}
	
	/**
	 * 
	 * @return 返回税率100比
	 */
	@JsonProperty
	@Transient
	public String getPtTaxPre() {
		if (getPpatax() != null){
			BigDecimal v=new BigDecimal(this.getPpatax().getPtTax());//.multiply(new BigDecimal(100.00));
			//v=v.divide(new BigDecimal(100),2);
			String rv=String.valueOf(v.doubleValue())+"%";
			return rv;
		}
		return "0";
	}
	
	
	
	/**
	 * 
	 * @return 返回所有的产品总销量=基础销量+实际销量
	 */
	@JsonProperty
	@Transient
	public String getPAllSales() {
		Double v=0.00;
		
		if(null!=getPSales() ){
			v=v+getPSales();
		}
		if(null!=getPBaseSales()){
			v=v+getPBaseSales();
		}
		
		
		String rv=String.valueOf(v.doubleValue());
		if(null!=rv && rv.length()>0 ){
			
			rv=rv.split("\\.")[0];
		}
		
		return rv;
		
	}
	
	
	/**
	 * 
	 * @return 返回所有的产品的浏览数
	 */
	@JsonProperty
	@Transient
	public String getPAllHits() {
		Double v=0.00;
		
		if(null!=getPHits() ){
			v=v+getPHits();
		}
		if(null!=getPBaseHits()){
			v=v+getPBaseHits();
		}
		
		
		String rv=String.valueOf(v.doubleValue());
		if(null!=rv && rv.length()>0 ){
			
			rv=rv.split("\\.")[0];
		}
		
		return rv;
		
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B_UUID")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "G_UUID", nullable = false, updatable = false)
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SP_P_PT_PROTAG", joinColumns = { @JoinColumn(name = "P_UUID") }, inverseJoinColumns = { @JoinColumn(name = "T_UUID") })
	@OrderBy("orders asc")
	public Set<Tag> getTags() {
		return this.tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SP_P_PS_PROSPEC", joinColumns = { @JoinColumn(name = "P_UUID") }, inverseJoinColumns = { @JoinColumn(name = "S_UUID") })
	@OrderBy("orders asc")
	public Set<Specification> getSpecifications() {
		return this.specifications;
	}

	public void setSpecifications(Set<Specification> specifications) {
		this.specifications = specifications;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SP_P_PSD_PROSPECDTL", joinColumns = { @JoinColumn(name = "P_UUID") }, inverseJoinColumns = { @JoinColumn(name = "SD_UUID") })
	@OrderBy("orders asc")
	public Set<SpecificationDtl> getSpecificationDtls() {
		return specificationDtls;
	}

	public void setSpecificationDtls(Set<SpecificationDtl> specificationDtls) {
		this.specificationDtls = specificationDtls;
	}

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "SP_P_PPV_PROPARAVAL", joinColumns = { @JoinColumn(name = "P_UUID") })
	@MapKeyJoinColumn(name = "PD_UUID")
	@Column(name = "VAL")
	public Map<ParameterDtl, String> getParameterDtls() {
		return this.parameterDtls;
	}

	public void setParameterDtls(Map<ParameterDtl, String> parameterDtls) {
		this.parameterDtls = parameterDtls;
	}

	@JsonProperty
	@Valid
	@ElementCollection
	@CollectionTable(name = "SP_P_PI_PROIMAGE", joinColumns = @JoinColumn(name = "P_UUID"))
	public List<ProductImage> getProductImages() {
		return this.productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.REMOVE })
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.REMOVE })
	public Set<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	@ManyToMany(mappedBy = "collectProducts", fetch = FetchType.LAZY)
	public Set<Member> getCollectMembers() {
		return this.collectMembers;
	}

	public void setCollectMembers(Set<Member> collectMembers) {
		this.collectMembers = collectMembers;
	}

	
	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	public Set<Promotion> getPromotions() {
		return this.promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.REMOVE })
	public Set<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@OneToMany(mappedBy = "gift", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.ALL })
	public Set<GiftItem> getGiftItems() {
		return this.giftItems;
	}

	public void setGiftItems(Set<GiftItem> giftItems) {
		this.giftItems = giftItems;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.REMOVE })
	public Set<Notify> getNotifys() {
		return notifys;
	}

	public void setNotifys(Set<Notify> notifys) {
		this.notifys = notifys;
	}

	
	

	/**
	 * 
	 * @return 返回会员对应的产品价格集合
	 */
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "SP_P_PMP_PROMENPRICE", joinColumns = @JoinColumn(name = "P_UUID"))
	@MapKeyJoinColumn(name = "MR_UUID")
	@Column(name = "PMP_VAL")
	public Map<MemberRank, BigDecimal> getMemberPrice() {
		return this.memberPrice;
	}

	public void setMemberPrice(Map<MemberRank, BigDecimal> memberPrice) {
		this.memberPrice = memberPrice;
	}

//	@Transient
//	public String getAttributeValue(Attribute attribute) {
//		if ((attribute != null) && (attribute.getAIndex() != null)) {
//			try {
//				String _index = (attribute.getAIndex());//.longValue();
//				String attName = "PAttributeValue" + _index;
//				return (String) PropertyUtils.getProperty(this, attName);
//			} catch (IllegalAccessException e1) {
//				e1.printStackTrace();
//			} catch (InvocationTargetException e2) {
//				e2.printStackTrace();
//			} catch (NoSuchMethodException e3) {
//				e3.printStackTrace();
//			}
//		}
//		return null;
//	}

//	@Transient
//	public void setAttributeValue(Attribute attribute, String value) {
//		if ((attribute != null) && (attribute.getAIndex() != null)) {
//			if (StringUtils.isEmpty(value)) {
//				value = null;
//			}
//			if ((value == null)
//					|| ((attribute.getOptions() != null) && (attribute
//							.getOptions().contains(value)))) {
//				try {
//					String _index = (attribute.getAIndex());//.longValue();
//					String attName = "PAttributeValue" + _index;
//					PropertyUtils.setProperty(this, attName, value);
//				} catch (IllegalAccessException localIllegalAccessException1) {
//					localIllegalAccessException1.printStackTrace();
//				} catch (InvocationTargetException localInvocationTargetException1) {
//					localInvocationTargetException1.printStackTrace();
//				} catch (NoSuchMethodException localNoSuchMethodException1) {
//					localNoSuchMethodException1.printStackTrace();
//				}
//			}
//		}
//	}

//	@Transient
//	public List<Product> getSiblings() {
//		ArrayList<Product> _list = new ArrayList<Product>();
//		if ((getGoods() != null) && (getGoods().getProducts() != null)) {
//			Iterator<Product> _iterator = getGoods().getProducts().iterator();
//			while (_iterator.hasNext()) {
//				Product _product = _iterator.next();
//
//				// String mp=_product.getMobilePath();
//				if (!equals(_product)) {
//					_list.add(_product);
//				}
//			}
//		}
//		return _list;
//	}

//	@JsonProperty
//	@Transient
//	public String getPath() {
//		HashMap<String, Object> _hmap = new HashMap<String, Object>();
//		_hmap.put("id", getPUuid());
//		_hmap.put("createtime", getCreatetime());
//		_hmap.put("edittime", getEdittime());
//		_hmap.put("sn", getPSn());
//		_hmap.put("name", getPName());
//		_hmap.put("fullName", getPFullName());
//		_hmap.put("seoTitle", getPSeoTitle());
//		_hmap.put("seoKeywords", getPSeoKeywords());
//		_hmap.put("seoDescription", getPSeoDescription());
//		_hmap.put("category", getCategory());
//		return FreemarkerUtils.process(staticPath, _hmap);
//	}
	
	/**
	 * 
	 * @return 返回对应的手机路径
	 */
//	@JsonProperty
//	@Transient
//	public String getMobilePath() {
//		HashMap<String, Object> _hmap = new HashMap<String, Object>();
//		_hmap.put("id", getPUuid());
//		_hmap.put("createtime", getCreatetime());
//		_hmap.put("edittime", getEdittime());
//		_hmap.put("sn", getPSn());
//		_hmap.put("name", getPName());
//		_hmap.put("fullName", getPFullName());
//		_hmap.put("seoTitle", getPSeoTitle());
//		_hmap.put("seoKeywords", getPSeoKeywords());
//		_hmap.put("seoDescription", getPSeoDescription());
//		_hmap.put("category", getCategory());
//		String pt=FreemarkerUtils.process(staticMobilePath, _hmap);
//		return pt;
//	}
	
	/**
	 * 
	 * @return 返回对应的产品小图路径
	 */
	@JsonProperty
	@Transient
	public String getSmallPicPath() {
		if ((getProductImages() != null) && (!getProductImages().isEmpty())) {
			return ((ProductImage) getProductImages().get(0)).getISmall();
		}
		return null;
	}
	
	/**
	 * 
	 * @return 返回对应的产品大图路径
	 */
	@JsonProperty
	@Transient
	public String getLargePicPath() {
		if ((getProductImages() != null) && (!getProductImages().isEmpty())) {
			return ((ProductImage) getProductImages().get(0)).getILarge();
		}
		return null;
	}
	


	/**
	 * return 返回满足条件的所有和这个产品有关的促销
	 */
//	@Transient
//	public Set<Promotion> getValidPromotions() {
//		HashSet<Promotion> _hset = new HashSet<Promotion>();
//
//		//和这个产品有关的所有促销
//		if (getPromotions() != null) {
//			_hset.addAll(getPromotions());
//		}
//
//		//和这个产品的目录有关的所有促销
//		if ((getCategory() != null) && (getCategory().getPromotions() != null)) {
//			_hset.addAll(getCategory().getPromotions());
//		}
//
//		//和这个品牌有关的所有促销
//		if ((getBrand() != null) && (getBrand().getPromotions() != null)) {
//			_hset.addAll(getBrand().getPromotions());
//		}
//
//		TreeSet<Promotion> _tset = new TreeSet<Promotion>();
//		Iterator<Promotion> _iterator = _hset.iterator();
//		while (_iterator.hasNext()) {
//			Promotion _promotion = _iterator.next();
//			//只有满足当前时间>开始时间,并且不这空和当前时间<结束时间,才是我们要的促销
//			if ((_promotion != null) && (_promotion.hasBegun())
//					&& (!_promotion.hasEnded())) {
//				_tset.add(_promotion);
//			}
//		}
//		return _tset;
//	}

	@JsonProperty
	@Transient
	public Integer getAvailableStock() {
		Integer _availableCount = null;
		if ((getPStock() != null) && (getPAllOcatedStock() != null)) {
			_availableCount = Integer.valueOf(getPStock().intValue()
					- getPAllOcatedStock().intValue());
			if (_availableCount.intValue() < 0)
				_availableCount = Integer.valueOf(0);
		}
		return _availableCount;
	}

	/**
	 * 预分配库存是否大于当前的库存
	 * @return 大于返回true,否则返加false
	 */
	@Transient
	public Boolean getIsOutOfStock() {
		
		//System.out.println("1getPAllOcatedStock().intValue:"+getPAllOcatedStock().intValue());
		//System.out.println("2getPStock().intValue():"+getPStock().intValue());
		int _pAllOcatedStock=0;
		if (getPAllOcatedStock() != null){
			_pAllOcatedStock=getPAllOcatedStock().intValue();
			if( _pAllOcatedStock<0){
				_pAllOcatedStock=0;
			}
		}		
		if ((getPStock() != null) && (getPAllOcatedStock() != null)
				&& (_pAllOcatedStock >= getPStock().intValue())) {
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

//	@PreRemove
//	public void preRemove() {
//
//		Set<Member> _memberSet = getCollectMembers();
//		if (_memberSet != null) {
//			Iterator<Member> _iterator = _memberSet.iterator();
//			while (_iterator.hasNext()) {
//				Member _member = _iterator.next();
//				_member.getCollectProducts().remove(this);
//			}
//		}
//
//		Set<Promotion> _promotionSet = getPromotions();
//		if (_promotionSet != null) {
//			Iterator<Promotion> _iterator = _promotionSet.iterator();
//			while (_iterator.hasNext()) {
//				Promotion _promotion = _iterator.next();
//				_promotion.getProducts().remove(this);
//			}
//		}
//
//		Set<OrderItem> _orderSet = getOrderItems();
//		if (_orderSet != null) {
//			Iterator<OrderItem> _iterator = _orderSet.iterator();
//			while (_iterator.hasNext()) {
//				OrderItem _orderItem = _iterator.next();
//				_orderItem.setProduct(null);
//			}
//		}
//	}

	@PrePersist
	public void prePersist() {
		if (getPStock() == null) {
			setPAllOcatedStock(0D);
		}
		setPScore(0D);
	}

	@PreUpdate
	public void preUpdate() {
		if (getPStock() == null) {
			setPAllOcatedStock(0D);
		}
		if ((getPTotalScore() != null) && (getPScoreCount() != null)
				&& (getPScoreCount().longValue() != 0L)) {
			setPScore(Double.valueOf((float) getPTotalScore().longValue()
					/ (float) getPScoreCount().longValue()));
		} else {
			setPScore(0D);
		}
	}

	public String toString() {
		return getPFullName();
	}

	@JsonProperty
	@Length(max = 200)
	@Column(name = "P_ERPCODE")
	public String getPErpCode() {
		return PErpCode;
	}

	public void setPErpCode(String pErpCode) {
		PErpCode = pErpCode;
	}
	
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_COUNTCOLLECT", precision = 15)
	public Double getPCountCollect() {
		if(PCountCollect==null){
			PCountCollect=0.00;
		}
		return PCountCollect;
	}

	public void setPCountCollect(Double pCountCollect) {
		if(pCountCollect==null){
			pCountCollect=0.00;
		}
		PCountCollect = pCountCollect;
	}
	
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Column(name = "P_COUNTSHARE", precision = 15)
	public Double getPCountShare() {
		if(PCountShare==null){
			PCountShare=0.00;
		}
		return PCountShare;
	}

	public void setPCountShare(Double pCountShare) {
		if(pCountShare==null){
			pCountShare=0.00;
		}
		PCountShare = pCountShare;
	}
//-------------------------------------------------------------
	@JsonProperty
	@NotNull
	@Column(name = "P_ISFINISHFAX", precision = 15, nullable = false)
	public Boolean getPIsFinishFax() {
		return PIsFinishFax;
	}

	public void setPIsFinishFax(Boolean pIsFinishFax) {
		PIsFinishFax = pIsFinishFax;
	}

	@JsonProperty
	@NotNull
	@Column(name = "P_ISGETSUBTRADERMONEY", precision = 15, nullable = false)
	public Boolean getPIsGetSubTraderMoney() {
		return PIsGetSubTraderMoney;
	}

	public void setPIsGetSubTraderMoney(Boolean pIsGetSubTraderMoney) {
		PIsGetSubTraderMoney = pIsGetSubTraderMoney;
	}

	@JsonProperty
	@NotNull
	@Column(name = "P_ISNOTRADERMONEY", precision = 15, nullable = false)
	public Boolean getPIsNoTraderMoney() {
		return PIsNoTraderMoney;
	}

	public void setPIsNoTraderMoney(Boolean pIsNoTraderMoney) {
		PIsNoTraderMoney = pIsNoTraderMoney;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_TRADERFIRSTMONEY", precision = 21, scale = 6, nullable = false)
	public BigDecimal getPTraderFirstMoney() {
		return PTraderFirstMoney;
	}

	public void setPTraderFirstMoney(BigDecimal pTraderFirstMoney) {
		PTraderFirstMoney = pTraderFirstMoney;
	}
	
	@Column(name = "P_TRADERFIRSTPEA", precision = 15)
	public Double getPTraderFirstPea() {
		return PTraderFirstPea;
	}

	public void setPTraderFirstPea(Double pTraderFirstPea) {
		PTraderFirstPea = pTraderFirstPea;
	}
	
	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_TRADERSECONDMONEY", precision = 21, scale = 6, nullable = false)
	public BigDecimal getPTraderSecondMoney() {
		return PTraderSecondMoney;
	}

	public void setPTraderSecondMoney(BigDecimal pTraderSecondMoney) {
		PTraderSecondMoney = pTraderSecondMoney;
	}
	
	@Column(name = "P_TRADERSECONDPEA", precision = 15)
	public Double getPTraderSecondPea() {
		return PTraderSecondPea;
	}

	public void setPTraderSecondPea(Double pTraderSecondPea) {
		PTraderSecondPea = pTraderSecondPea;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_TRADERTHREEMONEY", precision = 21, scale = 6, nullable = false)
	public BigDecimal getPTraderThreeMoney() {
		return PTraderThreeMoney;
	}

	public void setPTraderThreeMoney(BigDecimal pTraderThreeMoney) {
		PTraderThreeMoney = pTraderThreeMoney;
	}
	
	@Column(name = "P_TRADERTHREEPEA", precision = 15)
	public Double getPTraderThreePea() {
		return PTraderThreePea;
	}

	public void setPTraderThreePea(Double pTraderThreePea) {
		PTraderThreePea = pTraderThreePea;
	}

	@JsonProperty
	@NotNull
	@Column(name = "P_ISGETSUBAGENTMONEY", precision = 15, nullable = false)
	public Boolean getPIsGetSubAgentMoney() {
		return PIsGetSubAgentMoney;
	}

	public void setPIsGetSubAgentMoney(Boolean pIsGetSubAgentMoney) {
		PIsGetSubAgentMoney = pIsGetSubAgentMoney;
	}

	@JsonProperty
	@NotNull
	@Column(name = "P_ISNOAGENTMONEY", precision = 15, nullable = false)
	public Boolean getPIsNoAgentMoney() {
		return PIsNoAgentMoney;
	}

	public void setPIsNoAgentMoney(Boolean pIsNoAgentMoney) {
		PIsNoAgentMoney = pIsNoAgentMoney;
	}

	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_AGENTFIRSTMONEY", precision = 21, scale = 6, nullable = false)
	public BigDecimal getPAgentFirstMoney() {
		return PAgentFirstMoney;
	}

	public void setPAgentFirstMoney(BigDecimal pAgentFirstMoney) {
		PAgentFirstMoney = pAgentFirstMoney;
	}

	@Column(name = "P_AGENTFIRSTPEA", precision = 15)
	public Double getPAgentFirstPea() {
		return PAgentFirstPea;
	}

	public void setPAgentFirstPea(Double pAgentFirstPea) {
		PAgentFirstPea = pAgentFirstPea;
	}
	
	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_AGENTSECONDMONEY", precision = 21, scale = 6, nullable = false)
	public BigDecimal getPAgentSecondMoney() {
		return PAgentSecondMoney;
	}

	public void setPAgentSecondMoney(BigDecimal pAgentSecondMoney) {
		PAgentSecondMoney = pAgentSecondMoney;
	}

	@Column(name = "P_AGENTSECONDPEA", precision = 15)
	public Double getPAgentSecondPea() {
		return PAgentSecondPea;
	}

	public void setPAgentSecondPea(Double pAgentSecondPea) {
		PAgentSecondPea = pAgentSecondPea;
	}
	
	@Field(store = Store.YES, index = Index.NO)
	@Min(0L)
	@Digits(integer = 12, fraction = 3)
	@Column(name = "P_AGENTTHREEMONEY", precision = 21, scale = 6, nullable = false)
	public BigDecimal getPAgentThreeMoney() {
		return PAgentThreeMoney;
	}

	public void setPAgentThreeMoney(BigDecimal pAgentThreeMoney) {
		PAgentThreeMoney = pAgentThreeMoney;
	}

	@Column(name = "P_AGENTTHREEPEA", precision = 15)
	public Double getPAgentThreePea() {
		return PAgentThreePea;
	}

	public void setPAgentThreePea(Double pAgentThreePea) {
		PAgentThreePea = pAgentThreePea;
	}

	@JsonProperty
	@NotNull
	@Column(name = "P_ISNOLOGISTICS", precision = 15, nullable = false)
	public Boolean getPIsNoLogistics() {
		return PIsNoLogistics;
	}

	public void setPIsNoLogistics(Boolean pIsNoLogistics) {
		PIsNoLogistics = pIsNoLogistics;
	}


	@JsonProperty
	@NotNull
	@Column(name = "P_ISSHOWSTOCK", precision = 15, nullable = false)
	public Boolean getPIsShowStock() {
		return PIsShowStock;
	}

	public void setPIsShowStock(Boolean pIsShowStock) {
		PIsShowStock = pIsShowStock;
	}

	@Column(name = "P_FIRSTPOINT", precision = 15)
	public Double getPFirstPoint() {
		return PFirstPoint;
	}

	public void setPFirstPoint(Double pFirstPoint) {
		PFirstPoint = pFirstPoint;
	}

	@Column(name = "P_SECONDPOINT", precision = 15)
	public Double getPSecondPoint() {
		return PSecondPoint;
	}

	public void setPSecondPoint(Double pSecondPoint) {
		PSecondPoint = pSecondPoint;
	}

	@Column(name = "P_THREEPOINT", precision = 15)
	public Double getPThreePoint() {
		return PThreePoint;
	}

	public void setPThreePoint(Double pThreePoint) {
		PThreePoint = pThreePoint;
	}

	@Column(name = "P_BASESALES", precision = 15)
	public Double getPBaseSales() {
		return PBaseSales;
	}

	public void setPBaseSales(Double pBaseSales) {
		PBaseSales = pBaseSales;
	}

	@Column(name = "P_PRAISENUM", precision = 15)
	public Double getPPraiseNum() {
		return PPraiseNum;
	}

	public void setPPraiseNum(Double pPraiseNum) {
		PPraiseNum = pPraiseNum;
	}
	
	@Column(name = "P_BASEPRAISENUM", precision = 15)
	public Double getPBasePraiseNum() {
		return PBasePraiseNum;
	}

	public void setPBasePraiseNum(Double pBasePraiseNum) {
		PBasePraiseNum = pBasePraiseNum;
	}

	@Column(name = "P_PVOLUME", precision = 15)
	public Double getPVolume() {
		return PVolume;
	}

	public void setPVolume(Double pVolume) {
		PVolume = pVolume;
	}
	@Column(name = "P_BASEHITS", precision = 15, nullable = false)
	public Double getPBaseHits() {
		return PBaseHits;
	}

	public void setPBaseHits(Double pBaseHits) {
		PBaseHits = pBaseHits;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}