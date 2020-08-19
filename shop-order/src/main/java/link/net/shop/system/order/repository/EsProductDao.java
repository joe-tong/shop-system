package link.net.shop.system.order.repository;

import link.net.shop.system.order.model.es.EsProduct;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 * Created by macro on 2018/6/19.
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
