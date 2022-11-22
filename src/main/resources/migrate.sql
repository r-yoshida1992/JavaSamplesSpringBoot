-- テーブルの作成
drop table users;
create table if not exists users(id int primary key, name varchar (255) not null);
