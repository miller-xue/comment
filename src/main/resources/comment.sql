/* 广告表 */
CREATE TABLE `ad`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `img_file_name` varchar(100) DEFAULT NULL COMMENT '图片文件名',
  `link` varchar(200) DEFAULT NULL COMMENT '连接地址',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `business`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_file_name` varchar(100) DEFAULT NULL COMMENT '图片文件名',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(100) DEFAULT NULL COMMENT '副标题',
  `price` decimal(11,2) DEFAULT NULL COMMENT '价格(单位：元)',
  `distance` int(11) DEFAULT NULL COMMENT '距离(单位：米)',
  `number` int(11) DEFAULT NULL COMMENT '已售数量',
  `desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `city` varchar(16) DEFAULT NULL COMMENT '城市',
  `category` varchar(16) DEFAULT NULL COMMENT '类别',
  `star_total_num` int(1) DEFAULT NULL COMMENT '星级',
  `comment_total_num` int(11) DEFAULT NULL COMMENT '评论总次数',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic` (
  `type` varchar(16) NOT NULL,
  `code` varchar(16) NOT NULL,
  `name` varchar(16) NOT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`type`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 用户表
CREATE TABLE `member` (
  `id` int (11) NOT NULL,
  `phone` bigint(13) DEFAULT NULL COMMENT '手机号',
  `name` varchar(16) DEFAULT NULL COMMENT '用户名',
  `password` char(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_unique`(`phone`),
  UNIQUE KEY `name_unique`(`name`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8


-- 订单表
CREATE TABLE  `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `business_id` int(11) DEFAULT NULL  COMMENT '商户主键',
  `member_id` int(11) DEFAULT NULL  COMMENT '会员主键',
  `num` int(11) DEFAULT NULL  COMMENT '消费人数',
  `comment_state` int(1) DEFAULT NULL COMMENT '评论状态, -- 0 未评论, 2：已评论',
  `price` decimal(11,2) DEFAULT NULL COMMENT '价格（消费金额）',
  PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8