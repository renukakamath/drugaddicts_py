/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - py_drug_addicts_counseling
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`py_drug_addicts_counseling` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `py_drug_addicts_counseling`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `file` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `blog` */

insert  into `blog`(`blog_id`,`user_id`,`file`,`description`,`date`) values 
(1,3,'static/uploads/b8d0cf0f-e238-4fc9-b5bb-0384e674b72aabc.jpg','uuuu','2022-03-07'),
(2,2,'static/uploads/31d6735c-2045-4545-a015-b24ee864187eabc.jpg','hai','2023-02-05'),
(3,2,'static/uploads/2995389e-bc40-4a3c-abae-c580032fa58eabc.jpg','hai','2023-02-05'),
(4,2,'static/uploads/274a35a4-5e61-4c28-997b-45fbd3872cc6abc.jpg','hai','2023-02-05'),
(5,2,'static/uploads/3fd241e0-1f45-405f-a2d9-bd9c4551d200abc.jpg','hai','2023-02-05'),
(6,2,'static/uploads/8c3d2338-4c7a-4d70-b48d-72f583032000abc.jpg','hai','2023-02-05'),
(7,2,'static/uploads/a3e51085-753c-44d4-9286-e418b2ff1d16abc.jpg','hai','2023-02-05'),
(8,2,'static/uploads/1aa4fd18-900a-427d-87cb-3bf3a1bdd04dabc.jpg','hai','2023-02-05'),
(9,2,'static/uploads/82fcea34-f16a-4dac-beda-c3f67f440658abc.jpg','hai','2023-02-05'),
(10,2,'static/uploads/c2fe417e-7b7d-47b7-ad67-e2bf3094de49abc.jpg','hai','2023-02-05'),
(11,2,'static/uploads/6d0f733e-ae2b-4b8b-85fe-a3a819ba0b9eabc.jpg','hai','2023-02-05'),
(12,2,'static/uploads/94a76dad-1ff8-4d2e-84f7-8d58ac3c7251abc.jpg','hai','2023-02-05'),
(13,2,'static/uploads/313d592e-5dd6-4e06-ab94-016c3a84c5d5abc.jpg','hai','2023-02-05'),
(14,2,'static/uploads/650f2257-4372-4aec-823a-9070638899ffabc.jpg','hai','2023-02-05'),
(15,2,'static/uploads/70536a98-ea9e-4a5f-937a-94c5dc4ba0e0abc.jpg','hai','2023-02-05'),
(16,2,'static/uploads/4aa20a3c-2e00-4a29-af11-3438cfe29291abc.jpg','hai','2023-02-05'),
(17,4,'static/uploads/445cd94e-666f-43a0-b78e-9005c2be653aabc.jpg','good','2023-02-05');

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `psychologist_id` int(11) DEFAULT NULL,
  `booking_date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

/*Data for the table `booking` */

insert  into `booking`(`booking_id`,`user_id`,`psychologist_id`,`booking_date`,`status`) values 
(1,1,1,'2022/11/22','booked'),
(2,2,1,'2022-02-23','booked'),
(27,2,1,'2022-02-24','booked'),
(28,3,1,'2022-02-28','booked'),
(29,3,1,'2022-03-07','booked'),
(30,4,1,'2023-02-05','booked'),
(31,4,1,'2023-02-05','booked'),
(32,4,2,'2023-02-05','booked'),
(33,4,3,'2023-02-05','booked'),
(34,1,3,'2023-02-06','booked'),
(35,1,1,'2023-02-06','booked');

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`sender_id`,`receiver_id`,`message`,`date`) values 
(1,3,2,'hi',NULL),
(2,2,3,'hi',NULL),
(3,3,2,'hello',NULL),
(4,3,2,'hello',NULL),
(5,3,2,'hello',NULL),
(6,3,2,'hello',NULL),
(7,3,2,'hello',NULL),
(8,8,3,'hh',NULL),
(9,8,3,'tt','2022-03-07'),
(10,3,8,'hhh',NULL),
(11,3,2,'hai','2023-02-05'),
(12,3,2,'hello','2023-02-05'),
(13,3,2,'hai','2023-02-05'),
(14,10,3,'good','2023-02-05'),
(15,10,9,'hai','2023-02-06');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `complaint` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`user_id`,`complaint`,`reply`,`date`) values 
(1,1,'hi','ok','2022/11/22');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'ann','ann','user'),
(3,'aa','aa','psychologist'),
(4,'mm','mm','psychologist'),
(7,'adhi','adhi','user'),
(8,'hy','hy','user'),
(9,'user','user','psychologist'),
(10,'hai','hai','user');

/*Table structure for table `medicine` */

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `medicine_id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `file` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`medicine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `medicine` */

insert  into `medicine`(`medicine_id`,`booking_id`,`details`,`file`) values 
(1,28,'ss','static/assets/img/767616df-530d-46fe-b805-5d8a15be59f9download (2).png'),
(2,27,'xcvb','static/assets/img/e62467c9-c8e0-471f-97e5-3c329b24bedfbike.jpg'),
(3,34,'xcvb','static/assets/img/b90d2b10-07e0-4cdc-840e-e22b3c4870aebike.jpg');

/*Table structure for table `meditation` */

DROP TABLE IF EXISTS `meditation`;

CREATE TABLE `meditation` (
  `meditation_id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `file` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`meditation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `meditation` */

insert  into `meditation`(`meditation_id`,`booking_id`,`details`,`file`) values 
(1,1,'trimol','static/assets/img/30b68b7a-fb97-454a-8b1d-1913963ca774download (1).jpg'),
(2,34,'dfghjkl','static/assets/img/607d12f8-c762-44c4-8e06-0b7109859312bike.jpg');

/*Table structure for table `psychologist` */

DROP TABLE IF EXISTS `psychologist`;

CREATE TABLE `psychologist` (
  `psychologist_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`psychologist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `psychologist` */

insert  into `psychologist`(`psychologist_id`,`login_id`,`firstname`,`lastname`,`email`,`phone`,`place`,`designation`) values 
(1,3,'Athira','k','iathira@gmail.com','9630011203','kochi','doctor'),
(2,4,'moni','a','q@gmail.com','9988776611','kollam','yduhue'),
(3,9,'user1','qwerty1','student1@gmail.com','92234567891','kerala1','dfghjk1');

/*Table structure for table `schedule` */

DROP TABLE IF EXISTS `schedule`;

CREATE TABLE `schedule` (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) DEFAULT NULL,
  `schedule_date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `schedule` */

insert  into `schedule`(`schedule_id`,`booking_id`,`schedule_date`) values 
(1,1,'2022-02-28'),
(2,28,'2022-02-22'),
(3,27,'2023-02-26'),
(4,27,'2023-03-04T01:59');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`firstname`,`lastname`,`email`,`phone`,`place`) values 
(1,2,'ammu','k','a@gmail.cm','9977665544','kochi'),
(2,7,'Adhithya ','Kumar ','Adhi@gmail.com ','9988665544','kochi'),
(3,8,'eheh','shsy','sheue@udjd.sns','8976788766','ysys'),
(4,10,'hai','hello','hai@gmail.com','1234556789','eekm');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
