create table channels
(
    id            uuid         not null,
    owner_type    varchar(255) not null,
    owner_id      varchar(255) not null,
    address_type  varchar(255) not null,
    address_value varchar(255) not null,
    primary key (id)
);
