package yxm.filmmanage.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data      //lombok插件，在编译时为实体类添加get/set等方法
@EntityListeners(AuditingEntityListener.class)          // 为@CreatDate等注解有效，在类上面添加该注解
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "tel")
    private String tel;

    @Column(name = "address")
    private String address;

    // 创建时间
    @CreatedDate
    @Column(name = "creatDate")
    private Date creatDate;

    // 最后一次修改时间
    @LastModifiedDate
    @Column(name = "updateDate")
    private Date updateDate;


}
