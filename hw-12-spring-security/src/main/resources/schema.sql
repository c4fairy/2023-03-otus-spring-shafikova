drop table if exists comment;
create table comment
(
    id      bigint primary key auto_increment,
    text    varchar2(255),
    book_id bigint
);

drop table if exists book;
create table book
(
    id        bigint primary key auto_increment,
    title     varchar2(255),
    genre_id  bigint,
    author_id bigint
);

drop table if exists genre;
create table genre
(
    id   bigint primary key auto_increment,
    name varchar2(255) unique
);

drop table if exists author;
create table author
(
    id   bigint primary key auto_increment,
    name varchar2(255)
);

create table users
(
    username varchar_ignorecase(50)  not null primary key,
    password varchar_ignorecase(500) not null,
    enabled  boolean                 not null
);
create table authorities
(
    id        bigint auto_increment,
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    primary key (id) ,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

CREATE UNIQUE INDEX uq_author
    ON author (name);

alter table book
    add constraint fk_bookGenre
        foreign key (genre_id) references genre (Id);

alter table book
    add constraint fk_bookAuthor
        foreign key (author_id) references author (Id);

alter table comment
    add constraint fk_commentBook
        foreign key (book_id) references book (Id) ON DELETE CASCADE;