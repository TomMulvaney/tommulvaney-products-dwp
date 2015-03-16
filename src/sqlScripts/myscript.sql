CREATE SCHEMA TESTUSER;

CREATE TABLE TESTUSER.Products (ID INTEGER NOT NULL,
	Name VARCHAR(255) NOT NULL,
	Description VARCHAR(255),
	Quantity INTEGER NOT NULL,
	PRIMARY KEY (ID));
	
insert into TESTUSER.Products values (2, 'Android: Netrunner','Living Card Game',8);