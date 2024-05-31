# AI智能答题平台
AI智能答题平台是一款基于 Vue 3 + Spring Boot + Redis + ChatGLM + RxJava + SSE 的 AI 答题应用平台。

用户可以基于 AI 快速制作并发布答题应用，支持检索、分享、在线答题并基于 AI 得到回答总结；管理员可以集中管理和审核应用。

在线访问：http://121.40.229.102:7070
## 一、技术栈
+ Java Spring Boot 开发框架（万用后端模板）
+ MyBatis-Plus 及 MyBatis X 自动生成
+ 基于 ChatGLM 大模型实现 AI 能力
+ RxJava 响应式框架+SSE 服务端推送
+ 阿里云 OSS 对象存储
+ Caffeine 本地缓存
+ Redission 分布式锁
+ Shardingsphere 分库分表
+ 策略模式

## 二、各业务模块流程图

![image-20240523202153526](https://gitee.com/alanysc/image/raw/master/image-20240523202153526-17166243340623.png) 

![image-20240523202237771](https://gitee.com/alanysc/image/raw/master/image-20240523202237771-17166243340625.png) 

![image-20240523202326196](https://gitee.com/alanysc/image/raw/master/image-20240523202326196-17166243340627.png) 

![image-20240523202358999](https://gitee.com/alanysc/image/raw/master/image-20240523202358999-17166243340629.png)
