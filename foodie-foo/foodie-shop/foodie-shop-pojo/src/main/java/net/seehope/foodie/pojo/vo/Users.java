package net.seehope.foodie.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "`users`")
public class Users implements Serializable {
    @Id
    @Column(name = "`uid`")
    private Integer uid;

    @Column(name = "`uname`")
    private String uname;

    @Column(name = "`uage`")
    private Integer uage;

    private static final long serialVersionUID = 1L;
}