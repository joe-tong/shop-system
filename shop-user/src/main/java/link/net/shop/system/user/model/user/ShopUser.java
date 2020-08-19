package link.net.shop.system.user.model.user;

import link.net.shop.system.user.model.BaseEntity;
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
        name = "shop_user",
        indexes = {
                @Index(columnList="id_card"),
        }
)
@Setter
@Getter
public class ShopUser  {
    @Id
    @GeneratedValue
    private Integer id;
    private String tel;
    private String role;
    private String name;
    @Column(name = "id_card")
    private String idCard;
}
