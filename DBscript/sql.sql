CREATE DATABASE `myDb` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    age INT
);


INSERT INTO myDb.employees
( first_name, last_name, age)
VALUES( 'firstname1', 'lastname1', 1);

INSERT INTO myDb.employees
( first_name, last_name, age)
VALUES( 'firstname2', 'lastname2', 2);

INSERT INTO myDb.employees
( first_name, last_name, age)
VALUES( '首名3', '首名3', 3);