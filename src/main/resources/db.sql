-- MySQL Script generated by MySQL Workbench
-- 05/09/16 11:06:42
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dada
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dada
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dada` DEFAULT CHARACTER SET utf8;
USE `dada` ;

-- -----------------------------------------------------
-- Table `dada`.`common_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dada`.`common_member` ;

CREATE TABLE IF NOT EXISTS `dada`.`common_member` (
  `uid` INT  NOT NULL AUTO_INCREMENT,
  `userName` CHAR(15) NOT NULL,
  `password` CHAR(32) NOT NULL,
  `email` CHAR(40),
  `status` BIT NOT NULL DEFAULT 0,
  `emailStatus` BIT NOT NULL DEFAULT 0,
  `regDate` BIGINT  NOT NULL DEFAULT 0,
  `credits` INT NOT NULL DEFAULT 0,
  `lastLoginIp` INT NOT NULL DEFAULT 0,
  `lastLoginTime` BIGINT  NOT NULL DEFAULT 0,
  PRIMARY KEY (`uid`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dada`.`forum_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dada`.`forum_post` ;

CREATE TABLE IF NOT EXISTS `dada`.`forum_post` (
  `pid` INT NOT NULL AUTO_INCREMENT,
  `tid` INT NOT NULL DEFAULT 0,
  `authorName` VARCHAR(15) NOT NULL,
  `authorId` INT NOT NULL DEFAULT 0,
  `subject` VARCHAR(80) NOT NULL,
  `dateLine` BIGINT NOT NULL DEFAULT 0,
  `message` MEDIUMTEXT NOT NULL,
  `useIp` VARCHAR(15) NOT NULL DEFAULT 0,
  `invisible` BIT NOT NULL DEFAULT 0,
  `anonymous` BIT NOT NULL DEFAULT 0,
  `status` BIT NOT NULL DEFAULT 0,
  `tags` VARCHAR(255) NOT NULL DEFAULT 0,
  `ppid` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`pid`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dada`.`forum_topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dada`.`forum_topic` ;

CREATE TABLE IF NOT EXISTS `dada`.`forum_topic` (
  `tid` INT  NOT NULL AUTO_INCREMENT,
  `readPerm` INT  NOT NULL DEFAULT 0,
  `authorName` CHAR(15) NOT NULL,
  `authorId` INT  NOT NULL DEFAULT 0,
  `subject` CHAR(80) NOT NULL,
  `message` MEDIUMTEXT NOT NULL,
  `dateLine` BIGINT  NOT NULL DEFAULT 0,
  `lastPost` BIGINT  NOT NULL DEFAULT 0,
  `lastPoster` CHAR(15) NOT NULL,
  `views` INT NOT NULL DEFAULT 0,
  `replies` INT  NOT NULL DEFAULT 0,
  `displayOrder` INT NOT NULL DEFAULT 0,
  `highLight` BIT NOT NULL DEFAULT 0,
  `digest` BIT NOT NULL DEFAULT 0,
  `closed` INT  NOT NULL DEFAULT 0,
  `stickReply` BIT  NOT NULL DEFAULT 0,
  `status` INT  NOT NULL DEFAULT 0,
  `favTimes` INT NOT NULL DEFAULT 0,
  `shareTimes` INT NOT NULL DEFAULT 0,
  `stamp` INT NOT NULL DEFAULT -1,
  `icon` INT NOT NULL DEFAULT -1,
  PRIMARY KEY (`tid`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
