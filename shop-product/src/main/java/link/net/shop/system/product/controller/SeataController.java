package link.net.shop.system.product.controller;

import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import link.net.shop.system.common.response.ServerResponse;
import link.net.shop.system.product.model.Product;
import link.net.shop.system.product.pojo.vo.request.QueryProductRequest;
import link.net.shop.system.product.pojo.vo.response.ProductInfoResponse;
import link.net.shop.system.product.repository.ProductRepository;
import link.net.shop.system.user.api.UserFeignApi;
import link.net.shop.system.user.request.AdminCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : joe
 * @desc :
 * @date : 2020年10月30日 5:16 下午
 **/
@Api(tags = "seata")
@RestController
@RequestMapping("seata")
public class SeataController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserFeignApi userFeignApi;

    @GetMapping("test")
    @GlobalTransactional(name = "fsp_tx_group", rollbackFor = Exception.class)
    public ServerResponse<Void> queryOrder() {
        Product product = new Product();
        product.setBrandName("apple");
        product.setName("iphone 12");
        productRepository.save(product);

        userFeignApi.createAdmin(new AdminCreateRequest());

        return ServerResponse.success();
    }
}
