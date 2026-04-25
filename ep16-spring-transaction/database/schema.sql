DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	role VARCHAR(20) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	email VARCHAR(60) UNIQUE NOT NULL
);


DROP TABLE IF EXISTS supplier;

CREATE TABLE supplier (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	email VARCHAR(60) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	email VARCHAR(60) UNIQUE NOT NULL
);


DROP TABLE IF EXISTS category;

CREATE TABLE category (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL
);


DROP TABLE IF EXISTS product;

CREATE TABLE product (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL
);


DROP TABLE IF EXISTS product_category;

CREATE TABLE product_category (
	product_id INT NOT NULL,
	category_id INT NOT NULL,
	PRIMARY KEY (product_id, category_id),
	FOREIGN KEY (product_id) REFERENCES product (id),
	FOREIGN KEY (category_id) REFERENCES category (id)
);

DROP TABLE IF EXISTS product_supplier;

CREATE TABLE product_supplier (
	product_id INT NOT NULL,
	supplier_id INT NOT NULL,
	PRIMARY KEY (product_id, supplier_id),
	FOREIGN KEY (product_id) REFERENCES product (id),
	FOREIGN KEY (supplier_id) REFERENCES supplier (id)
);


DROP TABLE IF EXISTS stock;

CREATE TABLE stock (
	product_id INT NOT NULL PRIMARY KEY,
	version INT NOT NULL,
	stock_amount INT NOT NULL,
	purchase_price INT,
	sale_price INT,
	FOREIGN KEY (product_id) REFERENCES product (id)
);

DROP TABLE IF EXISTS stock_history;

CREATE TABLE stock_history (
	product_id INT NOT NULL,
	version INT NOT NULL,
	action_type VARCHAR(10) NOT NULL,
	purchase_price INT NOT NULL,
	sale_price INT NOT NULL,
	amount INT NOT NULL,
	PRIMARY KEY (product_id, version),
	FOREIGN KEY (product_id) REFERENCES stock (product_id)
);


DROP TABLE IF EXISTS sale;

CREATE TABLE sale (
	id INT AUTO_INCREMENT PRIMARY KEY,
	customer_id INT NOT NULL,
	sale_at TIMESTAMP NOT NULL,
	delivery_fee INT,
	discount INT,
	tax_rate DOUBLE,
	FOREIGN KEY (customer_id) REFERENCES customer (id)
);

DROP TABLE IF EXISTS sale_product;

CREATE TABLE sale_product (
	product_id INT NOT NULL,
	version INT NOT NULL,
	sale_id INT NOT NULL,
	unit_price INT NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY (product_id, version),
	FOREIGN KEY (product_id, version) REFERENCES stock_history (product_id, version),
	FOREIGN KEY (product_id) REFERENCES product (id),
	FOREIGN KEY (sale_id) REFERENCES sale (id)
);

DROP TABLE IF EXISTS purchase;

CREATE TABLE purchase (
	id INT AUTO_INCREMENT PRIMARY KEY,
	employee_id INT NOT NULL,
	supplier_id INT NOT NULL,
	purchase_date DATE NOT NULL,
	transportation_fee INT NOT NULL,
	FOREIGN KEY (employee_id) REFERENCES employee (id),
	FOREIGN KEY (supplier_id) REFERENCES supplier (id)
);

DROP TABLE IF EXISTS purchase_product;

CREATE TABLE purchase_product (
	product_id INT NOT NULL,
	version INT NOT NULL,
	purchase_id INT NOT NULL,
	unit_price INT NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY (product_id, version),
	FOREIGN KEY (product_id, version) REFERENCES stock_history (product_id, version),
	FOREIGN KEY (product_id) REFERENCES product (id),
	FOREIGN KEY (purchase_id) REFERENCES purchase (id)
);