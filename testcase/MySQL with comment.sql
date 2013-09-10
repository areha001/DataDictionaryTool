SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for gauge
-- ----------------------------
CREATE TABLE `gauge` (
  `id` int(6) unsigned NOT NULL auto_increment,
  `visual_density` double(10,3) default NULL COMMENT '你好啊',
  `visual_temperature` double(10,3) default NULL COMMENT '注释也\n引用XX表SYS_ID',
  `stand_density` double(10,3) default NULL COMMENT '智能五笔',
  `cal_density` double(10,3) default NULL COMMENT '无聊ING',
  `oil_temperature` double(10,3) default NULL COMMENT '无聊ING',
  `vcf` double(10,3) default NULL,
  `oil_height` double(10,3) default NULL COMMENT '无聊ING',
  `water_height` double(10,3) default NULL COMMENT '无聊ING',
  `vt` double(10,3) default NULL,
  `v20` double(10,3) default NULL,
  `mass` double(10,3) default '0.000' COMMENT '无聊ING',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;