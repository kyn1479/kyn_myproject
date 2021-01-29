package com.kyn.myproject.demo.shiro_demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kyn.myproject.demo.shiro_demo.enums.StatusEnum;
import com.kyn.myproject.demo.shiro_demo.utils.StatusUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kangyanan
 * @Description: 角色表
 * @date 2021/1/28 16:49
 */
@Data
@Entity
@Table(name = "sys_role")
@ToString(exclude = {"users", "menus", "createBy", "updateBy"})
@EqualsAndHashCode(exclude = {"users", "menus", "createBy", "updateBy"})
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update sys_role" + StatusUtil.SLICE_DELETE)
@Where(clause = StatusUtil.NOT_DELETE)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //名称
    private String name;
    //主题
    private String title;
    //备注
    private String remark;
    //创建时间
    @CreatedDate
    private Date createTime;
    //修改时间
    @LastModifiedDate
    private Date updateTime;
    //创建人
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name = "create_by")
    @JsonIgnore
    private User createBy;
    //修改人
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name = "update_by")
    @JsonIgnore
    private User updateBy;
    //状态
    private Byte status = StatusEnum.OK.getCode();

    //用户
    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<User> users = new HashSet<>(0);

    //权限
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_menu",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    @JsonIgnore
    private Set<Menu> menus = new HashSet<>(0);
}
