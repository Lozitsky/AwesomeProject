# mysql -u admin_db -p
# use awesome_project

# drop table if exists task;
# create table if not exists task
# (
#     id                 bigint not null auto_increment primary key,
#     name               varchar(100) not null,
#     description        varchar(1000),
#     status             varchar(30),
#     created_date       timestamp,
#     last_modified_date timestamp
# );