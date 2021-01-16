# DROP DATABASE awesome_project;

create database if not exists awesome_project;
-- desc mysql.user;
select host, user from mysql.user;
-- drop user 'admin_db'@'localhost';
create user 'admin_db'@'localhost' identified by 'password';
grant all on awesome_project.* to 'admin_db'@'localhost';
show grants for 'admin_db'@'localhost';
-- revoke all privileges on *.* from 'user_db'@'localhost';
-- set password for 'user_db'@'localhost' = 'password';
-- grant select on awesome_project.* to 'user_db'@'localhost';
# exit;
