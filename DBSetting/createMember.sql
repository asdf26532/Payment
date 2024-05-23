-- 1. 회원 테이블 생성
CREATE TABLE member (
    id INT NOT NULL auto_increment PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    address VARCHAR(256) NOT NULL,
    email VARCHAR(100) NOT NULL,
    userid VARCHAR(50) UNIQUE NOT NULL,
    userpw VARCHAR(256) NOT NULL,
    nick VARCHAR(50) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE message (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    content VARCHAR(255) NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transport (
    message_id INT NOT NULL,
    member_id INT NOT NULL,
    PRIMARY KEY (message_id, member_id),
    FOREIGN KEY (message_id) REFERENCES message (id),
    FOREIGN KEY (member_id) REFERENCES member (id)
);
