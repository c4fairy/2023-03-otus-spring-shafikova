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
    id      bigint primary key auto_increment,
    name    varchar2(255),
    surname varchar2(255)
);

CREATE UNIQUE INDEX uq_author
    ON author (name, surname);

alter table book
    add constraint fk_bookGenre
        foreign key (genre_id) references genre (Id);

alter table book
    add constraint fk_bookAuthor
        foreign key (author_id) references author (Id);

alter table comment
    add constraint fk_commentBook
        foreign key (book_id) references book (Id) ON DELETE CASCADE;