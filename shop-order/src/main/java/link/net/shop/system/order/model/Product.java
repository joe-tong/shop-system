package link.net.shop.system.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.Table;
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
public class Product  {
    @Id
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String img;
    private String brandName;
    private String subTitle;
    private String keywords;
}
