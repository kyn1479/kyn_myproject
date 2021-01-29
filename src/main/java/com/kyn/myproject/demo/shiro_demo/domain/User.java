package com.kyn.myproject.demo.shiro_demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kyn.myproject.demo.shiro_demo.enums.StatusEnum;
import com.kyn.myproject.demo.shiro_demo.utils.StatusUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kangyanan
 * @Description: 用户表
 * @date 2021/1/28 15:24
 */
@Data
@Entity
@Table(name = "sys_user")
@ToString(exclude = {"roles"})
@EqualsAndHashCode(exclude = {"roles"})
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update sys_user" + StatusUtil.SLICE_DELETE)
@Where(clause = StatusUtil.NOT_DELETE)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //用户名
    private String username;
    //密码
    @JsonIgnore
    private String password;
    //盐
    @JsonIgnore
    private String salt;
    //手机号
    private String phone;
    //邮箱
    private String email;
    //创建时间
    @CreatedDate
    private Date createTime;
    //修改时间
    @LastModifiedDate
    private Date updateTime;
    //备注
    private String remark;
    //状态
    private Byte status = StatusEnum.OK.getCode();
    //角色
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles = new HashSet<>(0);


}
