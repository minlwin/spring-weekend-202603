SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE employee;
INSERT INTO employee (id, name, role, phone, email) values (1,'Aung Aung','Store Manager','09-0091-1111','aung@gmail.com');
INSERT INTO employee (id, name, role, phone, email) values (2,'Thidar','Sale Staff','09-0091-1112','thidar@gmail.com');

TRUNCATE TABLE supplier;
INSERT INTO supplier (id, name, phone, email) values (1,'Aung Gabar','09-1122-2233','aunggabar@example.com');
INSERT INTO supplier (id, name, phone, email) values (2,'Thiri Mon','09-1122-2234','thiri@example.com');
INSERT INTO supplier (id, name, phone, email) values (3,'Nyein Myitter','09-1122-2235','nyein@example.com');
INSERT INTO supplier (id, name, phone, email) values (4,'Shwe Store','09-1122-2236','shwe@example.com');

TRUNCATE TABLE customer;
INSERT INTO customer (id, name, phone, email) values (1,'Min Aung','09-9122-2233','minaung@example.com');
INSERT INTO customer (id, name, phone, email) values (2,'Htin Aung','09-9122-2234','htinaung@example.com');

TRUNCATE TABLE product;
INSERT INTO product (id, name) values (1,'Coke');
INSERT INTO product (id, name) values (2,'Pepsi');
INSERT INTO product (id, name) values (3,'Nest Café');

TRUNCATE TABLE stock;
INSERT INTO stock (product_id, version, stock_amount, purchase_price, sale_price) values (1,0,0,0,0);
INSERT INTO stock (product_id, version, stock_amount, purchase_price, sale_price) values (2,1,100,2000,2200);
INSERT INTO stock (product_id, version, stock_amount, purchase_price, sale_price) values (3,1,100,3000,3300);

TRUNCATE TABLE stock_history;
INSERT INTO stock_history (product_id, version, action_type, purchase_price, sale_price, amount) values (1,0,'Initiate' ,0,0,0);
INSERT INTO stock_history (product_id, version, action_type, purchase_price, sale_price, amount) values (2,0,'Initiate' ,0,0,0);
INSERT INTO stock_history (product_id, version, action_type, purchase_price, sale_price, amount) values (3,0,'Initiate' ,0,0,0);
INSERT INTO stock_history (product_id, version, action_type, purchase_price, sale_price, amount) values (2,1,'Purchase' ,2000,2200,100);
INSERT INTO stock_history (product_id, version, action_type, purchase_price, sale_price, amount) values (3,1,'Purchase' ,3000,3300,100);

TRUNCATE TABLE product_supplier;
INSERT INTO product_supplier (product_id, supplier_id) values (1,1);
INSERT INTO product_supplier (product_id, supplier_id) values (2,1);
INSERT INTO product_supplier (product_id, supplier_id) values (3,1);

TRUNCATE TABLE purchase;
INSERT INTO purchase (id, employee_id, supplier_id, purchase_date, transportation_fee) values (1,1,1,'2026-04-10' ,15000);

TRUNCATE TABLE purchase_product;
INSERT INTO purchase_product (product_id, version, purchase_id, unit_price, quantity) values (2,1,1,2000,100);
INSERT INTO purchase_product (product_id, version, purchase_id, unit_price, quantity) values (3,1,1,3000,100);

SET FOREIGN_KEY_CHECKS = 1;
