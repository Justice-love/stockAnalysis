# stockAnalysis
页面抓取聚合，分析，展示

## 构建方式
* 修改web/application-pro.yml中的数据库连接
* 在根项目下执行```mvn clean package```
* 执行 ```java -Dspring.profiles.active=pro -jar web/target/web-1.0-SNAPSHOT.jar```

## 数据爬取
* 支持两种爬取协议：HttpClient，Jsoup
* 爬取URL可自定义，详见```xml/urls/second.xml```
* 可自定义数据解析算法，详见```org.eddy.entity.SelectType```
* 爬取的结果均为字符串类型，可注册自定义的类型转换器。