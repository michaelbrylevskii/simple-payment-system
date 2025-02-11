create table accounts
(
    number     varchar(48)    not null primary key,
    type       varchar(48)    not null,
    balance    decimal(19, 4) not null,
    currency   varchar(4)     not null,
    status     varchar(48)    not null,
    created_at timestamp(3)   not null
);
