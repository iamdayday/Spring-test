package net.seehope.foodie.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`items`")
public class Items implements Serializable {
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 商品名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 商品定价
     */
    @Column(name = "`price`")
    private Float price;

    /**
     * 商品图片
     */
    @Column(name = "`pic`")
    private String pic;

    /**
     * 生产日期
     */
    @Column(name = "`createtime`")
    private Date createtime;

    /**
     * 商品描述
     */
    @Column(name = "`detail`")
    private String detail;

    private static final long serialVersionUID = 1L;
}