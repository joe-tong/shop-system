//package link.net.shop.system.product.repository;
//
//import link.net.shop.system.product.model.es.EsProduct;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.jpa.repository.Query;
//
///**
// * 商品ES操作类
// */
//public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
//    /**
//     * 搜索查询
//     *
//     * @param name              商品名称
//     * @param subTitle          商品标题
//     * @param keywords          商品关键字
//     * @param page              分页信息
//     * @return
//     */
//    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
//
//    Page<EsProduct> findEsProductByName(String name, Pageable page);
//
//}
