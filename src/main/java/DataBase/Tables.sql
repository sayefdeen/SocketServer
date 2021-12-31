CREATE DATABASE if not exists uni;


CREATE TABLE if not exists users
(
    id serial auto_increment,
    uuid varchar(150) not null,
    name varchar(50) not null ,
    password varchar(250) not null
);

CREATE TABLE if not exists students
(
    id serial auto_increment,
    u_id int REFERENCES users (id)  ON UPDATE CASCADE  ON DELETE  CASCADE,
    major varchar(25) not null
);

CREATE TABLE if not exists courses
(
    id serial auto_increment,
    uuid varchar(150) not null,
    name varchar(100) not null,
    section int not null default 1
);

CREATE TABLE if not exists stu_cou
(
    id serial auto_increment,
    s_id  int REFERENCES students (id) ON UPDATE CASCADE  ON DELETE  CASCADE ,
    c_id  int REFERENCES courses (id) ON UPDATE CASCADE  ON DELETE  CASCADE
)

