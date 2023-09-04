CREATE TABLE FCSDB.fee (
id INT PRIMARY KEY AUTO_INCREMENT,
amount INT,
generated_date VARCHAR(7),
paid BOOLEAN DEFAULT FALSE,
owner_member_id INT NOT NULL,
FOREIGN KEY (owner_member_id) REFERENCES FCSDB.member(id)
)