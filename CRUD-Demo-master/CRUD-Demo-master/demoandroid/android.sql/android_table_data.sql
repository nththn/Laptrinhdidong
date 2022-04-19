
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `data`
--
-- Tạo: Th3 26, 2022 lúc 04:03 AM
-- Cập nhật lần cuối: Th4 15, 2022 lúc 03:11 AM
--

DROP TABLE IF EXISTS `data`;
CREATE TABLE IF NOT EXISTS `data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `contact` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- RELATIONSHIPS FOR TABLE `data`:
--

--
-- Đang đổ dữ liệu cho bảng `data`
--

INSERT INTO `data` (`id`, `name`, `email`, `contact`, `address`) VALUES
(3, 'nguyen le the', 'lethe@gmail.com', '290234452', '08 phan thanh da nang'),
(10, 'ha', 'ha@gmail.com', '090341', '91 ngu han son');
