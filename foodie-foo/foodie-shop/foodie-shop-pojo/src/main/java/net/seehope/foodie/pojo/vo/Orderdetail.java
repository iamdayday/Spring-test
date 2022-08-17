package net.seehope.foodie.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "`orderdetail`")
public class Orderdetail implements Serializable {
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "`orders_id`")
    private Integer ordersId;

    /**
     * 商品id
     */
    @Column(name = "`items_id`")
    private Integer itemsId;

    /**
     * 商品购买数量
     */
    @Column(name = "`items_num`")
    private Integer itemsNum;

    private static final long serialVersionUID = 1L;
}