CREATE DATABASE IF NOT EXISTS `lifeedge`;
USE `lifeedge`;

-- 用户表
CREATE TABLE user
(
    id          VARCHAR(64) PRIMARY KEY,
    picture_url VARCHAR(256),
    username    VARCHAR(128) NOT NULL
);

-- 提醒表
CREATE TABLE `reminder`
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    type         INT COMMENT '暂定 0:普通提醒 1:计算题 2:摇一摇 3:小游戏, 详见common文件夹的常量',
    remind_at    BIGINT,
    repeat_times INT
);

-- 事件表
CREATE TABLE event
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(128) NOT NULL,
    description VARCHAR(2096),
    begin_at    BIGINT,
    end_at      BIGINT,
    color       INT,
    reminder_id BIGINT,
    owner_id    VARCHAR(64),
    FOREIGN KEY (reminder_id) REFERENCES reminder (id),
    FOREIGN KEY (owner_id) REFERENCES user (id)
);

-- 作息教程表
CREATE TABLE tutorial
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(128)  NOT NULL,
    description VARCHAR(2096) NOT NULL
);
