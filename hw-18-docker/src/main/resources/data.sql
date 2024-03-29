insert into genre (name)
values ('Роман');
insert into genre (name)
values ('Фэнтези');
insert into genre (name)
values ('Фантастика');

insert into author (name)
values ('Федор');
insert into author (name)
values ('Джон');
insert into author (name)
values ('Айзек');

insert into book (title, genre_id, author_id)
values ('Преступление и наказание', 1, 1);
insert into book (title, genre_id, author_id)
values ('Властелин колец', 2, 2);
insert into book (title, genre_id, author_id)
values ('Конец вечности', 3, 3);

insert into comment (text, book_id)
values ('Good book', 1);
insert into comment (text, book_id)
values ('Very good book', 1);
insert into comment (text, book_id)
values ('Very intresting', 2);

insert into users (username, password, enabled)
values ('user', 'password', true);
insert into users (username, password, enabled)
values ('admin', 'password', true);

INSERT INTO authorities (id, username, authority)
values (default, 'user', 'ROLE_USER'),
       (default, 'admin', 'ROLE_USER'),
       (default, 'admin', 'ROLE_ADMIN')
;