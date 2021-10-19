CREATE SCHEMA IF NOT EXISTS `tcc_db`;
CREATE TABLE IF NOT EXISTS `tcc_db`.`user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(1) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `tcc_db`.`product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);
