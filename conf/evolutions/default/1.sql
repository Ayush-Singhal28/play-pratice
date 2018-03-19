# --- !Ups

create table UserInfoTable
(
    id INT Primary Key auto_increment,
    email varchar(20),
    fname varchar(10),
    lname varchar(10)
    );

# --- !Downs

drop table UserInfoTable;