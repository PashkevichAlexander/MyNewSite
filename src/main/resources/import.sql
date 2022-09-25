insert into  springboottest.users (username, enabled, password)  values ('admin', true, '$2a$10$dEv.pFKmpF2Ok/NxNS4iwecjiGHwrxrzGn0uVX72NEK7w01k68tQK'); -- admin password is root
insert into  springboottest.users (username, enabled, password) values ('test', true, '$2a$10$qYPXUwwi5EOu4HDgTGiU7OlYQjqYu6APrRubImP4tfeqfCJzaSPBi'); -- test password is test
insert into  springboottest.users (username, enabled, password) values ('user', true, '$2a$10$dEv.pFKmpF2Ok/NxNS4iwecjiGHwrxrzGn0uVX72NEK7w01k68tQK'); -- user password is root
insert into  springboottest.users (username, enabled, password) values ('editor', true, '$2a$10$dEv.pFKmpF2Ok/NxNS4iwecjiGHwrxrzGn0uVX72NEK7w01k68tQK'); -- editor password is root

insert into  springboottest.roles (name) values ('ROLE_ADMIN'); -- 1 is ADMIN
insert into  springboottest.roles (name) values ('ROLE_USER'); -- 2 is USER
insert into  springboottest.roles (name) values ('ROLE_EDITOR'); -- 3 is EDITOR

insert into  springboottest.users_roles (user_id, role_id) values (1, 1); -- user admin has role admin
insert into  springboottest.users_roles (user_id, role_id) values (1, 3); -- user admin has role editor

insert into  springboottest.users_roles (user_id, role_id) values (2, 1); -- user test has role admin
insert into  springboottest.users_roles (user_id, role_id) values (2, 2); -- user test has role user
insert into  springboottest.users_roles (user_id, role_id) values (2, 3); -- user test has role editor

insert into  springboottest.users_roles (user_id, role_id) values (3, 2); -- user user has role user