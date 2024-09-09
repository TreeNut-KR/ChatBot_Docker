GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '1234' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS chatbot;


-- 유저
CREATE TABLE users (
    idx INT AUTO_INCREMENT,
    idx INT AUTO_INCREMENT,
    userid VARCHAR(50),
    username VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(255),
    access_token text,
    refresh_token text,
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW(),
    PRIMARY KEY(idx)
    PRIMARY KEY(idx)
) ENGINE=InnoDB CHARSET=utf8mb4;

-- 캐릭터
CREATE TABLE characters (
    
    idx INT AUTO_INCREMENT,
    uuid CHAR(36) UNIQUE DEFAULT UUID(),
    useridx VARCHAR(50),
    character_name VARCHAR(30),
    character_setting VARCHAR(255),
    description VARCHAR(255),
    greeting TEXT,
    accesslevel int,
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW(),
    PRIMARY KEY(characters_pk),
    FOREIGN KEY (creater) REFERENCES users(idx)
) ENGINE=InnoDB CHARSET=utf8mb4;



-- -- 채팅방
-- CREATE TABLE chatroom (
--     chatroom_pk INT AUTO_INCREMENT,
--     users_idx INT,
--     characters_pk INT,
--     mongo_chatlog VARCHAR(100),
--     created_at DATETIME DEFAULT NOW(),
--     updated_at DATETIME DEFAULT NOW(),


--     PRIMARY KEY(chatroom_pk),
--     FOREIGN KEY (users_idx) REFERENCES users(id), /*외부키 설정*/
--     FOREIGN KEY (characters_pk) REFERENCES characters(characters_pk) /*외부키 설정*/
-- ) ENGINE=InnoDB CHARSET=utf8mb4;


USE chatbot;
