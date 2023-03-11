use `seec_erp`;

DROP TABLE IF EXISTS `bonus`;

CREATE TABLE `bonus` (
                         `staff_id` int primary key COMMENT '员工编号',
                         `staff_name` varchar(20) not null COMMENT '员工姓名',
                         `staff_bank_card` varchar(20) not null COMMENT '员工银行账号',
                         `origin_salary` int not null COMMENT '应发工资',
                         `tax` int not null COMMENT '扣除税款',
                         `absence_deduction` int not null COMMENT '缺勤扣除',
                         `actual_salary` int not null COMMENT '实发工资',
                         `year` varchar(20) not null default '2022' comment '年份',
                         `bonus` decimal(10, 2) COMMENT '年终奖'
)default character set = utf8;
