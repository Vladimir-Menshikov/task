create sequence hibernate_sequence start 1 increment 1;

create table author (
   id int8 not null,
    first_name varchar(255),
    last_name varchar(255),
    primary key (id)
);
create table book (
   id int8 not null,
    name varchar(255),
    price numeric(19, 2),
    release_date date,
    author_id int8,
    primary key (id)
);
alter table book
   add constraint book_author_fk
   foreign key (author_id)
   references author;