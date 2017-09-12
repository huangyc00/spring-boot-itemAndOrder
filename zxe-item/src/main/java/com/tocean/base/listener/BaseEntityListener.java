package com.tocean.base.listener;


/**
 * 提供主核心表进行用户操作轨迹记录。这里不提供vip之类的帐号操作
 *
 * @author alan.yan
 * @since 2016年8月29日
 *
 */
public class BaseEntityListener {




//    /**
//     * 新增数据时创建人,修改人,创建时间,修改时间进行标识,当是执行insert时确发
//     * <code>
//     * @PrePersist 和 @PostPersist 事 件 在 实 体 对 象 插 入 到 数 据 库 的 过 程 中 发 生 ，
//     * @PrePersist 事件在调用 EntityManager.persist( )方法后立刻发生， 级联保存
//     * 也会发生此事 件，此时的数据还没有真实插入进数据库。
//     * @PostPersist 事件在数据已经插入进数据库后 发生。</code>
//     * @param entity 回调监听的bean
//     */
//    @PrePersist
//    public void prePersist(BaseEntity entity) {
//
//        //取出当前的登录用户
//        Subject currentUser = SecurityUtils.getSubject();
//
//        CustomPrincipal principal = (CustomPrincipal)currentUser.getPrincipal();
//        if(principal!=null){
//            String userUuid = principal.getLoginUuid();
//            //创建人,修改人都同样上uuid
//            entity.setCreateman(userUuid);
//            entity.setEditman(userUuid);
//        }
//        /**
//         * 创建人,修改人都赋上当前的日期
//         */
//        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        entity.setCreatetime(ts);
//        entity.setEdittime(ts);
//    }
//
//    /**
//     * 更改数据时,对修改人及修改时间进行更新,当发生update时确发
//     * @param entity
//     */
//    @PreUpdate
//    public void preUpdate(BaseEntity entity) {
//        Subject currentUser = SecurityUtils.getSubject();
//        CustomPrincipal principal = (CustomPrincipal)currentUser.getPrincipal();
//        if(principal!=null){
//            String userUuid = principal.getLoginUuid();
//            entity.setEditman(userUuid);
//        }
//        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        entity.setEdittime(ts);
//
//    }

}
