package link.net.shop.system.security.config.inmermory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * @Author: Joe
 * @Description: 授权服务配置  token、client都存储到内存（内测版，不推荐使用）
 * @Date 2020/8/19 15:05
 */
//@Configuration
//@EnableAuthorizationServer
public class OAuth2MemoryConfig extends AuthorizationServerConfigurerAdapter {

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
    private TokenStore redisTokenStore;

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
                .tokenStore(redisTokenStore);

    }



    /**
     * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】服务
     * 设置 认证信息的来源：数据库 (可选项：数据库和内存,使用内存一般用来作测试)
     * 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     * 1.inMemory 方式存储的，将配置保存到内存中，相当于硬编码了。正式环境下的做法是持久化到数据库中，比如 mysql 中。
     * 2. secret加密是client_id:secret 然后通过base64编码后的字符串
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //添加客户端信息
        //使用内存存储OAuth客服端信息
        clients.inMemory()
                // client_id 客户单ID
                .withClient("order-client")
                // client_secret 客户单秘钥
                .secret(passwordEncoder.encode("order-secret-8888"))
                // 该客户端允许的授权类型，不同的类型，则获取token的方式不一样
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                // token 有效期
                .accessTokenValiditySeconds(10)
                // refresh 过期时间
                .refreshTokenValiditySeconds(200)
                // 允许的授权范围
                .scopes("all")
                .and()
                .withClient("user-client")
                .secret(passwordEncoder.encode("user-secret-8888"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(10)
                //对应资源服务的ID
                .resourceIds("client-resourceId")
                // refresh 过期时间
                .refreshTokenValiditySeconds(200)
                .redirectUris("http://www.baidu.com")
                .scopes("all");
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
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }


}