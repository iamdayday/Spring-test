package net.seehope.foodie.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "`account`")
public class Account implements Serializable {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`username`")
    private String username;

    @Column(name = "`money`")
    private Double money;

    private static final long serialVersionUID = 1L;
}