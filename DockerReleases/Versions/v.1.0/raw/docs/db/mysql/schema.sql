
CREATE TABLE `availability` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `competence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `role_id` int(11) DEFAULT '2',
  PRIMARY KEY (`id`),
  CONSTRAINT `role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);




DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `date_of_registration` datetime DEFAULT CURRENT_TIMESTAMP,
  `status_id` int(11) DEFAULT '0',
  `availability_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `existing_availability` FOREIGN KEY (`availability_id`) REFERENCES `availability` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `existing_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `existing_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE `competence_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) NOT NULL,
  `competence_id` int(11) NOT NULL,
  `years_of_experience` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `competence` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `existing_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);