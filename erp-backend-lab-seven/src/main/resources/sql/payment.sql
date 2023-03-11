/**
 * author: 于金甲
 * datetime: 2022/02/28 20:21:00
 */
use `seec_erp`;

DROP TABLE IF EXISTS `payment_sheet_content`;
DROP TABLE IF EXISTS `payment_sheet`;

CREATE TABLE `payment_sheet` (
                                 `id` varchar(40) primary key,
                                 `account_name` varchar(20),
                                 `operator` varchar(40),
                                 `total_amount` decimal(10, 2),
                                 `state` varchar(40),
                                 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'
)default character set = utf8;

INSERT INTO `payment_sheet` VALUES ('FKD-20220523-00000', 'acount1', 'xiaoshoujingli',1000000.00, '审批完成', '2022-05-23 23:13:59');
INSERT INTO `payment_sheet` VALUES ('FKD-20220523-00001', 'acount1', 'xiaoshoujingli',2200000.00, '审批完成', '2022-05-23 23:13:59');
INSERT INTO `payment_sheet` VALUES ('FKD-20220523-00002', 'acount1', 'xiaoshoujingli',3450000.00, '审批完成', '2022-05-23 23:15:57');
INSERT INTO `payment_sheet` VALUES ('FKD-20220524-00000', 'acount1', 'xiaoshoujingli',2200000.00, '待审批','2022-05-24 00:56:54');
INSERT INTO `payment_sheet` VALUES ('FKD-20220524-00001', 'acount1', 'xiaoshoujingli',3240000.00, '待审批','2022-05-24 00:57:29');
INSERT INTO `payment_sheet` VALUES ('FKD-20220524-00002', 'acount1', 'xiaoshoujingli',1650000.00, '审批失败','2022-05-24 01:02:04');
CREATE TABLE `payment_sheet_content`(
                                        `id` int primary key auto_increment,
                                        `payment_sheet_id` varchar(40),
                                        `customer` int(11) NULL,
                                        `amount` decimal(10, 2) NULL,
                                        `remark` varchar(255) NULL
)default character set = utf8;
INSERT INTO `payment_sheet_content` VALUES (51, 'FKD-20220523-00000', 1, 1000000.00, 'a');
INSERT INTO `payment_sheet_content` VALUES (52, 'FKD-20220523-00001', 1, 1200000.00, 'b');
INSERT INTO `payment_sheet_content` VALUES (53, 'FKD-20220523-00001', 1, 1000000.00, 'b');
INSERT INTO `payment_sheet_content` VALUES (54, 'FKD-20220523-00002', 1, 650000.00, 'c');
INSERT INTO `payment_sheet_content` VALUES (55, 'FKD-20220523-00002', 1, 2800000.00, 'c');
INSERT INTO `payment_sheet_content` VALUES (56, 'FKD-20220524-00000', 1, 750000.00, '');
INSERT INTO `payment_sheet_content` VALUES (57, 'FKD-20220524-00000', 1, 1450000.00, NULL);
INSERT INTO `payment_sheet_content` VALUES (58, 'FKD-20220524-00001', 1, 1140000.00, '');
INSERT INTO `payment_sheet_content` VALUES (59, 'FKD-20220524-00001', 1, 2100000.00, NULL);
INSERT INTO `payment_sheet_content` VALUES (60, 'FKD-20220524-00002', 1, 570000.00, '');
INSERT INTO `payment_sheet_content` VALUES (61, 'FKD-20220524-00002', 1, 1080000.00, NULL);



