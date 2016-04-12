CREATE TABLE forum_comment
(
    cid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    pid INT NOT NULL,
    uid INT NOT NULL,
    message VARCHAR(4000) NOT NULL,
    dateline VARCHAR(30) NOT NULL,
    rcid INT,
    status INT DEFAULT 0 NOT NULL
);
CREATE TABLE forum_permit
(
    pid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(2000)
);
CREATE TABLE forum_post
(
    pid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    sid INT NOT NULL,
    uid INT NOT NULL,
    subject VARCHAR(100) NOT NULL,
    message VARCHAR(4000) NOT NULL,
    dateline VARCHAR(30) NOT NULL,
    status INT DEFAULT 0 NOT NULL,
    viewcount INT DEFAULT 0 NOT NULL,
    commentcount INT DEFAULT 0 NOT NULL
);
CREATE TABLE forum_role
(
    rid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(2000)
);
CREATE TABLE forum_role_permit
(
    rid INT NOT NULL,
    pid INT NOT NULL,
    rpid INT PRIMARY KEY NOT NULL AUTO_INCREMENT
);
CREATE TABLE forum_section
(
    sid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    status INT DEFAULT 0 NOT NULL,
    displayorder INT,
    notice VARCHAR(1000),
    title VARCHAR(100),
    parentsid INT
);
CREATE TABLE forum_user
(
    uid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rid INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(200),
    tel VARCHAR(20),
    status INT DEFAULT 0 NOT NULL
);
CREATE INDEX pid ON forum_comment (pid);
CREATE INDEX rcid ON forum_comment (rcid);
CREATE INDEX uid ON forum_comment (uid);
CREATE UNIQUE INDEX unique_name ON forum_permit (name);
CREATE INDEX sid ON forum_post (sid);
CREATE INDEX uid ON forum_post (uid);
CREATE UNIQUE INDEX unique_name ON forum_role (name);
CREATE INDEX pid ON forum_role_permit (pid);
CREATE INDEX rid ON forum_role_permit (rid);
CREATE INDEX parentsid ON forum_section (parentsid);
CREATE UNIQUE INDEX unique_email ON forum_user (email);
CREATE UNIQUE INDEX unique_name ON forum_user (name);
CREATE UNIQUE INDEX unique_tel ON forum_user (tel);
CREATE INDEX rid ON forum_user (rid);
