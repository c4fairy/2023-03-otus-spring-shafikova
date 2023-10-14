insert into subject (id, name)
values ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e1', 'Математика');
insert into subject (id, name)
values ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e2', 'Программирование');
insert into subject (id, name)
values ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e3', 'Литература');

insert into person_info (id, fio, email, tutor, balance, subject_id)
values ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e4', 'Some Student', 'student mail', false, 0.00, '0edf61ff-a6aa-4a90-b9b7-6795a8ed83e1');
insert into person_info (id, fio, email, tutor, balance, subject_id)
values ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e5', 'Some Tutor', 'tutor mail', false, 0.00, '0edf61ff-a6aa-4a90-b9b7-6795a8ed83e1');


insert into lesson_request (id, subject_id, tutor_id, student_id, lesson_start, lesson_end, paid, status)
values ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e6', '0edf61ff-a6aa-4a90-b9b7-6795a8ed83e1',
        '0edf61ff-a6aa-4a90-b9b7-6795a8ed83e5', '0edf61ff-a6aa-4a90-b9b7-6795a8ed83e4',
        now(), now(), false, 'CREATED');

insert into users (username, password, enabled)
values ('tutor1', 'password', true);
insert into users (username, password, enabled)
values ('student1', 'password', true);
insert into users (username, password, enabled)
values ('admin', 'password', true);

INSERT INTO authorities (id, username, authority)
values ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e7', 'tutor1', 'TUTOR'),
       ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e8', 'student1', 'STUDENT'),
       ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e9', 'admin', 'TUTOR'),
       ('0edf61ff-a6aa-4a90-b9b7-6795a8ed83e0', 'admin', 'STUDENT')
;
