package link.net.shop.system.product.model.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 搜索中的商品属性信息
 */
@Setter
@Getter
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Long productAttributeId;
    //属性值
    @Field(type = FieldType.Keyword)
    private String value;
    //属性参数：0->规格；1->参数
    private Integer type;
    //属性名称
    @Field(type= FieldType.Keyword)
    private String name;



}
