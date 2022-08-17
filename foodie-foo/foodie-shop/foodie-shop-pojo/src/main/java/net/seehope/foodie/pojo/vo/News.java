package net.seehope.foodie.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`news`")
public class News implements Serializable {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`author_avatar`")
    private String authorAvatar;

    @Column(name = "`author_email`")
    private String authorEmail;

    @Column(name = "`author_name`")
    private String authorName;

    @Column(name = "`column_id`")
    private Integer columnId;

    @Column(name = "`column_name`")
    private String columnName;

    @Column(name = "`comments_count`")
    private Integer commentsCount;

    @Column(name = "`content`")
    private String content;

    @Column(name = "`cover`")
    private String cover;

    @Column(name = "`created_at`")
    private String createdAt;

    @Column(name = "`from_id`")
    private Integer fromId;

    @Column(name = "`post_id`")
    private Integer postId;

    @Column(name = "`published_at`")
    private Date publishedAt;

    @Column(name = "`store_at`")
    private Date storeAt;

    @Column(name = "`summary`")
    private String summary;

    @Column(name = "`title`")
    private String title;

    @Column(name = "`type`")
    private String type;

    @Column(name = "`updated_at`")
    private Date updatedAt;

    @Column(name = "`views_count`")
    private Integer viewsCount;

    private static final long serialVersionUID = 1L;
}