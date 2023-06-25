create table IF NOT EXISTS person(
        id serial not null,
        firstName varchar(99),
        lastName varchar(99),
        phoneNumber bigint not null,
        primary key (id)
);
create table IF NOT EXISTS address(
        id serial not null,
        city varchar(99),
        street varchar(255),
        home integer not null,
		building varchar(99),
        primary key (id)
);
create table IF NOT EXISTS industry(
        id serial not null,
		addressId integer not null,
		personId integer not null,
        target varchar(99),
        area integer not null,
		aim varchar(99),
        primary key (id)
);