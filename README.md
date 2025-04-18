# Spring Boot Demo
一个简单的 Spring Boot 后端项目基础模板

## 模板特点

### 框架整合
- Spring Boot
- MyBatis Plus

### 数据库整合
- MySQL
- PostgresSQL
- MariaDB
- Redis
- MongoDB
- Elasticsearch

### 工具库整合
- Lombok
- Hutool 工具库
- Easy Excel

## 框架功能

### 登录与鉴权
参考 Spring Security 的架构设计，提供了 `UserDetailsService`，`UserRoleService`，
`TokenService` 接口及默认实现类，同时实现 Spring MVC 的 `WebMvcConfigurer` 接口 `
WebConfig` 进行登录与鉴权