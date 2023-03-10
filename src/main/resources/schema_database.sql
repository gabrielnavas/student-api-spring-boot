CREATE TABLE CLIENT (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  CPF VARCHAR(11),
  NAME VARCHAR(100)
);

CREATE TABLE PRODUCT (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  DESCRIPTION VARCHAR(100)
);

CREATE TABLE ORDER (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  CLIENT_ID INTEGER REFERENCES CLIENT (ID),
  CREATED_AT TIMESTAMP,
  TOTAL NUMERIC(20, 2),
  STATUS VARCHAR(20)
);

CREATE TABLE ITEM_ORDER (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  ORDER_ID INTEGER REFERENCES ORDER (ID),
  PRODUCT_ID INTEGER REFERENCES PRODUCT (ID),
  AMOUNT INTEGER,
  PRICE NUMERIC(20, 2)
);