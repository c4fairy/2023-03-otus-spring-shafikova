create table users
(
    username varchar(255) not null
        primary key,
    password varchar(255) not null,
    enabled  boolean      not null
);

create table authorities
(
    id        uuid         not null
        primary key,
    username  varchar(255) not null
        constraint fk_authorities_users
            references users,
    authority varchar(255) not null
);

create unique index ix_auth_username
    on authorities (username, authority);

create table subject
(
    id   uuid not null
        primary key,
    name varchar(255)
        unique
);

create table person_info
(
    id         uuid                  not null
        primary key,
    fio        varchar(255)          not null,
    phone      varchar(255),
    email      varchar(255)          not null,
    tutor      boolean default false not null,
    balance    bigint  default 0.00  not null,
    subject_id uuid
        constraint fk_subject
            references subject
);

create table lesson_request
(
    id           uuid                                              not null
        primary key,
    subject_id   uuid                                              not null
        constraint fk_subject
            references subject,
    tutor_id     uuid                                              not null
        constraint fk_tutor
            references person_info,
    student_id   uuid                                              not null
        constraint fk_student
            references person_info,
    lesson_start timestamp                                         not null,
    lesson_end   timestamp                                         not null,
    paid         boolean      default false                        not null,
    status       varchar(255) default 'CREATED'::character varying not null
);
