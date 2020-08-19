package link.net.shop.system.product.pojo.vo.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/23 19:07
 */
@Data
@Builder
public class ProductInfoResponse {
    private String name;
    private String subTitle;
    private String img;
    private String keywords;
}
