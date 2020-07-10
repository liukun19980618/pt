CREATE DATABASE IF NOT EXISTS`pt` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;

USE `pt`;

/*认证服务器客户端*/
DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` VARCHAR(128) NOT NULL,
  `resource_ids` VARCHAR(256) DEFAULT NULL,
  `client_secret` VARCHAR(256) DEFAULT NULL,
  `scope` VARCHAR(256) DEFAULT NULL,
  `authorized_grant_types` VARCHAR(256) DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(256) DEFAULT NULL,
  `authorities` VARCHAR(256) DEFAULT NULL,
  `access_token_validity` INT(11) DEFAULT NULL,
  `refresh_token_validity` INT(11) DEFAULT NULL,
  `additional_information` VARCHAR(4096) DEFAULT NULL,
  `autoapprove` VARCHAR(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_details` */
/*密码：secret*/
INSERT  INTO `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`)
VALUES ('center',NULL,'$2a$10$NFa0OR/Pj8vPjYgr.5K6J.P6qffapWV/pbJss/QbBrO0GmMSOm3oS','all','authorization_code,refresh_token,password',NULL,NULL,3600,36000,NULL,'1'),
('dealer',NULL,'$2a$10$NFa0OR/Pj8vPjYgr.5K6J.P6qffapWV/pbJss/QbBrO0GmMSOm3oS','all','authorization_code,refresh_token,password',NULL,NULL,3600,36000,NULL,'1'),
('manor',NULL,'$2a$10$NFa0OR/Pj8vPjYgr.5K6J.P6qffapWV/pbJss/QbBrO0GmMSOm3oS','all','authorization_code,refresh_token,password',NULL,NULL,3600,36000,NULL,'1'),
('warehouse',NULL,'$2a$10$NFa0OR/Pj8vPjYgr.5K6J.P6qffapWV/pbJss/QbBrO0GmMSOm3oS','all','authorization_code,refresh_token,password',NULL,NULL,3600,36000,NULL,'1');

/*经销商部门表*/
DROP TABLE IF EXISTS `dealer_dept`;

