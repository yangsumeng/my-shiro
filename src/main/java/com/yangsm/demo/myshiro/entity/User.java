package com.yangsm.demo.myshiro.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 说明：
 * 这里使用@Getter,@Setter注解，不能使用@Data注解，因为实体使用了jpa的@oneToMany ，
 * 加载方式为lazy，在主表查询时关联表未加载，而主表使用@Data后会实现带关联表属性的hashCode和equals等方法。
 * 在运行过程中调用关联表数据时会显示异常 java.lang.stackoverflowerror。
 */
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName; //登录用户名

    @Column(nullable = false)
    private String name;//名称（昵称或者真实姓名，根据实际情况定义）

    @Column(nullable = false)
    private String password;

    private String salt;//加密密码的盐

    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "UserRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<Role> roleList;// 一个用户具有多个角色

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiredDate;//过期日期

    private String email;

    /**密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解 */
    public String getCredentialsSalt(){
        return this.userName+this.salt;
    }
}