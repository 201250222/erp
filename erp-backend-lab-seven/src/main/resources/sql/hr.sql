/**
 * author: 于金甲
 * datetime: 2022/02/28 20:21:00
 */

use `seec_erp`;

drop table if exists `staff_checkup`;
drop table if exists `staff`;
drop table if exists `role`;


create table `role`(
                       id int(11) not null primary key auto_increment,
                       role_name varchar(20) not null unique,
                       role_salary int,
                       salary_calculate varchar(40),
                       salary_release int
)default character set = utf8;

create table `staff`(
                        id int not null primary key AUTO_INCREMENT,
                        name varchar(20) not null unique,
                        gender varchar(20),
                        birthday date,
                        phone varchar(20),
                        role varchar(40),
                        bank_card varchar(20),
                        performance_salary int,
                        foreign key(role) references `role`(role_name)
)default character set = utf8;

create table `staff_checkup`(
                                `staff_name` varchar(40),
                                `date` datetime not null,
                                foreign key(staff_name) references staff(name),
                                primary key(staff_name, `date`)
)default character set = utf8;


insert into `role`(role_name, role_salary, salary_calculate, salary_release)
values
    ('INVENTORY_MANAGER', 4000,'MONTH',0),
    ('SALE_STAFF', 5000,'MONTH_PLUS_PERFORMANCE',0),
    ('FINANCIAL_STAFF', 8000,'MONTH',0),
    ('SALE_MANAGER', 6000,'MONTH_PLUS_PERFORMANCE',0),
    ('HR', 8000,'MONTH',0),
    ('GM', 20000,'YEAR',0),
    ('ADMIN', 0,'NULL',0);

insert into `staff`(name, gender, birthday, phone, `role`,bank_card, performance_salary)
values('seecoder', '男', '2002-10-10', '15978943333', 'INVENTORY_MANAGER', '123456',0),
      ('uncln', '男', '2002-10-10', '15978943333', 'INVENTORY_MANAGER', '123456',0),
      ('kucun', '男', '2002-10-10', '15978943333', 'INVENTORY_MANAGER', '123456',0),
      ('sky', '男', '2002-10-10', '15978943333', 'ADMIN', '123456',0),
      ('zxr', '男', '2002-10-10', '15978943333', 'SALE_MANAGER', '123456',0),
      ('67', '男', '2002-10-10', '15978943333', 'GM', '123456',0),
      ('xiaoshou', '男', '2002-10-10', '15978943333', 'SALE_STAFF', '123456',0),
      ('Leone', '男', '2002-10-10', '15978943333', 'GM', '123456',0),
      ('xiaoshoujingli', '男', '2002-10-10', '15978943333', 'SALE_MANAGER', '123456',0);



