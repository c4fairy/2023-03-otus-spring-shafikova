insert into genre (name)
values ('Роман');
insert into genre (name)
values ('Фэнтези');
insert into genre (name)
values ('Фантастика');

insert into author (id, name, surname)
values (1, 'Федор', 'Достоевский');
insert into author (id, name, surname)
values (2, 'Джон', 'Толкин');
insert into author (id, name, surname)
values (3, 'Айзек', 'Азимов');

insert into book (id, title, genreId, authorId)
values (1, 'Преступление и наказание', 1, 1);
insert into book (id, title, genreId, authorId)
values (2, 'Властелин колец', 2, 2);
insert into book (id, title, genreId, authorId)
values (3, 'Конец вечности', 3, 3);