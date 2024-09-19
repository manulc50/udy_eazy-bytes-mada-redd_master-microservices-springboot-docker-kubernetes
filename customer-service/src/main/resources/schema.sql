CREATE TABLE IF NOT EXISTS `customers` (
  `id` BIGINT AUTO_INCREMENT  PRIMARY KEY,
  `name` VARCHAR(30) NOT NULL,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `mobile_number` CHAR(10) NOT NULL UNIQUE,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` VARCHAR(40) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` VARCHAR(40) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
  `number` BIGINT PRIMARY KEY,
  `type` VARCHAR(50) NOT NULL,
  `branch_address` VARCHAR(100) NOT NULL,
  `customer_id` BIGINT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(40) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` VARCHAR(40) DEFAULT NULL,
  CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
);
