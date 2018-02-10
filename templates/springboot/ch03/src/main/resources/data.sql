-- drop table if exists springbootdb.Book;
-- create table springbootdb.Book (id bigint not null auto_increment, author varchar(255), description varchar(255), isbn varchar(255), title varchar(255), reader varchar(255), primary key (id));
-- drop table if exists springbootdb.Reader;
-- create table springbootdb.Reader (username varchar(255) not null, fullname varchar(255), password varchar(255), primary key (username));

-- insert into Reader (username, password, fullname) values ('craig', 'password', 'Craig Walls');
-- insert into Reader (username, password, fullname) values ('walt', 'marceline', 'Walt Disney');
insert into reader (username, password, fullname) values ('craig', 'password', 'Craig Walls');
insert into reader (username, password, fullname) values ('walt', 'marceline', 'Walt Disney');
