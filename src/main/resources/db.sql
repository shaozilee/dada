/*
 Navicat MySQL Data Transfer

 Source Server         : linux
 Source Server Version : 50173
 Source Host           : 192.168.0.136
 Source Database       : dada

 Target Server Version : 50173
 File Encoding         : utf-8

 Date: 04/27/2016 22:32:05 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `common_member`
-- ----------------------------
DROP TABLE IF EXISTS `common_member`;
CREATE TABLE `common_member` (
  `uid` mediumint(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(15) NOT NULL,
  `password` char(32) NOT NULL,
  `email` char(40) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `emailstatus` tinyint(1) NOT NULL DEFAULT '0',
  `regdate` int(10) unsigned NOT NULL DEFAULT '0',
  `credits` int(10) NOT NULL DEFAULT '0',
  `lastloginip` int(10) NOT NULL DEFAULT '0',
  `lastlogintime` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `forum_access`
-- ----------------------------
DROP TABLE IF EXISTS `forum_access`;
CREATE TABLE `forum_access` (
  `uid` mediumint(8) unsigned NOT NULL,
  `fid` mediumint(8) unsigned NOT NULL,
  `allowview` tinyint(1) NOT NULL DEFAULT '0',
  `allowpost` tinyint(1) NOT NULL DEFAULT '0',
  `allowreply` tinyint(1) NOT NULL DEFAULT '0',
  `allowgetattach` tinyint(1) NOT NULL DEFAULT '0',
  `allowpostattach` tinyint(1) NOT NULL DEFAULT '0',
  `allowpostimage` tinyint(1) NOT NULL DEFAULT '0',
  `adminuser` tinyint(1) NOT NULL DEFAULT '0',
  `dateline` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`,`fid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `forum_announcemen`
-- ----------------------------
DROP TABLE IF EXISTS `forum_announcemen`;
CREATE TABLE `forum_announcemen` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `author` varchar(15) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `type` tinyint(1) NOT NULL DEFAULT '0',
  `displayorder` tinyint(3) NOT NULL DEFAULT '0',
  `starttime` int(10) NOT NULL DEFAULT '0',
  `endtime` int(10) NOT NULL DEFAULT '0',
  `message` text NOT NULL,
  `groups` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `forum_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `forum_attachment`;
CREATE TABLE `forum_attachment` (
  `aid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `tid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `pid` int(10) unsigned NOT NULL DEFAULT '0',
  `width` smallint(6) unsigned NOT NULL DEFAULT '0',
  `dateline` int(10) unsigned NOT NULL DEFAULT '0',
  `readperm` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `price` smallint(6) unsigned NOT NULL DEFAULT '0',
  `filename` char(100) NOT NULL,
  `filetype` char(50) NOT NULL,
  `filesize` int(10) unsigned NOT NULL DEFAULT '0',
  `attachment` char(100) NOT NULL,
  `downloads` char(100) NOT NULL DEFAULT '0',
  `isimage` tinyint(1) NOT NULL,
  `uid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `thumb` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `remote` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `picid` mediumint(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`aid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `forum_post`
-- ----------------------------
DROP TABLE IF EXISTS `forum_post`;
CREATE TABLE `forum_post` (
  `pid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `tid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `author` varchar(15) NOT NULL,
  `authorid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `subject` varchar(80) NOT NULL,
  `dateline` int(10) unsigned NOT NULL DEFAULT '0',
  `message` mediumtext NOT NULL,
  `useip` varchar(15) NOT NULL,
  `invisible` tinyint(1) NOT NULL DEFAULT '0',
  `anonymous` tinyint(1) NOT NULL DEFAULT '0',
  `useig` tinyint(1) NOT NULL DEFAULT '0',
  `attachment` tinyint(1) NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `tags` varchar(255) NOT NULL DEFAULT '0',
  `position` int(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pid`,`position`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `forum_postcomment`
-- ----------------------------
DROP TABLE IF EXISTS `forum_postcomment`;
CREATE TABLE `forum_postcomment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `pid` int(10) unsigned NOT NULL DEFAULT '0',
  `author` varchar(15) NOT NULL,
  `authorid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `dateline` int(10) unsigned NOT NULL DEFAULT '0',
  `comment` varchar(255) NOT NULL,
  `score` tinyint(1) NOT NULL DEFAULT '0',
  `useip` varchar(15) NOT NULL DEFAULT '0',
  `rpid` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `forum_postposition`
-- ----------------------------
DROP TABLE IF EXISTS `forum_postposition`;
CREATE TABLE `forum_postposition` (
  `tid` mediumint(8) unsigned NOT NULL,
  `position` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`tid`,`position`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `forum_thread`
-- ----------------------------
DROP TABLE IF EXISTS `forum_thread`;
CREATE TABLE `forum_thread` (
  `tid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `fid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `readperm` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `price` smallint(6) NOT NULL,
  `author` char(15) NOT NULL,
  `authorid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `subject` char(80) NOT NULL,
  `dateline` int(10) unsigned NOT NULL DEFAULT '0',
  `lastpost` int(10) unsigned NOT NULL DEFAULT '0',
  `lastposter` char(15) NOT NULL,
  `views` int(10) unsigned NOT NULL DEFAULT '0',
  `replies` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `displayorder` tinyint(1) NOT NULL DEFAULT '0',
  `highlight` tinyint(1) NOT NULL DEFAULT '0',
  `digest` tinyint(1) NOT NULL DEFAULT '0',
  `attachment` tinyint(1) NOT NULL DEFAULT '0',
  `closed` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `stickreply` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `status` smallint(6) unsigned NOT NULL DEFAULT '0',
  `favtimes` mediumint(8) NOT NULL DEFAULT '0',
  `sharetimes` mediumint(8) NOT NULL DEFAULT '0',
  `stamp` tinyint(3) NOT NULL DEFAULT '-1',
  `icon` tinyint(3) NOT NULL DEFAULT '-1',
  `pushedaid` mediumint(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
