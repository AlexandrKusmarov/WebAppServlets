create database if not exists study;
use study;

create table if not exists courses (
  idCourses      int(8)       not null auto_increment primary key,
  theme          varchar(255) not null,
  nameOfCourses  varchar(255) not null,
  startOfCourses date         not null,
  endOfCourses   date         not null,
  price          int(8)       not null
);

create table if not exists journal (
  idJournal int(8) not null auto_increment primary key,
  mark      int(8),
  coursId   int(8),
  studentId int(8)
);

create table if not exists role (
  userId int(8) not null,
  roles  varchar(255)
);

create table if not exists usr (
  idUser    int(8)       not null auto_increment primary key,
  login     varchar(255) not null unique,
  password  varchar(255) not null,
  email     varchar(255) not null,
  userRole  varchar(255) not null,
  mark      int(8),
  journalId int(8),
  coursesId int(8)
);

alter table usr
  add constraint user_journal_fk
foreign key (journalId) references journal (idJournal);

alter table usr
  add constraint user_courses_fk
foreign key (coursesId) references courses (idCourses);

alter table journal
  add constraint journal_courses_fk
foreign key (coursId) references courses (idCourses);

alter table journal
  add constraint journal_user_fk
foreign key (studentId) references usr (idUser);

alter table role
  add constraint role_user_fk
foreign key (userId) references usr (idUser);





