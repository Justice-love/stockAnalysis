
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
  KEY `idx_date_stock_code_time` (`date`,`stock_code`,`time`) COMMENT '日期+code+时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED COMMENT='股票实时价格信息';

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


CREATE INDEX `idx_stock_code`  ON `stock`.`stock_source_data` (stock_code)

CREATE TABLE `stock_want_buy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '股票名称',
  `stock_code` varchar(45) DEFAULT NULL COMMENT '股票代码',
  `current_price` varchar(45) DEFAULT NULL,
  `current_up` varchar(45) DEFAULT NULL,
  `current_stock_date` varchar(45) DEFAULT NULL,
  `current_stock_time` varchar(45) DEFAULT NULL,
  `validaters` varchar(100) DEFAULT NULL COMMENT '通过的校验器, ｜分隔',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='希望买入的股票';

CREATE TABLE `stock`.`stock_had_buy` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT '股票名称',
  `stock_code` VARCHAR(45) NULL COMMENT '股票代码',
  `buy_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '买入时间',
  `buy_price` VARCHAR(45) NULL COMMENT '买入价格',
  `removed` TINYINT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`))
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '已买股票';

CREATE TABLE `stock_rule_swing` (
  `pri_id` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) DEFAULT NULL,
  `root` tinyint(4) DEFAULT '0',
  `expression` varchar(45) DEFAULT NULL,
  `validate_type` varchar(45) DEFAULT NULL,
  `expect` varchar(45) DEFAULT NULL,
  `auto_trigger` tinyint(4) DEFAULT NULL,
  `or_else` varchar(45) DEFAULT NULL,
  `executor` varchar(45) DEFAULT NULL,
  `child_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pri_id`),
  UNIQUE KEY `idx_swing_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则模块';



