package net.seehope.foodie.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`user`")
public class User implements Serializable {
    @Id
    @Column(name = "`id`")
    private String id;

    /**
     * 用户名称
     */
    @Column(name = "`username`")
    private String username;

    /**
     * 生日
     */
    @Column(name = "`birthday`")
    private Date birthday;

    /**
     * 性别
     */
    @Column(name = "`sex`")
    private String sex;

    /**
     * 地址
     */
    @Column(name = "`address`")
    private String address;

    /**
     * 密码
     */
    @Column(name = "`password`")
    @JsonProperty
    private String password;

    /**
     * 手机号
     */
    @Column(name = "`mobile`")
    private String mobile;

    private static final long serialVersionUID = 1L;
}