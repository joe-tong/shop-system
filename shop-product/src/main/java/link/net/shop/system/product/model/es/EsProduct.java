package link.net.shop.system.product.model.es;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/27 20:30
 */
@Document(indexName = "pms", type = "product",shards = 1,replicas = 0)
@Setter
@Getter
@NoArgsConstructor
public class EsProduct implements Serializable {
    @Id
    private Long id;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;

    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;

    public EsProduct(Long id, String name, String subTitle, String keywords) {
        this.id = id;
        this.name = name;
        this.subTitle = subTitle;
        this.keywords = keywords;
    }
}
