package link.net.shop.system.security.config.indb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * @Author: Joe
 * @Description: 授权服务配置 token、client配置都放到数据库
 * @Date 2020/8/19 15:05   生产的配置文件
 */
@Configuration
@EnableAuthorizationServer
public class OAuthDB2Config extends AuthorizationServerConfigurerAdapter {

    /**
     * 指定密码的加密方式
     */
    @Autowired
    public PasswordEncoder passwordEncoder;
    /**
     * 该对象为刷新token提供支持
     */
    @Autowired
    public UserDetailsService kiteUserDetailsService;
    /**
     * 该对象用来支持password模式
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 该对象用来讲令牌信息存储到内存中
     */
    @Autowired
    private TokenStore jdbcTokenStore;

    @Autowired
    private DataSource dataSource;

    /**
     * 密码模式下配置认证管理器 AuthenticationManager,并且设置 AccessToken的存储介质tokenStore,如果不设置，则会默认使用内存当做存储介质。
     * 而该AuthenticationManager将会注入 2个Bean对象用以检查(认证)
     * 1、ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     * 2、UserDetailsService的实现类 KiteUserDetailsService (检查 UserDetails 对象)
     */
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(kiteUserDetailsService)
                //生产还是使用redis或者jwt
                .tokenStore(jdbcTokenStore);

    }

    /**
     * jdbc配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsServiceBuilder jcsb = clients.jdbc(dataSource);
        jcsb.passwordEncoder(passwordEncoder);
    }


    /**
     * 配置：安全检查流程
     * 默认过滤器：BasicAuthenticationFilter
     * 1、oauth_client_details表中clientSecret字段加密【ClientDetails属性secret】
     * 2、CheckEndpoint类的接口 oauth/check_token 无需经过过滤器过滤，默认值：denyAll()
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        ///允许客户表单认证
        security.allowFormAuthenticationForClients();
        //对于CheckEndpoint控制器[框架自带的校验]的/oauth/check端点允许所有客户端发送器请求而不会被Spring-security拦截
        security.checkTokenAccess("permitAll()");
        security.tokenKeyAccess("isAuthenticated()");
    }


}