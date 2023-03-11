use `seec_erp`;

drop table if exists `salary_sheet`;

create TABLE `salary_sheet`(
    `id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工资单id',
    `staff_id` int not null COMMENT '员工编号',
    `staff_name` varchar(20) not null COMMENT '员工姓名',
    `release_date` varchar(10) not null COMMENT '应发工资的日期',
    `staff_bank_card` varchar(20) not null COMMENT '员工银行账号',
    `origin_salary` int not null COMMENT '应发工资',
    `tax` int not null COMMENT '扣除税款',
    `absence_deduction` int not null COMMENT '缺勤扣除',
    `actual_salary` int not null COMMENT '实发工资',
    `operator` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作员',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
    primary key (staff_name, release_date)
)