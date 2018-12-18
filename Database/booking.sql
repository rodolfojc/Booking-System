-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2018 at 06:46 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `booking`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrators`
--

CREATE TABLE `administrators` (
  `admin_id` int(11) NOT NULL,
  `admin_user` varchar(50) NOT NULL,
  `admin_pass` varchar(256) NOT NULL,
  `privilege` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrators`
--

INSERT INTO `administrators` (`admin_id`, `admin_user`, `admin_pass`, `privilege`) VALUES
(9, 'admin@admin.admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '1'),
(10, 'karla@admin.admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '2'),
(11, 'rodolfo@admin.admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '2'),
(12, 'chriss@admin.admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '2'),
(13, 'krisbell@admin.admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '2'),
(14, 'kamil@admin.admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '2'),
(16, 'claribel@admin.admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '2');

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appoint_ref` int(11) NOT NULL,
  `avai_ref` int(11) DEFAULT NULL,
  `cust_id` int(11),
  `comments` varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appoint_ref`, `avai_ref`, `cust_id`, `comments`) VALUES
(1, 4, 1, 'Admin: Well done'),
(2, 10, 6, 'Customer: Waiting for confirmation'),
(3, 12, 6, 'No'),
(4, 15, 5, 'Customer: Why the provider has cancelled my appointment?'),
(5, 16, 5, 'No');

-- --------------------------------------------------------

--
-- Table structure for table `availabilities`
--

CREATE TABLE `availabilities` (
  `avai_ref` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `available` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `availabilities`
--

INSERT INTO `availabilities` (`avai_ref`, `pro_id`, `date`, `time`, `available`) VALUES
(1, 1, '2018-12-17', '08:00:00', 'Yes'),
(4, 1, '2018-12-20', '10:00:00', 'Confirmed'),
(5, 1, '2018-12-21', '11:30:00', 'Yes'),
(6, 2, '2018-12-18', '13:00:00', 'Yes'),
(7, 2, '2018-12-19', '13:30:00', 'Yes'),
(8, 2, '2018-12-20', '14:30:00', 'Cancelled'),
(10, 5, '2018-12-14', '09:30:00', 'Cancelled'),
(12, 5, '2018-12-16', '13:30:00', 'Confirmed'),
(13, 7, '2018-12-18', '08:00:00', 'Yes'),
(14, 7, '2018-12-19', '09:00:00', 'Yes'),
(15, 7, '2018-12-20', '10:00:00', 'Cancelled'),
(16, 7, '2018-12-21', '14:00:00', 'Confirmed'),
(18, 8, '2018-12-17', '12:30:00', 'Yes'),
(19, 8, '2018-12-17', '13:00:00', 'Yes'),
(21, 5, '2018-12-17', '11:00:00', 'Yes'),
(22, 5, '2018-12-25', '11:30:00', 'Yes'),
(24, 5, '2018-12-29', '16:00:00', 'Yes'),
(25, 1, '2018-12-30', '12:00:00', 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `cust_id` int(11) NOT NULL,
  `cust_name` varchar(35) NOT NULL,
  `cust_surname` varchar(35) NOT NULL,
  `mob_num` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `pass` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`cust_id`, `cust_name`, `cust_surname`, `mob_num`, `email`, `address`, `pass`) VALUES
(1, 'Rodolfo', 'Carvajal', '0834440503', 'rodolfo@rodolfo.com', '114 Church Road, East Wall', 'd0093c0cc9527f31105b74f8596559e232306d87220721246c580be5a9c9262722821edd0f4ccad4cad2048677c203c36879e1294dd931c15a6b212fd8b2ff5f'),
(3, 'Carlos', 'Velez', '0835872958', 'carlos@carlos.com', '56 Munthjoy Square, Dublin 3', '3c9f2dd6503e5fde0f9c0dc70bbf4e59c93d2bdd1a34f5abf03f3ce693656709a16980273cb46282dcdb85234dc70ea1c3999226c4875ee199eac32e7de92479'),
(5, 'Olivo', 'Belandria', '0894432876', 'olivo@olivo.com', '113 Church Road, East Wall Dublin 3', '8ae02a19aec3bdc2167aab716a74c10fb2a6992b40188fb14654e442c7582b239d2ff77de9afdfefe7ba5c201d3b1d7338c4a484ef94a131b5fb6089f75609c8'),
(6, 'Oliver', 'Marquez', '0835558732', 'oliver@oliver.com', '43 Montjoy Square, Dublin 1', '6c114dd4de6ea003472fb91d8deca5cef6abfc3438f08418e79e5c1afe753d2708f3ced85f11f4f1ca1c12734c97e68943070ace5b7c8aae51c7bea323eda2d4'),
(8, 'Frank', 'Belandria', '0894437645', 'frank@frank.com', '123 Sandymount Rd. Dublin 4', 'bdb2c2540af6e4c9a8947ef691df0344ddec374218851fdbed039cc05e9ea460fe417bf7be8d83db3f5a004d67ed75f6aa30c189ffb68193a408a21f8cce5084');

-- --------------------------------------------------------

--
-- Table structure for table `providers`
--

CREATE TABLE `providers` (
  `pro_id` int(11) NOT NULL,
  `pro_name` varchar(35) NOT NULL,
  `pro_surname` varchar(35) NOT NULL,
  `mob_num` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `location` varchar(50) NOT NULL,
  `pass` varchar(256) NOT NULL,
  `status` varchar(11) NOT NULL,
  `reg_day` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `providers`
--

INSERT INTO `providers` (`pro_id`, `pro_name`, `pro_surname`, `mob_num`, `email`, `address`, `location`, `pass`, `status`, `reg_day`) VALUES
(1, 'Victor', 'Rangel', '0893872846', 'victor@victor.com', '46 Clontarf Road, Dublin 3', 'Dublin 3', '99fb44ccf2d30176f363b3331c2779eef8bb802ab91f45ba162e87331b384b5b3f47b7838adc3b7ece0e095cb3514703dbe933779109d579fa98b3491827b703', 'Confirmed', '2018-12-10'),
(2, 'Jean', 'Moretti', '0894529843', 'jean@jean.com', '99 Circular Road, Dublin 1', 'Dublin 6', '0bdc0c0b80324237b577b24e2ef1bf61fd9d0ac20b1d19a6e6f6f185204293c1726c8834692ecef3f8c185c4d14df42918374bbbe67098e28eea145c33f7eb0d', 'Confirmed', '2018-12-10'),
(4, 'Cesar', 'Padron', '0894347645', 'cesar@cesar.com', '12 Fairview Road, Dublin 3', 'Dublin 3', '2ddf7f1b92febaf176d3f0d4bcaa4e7074d7079b50b4663c18a858066498784ddc14da0e071231085526d991fde8afdc48afbc4d4091d80634baf0424b8f3c54', 'Pending', '2018-12-14'),
(5, 'Juan', 'Velasquez', '0894448877', 'juan@juan.com', '14 Talbot St. Dublin 1', 'Dublin 1', '7bd30fb44f31727c0f4192c497cbd82796efc2fdf2fdd42c237fbcef1e08493a2b73e60fcb4b850c5086e5f692363bbb170e711ba6e890fe74a079ffb51ae8e0', 'Confirmed', '2018-12-14'),
(6, 'Nestor', 'Briceno', '0895574632', 'nestor@nestor.com', '78 Calderon St. Dublin 8', 'Dublin 4', '7143224d0c0e65e94653bd30cfb478dbe26635db068249d479a677962a5060092b094e399f2b8ad2a532f0e2fa3896de4dc2e174acfee4e329a23705c00f2b8b', 'Pending', '2018-12-14'),
(7, 'Manuel', 'Cerrada', '0892025837', 'manuel@manuel.com', '34 Hamilton St. Dublin 7 ', 'The Latin Barbers', '66ffd3e973549f3702bbcd390468eb78c450e8b32868590ce9a22fb1d059210f3ec85608b418ec77589f3bf8f24ef0c43513c6817808d5fa1fa01d8116e0791c', 'Confirmed', '2018-12-14'),
(8, 'Orlando', 'Trasmonte', '9874327645', 'orlando@orlando.com', '43 May Ln Dublin 1', 'StyleShop', 'b57bf9a65700226f2a457f2a67c41bc359ff84ce16c699f387ffbb5f480f994d86899dd4edaf8a1727e69a9dcf5d6c9c2ba5f76bc88bda5d86a13f186e7c1ecb', 'Confirmed', '2018-12-14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrators`
--
ALTER TABLE `administrators`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appoint_ref`),
  ADD KEY `FK_custID` (`cust_id`),
  ADD KEY `FK_ref` (`avai_ref`);

--
-- Indexes for table `availabilities`
--
ALTER TABLE `availabilities`
  ADD PRIMARY KEY (`avai_ref`),
  ADD KEY `FK_proID` (`pro_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`cust_id`);

--
-- Indexes for table `providers`
--
ALTER TABLE `providers`
  ADD PRIMARY KEY (`pro_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrators`
--
ALTER TABLE `administrators`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appoint_ref` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `availabilities`
--
ALTER TABLE `availabilities`
  MODIFY `avai_ref` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `cust_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `providers`
--
ALTER TABLE `providers`
  MODIFY `pro_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `FK_custID` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`cust_id`),
  ADD CONSTRAINT `FK_ref` FOREIGN KEY (`avai_ref`) REFERENCES `availabilities` (`avai_ref`);

--
-- Constraints for table `availabilities`
--
ALTER TABLE `availabilities`
  ADD CONSTRAINT `FK_proID` FOREIGN KEY (`pro_id`) REFERENCES `providers` (`pro_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
