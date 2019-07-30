create database if not exists study;

SET FOREIGN_KEY_CHECKS=0;
drop table if exists courses;
drop table if exists usr;
drop table if exists journal;
drop table if exists cts;
SET FOREIGN_KEY_CHECKS=1;

create table if not exists courses (
  idCourses int(8) not null auto_increment primary key,
  theme          varchar(255) not null,
  nameOfCourses  varchar(255) not null,
  startOfCourses date         not null,
  endOfCourses   date         not null,
  price          int(8)       not null
);

create table if not exists journal (
  idJournal int(8) not null auto_increment primary key,
  coursId   int(8)
);

create table if not exists usr (
  idUser   int(8)       not null auto_increment primary key,
  login    varchar(255) not null unique,
  password varchar(255) not null,
  email    varchar(255) not null,
  userRole varchar(255) not null,
  isActive tinyint(1)
);

create table if not exists cts (
  idCts int(8) not null auto_increment primary key,
  userId int(8),
  courseId int(8),
  mark int (8)
);

alter table journal
  add constraint journal_courses_fk
foreign key (coursId) references courses (idCourses);

alter table cts
  add constraint cts_courses_fk
foreign key (courseId) references courses(idCourses)
  on DELETE CASCADE;

alter table cts
  add constraint cts_user_fk
foreign key (userId) references usr(idUser)
  on DELETE CASCADE;
