drop table if exists `account`;
CREATE TABLE account(
                        name varchar(255) PRIMARY KEY,
                        balance bigint,
                        cardNum varchar(32)
);
INSERT INTO account(name, balance, cardNum)
values
    ('account1', 1000, '1010283721798'),
    ('balance2', 2000, '72937901243'),
    ('balance3', 3000, '7908238');
