create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('nikolay', '$2y$10$p/Ep33HVsvpMS69qNj335.aq4dSx0uiuJWHYS9jqa746n8WXKE1H2');

insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 2);

create table user_messages
(
    id         bigserial primary key,
    message    varchar(255),
    user_id    bigint not null references users (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into user_messages (message, user_id)
values ('Message #1', 1),
       ('Message #2', 1),
       ('Message #3', 1),
       ('Message #4', 1),
       ('Message #5', 1),
       ('Message #6', 1),
       ('Message #7', 1),
       ('Message #8', 1),
       ('Message #9', 1),
       ('Message #10', 1),
       ('Message #11', 1),
       ('Message #12', 1);