-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: furbabyfinder
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `filter_pets`
--

DROP TABLE IF EXISTS `filter_pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_pets` (
  `filter_id` int(11) NOT NULL,
  `pet_id` int(11) NOT NULL,
  KEY `pet_id_idx` (`pet_id`),
  KEY `filter_id_idx` (`filter_id`),
  CONSTRAINT `filter_pets_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `filter_pets_ibfk_2` FOREIGN KEY (`filter_id`) REFERENCES `filters` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_pets`
--

LOCK TABLES `filter_pets` WRITE;
/*!40000 ALTER TABLE `filter_pets` DISABLE KEYS */;
/*!40000 ALTER TABLE `filter_pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filters`
--

DROP TABLE IF EXISTS `filters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filters` (
  `id` int(11) NOT NULL,
  `filter_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filters`
--

LOCK TABLES `filters` WRITE;
/*!40000 ALTER TABLE `filters` DISABLE KEYS */;
/*!40000 ALTER TABLE `filters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_images`
--

DROP TABLE IF EXISTS `pet_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_images` (
  `id` int(11) NOT NULL,
  `profile_pic` tinyint(4) DEFAULT NULL,
  `image_url` varchar(45) NOT NULL,
  `imagedesc` varchar(45) DEFAULT NULL,
  `after_adopt` tinyint(4) DEFAULT NULL,
  `pet_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pet_id_idx` (`pet_id`),
  CONSTRAINT `pet_id` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_images`
--

LOCK TABLES `pet_images` WRITE;
/*!40000 ALTER TABLE `pet_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `pet_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pets`
--

DROP TABLE IF EXISTS `pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pets` (
  `ID` int(11) NOT NULL,
  `pet_name` varchar(45) DEFAULT NULL,
  `story` text,
  `age` varchar(45) DEFAULT NULL,
  `private_notes` text,
  `ready_to_adopt` tinyint(4) DEFAULT NULL,
  `foster_id` int(11) DEFAULT NULL,
  `adopter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `user_id_idx` (`foster_id`),
  KEY `adopter_fk_idx` (`adopter_id`),
  CONSTRAINT `adopter_fk` FOREIGN KEY (`adopter_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `foster_fk` FOREIGN KEY (`foster_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pets`
--

LOCK TABLES `pets` WRITE;
/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_favorites`
--

DROP TABLE IF EXISTS `user_favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_favorites` (
  `user_id` int(11) NOT NULL,
  `pet_id` int(11) NOT NULL,
  KEY `user_id_idx` (`user_id`),
  KEY `pet_id_idx` (`pet_id`),
  CONSTRAINT `user_favorites_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_favorites_ibfk_2` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favorites`
--

LOCK TABLES `user_favorites` WRITE;
/*!40000 ALTER TABLE `user_favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `street_addr_1` varchar(45) DEFAULT NULL,
  `street_addr_2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `main_phone` varchar(45) DEFAULT NULL,
  `alt_phone` varchar(45) DEFAULT NULL,
  `FosterAppInfo` text,
  `AdoptAppInfo` text,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-14 14:14:19
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: furbabies
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city1` varchar(255) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `event_desc` varchar(1000) NOT NULL,
  `image_loc` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `state1` varchar(255) DEFAULT NULL,
  `street1` varchar(255) DEFAULT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `zip1` varchar(255) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK96020fdrh2ek9xdmoe9of7tdy` (`owner_id`),
  CONSTRAINT `FK96020fdrh2ek9xdmoe9of7tdy` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'','08/01/2017dddd','All the dogs party','','','','','','Dog Party','',NULL);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter_pets`
--

DROP TABLE IF EXISTS `filter_pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_pets` (
  `pet_id` bigint(20) NOT NULL,
  `filter_id` bigint(20) NOT NULL,
  KEY `FKi173ynpb2ir844ecbe8jlmiq7` (`filter_id`),
  KEY `FKmrgm1jmfcmuahctgai505woiv` (`pet_id`),
  CONSTRAINT `FKi173ynpb2ir844ecbe8jlmiq7` FOREIGN KEY (`filter_id`) REFERENCES `filters` (`id`),
  CONSTRAINT `FKmrgm1jmfcmuahctgai505woiv` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_pets`
--

LOCK TABLES `filter_pets` WRITE;
/*!40000 ALTER TABLE `filter_pets` DISABLE KEYS */;
INSERT INTO `filter_pets` VALUES (51,4),(51,1),(51,3),(51,7),(53,5),(53,1),(53,3),(53,6),(53,7),(53,10),(54,4),(54,6),(54,7),(54,9),(54,10),(55,4),(55,8),(56,5),(56,8),(56,10),(57,4),(57,7),(58,4),(58,8),(59,5),(59,8),(59,7),(60,5),(60,11),(61,5),(61,11),(61,3),(62,4),(63,4),(63,10),(64,5),(64,10),(65,5),(65,1),(65,3),(65,7),(65,10),(68,5),(68,11),(68,6),(68,9),(68,10),(69,4),(69,8),(70,5),(70,8),(71,4),(71,9),(72,5),(72,11),(72,9),(72,10),(50,5),(50,1),(66,4),(66,3),(66,9),(52,4),(52,11),(52,1),(52,3),(52,7),(67,4),(67,1),(67,3),(67,7);
/*!40000 ALTER TABLE `filter_pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filters`
--

DROP TABLE IF EXISTS `filters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `filter_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filters`
--

LOCK TABLES `filters` WRITE;
/*!40000 ALTER TABLE `filters` DISABLE KEYS */;
INSERT INTO `filters` VALUES (1,'great_with_kids'),(2,'neutered_spayed'),(3,'potty_trained'),(4,'female'),(5,'male'),(6,'crate_trained'),(7,'great_with_dogs'),(8,'cat_friendly'),(9,'medical_foster'),(10,'microchipped'),(11,'not_cat_friendly'),(12,'under_year'),(13,'not_dog_friendly');
/*!40000 ALTER TABLE `filters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_records`
--

DROP TABLE IF EXISTS `medical_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `vacination` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_records`
--

LOCK TABLES `medical_records` WRITE;
/*!40000 ALTER TABLE `medical_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `medical_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_images`
--

DROP TABLE IF EXISTS `pet_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_description` varchar(255) DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `after_adoption` bit(1) DEFAULT NULL,
  `profile_pic` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_images`
--

LOCK TABLES `pet_images` WRITE;
/*!40000 ALTER TABLE `pet_images` DISABLE KEYS */;
INSERT INTO `pet_images` VALUES (48,'','https://cdn.filestackcontent.com/rHzqSgCXTteyJr0MVnlG','\0',''),(49,'test filestack upload','https://cdn.filestackcontent.com/rP2e5KHDR1auUBAhbTdF','\0','\0'),(50,'','https://cdn.filestackcontent.com/6spL5tDTSXOhIVCJBnHJ','\0',''),(51,'fafsfdsfdsafdsfadfaf','https://cdn.filestackcontent.com/Mtb5sIdSl6KTomryxX0A','\0','\0'),(52,'Donut Closeup Picture','https://cdn.filestackcontent.com/5Fgv1QSEGfPyRjpHDwcK','\0',''),(53,'Donut Looking Off Camera','https://cdn.filestackcontent.com/xJjwqqBgQQWBHLYUEobM','\0','\0'),(54,'Donut Looking into the camera','https://cdn.filestackcontent.com/eq83VZPZSA2F6dRalthN','\0','\0'),(55,'Gigi profile picture','https://cdn.filestackcontent.com/JSUUOF0rRui4eqy8xn38','\0',''),(56,'Gigi lounging in the grass','https://cdn.filestackcontent.com/5pkDkG8SOafqatYqUxNB','\0','\0'),(57,'Gigi looking off the camera','https://cdn.filestackcontent.com/jt0ckRvoR3uI1xQLPcgW','\0','\0'),(58,'Gigi looking towards the camera','https://cdn.filestackcontent.com/esrAiE2ZQQqxaD5ZpYbT','\0','\0'),(59,'Hank Profile ','https://cdn.filestackcontent.com/ScNLA1cRS3KBzcS8gTdZ','\0',''),(60,'Hank looking off camera','https://cdn.filestackcontent.com/TmUQzm34TsynZsAgmihQ','\0','\0'),(61,'Hank Looking handsome','https://cdn.filestackcontent.com/twEFaV9oSLmN0hblnXuJ','\0','\0'),(62,'Hank looking cool','https://cdn.filestackcontent.com/NeWxmbHBSkKe49Apae5Q','\0','\0'),(63,'Kate Profile','https://cdn.filestackcontent.com/LWvKpTeSQ5ugbEyGRX4U','\0',''),(64,'Kate looking happy','https://cdn.filestackcontent.com/9yICCQZGTBKHjQhrsQSv','\0','\0'),(65,'Bella Profile','https://cdn.filestackcontent.com/ah1Pa3doTcWJxre2UeTl','\0',''),(66,'Bella in her safe place','https://cdn.filestackcontent.com/CNkPkSOJT9Gig9BzC6eW','\0','\0'),(67,'Garfield Profile','https://cdn.filestackcontent.com/cH3hQTVRd6kRqVzV0Yhk','\0',''),(68,'Garfield looking up','https://cdn.filestackcontent.com/XAlqfRQ8Q6Kii5lyLZkU','\0','\0'),(69,'Garfield looking off to the side','https://cdn.filestackcontent.com/AargkPD2RNCaUlAOSuCt','\0','\0'),(70,'Hayley Profile Picture','https://cdn.filestackcontent.com/i455x4y0QNeK5EHVLGbf','\0',''),(71,'Hayley relaxing on the floor','https://cdn.filestackcontent.com/kZNNyI1VQEu6fq8JkLRc','\0','\0'),(72,'Hayley looking off to the side','https://cdn.filestackcontent.com/EF4r6oKnTLqqBcDoxRTY','\0','\0'),(73,'Hayley looking at the camera','https://cdn.filestackcontent.com/34py1aW6R1ebWRIfEQdp','\0','\0'),(74,'Kiara Profile','https://cdn.filestackcontent.com/nmBL3R4zR3Gg8UKnj2MC','\0',''),(75,'Kiara sitting on porch','https://cdn.filestackcontent.com/EuAcCX3hRXyOqxIjQNYG','\0','\0'),(76,'Kiara standing on porch','https://cdn.filestackcontent.com/hAmgoYcrT86iwLw0NMYy','\0','\0'),(77,'Kiara showing off her lion cut','https://cdn.filestackcontent.com/0iJ8xi9NTsJBbwt5G2O8','\0','\0'),(78,'Max Very close up','https://cdn.filestackcontent.com/EIsYltOFTWCR98TSDdbj','\0',''),(79,'Max being held up for the camera','https://cdn.filestackcontent.com/FAFHhqIkQ5hxqm2kR0vH','\0','\0'),(80,'Max playing with leash','https://cdn.filestackcontent.com/7Zv9u0lOSeqwcTSWqv1H','\0','\0'),(81,'Kodi Profile','https://cdn.filestackcontent.com/nMTKP6tS1qKGbf3caugh','\0',''),(82,'Kodi snuggling','https://cdn.filestackcontent.com/QuEsdmkPSgWhP0RQ81Qu','\0','\0'),(83,'Kodi sleeping with toy','https://cdn.filestackcontent.com/kLWjwdy6ROm8C6uLyK7N','\0','\0'),(84,'Kodi snuggling with eyes closed','https://cdn.filestackcontent.com/d3QqyA7NRVOMmn2w6nAD','\0','\0'),(85,'Kodi lounging','https://cdn.filestackcontent.com/fFByxtPJR6Xv0INqN7JF','\0','\0'),(86,'Lionel Profile Pic','https://cdn.filestackcontent.com/xwaVXzAVSNykq9J1Dmgv','\0',''),(87,'Lionel looking up at the camera','https://cdn.filestackcontent.com/685NDZLsTHOHTLpxrYSI','\0','\0'),(88,'Lionel standing on the porch','https://cdn.filestackcontent.com/hqXnB8CKQGarAqW8x8P1','\0','\0'),(89,'Lionel closeup','https://cdn.filestackcontent.com/dZB02aJRQkq6IluGT8Wp','\0','\0'),(90,'Mistie Profile pic','https://cdn.filestackcontent.com/7DotC1sVReucyGpNMeQG','\0',''),(91,'Mistie licking her paw','https://cdn.filestackcontent.com/6Phm4qenS2mH68oH4zsC','\0','\0'),(92,'Misti looking to the side','https://cdn.filestackcontent.com/BmAouirAQ5KeLmO2Q6IA','\0','\0'),(93,'Patches Profile Pic','https://cdn.filestackcontent.com/cRXMCyKaTYy89kb0tVqO','\0',''),(94,'Patches looking above camera','https://cdn.filestackcontent.com/DTKsKJRyTmmQxnBT1rbl','\0','\0'),(95,'Patches leaning against furniture','https://cdn.filestackcontent.com/q4hh5XKCTRO3FHEnLVlE','\0','\0'),(96,'Patches upside down','https://cdn.filestackcontent.com/JqR9W1OR6mOlADJURu4x','\0','\0'),(97,'Toby Profile Pic','https://cdn.filestackcontent.com/kRETw7diQwepvtFYE3kY','\0',''),(98,'Toby standing sideways','https://cdn.filestackcontent.com/VhiJgpLuROe3jcIyh183','\0','\0'),(99,'Toby standing in front of litter mate.','https://cdn.filestackcontent.com/WodGgIkpTmyOtoNWBYFD','\0','\0'),(100,'Toby lounging outside','https://cdn.filestackcontent.com/ehPcHx5VRk2zXchv7tTb','\0','\0'),(101,'Einstein Profile Pic','https://cdn.filestackcontent.com/0MkTAByuTcSCkoUQY7Hm','\0',''),(102,'Einstein looking towards camera','https://cdn.filestackcontent.com/9ZluBG5zTySUa7tZzLre','\0','\0'),(103,'Einstein Smiling for the camera','https://cdn.filestackcontent.com/cJgKP6SSRCK8b9mjKB9y','\0','\0'),(104,'Hermione profile pic','https://cdn.filestackcontent.com/qvqhdxtHTYKkd1NweGsR','\0',''),(105,'Hermione looking to the side','https://cdn.filestackcontent.com/23rjVl10RvyF2E6308k6','\0','\0'),(106,'Hermione close-up looking to the side','https://cdn.filestackcontent.com/j2asJuTtQuGfV7EEZaWp','\0','\0'),(107,'Suri Profile ','https://cdn.filestackcontent.com/w3TiE3FvSfiV8TrDI9XT','\0',''),(108,'Suri looking at camera','https://cdn.filestackcontent.com/OYW7JZiiSzCC13TfSwmY','\0','\0'),(109,'Harrington Profile','https://cdn.filestackcontent.com/iX0YfVjS5eEzCYDn5c0A','\0',''),(110,'Harrington looking left','https://cdn.filestackcontent.com/AGtB8CJQ8KjU1ktzjS6s','\0','\0'),(111,'Harrington looking right','https://cdn.filestackcontent.com/DyqldxoTnaMhDqTo3w2m','\0','\0'),(112,'Harrington checking his surroundings','https://cdn.filestackcontent.com/jEuVXbbqS6GpuL260Tos','\0','\0'),(113,'Eyebrows profile','https://cdn.filestackcontent.com/BesUWXffQE5byAiT5RKg','\0',''),(114,'Eyebrows sitting on chair','https://cdn.filestackcontent.com/sDco8dgmS0yBMZPD3zN3','\0','\0'),(115,'Eyebrows and friend on sofa','https://cdn.filestackcontent.com/6sTM5AxhTXGfKMtfHN31','\0','\0'),(116,'Eyebrows in crate','https://cdn.filestackcontent.com/dlQaos30SFfTcN9CPRDx','\0','\0'),(117,'Potato profile','https://cdn.filestackcontent.com/kJc0yWymT4KUax4Ak6NU','\0',''),(118,'Potato with friend','https://cdn.filestackcontent.com/CVPr7DB3Rdahad4Ih1dl','\0','\0'),(119,'Potato in shoe','https://cdn.filestackcontent.com/4B1W2VZWRlOtbFFbtuBs','\0','\0'),(120,'Potato with litter mates','https://cdn.filestackcontent.com/MySf2qliSOexcozzrpCM','\0','\0'),(121,'Potato snuggled in blanket with friend','https://cdn.filestackcontent.com/I2MfnBZSdWEhSA7f8zcQ','\0','\0'),(122,'Hera Profile pic','https://cdn.filestackcontent.com/gESCXyWaSpCTAKe55Ca9','\0',''),(123,'Hera sitting on the floor','https://cdn.filestackcontent.com/tcT8ep4ATBqTXrsfPiqF','\0','\0'),(124,'Hera playing on the floor','https://cdn.filestackcontent.com/1V7VLx5ASvug4LhtEsDw','\0','\0'),(125,'Jax with tongue out','https://cdn.filestackcontent.com/EGpBZhhsT8zG43lC2lOv','\0',''),(126,'Jax looking dignified','https://cdn.filestackcontent.com/r2LLueMxR6KtUMcek2bF','\0','\0'),(127,'Jax licking chops','https://cdn.filestackcontent.com/P3NH7vRcSj66cpXkpVYj','\0','\0'),(128,'Jax sitting full body picture','https://cdn.filestackcontent.com/hpVbkHmiQEKKdZcPhSSi','\0','\0');
/*!40000 ALTER TABLE `pet_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_medical`
--

DROP TABLE IF EXISTS `pet_medical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_medical` (
  `pet_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1op4ab0b5u01dwasd4n3g6gpi` (`pet_id`),
  CONSTRAINT `FK1op4ab0b5u01dwasd4n3g6gpi` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`),
  CONSTRAINT `FK72hfnx9udjil9pi4owjam99a2` FOREIGN KEY (`id`) REFERENCES `medical_records` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_medical`
--

LOCK TABLES `pet_medical` WRITE;
/*!40000 ALTER TABLE `pet_medical` DISABLE KEYS */;
/*!40000 ALTER TABLE `pet_medical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_to_image`
--

DROP TABLE IF EXISTS `pet_to_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_to_image` (
  `pet_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7vry9rpot835gxu0d0g53emnd` (`pet_id`),
  CONSTRAINT `FK7vry9rpot835gxu0d0g53emnd` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`),
  CONSTRAINT `FKmd6syaurgyf9urkijmoed8cvy` FOREIGN KEY (`id`) REFERENCES `pet_images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_to_image`
--

LOCK TABLES `pet_to_image` WRITE;
/*!40000 ALTER TABLE `pet_to_image` DISABLE KEYS */;
INSERT INTO `pet_to_image` VALUES (50,48),(51,52),(51,53),(51,54),(52,55),(52,56),(52,57),(52,58),(53,59),(53,60),(53,61),(53,62),(54,63),(54,64),(55,65),(55,66),(56,67),(56,68),(56,69),(57,70),(57,71),(57,72),(57,73),(58,74),(58,75),(58,76),(58,77),(59,78),(59,79),(59,80),(60,81),(60,82),(60,83),(60,84),(60,85),(61,86),(61,87),(61,88),(61,89),(62,90),(62,91),(62,92),(63,93),(63,94),(63,95),(63,96),(64,97),(64,98),(64,99),(64,100),(65,101),(65,102),(65,103),(66,104),(66,105),(66,106),(67,107),(67,108),(68,109),(68,110),(68,111),(68,112),(69,113),(69,114),(69,115),(69,116),(70,117),(70,118),(70,119),(70,120),(70,121),(71,122),(71,123),(71,124),(72,125),(72,126),(72,127),(72,128);
/*!40000 ALTER TABLE `pet_to_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pets`
--

DROP TABLE IF EXISTS `pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `private_notes` varchar(1000) DEFAULT NULL,
  `ready_to_adopt` bit(1) NOT NULL,
  `species` varchar(255) NOT NULL,
  `story` varchar(5000) DEFAULT NULL,
  `user_adopter_id` bigint(20) DEFAULT NULL,
  `user_foster_id` bigint(20) DEFAULT NULL,
  `breed` varchar(255) DEFAULT NULL,
  `adoption_date` varchar(255) DEFAULT NULL,
  `arrival_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsm5cjgby0jx7sxhrjgomb4km7` (`user_adopter_id`),
  KEY `FKoo1bstrsm8ru8ajraf64gshiy` (`user_foster_id`),
  CONSTRAINT `FKoo1bstrsm8ru8ajraf64gshiy` FOREIGN KEY (`user_foster_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsm5cjgby0jx7sxhrjgomb4km7` FOREIGN KEY (`user_adopter_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pets`
--

LOCK TABLES `pets` WRITE;
/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
INSERT INTO `pets` VALUES (50,4,'Henry','','','Donkey','Henry was found wandering through a subdivision like a donkey without an owner.',NULL,1,'','','07/07/2017'),(51,2,'Donut','','','Dog','Always sweet and perfectly soft, Donut is always a great choice! This beautiful girl is as lab like as they come! She is always happy-go-lucky and as loyal as can be. She will stick to you like a glazed eclair once she knows how much you love her. Donut loves to play with her toys (preferably Donut-shaped ones) and will spend a hot summer day running through the sprinklers or dipping her toes in the pool. As sweet as can be, Donut gets along great with kids and other dogs as well. What better way to start everyday than with Donut by your side?! Fill out an application for Donut today!',NULL,1,'Lab Mix','','07/01/2017'),(52,5,'Gigi','','','Dog','As soft as a cloud and as loving as can be, Gigi stole our heart immediately. We received a call out from Animal Care Services for a rescue to take sweet Gigi as she was owner-surrendered simply because her owner had a “change of lifestyle” on a Friday afternoon. ACS was already at capacity which means owner-surrendered pups are the first to go. So we sent out a plea to our limited large dog fosters and fortunately found a place for Gigi to go. She was initially a little confused having just been whisked away from the only family she knew. Her whole life had drastically changed at the drop of a..well, dog. But after a few hours of cuddles and treats, Gigi was appreciative of all of the attention. The upside about having been a housedog is that Gigi is a perfectly behaved lady. She is housebroken, mild mannered, loves to lounge on her dog bed, will follow you from room to room, and is devoted to her new foster parents. She lives with a house full of dogs and does just fine. She doesn’t care for cats all that much so would be best in a house without felines. Gigi does great with kids and loves their snuggles and tiny hands rubbing her ears. She is leash trained and would be a great dog to take on walks. Big dogs need their exercise. She is middle aged which means you can say goodbye to the puppy antics and enjoy all the perks of just having a trained companion. She also loves being brushed. Don’t pass her up- she is super easy and a lovely dog that deserves a second chance. Fill out an application for Gigi today!',NULL,6,'Shepard Mix','','06/30/2017'),(53,3,'Hank','','','Dog','Hank was seized due to cruelty and came to us severely malnourished. Hank is an absolute doll and staff favorite. He is GREAT with other dogs, people, and even cats. He has blossomed in his foster home and after being returned is still on the hunt for his fur-ever family.',NULL,2,'Pitbull','','07/03/2017'),(54,3,'Kate','','','Dog','Kate was saved from death row with her lone puppy, Quinn. They were both suffering from mange. Kate is a spunky girl that has already started to open up after being rescued! She is searching for a foster that can get her ready for adoption.',NULL,2,'Pitbull','','07/02/2017'),(55,10,'Bella','','','Cat','Bella is a female 10-year old orange & grey Calico Shorthair.  Bella qualifies for San An Seniors for Seniors program ... her adoption fee is waived for an approved adopter at least 55 years of age!  Bella is spayed, current on vaccinations and microchipped. Her adoption comes with one free month of pet health insurance. She needs adoption, foster care or transfer to a rescue organization.',NULL,3,'Calico Shorthair','','07/10/2017'),(56,0,'Garfield','','','Cat','Garfield is a male orange & white shorthair born mid-April 2017. He is super-playful and loves playing with other kittens. He is neutered, current on vaccinations and microchipped. His adoption comes with one free month of pet health insurance. He needs adoption, foster care or transfer to a rescue organization',NULL,3,'Domestic Short Hair','','07/07/2017'),(57,1,'Hayley','','','Dog','At least two to three times a week, we receive “code red” dogs from Animal Control Services. Code red dogs are dogs that have until 5 pm to be adopted or rescued or else they are euthanized because the shelter is full. Unfortunately, owner surrendered animals are the first to go once the shelter is full. Hayley and her two friends were owner surrendered and after 5 days of being in the shelter, time was up for Hayley as there was no more room. We received the “code red” email late in the day and rushed to pick Hayley and her pals up. Thankfully we got there with an hour to spare and brought Taylor and her friends to SNIPSA. Covered in fleas and ticks, we weren’t surprised that they were owner surrendered as they clearly hadn’t been taken care of. After a bath, some food, and a lot of love from our staff, Hayley was nice and clean and as loving as can be. Hayley is slowly coming out of her shell and loves a good belly rub. She gets along great with other dogs and is enjoying walks through the neighborhood and relaxing by the pool. Want to be part of Hayley’s rescue story? Fill out an application today!',NULL,4,'Border Collie Mix','','07/08/2017'),(58,5,'Kiara','','','Dog','Did you do a double take when you first set eyes on Kiara? No, she’s not a lion…she’s a poodle! Sweet Kiara got her adorable lion cut as she came in a completely matted mess! We had to shave her down to her skin to get rid of the mats but as you can see from her front half, she has that gorgeous soft and curly poodle fur! Kiara is a sweet pea and won’t leave your side! She loves to snuggle up with you and makes a great lapdog. Kiara gets along well with other dogs and doesn’t mind cats at all. Kiara is working on her house training and crate training and is picking it up quickly. She’ll need a little refresher when she gets into a new home-just until she learns your schedule and the lay of the land. Kiara’s apricot coat will soon come in and she’ll be as beautiful as ever! If a curly-coated pup is what you’ve been looking for, fill out an application for Kiara today!',NULL,2,'Miniature Poodle Mix','','07/17/2017'),(59,0,'Max','','','Cat','Max is an 8 week old loving little boy. He loves to be next to you and cuddle. He\'s very outspoken and loves attention. Good with other animals. Very playful!    I was scheduled to be euthanized, but Fur Baby Finder saved me and now they are helping me find the forever, loving home that I have never known.  I am in a foster home. By adopting me, you not only give me a new leash on life; you also save the life of the additional cat who can be rescued from euthanasia and placed in foster care.',NULL,1,'Domestic Short Hair','','07/18/2017'),(60,2,'Kodi',' FIV positive','','Cat','Kodi is approximately 2 years old and is a sweet, gentle and very affectionate cat. He loves to be around people and will greet you at the door on your return home. Kodi loves when you pet him and give him attention. He is a cuddlier and just wants to be loved. While at the same time, Kodi has no problem keeping himself busy taking cat naps. He loves a good belly or neck scratch. Kodi can be a talker. It seems Kodi likes to talk when he is hungry or wants some affection from you. He tolerates being bathed, brushed, nails clipped, etc. very well.    Kodi was found outside of an apartment complex dirty, wet (from rain), banged up, and hungry. It was clear that he was neglected/abandoned and looking for a home. He was taken in, cleaned, fed and brought to the vet to be looked at. Kodi was cared for and is now grateful for being fostered until he finds a permanent home. Kodi tested FIV positive which means he cannot be around other non-FIV positive cats but would be perfect companion for someone who wants one loving cat.',NULL,4,'Domestic Short Hair','','07/02/2017'),(61,2,'Lionel','Barks at visitors.  Good little guard dog','','Dog','Lionel is the happiest little guy you ever did see! He loves to run around and chase his toys and of course, play with his foster pals!When he isn’t running around, this little Pekingese mix is wagging his tail while snuggling up in your arms! Lionel is super smart and always wants to impress you so he is already making way with his house training. He has the sweetest disposition and would make the best companion to take everywhere with you!Is this spunky pup perfect for you? Fill out an application for Lionel today!',NULL,4,'Pekingese Mix','','07/15/2017'),(62,0,'Mistie','','','Cat',' Mistie is 8 weeks old. She\'s a very curious and playful little girl and loves to explore! She\'s quite mature for her young age but still enjoys to cuddle. She\'s cautious with other animals but will warm up with time.   I was scheduled to be euthanized, but Fur Baby Finder saved me and now they are helping me find the forever, loving home that I have never known.  By adopting me, you not only give me a new leash on life; you also save the life of the additional cat who can be rescued from euthanasia and placed in foster care.',NULL,5,'Domestic Short Hair','','07/10/2017'),(63,5,'Patches','','','Cat','Patches is a female 6-year old tortoiseshell shorthair. She is spayed, current on vaccinations and microchipped. Her adoption comes with one free month of pet health insurance',NULL,3,'Tortoiseshell','','07/08/2017'),(64,0,'Toby','','','Dog','The adorable Toby is the happiest little guy you’ll ever meet! He loves to run around and chase his toys and play a long standing game of tug o’ war with you! When he isn’t running around, this little bright-eyed boy is wagging his tail while snuggling up in your arms! Toby is super smart and always wants to impress you so he is already making way with his house training. He has the sweetest disposition and would make the best companion to take everywhere with you! If a fun-loving boy is what you’re looking for, fill out an application for Toby today!',NULL,4,'Jack Russell Terrier Mix','','07/15/2017'),(65,0,'Einstein','','','Dog','A border collie mix, Einstein is one smart pup! He walks great on a leash and behaves very well! He knows the difference in playing with bigger dogs versus playing with puppies and is loving and patient with kids. Einstein doesn’t just have brains-he has beauty too! With his gorgeous border collie coat and short stumpy legs, you can’t help but look right at Einstein when he trots into the room with his tail wagging. He always has a smile on his face as long as he’s by your side. He loves to attend farmer’s markets, meet new friends at the park, or just chill at your feet underneath your desk while you work-it doesn’t matter to Einstein, as long as he’s with you, he is one happy pup! He also doesn’t mind waiting for you to get home and will read on the couch or play with his toys while he waits to greet you at the door. Smart enough to adopt this brainy boy? Fill out an application for Einstein today!',NULL,4,'Border Collie Mix','','07/01/2017'),(66,0,'Hermione','','','Dog','Hermione was brought into ACS as a stray. We rescued her before she could be euthanized as her time ran out. She is deaf but don’t let that deter you! She is a very sweet, happy-go-lucky girl that LOVES to play.',NULL,4,'Pitbull','','07/01/2017'),(67,2,'Suri','','','Dog','Suri was rescued as a puppy with her litter mates and mom (Lucy). She was adopted then returned almost a year later because she is a bit rambunctious which made her elderly canine pal nervous! She is very shy, sweet, and great with other playful dogs. She would make any family very happy.',NULL,6,'Pitbull','','02/17/2017'),(68,3,'Harrington','Almost Potty trained','','Dog','Harrington was rescued from a shelter in St. Parish, Louisiana. The small rural establishment is underfunded and over populated. Although they try their very best, the sad reality is that they were overrun with unwanted pit bulls. They were going to euthanize 39 of these poor souls so we made a road trip to save Harrington and 5 other pups. He needs to be the only animal in the home at this time.',NULL,1,'Pitbull','','05/05/2017'),(69,0,'Ms. Eyebrows AKA \"Eyebrows\"','','','Cat','Ms Eyebrows is very affectionate, she will follow you every where you go and try to play with you or just cuddle up next to you. She gets along great with everyone of her foster siblings but seems to enjoy the company of humans a little more. She usually sticks to herself if she\'s not following me around and is very gentle and sweet.',NULL,1,'Tortoiseshell','','07/02/2017'),(70,0,'Potato','','','Cat',' Potato sounds like a grumpy old man when he meows but don\'t let that fool you, he is very sweet and affectionate! He isn\'t as high-energy as his brother but he is just as cuddly, if not more. He gets along well with all his other foster siblings and is very even tempered. Him and his brother somehow manage to stick together most of the time, seeming to prefer each other\'s company the most out of all the others kitties. Just like his brother Peaches, Potato has a strange and adorable obsession with sleeping in my shoes.',NULL,2,'Domestic Short Hair','','07/08/2017'),(71,0,'Hera','Now tests negative for infection','','Cat',' Hera was born mid-February and, along with her sister, Helena, came in at three weeks old looking pretty shabby with an upper respiratory infection.  Fur Baby Finder rescued them off the euthanasia list when they found foster who took them both.   Hera is four months old and quite a beauty, with her bright, expressive eyes and unique tabby markings that resemble chocolate and caramel swirls on each side. But it\'s her personality that really makes her stand out from the rest. Hera is THE most loving, affectionate cat you will ever meet! Even if a bit cautious just at first, it\'s not long before she wants to nuzzle up and let you know she\'s up for some cuddle time. If it\'s a lap cat you want, this is your girl! She\'s still kitten enough to get her silly on and loves to play with toys and other cats, but has an unusually-nurturing and gentle nature. She has not been exposed to a dog in the home but maybe if it pretends to be a cat, can try to give it a motherly bath.    An ideal home for Hera would be with folks who truly want a cat around to interact and snuggle with on a daily basis, and in a somewhat-peaceful environment. Hera is very quiet and, as with many cats, can become skittish around loud and sudden noises. Any other cats or accepting pets, as well would be a huge plus.',NULL,2,'Domestic Short Hair','','06/03/2017'),(72,4,'Jax','FIV+ positive','','Cat','Jax is a male 4-year old grey & white shorthair. He is a curious guy and loves to explore. He isn\'t the most affectionate cat, but he would still love to relax with you and enjoy a snack. Jax LOVES snacks.  He is FIV+, so would do best as an only cat in the home. Any doggy friends would need to come by for a meet and greet to see how he does with them.',NULL,4,'Domestic Short Hair','','06/15/2017');
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_favorites`
--

DROP TABLE IF EXISTS `user_favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_favorites` (
  `user` bigint(20) NOT NULL,
  `pet_id` bigint(20) NOT NULL,
  KEY `FKh5ihgiesw4482euo2j22rqlsc` (`pet_id`),
  KEY `FK8hfnnuwed05ckwyijodw1lpt7` (`user`),
  CONSTRAINT `FK8hfnnuwed05ckwyijodw1lpt7` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  CONSTRAINT `FKh5ihgiesw4482euo2j22rqlsc` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favorites`
--

LOCK TABLES `user_favorites` WRITE;
/*!40000 ALTER TABLE `user_favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (5,'basic',5),(7,'adopter',1),(10,'admin',1),(13,'foster',5),(14,'admin',5),(15,'foster',1),(16,'basic',2),(17,'foster',2),(18,'basic',3),(19,'foster',3),(20,'basic',4),(21,'foster',4),(22,'basic',6),(23,'basic',7),(24,'admin',6),(25,'foster',6),(27,'adopter',NULL),(28,'foster',NULL),(29,'admin',NULL),(30,'basic',NULL),(31,'basic',8);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adopt_app_info` varchar(255) DEFAULT NULL,
  `foster_app_info` varchar(255) DEFAULT NULL,
  `alt_phone` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `main_phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street_add1` varchar(255) DEFAULT NULL,
  `street_add2` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `zip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'','','5553332222','San Antonio','dave@mail.com','Bill','Smith','2104947321','$2a$10$yfNI.cGx.c5FF1b67bl.9u6b/6aoLneBtrfEPXIP3z47a4s/IQYRO','Texas','13249 Washita Way','','frenchfryes','47401'),(2,'','','','San Antonio','tom@mail.com','Bob','Skylar','2104437321','$2a$10$yfNI.cGx.c5FF1b67bl.9u6b/6aoLneBtrfEPXIP3z47a4s/IQYRO','Texas','13242 Apache Way','','wannapup','47403'),(3,'','','','San Antonio','sarah@mail.com','Sarah','Heisenberg','2106637321','$2a$10$yfNI.cGx.c5FF1b67bl.9u6b/6aoLneBtrfEPXIP3z47a4s/IQYRO','Texas','132 Blue Way','','sarahisdabomb','47405'),(4,'','','','San Antonio','melanie@mail.com','Melanie','Tucon','2914947321','$2a$10$yfNI.cGx.c5FF1b67bl.9u6b/6aoLneBtrfEPXIP3z47a4s/IQYRO','Texas','43249 Yahoo Way','','mellibelly','47401'),(5,NULL,NULL,' ','San Antonio','melody@gmail.com','Melody','Templeton','210-413-8366','$2a$10$yfNI.cGx.c5FF1b67bl.9u6b/6aoLneBtrfEPXIP3z47a4s/IQYRO','TX','3333 Pleasant Dr','','melody','78261'),(6,NULL,NULL,'','San Antonio','micah@mail.com','Micah','Disney','210-555-3421','$2a$10$yfNI.cGx.c5FF1b67bl.9u6b/6aoLneBtrfEPXIP3z47a4s/IQYRO','TX','1234 Bittersweet Dr','','micah','78258'),(7,NULL,NULL,NULL,NULL,'derek@mail.com',NULL,NULL,NULL,'$2a$10$yfNI.cGx.c5FF1b67bl.9u6b/6aoLneBtrfEPXIP3z47a4s/IQYRO',NULL,NULL,NULL,'derek',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-14 14:14:19
