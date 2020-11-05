package link.net.shop.system.product.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/27 19:52
 */
@Entity
@Table(
        name = "product"
)
@Data
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String img;
    private String brandName;
    private String subTitle;
    private String keywords;


}
