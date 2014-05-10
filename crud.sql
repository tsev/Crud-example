CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6;

INSERT INTO `user` (`id`, `age`, `name`) VALUES
(1, 26, 'Alexander'),
(2, 16, 'Boris'),
(3, 31, 'Cris'),
(4, 12, 'Denis'),
(5, 3, 'Egor'),
(6, 102, 'Faina');
