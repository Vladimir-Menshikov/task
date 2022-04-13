create table roles (
       id int8 not null,
        name varchar(255),
        primary key (id)
    );

create table user_roles (
       user_id int8 not null,
        role_id int8 not null,
        primary key (user_id, role_id)
    );

create table users (
       id int8 not null,
        created timestamp,
        email varchar(255) not null,
        password varchar(255) not null,
        status varchar(25) default 'ACTIVE',
        updated timestamp,
        username varchar(255),
        primary key (id)
    );

alter table users
       add constraint UK_user_username unique (username);

alter table user_roles
       add constraint FK_user_roles_roles
       foreign key (role_id)
       references roles;

alter table user_roles
       add constraint FK_user_roles_users
       foreign key (user_id)
       references users;