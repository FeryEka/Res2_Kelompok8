-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2018 at 06:18 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_laundry`
--

-- --------------------------------------------------------

--
-- Table structure for table `inputan`
--

CREATE TABLE `inputan` (
  `KdPelanggan` int(11) NOT NULL,
  `Tanggal` date NOT NULL,
  `Nama_Pelanggan` varchar(50) NOT NULL,
  `No_Telp` varchar(50) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `BeratPakaian` varchar(50) NOT NULL,
  `Harga` varchar(50) NOT NULL,
  `TotalBayar` varchar(50) NOT NULL,
  `BayarUang` varchar(50) NOT NULL,
  `Kembalian` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inputan`
--

INSERT INTO `inputan` (`KdPelanggan`, `Tanggal`, `Nama_Pelanggan`, `No_Telp`, `Alamat`, `BeratPakaian`, `Harga`, `TotalBayar`, `BayarUang`, `Kembalian`) VALUES
(1, '2018-12-07', 'Astuti', '085334555777', 'jl.candi 3e', '10', '6000', '60000', '', '0'),
(100, '2018-12-07', 'sherly kampreet', '', 'malang', '90', '6000', '540000', '', '0');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin', 'admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
