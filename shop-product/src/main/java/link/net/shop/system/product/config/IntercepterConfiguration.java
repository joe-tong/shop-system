package link.net.shop.system.product.config;

import link.net.shop.system.product.interceptor.BloomFilterInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 **/
@Configuration
public class IntercepterConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(authInterceptorHandler())
                .addPathPatterns("/product/**");
    }

    @Bean
    public BloomFilterInterceptor authInterceptorHandler(){
        return new BloomFilterInterceptor();
    }
}
