package net.seehope.foodie.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "`uuid_user`")
public class UuidUser implements Serializable {
    @Id
    @Column(name = "`id`")
    private String id;

    @Column(name = "`mark`")
    private String mark;

    private static final long serialVersionUID = 1L;
}