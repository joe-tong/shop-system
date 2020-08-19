package link.net.shop.system.order.controller;

import io.swagger.annotations.Api;
import link.net.shop.system.common.response.ServerResponse;
import link.net.shop.system.order.pojo.vo.request.QueryOrderRequest;
import link.net.shop.system.order.pojo.vo.response.OrderInfoResponse;
import link.net.shop.system.user.api.UserFeignApi;
import link.net.shop.system.user.response.UserDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/23 18:43
 */

@Api(tags = "订单")
@RestController
public class OrderController {

    @Autowired
    private UserFeignApi userFeignApi;

    @GetMapping("query/detail")
    public ServerResponse<OrderInfoResponse> queryOrder(QueryOrderRequest request) {

        //remote user server to query user detail
        ServerResponse<UserDetailResponse> userResponse = userFeignApi.queryUserDetail(request.getUserId());
        String userName = null;
        String userTel = null;
        if (userResponse.isSuccess()) {
            userTel = userResponse.getData().getTel();
            userName = userResponse.getData().getUserName();
        }

        OrderInfoResponse response = OrderInfoResponse.builder()
                .userName(userName)
                .tel(userTel)
                .orderId(request.getOrderId())
                .build();

        return ServerResponse.success(response);
    }
}
