package link.net.shop.system.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import link.net.shop.system.common.response.ServerResponse;
import link.net.shop.system.product.model.Product;
import link.net.shop.system.product.model.es.EsProduct;
import link.net.shop.system.product.pojo.vo.request.QueryProductRequest;
import link.net.shop.system.product.pojo.vo.response.ProductInfoResponse;
import link.net.shop.system.product.repository.ProductRepository;
import link.net.shop.system.product.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/23 18:43
 */

@Api(tags = "产品搜索")
@RestController
public class ProductController {

    @Autowired
    private EsProductService esProductService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("query/detail")
    public ServerResponse<ProductInfoResponse> queryOrder(QueryProductRequest request) {

        ProductInfoResponse response = ProductInfoResponse.builder()

                .build();

        return ServerResponse.success(response);
    }

    @ApiOperation("根据关键字搜索")
    @GetMapping("query/keyword")
    public ServerResponse<List<EsProduct>> queryBykeywords(String keyword, int pageNum, int pageSize) {
        Page<EsProduct> esps = esProductService.search(keyword, 1, 10);
        return ServerResponse.success(esps.getContent());
    }

    @ApiOperation("新增产品")
    @PostMapping("create")
    public ServerResponse createProduct(@RequestBody Product product) {

        productRepository.save(product);
        esProductService.create(product.getId());

        return ServerResponse.success();
    }

    @ApiOperation("修改")
    @PostMapping("update")
    public ServerResponse updateProduct(Long id, String keywords) {

        Product product = productRepository.getOne(id);
        product.setKeywords(keywords);
        productRepository.save(product);

        //删除
        esProductService.delete(id);
        //新增
        esProductService.create(id);
        return ServerResponse.success();
    }


    @ApiOperation("删除")
    @PostMapping("delete")
    public ServerResponse delete(Long id) {
        //删除
        esProductService.delete(id);
        return ServerResponse.success();
    }
}
