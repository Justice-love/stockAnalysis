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

## 数据分析
* 项目中完成单个数据分析的节点我称他为 **swing**，分析结果返回true/false
* 整个分析引擎采用二叉树结构设计，即swing可以包含子节点，对应分析结果为true之后的调用；也可以包含一个else节点，对应分析结果为false之后的调用。
* 最终的叶子节点可以配置一个executor,当解析结果为true时会被调用。
* 关于swing的算子相关可参考```org.eddy.swing.entity.Validater```
* 关于swing的上下文信息配置可参考```swing/xml/swing.xml```

## 页面展示

