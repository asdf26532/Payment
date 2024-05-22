-- 1. 회원 테이블 생성
CREATE TABLE member (
    id INT NOT NULL auto_increment PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    address VARCHAR(256) NOT NULL,
    email VARCHAR(100) NOT NULL,
    userid VARCHAR(50) UNIQUE NOT NULL,
    userpw VARCHAR(256) NOT NULL,
    nick VARCHAR(50) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    CONSTRAINT fk_member_id FOREIGN KEY (id) REFERENCES member_id_seq(id)
);
