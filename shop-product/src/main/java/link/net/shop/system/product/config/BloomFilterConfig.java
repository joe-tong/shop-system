package link.net.shop.system.product.config;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import link.net.shop.system.common.util.BloomFilterHelper;
import link.net.shop.system.common.util.LocalBloomFilter;
import link.net.shop.system.product.component.BloomRedisService;
import link.net.shop.system.product.model.Product;
import link.net.shop.system.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：杨过
 * @date ：Created in 2020/2/19
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Slf4j
@Configuration
public class BloomFilterConfig implements InitializingBean {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public BloomFilterHelper<String> initBloomFilterHelper() {
        return new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8)
                .putString(from, Charsets.UTF_8), 1000000, 0.01);
    }

    /**
     * 布隆过滤器bean注入
     *
     * @return
     */
    @Bean
    public BloomRedisService bloomRedisService() {
        BloomRedisService bloomRedisService = new BloomRedisService();
        bloomRedisService.setBloomFilterHelper(initBloomFilterHelper());
        bloomRedisService.setRedisTemplate(redisTemplate);
        return bloomRedisService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Long> list = productRepository.findAll().stream().map(Product::getId).collect(Collectors.toList());
        log.info("加载产品到布隆过滤器当中,size:{}", list.size());
        if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(item -> {
                LocalBloomFilter.put(item);
//                bloomRedisService().addByBloomFilter(RedisKeyPrefixConst.PRODUCT_REDIS_BLOOM_FILTER, item + "");
            });
        }
    }
}
