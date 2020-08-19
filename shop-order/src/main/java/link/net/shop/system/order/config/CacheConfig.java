package link.net.shop.system.order.config;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

/**
 * @Author: Joe
 * @Description:
 * @Date 2020/7/28 10:11
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * ehcache 主要的管理器
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
        System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY, "true");
        return new EhCacheCacheManager(bean.getObject());
    }

    /**
     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache-core.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    private CacheManager getCacheManager() {
        return ehCacheCacheManager(ehCacheManagerFactoryBean()).getCacheManager();
    }

    @Bean(name = "cacheService")
    public Cache callBackCache() {
        CacheManager cacheManager = getCacheManager();
        Assert.state(cacheManager != null, "No CacheManager set");

        // Check the EhCache cache again (in case the cache was added at runtime)
        Ehcache ehcache = cacheManager.getEhcache("cacheService");
        return new EhCacheCache(ehcache);
    }

}
