CREATE DATABASE IF NOT EXISTS skt_activity;
CREATE TABLE if not exists skt_activity.products (
    product_id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(50),product_type VARCHAR(50),
    product_description VARCHAR(250),
    CONSTRAINT products_pk PRIMARY KEY (product_id));
