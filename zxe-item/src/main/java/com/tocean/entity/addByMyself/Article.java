package com.tocean.entity.addByMyself;

import com.tocean.base.baseObject.BaseEntity;
import com.tocean.entity.item.Tag;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/9.
 */
@Entity
@Table(name="item_article")
@Data
public class Article extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UUID", unique = true, nullable = false, length = 44)
    private String uuid;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "item_tags", joinColumns = @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "UUID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "T_UUID"))
    private Set<Tag> tags =new HashSet<Tag>();
}
