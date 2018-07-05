-- 建立数据库
CREATE DATABASE seckill;
USE seckill;

-- 创建秒杀库存表 
CREATE TABLE seckill(
 `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '库存ID',
 `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
 `number` INT NOT NULL  COMMENT '库存数量',
 `start_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀开启时间',
 `end_time` TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
 `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 PRIMARY KEY(seckill_id),
 KEY idx_start_time(start_time),
 KEY idx_end_time(end_time),
 KEY idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存'

-- 数据
INSERT INTO seckill(NAME,number,start_time,end_time)
VALUES('1000秒杀iphone8',100,'2018-6-6 00:00:00','2018-6-7 00:00:00'),
('500元秒杀ipad2',200,'2018-6-6 00:00:00','2018-6-7 00:00:00'),
('300元秒杀小米电视',300,'2018-6-6 00:00:00','2018-6-7 00:00:00'),
('100元秒杀小米手环',400,'2018-6-6 00:00:00','2018-6-7 00:00:00');

-- 秒杀成功明细表
CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
  `user_phone` VARCHAR(11) NOT NULL COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT 0 COMMENT '状态标志：-1无效 0成功 1已付款 2已发货',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY(seckill_id,user_phone), /* 联合主键 */
  KEY idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表'
