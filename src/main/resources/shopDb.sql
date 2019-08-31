-- # CREATE TABLES # --

CREATE TABLE shop_user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id VARCHAR(10) NOT NULL,
	nick_name VARCHAR(20) NOT NULL,
	passwd VARCHAR(10) NOT NULL,
	name VARCHAR(10) NOT NULL,
	address VARCHAR(150) NOT NULL,
	tel VARCHAR(14) NOT NULL,
	email VARCHAR(100) NOT NULL,
	authority INT(1) NOT NULL DEFAULT 1,
	inp_date DATE NOT NULL,
	upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE shop_category(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	delete_flag INT NOT NULL DEFAULT 1
);

CREATE TABLE shop_product(
	id INT PRIMARY KEY AUTO_INCREMENT,
	category_id INT NOT NULL,
	name VARCHAR(100) NOT NULL,
	image VARCHAR(100),
	price INT NOT NULL,
	stock_quantity INT NOT NULL,
	details TEXT,
	delete_flag INT NOT NULL DEFAULT 1,
	inp_date DATE NOT NULL,
	upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	FOREIGN KEY(category_id) references shop_category(id)
);

CREATE TABLE shop_order(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL,
	total_price INT NOT NULL,
	delivery_status INT NOT NULL DEFAULT 1, 
	order_date DATE NOT NULL,
	inp_date DATE NOT NULL,
	upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	FOREIGN KEY(user_id) references shop_user(id)
);

CREATE TABLE shop_order_detail(
	id INT PRIMARY KEY AUTO_INCREMENT,
	order_id INT NOT NULL,
	product_id INT NOT NULL,
	order_quantity INT NOT NULL,
	inp_date DATE NOT NULL,
	upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	FOREIGN KEY(order_id) references shop_order(id),
	FOREIGN KEY(product_id) references shop_product(id)
);

CREATE TABLE shop_comment(
	id INT PRIMARY KEY AUTO_INCREMENT,
	product_id INT NOT NULL,
	user_id INT NOT NULL,
	order_detail_id INT NOT NULL,
	score INT NOT NULL DEFAULT 1,
	comment TEXT,
	inp_date DATE NOT NULL,
	upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	FOREIGN KEY(product_id) references shop_product(id),
	FOREIGN KEY(user_id) references shop_user(id),
	FOREIGN KEY(order_detail_id) references shop_order_detail(id)
);


