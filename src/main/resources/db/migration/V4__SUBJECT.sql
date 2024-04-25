CREATE TABLE `SUBJECT` (
	`ID` SERIAL NOT NULL COMMENT 'ID',
	`SCHOOL_CD` CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`CD` CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`NAME` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	PRIMARY KEY (`SCHOOL_CD`,`CD`)
) ENGINE=InnoDB;