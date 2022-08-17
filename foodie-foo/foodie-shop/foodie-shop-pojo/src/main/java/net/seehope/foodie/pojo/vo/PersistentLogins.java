package net.seehope.foodie.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`persistent_logins`")
public class PersistentLogins implements Serializable {
    @Id
    @Column(name = "`series`")
    private String series;

    @Column(name = "`username`")
    private String username;

    @Column(name = "`token`")
    private String token;

    @Column(name = "`last_used`")
    private Date lastUsed;

    private static final long serialVersionUID = 1L;
}