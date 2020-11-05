# Shop-system电商系统技术栈-仅供学习Demo
## 目录结构

```
shop-system  父工程模块:管理依赖
  --shop-common 公共模块：提供公共数据和功能
  --shop-user 用户模块  (双数据源)：2001
  --shop-user-api 用户对外提供feign api
  --shop-order 订单模块：3002
  --shop-product 产品模块 （es集群、秒杀、缓存击穿、sentinel、seata分布式事务）：4001
  --shop-mq 消息通知（kafka,rocketmq,rabbitmq）：5001
  --shop-gateway 网关(oauth 认证、鉴权)：1001
  --shop-oauth2-server: 认证服务：6001
  --shop-oauth2-client: 资源服务（内测）：6101
  --shop-net: 网络服务（netty）：7001
  --shop-seckill: 秒杀服务（）：8001
```

## 涉及技术

### 1.spring-cloud:

- nacos注册中心、配置中心
- gateway 微服务网关
- feign  微服务http调用
- sleuth+zipkin 微服务追踪链
- sentinel 限流熔断器

#### 2.jpa双数据切换

#### 3.缓存

- ehcache 
- redis
- mongodb

#### 4.elasticsearch 

spring-boot-starter-data 整合 elasticsearch 集群使用使用

#### 5.消息中间件
-kafka 、rabbitmq、rocketmq

问题：

- 如何保证消息重复消费

- 如何保证消息不丢失

- 消息积压问题

  **代码里面有解决方案**

#### 6.分布式session
spring-session

#### 7.分布式锁
redission
#
### 8.布隆过滤器-缓存击穿
bloomFilter 过滤不存在的商品：
1.LocalBloomFilter
2.Redis的bitMap

### 8.sentinel
1.熔断
2.限流

### 9.seata分布式事务

#### 玩的过程中发现的问题

```
1.gateway整合swagger,如果gateway使用了前缀，会替换掉微服务其他前缀路径，这个好坑。暂时不想解决
```

## 后续会把服务搭建文档公布出来.....敬请期待


