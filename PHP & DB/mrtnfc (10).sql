-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 20, 2018 at 04:03 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mrtnfc`
--

-- --------------------------------------------------------

--
-- Table structure for table `casepaper`
--

DROP TABLE IF EXISTS `casepaper`;
CREATE TABLE IF NOT EXISTS `casepaper` (
  `Description` text NOT NULL,
  `Type` text NOT NULL,
  `DoctorID` int(11) NOT NULL,
  `PatientID` int(11) NOT NULL,
  `HospitalID` int(11) NOT NULL,
  `Pic` text NOT NULL,
  `Date` text NOT NULL,
  KEY `DrID` (`DoctorID`),
  KEY `hrID` (`HospitalID`),
  KEY `PID` (`PatientID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `casepaper`
--

INSERT INTO `casepaper` (`Description`, `Type`, `DoctorID`, `PatientID`, `HospitalID`, `Pic`, `Date`) VALUES
('Working with blood samples from patients with infections, BRI researchers are using the Infectious Diseases & Vaccines Biorepository to understand the molecular signature of the early immune response to different infectious diseases in order to develop specific and rapid diagnostic tests that will allow earlier identification and treatment of these diseases as well as improvements to vaccines.', 'Infectious Diseases', 98900989, 77438199, 66778899, '1234', '12/12/2017'),
('Create a new project', 'Sonography', 12345678, 11223344, 66778899, '11', '21/5/2016'),
('Go jim regularly.', 'Sonography', 12345678, 11223344, 66778899, '4750', '9/6/2016'),
('temp', 'temp', 98900989, 11223344, 66778899, 'Vincent.jpg', 'Mar 15, 2018 4:28:05 PM'),
('I am trying to get the best way.  the only thing I can do it for the first time in the morning and I will be a good time to time and money. ', 'birth certificate ', 98900989, 11223344, 66778899, 'Vincent.jpg', 'Mar 15, 2018 3:08:58 AM'),
('temppp', 'tempppp', 98900989, 11223344, 66778899, 'Vincent.jpg', 'Mar 15, 2018 10:31:46 PM'),
('sam', 'sam', 98900989, 11223344, 66778899, 'Vincent.jpg', 'Mar 16, 2018 12:39:22 AM'),
('I am trying to get the best way to get the best way ', 'dentist', 98900989, 989009934, 66778899, 'Vincent.jpg', 'Mar 17, 2018 2:02:49 AM');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `DoctorID` int(11) NOT NULL AUTO_INCREMENT,
  `Qualification` text NOT NULL,
  `Speciality` text NOT NULL,
  `CertificateNo` int(11) NOT NULL,
  `HospitalID` int(11) NOT NULL,
  PRIMARY KEY (`DoctorID`),
  KEY `HospitalFK` (`HospitalID`)
) ENGINE=MyISAM AUTO_INCREMENT=989009932 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`DoctorID`, `Qualification`, `Speciality`, `CertificateNo`, `HospitalID`) VALUES
(12345678, 'MBA', 'Dentist', 7885456, 66778899),
(98900989, 'MBA', 'Dentist', 12535533, 66778899),
(48621575, 'MBA', 'Bone', 468578574, 458458458),
(989009931, 'Immunologist', 'MBA ', 46364646, 458458458);

-- --------------------------------------------------------

--
-- Table structure for table `familymember`
--

