create table user (
id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
username varchar(50),
firstname varchar(50),
lastname varchar(50));


