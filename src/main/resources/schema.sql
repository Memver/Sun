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
--CREATE TABLE IF NOT EXISTS "SIGN_UP"(
--	"ID" BIGINT NOT NULL,
--	"NAME" VARCHAR(255) NOT NULL,
--	"PASSWORD" VARCHAR(255) NOT NULL,
--	"MAIL" VARCHAR(255) NOT NULL,
--	CONSTRAINT "SIGN_UP_PKEY" PRIMARY KEY ("ID")
--);
--CREATE TABLE IF NOT EXISTS "PROJECT"(
--	"ID" BIGINT NOT NULL,
--	"TITLE" VARCHAR(255) NOT NULL,
--	CONSTRAINT "PROJECT_PKEY" PRIMARY KEY ("ID")
--);
--CREATE TABLE IF NOT EXISTS "EMPLOYEE"
--(
--	"ID" BIGINT NOT NULL,
--	"FIRST_NAME" VARCHAR(255) NOT NULL,
--	"LAST_NAME" VARCHAR(255) NOT NULL,
--	"BIRTHDAY" DATE NOT NULL,
--	"SIGN_UP_ID" BIGINT NOT NULL,
--	CONSTRAINT "EMPLOYEE_PKEY" PRIMARY KEY ("ID"),
--	CONSTRAINT "EMPLOYEE_FKEY" FOREIGN KEY ("SIGN_UP_ID")
--	REFERENCES "SIGN_UP" ("ID")
--);
--CREATE TABLE IF NOT EXISTS "EMPL_PROJ"
--(
--	"EMPLOYEE_ID" BIGINT NOT NULL,
--	"PROJECT_ID" BIGINT NOT NULL,
--	CONSTRAINT "EMPL_PROJ_EMPLOYEE_ID_FKEY" FOREIGN KEY ("EMPLOYEE_ID")
--		REFERENCES "EMPLOYEE" ("ID"),
--	CONSTRAINT "EMPL_PROJ_PROJECT_ID_FKEY" FOREIGN KEY ("PROJECT_ID")
--		REFERENCES "PROJECT" ("ID")
--);