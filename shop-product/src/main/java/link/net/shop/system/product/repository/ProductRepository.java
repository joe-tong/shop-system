package link.net.shop.system.product.repository;

import link.net.shop.system.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/28 16:14
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select new  link.net.shop.system.product.model.es.EsProduct(p.id, p.name, p.subTitle,p.keywords) from Product p ")
//    List<EsProduct> getAll();
//
//    @Query("select new  link.net.shop.system.product.model.es.EsProduct(p.id, p.name, p.subTitle,p.keywords) from Product p where p.id = ?1")
//    EsProduct getById(Long id);


}
