CREATE SCHEMA PRODUCTAPP;

CREATE TABLE PRODUCTAPP.Product (ID INTEGER NOT NULL,
	Name VARCHAR(255) NOT NULL,
	Description VARCHAR(255),
	Quantity INTEGER NOT NULL,
	PRIMARY KEY (ID));
	
insert into PRODUCTAPP.Product values ('Agricola','Farming board game',3);