DROP TABLE IF EXISTS `familymember`;
CREATE TABLE IF NOT EXISTS `familymember` (
  `PrimaryID` int(11) NOT NULL,
  `Type` text NOT NULL,
  `MemberID` int(11) NOT NULL,
  `Accepted` text NOT NULL,
  `Primary_EmergencyAction` text NOT NULL,
  `Secondary_EmergencyAction` text NOT NULL,
  `Location` text NOT NULL,
  `Date` text NOT NULL,
  `OtherSenderID` int(11) NOT NULL,
  KEY `otherId` (`OtherSenderID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `familymember`
--

INSERT INTO `familymember` (`PrimaryID`, `Type`, `MemberID`, `Accepted`, `Primary_EmergencyAction`, `Secondary_EmergencyAction`, `Location`, `Date`, `OtherSenderID`) VALUES
(11223344, 'Brother', 77438199, 'Accepted', 'Normal', 'Normal', 'tteemmpp', '2018-01-11 12:27:30', 11223344),
(77438199, '', 11223344, 'Pending', 'Normal', 'Normal', '0', '0', 0),
(77438199, 'temp', 11223344, 'Pending', 'Normal', 'Normal', '0', '0', 0),
(77438199, 'ttt', 11223344, 'Pending', 'Normal', 'Normal', '0', '0', 0),
(77438199, 'nihal', 11223344, 'Pending', 'Normal', 'Normal', '0', '0', 0),
(989009934, 'brother ', 77438199, 'Accepted', 'Normal', 'Normal', '0', '0', 0),
(989009934, 'brother ', 77438199, 'Accepted', 'Normal', 'Normal', '0', '0', 0),
(989009934, 'brother ', 11223344, 'Accepted', 'Normal', 'Normal', 'jaipur', '0', 77438199);

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
CREATE TABLE IF NOT EXISTS `hospital` (
  `HospitalID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` text NOT NULL,
  `Address` text NOT NULL,
  `ContactNo` text NOT NULL,
  `EmergencyNo` text NOT NULL,
  `WorkingHours` text NOT NULL,
  `State` text NOT NULL,
  `City` text NOT NULL,
  UNIQUE KEY `HID` (`HospitalID`)
) ENGINE=MyISAM AUTO_INCREMENT=827559361 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`HospitalID`, `Name`, `Address`, `ContactNo`, `EmergencyNo`, `WorkingHours`, `State`, `City`) VALUES
(66778899, 'Surve Hospital', 'Patas', '7743819915', '1111111111', '24hrs', 'Maharashtra (MH)', 'Pune'),
(827559360, 'Raje Hospital', 'pune', '7589534526', '66778899', '26Hrs', 'Maharashtra (MH)', 'Raigad'),
(458458458, 'pyramid Hospital', 'Daund pune Sidhatek road', '4989989898', '4565645644', '22Hrs', 'Maharashtra (MH)', 'Pune');

-- --------------------------------------------------------

--
-- Table structure for table `labs`
--

DROP TABLE IF EXISTS `labs`;
CREATE TABLE IF NOT EXISTS `labs` (
  `LabName` text NOT NULL,
  `LabID` int(11) NOT NULL,
  `Type` text,
  `Address` text,
  `State` text,
  `City` text,
  UNIQUE KEY `LID` (`LabID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labs`
--

INSERT INTO `labs` (`LabName`, `LabID`, `Type`, `Address`, `State`, `City`) VALUES
('Shokantika', 7591536, 'Temp', 'Pune near Ambegaon', 'Maharashtra (MH)', 'Pune'),
('Radiologists', 14414323, 'Radiology', 'Pune, Ambegaon bk near Maharastra Bank', 'Maharashtra (MH)', 'Raigad');

-- --------------------------------------------------------

--
-- Table structure for table `medical`
--

DROP TABLE IF EXISTS `medical`;
CREATE TABLE IF NOT EXISTS `medical` (
  `MedicalID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` text NOT NULL,
  `Address` text NOT NULL,
  `ContactNo` text NOT NULL,
  `WorkerID` text NOT NULL,
  `State` text NOT NULL,
  `City` text NOT NULL,
  UNIQUE KEY `MID` (`MedicalID`)
) ENGINE=MyISAM AUTO_INCREMENT=75315986 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medical`
--

INSERT INTO `medical` (`MedicalID`, `Name`, `Address`, `ContactNo`, `WorkerID`, `State`, `City`) VALUES
(75315985, 'Drugs', 'patas near takle Road', '78951566', '45845458', 'Maharashtra (MH)', 'Pune');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `ID` int(11) NOT NULL,
  `Allergies` text NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`ID`, `Allergies`) VALUES
(11223344, 'mahit nahi'),
(989009909, 'Dust'),
(989009908, 'Dust'),
(989009907, 'Dust'),
(989009910, 'girls'),
(989009911, 'girls'),
(989009912, 'girls'),
(989009913, 'girls'),
(989009914, 'hj'),
(989009915, 'h'),
(989009916, 'a'),
(989009917, 'b'),
(989009918, 'b'),
(989009919, 'b'),
(989009920, 'b'),
(989009921, 'b'),
(989009922, 'h'),
(989009923, 'h'),
(989009924, 'h'),
(989009925, 'h'),
(989009926, 'h'),
(989009927, 'b'),
(989009928, 'girls'),
(989009930, 'viruswifi '),
(989009931, 'viruswifi '),
(989009932, 'b'),
(989009933, 'b'),
(989009934, 'study');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
CREATE TABLE IF NOT EXISTS `prescription` (
  `PatientID` int(11) NOT NULL,
  `DoctorID` int(11) NOT NULL,
  `PrescriptionID` int(11) NOT NULL AUTO_INCREMENT,
  `Pic` text NOT NULL,
  `Date` text NOT NULL,
  `MdedicalID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PrescriptionID`),
  KEY `MDFC` (`MdedicalID`)
) ENGINE=MyISAM AUTO_INCREMENT=42545559 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`PatientID`, `DoctorID`, `PrescriptionID`, `Pic`, `Date`, `MdedicalID`) VALUES
(11223344, 98900989, 1145236, '459', '15/75/2015', 75315985),
(11223344, 98900989, 42545555, '4544', '12/5/2016', 75315985),
(11223344, 98900989, 42545558, 'Vincent.jpg', 'Mar 16, 2018 5:22:23 PM', 75315985),
(11223344, 98900989, 42545557, 'Vincent.jpg', 'Mar 16, 2018 5:21:23 PM', 75315985);

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
CREATE TABLE IF NOT EXISTS `reports` (
  `DoctorID` int(11) NOT NULL,
  `PatientID` int(11) NOT NULL,
  `LabID` int(11) DEFAULT NULL,
  `ReportType` text NOT NULL,
  `ReportName` text NOT NULL,
  `Description` text NOT NULL,
  `Pic` text NOT NULL,
  `Date` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`DoctorID`, `PatientID`, `LabID`, `ReportType`, `ReportName`, `Description`, `Pic`, `Date`) VALUES
