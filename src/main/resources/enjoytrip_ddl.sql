-- 데이터베이스 새로 생성
CREATE DATABASE  IF NOT EXISTS `enjoytrip`;
USE `enjoytrip`;

-- -----------------------------------------------------
-- Table `enjoytrip`.`members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `members`;

CREATE TABLE IF NOT EXISTS `members` (
  `id` VARCHAR(16) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `mbti` VARCHAR(4) DEFAULT NULL,
  `introduction` VARCHAR(45) DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `enjoytrip`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board` ;

CREATE TABLE IF NOT EXISTS `board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_members_id_fk` (`id` ASC) VISIBLE,
  CONSTRAINT `board_to_members_id_fk`
    FOREIGN KEY (`id`)
    REFERENCES `enjoytrip`.`members` (`id`)
);

-- -----------------------------------------------------
-- Table `enjoytrip`.`inquiry`
-- -----------------------------------------------------    
DROP TABLE IF EXISTS `enjoytrip`.`inquiry` ;

CREATE TABLE IF NOT EXISTS `inquiry` (
  `inquiry_no` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) NULL DEFAULT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`inquiry_no`)
);

-- -----------------------------------------------------
-- Table `enjoytrip`.`file_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`file_info` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`file_info` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `accompany_no` INT NULL,
  `save_folder` VARCHAR(45) NULL,
  `original_file` VARCHAR(50) NULL,
  `save_file` VARCHAR(50) NULL,
  PRIMARY KEY (`idx`),
  CONSTRAINT `file_info_to_accompany_accompany_no_fk`
    FOREIGN KEY (`accompany_no`)
    REFERENCES `enjoytrip`.`accompany` (`accompany_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `enjoytrip`.`accompany`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accompany` ;

CREATE TABLE IF NOT EXISTS `accompany` (
  `accompany_no` INT NOT NULL AUTO_INCREMENT,
  `accompany_title` VARCHAR(20) NOT NULL,  
  `accompany_content` VARCHAR(100) NOT NULL,
  `accompany_loc` VARCHAR(45) NOT NULL,
  `accompany_date` TIMESTAMP NOT NULL,
  `accompany_num` INT NOT NULL DEFAULT 1,
  `accompany_total` INT NOT NULL,
  `accompany_photo` VARCHAR(150) DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `id` VARCHAR(16) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`accompany_no`)
);

-- -----------------------------------------------------
-- Table `enjoytrip`.`accompany_join`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accompany_join` ;

CREATE TABLE IF NOT EXISTS `accompany_join` (
  `accompany_no` INT NOT NULL,
  `id` VARCHAR(16) NOT NULL
);

ALTER TABLE `enjoytrip`.`members` 
CHANGE COLUMN `password` `password` VARCHAR(256) NOT NULL ;

ALTER TABLE `enjoytrip`.`members` 
ADD COLUMN `salt` VARCHAR(16) NOT NULL AFTER `role`;

-- -----------------------------------------------------
-- Table `enjoytrip`.`accompany_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accompany_comment` ;

CREATE TABLE IF NOT EXISTS `accompany_comment` (
  `comment_no` INT NOT NULL AUTO_INCREMENT,
  `accompany_no` INT NOT NULL,  
  `id` VARCHAR(16) NOT NULL,
  `comment_content` VARCHAR(100) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_no`)
);