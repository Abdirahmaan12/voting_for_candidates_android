-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2023 at 12:25 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs19k_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `candidates`
--

CREATE TABLE `candidates` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `img` varchar(50) NOT NULL,
  `memo` varchar(50) NOT NULL,
  `elec_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `date_reg` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `candidates`
--

INSERT INTO `candidates` (`id`, `name`, `img`, `memo`, `elec_id`, `p_id`, `date_reg`) VALUES
(1, 'abdirahmaan mohamed', 'images/canpr1.jpg', 'cadaalad iyo sinaan', 2, 2, '2022-12-27 17:54:54'),
(2, 'anwar isaakh', 'images/canpr4.jpg', 'Tuuganimo iyo dil', 2, 2, '2022-12-27 17:54:54'),
(3, 'farxaan saciid nuur', 'images/canpr3.jpg', 'cadaalad iyo sinaan', 2, 1, '2022-12-27 17:58:12'),
(4, 'maxamed deeq', 'images/canpr5.jpg', 'cadaalad iyo sinaan', 2, 1, '2022-12-27 17:59:46'),
(5, 'xaliima saciya ali', 'images/canpr6.jpg', 'cadaalad iyo sinaan', 2, 2, '2022-12-27 18:02:26'),
(6, 'maxamuud cali anwar', 'images/canpr7.jpg', 'cadaalad iyo sinaan', 2, 2, '2022-12-27 18:03:08');

-- --------------------------------------------------------

--
-- Table structure for table `election`
--

CREATE TABLE `election` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `date_reg` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `election`
--

INSERT INTO `election` (`id`, `name`, `date_reg`) VALUES
(1, 'Doorashada gudoomiyaha ardayda 2020', '2022-12-26 13:52:47'),
(2, 'Doorashada gudoomiyaha ardayda 2023', '2022-12-26 13:52:59');

-- --------------------------------------------------------

--
-- Table structure for table `positions`
--

CREATE TABLE `positions` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `elec_id` int(11) NOT NULL,
  `date_reg` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `positions`
--

INSERT INTO `positions` (`id`, `name`, `elec_id`, `date_reg`) VALUES
(1, 'Gudoomiye', 2, '2022-12-26 20:19:40'),
(2, 'Gudoomiye ku xigeen', 2, '2022-12-26 20:19:49');

-- --------------------------------------------------------

--
-- Table structure for table `voters`
--

CREATE TABLE `voters` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `elec_id` int(11) NOT NULL,
  `v_status` int(11) NOT NULL DEFAULT 0,
  `date_reg` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `voters`
--

INSERT INTO `voters` (`id`, `name`, `username`, `pass`, `elec_id`, `v_status`, `date_reg`) VALUES
(1, 'Abdirahmaan mohamed ahmed', 'samafale', '123', 2, 1, '2022-12-26 13:31:59'),
(2, 'mohamed deeq muqtaar', 'moha', '1111', 2, 1, '2023-01-24 00:10:46');

-- --------------------------------------------------------

--
-- Table structure for table `voting`
--

CREATE TABLE `voting` (
  `id` int(11) NOT NULL,
  `v_id` int(11) NOT NULL,
  `can_id` int(11) NOT NULL,
  `elec_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `date_reg` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `voting`
--

INSERT INTO `voting` (`id`, `v_id`, `can_id`, `elec_id`, `p_id`, `date_reg`) VALUES
(1, 1, 1, 2, 2, '2023-01-03 00:49:46'),
(2, 2, 5, 2, 2, '2023-01-03 00:51:32'),
(3, 1, 3, 2, 1, '2023-01-03 02:33:12'),
(4, 2, 4, 2, 1, '2023-01-04 17:28:47');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `candidates`
--
ALTER TABLE `candidates`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `election`
--
ALTER TABLE `election`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `positions`
--
ALTER TABLE `positions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `voters`
--
ALTER TABLE `voters`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `voting`
--
ALTER TABLE `voting`
  ADD PRIMARY KEY (`id`),
  ADD KEY `can_id` (`can_id`),
  ADD KEY `p_id` (`p_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `candidates`
--
ALTER TABLE `candidates`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `election`
--
ALTER TABLE `election`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `positions`
--
ALTER TABLE `positions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `voters`
--
ALTER TABLE `voters`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `voting`
--
ALTER TABLE `voting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
