CREATE TABLE IF NOT EXISTS `loans` (
  `number` BIGINT NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `total` NUMERIC(10,2) NOT NULL,
  `amount_paid` NUMERIC(10,2) NOT NULL,
  `outstanding_amount` NUMERIC(10,2) NOT NULL,
  `customer_mobile_number` CHAR(10) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` VARCHAR(40) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` VARCHAR(40) DEFAULT NULL,
  PRIMARY KEY (`number`)
);