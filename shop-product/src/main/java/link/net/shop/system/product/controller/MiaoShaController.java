package link.net.shop.system.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import link.net.shop.system.common.response.ServerResponse;
import link.net.shop.system.product.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : joe
 * @desc :
 * @date : 2020年10月29日 3:12 下午
 **/
@Api(tags = "产品搜索")
@RestController
public class MiaoShaController {

    @ApiOperation("下单")
    @PostMapping("ms")
    public ServerResponse miaosha() {


        return ServerResponse.success();
    }
}
