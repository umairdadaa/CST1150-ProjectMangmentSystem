-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2021 at 12:48 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectmanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `projectinfo`
--

CREATE TABLE `projectinfo` (
  `projectID` int(50) NOT NULL,
  `projectClient` varchar(200) NOT NULL,
  `projectTitle` text NOT NULL,
  `projectCost` bigint(100) NOT NULL,
  `projectRequirement` text NOT NULL,
  `projectDeliveryDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `projectinfo`
--

INSERT INTO `projectinfo` (`projectID`, `projectClient`, `projectTitle`, `projectCost`, `projectRequirement`, `projectDeliveryDate`) VALUES
(1, 'Client Name', 'Project Title - Completed', 200000, '1. Project Requirements\n2. Requirement #2\n3. Requirement #3\n4. Requirement #4', '2020-04-15'),
(2, 'Java BD', 'Business Dev', 12560, '1.\n2.\n3.\n4.', '2021-06-16'),
(3, 'Nasa', 'PS6', 200000, 'Best console possible\nController\nWHeeel\nCAmera\n95 Inch TV Custom', '2025-07-07');

-- --------------------------------------------------------

--
-- Table structure for table `project_resource`
--

CREATE TABLE `project_resource` (
  `resourceID` int(100) NOT NULL,
  `resourceUser` varchar(100) NOT NULL,
  `resourceProject` int(100) NOT NULL,
  `resourceRole` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project_resource`
--

INSERT INTO `project_resource` (`resourceID`, `resourceUser`, `resourceProject`, `resourceRole`) VALUES
(1, 'A02', 2, 'GUI Developer'),
(2, 'A03', 2, 'Backend Developer'),
(3, 'A02', 6, 'QA/Testing'),
(4, 'A01', 6, 'Backend Developer'),
(5, 'A03', 6, 'Project Manager'),
(6, 'A04', 6, 'DataBase Developer'),
(7, 'A01', 6, 'GUI Developer'),
(8, 'A03', 7, 'Project Manager'),
(9, 'A02', 7, 'QA/Testing'),
(10, 'A04', 7, 'Backend Developer'),
(11, 'A04', 7, 'GUI Developer'),
(12, 'A01', 7, 'DataBase Developer'),
(13, 'A02', 7, 'Backend Developer'),
(14, 'A01', 8, 'Project Manager');

-- --------------------------------------------------------

--
-- Table structure for table `task_details`
--

CREATE TABLE `task_details` (
  `Task_id` int(100) NOT NULL,
  `Task_project` int(100) NOT NULL,
  `Task_details` text NOT NULL,
  `Task_resource` varchar(100) NOT NULL,
  `Task_completionDate` date NOT NULL,
  `Task_Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `task_details`
--

INSERT INTO `task_details` (`Task_id`, `Task_project`, `Task_details`, `Task_resource`, `Task_completionDate`, `Task_Status`) VALUES
(1, 2, 'Create Project Details', 'A03', '2021-04-10', 'Pending'),
(2, 6, 'Project Overview', 'A02', '2021-06-08', 'Pending'),
(3, 6, 'Test', 'A02', '2021-03-30', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `userID` varchar(50) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `userEmail` varchar(50) NOT NULL,
  `userContact` bigint(100) NOT NULL,
  `userAddress` varchar(200) NOT NULL,
  `userPassword` varchar(50) NOT NULL,
  `userType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`userID`, `userName`, `userEmail`, `userContact`, `userAddress`, `userPassword`, `userType`) VALUES
('A01', 'Ayaan', 'businessdev@javaproject.com', 971558253612, 'Knowledge Park, Dubai', '123456', 'Business Developer'),
('A02', 'Umair', 'admin@javaproject.com', 971529515397, 'Knowledge Park, Dubai', '12345', 'Admin'),
('A03', 'Dada', 'projectmanager@javaproject.com', 971562666318, 'Knowledge Park, Dubai', '1234567', 'Project Manager'),
('A04', 'Khan', 'projectdeveloper@javaproject.com', 971505455488, 'Knowledge Park, Dubai', '1234', 'Project Developer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `projectinfo`
--
ALTER TABLE `projectinfo`
  ADD PRIMARY KEY (`projectID`);

--
-- Indexes for table `project_resource`
--
ALTER TABLE `project_resource`
  ADD PRIMARY KEY (`resourceID`);

--
-- Indexes for table `task_details`
--
ALTER TABLE `task_details`
  ADD PRIMARY KEY (`Task_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `projectinfo`
--
ALTER TABLE `projectinfo`
  MODIFY `projectID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `project_resource`
--
ALTER TABLE `project_resource`
  MODIFY `resourceID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `task_details`
--
ALTER TABLE `task_details`
  MODIFY `Task_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
