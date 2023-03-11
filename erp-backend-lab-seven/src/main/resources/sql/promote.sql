use `seec_erp`;

drop table if exists `promote`;

create table `promote`(
                       id int(11) auto_increment primary key,
                       operator varchar(255) not null,
                       begin_time date not null,
                       end_time date not null,
                       promote_trigger int not null,
                       customer_level int not null,
                       product_id1 varchar(16) null default null,
                       amount1 int null default null,
                       product_id2 varchar(16) null default null,
                       amount2 int null default null,
                       product_id3 varchar(16) null default null,
                       amount3 int null default null,
                       total_price decimal(10, 2) null default null,
                       promote_type int not null,
                       present_id varchar(16) null default null,
                       present_number int null default null,
                       discount double(2,2) null default null,
                       voucher_price decimal(10, 2) null default null
)default character set = utf8;