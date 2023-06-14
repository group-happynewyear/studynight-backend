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
    id         uuid         not null,
    match_id   uuid         not null,
    student_id uuid         not null,
    state      varchar(255) not null,
    primary key (id)
);
