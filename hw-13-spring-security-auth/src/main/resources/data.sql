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

insert into comment (text, book_id, user_id)
values ('Good book', 1, 1);
insert into comment (text, book_id, user_id)
values ('Very good book', 1, 2);
insert into comment (text, book_id, user_id)
values ('Very interesting', 2, 4);

insert into users (id, username, password)
values (1, 'admin', 'admin');
insert into users (id, username, password)
values (2, 'user1', 'user1');
insert into users (id, username, password)
values (3, 'user2', 'user2');
insert into users (id, username, password)
values (4, 'user3', 'user3');

insert into user_role (user_id, roles)
values (1, 'ADMIN');
insert into user_role (user_id, roles)
values (2, 'USER');
insert into user_role (user_id, roles)
values (3, 'DELETED');
insert into user_role (user_id, roles)
values (4, 'USER');