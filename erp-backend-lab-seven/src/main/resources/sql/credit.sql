DROP TABLE IF EXISTS `credit_sheet`;
CREATE TABLE `credit_sheet` (
    `id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    `customer` int(11) NULL,
    `operator` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `total_amount` decimal(10, 2) NULL,
    `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
);
INSERT INTO `credit_sheet` VALUES ('SKD-20220523-00000', 1, 'xiaoshoujingli',1000000.00, '审批完成', '2022-05-23 23:13:59');
INSERT INTO `credit_sheet` VALUES ('SKD-20220523-00001', 1, 'xiaoshoujingli',2200000.00, '审批完成', '2022-05-23 23:14:34');
INSERT INTO `credit_sheet` VALUES ('SKD-20220523-00002', 1, 'xiaoshoujingli',3450000.00, '审批完成', '2022-05-23 23:15:57');
INSERT INTO `credit_sheet` VALUES ('SKD-20220524-00000', 1, 'xiaoshoujingli',2200000.00, '待审批', '2022-05-24 00:56:54');
INSERT INTO `credit_sheet` VALUES ('SKD-20220524-00001', 1, 'xiaoshoujingli',3240000.00, '待审批', '2022-05-24 00:57:29');
INSERT INTO `credit_sheet` VALUES ('SKD-20220524-00002', 1, 'xiaoshoujingli',1650000.00, '审批失败', '2022-05-24 01:02:04');
DROP TABLE IF EXISTS `credit_sheet_content`;
CREATE TABLE `credit_sheet_content`(
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `credit_sheet_id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
   `account_name` varchar(255),
   `amount` decimal(10, 2) NULL,
   `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
   PRIMARY KEY (`id`) USING BTREE
);
INSERT INTO `credit_sheet_content` VALUES (51, 'SKD-20220523-00000', 'account1', 1000000.00, 'a');
INSERT INTO `credit_sheet_content` VALUES (52, 'SKD-20220523-00001', 'account1', 1200000.00, 'b');
INSERT INTO `credit_sheet_content` VALUES (53, 'SKD-20220523-00001', 'account1', 1000000.00, 'b');
INSERT INTO `credit_sheet_content` VALUES (54, 'SKD-20220523-00002', 'balance2', 650000.00, 'c');
INSERT INTO `credit_sheet_content` VALUES (55, 'SKD-20220523-00002', 'balance3', 2800000.00, 'c');
INSERT INTO `credit_sheet_content` VALUES (56, 'SKD-20220524-00000', 'balance2', 750000.00, '');
INSERT INTO `credit_sheet_content` VALUES (57, 'SKD-20220524-00000', 'balance3', 1450000.00, NULL);
INSERT INTO `credit_sheet_content` VALUES (58, 'SKD-20220524-00001', 'balance2', 1140000.00, '');
INSERT INTO `credit_sheet_content` VALUES (59, 'SKD-20220524-00001', 'account1', 2100000.00, NULL);
INSERT INTO `credit_sheet_content` VALUES (60, 'SKD-20220524-00002', 'account1', 570000.00, '');
INSERT INTO `credit_sheet_content` VALUES (61, 'SKD-20220524-00002', 'account1', 1080000.00, NULL);
