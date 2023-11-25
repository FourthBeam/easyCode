CREATE DATABASE `myDb` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    age INT
);