-- # INSERT DATA # --
-- shop_user
INSERT INTO shop_user VALUES(0, 'admin', 'admin', 'admin', 'admin', 'EARTH', '07043372692', 'heyawoo@gmail.com', 0, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_user VALUES(0, 'test', 'test', 'test', 'test', 'UNIVERSE', '01040260253', 'hellotest3000@gmail.com', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_user VALUES(0, 'aaa', 'aaa', 'aaa', 'aaa', 'UNIVERSE', '01040260253', 'bluebadger@naver.com', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_user VALUES(0, 'tester', 'mynickname', 'tester', 'tester', 'UNIVERSE', '01040260253', 'test@gmail.com', 1, CURDATE(), CURRENT_TIMESTAMP());
-- shop_category
INSERT INTO shop_category VALUES(0, 'Bedroom', 1);
INSERT INTO shop_category VALUES(0, 'Kitchen', 1);
INSERT INTO shop_category VALUES(0, 'Bathroom', 1);
INSERT INTO shop_category VALUES(0, 'Outdoor', 1);
INSERT INTO shop_category VALUES(0, 'Decorations', 1);
INSERT INTO shop_category VALUES(0, 'Dining', 1);
INSERT INTO shop_category VALUES(0, 'Living', 1);
INSERT INTO shop_category VALUES(0, 'Lighting', 1);
INSERT INTO shop_category VALUES(0, 'Storage', 1);
INSERT INTO shop_category VALUES(0, 'etc', 1);
-- shop_product
INSERT INTO shop_product VALUES(0, 1, 'apple', 100, 10, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 2, 'leaves', 5, 10, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 10, 'pizza', 1500, 15, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 8, 'ballons', 120, 3, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 9, 'money', 10000, 3, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 4, 'wine', 1900, 1, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 10, 'vegetables', 1000, 6, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 5, 'orange', 150, 9, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 3, 'aqua', 100, 50, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 6, 'cola', 500, 8, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_product VALUES(0, 7, 'mac', 100000, 2, 'explain', 1, CURDATE(), CURRENT_TIMESTAMP());

-- shop_order
INSERT INTO shop_order VALUES(0, 1, 10240, 1, CURDATE(), CURDATE(), CURRENT_TIMESTAMP());
-- shop_order_detail
INSERT INTO shop_order_detail VALUES(0, 1, 4, 2, CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_order_detail VALUES(0, 1, 5, 1, CURDATE(), CURRENT_TIMESTAMP());
-- shop_comment
INSERT INTO shop_comment VALUES(0, 4, 1, 1, 4, 'soso', CURDATE(), CURRENT_TIMESTAMP());
INSERT INTO shop_comment VALUES(0, 5, 1, 2, 3, 'ummmm.......', CURDATE(), CURRENT_TIMESTAMP());

-- # DROP TABLES # --
DROP TABLE shop_comment;
DROP TABLE shop_order_detail;
DROP TABLE shop_order;
DROP TABLE shop_product;
DROP TABLE shop_category;
DROP TABLE shop_user;



-- # DELETE DATA # --
DELETE FROM shop_order_detail;
DELETE FROM shop_order;
DELETE FROM shop_product;
DELETE FROM shop_category;
DELETE FROM shop_user;
DELETE FROM shop_comment;

ALTER TABLE shop_order_detail AUTO_INCREMENT = 1;
ALTER TABLE shop_order AUTO_INCREMENT = 1;
ALTER TABLE shop_product AUTO_INCREMENT = 1;
ALTER TABLE shop_category AUTO_INCREMENT = 1;
ALTER TABLE shop_user AUTO_INCREMENT = 1;
ALTER TABLE shop_comment AUTO_INCREMENT = 1;



-- main select
SELECT p.*, IFNULL(p.stock_quantity - d.order_quantity, p.stock_quantity) AS extra
FROM shop_product p 
LEFT JOIN shop_order_detail d
ON p.id = d.product_id
ORDER BY p.inp_date, p.id DESC
LIMIT 6;

SELECT p.id, c.name as category, p.name, p.price, IFNULL(p.stock_quantity - d.order_quantity, p.stock_quantity) AS stock_quantity
FROM shop_product p
LEFT JOIN shop_order_detail d
ON p.id = d.product_id
JOIN shop_category c
On p.category_id = c.id
ORDER BY p.inp_date, p.id DESC
LIMIT 6;

-- list
SELECT p.id as id, p.category_id, c.name as category, p.name as name, p.price as price, IFNULL(p.stock_quantity - d.order_quantity, p.stock_quantity) AS stock_quantity,  p.details as details
FROM shop_product p
LEFT JOIN shop_order_detail d
ON p.id = d.product_id
JOIN shop_category c
On p.category_id = c.id
WHERE 
p.name LIKE '%a%'
AND p.category_id = 1
GROUP BY p.id
ORDER BY p.inp_date ASC

-- paging
SELECT COUNT(*) totalcount 
FROM shop_product;


-- user purchase history
SELECT u.id, d.product_id
FROM shop_order_detail d
LEFT JOIN shop_order o
IN d.order_id = o.id
LEFT JOIN shop_user u
IN o.user_id = u.id
GROUP BY d.product_id
ORDER BY d.product_id ASC

-- comment select??
SELECT u.nick_name, d.product_id, o.user_id, d.id
FROM shop_comment c
JOIN shop_user u
ON u.id = c.user_id
LEFT JOIN shop_order o
ON o.user_id = c.user_id
JOIN shop_order_detail d
ON d.order_id = o.id
GROUP BY d.product_id
HAVING d.product_id = 4
-- comment select
SELECT u.nick_name, c.*
FROM shop_comment c
JOIN shop_user u
ON u.id = c.user_id
LEFT JOIN shop_order o
ON o.user_id = c.user_id
JOIN shop_order_detail d
ON d.order_id = o.id
GROUP BY d.product_id
HAVING d.product_id = 4
ORDER BY inp_date DESC, upd_date DESC



