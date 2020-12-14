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
@Table(name = "film")
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer filmId;

    @Column(name = "coverImg")
    private String coverImg;

    @Column(name = "filmname")
    private String filmName;

    @Column(name = "classify")
    private String classify;           //类型

    @Column(name = "director")
    private String director;           //导演

    @Column(name = "showtime")         //上映时间
    private String showtime;

    @Column(name = "movie")
    private String movie;              //影片资源地址

    @Column(name = "sketch")
    private String sketch;             //简述

    @CreatedDate
    @Column(name = "creatDate")
    private Date creatDate;

    @LastModifiedDate
    @Column(name = "updateDate")
    private Date updateDate;

}
