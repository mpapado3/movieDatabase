drop table Genre; 
drop table Movie; 
drop table Favorite_List; 


create table Genre ( 
Id integer PRIMARY KEY NOT NULL, Name varchar(20) NOT NULL ); 


create table Favorite_List ( 
Id integer NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
Name varchar(50) NOT NULL, 
CONSTRAINT primary_key PRIMARY KEY(id)); 


create table Movie ( 
Id integer PRIMARY KEY NOT NULL, 
Title varchar(100) NOT NULL, 
Genre_Id integer NOT NULL, 
Release_Date date NOT NULL, 
Rating float(10) NOT NULL, 
Overview varchar(500) NOT NULL, 
Favorite_List_Id integer, 
Foreign Key (Favorite_List_Id) References Favorite_List (Id), 
Foreign key (Genre_Id) References Genre (Id) );