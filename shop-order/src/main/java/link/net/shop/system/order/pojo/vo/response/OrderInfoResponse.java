package link.net.shop.system.order.pojo.vo.response;

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
public class OrderInfoResponse {
    private String userName;
    private String tel;
    private String title;
    private String orderNum;
    private String orderId;
}
