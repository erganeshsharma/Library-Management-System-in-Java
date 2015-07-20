-- MySQL dump 10.13  Distrib 5.5.17, for Win32 (x86)
--
-- Host: localhost    Database: Library
-- ------------------------------------------------------
-- Server version	5.5.17

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `BookID` int(11) NOT NULL AUTO_INCREMENT,
  `Subject` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `Title` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `Author` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Publisher` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `Copyright` int(11) DEFAULT NULL,
  `Edition` int(11) DEFAULT NULL,
  `Pages` int(11) DEFAULT NULL,
  `ISBN` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `NumberOfBooks` int(11) DEFAULT NULL,
  `NumberOfAvailbleBooks` int(11) DEFAULT NULL,
  `NumberOfBorrowedBooks` int(11) DEFAULT '0',
  `Library` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `Availble` bit(1) DEFAULT NULL,
  `ShelfNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`BookID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Java','Java : The Complete Reference','Herbert Schildt','Tata McGraw Hill',2007,7,1023,'007063677X',10,9,1,'Main Library','',3),(2,'Networks','Computer Networks and Internets','Douglas E. Comer','Pearson Education',2004,4,719,'8129703300',5,5,0,'NIELIT Library','',2),(3,'Compiler','Introduction to Automata Theory, Languages, Computation','John E. Hopcroft, Rajeev Motwani, Jeffrey D. Ullman','Pearson Education',2001,2,521,'0201441241',7,7,0,'Main Library','',6),(4,'Java','JAVA Swing','Robert Eckstein,Marc Loy & Dave Wood','OREILLY',1999,1,1227,'817366109X',5,5,0,'NIELIT Library','',4),(5,'Networking','Data Communications and Networking','Behrouz A. Forouzan','Tata McGraw Hill',2006,4,1134,'0074634149',15,14,1,'Electronics Library','',2),(6,'Java','Java Concurrency in practice','Brian Goetz, David Holmes, Doug Lea, Tim Peierls,Joshua Bloch','Pearson Education',2006,1,403,'9788131713396',12,12,0,'Main Library','',1),(7,'Security','Network Security Essentials : Applications and Standard','Willian Stallings','Pearson',2011,4,417,'9788131761755',11,11,0,'Main','',2),(8,'Java','Swing : A Beginners Guide','Herbert Schildt','Tata McGraw Hill',2007,1,590,'0070636486',9,9,0,'NIELIT Library','',1),(9,'Java','Java Network Programming','Elliotte Rusty Harold','OREILLY',2005,3,735,'9788173663536',10,10,0,'Electronics','',4),(10,'Microprocessor','Microprocessors and Microcontrollers','B. Ram','Dhanpat Rai Publication',2005,6,530,'9788189928605',10,10,0,'Electronics Library','',3),(11,'Automata','Automata Theory & Formal Languages','Adesh K. Pandey','Katson Books',2004,5,358,'8189757148',10,10,0,'NIELIT Library','',2),(12,'Compiler','Compiler Design','Adesh K. Pandey','Katson Books',2007,2,586,'8189757172',10,10,0,'NIELIT Library','',1),(13,'Internet','Web Technologies TCP/IP Architecture','Achyut S Godbole & Atul Kahate','Tata McGraw Hill',2002,2,607,'0070669058',5,5,0,'Main','',2);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow` (
  `BookId` int(11) NOT NULL,
  `MemberID` int(11) NOT NULL,
  `DayOfBorrowed` date NOT NULL,
  `DayOfReturn` date DEFAULT NULL,
  PRIMARY KEY (`BookId`,`MemberID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (1,1,'2015-03-04','2015-03-19'),(5,3,'2015-02-22','2015-03-07');
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `Username` varchar(30) CHARACTER SET utf8 NOT NULL,
  `Password` varchar(30) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('Librarian','librarian');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `members` (
  `MemberID` int(11) NOT NULL AUTO_INCREMENT,
  `RegNo` int(11) DEFAULT NULL,
  `Password` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `Name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `EMail` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `Major` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `NumberOfBooks` int(11) DEFAULT '0',
  `ValidUpto` date DEFAULT NULL,
  PRIMARY KEY (`MemberID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,730525,'ganesh123','Ganesh Sharma','ganeshsharmait@gmail.com','IT',1,'2016-07-31'),(2,811805,'patar','Pankaj Patar','pankajpator@ymail.com','IT',0,'2016-01-01'),(3,730526,'prachu123','Prachujya Pathak','prachujya@gmail.com','CS',1,'2016-01-01'),(4,730527,'joti555','Jyotirindra Das','jyoti_das@rocketmail.com','CS',0,'2016-01-01'),(5,814652,'AMIT123','Amit Sharma','amit_sharma@rocketmail.com','IT',0,'2016-01-01');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-20  9:23:24
