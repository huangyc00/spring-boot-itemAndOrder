package com.tocean.base.baseObject;


import com.tocean.base.listener.BaseEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;



/**
 * 如新业务表是操作的帐户是admin表的操作员，请继承这个类。
 * 实体的公共类,提供创建者,创建时间 ,编辑人,编辑时间几个通用的属性值变动
 * 系统提供二个基类，一个是vip会员为主导的，一类是公司内部员工为主导
 *
 * @author alan.yan
 * @since 2016年8月29日
 */
@EntityListeners({BaseEntityListener.class})
@MappedSuperclass
public abstract class BaseEntity implements Serializable{


    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @Column(name = "TENANT_ID", length = 44)
    private String 	tenantId;
    /**
     * 创建人
     */
    @Column(name = "CREATEMAN", length = 44)
    private String 	createman;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME", length = 19)
    private Timestamp 	createtime;

    /**
     * 编辑人
     */
    @Column(name = "EDITMAN", length = 44)
    private String 	editman;

    /**
     * 编辑时间
     */
    @Column(name = "EDITTIME", length = 19)
    private Timestamp 	edittime;

    /**
     * 是否逻辑删除，实现删除的表也冗余这个字段，但发生删除时则是物理删除
     */
    @Column(name = "ISDEL",nullable = false, length = 1)
    private Integer isdel=0; //这里设置一个默认值

    /**
     * 排序
     */
    @Column(name = "ORDERS")
    private Integer orders=0; //这里设置一个默认值

    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }



    public String getEditman() {
        return editman;
    }

    public void setEditman(String editman) {
        this.editman = editman;
    }


    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getEdittime() {
        return edittime;
    }

    public void setEdittime(Timestamp edittime) {
        this.edittime = edittime;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }


    public class Save {


    }
}

