CREATE TABLE FCSDB.member (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
surname VARCHAR(30) NOT NULL,
category VARCHAR(1) NOT NULL,
address VARCHAR(50) NOT NULL,
phone VARCHAR(40) NOT NULL,
email VARCHAR(40)
)