(98900989, 11223344, 14414323, 'Pregnancy', 'Sonography report', 'Pregnancy: The state of carrying a developing embryo or fetus within the female body. ... It is in the first trimester that some women experience \"morning sickness,\" a form of nausea on awaking that usually passes within an hour.', '2356', '8/9/2015'),
(98900989, 11223344, 14414323, 'Pregnancy', 'Sonography report', 'Pregnancy: The state of carrying a developing embryo or fetus within the female body. ... It is in the first trimester that some women experience \"morning sickness,\" a form of nausea on awaking that usually passes within an hour.', '2356', '8/9/2015'),
(98900989, 11223344, 7591536, 'hafus', 'mango', 'mango the best way to get the best way ', '20180315093647jpg', 'Mar16201830646AM');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
CREATE TABLE IF NOT EXISTS `request` (
  `RequestID` int(11) NOT NULL AUTO_INCREMENT,
  `PatientID` int(11) DEFAULT NULL,
  `DoctorID` int(11) DEFAULT NULL,
  `Description` text NOT NULL,
  `Accept` text,
  `ColumnName` text NOT NULL,
  `ColumnValue` text NOT NULL,
  `Type` text NOT NULL,
  `PatientIDColumnName` text NOT NULL,
  UNIQUE KEY `1` (`RequestID`)
) ENGINE=MyISAM AUTO_INCREMENT=1223 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`RequestID`, `PatientID`, `DoctorID`, `Description`, `Accept`, `ColumnName`, `ColumnValue`, `Type`, `PatientIDColumnName`) VALUES
(1, 11223344, 48621575, 'change mobile no.', 'Pending', 'ContactNo', '143143143', 'users', 'ID'),
(2, 827559360, 98900989, 'Change Hospital Workingh Hours', 'Accept', 'WorkingHours', '26Hrs', 'hospital', 'HospitalID'),
(1221, 11223344, 48621575, 'Change in age', 'Rejected', 'Users', '23', 'Patient', 'ID');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LastName` text NOT NULL,
  `FirstName` text NOT NULL,
  `MiddleName` text NOT NULL,
  `Address` text NOT NULL,
  `ContactNo` text NOT NULL,
  `Type` text NOT NULL,
  `Gender` text NOT NULL,
  `DOB` text NOT NULL,
  `BloodGrp` text NOT NULL,
  `UserName` text NOT NULL,
  `Password` text NOT NULL,
  `NfcID` text NOT NULL,
  `RegTimestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Email` text NOT NULL,
  `AadharNo` text NOT NULL,
  `State` text NOT NULL,
  `City` text NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=989009935 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `LastName`, `FirstName`, `MiddleName`, `Address`, `ContactNo`, `Type`, `Gender`, `DOB`, `BloodGrp`, `UserName`, `Password`, `NfcID`, `RegTimestamp`, `Email`, `AadharNo`, `State`, `City`) VALUES
(11223344, 'Shitole', 'Ajit', 'Mahesh', 'pune', '143143143', 'Patient', 'Male', '04/04/1998', 'A+', 'aj_ajit', 'ajit', '048E2312FF3880', '2017-12-15 20:39:14', 'ajitshitole28@gmail.com', '11228389538457', 'Maharashtra (MH)', 'Pune'),
(12345678, 'Rao', 'Ajay', 'HJ', 'Dubai', '8275593601', 'Doctor', 'Male', '4/5/1231', 'B+', 'rockstar', 'rockstar', '888888888888', '2017-12-15 00:44:24', 'doctor_1233@gmail.com', '223322332233', 'Maharashtra (MH)', 'Pune'),
(98900989, 'shelake', 'Sagar', 'Rajesh', 'Satara', '989004588', 'Doctor', 'Male', '4/5/1995', 'B-', 'r', 'r', '753159852', '2017-12-23 22:41:13', 'ramushalke123@gmail.com', '951753258', 'Maharashtra (MH)', 'Raigad'),
(77438199, 'Gutte', 'Shrinivas', 'Uttamrao', 'Daund', '4568521594', 'Patient', 'Male', '26/7/1998', 'A+', 'gutte', 'gutte', '048234234234', '2017-12-24 10:08:00', 'ketansabale@gmail.com', '75836524521', 'Maharashtra (MH)', 'Raigad'),
(989009909, 'Rao', 'Aman', 'Amol', 'Warje Pune', '9011689890', 'Patient', 'Male', '30/09/1997', 'B+', 'amanrao1997', '123456', '011110', '2018-02-22 03:10:33', 'amanrao1997@gmail.com', '123456789014', 'Maharastra', 'Pune'),
(45845458, 'Sabale', 'Aishwarya', 'Anil', 'Daune', '464564655', 'Medical', 'Female', '1/04/1998', 'A+', 'Aish', 'Aish', '85986696658', '2017-12-30 12:15:24', 'sabale123@gmail.com', '548246864', 'Maharashtra (MH)', 'Pune'),
(48621575, 'Jadhav', 'Amerjeet', 'Sagar', 'Shri chaitanya Building, near Maharastra Bank, Pune', '7891564826', 'Doctor', 'Male', '06/11/1985', 'B+', 'sagar123', 'sagar', '785156821', '2018-01-06 02:51:19', 'sagarjadhav34@gmail.com', '753158692', 'Maharashtra (MH)', 'Pune'),
(77885544, 'Dhumal', 'Akash', 'Shankar', 'Vedang Building, Gandhi Road,Satara', '4581563264', 'Doctor', 'Male', '25/07/1977', 'A-', 'akash23', 'akash', '759852314', '2018-01-09 03:41:55', 'akashdhumal_23@gmail.com', '753656565', 'Maharashtra (MH)', 'Raigad'),
(1673, 'Shitole', 'Mahesh', 'Shankar', 'Kusegaon', '8275593601', 'Patient', 'Male', '01/06/1973', 'O+', 'mahesh', 'mh', '1122332233', '2018-02-01 14:46:12', 'maheshshitole12@gmail.com', '65467547654', 'Odisha(OR)', 'Angul'),
(989009931, 'Gutte', 'Shrinivas', 'Uttamrao', 'parbhani ', '9896587485', 'Doctor', 'Male', '14/1/1997', 'B+', 'gutte', 'gutte', 'uig3ekfykufyg', '2018-02-24 23:27:09', 'shreegutte@gmail.com', '568585457525', '-- Select State --', '-- Select City --'),
(989009928, 'Jadhav', 'Hrishikesh ', 'Harish', 'Ambegaon bk', '8275593601', 'Admin', 'Male', '16/2/1999', 'O+', 'hj', 'hj', '4634634t3t4', '2018-02-24 19:45:38', 'hrishikesh.jadhav@gmail.com', '123451245124', 'Maharashtra (MH)', 'Pune'),
(989009932, 'v', 'v', 'v', 'v', '9', 'Patient', 'Male', '31/12/2', 'B-', 'v', 'bb', 'asads', '2018-03-13 15:18:42', 'v', '124512451245', 'Assam (AS)', 'Dhubri'),
(989009933, 'v', 'v', 'v', 'v', '9', 'Patient', 'Male', '31/12/2', 'B-', 'v', 'bb', 'sdgfsdg', '2018-03-13 15:20:40', 'v', '124512451245', 'Assam (AS)', '-- Select City --'),
(989009934, 'Korade', 'Vinit', 'Sadashiv', 'Sidhatec road dhumal vasti, daund', '7743819915', 'Patient', 'Male', '31/12/1999', 'A+', 'vinit', 'vinit', '40E66B38', '2018-03-16 20:31:53', 'amarjeetghongade@gmail.com', '124512451245', 'Maharashtra (MH)', 'Pune');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