CREATE TABLE `dealer_dept` (
                               `dept_id` varchar(32) NOT NULL COMMENT '部门id',
                               `parent_id` varchar(32) DEFAULT NULL COMMENT '父部门id',
                               `ancestors` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '祖级列表',
                               `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '部门名称',
                               `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
                               `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人',
                               `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
                               `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
                               `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
                               `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                               `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';



insert  into `dealer_dept`(`dept_id`,`parent_id`,`ancestors`,`dept_name`,`order_num`,`leader`,`phone`,`email`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values ('100','0','0','若依科技',0,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('101','100','0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('102','100','0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('103','101','0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('104','101','0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('105','101','0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('106','101','0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('107','101','0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('108','102','0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00'),('109','102','0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00');


/*经销商菜单表*/
DROP TABLE IF EXISTS `dealer_menu`;

CREATE TABLE `dealer_menu` (
                               `menu_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
                               `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
                               `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '父菜单ID',
                               `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
                               `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由地址',
                               `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
                               `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
                               `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
                               `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
                               `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
                               `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
                               `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '菜单图标',
                               `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
                               PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `dealer_menu` */

insert  into `dealer_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values ('1','系统管理','0',1,'/system','Layout',1,'M','0','0','','system','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统管理目录'),('100','用户管理','1',1,'user','system/user/index',1,'C','0','0','system:user:list','user','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','用户管理菜单'),('1001','用户查询','100',1,'','',1,'F','0','0','system:user:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1002','用户新增','100',2,'','',1,'F','0','0','system:user:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1003','用户修改','100',3,'','',1,'F','0','0','system:user:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1004','用户删除','100',4,'','',1,'F','0','0','system:user:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1005','用户导出','100',5,'','',1,'F','0','0','system:user:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1006','用户导入','100',6,'','',1,'F','0','0','system:user:import','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1007','重置密码','100',7,'','',1,'F','0','0','system:user:resetPwd','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1008','角色查询','101',1,'','',1,'F','0','0','system:role:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1009','角色新增','101',2,'','',1,'F','0','0','system:role:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('101','角色管理','1',2,'role','system/role/index',1,'C','0','0','system:role:list','peoples','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','角色管理菜单'),('1010','角色修改','101',3,'','',1,'F','0','0','system:role:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1011','角色删除','101',4,'','',1,'F','0','0','system:role:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1012','角色导出','101',5,'','',1,'F','0','0','system:role:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1013','菜单查询','102',1,'','',1,'F','0','0','system:menu:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1014','菜单新增','102',2,'','',1,'F','0','0','system:menu:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1015','菜单修改','102',3,'','',1,'F','0','0','system:menu:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1016','菜单删除','102',4,'','',1,'F','0','0','system:menu:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1017','部门查询','103',1,'','',1,'F','0','0','system:dept:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1018','部门新增','103',2,'','',1,'F','0','0','system:dept:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1019','部门修改','103',3,'','',1,'F','0','0','system:dept:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('102','菜单管理','1',3,'menu','system/menu/index',1,'C','0','0','system:menu:list','tree-table','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','菜单管理菜单'),('1020','部门删除','103',4,'','',1,'F','0','0','system:dept:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1021','岗位查询','104',1,'','',1,'F','0','0','system:post:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1022','岗位新增','104',2,'','',1,'F','0','0','system:post:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1023','岗位修改','104',3,'','',1,'F','0','0','system:post:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1024','岗位删除','104',4,'','',1,'F','0','0','system:post:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1025','岗位导出','104',5,'','',1,'F','0','0','system:post:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1026','字典查询','105',1,'#','',1,'F','0','0','system:dict:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1027','字典新增','105',2,'#','',1,'F','0','0','system:dict:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1028','字典修改','105',3,'#','',1,'F','0','0','system:dict:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1029','字典删除','105',4,'#','',1,'F','0','0','system:dict:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('103','部门管理','1',4,'dept','system/dept/index',1,'C','0','0','system:dept:list','tree','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','部门管理菜单'),('1030','字典导出','105',5,'#','',1,'F','0','0','system:dict:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1031','参数查询','106',1,'#','',1,'F','0','0','system:config:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1032','参数新增','106',2,'#','',1,'F','0','0','system:config:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1033','参数修改','106',3,'#','',1,'F','0','0','system:config:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1034','参数删除','106',4,'#','',1,'F','0','0','system:config:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1035','参数导出','106',5,'#','',1,'F','0','0','system:config:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1036','公告查询','107',1,'#','',1,'F','0','0','system:notice:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1037','公告新增','107',2,'#','',1,'F','0','0','system:notice:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1038','公告修改','107',3,'#','',1,'F','0','0','system:notice:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1039','公告删除','107',4,'#','',1,'F','0','0','system:notice:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('104','岗位管理','1',5,'post','system/post/index',1,'C','0','0','system:post:list','post','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','岗位管理菜单'),('1040','操作查询','500',1,'#','',1,'F','0','0','monitor:operlog:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1041','操作删除','500',2,'#','',1,'F','0','0','monitor:operlog:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1042','日志导出','500',4,'#','',1,'F','0','0','monitor:operlog:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1043','登录查询','501',1,'#','',1,'F','0','0','monitor:logininfor:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1044','登录删除','501',2,'#','',1,'F','0','0','monitor:logininfor:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1045','日志导出','501',3,'#','',1,'F','0','0','monitor:logininfor:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1046','在线查询','109',1,'#','',1,'F','0','0','monitor:online:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1047','批量强退','109',2,'#','',1,'F','0','0','monitor:online:batchLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1048','单条强退','109',3,'#','',1,'F','0','0','monitor:online:forceLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1049','任务查询','110',1,'#','',1,'F','0','0','monitor:job:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('105','字典管理','1',6,'dict','system/dict/index',1,'C','0','0','system:dict:list','dict','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','字典管理菜单'),('1050','任务新增','110',2,'#','',1,'F','0','0','monitor:job:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1051','任务修改','110',3,'#','',1,'F','0','0','monitor:job:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1052','任务删除','110',4,'#','',1,'F','0','0','monitor:job:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1053','状态修改','110',5,'#','',1,'F','0','0','monitor:job:changeStatus','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1054','任务导出','110',7,'#','',1,'F','0','0','monitor:job:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1055','生成查询','114',1,'#','',1,'F','0','0','tool:gen:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1056','生成修改','114',2,'#','',1,'F','0','0','tool:gen:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1057','生成删除','114',3,'#','',1,'F','0','0','tool:gen:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1058','导入代码','114',2,'#','',1,'F','0','0','tool:gen:import','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('1059','预览代码','114',4,'#','',1,'F','0','0','tool:gen:preview','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('106','参数设置','1',7,'config','system/config/index',1,'C','0','0','system:config:list','edit','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','参数设置菜单'),('1060','生成代码','114',5,'#','',1,'F','0','0','tool:gen:code','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('107','通知公告','1',8,'notice','system/notice/index',1,'C','0','0','system:notice:list','message','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知公告菜单'),('108','日志管理','1',9,'log','system/log/index',1,'M','0','0','','log','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','日志管理菜单'),('109','在线用户','2',1,'online','monitor/online/index',1,'C','0','0','monitor:online:list','online','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','在线用户菜单'),('110','定时任务','2',2,'job','monitor/job/index',1,'C','0','0','monitor:job:list','job','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','定时任务菜单'),('111','数据监控','2',3,'druid','monitor/druid/index',1,'C','0','0','monitor:druid:list','druid','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','数据监控菜单'),('112','服务监控','2',4,'server','monitor/server/index',1,'C','0','0','monitor:server:list','server','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','服务监控菜单'),('113','表单构建','3',1,'build','tool/build/index',1,'C','0','0','tool:build:list','build','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','表单构建菜单'),('114','代码生成','3',2,'gen','tool/gen/index',1,'C','0','0','tool:gen:list','code','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','代码生成菜单'),('115','系统接口','3',3,'swagger','tool/swagger/index',1,'C','0','0','tool:swagger:list','swagger','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统接口菜单'),('2','系统监控','0',2,'/monitor','Layout',1,'M','0','0','','monitor','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统监控目录'),('3','系统工具','0',3,'/tool','Layout',1,'M','0','0','','tool','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统工具目录'),('4','若依官网','0',4,'http://ruoyi.vip','Layout',0,'M','0','0','','guide','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','若依官网地址'),('500','操作日志','108',1,'operlog','monitor/operlog/index',1,'C','0','0','monitor:operlog:list','form','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','操作日志菜单'),('501','登录日志','108',2,'logininfor','monitor/logininfor/index',1,'C','0','0','monitor:logininfor:list','logininfor','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','登录日志菜单');

/*经销商角色表*/

DROP TABLE IF EXISTS `dealer_role`;

CREATE TABLE `dealer_role` (
                               `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
                               `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
                               `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色权限',
                               `role_sort` int(2) DEFAULT NULL COMMENT '显示顺序',
                               `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `status` char(1) DEFAULT NULL COMMENT '角色状态（0正常 1停用）',
                               `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标志',
                               PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dealer_role` */

insert  into `dealer_role`(`role_id`,`role_name`,`role_code`,`role_sort`,`create_by`,`create_time`,`status`,`del_flag`) values ('2dfde145251c4d2199dd1f4005cb0d0f','游客','VISITOR',1,'ADMIN','2020-06-05 00:00:00','0','0'),('a55a346ad8004deb9bffaeecf151e0f0','管理员','ADMIN',0,'ADMIN','2020-06-05 00:00:00','0','0');

/*经销商角色-菜单表*/

DROP TABLE IF EXISTS `dealer_role_menu`;

CREATE TABLE `dealer_role_menu` (
                                    `role_id` varchar(255) NOT NULL COMMENT '角色ID',
                                    `menu_id` varchar(255) NOT NULL COMMENT '菜单ID',
                                    PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Data for the table `dealer_role_menu` */

insert  into `dealer_role_menu`(`role_id`,`menu_id`) values ('2dfde145251c4d2199dd1f4005cb0d0f','100'),('a55a346ad8004deb9bffaeecf151e0f0','1'),('a55a346ad8004deb9bffaeecf151e0f0','100'),('a55a346ad8004deb9bffaeecf151e0f0','1001'),('a55a346ad8004deb9bffaeecf151e0f0','1002'),('a55a346ad8004deb9bffaeecf151e0f0','1003'),('a55a346ad8004deb9bffaeecf151e0f0','1004'),('a55a346ad8004deb9bffaeecf151e0f0','1005'),('a55a346ad8004deb9bffaeecf151e0f0','1006'),('a55a346ad8004deb9bffaeecf151e0f0','1007'),('a55a346ad8004deb9bffaeecf151e0f0','1008'),('a55a346ad8004deb9bffaeecf151e0f0','1009'),('a55a346ad8004deb9bffaeecf151e0f0','101'),('a55a346ad8004deb9bffaeecf151e0f0','1010'),('a55a346ad8004deb9bffaeecf151e0f0','1011'),('a55a346ad8004deb9bffaeecf151e0f0','1012'),('a55a346ad8004deb9bffaeecf151e0f0','1013'),('a55a346ad8004deb9bffaeecf151e0f0','1014'),('a55a346ad8004deb9bffaeecf151e0f0','1015'),('a55a346ad8004deb9bffaeecf151e0f0','1016'),('a55a346ad8004deb9bffaeecf151e0f0','1017'),('a55a346ad8004deb9bffaeecf151e0f0','1018'),('a55a346ad8004deb9bffaeecf151e0f0','1019'),('a55a346ad8004deb9bffaeecf151e0f0','102'),('a55a346ad8004deb9bffaeecf151e0f0','1020'),('a55a346ad8004deb9bffaeecf151e0f0','1021'),('a55a346ad8004deb9bffaeecf151e0f0','1022'),('a55a346ad8004deb9bffaeecf151e0f0','1023'),('a55a346ad8004deb9bffaeecf151e0f0','1024'),('a55a346ad8004deb9bffaeecf151e0f0','1025'),('a55a346ad8004deb9bffaeecf151e0f0','1026'),('a55a346ad8004deb9bffaeecf151e0f0','1027'),('a55a346ad8004deb9bffaeecf151e0f0','1028'),('a55a346ad8004deb9bffaeecf151e0f0','1029'),('a55a346ad8004deb9bffaeecf151e0f0','103'),('a55a346ad8004deb9bffaeecf151e0f0','1030'),('a55a346ad8004deb9bffaeecf151e0f0','1031'),('a55a346ad8004deb9bffaeecf151e0f0','1032'),('a55a346ad8004deb9bffaeecf151e0f0','1033'),('a55a346ad8004deb9bffaeecf151e0f0','1034'),('a55a346ad8004deb9bffaeecf151e0f0','1035'),('a55a346ad8004deb9bffaeecf151e0f0','1036'),('a55a346ad8004deb9bffaeecf151e0f0','1037'),('a55a346ad8004deb9bffaeecf151e0f0','1038'),('a55a346ad8004deb9bffaeecf151e0f0','1039'),('a55a346ad8004deb9bffaeecf151e0f0','104'),('a55a346ad8004deb9bffaeecf151e0f0','1040'),('a55a346ad8004deb9bffaeecf151e0f0','1041'),('a55a346ad8004deb9bffaeecf151e0f0','1042'),('a55a346ad8004deb9bffaeecf151e0f0','1043'),('a55a346ad8004deb9bffaeecf151e0f0','1044'),('a55a346ad8004deb9bffaeecf151e0f0','1045'),('a55a346ad8004deb9bffaeecf151e0f0','1046'),('a55a346ad8004deb9bffaeecf151e0f0','1047'),('a55a346ad8004deb9bffaeecf151e0f0','1048'),('a55a346ad8004deb9bffaeecf151e0f0','1049'),('a55a346ad8004deb9bffaeecf151e0f0','105'),('a55a346ad8004deb9bffaeecf151e0f0','1050'),('a55a346ad8004deb9bffaeecf151e0f0','1051'),('a55a346ad8004deb9bffaeecf151e0f0','1052'),('a55a346ad8004deb9bffaeecf151e0f0','1053'),('a55a346ad8004deb9bffaeecf151e0f0','1054'),('a55a346ad8004deb9bffaeecf151e0f0','1055'),('a55a346ad8004deb9bffaeecf151e0f0','1056'),('a55a346ad8004deb9bffaeecf151e0f0','1057'),('a55a346ad8004deb9bffaeecf151e0f0','1058'),('a55a346ad8004deb9bffaeecf151e0f0','1059'),('a55a346ad8004deb9bffaeecf151e0f0','106'),('a55a346ad8004deb9bffaeecf151e0f0','1060'),('a55a346ad8004deb9bffaeecf151e0f0','107'),('a55a346ad8004deb9bffaeecf151e0f0','108'),('a55a346ad8004deb9bffaeecf151e0f0','109'),('a55a346ad8004deb9bffaeecf151e0f0','110'),('a55a346ad8004deb9bffaeecf151e0f0','111'),('a55a346ad8004deb9bffaeecf151e0f0','112'),('a55a346ad8004deb9bffaeecf151e0f0','113'),('a55a346ad8004deb9bffaeecf151e0f0','114'),('a55a346ad8004deb9bffaeecf151e0f0','115'),('a55a346ad8004deb9bffaeecf151e0f0','2'),('a55a346ad8004deb9bffaeecf151e0f0','3'),('a55a346ad8004deb9bffaeecf151e0f0','4'),('a55a346ad8004deb9bffaeecf151e0f0','500'),('a55a346ad8004deb9bffaeecf151e0f0','501');

/*经销商用户表*/

DROP TABLE IF EXISTS `dealer_user`;

CREATE TABLE `dealer_user` (
                               `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                               `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门id',
                               `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户账号',
                               `nick_name` varchar(32) DEFAULT NULL COMMENT '用户昵称',
                               `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
                               `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
                               `phone` varchar(13) DEFAULT NULL COMMENT '用户手机号码',
                               `sex` char(1) DEFAULT '2' COMMENT '性别 0-男 1-女 2-未指定',
                               `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
                               `status` char(1) DEFAULT '0' COMMENT '状态 0-正常 1-停用',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标志 0-正常 1-已删除',
                               PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dealer_user` */

insert  into `dealer_user`(`user_id`,`dept_id`,`user_name`,`nick_name`,`password`,`email`,`phone`,`sex`,`avatar`,`status`,`create_time`,`del_flag`) values ('1','103','admin','管理员-王','$2a$10$mC3UHi0fywGB8oUlCXadz.Tx3QMhprJ5SCbDCZ0pPZOxMeBTNZ3ey','836591036@qq.com','13269997166','2',NULL,'0','2020-06-03 10:11:14','0'),('2','102','user','游客-王','$2a$10$mC3UHi0fywGB8oUlCXadz.Tx3QMhprJ5SCbDCZ0pPZOxMeBTNZ3ey',NULL,NULL,'2',NULL,'0','2020-06-03 10:11:16','0');

/*经销商用户-角色表*/

DROP TABLE IF EXISTS `dealer_user_role`;

CREATE TABLE `dealer_user_role` (
                                    `user_id` varchar(255) NOT NULL,
                                    `role_id` varchar(255) NOT NULL,
                                    PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dealer_user_role` */

insert  into `dealer_user_role`(`user_id`,`role_id`) values ('1','2dfde145251c4d2199dd1f4005cb0d0f'),('1','a55a346ad8004deb9bffaeecf151e0f0');

/*字典数据表*/

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
                                 `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
                                 `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
                                 `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
                                 `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
                                 `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
                                 `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                 `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
                                 `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
                                 `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`dict_code`,`dict_sort`,`dict_label`,`dict_value`,`dict_type`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,1,'男','0','sys_user_sex','','','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','性别女'),(3,3,'未知','2','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','关闭状态'),(18,1,'新增','1','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','新增操作'),(19,2,'修改','2','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','修改操作'),(20,3,'删除','3','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','删除操作'),(21,4,'授权','4','sys_oper_type','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','授权操作'),(22,5,'导出','5','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','导出操作'),(23,6,'导入','6','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','导入操作'),(24,7,'强退','7','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','强退操作'),(25,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','生成操作'),(26,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','清空操作'),(27,1,'成功','0','sys_common_status','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态');

/*字典类型表*/

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
                                 `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
                                 `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
                                 `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
                                 `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`dict_id`),
                                 UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`dict_id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'用户性别','sys_user_sex','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','登录状态列表');


DROP TABLE IF EXISTS `dealer_post`;

CREATE TABLE `dealer_post` (
                               `post_id` varchar(32) NOT NULL COMMENT '岗位ID',
                               `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
                               `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
                               `post_sort` int(2) NOT NULL COMMENT '显示顺序',
                               `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
                               `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                               PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

/*Data for the table `dealer_post` */

insert  into `dealer_post`(`post_id`,`post_code`,`post_name`,`post_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values ('1','ceo','董事长',1,'0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('2','se','项目经理',2,'0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),('3','hr','人力资源',3,'0','admin','2018-03-16 11:33:00','管理员-王','2020-06-08 15:56:01','11');

/*Table structure for table `dealer_user_post` */

DROP TABLE IF EXISTS `dealer_user_post`;

CREATE TABLE `dealer_user_post` (
                                    `user_id` varchar(32) NOT NULL COMMENT '用户ID',
                                    `post_id` varchar(32) NOT NULL COMMENT '岗位ID',
                                    PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

/*Data for the table `dealer_user_post` */

insert  into `dealer_user_post`(`user_id`,`post_id`) values ('1','1'),('2','2'),('f581b220a23d4b5bae0851f42cb4b4b2','2');
