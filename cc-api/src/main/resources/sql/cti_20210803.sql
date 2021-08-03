

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cc_admin_account
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_account`;
CREATE TABLE `cc_admin_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `passwd` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `user_type` int(11) NOT NULL DEFAULT '1' COMMENT '类型',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='企业管理员表';

-- ----------------------------
-- Records of cc_admin_account
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_account` VALUES (1, 1, 1, 1, 'admin', 'q11111111', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_account_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_account_role`;
CREATE TABLE `cc_admin_account_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `account_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '账号ID',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号角色表';

-- ----------------------------
-- Records of cc_admin_account_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_permission`;
CREATE TABLE `cc_admin_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `permission_name` varchar(255) NOT NULL DEFAULT '' COMMENT '权限名称',
  `permission_url` varchar(255) NOT NULL DEFAULT '' COMMENT '权限URL',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父权限id',
  `permission_order` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
  `is_front` int(11) NOT NULL DEFAULT '1' COMMENT '是否前端权限',
  `is_interface` int(11) NOT NULL DEFAULT '1' COMMENT '是否后端权限',
  `status` int(11) NOT NULL DEFAULT '1',
  `permission_level` int(11) NOT NULL DEFAULT '1' COMMENT '菜单级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of cc_admin_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_permission_role`;
