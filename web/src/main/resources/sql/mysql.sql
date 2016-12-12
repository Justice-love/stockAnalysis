
set global time_zone='+8:00'

CREATE TABLE `stock_source_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '' COMMENT '股票名称',
  `stock_code` varchar(45) NOT NULL COMMENT '股票代码',
  `price` varchar(45) DEFAULT '' COMMENT '价格',
  `up` varchar(45) DEFAULT '' COMMENT '涨幅(百分比)',
  `up_price` varchar(45) DEFAULT '' COMMENT '涨幅金额',
  `today_begin` varchar(45) DEFAULT NULL COMMENT '今日开盘价格',
  `yesterday_end` varchar(45) DEFAULT NULL COMMENT '昨日收盘价格',
  `date` varchar(45) DEFAULT NULL COMMENT '日期',
  `time` varchar(45) DEFAULT NULL COMMENT '时间',
  `buy1` varchar(45) DEFAULT NULL COMMENT '买1',
  `buy1_price` varchar(45) DEFAULT NULL COMMENT '买1价格',
  `buy2` varchar(45) DEFAULT NULL COMMENT '买2',
  `buy2_price` varchar(45) DEFAULT NULL COMMENT '买2价格',
  `buy3` varchar(45) DEFAULT NULL COMMENT '买3',
  `buy3_price` varchar(45) DEFAULT NULL COMMENT '买3价格',
  `sale1` varchar(45) DEFAULT NULL COMMENT '卖1',
  `sale1_price` varchar(45) DEFAULT NULL COMMENT '卖1价格',
  `sale2` varchar(45) DEFAULT NULL COMMENT '卖2',
  `sale2_price` varchar(45) DEFAULT NULL COMMENT '卖2价格',
  `sale3` varchar(45) DEFAULT NULL COMMENT '卖3',
  `sale3_price` varchar(45) DEFAULT NULL COMMENT '卖3价格',
  `to_buy_price` varchar(45) DEFAULT NULL COMMENT '竞买价格',
  `to_sale_price` varchar(45) DEFAULT NULL COMMENT '竞卖价格',
  `deal_stock` varchar(45) DEFAULT NULL COMMENT '成交股票数',
  `deal_money` varchar(45) DEFAULT NULL COMMENT '成交金额',
  PRIMARY KEY (`id`),
  KEY `idx_date_time_stock_code` (`date`,`time`,`stock_code`) COMMENT '日期时间股票编号'
) ENGINE=InnoDB AUTO_INCREMENT=1162 DEFAULT CHARSET=utf8 COMMENT='股票实时价格信息';


CREATE TABLE `stock_source_error` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(45) DEFAULT NULL COMMENT '股票代码',
  `error_content` varchar(100) DEFAULT NULL COMMENT '抓取内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票抓取失败数据存储';

CREATE TABLE `stock_daily_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '' COMMENT '股票名称',
  `stock_code` varchar(45) NOT NULL COMMENT '股票代码',
  `price` varchar(45) DEFAULT '' COMMENT '价格',
  `up` varchar(45) DEFAULT '' COMMENT '涨幅(百分比)',
  `up_price` varchar(45) DEFAULT '' COMMENT '涨幅金额',
  `today_begin` varchar(45) DEFAULT NULL COMMENT '今日开盘价格',
  `yesterday_end` varchar(45) DEFAULT NULL COMMENT '昨日收盘价格',
  `date` varchar(45) DEFAULT NULL COMMENT '日期',
  `buy1` varchar(45) DEFAULT NULL COMMENT '买1',
  `buy1_price` varchar(45) DEFAULT NULL COMMENT '买1价格',
  `buy2` varchar(45) DEFAULT NULL COMMENT '买2',
  `buy2_price` varchar(45) DEFAULT NULL COMMENT '买2价格',
  `buy3` varchar(45) DEFAULT NULL COMMENT '买3',
  `buy3_price` varchar(45) DEFAULT NULL COMMENT '买3价格',
  `sale1` varchar(45) DEFAULT NULL COMMENT '卖1',
  `sale1_price` varchar(45) DEFAULT NULL COMMENT '卖1价格',
  `sale2` varchar(45) DEFAULT NULL COMMENT '卖2',
  `sale2_price` varchar(45) DEFAULT NULL COMMENT '卖2价格',
  `sale3` varchar(45) DEFAULT NULL COMMENT '卖3',
  `sale3_price` varchar(45) DEFAULT NULL COMMENT '卖3价格',
  `to_buy_price` varchar(45) DEFAULT NULL COMMENT '竞买价格',
  `to_sale_price` varchar(45) DEFAULT NULL COMMENT '竞卖价格',
  `deal_stock` varchar(45) DEFAULT NULL COMMENT '成交股票数',
  `deal_money` varchar(45) DEFAULT NULL COMMENT '成交金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票每日价格汇总信息';

