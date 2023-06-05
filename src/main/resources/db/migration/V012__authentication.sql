create table users
(
    id    uuid         not null,
    email varchar(255) not null,
    primary key (id)
);

create table accounts
(
    id       uuid         not null,
    email    varchar(255) not null,
    password varchar(255) not null,
    user_id  uuid         not null,
    primary key (id)
);
alter table accounts add constraint UK_accounts_user_id unique (user_id);
alter table accounts add constraint UK_accounts_email unique (email);

create table social_accounts
(
    id          uuid         not null,
    external_id varchar(255) not null,
    provider    varchar(255) not null,
    user_id     uuid         not null,
    primary key (id)
);
alter table social_accounts add constraint UK_social_accounts_user_id unique (user_id);
alter table social_accounts add constraint UK_social_accounts_provider_external_id unique (provider, external_id);

create table refresh_token_chains
(
    id      uuid not null,
    user_id uuid not null,
    primary key (id)
);

create table refresh_tokens
(
    id                     uuid         not null,
    expired_at             timestamp(6) not null,
    used                   boolean      not null,
    refresh_token_chain_id uuid         not null,
    primary key (id)
);
