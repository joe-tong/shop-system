package link.net.shop.system.order.pojo.vo.request;

import lombok.Data;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/23 19:02
 */
@Data
public class QueryOrderRequest {
    private String orderId;
    private String userId;
}
