
create table LIBRARY_USER (
    ID number(18) primary key,
    NAME varchar(128) unique not null
);

create table USER_ACCOUNT (
    ID number(18) primary key,
    EMAIL varchar(255) unique not null,
    PASSWORD_HASH varchar(255) not null,
    USER_ID number(18) references LIBRARY_USER(ID) unique not null,
    STATUS varchar(16) not null
);

create table ACCOUNT_REGISTRATION (
    ID number(18) primary key,
    REGISTERED timestamp not null,
    TOKEN varchar(255) unique not null,
    USER_ACCOUNT_ID number(18) references USER_ACCOUNT(ID) unique not null
);