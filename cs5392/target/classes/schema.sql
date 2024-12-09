

CREATE TABLE IF NOT EXISTS `User` (
  `ID` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `UserName` varchar(128) NOT NULL,
  `Password` VARCHAR(100) NOT NULL,
  `FirstName` varchar(64) DEFAULT NULL,
  `LastName` varchar(64) DEFAULT NULL,
  `Email` varchar(128) DEFAULT NULL,
  `Type` varchar(45) DEFAULT NULL,
  UNIQUE KEY `UserName_UNIQUE` (`UserName`) 
);

CREATE TABLE IF NOT EXISTS `Role` (
    `ID` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) UNIQUE NOT NULL
);


CREATE TABLE IF NOT EXISTS `UserRoles` (
    `UserRoleID` BIGINT AUTO_INCREMENT PRIMARY KEY, -- Added a primary key for unique identification
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `User`(`ID`) ON DELETE CASCADE, -- Ensures user-role mappings are removed when the user is deleted
    FOREIGN KEY (`role_id`) REFERENCES `Role`(`ID`) ON DELETE CASCADE, -- Ensures mappings are removed when the role is deleted
    UNIQUE KEY `UserRole_Unique` (`user_id`, `role_id`) -- Prevent duplicate entries
);

CREATE TABLE IF NOT EXISTS `Node` (
  `NodeID` VARCHAR(64) NOT NULL,
  `NetworkID` VARCHAR(64) NOT NULL,
  `LeftNeighborID` VARCHAR(64) NOT NULL,
  `RightNeighborID` VARCHAR(64) NOT NULL,
  `InboxID` VARCHAR(64) NOT NULL,
  `StoreID` VARCHAR(64) NOT NULL,
  `Status` TINYINT NOT NULL,
  PRIMARY KEY (`NodeID`),
  UNIQUE KEY `NodeID_UNIQUE` (`NodeID`)
);

CREATE TABLE IF NOT EXISTS `Message` (
  `MessageID` varchar(64) NOT NULL,
  `SenderID` varchar(64) NOT NULL,
  `ReceiverID` varchar(64) NOT NULL,
  `TimeStampCreated` datetime NOT NULL,
  `TimeStampReceived` datetime NOT NULL,
  `Direction` varchar(45) NOT NULL,
  `Path` varchar(256) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Message` varchar(256) NOT NULL,
  PRIMARY KEY (`MessageID`),
  UNIQUE KEY `MessageID_UNIQUE` (`MessageID`)
);

CREATE TABLE IF NOT EXISTS `Inbox` (
  `ID` BIGINT AUTO_INCREMENT PRIMARY KEY, -- Auto-increment primary key
  `InboxID` VARCHAR(64) NOT NULL,
  `MessageID` VARCHAR(64) NOT NULL,
  `TimeReceived` DATETIME DEFAULT NULL,
  `OwnerNodeID` VARCHAR(64) DEFAULT NULL,
  `InboxSize` INT DEFAULT NULL,
  UNIQUE KEY `Inbox_Unique` (`InboxID`, `MessageID`) -- Ensures the combination is unique
);

CREATE TABLE IF NOT EXISTS `Store` (
  `ID` BIGINT AUTO_INCREMENT PRIMARY KEY, -- Auto-increment primary key
  `StoreID` varchar(64) NOT NULL,
  `MessageID` varchar(64) NOT NULL,
  `TimeReceived` datetime DEFAULT NULL,
  `OwnerNodeID` varchar(64) DEFAULT NULL,
  UNIQUE KEY `Store_Unique` (`StoreID`, `MessageID`) -- Ensures the combination is unique
);