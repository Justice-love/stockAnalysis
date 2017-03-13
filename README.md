# stockAnalysis
页面抓取聚合，分析，展示

## 构建方式
* 修改web/application-pro.yml中的数据库连接
* 在根项目下执行```mvn clean package```
* 执行 ```java -Dspring.profiles.active=pro -jar web/target/web-1.0-SNAPSHOT.jar```
