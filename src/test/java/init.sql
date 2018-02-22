USE o2o;

CREATE TABLE Area(
	`areaId` INT(2) NOT NULL AUTO_INCREMENT,
	`areaName` VARCHAR(200) NOT NULL,
	`priority` int(2) NOT NULL DEFAULT '0',
	`createTime` datetime DEFAULT NULL,
	`updateTime` datetime DEFAULT NULL,
	PRIMARY KEY (`areaId`),
	UNIQUE KEY `UK_AREA` (`areaName`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE PersonInfo(
	`userId` INT(10) NOT NULL AUTO_INCREMENT,
	`userName` VARCHAR(32) NOT NULL,
	`profileImg` VARCHAR(1024) DEFAULT NULL,
	`email` VARCHAR(1024) DEFAULT NULL,
	`gender` VARCHAR(2) DEFAULT NUll,
	`enableStatus` int(2) Not Null DEFAULT '0' COMMENT '0禁止使用1可以使用',
	`userType` int(2) NOT NULL DEFAULT '1',
	`createTime` datetime DEFAULT NULL,
	`updateTime` datetime DEFAULT NULL,
	PRIMARY KEY (`userId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `WeChatAuth`(
  `wechatAuthId` INT(10) NOT NULL AUTO_INCREMENT,
  `userId` INT(10) NOT NULL,
  `openId` VARCHAR(1024) NOT NULL,
  `createTime` DATETIME DEFAULT NULL,
  PRIMARY KEY(`wechatAuthId`),
  UNIQUE KEY (`openId`),
  CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY(`userId`) REFERENCES `PersonInfo`(`userId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `LocalAuth`(
  `localAuthId` int(10) NOT NULL AUTO_INCREMENT,
  `userId` INT(10) NOT NULL,
  `userName` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL ,
  `createTime` DATETIME DEFAULT NULL ,
  `updateTime` DATETIME DEFAULT NULL ,
  PRIMARY KEY (`localAuthId`),
  UNIQUE KEY `uk_local_profile`(`userName`),
  CONSTRAINT `fk_localauth_profile` FOREIGN KEY(`userId`) REFERENCES `PersonInfo`(`userId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `headLine` (
  `lineId` int(100) NOT NULL AUTO_INCREMENT,
  `lineName` varchar(1000) DEFAULT NULL,
  `lineLink` varchar(2000) NOT NULL,
  `lineImg` varchar(2000) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enableStatus` int(2) NOT NULL DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`lineId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `shopCategory` (
  `shopCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `shopCategoryName` varchar(100) NOT NULL DEFAULT '',
  `shopCategoryDesc` varchar(1000) DEFAULT '',
  `shopCategoryImg` varchar(2000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`shopCategoryId`),
  KEY `fk_shop_category_self` (`parentId`),
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parentId`) REFERENCES `shopCategory` (`shopCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

CREATE TABLE `shop` (
  `shopId` int(10) NOT NULL AUTO_INCREMENT,
  `ownerId` int(10) NOT NULL COMMENT '店铺创建人',
  `areaId` int(5) DEFAULT NULL,
  `shopCategoryId` int(11) DEFAULT NULL,
  `shopName` varchar(256) NOT NULL,
  `shopDesc` varchar(1024) DEFAULT NULL,
  `shopAddr` varchar(200) DEFAULT NULL,
  `phone` varchar(128)  DEFAULT NULL,
  `shopImg` varchar(1024) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `enableStatus` int(2) NOT NULL DEFAULT '0',
  `advice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shopId`),
  KEY `fk_shop_profile` (`ownerId`),
  KEY `fk_shop_area` (`areaId`),
  KEY `fk_shop_shopcate` (`shopCategoryId`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`areaId`) REFERENCES `Area` (`areaId`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`ownerId`) REFERENCES `personInfo` (`userId`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shopCategoryId`) REFERENCES `shopCategory` (`shopCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `productCategory` (
  `productCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `productCategoryName` varchar(100) NOT NULL,
  `priority` int(2) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `shopId` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`productCategoryId`),
  KEY `fk_procate_shop` (`shopId`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shopId`) REFERENCES `shop` (`shopId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `productId` int(100) NOT NULL AUTO_INCREMENT,
  `productName` varchar(100) NOT NULL,
  `productDesc` varchar(2000) DEFAULT NULL,
  `imgAddr` varchar(2000) DEFAULT '',
  `normalPrice` varchar(100) DEFAULT NULL,
  `promotionPrice` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `enableStatus` int(2) NOT NULL DEFAULT '0',
  `productCategoryId` int(11) DEFAULT NULL,
  `shopId` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`productId`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`productCategoryId`) REFERENCES `productCategory` (`productCategoryId`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shopId`) REFERENCES `shop` (`shopId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `productImg` (
  `productImgId` int(20) NOT NULL AUTO_INCREMENT,
  `imgAddr` varchar(2000) NOT NULL,
  `imgDesc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `productId` int(20) DEFAULT NULL,
  PRIMARY KEY (`productImgId`),
  KEY `fk_proimg_product` (`productId`),
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;