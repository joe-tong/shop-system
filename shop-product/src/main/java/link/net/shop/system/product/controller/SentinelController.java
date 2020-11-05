package link.net.shop.system.product.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : joe
 * @desc :
 * @date : 2020年10月30日 11:57 上午
 **/
@Api(tags = "sentinel")
@RestController()
@RequestMapping("sentinel")
public class SentinelController {

    @GetMapping("test")
    public String testHot() {
        return "testHot";
    }
}
