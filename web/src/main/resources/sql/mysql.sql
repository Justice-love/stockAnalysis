CREATE TABLE `stock`.`stock_source_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '股票名称',
  `code` VARCHAR(45) NOT NULL COMMENT '股票code',
  `price` VARCHAR(45) NULL DEFAULT '' COMMENT '价格',
  `up` VARCHAR(45) NULL DEFAULT '' COMMENT '涨幅(百分比)',
  `upPrice` VARCHAR(45) NULL DEFAULT '' COMMENT '涨幅金额',
  `bye` VARCHAR(45) NULL COMMENT '大户买入股票数',
  `sale` VARCHAR(45) NULL COMMENT '大户卖出股票数',
  `today_begin` VARCHAR(45) NULL COMMENT '今日开盘价格',
  `yesterday_end` VARCHAR(45) NULL COMMENT '昨日收盘价格',
  `date` VARCHAR(45) NULL COMMENT '日期',
  `time` VARCHAR(45) NULL COMMENT '时间',
  PRIMARY KEY (`id`))
COMMENT = '股票实时价格信息';

CREATE TABLE `stock`.`stock_source_error` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sotck_code` VARCHAR(45) NULL COMMENT '股票代码',
  `error_content` VARCHAR(45) NULL COMMENT '抓取内容',
  PRIMARY KEY (`id`))
COMMENT = '股票抓取失败数据存储';

ALTER TABLE `stock`.`stock_source_data`
CHANGE COLUMN `code` `stock_code` VARCHAR(45) NOT NULL COMMENT '股票代码' ;

CREATE INDEX `idx_stock_code_date_time`  ON `stock`.`stock_source_data` (stock_code, date, time)
