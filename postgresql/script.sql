create table Car (Brand TEXT, Model TEXT, YEAR INT, PRICE NUMERIC(5,2));

insert into car values('Volks', 'Tiguan R-LINE', 2019, 169.22);



create table Person
(
    name varchar not null,
    address varchar not null,
    dob date not null,
    ordered integer default 1 not null,
    primary key (name, address, dob, ordered)
);