CREATE TABLE `cc_admin_permission_role` (
  `id` bigint(20) NOT NULL COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限ID',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限角色表';

-- ----------------------------
-- Records of cc_admin_permission_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_role`;
CREATE TABLE `cc_admin_role` (
  `id` bigint(20) NOT NULL COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属企业',
  `role_name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名称',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of cc_admin_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_agent
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent`;
CREATE TABLE `cc_agent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `agent_id` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席工号',
  `agent_key` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席账户',
  `agent_name` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席名称',
  `agent_code` varchar(20) NOT NULL DEFAULT '' COMMENT '坐席分机号',
  `agent_type` int(11) NOT NULL DEFAULT '2' COMMENT '座席类型：2:普通座席；1：班长',
  `passwd` varchar(255) NOT NULL DEFAULT '' COMMENT '座席密码',
  `sip_phone` varchar(255) NOT NULL DEFAULT '' COMMENT '绑定的电话号码',
  `record` int(11) NOT NULL DEFAULT '0' COMMENT '是否录音 0 no 1 yes',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '座席主要技能组  不能为空 必填项',
  `after_interval` int(11) NOT NULL DEFAULT '5' COMMENT '话后自动空闲间隔时长',
  `diaplay` varchar(255) NOT NULL DEFAULT '' COMMENT '主叫显号',
  `ring_time` int(11) NOT NULL DEFAULT '10' COMMENT '振铃时长',
  `ext1` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展3',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '状态：1 开通，0关闭',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_ccagent_agent_key` (`agent_key`) USING BTREE,
  KEY `idx_ccagent_company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='座席工号表';

-- ----------------------------
-- Records of cc_agent
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent` VALUES (1, 1604503580, 1604503580, 1, '1001', '1001@test', '测试坐席', '1001', 2, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '188899998889', 0, 1, 5, '', 10, '', '', '', 1);
INSERT INTO `cc_agent` VALUES (2, 1604560158, 1604560158, 1, '1002', '1002@test', '测试2号', '1002', 2, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '188999988887', 0, 1, 5, '', 10, '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_agent_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent_group`;
CREATE TABLE `cc_agent_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `agent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '坐席id',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能组id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_idx_agentgroup_agent_group` (`group_id`,`agent_id`) USING BTREE,
  KEY `idx_agentgroup_company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='坐席技能组表';

-- ----------------------------
-- Records of cc_agent_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_group` VALUES (1, 1604560158, 1604560158, 1, 1, 1, 1);
INSERT INTO `cc_agent_group` VALUES (2, 1604560158, 1604560158, 1, 2, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_agent_sip
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent_sip`;
CREATE TABLE `cc_agent_sip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cts` bigint(20) NOT NULL DEFAULT '0',
  `uts` bigint(20) NOT NULL DEFAULT '0',
  `company_id` bigint(20) DEFAULT '0',
  `sip` varchar(32) NOT NULL DEFAULT '',
  `agent_id` bigint(20) NOT NULL DEFAULT '0',
  `sip_pwd` varchar(255) DEFAULT '',
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_agentsip_agent_id` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='sip表';

-- ----------------------------
-- Records of cc_agent_sip
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_sip` VALUES (1, 1622024375, 1622033134, 1, '871556590425001', 1, '12345678', 1);
INSERT INTO `cc_agent_sip` VALUES (2, 1622072271, 0, 1, '871556519788001', 2, '12345678', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_agent_state_log
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent_state_log`;
CREATE TABLE `cc_agent_state_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '主技能组id',
  `agent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '坐席id',
  `agent_key` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席编号',
  `call_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '通话唯一标识',
  `login_type` int(11) NOT NULL DEFAULT '1' COMMENT '登录类型',
  `work_type` int(11) NOT NULL DEFAULT '1' COMMENT '工作类型',
  `host` varchar(255) NOT NULL DEFAULT '' COMMENT '服务站点',
  `remote_address` varchar(255) NOT NULL DEFAULT '' COMMENT '远端地址',
  `before_state` varchar(50) NOT NULL DEFAULT '' COMMENT '变更之前状态',
  `before_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更变之前时间',
  `state` varchar(50) NOT NULL DEFAULT '' COMMENT '变更之后状态',
  `state_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '当前时间',
  `duration` int(11) NOT NULL DEFAULT '0' COMMENT '持续时间',
  `busy_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '忙碌类型',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `ext1` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `ext3` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_agentstate_agentkey` (`agent_key`),
  KEY `idx_agentstate_cts` (`state_time`) USING BTREE,
  KEY `idx_agentstate_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='坐席状态历史表';

-- ----------------------------
-- Records of cc_agent_state_log
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_state_log` VALUES (1, 1627662290, 1627662290, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'LOGOUT', 0, 'LOGIN', 1627662290187, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (2, 1627662301, 1627662301, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'LOGIN', 1627662290187, 'READY', 1627662301283, 11096, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (3, 1627662301, 1627662301, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'READY', 1627662301283, 'NOT_READY', 1627662301741, 458, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (4, 1627662332, 1627662332, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'NOT_READY', 1627662301741, 'READY', 1627662332866, 31125, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (5, 1627662333, 1627662333, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'READY', 1627662332866, 'NOT_READY', 1627662333407, 541, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (6, 1627662336, 1627662336, 1, 1, 1, '1001@test', 209104236078694400, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'NOT_READY', 1627662333407, 'OUT_CALL', 1627662336980, 3573, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (7, 1627662347, 1627662347, 1, 1, 1, '1001@test', 209104236078694400, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'OUT_CALL', 1627662336980, 'TALKING', 1627662347298, 10318, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (8, 1627662379, 1627662379, 1, 1, 1, '1001@test', 209104236078694400, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'TALKING', 1627662347298, 'AFTER', 1627662379098, 31800, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (9, 1627662790, 1627662790, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59654', 'AFTER', 1627662379098, 'LOGOUT', 1627662790136, 411038, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (10, 1627993276, 1627993276, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53371', 'LOGOUT', 0, 'LOGIN', 1627993276163, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (11, 1627993276, 1627993276, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53371', 'LOGIN', 1627993276163, 'READY', 1627993276773, 610, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (12, 1627993277, 1627993277, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53371', 'READY', 1627993276773, 'NOT_READY', 1627993277313, 540, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (13, 1627993320, 1627993320, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53371', 'NOT_READY', 1627993277313, 'READY', 1627993320282, 42969, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (14, 1627993416, 1627993416, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53371', 'READY', 1627993320282, 'NOT_READY', 1627993416072, 95790, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (15, 1627993417, 1627993417, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53371', 'NOT_READY', 1627993416072, 'READY', 1627993417216, 1144, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (16, 1627993453, 1627993453, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54761', 'LOGOUT', 0, 'LOGIN', 1627993453356, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (17, 1627993454, 1627993454, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54761', 'LOGIN', 1627993453356, 'READY', 1627993454239, 883, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (18, 1627993455, 1627993455, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54761', 'READY', 1627993454239, 'NOT_READY', 1627993455295, 1056, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (19, 1627993462, 1627993462, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54832', 'LOGOUT', 0, 'LOGIN', 1627993462721, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (20, 1627993463, 1627993463, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54832', 'LOGIN', 1627993462721, 'READY', 1627993463408, 687, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (21, 1627993467, 1627993467, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54866', 'LOGOUT', 0, 'LOGIN', 1627993467329, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (22, 1627993477, 1627993477, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54866', 'LOGIN', 1627993467329, 'LOGOUT', 1627993477809, 10480, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (23, 1627993479, 1627993479, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54962', 'LOGOUT', 0, 'LOGIN', 1627993479696, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (24, 1627993481, 1627993481, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54962', 'LOGIN', 1627993479696, 'READY', 1627993481236, 1540, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (25, 1627993579, 1627993579, 1, 1, 2, '1002@test', 210493565480992768, 1, 1, '0.0.0.0:6800', '127.0.0.1:54962', 'READY', 1627993481236, 'IN_CALL_RING', 1627993579562, 98326, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (26, 1627993597, 1627993597, 1, 1, 2, '1002@test', 210493565480992768, 1, 1, '0.0.0.0:6800', '127.0.0.1:54962', 'IN_CALL_RING', 1627993579562, 'AFTER', 1627993597412, 17850, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (27, 1627993600, 1627993600, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55886', 'LOGOUT', 1627993477809, 'LOGIN', 1627993600666, 122857, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (28, 1627993601, 1627993601, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55886', 'LOGIN', 1627993600666, 'READY', 1627993601691, 1025, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (29, 1627993610, 1627993610, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:54962', 'AFTER', 1627993597412, 'LOGOUT', 1627993610894, 13482, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (30, 1627993612, 1627993612, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55978', 'LOGOUT', 1627993610894, 'LOGIN', 1627993612202, 1308, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (31, 1627993620, 1627993620, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55978', 'LOGIN', 1627993612202, 'READY', 1627993620845, 8643, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (32, 1627993627, 1627993627, 1, 1, 2, '1002@test', 210493770993500160, 1, 1, '0.0.0.0:6800', '127.0.0.1:55978', 'READY', 1627993620845, 'IN_CALL_RING', 1627993627783, 6938, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (33, 1627993659, 1627993659, 1, 1, 2, '1002@test', 210493770993500160, 1, 1, '0.0.0.0:6800', '127.0.0.1:55978', 'IN_CALL_RING', 1627993627783, 'AFTER', 1627993659268, 31485, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (34, 1627993665, 1627993665, 1, 1, 1, '1001@test', 210493929433333760, 1, 1, '0.0.0.0:6800', '127.0.0.1:55886', 'READY', 1627993601691, 'IN_CALL_RING', 1627993665199, 63508, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (35, 1627993709, 1627993709, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55886', 'IN_CALL_RING', 1627993665199, 'LOGOUT', 1627993709338, 44139, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (36, 1627993709, 1627993709, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55978', 'AFTER', 1627993659268, 'LOGOUT', 1627993709339, 50071, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (37, 1627993775, 1627993775, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:57229', 'LOGOUT', 0, 'LOGIN', 1627993775731, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (38, 1627993777, 1627993777, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:57229', 'LOGIN', 1627993775731, 'READY', 1627993777039, 1308, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (39, 1627993787, 1627993787, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:57322', 'LOGOUT', 0, 'LOGIN', 1627993787998, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (40, 1627993789, 1627993789, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:57322', 'LOGIN', 1627993787998, 'READY', 1627993789571, 1573, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (41, 1627993863, 1627993863, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:57229', 'READY', 1627993777039, 'LOGOUT', 1627993863006, 85967, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (42, 1627993863, 1627993863, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:57322', 'READY', 1627993789571, 'LOGOUT', 1627993863007, 73436, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (43, 1627993880, 1627993880, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:58033', 'LOGOUT', 0, 'LOGIN', 1627993880830, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (44, 1627993890, 1627993890, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:58109', 'LOGOUT', 0, 'LOGIN', 1627993890721, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (45, 1627993897, 1627993897, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:58109', 'LOGIN', 1627993890721, 'READY', 1627993897545, 6824, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (46, 1627993901, 1627993901, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:58033', 'LOGIN', 1627993880830, 'READY', 1627993901407, 20577, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (47, 1627993969, 1627993969, 1, 1, 2, '1002@test', 210495211925667840, 1, 1, '0.0.0.0:6800', '127.0.0.1:58033', 'READY', 1627993901407, 'IN_CALL_RING', 1627993969899, 68492, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (48, 1627994065, 1627994065, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:58033', 'IN_CALL_RING', 1627993969899, 'LOGOUT', 1627994065620, 95721, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (49, 1627994065, 1627994065, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:58109', 'READY', 1627993897545, 'LOGOUT', 1627994065621, 168076, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (50, 1627994126, 1627994126, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59922', 'LOGOUT', 0, 'LOGIN', 1627994126764, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (51, 1627994127, 1627994127, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59922', 'LOGIN', 1627994126764, 'READY', 1627994127646, 882, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (52, 1627994131, 1627994131, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59957', 'LOGOUT', 0, 'LOGIN', 1627994131006, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (53, 1627994131, 1627994131, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:59957', 'LOGIN', 1627994131006, 'READY', 1627994131808, 802, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (54, 1627994136, 1627994136, 1, 1, 1, '1001@test', 210495909539086336, 1, 1, '0.0.0.0:6800', '127.0.0.1:59922', 'READY', 1627994127646, 'IN_CALL_RING', 1627994136821, 9175, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (55, 1627994167, 1627994167, 1, 1, 1, '1001@test', 210495909539086336, 1, 1, '0.0.0.0:6800', '127.0.0.1:59922', 'IN_CALL_RING', 1627994136821, 'AFTER', 1627994167425, 30604, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (56, 1627994172, 1627994172, 1, 1, 2, '1002@test', 210496056624939008, 1, 1, '0.0.0.0:6800', '127.0.0.1:59957', 'READY', 1627994131808, 'IN_CALL_RING', 1627994172688, 40880, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (57, 1627994410, 1627994410, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:62105', 'LOGOUT', 0, 'LOGIN', 1627994410427, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (58, 1627994410, 1627994410, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:62105', 'LOGIN', 1627994410427, 'READY', 1627994410929, 502, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (59, 1627994434, 1627994434, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:62105', 'READY', 1627994410929, 'LOGOUT', 1627994434460, 23531, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (60, 1627994458, 1627994458, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:62514', 'LOGOUT', 0, 'LOGIN', 1627994458086, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (61, 1627994458, 1627994458, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:62514', 'LOGIN', 1627994458086, 'READY', 1627994458720, 634, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (62, 1627994463, 1627994463, 1, 1, 1, '1001@test', 210497283047817216, 1, 1, '0.0.0.0:6800', '127.0.0.1:62514', 'READY', 1627994458720, 'IN_CALL_RING', 1627994463698, 4978, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (63, 1627994648, 1627994648, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:62514', 'IN_CALL_RING', 1627994463698, 'LOGOUT', 1627994648044, 184346, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (64, 1627994648, 1627994648, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:63972', 'LOGOUT', 1627994648044, 'LOGIN', 1627994648780, 736, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (65, 1627994649, 1627994649, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:63972', 'LOGIN', 1627994648780, 'READY', 1627994649584, 804, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (66, 1627994656, 1627994656, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:63972', 'READY', 1627994649584, 'AFTER', 1627994656045, 6461, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (67, 1627994665, 1627994665, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:63972', 'AFTER', 1627994656045, 'NOT_READY', 1627994665212, 9167, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (68, 1627994666, 1627994666, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:63972', 'NOT_READY', 1627994665212, 'READY', 1627994666242, 1030, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (69, 1627994776, 1627994776, 1, 1, 1, '1001@test', 210498203802402816, 1, 1, '0.0.0.0:6800', '127.0.0.1:63972', 'READY', 1627994666242, 'IN_CALL_RING', 1627994776028, 109786, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (70, 1627994776, 1627994776, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:63972', 'IN_CALL_RING', 1627994776028, 'LOGOUT', 1627994776108, 80, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (71, 1627994809, 1627994809, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:65209', 'LOGOUT', 0, 'LOGIN', 1627994809235, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (72, 1627994809, 1627994809, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:65209', 'LOGIN', 1627994809235, 'READY', 1627994809939, 704, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (73, 1627994817, 1627994817, 1, 1, 1, '1001@test', 210498751016468480, 1, 1, '0.0.0.0:6800', '127.0.0.1:65209', 'READY', 1627994809939, 'IN_CALL_RING', 1627994817306, 7367, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (74, 1627995113, 1627995113, 1, 1, 1, '1001@test', 210498751016468480, 1, 1, '0.0.0.0:6800', '127.0.0.1:65209', 'IN_CALL_RING', 1627994817306, 'AFTER', 1627995113565, 296259, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (75, 1627995191, 1627995191, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:65209', 'AFTER', 1627995113565, 'LOGOUT', 1627995191900, 78335, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (76, 1627995216, 1627995216, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'LOGOUT', 0, 'LOGIN', 1627995216703, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (77, 1627995218, 1627995218, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'LOGIN', 1627995216703, 'READY', 1627995218205, 1502, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (78, 1627995218, 1627995218, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'READY', 1627995218205, 'NOT_READY', 1627995218788, 583, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (79, 1627995220, 1627995220, 1, 1, 1, '1001@test', 210500456688910336, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'NOT_READY', 1627995218788, 'OUT_CALL', 1627995220392, 1604, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (80, 1627995233, 1627995233, 1, 1, 1, '1001@test', 210500456688910336, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'OUT_CALL', 1627995220392, 'TALKING', 1627995233093, 12701, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (81, 1627995345, 1627995345, 1, 1, 1, '1001@test', 210500456688910336, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'TALKING', 1627995233093, 'AFTER', 1627995345249, 112156, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (82, 1627995352, 1627995352, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'AFTER', 1627995345249, 'READY', 1627995352506, 7257, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (83, 1627995358, 1627995358, 1, 1, 1, '1001@test', 210501027873423360, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'READY', 1627995352506, 'IN_CALL_RING', 1627995358036, 5530, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (84, 1627995364, 1627995364, 1, 1, 1, '1001@test', 210501027873423360, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'IN_CALL_RING', 1627995358036, 'TALKING', 1627995364447, 6411, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (85, 1627995382, 1627995382, 1, 1, 1, '1001@test', 210501027873423360, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'TALKING', 1627995364447, 'AFTER', 1627995382299, 17852, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (86, 1627995391, 1627995391, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:51957', 'AFTER', 1627995382299, 'LOGOUT', 1627995391933, 9634, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (87, 1627995393, 1627995393, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53309', 'LOGOUT', 1627995391933, 'LOGIN', 1627995393369, 1436, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (88, 1627995394, 1627995394, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53309', 'LOGIN', 1627995393369, 'READY', 1627995394419, 1050, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (89, 1627995404, 1627995404, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53309', 'READY', 1627995394419, 'LOGOUT', 1627995404035, 9616, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (90, 1627995432, 1627995432, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53648', 'LOGOUT', 0, 'LOGIN', 1627995432907, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (91, 1627995435, 1627995435, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53648', 'LOGIN', 1627995432907, 'READY', 1627995435294, 2387, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (92, 1627995440, 1627995440, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'LOGOUT', 0, 'LOGIN', 1627995440149, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (93, 1627995441, 1627995441, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'LOGIN', 1627995440149, 'READY', 1627995441363, 1214, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (94, 1627995453, 1627995453, 1, 1, 1, '1001@test', 210501432476958720, 1, 1, '0.0.0.0:6800', '127.0.0.1:53648', 'READY', 1627995435294, 'IN_CALL_RING', 1627995453172, 17878, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (95, 1627995461, 1627995461, 1, 1, 1, '1001@test', 210501432476958720, 1, 1, '0.0.0.0:6800', '127.0.0.1:53648', 'IN_CALL_RING', 1627995453172, 'TALKING', 1627995461854, 8682, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (96, 1627995470, 1627995470, 1, 1, 1, '1001@test', 210501432476958720, 1, 1, '0.0.0.0:6800', '127.0.0.1:53648', 'TALKING', 1627995461854, 'AFTER', 1627995470717, 8863, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (97, 1627995475, 1627995475, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53648', 'AFTER', 1627995470717, 'READY', 1627995475700, 4983, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (98, 1627995482, 1627995482, 1, 1, 2, '1002@test', 210501557446246400, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'READY', 1627995441363, 'IN_CALL_RING', 1627995482806, 41443, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (99, 1627995502, 1627995502, 1, 1, 2, '1002@test', 210501557446246400, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'IN_CALL_RING', 1627995482806, 'AFTER', 1627995502479, 19673, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (100, 1627995521, 1627995521, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53648', 'READY', 1627995475700, 'NOT_READY', 1627995521109, 45409, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (101, 1627995527, 1627995527, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'AFTER', 1627995502479, 'READY', 1627995527076, 24597, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (102, 1627995539, 1627995539, 1, 1, 2, '1002@test', 210501796244750336, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'READY', 1627995527076, 'IN_CALL_RING', 1627995539567, 12491, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (103, 1627995570, 1627995570, 1, 1, 2, '1002@test', 210501796244750336, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'IN_CALL_RING', 1627995539567, 'AFTER', 1627995570127, 30560, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (104, 1627995598, 1627995598, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'AFTER', 1627995570127, 'READY', 1627995598605, 28478, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (105, 1627995601, 1627995601, 1, 1, 2, '1002@test', 210502051824664576, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'READY', 1627995598605, 'OUT_CALL', 1627995601572, 2967, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (106, 1627995613, 1627995613, 1, 1, 2, '1002@test', 210502051824664576, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'OUT_CALL', 1627995601572, 'TALKING', 1627995613150, 11578, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (107, 1627995623, 1627995623, 1, 1, 2, '1002@test', 210502051824664576, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'TALKING', 1627995613150, 'AFTER', 1627995623455, 10305, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (108, 1627995626, 1627995626, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53703', 'AFTER', 1627995623455, 'LOGOUT', 1627995626731, 3276, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (109, 1627995629, 1627995629, 1, 1, 1, '1001@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:53648', 'NOT_READY', 1627995521109, 'LOGOUT', 1627995629263, 108154, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (110, 1627995649, 1627995649, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55332', 'LOGOUT', 0, 'LOGIN', 1627995649531, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (111, 1627995651, 1627995651, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55332', 'LOGIN', 1627995649531, 'READY', 1627995651007, 1476, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (112, 1627995653, 1627995653, 1, 1, 2, '1002@test', 210502276266065920, 1, 1, '0.0.0.0:6800', '127.0.0.1:55332', 'READY', 1627995651007, 'OUT_CALL', 1627995653985, 2978, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (113, 1627995664, 1627995664, 1, 1, 2, '1002@test', 210502276266065920, 1, 1, '0.0.0.0:6800', '127.0.0.1:55332', 'OUT_CALL', 1627995653985, 'TALKING', 1627995664692, 10707, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (114, 1627995683, 1627995683, 1, 1, 2, '1002@test', 210502276266065920, 1, 1, '0.0.0.0:6800', '127.0.0.1:55332', 'TALKING', 1627995664692, 'AFTER', 1627995683121, 18429, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (115, 1627995687, 1627995687, 1, 1, 2, '1002@test', 0, 1, 1, '0.0.0.0:6800', '127.0.0.1:55332', 'AFTER', 1627995683121, 'READY', 1627995687204, 4083, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (116, 1627995694, 1627995694, 1, 1, 2, '1002@test', 210502446751940608, 1, 1, '0.0.0.0:6800', '127.0.0.1:55332', 'READY', 1627995687204, 'IN_CALL_RING', 1627995694818, 7614, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` VALUES (117, 1627995700, 1627995700, 1, 1, 2, '1002@test', 210502446751940608, 1, 1, '0.0.0.0:6800', '127.0.0.1:55332', 'IN_CALL_RING', 1627995694818, 'TALKING', 1627995700975, 6157, '', 1, '', '', '');
COMMIT;

-- ----------------------------
-- Table structure for cc_call_detail
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_detail`;
CREATE TABLE `cc_call_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `start_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '开始时间',
  `end_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '结束时间',
  `call_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '通话ID',
  `detail_index` int(11) NOT NULL DEFAULT '1' COMMENT '顺序',
  `transfer_type` int(11) NOT NULL DEFAULT '0' COMMENT '1:进vdn,2:进ivr,3:技能组,4:按键收号,5:外线\n',
  `transfer_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '转接ID',
  `reason` varchar(50) NOT NULL DEFAULT '' COMMENT '出队列原因:排队挂机或者转坐席',
  `ext1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_calldetail_call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通话流程表';

-- ----------------------------
-- Records of cc_call_detail
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_call_device
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_device`;
CREATE TABLE `cc_call_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `call_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '通话ID',
  `device_id` varchar(50) NOT NULL DEFAULT '' COMMENT '设备id',
  `agent_key` varchar(50) NOT NULL DEFAULT '' COMMENT '坐席',
  `device_type` int(11) NOT NULL DEFAULT '1' COMMENT '1:坐席,2:客户,3:外线',
  `cdr_type` int(11) NOT NULL DEFAULT '1' COMMENT '1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插',
  `caller` varchar(50) NOT NULL DEFAULT '' COMMENT '主叫',
  `called` varchar(50) NOT NULL DEFAULT '' COMMENT '被叫',
  `display` varchar(50) NOT NULL DEFAULT '' COMMENT '显号',
  `called_location` varchar(255) NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `caller_location` varchar(50) NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `call_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '呼叫开始时间',
  `ring_start_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '振铃开始时间',
  `ring_end_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '振铃结束时间',
  `answer_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '接通时间',
  `bridge_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '桥接时间',
  `end_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '结束时间',
  `talk_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '通话时长',
  `sip_protocol` varchar(50) NOT NULL DEFAULT '' COMMENT '信令协议(tcp/udp)',
  `channel_name` varchar(50) NOT NULL DEFAULT '' COMMENT '呼叫地址',
  `hangup_cause` varchar(50) NOT NULL COMMENT '挂机原因',
  `ring_cause` varchar(50) NOT NULL DEFAULT '' COMMENT '回铃音识别',
  `sip_status` varchar(50) NOT NULL DEFAULT '' COMMENT 'sip状态',
  `ext1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_calldetail__call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='话单明细表';

-- ----------------------------
-- Records of cc_call_device
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_device` VALUES (1, 1627662334850, 1627662397325, 209104236078694400, '8426573253798413', '1001@test', 1, 2, '1001', '871556590425001', '1001', '', '', 1627662334850, 1627662356425, 1627662361445, 1627662361445, 1627662364065, 1627662397325, 35880, '', 'sofia/external/871556590425001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (2, 1627662342300, 1627662399205, 209104236078694400, '5483615731370355', '1001@test', 2, 2, '01088889999', '144****0002', '14400010002', '', '', 1627662342300, 1627662361685, 1627662363725, 1627662363725, 1627662364065, 1627662399205, 35480, '', 'sofia/external/14400010002@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (3, 1627993576936, 1627993620965, 210493565480992768, '7940458572093423', '1002@test', 1, 1, '1002', '871556519788001', '18612983191', '', '', 1627993576936, 1627993603145, 1627993611245, 1627993611245, 1627993611345, 1627993620965, 9720, '', 'sofia/external/871556519788001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (4, 1627993576796, 1627993621185, 210493565480992768, '36a22ba7-a93b-43d5-9702-740738636f1e', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627993576796, 0, 1627993600525, 1627993600525, 1627993611345, 1627993621185, 20660, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (5, 1627993625790, 1627993682589, 210493770993500160, '6aba578c-19e9-4497-aaa6-27ccf76ae84b', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627993625790, 0, 1627993649505, 1627993649505, 1627993655445, 1627993682589, 33084, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (6, 1627993625882, 1627993682745, 210493770993500160, '1449654305122410', '1002@test', 1, 1, '1002', '871556519788001', '18612983191', '', '', 1627993625882, 1627993651405, 1627993655345, 1627993655345, 1627993655445, 1627993682745, 27400, '', 'sofia/external/871556519788001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (7, 1627994135662, 1627994190785, 210495909539086336, '958cc123-3bc3-435d-b973-73b5bc676781', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627994135662, 0, 1627994159365, 1627994159365, 1627994165165, 1627994190785, 31420, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (8, 1627994135764, 1627994190965, 210495909539086336, '1983392123262088', '1001@test', 1, 1, '1001', '871556590425001', '18612983191', '', '', 1627994135764, 1627994160445, 1627994165065, 1627994165065, 1627994165165, 1627994190965, 25900, '', 'sofia/external/871556590425001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (9, 1627994463241, 1627994679605, 210497283047817216, '2025499111837537', '1001@test', 1, 1, '1001', '871556590425001', '18612983191', '', '', 1627994463241, 1627994487325, 1627994491545, 1627994491545, 1627994491625, 1627994679605, 188060, '', 'sofia/external/871556590425001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (10, 1627994463135, 1627994679825, 210497283047817216, 'aadec6fe-70e1-44a9-a161-142fd111314e', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627994463135, 0, 1627994486845, 1627994486845, 1627994491625, 1627994679825, 192980, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (11, 1627994813123, 1627995136885, 210498751016468480, 'cc866f0d-2b33-4e84-af29-038560734ff8', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627994813123, 0, 1627994836845, 1627994836845, 1627994844985, 1627995136885, 300040, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (12, 1627994816883, 1627995137145, 210498751016468480, '3668672366082991', '1001@test', 1, 1, '1001', '871556590425001', '18612983191', '', '', 1627994816883, 1627994840925, 1627994844885, 1627994844885, 1627994844985, 1627995137145, 292260, '', 'sofia/external/871556590425001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (13, 1627995219784, 1627995368745, 210500456688910336, '1613724580010904', '1001@test', 1, 2, '1001', '871556590425001', '1001', '', '', 1627995219784, 1627995244005, 1627995251425, 1627995251425, 1627995253805, 1627995368745, 117320, '', 'sofia/external/871556590425001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (14, 1627995228708, 1627995369045, 210500456688910336, '4456350976672025', '1001@test', 2, 2, '01088889999', '144****0002', '14400010002', '', '', 1627995228708, 1627995251565, 1627995253605, 1627995253605, 1627995253805, 1627995369045, 115440, '', 'sofia/external/14400010002@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (15, 1627995356099, 1627995405865, 210501027873423360, '5564290161630943', '1001@test', 1, 1, '1001', '871556590425001', '18612983191', '', '', 1627995356099, 1627995381625, 1627995386185, 1627995386185, 1627995386265, 1627995405865, 19680, '', 'sofia/external/871556590425001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (16, 1627995355965, 1627995406045, 210501027873423360, '013ce278-8cb5-46f9-a19e-1b6b65e06897', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627995355965, 0, 1627995379685, 1627995379685, 1627995386265, 1627995406045, 26360, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (17, 1627995452538, 1627995494265, 210501432476958720, '1018788979622682', '1001@test', 1, 1, '1001', '871556590425001', '18612983191', '', '', 1627995452538, 1627995476805, 1627995484205, 1627995484205, 1627995484305, 1627995494265, 10060, '', 'sofia/external/871556590425001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (18, 1627995452431, 1627995494485, 210501432476958720, 'b18e3b10-411c-4851-a943-e1b8bb7efa01', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627995452431, 0, 1627995476145, 1627995476145, 1627995484305, 1627995494485, 18340, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (19, 1627995482225, 1627995525945, 210501557446246400, '8c475c6f-6a16-47be-9c26-ad2a6293326a', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627995482225, 0, 1627995505925, 1627995505925, 0, 1627995525945, 20020, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (20, 1627995482313, 1627995526085, 210501557446246400, '8882163318227084', '1002@test', 1, 1, '1002', '871556519788001', '18612983191', '', '', 1627995482313, 1627995506425, 0, 0, 0, 1627995526085, 0, '', 'sofia/external/871556519788001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (21, 1627995539227, 1627995593665, 210501796244750336, '8781538151494795', '1002@test', 1, 1, '1002', '871556519788001', '18612983191', '', '', 1627995539227, 1627995563205, 0, 0, 0, 1627995593665, 0, '', 'sofia/external/871556519788001@172.17.0.2:6685', 'RECOVERY_ON_TIMER_EXPIRE', '', '408', '', '', 1);
INSERT INTO `cc_call_device` VALUES (22, 1627995539159, 1627995618385, 210501796244750336, '0f4a770d-65c0-4907-bbf9-125e7da95e19', '', 2, 1, '186****3191', '01011515902', '', '', '', 1627995539159, 0, 1627995562865, 1627995562865, 0, 1627995618385, 55520, 'udp', 'sofia/external/18612983191@192.168.180.37', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (23, 1627995600094, 1627995646965, 210502051824664576, '3102515140512459', '1002@test', 1, 2, '1002', '871556519788001', '1002', '', '', 1627995600094, 1627995625225, 1627995632605, 1627995632605, 1627995634885, 1627995646965, 14360, '', 'sofia/external/871556519788001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (24, 1627995609594, 1627995647205, 210502051824664576, '4139239056029410', '1002@test', 2, 2, '01088889999', '144****0002', '14400010002', '', '', 1627995609594, 1627995632725, 1627995634726, 1627995634726, 1627995634885, 1627995647205, 12479, '', 'sofia/external/14400010002@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` VALUES (25, 1627995653605, 1627995706645, 210502276266065920, '2429173174569363', '1002@test', 1, 2, '1002', '871556519788001', '1002', '', '', 1627995653605, 1627995677605, 1627995685005, 1627995685005, 1627995687405, 1627995706645, 21640, '', 'sofia/external/871556519788001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` VALUES (26, 1627995662160, 1627995706885, 210502276266065920, '3859403297110954', '1002@test', 2, 2, '01088889999', '144****0002', '14400010002', '', '', 1627995662160, 1627995685145, 1627995687165, 1627995687165, 1627995687405, 1627995706885, 19720, '', 'sofia/external/14400010002@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_call_dtmf
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_dtmf`;
CREATE TABLE `cc_call_dtmf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `dtmf_key` varchar(255) NOT NULL DEFAULT '' COMMENT '按键号码',
  `process_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '业务流程id',
  `call_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '通话标识id',
  `dtmf_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '按键时间',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='呼叫按键表';

-- ----------------------------
-- Records of cc_call_dtmf
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_call_log
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_log`;
CREATE TABLE `cc_call_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '落单时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `call_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '话单id',
  `caller_display` varchar(255) NOT NULL DEFAULT '' COMMENT '主叫显号',
  `caller` varchar(50) NOT NULL DEFAULT '' COMMENT '主叫',
  `called_display` varchar(255) NOT NULL DEFAULT '' COMMENT '被叫显号',
  `called` varchar(50) NOT NULL DEFAULT '' COMMENT '被叫',
  `agent_key` varchar(20) NOT NULL DEFAULT '' COMMENT '坐席',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能组',
  `login_type` int(11) NOT NULL DEFAULT '1' COMMENT '1:sip号,2:webrtc,3:手机',
  `task_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '任务ID',
  `ivr_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ivr',
  `bot_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '机器人id',
  `call_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '呼叫开始时间',
  `answer_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '接听时间',
  `end_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '结束时间',
  `call_type` varchar(32) NOT NULL DEFAULT '' COMMENT '呼叫类型',
  `direction` varchar(32) NOT NULL DEFAULT '' COMMENT '呼叫方向',
  `answer_flag` int(11) NOT NULL DEFAULT '0' COMMENT '通话标识(0:接通,1:坐席未接用户未接,2:坐席接通用户未接通,3:用户接通坐席未接通)',
  `wait_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '累计等待时长',
  `answer_count` int(11) NOT NULL DEFAULT '0' COMMENT '应答设备数',
  `hangup_dir` int(11) NOT NULL DEFAULT '1' COMMENT '挂机方向(1:主叫挂机,2:被叫挂机,3:系统挂机)',
  `hangup_cause` varchar(255) NOT NULL DEFAULT '' COMMENT '挂机原因',
  `media` varchar(255) NOT NULL DEFAULT '' COMMENT '媒体服务器',
  `record` varchar(255) NOT NULL DEFAULT '' COMMENT '录音地址',
  `talk_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '通话时长(秒)',
  `follow_data` varchar(4096) NOT NULL DEFAULT '' COMMENT '通话随路数据(2048)',
  `uuid1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展1',
  `uuid2` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext2` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext3` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_calllog_call_id` (`call_id`) USING BTREE,
  KEY `idx_calllog_create_time` (`call_time`) USING BTREE,
  KEY `idx_call_log_agent` (`agent_key`),
  KEY `idx_call_log_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='话单表';

-- ----------------------------
-- Records of cc_call_log
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_log` VALUES (1, 1627662334850, 1627662399205, 1, 209104236078694400, '1001', '871556590425001', '01088889999', '144****0002', '1001@test', 1, 1, 0, 0, 0, 1627662334850, 1627662364065, 1627662399205, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 'NORMAL_CLEARING', '172.17.0.2', '/app/clpms/record/20210731/209104236078694400_871556590425001_14400010002.wav', 35140, '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (2, 1627993576796, 1627993621185, 1, 210493565480992768, '', '186****3191', '', '01011515902', '1002@test', 1, 1, 0, 0, 0, 1627993576796, 1627993611345, 1627993621185, 'INBOUND_CALL', 'INBOUND', 0, 0, 3, 2, 'NORMAL_CLEARING', '172.17.0.2', '', 9840, '{}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (3, 1627993625790, 1627993682745, 1, 210493770993500160, '', '186****3191', '', '01011515902', '1002@test', 1, 1, 0, 0, 0, 1627993625790, 1627993655445, 1627993682745, 'INBOUND_CALL', 'INBOUND', 0, 0, 3, 1, 'NORMAL_CLEARING', '172.17.0.2', '', 27300, '{}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (4, 1627993663566, 0, 1, 210493929433333760, '', '186****3191', '', '01011515902', '1001@test', 1, 1, 0, 0, 0, 1627993663566, 1627993693145, 0, '', 'INBOUND', 0, 0, 3, 1, '', '172.17.0.2', '', 0, '', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (5, 1627993969338, 0, 1, 210495211925667840, '', '186****3191', '', '01011515902', '1002@test', 1, 1, 0, 0, 0, 1627993969338, 1627994001005, 0, '', 'INBOUND', 0, 0, 3, 1, '', '172.17.0.2', '', 0, '', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (6, 1627994135662, 1627994190965, 1, 210495909539086336, '', '186****3191', '', '01011515902', '1001@test', 1, 1, 0, 0, 0, 1627994135662, 1627994165165, 1627994190965, 'INBOUND_CALL', 'INBOUND', 0, 0, 3, 1, 'NORMAL_CLEARING', '172.17.0.2', '', 25800, '{}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (7, 1627994170727, 0, 1, 210496056624939008, '', '186****3191', '', '01011515902', '1002@test', 1, 1, 0, 0, 0, 1627994170727, 1627994200245, 0, '', 'INBOUND', 0, 0, 3, 1, '', '172.17.0.2', '', 0, '', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (8, 1627994463135, 1627994679825, 1, 210497283047817216, '', '186****3191', '', '01011515902', '1001@test', 1, 1, 0, 0, 0, 1627994463135, 1627994491625, 1627994679825, 'INBOUND_CALL', 'INBOUND', 0, 0, 3, 2, 'NORMAL_CLEARING', '172.17.0.2', '', 188200, '{}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (9, 1627994813123, 1627995137145, 1, 210498751016468480, '', '186****3191', '', '01011515902', '1001@test', 1, 1, 0, 0, 0, 1627994813123, 1627994844985, 1627995137145, 'INBOUND_CALL', 'INBOUND', 0, 0, 3, 1, 'NORMAL_CLEARING', '172.17.0.2', '', 292160, '{}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (10, 1627995219784, 1627995369045, 1, 210500456688910336, '1001', '871556590425001', '01088889999', '144****0002', '1001@test', 1, 1, 0, 0, 0, 1627995219784, 1627995253805, 1627995369045, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 'NORMAL_CLEARING', '172.17.0.2', '/app/clpms/record/20210803/210500456688910336_871556590425001_14400010002.wav', 115240, '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (11, 1627995355965, 1627995406045, 1, 210501027873423360, '', '186****3191', '', '01011515902', '1001@test', 1, 1, 0, 0, 0, 1627995355965, 1627995386265, 1627995406045, 'INBOUND_CALL', 'INBOUND', 0, 0, 3, 2, 'NORMAL_CLEARING', '172.17.0.2', '', 19780, '{}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (12, 1627995452431, 1627995494485, 1, 210501432476958720, '', '186****3191', '', '01011515902', '1001@test', 1, 1, 0, 0, 0, 1627995452431, 1627995484305, 1627995494485, 'INBOUND_CALL', 'INBOUND', 0, 0, 3, 2, 'NORMAL_CLEARING', '172.17.0.2', '', 10180, '{}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (13, 1627995539159, 1627995618385, 1, 210501796244750336, '', '186****3191', '', '01011515902', '1002@test', 1, 1, 0, 0, 0, 1627995539159, 0, 1627995618385, 'INBOUND_CALL', 'INBOUND', 3, 0, 2, 2, 'RECOVERY_ON_TIMER_EXPIRE', '172.17.0.2', '', 0, '{}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (14, 1627995600094, 1627995647205, 1, 210502051824664576, '1002', '871556519788001', '01088889999', '144****0002', '1002@test', 1, 1, 0, 0, 0, 1627995600094, 1627995634885, 1627995647205, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 'NORMAL_CLEARING', '172.17.0.2', '/app/clpms/record/20210803/210502051824664576_871556519788001_14400010002.wav', 12320, '{\"autoAnswer\":\"true\"}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (15, 1627995653605, 1627995706885, 1, 210502276266065920, '1002', '871556519788001', '01088889999', '144****0002', '1002@test', 1, 1, 0, 0, 0, 1627995653605, 1627995687405, 1627995706885, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 'NORMAL_CLEARING', '172.17.0.2', '/app/clpms/record/20210803/210502276266065920_871556519788001_14400010002.wav', 19480, '{\"autoAnswer\":\"true\"}', '', '', '', '', '', 1);
INSERT INTO `cc_call_log` VALUES (16, 1627995694253, 0, 1, 210502446751940608, '', '186****3191', '', '01011515902', '1002@test', 1, 1, 0, 0, 0, 1627995694253, 1627995723845, 0, '', 'INBOUND', 0, 0, 3, 1, '', '172.17.0.2', '', 0, '', '', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_company
-- ----------------------------
DROP TABLE IF EXISTS `cc_company`;
CREATE TABLE `cc_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `id_path` varchar(255) NOT NULL DEFAULT '' COMMENT '父企业ID',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父企业',
  `company_code` varchar(255) NOT NULL DEFAULT '' COMMENT '简称',
  `contact` varchar(255) NOT NULL DEFAULT '' COMMENT '联系人',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '电话',
  `balance` bigint(20) NOT NULL DEFAULT '0' COMMENT '金额',
  `bill_type` int(11) NOT NULL DEFAULT '0' COMMENT '1:呼出计费,2:呼入计费,3:双向计费,0:全免费',
  `pay_type` int(11) NOT NULL DEFAULT '0' COMMENT '0:预付费;1:后付费',
  `hidden_customer` int(11) NOT NULL DEFAULT '0' COMMENT '隐藏客户号码(0:不隐藏;1:隐藏)',
  `secret_key` varchar(32) NOT NULL DEFAULT '' COMMENT '验证秘钥',
  `ivr_limit` int(11) NOT NULL DEFAULT '50' COMMENT 'IVR通道数',
  `agent_limit` int(11) NOT NULL DEFAULT '50' COMMENT '开通坐席',
  `group_limit` int(11) NOT NULL DEFAULT '10' COMMENT '开通技能组',
  `group_agent_limit` int(11) NOT NULL DEFAULT '1000' COMMENT '单技能组中坐席上限',
  `agent_state` int(11) NOT NULL DEFAULT '0' COMMENT '坐席状态统计',
  `inbound_stat` int(11) NOT NULL DEFAULT '0' COMMENT '呼入报表统计',
  `outbound_stat` int(11) NOT NULL DEFAULT '0' COMMENT '外呼报表统计',
  `group_wait_stat` int(11) NOT NULL DEFAULT '0' COMMENT '技能组排队统计',
  `blacklist` bigint(20) NOT NULL DEFAULT '0' COMMENT '黑名单',
  `notify_url` varchar(255) NOT NULL DEFAULT '' COMMENT '话单回调通知',
  `ext1` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext4` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext5` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态(1:启用,0:未启用)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `company_unqidx_name` (`name`),
  UNIQUE KEY `company_unqidx_code` (`company_code`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='企业信息表';

-- ----------------------------
-- Records of cc_company
-- ----------------------------
BEGIN;
INSERT INTO `cc_company` VALUES (1, 1604503580, 1604503580, 'test企业', '', 0, 'test', 'test', '18612983191', 1, 0, 1, 1, '', 50, 5000, 20, 1000, 0, 0, 0, 0, 0, 'http://192.168.177.183:8709/push/forcePush/pushTest', '', '', '', '', '', 1);
INSERT INTO `cc_company` VALUES (2, 0, 1619796030, '20210430-delLwqXnW', '', 1, 'test2', '曹亮', '', 0, 0, 0, 1, 'djJHDuy34r87du34', 50, 50, 10, 1000, 0, 0, 0, 0, 0, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` VALUES (15, 0, 1619797353, 'aaaa-delcSJeGY', '', 0, 'acscwe-delIqjPqn', '', '', 0, 0, 0, 1, '', 50, 50, 10, 1000, 0, 0, 0, 0, 0, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` VALUES (16, 0, 0, 'aaaa', '', 0, 'acscwe', '', '', 0, 0, 0, 1, '', 50, 50, 10, 1000, 0, 0, 0, 0, 0, '', '', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_company_display
-- ----------------------------
DROP TABLE IF EXISTS `cc_company_display`;
CREATE TABLE `cc_company_display` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '号码池',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '1:主叫显号,2:被叫显号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `uni_idx_company_display` (`company_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='号码池表';

-- ----------------------------
-- Records of cc_company_display
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_display` VALUES (1, 1604503580, 1604503580, 1, '呼入号码池', 1, 1);
INSERT INTO `cc_company_display` VALUES (2, 1604503580, 1624463394, 1, '主叫外显-deleEwjrg', 2, 0);
INSERT INTO `cc_company_display` VALUES (3, 1604503580, 1624464161, 1, '被叫外显-delUKKKkq', 3, 0);
INSERT INTO `cc_company_display` VALUES (4, 1624443516, 0, 0, '主机号码池', 2, 1);
INSERT INTO `cc_company_display` VALUES (5, 1624443589, 0, 0, '主机号码池1', 2, 1);
INSERT INTO `cc_company_display` VALUES (6, 1624443849, 0, 0, '主机号码池12', 2, 1);
INSERT INTO `cc_company_display` VALUES (7, 1624445273, 0, 0, '主机号码池12222', 2, 1);
INSERT INTO `cc_company_display` VALUES (8, 1624445321, 0, 0, '主机号码池1212', 2, 1);
INSERT INTO `cc_company_display` VALUES (9, 1624445604, 0, 0, '主机号码池1213', 2, 1);
INSERT INTO `cc_company_display` VALUES (10, 1624445968, 0, 0, '主机号码池1214', 2, 1);
INSERT INTO `cc_company_display` VALUES (11, 1624451930, 0, 0, '主机号码池124', 2, 1);
INSERT INTO `cc_company_display` VALUES (12, 1624452041, 0, 0, '主机号码池224', 2, 1);
INSERT INTO `cc_company_display` VALUES (13, 1624452196, 0, 0, '主机号码池324', 2, 1);
INSERT INTO `cc_company_display` VALUES (14, 1624452379, 0, 0, '主机号码池321', 2, 1);
INSERT INTO `cc_company_display` VALUES (17, 1624452723, 0, 0, '主机号码池3211', 2, 1);
INSERT INTO `cc_company_display` VALUES (18, 1624452836, 0, 0, '主机号码池3333', 2, 1);
INSERT INTO `cc_company_display` VALUES (19, 1624453002, 0, 0, '主机号码池33331', 2, 1);
INSERT INTO `cc_company_display` VALUES (20, 1624453280, 0, 0, '主机号码池333331', 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_company_phone
-- ----------------------------
DROP TABLE IF EXISTS `cc_company_phone`;
CREATE TABLE `cc_company_phone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '号码',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '1:呼入号码,2:主叫显号,3:被叫显号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '1:未启用,2:启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_companyphone_phone` (`company_id`,`phone`,`type`),
  KEY `idx_companyhpone_company_id` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='企业号码';

-- ----------------------------
-- Records of cc_company_phone
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_phone` VALUES (1, 1604503580, 1622123026, 1, '18800010002', 1, 0);
INSERT INTO `cc_company_phone` VALUES (2, 1604503580, 1604503580, 1, '01012345678', 2, 1);
INSERT INTO `cc_company_phone` VALUES (3, 1604503580, 1604503580, 1, '01088889999', 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_company_phone_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_company_phone_group`;
CREATE TABLE `cc_company_phone_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `display_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '号码池id',
  `phone_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '号码id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cc_company_phone_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_phone_group` VALUES (1, 1624452836, 0, 0, 0, 1);
INSERT INTO `cc_company_phone_group` VALUES (2, 1624452836, 0, 0, 0, 3);
INSERT INTO `cc_company_phone_group` VALUES (3, 1624452836, 0, 0, 0, 4);
INSERT INTO `cc_company_phone_group` VALUES (4, 1624452836, 0, 0, 0, 5);
INSERT INTO `cc_company_phone_group` VALUES (5, 1624453002, 0, 0, 0, 1);
INSERT INTO `cc_company_phone_group` VALUES (6, 1624453037, 0, 0, 0, 3);
INSERT INTO `cc_company_phone_group` VALUES (7, 1624453037, 0, 0, 0, 4);
INSERT INTO `cc_company_phone_group` VALUES (8, 1624453037, 0, 0, 0, 5);
INSERT INTO `cc_company_phone_group` VALUES (9, 1624453280, 0, 0, 20, 1);
INSERT INTO `cc_company_phone_group` VALUES (10, 1624453304, 0, 0, 20, 3);
INSERT INTO `cc_company_phone_group` VALUES (11, 1624453304, 0, 0, 20, 4);
INSERT INTO `cc_company_phone_group` VALUES (12, 1624453304, 0, 0, 20, 5);
INSERT INTO `cc_company_phone_group` VALUES (25, 1624463566, 0, 0, 2, 1);
INSERT INTO `cc_company_phone_group` VALUES (26, 1624463566, 0, 0, 2, 3);
INSERT INTO `cc_company_phone_group` VALUES (27, 1624463566, 0, 0, 2, 4);
COMMIT;

-- ----------------------------
-- Table structure for cc_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_group`;
CREATE TABLE `cc_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '技能组名称',
  `control_flag` int(11) NOT NULL DEFAULT '0' COMMENT '控制开关 1:技能组,2:坐席',
  `after_interval` int(11) NOT NULL DEFAULT '5' COMMENT '话后自动空闲时长',
  `caller_display_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '主叫显号号码池',
  `called_display_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '被叫显号号码池',
  `record_type` int(11) NOT NULL DEFAULT '1',
  `level_value` int(11) NOT NULL DEFAULT '1' COMMENT '技能组优先级',
  `tts_engine` int(11) NOT NULL DEFAULT '0',
  `play_content` varchar(100) NOT NULL DEFAULT '' COMMENT '转坐席时播放内容',
  `evaluate` bigint(20) NOT NULL DEFAULT '0' COMMENT '转服务评价(0:否,1:是)',
  `queue_play` bigint(20) NOT NULL DEFAULT '0' COMMENT '排队音',
  `transfer_play` bigint(20) NOT NULL DEFAULT '0' COMMENT '转接提示音',
  `group_type` int(11) NOT NULL DEFAULT '0' COMMENT '技能组类型',
  `notify_position` int(11) NOT NULL DEFAULT '0' COMMENT '0:不播放排队位置,1:播放排队位置',
  `notify_rate` int(11) NOT NULL DEFAULT '10' COMMENT '频次',
  `notify_content` varchar(255) NOT NULL DEFAULT '' COMMENT '您前面还有$位用户在等待',
  `call_memory` int(11) NOT NULL DEFAULT '1' COMMENT '主叫记忆(1:开启,0:不开启)',
  `ext1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext4` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext5` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_company_name` (`company_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='技能组表';

-- ----------------------------
-- Records of cc_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_group` VALUES (1, 1604503580, 1604503580, 1, '测试技能组', 1, 5, 0, 0, 1, 1, 0, '0', 0, 0, 0, 0, 1, 10, '0', 1, '', '', '', '', '', 1);
INSERT INTO `cc_group` VALUES (2, 1621556151, 0, 1, '测试技能组1', 1, 5, 0, 0, 1, 1, 0, '0', 0, 0, 0, 1, 1, 10, '0', 1, '', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_group_agent_strategy
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_agent_strategy`;
CREATE TABLE `cc_group_agent_strategy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT ' 创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能组id',
  `strategy_type` int(11) NOT NULL DEFAULT '1' COMMENT '1:内置策略,2:自定义',
  `strategy_value` int(11) NOT NULL DEFAULT '1' COMMENT '(1最长空闲时间、2最长平均空闲、3最少应答次数、4最少通话时长、5最长话后时长、6轮选、7随机)',
  `custom_expression` varchar(255) NOT NULL DEFAULT '' COMMENT '自定义表达式',
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='技能组中坐席分配策略';

-- ----------------------------
-- Records of cc_group_agent_strategy
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_agent_strategy` VALUES (1, 1, 1, 1, 1, 1, 1, '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_group_memory
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_memory`;
CREATE TABLE `cc_group_memory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `agent_key` varchar(32) NOT NULL DEFAULT '' COMMENT '坐席',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '客户电话',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_idx_group_id` (`group_id`,`phone`,`agent_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='坐席与客户记忆表';

-- ----------------------------
-- Records of cc_group_memory
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_group_memory_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_memory_config`;
CREATE TABLE `cc_group_memory_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `success_strategy` int(11) NOT NULL DEFAULT '0' COMMENT '匹配成功策略',
  `success_strategy_value` bigint(20) NOT NULL DEFAULT '0' COMMENT '匹配成功策略值',
  `fail_strategy` int(11) NOT NULL DEFAULT '0' COMMENT '匹配失败策略',
  `fail_strategy_value` bigint(20) NOT NULL DEFAULT '0' COMMENT '匹配失败策略值',
  `memory_day` int(11) NOT NULL DEFAULT '30' COMMENT '记忆天数',
  `inbound_cover` int(11) NOT NULL DEFAULT '0' COMMENT '呼入覆盖',
  `outbound_cover` int(11) NOT NULL DEFAULT '0' COMMENT '外呼覆盖',
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='技能组坐席记忆配置表';

-- ----------------------------
-- Records of cc_group_memory_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_group_overflow
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_overflow`;
CREATE TABLE `cc_group_overflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `overflow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '溢出策略ID',
  `level_value` int(11) NOT NULL DEFAULT '1' COMMENT '优先级',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_group_overflow` (`group_id`,`overflow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='技能组排队策略表';

-- ----------------------------
-- Records of cc_group_overflow
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_overflow` VALUES (1, 1, 1, 1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_group_strategy_exp
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_strategy_exp`;
CREATE TABLE `cc_group_strategy_exp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能组id',
  `strategy_key` varchar(20) NOT NULL DEFAULT '' COMMENT '自定义值',
  `strategy_present` int(11) NOT NULL DEFAULT '1' COMMENT '百分百',
  `strategy_type` int(11) NOT NULL DEFAULT '1' COMMENT '类型',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='坐席自定义策略表';

-- ----------------------------
-- Records of cc_group_strategy_exp
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_strategy_exp` VALUES (1, 1, 1, 1, 1, '1', 1, 1, 1);
INSERT INTO `cc_group_strategy_exp` VALUES (2, 2, 2, 1, 1, '2', 2, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_overflow_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_overflow_config`;
CREATE TABLE `cc_overflow_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) DEFAULT '0' COMMENT '企业id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `handle_type` int(11) NOT NULL DEFAULT '1' COMMENT '1:排队,2:溢出,3:挂机',
  `busy_type` int(11) DEFAULT '1' COMMENT '排队方式(1:先进先出,2:vip,3:自定义)',
  `queue_timeout` int(11) DEFAULT '60' COMMENT '排队超时时间',
  `busy_timeout_type` int(11) DEFAULT '1' COMMENT '排队超时(1:溢出,2:挂机)',
  `overflow_type` int(11) DEFAULT '1' COMMENT '溢出(1:group,2:ivr,3:vdn)',
  `overflow_value` int(11) DEFAULT '0' COMMENT '溢出值',
  `lineup_expression` varchar(255) DEFAULT '' COMMENT '自定义排队表达式',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_name` (`company_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='溢出策略表';

-- ----------------------------
-- Records of cc_overflow_config
-- ----------------------------
BEGIN;
INSERT INTO `cc_overflow_config` VALUES (1, 1, 1, 1, '排队60秒', 1, 1, 60, 2, 1, 0, '');
COMMIT;

-- ----------------------------
-- Table structure for cc_overflow_exp
-- ----------------------------
DROP TABLE IF EXISTS `cc_overflow_exp`;
CREATE TABLE `cc_overflow_exp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `overflow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '溢出策略ID',
  `exp_key` varchar(30) NOT NULL DEFAULT '' COMMENT '自定义值',
  `rate` int(11) NOT NULL DEFAULT '1' COMMENT '权重',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='自定义溢出策略优先级';

-- ----------------------------
-- Records of cc_overflow_exp
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_overflow_front
-- ----------------------------
DROP TABLE IF EXISTS `cc_overflow_front`;
CREATE TABLE `cc_overflow_front` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `overflow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '策略ID',
  `front_key` varchar(20) NOT NULL DEFAULT '' COMMENT 'key',
  `compare_condition` int(11) NOT NULL DEFAULT '0' COMMENT '5种条件',
  `rank_value_start` int(11) NOT NULL DEFAULT '0',
  `rank_value` int(11) NOT NULL DEFAULT '0' COMMENT '符号条件值',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='溢出策略前置条件';

-- ----------------------------
-- Records of cc_overflow_front
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_playback
-- ----------------------------
DROP TABLE IF EXISTS `cc_playback`;
CREATE TABLE `cc_playback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL COMMENT '企业ID',
  `playback` varchar(255) NOT NULL DEFAULT '' COMMENT '放音文件',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '1:待审核,2:审核通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='语音文件表';

-- ----------------------------
-- Records of cc_playback
-- ----------------------------
BEGIN;
INSERT INTO `cc_playback` VALUES (1, 1, 1, 1, '/app/clpms/sounds/222.wav', 2);
COMMIT;

-- ----------------------------
-- Table structure for cc_push_fail_log
-- ----------------------------
DROP TABLE IF EXISTS `cc_push_fail_log`;
CREATE TABLE `cc_push_fail_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `call_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'callid',
  `send_times` int(11) NOT NULL DEFAULT '1' COMMENT '发送次数',
  `send_url` varchar(255) NOT NULL DEFAULT '' COMMENT '发送次数',
  `content` varchar(4000) NOT NULL DEFAULT '' COMMENT '推送内容',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态(1:推送，0:不推送)',
  PRIMARY KEY (`id`),
  KEY `idx_push_send_times` (`send_times`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cc_push_fail_log
-- ----------------------------
BEGIN;
INSERT INTO `cc_push_fail_log` VALUES (1, 1627662380, 0, 1, 209104236078694400, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1627662364065,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1627662363725,\"bridgeTime\":1627662364065,\"callId\":209104236078694400,\"callTime\":1627662342300,\"called\":\"14400010002\",\"calledLocation\":\"\",\"caller\":\"01088889999\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"5483615731370355\",\"deviceType\":2,\"display\":\"14400010002\",\"endTime\":1627662399205,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627662363725,\"ringStartTime\":1627662361685,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":35480},{\"agentKey\":\"1001@test\",\"answerTime\":1627662361445,\"bridgeTime\":1627662364065,\"callId\":209104236078694400,\"callTime\":1627662334850,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"8426573253798413\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1627662397325,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627662361445,\"ringStartTime\":1627662356425,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":35880}],\"callId\":209104236078694400,\"callTime\":1627662334850,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"cts\":1627662334850,\"direction\":\"OUTBOUND\",\"endTime\":1627662399205,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"/app/clpms/record/20210731/209104236078694400_871556590425001_14400010002.wav\",\"talkTime\":35140,\"taskId\":\"0\",\"uts\":1627662399205,\"uuid1\":\"123456\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (2, 1627993597, 0, 1, 210493565480992768, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1002@test\",\"answerCount\":3,\"answerFlag\":\"0\",\"answerTime\":1627993611345,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1002@test\",\"answerTime\":1627993611245,\"bridgeTime\":1627993611345,\"callId\":210493565480992768,\"callTime\":1627993576936,\"called\":\"871556519788001\",\"calledLocation\":\"\",\"caller\":\"1002\",\"callerLocation\":\"\",\"channelName\":\"sofia/external/871556519788001@172.17.0.2:6685\",\"deviceId\":\"7940458572093423\",\"deviceType\":1,\"display\":\"18612983191\",\"endTime\":1627993620965,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627993611245,\"ringStartTime\":1627993603145,\"sipProtocol\":\"\",\"sipStatus\":\"200\",\"talkTime\":9720},{\"agentKey\":\"\",\"answerTime\":1627993600525,\"bridgeTime\":1627993611345,\"callId\":210493565480992768,\"callTime\":1627993576796,\"called\":\"01011515902\",\"calledLocation\":\"\",\"caller\":\"18612983191\",\"callerLocation\":\"\",\"cdrType\":1,\"channelName\":\"sofia/external/18612983191@192.168.180.37\",\"deviceId\":\"36a22ba7-a93b-43d5-9702-740738636f1e\",\"deviceType\":2,\"display\":\"\",\"endTime\":1627993621185,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627993600525,\"sipProtocol\":\"udp\",\"sipStatus\":\"\",\"talkTime\":20660}],\"callId\":210493565480992768,\"callTime\":1627993576796,\"callType\":\"INBOUND_CALL\",\"called\":\"01011515902\",\"calledDisplay\":\"\",\"caller\":\"18612983191\",\"callerDisplay\":\"\",\"companyId\":1,\"cts\":1627993576796,\"direction\":\"INBOUND\",\"endTime\":1627993621185,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":\"1\",\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"talkTime\":9840,\"taskId\":\"0\",\"uts\":1627993621185,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (3, 1627993659, 0, 1, 210493770993500160, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1002@test\",\"answerCount\":3,\"answerFlag\":\"0\",\"answerTime\":1627993655445,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1002@test\",\"answerTime\":1627993655345,\"bridgeTime\":1627993655445,\"callId\":210493770993500160,\"callTime\":1627993625882,\"called\":\"871556519788001\",\"calledLocation\":\"\",\"caller\":\"1002\",\"callerLocation\":\"\",\"channelName\":\"sofia/external/871556519788001@172.17.0.2:6685\",\"deviceId\":\"1449654305122410\",\"deviceType\":1,\"display\":\"18612983191\",\"endTime\":1627993682745,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627993655345,\"ringStartTime\":1627993651405,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":27400},{\"agentKey\":\"\",\"answerTime\":1627993649505,\"bridgeTime\":1627993655445,\"callId\":210493770993500160,\"callTime\":1627993625790,\"called\":\"01011515902\",\"calledLocation\":\"\",\"caller\":\"18612983191\",\"callerLocation\":\"\",\"cdrType\":1,\"channelName\":\"sofia/external/18612983191@192.168.180.37\",\"deviceId\":\"6aba578c-19e9-4497-aaa6-27ccf76ae84b\",\"deviceType\":2,\"display\":\"\",\"endTime\":1627993682589,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627993649505,\"sipProtocol\":\"udp\",\"sipStatus\":\"200\",\"talkTime\":33084}],\"callId\":210493770993500160,\"callTime\":1627993625790,\"callType\":\"INBOUND_CALL\",\"called\":\"01011515902\",\"calledDisplay\":\"\",\"caller\":\"18612983191\",\"callerDisplay\":\"\",\"companyId\":1,\"cts\":1627993625790,\"direction\":\"INBOUND\",\"endTime\":1627993682745,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":\"1\",\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"talkTime\":27300,\"taskId\":\"0\",\"uts\":1627993682745,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (4, 1627994167, 0, 1, 210495909539086336, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":3,\"answerFlag\":\"0\",\"answerTime\":1627994165165,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1627994165065,\"bridgeTime\":1627994165165,\"callId\":210495909539086336,\"callTime\":1627994135764,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"1983392123262088\",\"deviceType\":1,\"display\":\"18612983191\",\"endTime\":1627994190965,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627994165065,\"ringStartTime\":1627994160445,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":25900},{\"agentKey\":\"\",\"answerTime\":1627994159365,\"bridgeTime\":1627994165165,\"callId\":210495909539086336,\"callTime\":1627994135662,\"called\":\"01011515902\",\"calledLocation\":\"\",\"caller\":\"18612983191\",\"callerLocation\":\"\",\"cdrType\":1,\"channelName\":\"sofia/external/18612983191@192.168.180.37\",\"deviceId\":\"958cc123-3bc3-435d-b973-73b5bc676781\",\"deviceType\":2,\"display\":\"\",\"endTime\":1627994190785,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627994159365,\"sipProtocol\":\"udp\",\"sipStatus\":\"200\",\"talkTime\":31420}],\"callId\":210495909539086336,\"callTime\":1627994135662,\"callType\":\"INBOUND_CALL\",\"called\":\"01011515902\",\"calledDisplay\":\"\",\"caller\":\"18612983191\",\"callerDisplay\":\"\",\"companyId\":1,\"cts\":1627994135662,\"direction\":\"INBOUND\",\"endTime\":1627994190965,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":\"1\",\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"talkTime\":25800,\"taskId\":\"0\",\"uts\":1627994190965,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (5, 1627994656, 0, 1, 210497283047817216, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":3,\"answerFlag\":\"0\",\"answerTime\":1627994491625,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1627994491545,\"bridgeTime\":1627994491625,\"callId\":210497283047817216,\"callTime\":1627994463241,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"2025499111837537\",\"deviceType\":1,\"display\":\"18612983191\",\"endTime\":1627994679605,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627994491545,\"ringStartTime\":1627994487325,\"sipProtocol\":\"\",\"sipStatus\":\"200\",\"talkTime\":188060},{\"agentKey\":\"\",\"answerTime\":1627994486845,\"bridgeTime\":1627994491625,\"callId\":210497283047817216,\"callTime\":1627994463135,\"called\":\"01011515902\",\"calledLocation\":\"\",\"caller\":\"18612983191\",\"callerLocation\":\"\",\"cdrType\":1,\"channelName\":\"sofia/external/18612983191@192.168.180.37\",\"deviceId\":\"aadec6fe-70e1-44a9-a161-142fd111314e\",\"deviceType\":2,\"display\":\"\",\"endTime\":1627994679825,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627994486845,\"sipProtocol\":\"udp\",\"sipStatus\":\"\",\"talkTime\":192980}],\"callId\":210497283047817216,\"callTime\":1627994463135,\"callType\":\"INBOUND_CALL\",\"called\":\"01011515902\",\"calledDisplay\":\"\",\"caller\":\"18612983191\",\"callerDisplay\":\"\",\"companyId\":1,\"cts\":1627994463135,\"direction\":\"INBOUND\",\"endTime\":1627994679825,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":\"1\",\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"talkTime\":188200,\"taskId\":\"0\",\"uts\":1627994679825,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (6, 1627995113, 0, 1, 210498751016468480, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":3,\"answerFlag\":\"0\",\"answerTime\":1627994844985,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"\",\"answerTime\":1627994836845,\"bridgeTime\":1627994844985,\"callId\":210498751016468480,\"callTime\":1627994813123,\"called\":\"01011515902\",\"calledLocation\":\"\",\"caller\":\"18612983191\",\"callerLocation\":\"\",\"cdrType\":1,\"channelName\":\"sofia/external/18612983191@192.168.180.37\",\"deviceId\":\"cc866f0d-2b33-4e84-af29-038560734ff8\",\"deviceType\":2,\"display\":\"\",\"endTime\":1627995136885,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627994836845,\"sipProtocol\":\"udp\",\"sipStatus\":\"200\",\"talkTime\":300040},{\"agentKey\":\"1001@test\",\"answerTime\":1627994844885,\"bridgeTime\":1627994844985,\"callId\":210498751016468480,\"callTime\":1627994816883,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"3668672366082991\",\"deviceType\":1,\"display\":\"18612983191\",\"endTime\":1627995137145,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627994844885,\"ringStartTime\":1627994840925,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":292260}],\"callId\":210498751016468480,\"callTime\":1627994813123,\"callType\":\"INBOUND_CALL\",\"called\":\"01011515902\",\"calledDisplay\":\"\",\"caller\":\"18612983191\",\"callerDisplay\":\"\",\"companyId\":1,\"cts\":1627994813123,\"direction\":\"INBOUND\",\"endTime\":1627995137145,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":\"1\",\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"talkTime\":292160,\"taskId\":\"0\",\"uts\":1627995137145,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (7, 1627995345, 0, 1, 210500456688910336, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1627995253805,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1627995251425,\"bridgeTime\":1627995253805,\"callId\":210500456688910336,\"callTime\":1627995219784,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"1613724580010904\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1627995368745,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995251425,\"ringStartTime\":1627995244005,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":117320},{\"agentKey\":\"1001@test\",\"answerTime\":1627995253605,\"bridgeTime\":1627995253805,\"callId\":210500456688910336,\"callTime\":1627995228708,\"called\":\"14400010002\",\"calledLocation\":\"\",\"caller\":\"01088889999\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"4456350976672025\",\"deviceType\":2,\"display\":\"14400010002\",\"endTime\":1627995369045,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995253605,\"ringStartTime\":1627995251565,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":115440}],\"callId\":210500456688910336,\"callTime\":1627995219784,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"cts\":1627995219784,\"direction\":\"OUTBOUND\",\"endTime\":1627995369045,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"/app/clpms/record/20210803/210500456688910336_871556590425001_14400010002.wav\",\"talkTime\":115240,\"taskId\":\"0\",\"uts\":1627995369045,\"uuid1\":\"123456\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (8, 1627995382, 0, 1, 210501027873423360, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":3,\"answerFlag\":\"0\",\"answerTime\":1627995386265,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"\",\"answerTime\":1627995379685,\"bridgeTime\":1627995386265,\"callId\":210501027873423360,\"callTime\":1627995355965,\"called\":\"01011515902\",\"calledLocation\":\"\",\"caller\":\"18612983191\",\"callerLocation\":\"\",\"cdrType\":1,\"channelName\":\"sofia/external/18612983191@192.168.180.37\",\"deviceId\":\"013ce278-8cb5-46f9-a19e-1b6b65e06897\",\"deviceType\":2,\"display\":\"\",\"endTime\":1627995406045,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995379685,\"sipProtocol\":\"udp\",\"sipStatus\":\"\",\"talkTime\":26360},{\"agentKey\":\"1001@test\",\"answerTime\":1627995386185,\"bridgeTime\":1627995386265,\"callId\":210501027873423360,\"callTime\":1627995356099,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"5564290161630943\",\"deviceType\":1,\"display\":\"18612983191\",\"endTime\":1627995405865,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995386185,\"ringStartTime\":1627995381625,\"sipProtocol\":\"\",\"sipStatus\":\"200\",\"talkTime\":19680}],\"callId\":210501027873423360,\"callTime\":1627995355965,\"callType\":\"INBOUND_CALL\",\"called\":\"01011515902\",\"calledDisplay\":\"\",\"caller\":\"18612983191\",\"callerDisplay\":\"\",\"companyId\":1,\"cts\":1627995355965,\"direction\":\"INBOUND\",\"endTime\":1627995406045,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":\"1\",\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"talkTime\":19780,\"taskId\":\"0\",\"uts\":1627995406045,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (9, 1627995471, 0, 1, 210501432476958720, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":3,\"answerFlag\":\"0\",\"answerTime\":1627995484305,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"\",\"answerTime\":1627995476145,\"bridgeTime\":1627995484305,\"callId\":210501432476958720,\"callTime\":1627995452431,\"called\":\"01011515902\",\"calledLocation\":\"\",\"caller\":\"18612983191\",\"callerLocation\":\"\",\"cdrType\":1,\"channelName\":\"sofia/external/18612983191@192.168.180.37\",\"deviceId\":\"b18e3b10-411c-4851-a943-e1b8bb7efa01\",\"deviceType\":2,\"display\":\"\",\"endTime\":1627995494485,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995476145,\"sipProtocol\":\"udp\",\"sipStatus\":\"\",\"talkTime\":18340},{\"agentKey\":\"1001@test\",\"answerTime\":1627995484205,\"bridgeTime\":1627995484305,\"callId\":210501432476958720,\"callTime\":1627995452538,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"1018788979622682\",\"deviceType\":1,\"display\":\"18612983191\",\"endTime\":1627995494265,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995484205,\"ringStartTime\":1627995476805,\"sipProtocol\":\"\",\"sipStatus\":\"200\",\"talkTime\":10060}],\"callId\":210501432476958720,\"callTime\":1627995452431,\"callType\":\"INBOUND_CALL\",\"called\":\"01011515902\",\"calledDisplay\":\"\",\"caller\":\"18612983191\",\"callerDisplay\":\"\",\"companyId\":1,\"cts\":1627995452431,\"direction\":\"INBOUND\",\"endTime\":1627995494485,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":\"1\",\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"talkTime\":10180,\"taskId\":\"0\",\"uts\":1627995494485,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (10, 1627995595, 0, 1, 210501796244750336, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1002@test\",\"answerCount\":2,\"answerFlag\":3,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1002@test\",\"callId\":210501796244750336,\"callTime\":1627995539227,\"called\":\"871556519788001\",\"calledLocation\":\"\",\"caller\":\"1002\",\"callerLocation\":\"\",\"channelName\":\"sofia/external/871556519788001@172.17.0.2:6685\",\"deviceId\":\"8781538151494795\",\"deviceType\":1,\"display\":\"18612983191\",\"endTime\":1627995593665,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"RECOVERY_ON_TIMER_EXPIRE\",\"ringCause\":\"\",\"ringStartTime\":1627995563205,\"sipProtocol\":\"\",\"sipStatus\":\"408\"},{\"agentKey\":\"\",\"answerTime\":1627995562865,\"callId\":210501796244750336,\"callTime\":1627995539159,\"called\":\"01011515902\",\"calledLocation\":\"\",\"caller\":\"18612983191\",\"callerLocation\":\"\",\"cdrType\":1,\"channelName\":\"sofia/external/18612983191@192.168.180.37\",\"deviceId\":\"0f4a770d-65c0-4907-bbf9-125e7da95e19\",\"deviceType\":2,\"display\":\"\",\"endTime\":1627995618385,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995562865,\"sipProtocol\":\"udp\",\"sipStatus\":\"200\",\"talkTime\":55520}],\"callId\":210501796244750336,\"callTime\":1627995539159,\"callType\":\"INBOUND_CALL\",\"called\":\"01011515902\",\"calledDisplay\":\"\",\"caller\":\"18612983191\",\"callerDisplay\":\"\",\"companyId\":1,\"cts\":1627995539159,\"direction\":\"INBOUND\",\"endTime\":1627995618385,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"RECOVERY_ON_TIMER_EXPIRE\",\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":\"1\",\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"talkTime\":\"0\",\"taskId\":\"0\",\"uts\":1627995618385,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (11, 1627995623, 0, 1, 210502051824664576, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1002@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1627995634885,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1002@test\",\"answerTime\":1627995634726,\"bridgeTime\":1627995634885,\"callId\":210502051824664576,\"callTime\":1627995609594,\"called\":\"14400010002\",\"calledLocation\":\"\",\"caller\":\"01088889999\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"4139239056029410\",\"deviceType\":2,\"display\":\"14400010002\",\"endTime\":1627995647205,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995634726,\"ringStartTime\":1627995632725,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":12479},{\"agentKey\":\"1002@test\",\"answerTime\":1627995632605,\"bridgeTime\":1627995634885,\"callId\":210502051824664576,\"callTime\":1627995600094,\"called\":\"871556519788001\",\"calledLocation\":\"\",\"caller\":\"1002\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556519788001@172.17.0.2:6685\",\"deviceId\":\"3102515140512459\",\"deviceType\":1,\"display\":\"1002\",\"endTime\":1627995646965,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995632605,\"ringStartTime\":1627995625225,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":14360}],\"callId\":210502051824664576,\"callTime\":1627995600094,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556519788001\",\"callerDisplay\":\"1002\",\"companyId\":1,\"cts\":1627995600094,\"direction\":\"OUTBOUND\",\"endTime\":1627995647205,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"/app/clpms/record/20210803/210502051824664576_871556519788001_14400010002.wav\",\"talkTime\":12320,\"taskId\":\"0\",\"uts\":1627995647205,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
INSERT INTO `cc_push_fail_log` VALUES (12, 1627995683, 0, 1, 210502276266065920, 1, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1002@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1627995687405,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1002@test\",\"answerTime\":1627995685005,\"bridgeTime\":1627995687405,\"callId\":210502276266065920,\"callTime\":1627995653605,\"called\":\"871556519788001\",\"calledLocation\":\"\",\"caller\":\"1002\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556519788001@172.17.0.2:6685\",\"deviceId\":\"2429173174569363\",\"deviceType\":1,\"display\":\"1002\",\"endTime\":1627995706645,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995685005,\"ringStartTime\":1627995677605,\"sipProtocol\":\"\",\"sipStatus\":\"200\",\"talkTime\":21640},{\"agentKey\":\"1002@test\",\"answerTime\":1627995687165,\"bridgeTime\":1627995687405,\"callId\":210502276266065920,\"callTime\":1627995662160,\"called\":\"14400010002\",\"calledLocation\":\"\",\"caller\":\"01088889999\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"3859403297110954\",\"deviceType\":2,\"display\":\"14400010002\",\"endTime\":1627995706885,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"ringCause\":\"\",\"ringEndTime\":1627995687165,\"ringStartTime\":1627995685145,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":19720}],\"callId\":210502276266065920,\"callTime\":1627995653605,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556519788001\",\"callerDisplay\":\"1002\",\"companyId\":1,\"cts\":1627995653605,\"direction\":\"OUTBOUND\",\"endTime\":1627995706885,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupCause\":\"NORMAL_CLEARING\",\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"172.17.0.2\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"/app/clpms/record/20210803/210502276266065920_871556519788001_14400010002.wav\",\"talkTime\":19480,\"taskId\":\"0\",\"uts\":1627995706885,\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_call
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_call`;
CREATE TABLE `cc_route_call` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属企业',
  `route_group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属路由组',
  `route_num` varchar(32) NOT NULL DEFAULT '' COMMENT '字冠号码',
  `num_max` int(11) NOT NULL DEFAULT '0' COMMENT '最长',
  `num_min` int(11) NOT NULL DEFAULT '0' COMMENT '最短',
  `caller_change` int(11) NOT NULL DEFAULT '0' COMMENT '主叫替换规则',
  `caller_change_num` varchar(32) NOT NULL DEFAULT '' COMMENT '替换号码',
  `called_change` int(11) NOT NULL DEFAULT '0' COMMENT '被叫替换规则',
  `called_change_num` varchar(32) NOT NULL DEFAULT '' COMMENT '替换号码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `unq_idx_route` (`company_id`,`route_num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='字冠路由表';

-- ----------------------------
-- Records of cc_route_call
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_call` VALUES (1, 1, 1, 1, 1, '87', 9, 4, 0, '1', 1, '1', 1);
INSERT INTO `cc_route_call` VALUES (2, 1, 1, 1, 2, '133', 9, 4, 0, '1 ', 1, '1', 1);
INSERT INTO `cc_route_call` VALUES (4, 1, 1, 1, 1, '18899998889', 9, 4, 0, '1', 1, '1', 1);
INSERT INTO `cc_route_call` VALUES (7, 1, 1, 1, 4, '18899998887', 9, 4, 0, '1', 0, '1', 1);
INSERT INTO `cc_route_call` VALUES (8, 1, 1, 1, 5, '144', 11, 4, 0, '1', 0, '', 1);
INSERT INTO `cc_route_call` VALUES (9, 1621915277, 1621923862, 1, 2, '18988889999', 32, 2, 0, '1', 0, '1', 0);
INSERT INTO `cc_route_call` VALUES (10, 1621923239, 0, 1, 1, '1889999888999', 9, 4, 0, '1', 1, '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_getway
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_getway`;
CREATE TABLE `cc_route_getway` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '号码',
  `media_host` varchar(255) NOT NULL DEFAULT '' COMMENT '媒体地址',
  `media_port` int(11) NOT NULL DEFAULT '0' COMMENT '媒体端口',
  `caller_prefix` varchar(255) NOT NULL DEFAULT '' COMMENT '主叫号码前缀',
  `called_prefix` varchar(255) NOT NULL DEFAULT '' COMMENT '被叫号码前缀',
  `sip_header1` varchar(255) NOT NULL DEFAULT '' COMMENT 'sip头1',
  `sip_header2` varchar(255) NOT NULL DEFAULT '' COMMENT 'sip头2',
  `sip_header3` varchar(255) NOT NULL DEFAULT '' COMMENT 'sip头3',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uindex_getway_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='媒体网关表';

-- ----------------------------
-- Records of cc_route_getway
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_getway` VALUES (1, 1604503580, 1604503580, '87', '172.17.0.2', 6685, '', '', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (2, 1604503580, 1604503580, '133', '192.168.180.37', 5080, '', '', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (3, 1604503580, 1604503580, 'auto87', '192.168.180.226', 38915, '', '', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (5, 1604503580, 1621856137, '1441', '172.17.0.2', 32460, '', '', 'P_CALL_ID:A4', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (14, 1621850816, 0, '183clpss', '192.168.177.183', 1111, '', '', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (16, 1621851435, 1621851612, '183clps1s-delRxNYpt', '192.168.177.183-deljefAWM', 1111, '', '', '', '', '', 0);
INSERT INTO `cc_route_getway` VALUES (17, 1621856177, 0, '183clps1s', '192.168.177.183', 1111, '', '', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (21, 1621921932, 0, '183clps1s2', '192.168.177.183', 11111, '', '', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (22, 1627392056, 0, '183clpsa1s2', '192.168.177.183', 11111, '', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_getway_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_getway_group`;
CREATE TABLE `cc_route_getway_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `getway_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '媒体网关',
  `route_group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '网关组',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='路由字冠关联组表';

-- ----------------------------
-- Records of cc_route_getway_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_getway_group` VALUES (1, 1, 1, 1, 1);
INSERT INTO `cc_route_getway_group` VALUES (2, 1, 1, 2, 2);
INSERT INTO `cc_route_getway_group` VALUES (3, 1, 1, 5, 3);
INSERT INTO `cc_route_getway_group` VALUES (4, 1, 1, 7, 4);
INSERT INTO `cc_route_getway_group` VALUES (5, 1, 1, 5, 5);
INSERT INTO `cc_route_getway_group` VALUES (9, 1622004752, 0, 1, 7);
INSERT INTO `cc_route_getway_group` VALUES (10, 1622004752, 0, 2, 7);
INSERT INTO `cc_route_getway_group` VALUES (11, 1622004752, 0, 3, 7);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_group`;
CREATE TABLE `cc_route_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `route_group` varchar(255) NOT NULL DEFAULT '' COMMENT '网关组',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='网关组';

-- ----------------------------
-- Records of cc_route_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_group` VALUES (1, 1, 1, '87路由', 1);
INSERT INTO `cc_route_group` VALUES (2, 1, 1, '133路由', 1);
INSERT INTO `cc_route_group` VALUES (3, 1, 1, '871556590425003', 1);
INSERT INTO `cc_route_group` VALUES (4, 1, 1, '18899998887', 1);
INSERT INTO `cc_route_group` VALUES (5, 1, 1, '144路由', 1);
INSERT INTO `cc_route_group` VALUES (7, 0, 1622004752, '网关组2', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_schedule_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_schedule_config`;
CREATE TABLE `cc_schedule_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '日程名称',
  `level_value` int(11) NOT NULL DEFAULT '1' COMMENT '优先级',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '1:指定时间,2:相对时间',
  `start_day` varchar(20) NOT NULL DEFAULT '' COMMENT '开始日期',
  `end_day` varchar(20) NOT NULL DEFAULT '' COMMENT '结束日期',
  `start_time` varchar(20) NOT NULL DEFAULT '' COMMENT '开始时间',
  `end_time` varchar(20) NOT NULL DEFAULT '' COMMENT '结束时间',
  `mon` int(11) NOT NULL DEFAULT '1' COMMENT '周一',
  `tue` int(11) NOT NULL DEFAULT '1' COMMENT '周二',
  `wed` int(11) NOT NULL DEFAULT '1' COMMENT '周三',
  `thu` int(11) NOT NULL DEFAULT '1' COMMENT '周四',
  `fri` int(11) NOT NULL DEFAULT '1' COMMENT '周五',
  `sat` int(11) NOT NULL DEFAULT '1' COMMENT '周六',
  `sun` int(11) NOT NULL DEFAULT '1' COMMENT '周天',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='日程表';

-- ----------------------------
-- Records of cc_schedule_config
-- ----------------------------
BEGIN;
INSERT INTO `cc_schedule_config` VALUES (1, 1, 1, 1, '全天日程', 3, 1, '2020-12-01', '2020-12-30', '', '', 1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO `cc_schedule_config` VALUES (2, 2, 2, 1, '上班时间', 2, 1, '2020-12-01', '2020-12-30', '09:00:00', '18:00:00', 1, 1, 1, 1, 1, 0, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_skill
-- ----------------------------
DROP TABLE IF EXISTS `cc_skill`;
CREATE TABLE `cc_skill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='技能表';

-- ----------------------------
-- Records of cc_skill
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_skill_agent
-- ----------------------------
DROP TABLE IF EXISTS `cc_skill_agent`;
CREATE TABLE `cc_skill_agent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `skill_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能id',
  `agent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '坐席id',
  `rank_value` int(11) NOT NULL DEFAULT '0' COMMENT '范围',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='坐席技能表';

-- ----------------------------
-- Records of cc_skill_agent
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_skill_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_skill_group`;
CREATE TABLE `cc_skill_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `skill_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能ID',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `rank_about` int(11) NOT NULL DEFAULT '1' COMMENT '等级类型(1:全部,2:等于,3:>,4:<,5:介于)',
  `rank_value_start` int(11) NOT NULL DEFAULT '0' COMMENT '介于的开始值',
  `rank_value` int(11) NOT NULL DEFAULT '1' COMMENT '等级值',
  `rate` int(11) NOT NULL DEFAULT '100' COMMENT '占用率',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_group_id` (`group_id`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='技能组技能表';

-- ----------------------------
-- Records of cc_skill_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_code
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_code`;
CREATE TABLE `cc_vdn_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT 'vdn名称',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='呼入路由表';

-- ----------------------------
-- Records of cc_vdn_code
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_code` VALUES (3, 3, 3, 1, '01011515901', 1);
INSERT INTO `cc_vdn_code` VALUES (4, 4, 4, 1, '01011515902', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_phone
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_phone`;
CREATE TABLE `cc_vdn_phone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `vdn_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '路由码',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '特服号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_phone` (`vdn_id`,`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='路由号码表';

-- ----------------------------
-- Records of cc_vdn_phone
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_phone` VALUES (3, 1, 1, 1, 3, '01011515901', 1);
INSERT INTO `cc_vdn_phone` VALUES (4, 1, 1, 1, 4, '01011515902', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_schedule
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_schedule`;
CREATE TABLE `cc_vdn_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '子码日程',
  `vdn_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'vdn_id',
  `schedule_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '日程id',
  `route_type` int(11) NOT NULL DEFAULT '1' COMMENT '路由类型(1:技能组,2:放音,3:ivr,4:坐席,5:外呼)',
  `route_value` varchar(255) NOT NULL DEFAULT '0' COMMENT '路由类型值',
  `play_type` int(11) NOT NULL DEFAULT '0' COMMENT '放音类型(1:按键导航,2:技能组,3:ivr,4:路由字码,5:挂机)',
  `play_value` bigint(20) NOT NULL DEFAULT '0' COMMENT '放音类型对应值',
  `dtmf_end` varchar(255) NOT NULL DEFAULT '*' COMMENT '结束音',
  `retry` int(11) NOT NULL DEFAULT '1' COMMENT '重复播放次数',
  `dtmf_max` int(11) NOT NULL DEFAULT '1' COMMENT '最大收键长度',
  `dtmf_min` int(11) NOT NULL DEFAULT '1' COMMENT '最小收键长度',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='路由字码表';

-- ----------------------------
-- Records of cc_vdn_schedule
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_schedule` VALUES (1, 1, 1, 1, '呼入转技能组', 1, 1, 1, '1', 1, 0, '*', 1, 1, 1, 1);
INSERT INTO `cc_vdn_schedule` VALUES (2, 2, 2, 1, '上班转技能组', 2, 1, 1, '1', 1, 0, '*', 1, 1, 1, 1);
INSERT INTO `cc_vdn_schedule` VALUES (3, 3, 3, 1, '按键导航', 2, 2, 2, '1', 1, 1, '*', 1, 1, 1, 1);
INSERT INTO `cc_vdn_schedule` VALUES (4, 4, 4, 1, '按键导航', 3, 1, 2, '1', 1, 1, '*', 1, 1, 1, 1);
INSERT INTO `cc_vdn_schedule` VALUES (5, 5, 5, 1, '进技能组', 4, 1, 1, '1', 1, 1, '*', 1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_schedule_dtmf
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_schedule_dtmf`;
CREATE TABLE `cc_vdn_schedule_dtmf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业ID',
  `navigate_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '按键导航ID',
  `dtmf` varchar(20) NOT NULL DEFAULT '1' COMMENT '按键',
  `route_type` int(11) NOT NULL DEFAULT '0' COMMENT '路由类型(1:技能组,2:IVR,3:路由字码,4:坐席分机,5:挂机)',
  `route_value` bigint(20) NOT NULL DEFAULT '0' COMMENT '路由值',
  `status` int(11) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='按键导航表';

-- ----------------------------
-- Records of cc_vdn_schedule_dtmf
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ruid` varchar(64) NOT NULL DEFAULT '',
  `username` varchar(64) NOT NULL DEFAULT '',
  `domain` varchar(64) DEFAULT NULL,
  `contact` varchar(255) NOT NULL DEFAULT '',
  `received` varchar(128) DEFAULT NULL,
  `path` varchar(512) DEFAULT NULL,
  `expires` datetime NOT NULL DEFAULT '2030-05-28 21:32:15',
  `q` float(10,2) NOT NULL DEFAULT '1.00',
  `callid` varchar(255) NOT NULL DEFAULT 'Default-Call-ID',
  `cseq` int(11) NOT NULL DEFAULT '1',
  `last_modified` datetime NOT NULL DEFAULT '1900-01-01 00:00:01',
  `flags` int(11) NOT NULL DEFAULT '0',
  `cflags` int(11) NOT NULL DEFAULT '0',
  `user_agent` varchar(255) NOT NULL DEFAULT '',
  `socket` varchar(64) DEFAULT NULL,
  `methods` int(11) DEFAULT NULL,
  `instance` varchar(255) DEFAULT NULL,
  `reg_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ruid_idx` (`ruid`) USING BTREE,
  KEY `account_contact_idx` (`username`,`domain`,`contact`) USING BTREE,
  KEY `expires_idx` (`expires`) USING BTREE,
  KEY `IDX_CA_CO_US` (`callid`,`contact`,`username`) USING BTREE,
  KEY `IDX_CONTACT` (`contact`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=718 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location
-- ----------------------------
BEGIN;
INSERT INTO `location` VALUES (1, '1', '871552904110001', NULL, '', NULL, NULL, '2030-05-28 21:32:15', 1.00, 'Default-Call-ID', 1, '1900-01-01 00:00:01', 0, 0, '', NULL, NULL, NULL, 0);
INSERT INTO `location` VALUES (684, 'uloc-5d706c09-ff3-3c', '8715628449220015', NULL, 'sip:8715628449220015@192.168.183.40:5060', 'sip:192.168.183.40:5060', NULL, '2019-10-25 20:07:55', -1.00, '0_3733794137@192.168.183.40', 870, '2019-10-25 19:07:55', 0, 0, 'Yealink SIP-T21P_E2 52.81.0.75', 'udp:192.168.183.145:8880', 16383, NULL, 0);
INSERT INTO `location` VALUES (714, 'uloc-5d706c09-ff4-7c', '871556590425000', NULL, 'sip:871556590425000@192.168.181.178:58312;rinstance=8bec30a11263681e', 'sip:192.168.181.178:58312', NULL, '2019-10-25 20:02:47', -1.00, '99140ZjZiYTk1M2Q5MGRkZTlkZWQ0MDNmYWI0ZWY3MmFmYTA', 26, '2019-10-25 19:02:47', 0, 0, 'X-Lite release 5.6.1 stamp 99140', 'udp:192.168.183.145:8880', 5087, NULL, 0);
INSERT INTO `location` VALUES (717, 'uloc-5d706c09-ff4-dc', '8715628449220001', NULL, 'sip:8715628449220001@192.168.177.84:33192;rinstance=26174ed65ca23022', 'sip:192.168.177.84:33192', NULL, '2019-10-25 20:18:36', -1.00, 'ZDRhMzg5YTE4MTA0ZGQ2MDdhNmUyODg2MjQ4MmUyMWU.', 2, '2019-10-25 19:18:36', 0, 0, 'eyeBeam release 1011d stamp 40820', 'udp:192.168.183.145:8880', 5087, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for siu_users
-- ----------------------------
DROP TABLE IF EXISTS `siu_users`;
CREATE TABLE `siu_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) NOT NULL COMMENT '企业ID',
  `sip_id` varchar(50) NOT NULL COMMENT 'SIP账号',
  `sip_password` varchar(255) NOT NULL COMMENT 'SIP密码',
  `subaccound` varchar(255) DEFAULT NULL COMMENT '子帐号',
  `subtoken` varchar(255) DEFAULT NULL COMMENT '子帐号令牌',
  `bind_phoneid` int(255) DEFAULT NULL COMMENT '绑定的特服号ID',
  `ccsdirectcallflag` tinyint(4) DEFAULT '1' COMMENT 'ccs是否支持硬话机外呼（0：不允许；1：允许)',
  `sip_domain` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型：1 ivr，2 ccs',
  `stationno` int(11) DEFAULT NULL COMMENT '绑定IVR或CCS站点号',
  `status` int(11) DEFAULT '1' COMMENT '账号是否有效：0：无效；1：有效',
  `ctime` int(11) DEFAULT NULL,
  `utime` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sip_id` (`sip_id`) USING BTREE,
  KEY `sip_companyid` (`companyid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='SIP用户注册表';

-- ----------------------------
-- Records of siu_users
-- ----------------------------
BEGIN;
INSERT INTO `siu_users` VALUES (1, 131, '871556519788000', '39636c0a', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519788, 1556519788);
INSERT INTO `siu_users` VALUES (2, 131, '871556519788001', '123456', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519788, 1556519788);
INSERT INTO `siu_users` VALUES (3, 128, '871556519802000', 'a3047e51', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519802, 1556519802);
INSERT INTO `siu_users` VALUES (4, 128, '871556519802001', 'b8d61274', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519802, 1556519802);
INSERT INTO `siu_users` VALUES (5, 128, '871556519817000', '0cc81268', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (6, 128, '871556519817001', 'e52d569d', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (7, 128, '871556519817002', 'f9e73f02', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (8, 128, '871556519817003', '7b71b071', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (9, 128, '871556519817004', '5f92b4ac', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (10, 128, '871556519817005', 'c09c1d70', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (11, 128, '871556519817006', '2828c389', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (12, 128, '871556519817007', '517236c0', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (13, 128, '871556519817008', '20f65e5f', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1556519817);
INSERT INTO `siu_users` VALUES (14, 128, '871556519817009', '25aa0ee3', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519817, 1585216196);
INSERT INTO `siu_users` VALUES (15, 131, '871556519829000', '19c6f4e7', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (16, 131, '871556519829001', '7ce4410a', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (17, 131, '871556519829002', 'b5bae4c0', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (18, 131, '871556519829003', '5f7f0b1a', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (19, 131, '871556519829004', 'c7d629bf', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (20, 131, '871556519829005', '327c6fe9', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (21, 131, '871556519829006', '8a13b508', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (22, 131, '871556519829007', 'f55b8e2a', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (23, 131, '871556519829008', 'b280db86', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (24, 131, '871556519829009', '9a9b7444', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556519829, 1556519829);
INSERT INTO `siu_users` VALUES (25, 129, '871556590425000', '1026b6a8', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556590425, 1556590425);
INSERT INTO `siu_users` VALUES (26, 129, '871556590425001', 'c2677bde', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556590425, 1556590425);
INSERT INTO `siu_users` VALUES (27, 129, '871556590425002', '6d8c0f76', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556590425, 1556590425);
INSERT INTO `siu_users` VALUES (28, 129, '871556590425003', 'c3ab0406', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556590425, 1556590425);
INSERT INTO `siu_users` VALUES (29, 129, '871556590425004', '7e11b1d5', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556590425, 1556590425);
INSERT INTO `siu_users` VALUES (30, 134, '871556590973000', '7d2763e7', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1556590973, 1556590973);
COMMIT;

-- ----------------------------
-- Table structure for ss_station
-- ----------------------------
DROP TABLE IF EXISTS `ss_station`;
CREATE TABLE `ss_station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `station_no` int(11) NOT NULL COMMENT '站点号',
  `station_type` int(11) NOT NULL COMMENT '站点类型:2:MG;3:SS；4：CCS;5:IVR;6:AAA,7:DBA;8:OAMSERVICE;9:WEBSERVICE;10:PDS;11:DIALER;12:SCHEDULE;13:CTISERVER;14:FILESERVER;15:APISERVER;16:statservice;17:TTS;18:cti录音模块.19：pdsmanage模块,20:NLP;21:ccsproxy;22:pushserver;100:ACD;101:newIVR;102clpss；103：crontab',
  `ipaddr` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `ipport` int(11) DEFAULT NULL COMMENT 'IP端口',
  `redundance_ip` varchar(255) DEFAULT NULL COMMENT '冗余站点IP地址',
  `redundance_port` int(11) DEFAULT NULL COMMENT '冗余站点端口',
  `ipaddr_bak` varchar(20) DEFAULT NULL COMMENT '备用的IP，连接OAM的ws用',
  `ipport_bak` int(5) DEFAULT NULL COMMENT '备用的端口，连接OAM的ws用 ccs开放pdstcp端口',
  `ivrflowpath` varchar(255) DEFAULT NULL COMMENT 'IVR站点流程路径',
  `validurl` varchar(255) DEFAULT NULL COMMENT '站点监听的有效URL（此字段为空，将以IP地址字段和Port字段拼出该站点URL）',
  `redundance_validurl` varchar(255) DEFAULT NULL COMMENT '站点监听的冗余有效URL（此字段为空，将以IP地址字段和Port字段拼出该站点URL）',
  `ccswebsocketurl` varchar(255) DEFAULT NULL COMMENT 'CCS websocket URL',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `socketport` int(11) DEFAULT '0' COMMENT 'OAM，SS监听的TCP端口',
  `ss_media_ip` varchar(32) DEFAULT NULL COMMENT 'SS媒体服务器地址',
  `ss_media_port` int(11) DEFAULT NULL COMMENT 'SS媒体服务器端口',
  `ss_media_playpath` varchar(255) DEFAULT NULL COMMENT 'SS对应的媒体服务器放音路径',
  `ss_media_recordpath` varchar(255) DEFAULT NULL COMMENT 'SS对应的媒体服务器录音路径',
  `ss_media_fileserverport` int(11) DEFAULT NULL COMMENT 'ss对应的媒体服务器文件接收端口或IVR文件接收端口',
  `register_ip` varchar(255) DEFAULT NULL COMMENT 'SIP注册服务器地址',
  `register_port` int(11) DEFAULT NULL COMMENT 'SIP注册服务器端口',
  `cti_linkid` varchar(100) DEFAULT '' COMMENT 'CTI连接ID',
  `cti_userid` varchar(100) DEFAULT '' COMMENT 'CTI用户ID',
  `cti_password` varchar(100) DEFAULT '' COMMENT 'CTI用户密码',
  `media_for_lan` int(11) DEFAULT '0' COMMENT 'MG是否用于内网，0代表本MG不用于内网，1代表本MG仅用于内网。默认是0',
  `register_ip_lan` varchar(255) DEFAULT '' COMMENT '当MG用于内网的时候，注册的IP地址。',
  `register_port_lan` int(11) DEFAULT '0' COMMENT '当MG用于内网的时候，注册的端口',
  `tts_path` varchar(255) DEFAULT '' COMMENT '下载tts的wav文件存放的路径',
  `tts_gateway_ip` varchar(255) DEFAULT '' COMMENT 'tts网关url',
  `record_auth` varchar(255) DEFAULT '' COMMENT 'cti录音模块的auth',
  `is_leader` tinyint(4) DEFAULT '0' COMMENT '站点是主备：1:主:，2:被，0: 默认没有含义',
  `station_group` int(11) DEFAULT '0' COMMENT '站点分组概念主要服务于双中心。pds只加载同一个组里的任务',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `memo` (`memo`) USING BTREE,
  KEY `index_no` (`station_no`) USING BTREE,
  KEY `prim_ip_index` (`ipaddr`) USING BTREE,
  KEY `redundance_ip_index` (`redundance_ip`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8 COMMENT='站点信息表';

-- ----------------------------
-- Records of ss_station
-- ----------------------------
BEGIN;
INSERT INTO `ss_station` VALUES (2, 2, 101, '114.215.196.183', 8066, '', 0, '', 0, '', 'http://114.215.196.183:8066/cc-ivr/', '', '', 'ivr', 6869, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '/app/clpms/sounds/', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (7, 7, 7, '114.215.196.183', 38080, '', 0, '', 0, '', 'http://114.215.196.183:38080/', '', '', 'dba', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (8, 8, 6, '114.215.196.183', 28080, '', 0, '', 0, '', 'http://114.215.196.183:28080/aaa/service/', '', '', 'aaa', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (10, 10, 10, '114.215.196.183', 48080, '', 0, '', 0, '', 'http://114.215.196.183:48080', '', '', 'pds', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 1);
INSERT INTO `ss_station` VALUES (11, 12, 4, '114.215.196.183', 8084, '', 0, '', 6868, '', 'http://114.215.196.183:8084/ccs', '', 'wss://ccdev.acloudcc.com:9090/ws', 'ccs', 19090, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (13, 13, 104, '114.215.196.183', 8188, '', 0, '', 0, '', '', '', '', '测试RTC网关', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (14, 14, 8, '114.215.196.183', 9999, '', 0, '', 0, '', '', '', 'wss://ccdev.acloudcc.com/oam/ws', 'oam', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (16, 16, 14, '114.215.196.183', 58080, '', 0, '', 0, '', 'http://114.215.196.183:58080/fileserver', '', '', 'fileserver', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (18, 18, 103, '114.215.196.183', 9195, '', 0, '', 0, '', 'http://114.215.196.183:9195', '', '', 'crontab', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (19, 19, 16, '114.215.196.183', 8380, '', 0, '', 0, '', 'http://114.215.196.183:8380', '', '', 'stat', 28802, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 1, 0);
INSERT INTO `ss_station` VALUES (21, 21, 102, '114.215.196.183', 8880, '', 0, '', 0, '', '', '', '', 'clpss', 0, '', 0, '', '', 0, '114.215.196.183', 8880, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (22, 22, 100, '114.215.196.183', 8083, '', 0, '', 0, '', 'http://114.215.196.183:8083/acd', '', '', 'acd', 6001, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (23, 23, 15, '114.215.196.183', 58113, '', 0, '', 0, '', 'http://114.215.196.183:58113', '', '', 'api', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (24, 24, 22, '114.215.196.183', 8123, '', 0, '', 0, '', 'http://192.168.177.183:58123', '', '', 'push', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (25, 25, 3, '114.215.196.183', 9910, '', 0, '', 0, '', 'http://114.215.196.183:9910', '', '', 'SS', 9977, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (195, 1, 2, '114.215.196.183', 38025, '', 0, '', 0, '', '', '', '', 'MG', 0, '', 0, '/app/clpms/sounds/', '/app/clpms/record/', 58080, '114.215.196.183', 8880, '', '', '', 0, '', 0, '', '', '', 0, 0);
INSERT INTO `ss_station` VALUES (197, 15, 9, '114.215.196.183', 18080, '', 0, '', 0, '', 'http://114.215.196.183:18080/3cs', '', '', 'cccs', 0, '', 0, '', '', 0, '', 0, '', '', '', 0, '', 0, '', '', '', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for ss_stationrelation
-- ----------------------------
DROP TABLE IF EXISTS `ss_stationrelation`;
CREATE TABLE `ss_stationrelation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '站点关联表',
  `stationno` int(11) DEFAULT NULL COMMENT '站点号',
  `peer_no` int(11) DEFAULT NULL COMMENT '关联站点号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index_station_peer` (`stationno`,`peer_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8 COMMENT='站点关联表';

-- ----------------------------
-- Records of ss_stationrelation
-- ----------------------------
BEGIN;
INSERT INTO `ss_stationrelation` VALUES (245, 1, 25);
INSERT INTO `ss_stationrelation` VALUES (188, 7, 87);
INSERT INTO `ss_stationrelation` VALUES (258, 8, 25);
INSERT INTO `ss_stationrelation` VALUES (46, 10, 11);
INSERT INTO `ss_stationrelation` VALUES (32, 10, 201);
INSERT INTO `ss_stationrelation` VALUES (45, 11, 10);
INSERT INTO `ss_stationrelation` VALUES (249, 11, 15);
INSERT INTO `ss_stationrelation` VALUES (30, 11, 201);
INSERT INTO `ss_stationrelation` VALUES (201, 11, 213);
INSERT INTO `ss_stationrelation` VALUES (248, 15, 11);
INSERT INTO `ss_stationrelation` VALUES (259, 15, 12);
INSERT INTO `ss_stationrelation` VALUES (251, 15, 13);
INSERT INTO `ss_stationrelation` VALUES (246, 15, 21);
INSERT INTO `ss_stationrelation` VALUES (247, 21, 15);
INSERT INTO `ss_stationrelation` VALUES (244, 25, 1);
INSERT INTO `ss_stationrelation` VALUES (257, 25, 8);
INSERT INTO `ss_stationrelation` VALUES (210, 61, 87);
INSERT INTO `ss_stationrelation` VALUES (234, 61, 215);
INSERT INTO `ss_stationrelation` VALUES (187, 87, 7);
INSERT INTO `ss_stationrelation` VALUES (232, 87, 215);
INSERT INTO `ss_stationrelation` VALUES (29, 201, 11);
INSERT INTO `ss_stationrelation` VALUES (224, 213, 11);
INSERT INTO `ss_stationrelation` VALUES (204, 213, 666);
INSERT INTO `ss_stationrelation` VALUES (235, 215, 61);
INSERT INTO `ss_stationrelation` VALUES (205, 666, 213);
COMMIT;

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `table_name` varchar(32) NOT NULL,
  `table_version` int(10) unsigned NOT NULL DEFAULT '0',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  UNIQUE KEY `table_name_idx` (`table_name`) USING BTREE,
  KEY `idx_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of version
-- ----------------------------
BEGIN;
INSERT INTO `version` VALUES ('location', 1013, '2020-10-25 23:03:05');
INSERT INTO `version` VALUES ('siu_users', 7, '2019-09-07 11:19:57');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
