use `seec_erp`;

drop table if exists `establishment_category`;
drop table if exists `establishment_product`;
drop table if exists `establishment_cutomer`;
drop table if exists `establishment_account`;

CREATE TABLE `establishment_category`  (
                                           `id` int(11) NOT NULL AUTO_INCREMENT,
                                           `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
                                           `parent_id` int(11) NOT NULL COMMENT '父节点id',
                                           `is_leaf` tinyint(4) NOT NULL COMMENT '是否为叶节点',
                                           `item_count` int(11) NOT NULL COMMENT '商品个数',
                                           `item_index` int(11) NOT NULL COMMENT '插入的下一个index',
                                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


create table `establishment_product`(
                                        `id` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类id(11位) + 位置(5位) = 编号',
                                        `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
                                        `category_id` int(11) NOT NULL COMMENT '商品分类id',
                                        `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品型号',
                                        `purchase_price` decimal(10, 2) NOT NULL COMMENT '进价',
                                        `retail_price` decimal(10, 2) NOT NULL COMMENT '零售价',
                                        `recent_pp` decimal(10, 2) NULL DEFAULT NULL COMMENT '最近进价',
                                        `recent_rp` decimal(10, 2) NULL DEFAULT NULL COMMENT '最近零售价',
                                        PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

create table `establishment_customer`(
                                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                         `type` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类(供应商\\销售商)',
                                         `level` int(11) NULL DEFAULT NULL COMMENT '级别（五级，一级普通用户，五级VIP客户）',
                                         `name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
                                         `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
                                         `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
                                         `zipcode` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
                                         `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
                                         `line_of_credit` decimal(10, 2) NULL DEFAULT NULL COMMENT '应收额度本公司给客户的信用额度，客户欠本公司的钱的总额不能超过应收额度）',
                                         `receivable` decimal(10, 2) NULL DEFAULT NULL COMMENT '应收（客户还应付给本公司但还未付的钱， 即本公司应收的钱）',
                                         `payable` decimal(10, 2) NULL DEFAULT NULL COMMENT '应付（本公司欠客户的钱）',
                                         `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认业务员',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;



CREATE TABLE `establishment_account`(
                                        name varchar(255) PRIMARY key comment '名称',
                                        balance bigint default 0 comment '余额',
                                        cardNum varchar(32) comment '卡号'
)default character set = utf8;


