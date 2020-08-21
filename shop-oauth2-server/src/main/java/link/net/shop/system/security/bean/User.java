package link.net.shop.system.security.bean;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/8/20 16:09
 */
@Entity
@Table(
        name = "user"
)
@Setter
@Getter
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "oauth_password")
    private String oauthpassword;
    @Column(name = "role")
    private String role;
}
