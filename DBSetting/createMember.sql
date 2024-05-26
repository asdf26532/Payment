-- 1. 회원 테이블
CREATE TABLE member
(
    id      INT                NOT NULL auto_increment PRIMARY KEY,
    name    VARCHAR(30)        NOT NULL,
    address VARCHAR(256)       NOT NULL,
    email   VARCHAR(100)       NOT NULL,
    userid  VARCHAR(50) UNIQUE NOT NULL,
    userpw  VARCHAR(256)       NOT NULL,
    nick    VARCHAR(50)        NOT NULL,
    phone   VARCHAR(20) UNIQUE NOT NULL
);

-- 2. 채팅 메세지 보관 테이블
CREATE TABLE message
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sender_id   INT          NOT NULL,
    receiver_id INT          NOT NULL,
    content     VARCHAR(255) NOT NULL,
    timestamp   DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 3. delivery 테이블
CREATE TABLE delivery
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    status  VARCHAR(30) DEFAULT '결제 전'
);

-- 4. orderitem 테이블
CREATE TABLE orderitem
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    order_id     INT UNIQUE     NOT NULL,
    product_name VARCHAR(100)   NOT NULL,
    count        INT DEFAULT 1,
    price        DECIMAL(10, 2) NOT NULL
);

-- 5. orders 테이블
CREATE TABLE orders
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    member_id    INT        NOT NULL,
    delivery_id  INT UNIQUE NOT NULL,
    orderitem_id INT,
    order_date   DATETIME DEFAULT CURRENT_TIMESTAMP,
    status       VARCHAR(30),
    count        INT      DEFAULT 0,
    CONSTRAINT fk_orders_member FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT fk_orders_orderitem FOREIGN KEY (orderitem_id) REFERENCES orderitem (order_id),
    CONSTRAINT fk_orders_delivery FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

-- 6. cart 뷰
CREATE VIEW cart AS
SELECT o.id                 AS order_id,
       m.id                 AS member_id,
       d.id                 AS delivery_id,
       m.name               AS member_name,
       m.email              AS member_email,
       m.address            AS member_address,
       m.phone              AS member_phone,
       oi.product_name,
       oi.order_id          AS oiorderid,
       oi.price             AS unit_price,
       (oi.price * o.count) AS total_price,
       (o.count)            AS count,
       o.status             AS order_status,
       d.address            AS address,
       d.status             AS delivery_status
FROM orders o
         JOIN
     member m ON o.member_id = m.id
         JOIN
     orderitem oi ON o.orderitem_id = oi.order_id
         JOIN
     delivery d ON o.delivery_id = d.id;

-- 7. 게시판 테이블
CREATE TABLE board
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(50) NOT NULL,
    contents  TEXT        NOT NULL,
    member_id INT,
    type      INT         NOT NULL,
    category  VARCHAR(30) CHECK (category IN ('곡물', '과일', '채소', '사료 작물', '비료 작물', '원예 작물', '공예 작물')),
    soldout   TINYINT  DEFAULT 1 CHECK (soldout IN (0, 1)),
    w_date    DATETIME DEFAULT CURRENT_TIMESTAMP,
    v_count   INT      DEFAULT 0,
    secret    TINYINT  DEFAULT 0 CHECK (secret IN (0, 1)),
    CONSTRAINT fk_board_member FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT fk_board_type FOREIGN KEY (type) REFERENCES board_type (id)
);

-- 8. 게시판 타입 테이블
CREATE TABLE board_type
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

-- 9. 닉네임과 member_id를 동기화하기 위한 뷰
CREATE OR REPLACE VIEW member_board_view AS
SELECT b.id,
       m.nick,
       b.title,
       b.contents,
       b.type,
       b.category,
       b.soldout,
       b.w_date,
       b.v_count,
       b.secret,
       b.member_id
FROM board b
         JOIN
     member m ON b.member_id = m.id;

-- 10. 댓글 테이블
CREATE TABLE reply
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT  NOT NULL,
    board_id  INT  NOT NULL,
    contents  TEXT NOT NULL,
    w_date    DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reply_member FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT fk_reply_board FOREIGN KEY (board_id) REFERENCES board (id)
);

-- 11. 댓글의 닉네임 표기를 위한 뷰
CREATE OR REPLACE VIEW reply_view AS
SELECT m.nick,
       r.id,
       r.member_id,
       r.board_id,
       r.contents,
       r.w_date
FROM member m
         JOIN
     reply r ON r.member_id = m.id;