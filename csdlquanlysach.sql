-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2019 at 01:08 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `csdlquanlysach`
--

-- --------------------------------------------------------

--
-- Table structure for table `nhaxuatban`
--

CREATE TABLE `nhaxuatban` (
  `ID` int(10) NOT NULL,
  `TenNXB` text NOT NULL,
  `DiaChi` text NOT NULL,
  `DienThoai` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhaxuatban`
--

INSERT INTO `nhaxuatban` (`ID`, `TenNXB`, `DiaChi`, `DienThoai`) VALUES
(1, 'Nhà xuất bản Trẻ', '161B Lý Chính Thắng, Phường 7, Quận 3, Thành phố Hồ Chí Minh', '(028) 39316289'),
(2, 'Nhà xuất bản Tổng hợp thành phố Hồ Chí Minh', '62 Nguyễn Thị Minh Khai, Phường Đa Kao, Quận 1, TP.HCM', ' (028) 38 225 340'),
(3, 'Nhà xuất bản chính trị quốc gia', 'Số 24 Quang Trung, Hoàn Kiếm, Hà Nội', '024.3822-1633'),
(4, 'Nhà xuất bản giáo dục', '81 Trần Hưng Đạo, Hà Nội', '024.38220801'),
(5, 'Nhà xuất bản Hội Nhà văn', 'Số 65 Nguyễn Du, Hà Nội', ' 024.38222135'),
(6, 'Nhà xuất bản Tư pháp', 'Số 35 Trần Quốc Toản, Hoàn Kiếm, Hà Nội', '024.62632078'),
(7, 'Nhà xuất bản thông tin và truyền thông', 'Tầng 6, Tòa nhà Cục Tần số Vô tuyến điện, số 115 Trần Duy Hưng, Hà Nội ', '024 35772145'),
(8, 'Nhà xuất bản lao động', '175 Giảng Võ, Đống Đa, Hà Nội', '0243.8515380 '),
(9, 'Nhà xuất bản giao thông vận tải', ' 80B Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', ' 024 3.9423346'),
(10, 'Công ty Cổ phần Sách Thái Hà', '533/9 Huỳnh Văn Bánh, P. 14, Q. Phú Nhuận, TP. Hồ Chí Minh', '08 3991 3276'),
(3333, 'nha xuat ban a', 'can tho', '21231231'),
(22222, 'eeeeee', 'eeeeeê', '344353434'),
(55555, '555555555', '5555555', '123123'),
(1111111, 'aaaaa', 'á', '1111111');

-- --------------------------------------------------------

--
-- Table structure for table `sach`
--

CREATE TABLE `sach` (
  `IDSach` varchar(10) NOT NULL,
  `tensach` text NOT NULL,
  `IDNhaXuatBan` int(10) NOT NULL,
  `sotrang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sach`
--

INSERT INTO `sach` (`IDSach`, `tensach`, `IDNhaXuatBan`, `sotrang`) VALUES
('001', 'Oxford Learner\'s Pocket Dictionary', 1, 324),
('002', 'Nhà Giả Kim', 2, 142),
('003', 'Tuổi Trẻ Đáng Giá Bao Nhiêu', 3, 352),
('004', 'Đắc Nhân Tâm (Khổ Lớn)', 4, 122),
('005', 'Khí Chất Bao Nhiêu, Hạnh Phúc Bấy Nhiêu', 5, 412),
('006', 'Nghĩ Đơn Giản, Sống Đơn Thuần', 6, 513),
('007', 'Bạn Đắt Giá Bao Nhiêu?', 7, 98),
('fdgdf', 'dfgdfgdfhhh', 1, 78);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `IDUser` int(11) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `Email` char(100) NOT NULL,
  `Phone` char(11) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`IDUser`, `FullName`, `Email`, `Phone`, `Address`) VALUES
(1, 'Hồ Tấn Mỹ', 'kd.aigapa@gmail.com', '0964082598', '309/3 Nguyễn Thái Sơn, P5,Gò Vấp, HCM'),
(2, 'Nguyên', 'nguyen@gmail.com', '0987654321', 'Abc xyz'),
(6, 'asdasd', 'ad@local.vn', '+9987654321', 'asdasdasd'),
(10, 'dasdas', 'tanmy@localhost.com', '0964082589', 'asdasdas'),
(11, 'asdasd', 'asdad@local.vn', '0987654321', 'asdasd'),
(12, 'tan my', 'local@local.vn', '0964082598', 'nha cua my'),
(14, '1231asdasd', 'ads@localhost.vn', '0987654321', 'adasdasd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`IDSach`),
  ADD KEY `IDNhaXuatBan` (`IDNhaXuatBan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`IDUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `IDUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `sach_ibfk_1` FOREIGN KEY (`IDNhaXuatBan`) REFERENCES `nhaxuatban` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
