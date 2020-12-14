package yxm.filmmanage.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)          // 为@CreatDate等注解有效，在类上面添加该注解
@Table(name = "news")
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer newsId;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @CreatedDate
    @Column(name = "creatDate")
    private Date creatDate;

    @LastModifiedDate
    @Column(name = "updateDate")
    private Date updateDate;

}
