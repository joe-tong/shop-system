package link.net.shop.system.user.model.admin;

import link.net.shop.system.user.model.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/24 15:02
 */
@Entity
@Table(
        name = "shop_admin_user",
        indexes = {
                @Index(columnList="tel"),
        }
)
@Setter
@Getter
public class ShopAdminUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String tel;
    private String role;
    private String name;
}
