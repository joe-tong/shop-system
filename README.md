# Shop-system电商系统整合技术栈
## 目录结构

```
shop-system  父工程模块:管理依赖
  --shop-common 公共模块：提供公共数据和功能
  --shop-user 用户模块  (双数据源)：2001
  --shop-user-api 用户对外提供feign api
  --shop-order 订单模块：3002
  --shop-product 产品模块 （es集群）：4001
  --shop-mq 消息通知（kafka,rocketmq,rabbitmq）：5001
  --shop-gateway 网关(oauth 认证、鉴权)：1001
  --shop-oauth2-server: 认证服务：6001
  --shop-oauth2-client: 资源服务（内测）：6101
```

## 涉及技术

### 1.spring-cloud:

- nacos注册中心，未使用配置中心
-  gateway 微服务网关
- feign  微服务http调用
- sleuth+zipkin 微服务追踪链

#### 2.jpa双数据切换

#### 3.缓存

- ehcache 
- redis
- mongdb

#### 4.elasticsearch 

spring-boot-starter-data 整合 elasticsearch 集群使用使用

#### 5.kafka

问题：

- 如何保证消息重复消费

- 如何保证消息不丢失

- 消息积压问题

  **代码里面有解决方案**



#### 玩的过程中发现的问题

```
1.gateway整合swagger,如果gateway使用了前缀，会替换掉微服务其他前缀路径，这个好坑。暂时不想解决
```

## 后续会把服务搭建文档公布出来.....敬请期待


