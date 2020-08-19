package link.net.shop.system.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/30 16:41
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "link.net.shop.system.product.repository")
public interface ESConfig {
}

