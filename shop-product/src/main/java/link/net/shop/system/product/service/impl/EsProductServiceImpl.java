//package link.net.shop.system.product.service.impl;
//
//import link.net.shop.system.product.model.es.EsProduct;
//import link.net.shop.system.product.repository.EsProductRepository;
//import link.net.shop.system.product.repository.ProductRepository;
//import link.net.shop.system.product.service.EsProductService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//
///**
// * 商品搜索管理Service实现类
// */
////@Service
//public class EsProductServiceImpl implements EsProductService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private EsProductRepository esProductRepository;
//
//    @Override
//    public int importAll() {
//        List<EsProduct> esProducts = productRepository.getAll();
//        Iterable<EsProduct> esProductIterable = esProductRepository.saveAll(esProducts);
//        Iterator<EsProduct> iterator = esProductIterable.iterator();
//        int result = 0;
//        while (iterator.hasNext()) {
//            result++;
//            iterator.next();
//        }
//        return result;
//    }
//
//    @Override
//    public void delete(Long id) {
//        esProductRepository.deleteById(id);
//    }
//
//    @Override
//    public EsProduct create(Long id) {
//        EsProduct esProduct = productRepository.getById(id);
//        esProductRepository.save(esProduct);
//        return esProduct;
//    }
//
//    @Override
//    public void delete(List<Long> ids) {
//        if (!CollectionUtils.isEmpty(ids)) {
//            List<EsProduct> esProductList = new ArrayList<>();
//            for (Long id : ids) {
//                EsProduct esProduct = new EsProduct();
//                esProduct.setId(id);
//                esProductList.add(esProduct);
//            }
//            esProductRepository.deleteAll(esProductList);
//        }
//    }
//
//    @Override
//    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        return esProductRepository.findEsProductByName(keyword,  pageable);
//    }
//
//}
