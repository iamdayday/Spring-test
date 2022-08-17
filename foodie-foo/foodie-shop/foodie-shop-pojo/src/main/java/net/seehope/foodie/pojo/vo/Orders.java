package net.seehope.foodie.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`orders`")
public class Orders implements Serializable {
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 下单用户id
     */
    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * 订单号
     */
    @Column(name = "`number`")
    private String number;

    /**
     * 创建订单时间
     */
    @Column(name = "`createtime`")
    private Date createtime;

    /**
     * 备注
     */
    @Column(name = "`note`")
    private String note;

    private static final long serialVersionUID = 1L;
}