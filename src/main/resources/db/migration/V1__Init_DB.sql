create sequence hibernate_sequence start 1 increment 1;

create table author (
   id int8 not null,
    first_name varchar(255),
    last_name varchar(255),
    primary key (id)
);

create table book (
   id int8 not null,
    isbn varchar(13),
    name varchar(255),
    price numeric(19, 2),
    release_date date,
    author_id int8,
    primary key (id)
);

alter table book
   add constraint UK_isbn unique (isbn);

alter table book
   add constraint FK_book_author
   foreign key (author_id)
   references author;