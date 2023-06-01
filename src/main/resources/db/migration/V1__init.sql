-- TODO

-- authentication

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


-- studynight

create table students
(
    id       uuid         not null,
    nickname varchar(255) not null,
    user_id  uuid         not null,
    primary key (id)
);
alter table students add constraint UK_students_user_id unique (user_id);

create table conditions
(
    id         uuid         not null,
    k          varchar(255) not null,
    v          varchar(255) not null,
    student_id uuid         not null,
    primary key (id)
);

create table transactions
(
    id         uuid         not null,
    type       varchar(255) not null,
    point      integer      not null,
    expired_at timestamp(6) not null,
    timestamp  timestamp(6) not null,
    student_id uuid         not null,
    primary key (id)
);

create table studies
(
    id              uuid         not null,
    condition       varchar(255) not null,
    contact_address varchar(255) not null,
    contact_type    varchar(255) not null,
    created_at      timestamp(6) not null,
    description     varchar(255) not null,
    title           varchar(255) not null,
    primary key (id)
);

create table engagements
(
    id         uuid         not null,
    role       varchar(255) not null,
    student_id uuid         not null,
    study_id   uuid         not null,
    primary key (id)
);

create table matches
(
    id        uuid         not null,
    condition varchar(255) not null,
    study_id  uuid         not null,
    primary key (id)
);

create table invitations
(
    id         uuid not null,
    match_id   uuid not null,
    student_id uuid not null,
    primary key (id)
);

-- notification
create table channels
(
    id            uuid         not null,
    owner_type    varchar(255) not null,
    owner_id      varchar(255) not null,
    address_type  varchar(255) not null,
    address_value varchar(255) not null,
    primary key (id)
);
