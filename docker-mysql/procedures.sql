CREATE DATABASE IF NOT EXISTS skt_activity;
CREATE TABLE if not exists skt_activity.products (
    product_id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(50),product_type VARCHAR(50),
    product_description VARCHAR(250),
    CONSTRAINT products_pk PRIMARY KEY (product_id));
    
USE skt_activity;
DELIMITER $$
CREATE PROCEDURE `findAll` ()
BEGIN
	SELECT * FROM products;
END $$
DELIMITER ;

USE skt_activity;
DELIMITER $$
CREATE PROCEDURE `insertProduct` 
(
    IN name_param VARCHAR(50),
    IN type_param VARCHAR(50),
    IN description_param VARCHAR(250),
    OUT id_param INT
)
BEGIN
	INSERT INTO products(product_name,product_type,product_description) VALUES(name_param,type_param,description_param);
    SET id_param := last_insert_id();
END $$
DELIMITER ;