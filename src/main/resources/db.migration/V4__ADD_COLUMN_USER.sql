alter table app_user add column roles VARCHAR(255) default 'USER';
insert into  app_user (email, password, username, roles) values ('admin@email.com', '$2a$10$sAGzQD/I8c36hz884rUvcuEE7dCTR7wBE3EDkQ5.eOjOp5YvFqgPG', 'admin', 'ADMIN');