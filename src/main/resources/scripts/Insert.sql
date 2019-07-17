INSERT INTO courses (theme, nameOfCourses, startOfCourses, endOfCourses, price)
VALUES ('Beginner', 'First steps in Java', '2019.08.01', '2019.12.01', 20),
       ('Beginner', 'Second steps in Java', '2019.01.20', '2020.07.20', 25),
       ('Advanced', 'Codding like a boss', '2020.08.20', '2021.12.25', 40);

insert into role (roleId, roles)
values (1,'ADMIN'),
       (2,'TEACHER'),
       (3, 'STUDENT');

insert into usr (idUser, login, password, email ,userRole)
  VALUES (1, 'Admin', 'Admin' , 'admin@admin', 'ADMIN');