
set global time_zone='+8:00'

CREATE TABLE `stock_source_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '' COMMENT '股票名称',
  `stock_code` varchar(45) NOT NULL COMMENT '股票代码',
  `price` varchar(45) DEFAULT '' COMMENT '价格',
  `up` varchar(45) DEFAULT '' COMMENT '涨幅(百分比)',
  `up_price` varchar(45) DEFAULT '' COMMENT '涨幅金额',
  `buy` varchar(45) DEFAULT NULL COMMENT '大户买入股票数',
  `sale` varchar(45) DEFAULT NULL COMMENT '大户卖出股票数',
  `today_begin` varchar(45) DEFAULT NULL COMMENT '今日开盘价格',
  `yesterday_end` varchar(45) DEFAULT NULL COMMENT '昨日收盘价格',
  `date` varchar(45) DEFAULT NULL COMMENT '日期',
  `time` varchar(45) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`),
  KEY `idx_stock_code_date_time` (`stock_code`,`date`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票实时价格信息';

CREATE TABLE `stock_source_error` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(45) DEFAULT NULL COMMENT '股票代码',
  `error_content` varchar(100) DEFAULT NULL COMMENT '抓取内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票抓取失败数据存储';
