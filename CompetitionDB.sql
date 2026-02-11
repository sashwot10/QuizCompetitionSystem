-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 11, 2026 at 02:30 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `CompetitionDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`admin_id`, `username`, `password`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE `players` (
  `player_id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `level` enum('BEGINNER','INTERMEDIATE','ADVANCED') DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `overall_score` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`player_id`, `first_name`, `last_name`, `level`, `age`, `overall_score`) VALUES
(6, 'test 2', '2', 'BEGINNER', 12, 0),
(7, 'test beg', 'test', 'BEGINNER', 10, 0),
(8, 'test', 'adv', 'ADVANCED', 22, 0),
(9, 'test', 'intermed', 'INTERMEDIATE', 18, 0),
(10, 'Sashwot', 'Pokharel', 'ADVANCED', 24, 0),
(11, 'David', 'Black', 'ADVANCED', 25, 0),
(12, 'Carol', 'White', 'ADVANCED', 26, 0),
(13, 'final', 'test', 'INTERMEDIATE', 100, 0);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `question_id` int(11) NOT NULL,
  `question_text` varchar(255) DEFAULT NULL,
  `option_a` varchar(100) DEFAULT NULL,
  `option_b` varchar(100) DEFAULT NULL,
  `option_c` varchar(100) DEFAULT NULL,
  `option_d` varchar(100) DEFAULT NULL,
  `correct_option` char(1) DEFAULT NULL,
  `level` enum('BEGINNER','INTERMEDIATE','ADVANCED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`question_id`, `question_text`, `option_a`, `option_b`, `option_c`, `option_d`, `correct_option`, `level`) VALUES
(56, 'What is 1 + 1?', '1', '2', '3', '4', 'B', 'BEGINNER'),
(57, 'What color is the sun?', 'Blue', 'Red', 'Yellow', 'Green', 'C', 'BEGINNER'),
(58, 'Which animal meows?', 'Dog', 'Cat', 'Cow', 'Horse', 'B', 'BEGINNER'),
(59, 'What is the capital of Italy?', 'Rome', 'Paris', 'Berlin', 'London', 'A', 'BEGINNER'),
(60, 'How many legs does a spider have?', '6', '8', '4', '10', 'B', 'BEGINNER'),
(61, 'Which fruit is red?', 'Banana', 'Apple', 'Orange', 'Grapes', 'B', 'BEGINNER'),
(62, 'What day comes after Monday?', 'Sunday', 'Tuesday', 'Friday', 'Wednesday', 'B', 'BEGINNER'),
(63, 'Which shape has four equal sides?', 'Circle', 'Triangle', 'Rectangle', 'Square', 'D', 'BEGINNER'),
(64, 'What is 5 - 2?', '2', '3', '4', '5', 'B', 'BEGINNER'),
(65, 'Which animal gives us milk?', 'Cow', 'Dog', 'Cat', 'Chicken', 'A', 'BEGINNER'),
(66, 'What is the color of grass?', 'Green', 'Red', 'Blue', 'Yellow', 'A', 'BEGINNER'),
(67, 'How many days are in a week?', '5', '6', '7', '8', 'C', 'BEGINNER'),
(68, 'Which is a primary color?', 'Pink', 'Green', 'Red', 'Black', 'C', 'BEGINNER'),
(69, 'What is the first letter of the English alphabet?', 'A', 'B', 'C', 'D', 'A', 'BEGINNER'),
(70, 'Which animal barks??', 'Cat', 'Dog', 'Cow', 'Sheep', 'B', 'BEGINNER'),
(71, 'What is 3 + 4?', '5', '6', '7', '8', 'C', 'BEGINNER'),
(72, 'Which month comes after January?', 'March', 'February', 'April', 'May', 'B', 'BEGINNER'),
(73, 'What is the color of snow?', 'White', 'Black', 'Blue', 'Red', 'A', 'BEGINNER'),
(74, 'Which fruit is yellow?', 'Apple', 'Banana', 'Grapes', 'Cherry', 'B', 'BEGINNER'),
(75, 'How many fingers do humans have?', '8', '9', '10', '12', 'C', 'BEGINNER'),
(76, 'Which animal lives in water?', 'Dog', 'Fish', 'Cow', 'Cat', 'B', 'BEGINNER'),
(77, 'What do bees make?', 'Milk', 'Honey', 'Bread', 'Cheese', 'B', 'BEGINNER'),
(78, 'What is 10 - 7?', '2', '3', '4', '5', 'B', 'BEGINNER'),
(79, 'Which day comes before Sunday?', 'Saturday', 'Monday', 'Friday', 'Tuesday', 'A', 'BEGINNER'),
(80, 'What color are bananas?', 'Red', 'Yellow', 'Blue', 'Green', 'B', 'BEGINNER'),
(81, 'How many wheels does a bicycle have?', '1', '2', '3', '4', 'B', 'BEGINNER'),
(82, 'Which animal has a long trunk?', 'Lion', 'Elephant', 'Giraffe', 'Monkey', 'B', 'BEGINNER'),
(83, 'What is 6 + 2?', '7', '8', '9', '10', 'B', 'BEGINNER'),
(84, 'Which season comes after spring?', 'Winter', 'Autumn', 'Summer', 'Spring', 'C', 'BEGINNER'),
(85, 'What color is the sky on a clear day?', 'Red', 'Blue', 'Green', 'Yellow', 'B', 'BEGINNER'),
(86, 'Which animal says \"moo\"?', 'Cow', 'Dog', 'Cat', 'Sheep', 'A', 'BEGINNER'),
(87, 'What is 7 - 5?', '1', '2', '3', '4', 'B', 'BEGINNER'),
(88, 'Which fruit is orange?', 'Orange', 'Apple', 'Banana', 'Grapes', 'A', 'BEGINNER'),
(89, 'What is the capital of France?', 'Rome', 'Paris', 'Berlin', 'London', 'B', 'BEGINNER'),
(90, 'How many months are in a year?', '10', '11', '12', '13', 'C', 'BEGINNER'),
(91, 'Which animal hops?', 'Dog', 'Cat', 'Kangaroo', 'Sheep', 'C', 'BEGINNER'),
(92, 'What is 4 + 5?', '8', '9', '10', '7', 'B', 'BEGINNER'),
(93, 'Which color is made by mixing red and white?', 'Pink', 'Purple', 'Brown', 'Gray', 'A', 'BEGINNER'),
(94, 'What do cows give us?', 'Milk', 'Honey', 'Bread', 'Meat', 'A', 'BEGINNER'),
(95, 'How many days are in February (non-leap year)?', '28', '29', '30', '31', 'A', 'BEGINNER'),
(96, 'Which animal has stripes?', 'Lion', 'Tiger', 'Elephant', 'Monkey', 'B', 'BEGINNER'),
(97, 'What is 8 - 3?', '4', '5', '6', '7', 'B', 'BEGINNER'),
(98, 'Which planet do we live on?', 'Mars', 'Earth', 'Jupiter', 'Venus', 'B', 'BEGINNER'),
(99, 'Which fruit is red?', 'Apple', 'Banana', 'Grapes', 'Orange', 'A', 'BEGINNER'),
(100, 'What is the first day of the week?', 'Sunday', 'Monday', 'Tuesday', 'Saturday', 'A', 'BEGINNER'),
(101, 'How many legs does a cat have?', '2', '4', '6', '8', 'B', 'BEGINNER'),
(102, 'Which animal quacks?', 'Dog', 'Cat', 'Duck', 'Cow', 'C', 'BEGINNER'),
(103, 'What is 9 + 1?', '9', '10', '11', '12', 'B', 'BEGINNER'),
(104, 'Which season comes after summer?', 'Winter', 'Autumn', 'Spring', 'Summer', 'B', 'BEGINNER'),
(105, 'Which color is a mix of red and blue?', 'Green', 'Purple', 'Orange', 'Brown', 'B', 'BEGINNER'),
(106, 'What is 5 x 2?', '7', '8', '9', '10', 'D', 'BEGINNER'),
(107, 'Which animal has a mane?', 'Tiger', 'Elephant', 'Lion', 'Dog', 'C', 'BEGINNER'),
(108, 'What is 12 - 5?', '5', '6', '7', '8', 'C', 'BEGINNER'),
(109, 'Which fruit is green?', 'Apple', 'Banana', 'Kiwi', 'Cherry', 'C', 'BEGINNER'),
(110, 'What do birds use to fly?', 'Wings', 'Fins', 'Legs', 'Arms', 'A', 'BEGINNER'),
(111, 'How many eyes do humans have?', '1', '2', '3', '4', 'B', 'BEGINNER'),
(112, 'Which animal is known as king of jungle?', 'Lion', 'Tiger', 'Elephant', 'Bear', 'A', 'BEGINNER'),
(113, 'What is 3 x 3?', '6', '9', '12', '15', 'B', 'BEGINNER'),
(114, 'Which fruit is small and red?', 'Strawberry', 'Banana', 'Apple', 'Cherry', 'A', 'BEGINNER'),
(115, 'What is 6 + 7?', '12', '13', '14', '15', 'B', 'BEGINNER'),
(116, 'Which day is after Thursday?', 'Friday', 'Saturday', 'Sunday', 'Wednesday', 'A', 'BEGINNER'),
(117, 'Which animal lives in water?', 'Fish', 'Dog', 'Cow', 'Cat', 'A', 'BEGINNER'),
(118, 'How many sides does a triangle have?', '2', '3', '4', '5', 'B', 'BEGINNER'),
(119, 'What is 7 + 2?', '8', '9', '10', '11', 'B', 'BEGINNER'),
(120, 'Which fruit is long and yellow?', 'Apple', 'Banana', 'Orange', 'Grapes', 'B', 'BEGINNER'),
(121, 'What is the color of coal?', 'Black', 'White', 'Red', 'Green', 'A', 'BEGINNER'),
(122, 'Which animal is slow?', 'Turtle', 'Dog', 'Cat', 'Rabbit', 'A', 'BEGINNER'),
(123, 'What is 5 - 3?', '1', '2', '3', '4', 'B', 'BEGINNER'),
(124, 'Which season is coldest?', 'Summer', 'Winter', 'Autumn', 'Spring', 'B', 'BEGINNER'),
(125, 'What do bees collect from flowers?', 'Honey', 'Pollen', 'Nectar', 'Leaves', 'C', 'BEGINNER'),
(126, 'Which animal has wings?', 'Bird', 'Dog', 'Fish', 'Cat', 'A', 'BEGINNER'),
(127, 'What is 9 - 4?', '4', '5', '6', '7', 'B', 'BEGINNER'),
(128, 'Which fruit is juicy and red?', 'Apple', 'Cherry', 'Banana', 'Orange', 'A', 'BEGINNER'),
(129, 'What is 10 + 5?', '14', '15', '16', '17', 'B', 'BEGINNER'),
(130, 'Which day comes before Wednesday?', 'Tuesday', 'Monday', 'Sunday', 'Thursday', 'A', 'BEGINNER'),
(131, 'What is the color of leaves?', 'Green', 'Red', 'Yellow', 'Blue', 'A', 'BEGINNER'),
(132, 'Which animal hops?', 'Kangaroo', 'Dog', 'Cat', 'Rabbit', 'A', 'BEGINNER'),
(133, 'What is 8 + 2?', '9', '10', '11', '12', 'B', 'BEGINNER'),
(134, 'Which fruit is tropical and yellow?', 'Banana', 'Apple', 'Mango', 'Orange', 'A', 'BEGINNER'),
(135, 'What is 4 x 2?', '6', '7', '8', '9', 'C', 'BEGINNER'),
(136, 'Which animal says \"oink\"?', 'Pig', 'Dog', 'Cat', 'Cow', 'A', 'BEGINNER'),
(137, 'What is 3 + 5?', '7', '8', '9', '10', 'B', 'BEGINNER'),
(138, 'Which fruit grows on trees?', 'Apple', 'Potato', 'Tomato', 'Carrot', 'A', 'BEGINNER'),
(139, 'What is 6 - 1?', '4', '5', '6', '7', 'B', 'BEGINNER'),
(140, 'Which color is used to stop?', 'Red', 'Green', 'Yellow', 'Blue', 'A', 'BEGINNER'),
(141, 'What is 7 x 1?', '6', '7', '8', '9', 'B', 'BEGINNER'),
(142, 'Which animal swims?', 'Fish', 'Dog', 'Cat', 'Elephant', 'A', 'BEGINNER'),
(143, 'What is 5 + 4?', '8', '9', '10', '11', 'B', 'BEGINNER'),
(144, 'Which fruit is small and blue?', 'Blueberry', 'Apple', 'Grapes', 'Banana', 'A', 'BEGINNER'),
(145, 'What is 10 - 2?', '7', '8', '9', '10', 'D', 'BEGINNER'),
(146, 'Which animal has a long neck?', 'Giraffe', 'Elephant', 'Horse', 'Dog', 'A', 'BEGINNER'),
(147, 'What is 2 x 3?', '5', '6', '7', '8', 'B', 'BEGINNER'),
(148, 'Which day comes after Tuesday?', 'Wednesday', 'Thursday', 'Monday', 'Friday', 'A', 'BEGINNER'),
(149, 'What is 1 + 3?', '3', '4', '5', '6', 'B', 'BEGINNER'),
(150, 'Which fruit is round and red?', 'Apple', 'Banana', 'Orange', 'Grapes', 'A', 'BEGINNER'),
(151, 'What is 7 - 4?', '2', '3', '4', '5', 'B', 'BEGINNER'),
(152, 'Which animal says \"quack\"?', 'Duck', 'Dog', 'Cat', 'Cow', 'A', 'BEGINNER'),
(153, 'What is 9 + 0?', '8', '9', '10', '11', 'B', 'BEGINNER'),
(154, 'Which color is a mix of blue and yellow?', 'Green', 'Purple', 'Orange', 'Red', 'A', 'BEGINNER'),
(155, 'What is 5 x 3?', '15', '16', '14', '13', 'A', 'BEGINNER'),
(156, 'Which animal has a shell?', 'Turtle', 'Snail', 'Dog', 'Cat', 'A', 'BEGINNER'),
(157, 'What is 12 x 12?', '124', '144', '154', '134', 'B', 'INTERMEDIATE'),
(158, 'Which planet is known as the Red Planet?', 'Earth', 'Mars', 'Venus', 'Jupiter', 'B', 'INTERMEDIATE'),
(159, 'What is the square root of 144?', '10', '12', '14', '16', 'B', 'INTERMEDIATE'),
(160, 'Who wrote \"Romeo and Juliet\"?', 'Shakespeare', 'Dickens', 'Hemingway', 'Tolkien', 'A', 'INTERMEDIATE'),
(161, 'What is 15 + 27?', '42', '40', '41', '43', 'D', 'INTERMEDIATE'),
(162, 'Which gas do humans exhale?', 'Oxygen', 'Carbon Dioxide', 'Nitrogen', 'Hydrogen', 'B', 'INTERMEDIATE'),
(163, 'What is 9 x 9?', '81', '72', '91', '99', 'A', 'INTERMEDIATE'),
(164, 'Who discovered gravity?', 'Newton', 'Einstein', 'Galileo', 'Tesla', 'A', 'INTERMEDIATE'),
(165, 'Which element has symbol H?', 'Helium', 'Hydrogen', 'Hafnium', 'Holmium', 'B', 'INTERMEDIATE'),
(166, 'What is 100 ÷ 4?', '25', '20', '24', '26', 'A', 'INTERMEDIATE'),
(167, 'What is the chemical formula for water?', 'H2O', 'CO2', 'O2', 'HO2', 'A', 'INTERMEDIATE'),
(168, 'Which country is famous for the Eiffel Tower?', 'Italy', 'France', 'Spain', 'Germany', 'B', 'INTERMEDIATE'),
(169, 'What is 7²?', '42', '49', '56', '36', 'B', 'INTERMEDIATE'),
(170, 'Who painted the Mona Lisa?', 'Van Gogh', 'Da Vinci', 'Picasso', 'Rembrandt', 'B', 'INTERMEDIATE'),
(171, 'Which organ pumps blood?', 'Lungs', 'Heart', 'Kidney', 'Liver', 'B', 'INTERMEDIATE'),
(172, 'What is 64 ÷ 8?', '6', '7', '8', '9', 'C', 'INTERMEDIATE'),
(173, 'What is 13 + 26?', '39', '36', '40', '38', 'A', 'INTERMEDIATE'),
(174, 'Which gas is essential for humans to breathe?', 'Oxygen', 'Carbon Dioxide', 'Nitrogen', 'Hydrogen', 'A', 'INTERMEDIATE'),
(175, 'Who invented the telephone?', 'Bell', 'Edison', 'Tesla', 'Curie', 'A', 'INTERMEDIATE'),
(176, 'Which planet is the largest in the solar system?', 'Earth', 'Saturn', 'Jupiter', 'Mars', 'C', 'INTERMEDIATE'),
(177, 'What is 11 x 11?', '121', '111', '131', '112', 'A', 'INTERMEDIATE'),
(178, 'Which country is famous for the Great Wall?', 'China', 'Japan', 'India', 'Egypt', 'A', 'INTERMEDIATE'),
(179, 'Who wrote \"Hamlet\"?', 'Shakespeare', 'Hemingway', 'Tolkien', 'Dickens', 'A', 'INTERMEDIATE'),
(180, 'What is the square root of 225?', '12', '15', '20', '14', 'B', 'INTERMEDIATE'),
(181, 'Which organ is responsible for filtering blood?', 'Heart', 'Liver', 'Kidney', 'Lungs', 'C', 'INTERMEDIATE'),
(182, 'What is 18 + 29?', '47', '46', '45', '48', 'A', 'INTERMEDIATE'),
(183, 'Which planet is closest to the Sun?', 'Venus', 'Mercury', 'Earth', 'Mars', 'B', 'INTERMEDIATE'),
(184, 'What is 8³?', '512', '256', '128', '64', 'A', 'INTERMEDIATE'),
(185, 'Who discovered electricity?', 'Newton', 'Edison', 'Galileo', 'Tesla', 'D', 'INTERMEDIATE'),
(186, 'Which element has symbol O?', 'Oxygen', 'Gold', 'Osmium', 'Neon', 'A', 'INTERMEDIATE'),
(187, 'What is 81 ÷ 9?', '9', '8', '7', '10', 'A', 'INTERMEDIATE'),
(188, 'Which ocean is the largest?', 'Atlantic', 'Pacific', 'Indian', 'Arctic', 'B', 'INTERMEDIATE'),
(189, 'What is 14 x 6?', '84', '86', '80', '90', 'A', 'INTERMEDIATE'),
(190, 'Who is known as the father of computers?', 'Babbage', 'Tesla', 'Einstein', 'Newton', 'A', 'INTERMEDIATE'),
(191, 'Which planet is called the Morning Star?', 'Venus', 'Mars', 'Mercury', 'Jupiter', 'A', 'INTERMEDIATE'),
(192, 'What is the chemical formula for carbon dioxide?', 'CO', 'CO2', 'C2O', 'O2', 'B', 'INTERMEDIATE'),
(193, 'What is 27 + 36?', '63', '62', '64', '65', 'A', 'INTERMEDIATE'),
(194, 'Who painted the ceiling of the Sistine Chapel?', 'Michelangelo', 'Da Vinci', 'Picasso', 'Rembrandt', 'A', 'INTERMEDIATE'),
(195, 'What is 10²?', '100', '90', '110', '120', 'A', 'INTERMEDIATE'),
(196, 'Which organ controls the nervous system?', 'Heart', 'Brain', 'Kidney', 'Lungs', 'B', 'INTERMEDIATE'),
(197, 'What is 50 ÷ 5?', '10', '9', '11', '12', 'A', 'INTERMEDIATE'),
(198, 'Which gas do plants release during photosynthesis?', 'Oxygen', 'Carbon Dioxide', 'Nitrogen', 'Hydrogen', 'A', 'INTERMEDIATE'),
(199, 'What is 16 x 4?', '60', '64', '68', '72', 'B', 'INTERMEDIATE'),
(200, 'Who wrote \"The Odyssey\"?', 'Homer', 'Shakespeare', 'Tolkien', 'Dickens', 'A', 'INTERMEDIATE'),
(201, 'Which is a prime number?', '9', '11', '15', '21', 'B', 'INTERMEDIATE'),
(202, 'What is 18 ÷ 3?', '5', '6', '7', '8', 'B', 'INTERMEDIATE'),
(203, 'Which planet has rings?', 'Saturn', 'Mars', 'Earth', 'Jupiter', 'A', 'INTERMEDIATE'),
(204, 'What is 9²?', '81', '72', '90', '79', 'A', 'INTERMEDIATE'),
(205, 'Who discovered penicillin?', 'Fleming', 'Newton', 'Einstein', 'Pasteur', 'A', 'INTERMEDIATE'),
(206, 'What is 36 ÷ 6?', '5', '6', '7', '8', 'B', 'INTERMEDIATE'),
(207, 'Which is the longest river in the world?', 'Amazon', 'Nile', 'Yangtze', 'Mississippi', 'B', 'INTERMEDIATE'),
(208, 'What is 45 + 27?', '72', '70', '71', '73', 'D', 'INTERMEDIATE'),
(209, 'Which planet is known for its blue color?', 'Earth', 'Neptune', 'Uranus', 'Saturn', 'B', 'INTERMEDIATE'),
(210, 'What is 6³?', '216', '126', '256', '196', 'A', 'INTERMEDIATE'),
(211, 'Who invented the light bulb?', 'Edison', 'Tesla', 'Newton', 'Bell', 'A', 'INTERMEDIATE'),
(212, 'Which element has symbol Na?', 'Sodium', 'Nitrogen', 'Neon', 'Nickel', 'A', 'INTERMEDIATE'),
(213, 'What is 64 ÷ 8?', '6', '7', '8', '9', 'B', 'INTERMEDIATE'),
(214, 'What is 13 x 7?', '91', '92', '93', '90', 'A', 'INTERMEDIATE'),
(215, 'Who wrote \"Pride and Prejudice\"?', 'Austen', 'Shakespeare', 'Hemingway', 'Dickens', 'A', 'INTERMEDIATE'),
(216, 'What is 20 + 25?', '45', '44', '46', '43', 'A', 'INTERMEDIATE'),
(217, 'Which gas is most abundant in the air?', 'Oxygen', 'Nitrogen', 'Carbon Dioxide', 'Hydrogen', 'B', 'INTERMEDIATE'),
(218, 'What is the capital of Japan?', 'Beijing', 'Tokyo', 'Seoul', 'Bangkok', 'B', 'INTERMEDIATE'),
(219, 'What is 11²?', '111', '121', '131', '101', 'B', 'INTERMEDIATE'),
(220, 'Which organ produces insulin?', 'Kidney', 'Liver', 'Pancreas', 'Heart', 'C', 'INTERMEDIATE'),
(221, 'What is 8 x 12?', '96', '86', '106', '92', 'A', 'INTERMEDIATE'),
(222, 'Who is the author of \"1984\"?', 'Orwell', 'Hemingway', 'Shakespeare', 'Tolkien', 'A', 'INTERMEDIATE'),
(223, 'Which planet is second from the Sun?', 'Venus', 'Mercury', 'Earth', 'Mars', 'A', 'INTERMEDIATE'),
(224, 'What is 90 ÷ 9?', '9', '10', '11', '12', 'B', 'INTERMEDIATE'),
(225, 'Which is the largest mammal?', 'Elephant', 'Blue Whale', 'Giraffe', 'Hippopotamus', 'B', 'INTERMEDIATE'),
(226, 'What is 30 + 45?', '75', '70', '74', '76', 'A', 'INTERMEDIATE'),
(227, 'Who painted \"Starry Night\"?', 'Van Gogh', 'Da Vinci', 'Picasso', 'Michelangelo', 'A', 'INTERMEDIATE'),
(228, 'What is 14 ÷ 2?', '6', '7', '8', '9', 'B', 'INTERMEDIATE'),
(229, 'Which element has atomic number 1?', 'Hydrogen', 'Helium', 'Lithium', 'Oxygen', 'A', 'INTERMEDIATE'),
(230, 'What is 7³?', '343', '3431', '342', '346', 'A', 'INTERMEDIATE'),
(231, 'Who discovered America?', 'Columbus', 'Vespucci', 'Magellan', 'Cook', 'A', 'INTERMEDIATE'),
(232, 'Which is the largest desert?', 'Sahara', 'Gobi', 'Kalahari', 'Arabian', 'A', 'INTERMEDIATE'),
(233, 'What is 6²?', '30', '36', '32', '42', 'B', 'INTERMEDIATE'),
(234, 'What is 25 + 36?', '60', '61', '62', '63', 'C', 'INTERMEDIATE'),
(235, 'Which planet is called the Evening Star?', 'Venus', 'Mercury', 'Mars', 'Jupiter', 'A', 'INTERMEDIATE'),
(236, 'What is 18 x 2?', '36', '38', '34', '35', 'A', 'INTERMEDIATE'),
(237, 'Which organ stores bile?', 'Liver', 'Gallbladder', 'Pancreas', 'Kidney', 'B', 'INTERMEDIATE'),
(238, 'What is 81 ÷ 9?', '8', '9', '10', '11', 'B', 'INTERMEDIATE'),
(239, 'Who wrote \"Macbeth\"?', 'Shakespeare', 'Dickens', 'Orwell', 'Austen', 'A', 'INTERMEDIATE'),
(240, 'What is 15 x 3?', '45', '44', '46', '47', 'A', 'INTERMEDIATE'),
(241, 'Which planet is farthest from the Sun?', 'Neptune', 'Mars', 'Earth', 'Venus', 'A', 'INTERMEDIATE'),
(242, 'What is 22 + 33?', '55', '54', '56', '57', 'A', 'INTERMEDIATE'),
(243, 'Who discovered penicillin?', 'Fleming', 'Newton', 'Einstein', 'Pasteur', 'A', 'INTERMEDIATE'),
(244, 'Which gas do we breathe in most?', 'Oxygen', 'Nitrogen', 'CO2', 'Hydrogen', 'B', 'INTERMEDIATE'),
(245, 'What is 20 ÷ 4?', '5', '4', '6', '7', 'A', 'INTERMEDIATE'),
(246, 'Which is the smallest prime number?', '1', '2', '3', '5', 'B', 'INTERMEDIATE'),
(247, 'What is 9 x 8?', '72', '71', '70', '69', 'A', 'INTERMEDIATE'),
(248, 'Who wrote \"The Iliad\"?', 'Homer', 'Shakespeare', 'Orwell', 'Dickens', 'A', 'INTERMEDIATE'),
(249, 'What is 32 ÷ 8?', '3', '4', '5', '6', 'B', 'INTERMEDIATE'),
(250, 'What is the derivative of sin(x)?', 'cos(x)', '-cos(x)', 'sin(x)', '-sin(x)', 'A', 'ADVANCED'),
(251, 'Solve: 2x + 3 = 11', '3', '4', '5', '6', 'C', 'ADVANCED'),
(252, 'Which planet has the shortest day?', 'Earth', 'Jupiter', 'Saturn', 'Mars', 'B', 'ADVANCED'),
(253, 'What is the chemical symbol for lead?', 'Pb', 'Pd', 'Le', 'Ld', 'A', 'ADVANCED'),
(254, 'Who developed the theory of relativity?', 'Newton', 'Einstein', 'Galileo', 'Tesla', 'B', 'ADVANCED'),
(255, 'Integral of 1/x dx is?', 'ln(x)', '1/x', 'x', 'e^x', 'A', 'ADVANCED'),
(256, 'Which element has the highest melting point?', 'Tungsten', 'Iron', 'Carbon', 'Gold', 'A', 'ADVANCED'),
(257, 'Solve for x: x² - 9 = 0', '3', '-3', '3 or -3', '0', 'C', 'ADVANCED'),
(258, 'Who painted The Last Supper?', 'Michelangelo', 'Da Vinci', 'Picasso', 'Rembrandt', 'B', 'ADVANCED'),
(259, 'What is 2^10?', '1024', '1000', '512', '2048', 'A', 'ADVANCED'),
(260, 'Which gas is used in fluorescent lamps?', 'Neon', 'Argon', 'Krypton', 'Xenon', 'B', 'ADVANCED'),
(261, 'What is the capital of Iceland?', 'Reykjavik', 'Oslo', 'Helsinki', 'Stockholm', 'A', 'ADVANCED'),
(262, 'Solve: 3x - 7 = 2x + 5', '12', '13', '10', '11', 'D', 'ADVANCED'),
(263, 'Which is the largest moon of Saturn?', 'Titan', 'Europa', 'Ganymede', 'Callisto', 'A', 'ADVANCED'),
(264, 'Which programming language is used for Android?', 'Swift', 'Kotlin', 'Ruby', 'Python', 'B', 'ADVANCED'),
(265, 'Derivative of x^3?', '3x^2', 'x^2', 'x^3', '3x^3', 'A', 'ADVANCED'),
(266, 'Who formulated the laws of motion?', 'Einstein', 'Newton', 'Galileo', 'Tesla', 'B', 'ADVANCED'),
(267, 'What is the atomic number of Uranium?', '92', '94', '90', '88', 'A', 'ADVANCED'),
(268, 'Integral of e^x dx?', 'e^x + C', 'x*e^x', 'ln(x)', '1/x', 'A', 'ADVANCED'),
(269, 'What is 15% of 200?', '25', '30', '35', '40', 'B', 'ADVANCED'),
(270, 'Which planet has the strongest gravity?', 'Earth', 'Jupiter', 'Neptune', 'Saturn', 'B', 'ADVANCED'),
(271, 'Solve: 5x + 2 = 3x + 10', '3', '4', '5', '6', 'D', 'ADVANCED'),
(272, 'Who invented the first practical telephone?', 'Edison', 'Bell', 'Tesla', 'Faraday', 'B', 'ADVANCED'),
(273, 'Derivative of ln(x)?', '1/x', 'ln(x)', 'x', 'x^2', 'A', 'ADVANCED'),
(274, 'What is the chemical formula of table salt?', 'NaCl', 'KCl', 'Na2SO4', 'CaCl2', 'A', 'ADVANCED'),
(275, 'Which is the heaviest naturally occurring element?', 'Uranium', 'Plutonium', 'Gold', 'Osmium', 'A', 'ADVANCED'),
(276, 'Solve: x^2 - 16 = 0', '4', '-4', '4 or -4', '0', 'C', 'ADVANCED'),
(277, 'Who wrote \"War and Peace\"?', 'Tolstoy', 'Dostoevsky', 'Hemingway', 'Orwell', 'A', 'ADVANCED'),
(278, 'What is 3^5?', '243', '125', '216', '512', 'A', 'ADVANCED'),
(279, 'Which is the smallest prime number greater than 50?', '53', '51', '57', '59', 'A', 'ADVANCED'),
(280, 'Integral of cos(x) dx?', 'sin(x) + C', 'cos(x) + C', '-sin(x)', '-cos(x)', 'A', 'ADVANCED'),
(281, 'Which is the farthest planet from the Sun?', 'Neptune', 'Pluto', 'Mars', 'Earth', 'A', 'ADVANCED'),
(282, 'Derivative of e^(2x)?', '2*e^(2x)', 'e^(2x)', 'e^x', '2e^x', 'A', 'ADVANCED'),
(283, 'Which organ produces bile?', 'Liver', 'Gallbladder', 'Pancreas', 'Kidney', 'A', 'ADVANCED'),
(284, 'Solve: 2x + 3y = 12, x = 3', 'y=1.5', 'y=2', 'y=3', 'y=4', 'A', 'ADVANCED'),
(285, 'Which element has symbol Fe?', 'Fluorine', 'Iron', 'Fermium', 'Francium', 'B', 'ADVANCED'),
(286, 'What is 7^4?', '2401', '2048', '2499', '2560', 'A', 'ADVANCED'),
(287, 'Who formulated the periodic table?', 'Mendeleev', 'Dalton', 'Curie', 'Einstein', 'A', 'ADVANCED'),
(288, 'Which is the largest desert on Earth?', 'Sahara', 'Arabian', 'Gobi', 'Kalahari', 'A', 'ADVANCED'),
(289, 'Integral of 1/(1+x^2)?', 'arctan(x) + C', 'ln(x)', 'x^2/2', '1/x', 'A', 'ADVANCED'),
(290, 'Solve: 5x - 7 = 3x + 5', '5', '6', '7', '8', 'C', 'ADVANCED'),
(291, 'Which planet is known as the Morning Star?', 'Venus', 'Mars', 'Mercury', 'Jupiter', 'A', 'ADVANCED'),
(292, 'Derivative of x^4?', '4x^3', 'x^3', '4x^4', 'x^4', 'A', 'ADVANCED'),
(293, 'What is the atomic number of Gold?', '79', '78', '80', '81', 'A', 'ADVANCED'),
(294, 'Which is the largest ocean?', 'Pacific', 'Atlantic', 'Indian', 'Arctic', 'A', 'ADVANCED'),
(295, 'Integral of sin(2x) dx?', '-cos(2x)/2 + C', 'cos(2x)/2 + C', 'sin(2x)/2 + C', '-sin(2x)/2 + C', 'A', 'ADVANCED'),
(296, 'Solve: x^2 + 6x + 9 = 0', 'x=-3', 'x=3', 'x=0', 'x=9', 'A', 'ADVANCED'),
(297, 'Which is the hottest planet in the solar system?', 'Mercury', 'Venus', 'Mars', 'Jupiter', 'B', 'ADVANCED'),
(298, 'Derivative of ln(2x)?', '1/x', '1/(2x)', '2/x', 'ln(2x)', 'B', 'ADVANCED'),
(299, 'Which organ controls endocrine functions?', 'Brain', 'Pituitary', 'Liver', 'Heart', 'B', 'ADVANCED'),
(300, 'Solve: 3x + 4 = 19', '4', '5', '6', '7', 'C', 'ADVANCED'),
(301, 'Which element is liquid at room temperature?', 'Mercury', 'Bromine', 'Lead', 'Iron', 'A', 'ADVANCED'),
(302, 'What is 2^8?', '256', '128', '512', '1024', 'A', 'ADVANCED'),
(303, 'Derivative of tan(x)?', 'sec^2(x)', 'tan^2(x)', 'cos^2(x)', '1/cos^2(x)', 'A', 'ADVANCED'),
(304, 'Integral of x dx?', 'x^2/2 + C', 'x + C', '2x + C', 'x^2 + C', 'A', 'ADVANCED'),
(305, 'Which is the heaviest noble gas?', 'Xenon', 'Radon', 'Neon', 'Argon', 'B', 'ADVANCED'),
(306, 'Solve: x^2 - 4x + 3 = 0', 'x=1 or 3', 'x=2', 'x=0 or 3', 'x=1', 'A', 'ADVANCED'),
(307, 'Who discovered the electron?', 'Thomson', 'Einstein', 'Curie', 'Newton', 'A', 'ADVANCED'),
(308, 'What is 3^6?', '729', '7290', '730', '728', 'A', 'ADVANCED'),
(309, 'Derivative of cos(3x)?', '-3sin(3x)', '3sin(3x)', 'cos(3x)', '3cos(3x)', 'A', 'ADVANCED'),
(310, 'Which organ is the largest in the human body?', 'Liver', 'Skin', 'Heart', 'Lungs', 'B', 'ADVANCED'),
(311, 'Integral of e^(3x) dx?', 'e^(3x)/3 + C', '3e^(3x) + C', 'e^(x) + C', '3e^x + C', 'A', 'ADVANCED'),
(312, 'Solve: 5x - 2 = 18', '4', '5', '6', '3', 'C', 'ADVANCED'),
(313, 'Which planet has the most moons?', 'Saturn', 'Jupiter', 'Earth', 'Mars', 'A', 'ADVANCED'),
(314, 'What is 4^5?', '1024', '1020', '1025', '1024', 'D', 'ADVANCED'),
(315, 'Derivative of x*sin(x)?', 'sin(x)+x*cos(x)', 'cos(x)', 'x*sin(x)', 'sin(x)', 'A', 'ADVANCED'),
(316, 'Integral of 1/(x^2 + 1)?', 'arctan(x)+C', 'ln(x)+C', '1/x + C', 'x + C', 'A', 'ADVANCED'),
(317, 'Solve: x^2 - 5x + 6 = 0', 'x=2 or 3', 'x=1 or 6', 'x=3 or 4', 'x=2 or 4', 'A', 'ADVANCED'),
(318, 'Which is the largest internal organ?', 'Liver', 'Lungs', 'Kidney', 'Heart', 'A', 'ADVANCED'),
(319, 'Who developed quantum theory?', 'Planck', 'Einstein', 'Newton', 'Tesla', 'A', 'ADVANCED'),
(320, 'What is 5^3?', '125', '120', '150', '100', 'A', 'ADVANCED'),
(321, 'Integral of cosh(x) dx?', 'sinh(x) + C', 'cosh(x) + C', 'tanh(x) + C', '1/cosh(x) + C', 'A', 'ADVANCED'),
(322, 'Derivative of ln(x^2)?', '2/x', '1/x', 'x^2', 'x', 'A', 'ADVANCED'),
(323, 'Solve: 7x - 14 = 0', 'x=2', 'x=1', 'x=0', 'x=3', 'A', 'ADVANCED'),
(324, 'Which element is radioactive?', 'Uranium', 'Gold', 'Lead', 'Silver', 'A', 'ADVANCED'),
(325, 'What is 6^4?', '1296', '1295', '1297', '1296', 'D', 'ADVANCED'),
(326, 'Derivative of e^(x^2)?', '2x*e^(x^2)', 'e^(x^2)', 'x*e^(x^2)', 'e^x', 'A', 'ADVANCED'),
(327, 'Integral of 1/x^2 dx?', '-1/x + C', '1/x + C', 'x + C', 'ln(x) + C', 'A', 'ADVANCED'),
(328, 'Solve: x^2 + x - 6 = 0', 'x=2 or -3', 'x=3 or -2', 'x=-2 or 3', 'x=1 or -6', 'A', 'ADVANCED'),
(329, 'Which organ is part of the central nervous system?', 'Heart', 'Brain', 'Kidney', 'Lungs', 'B', 'ADVANCED'),
(330, 'Who discovered the neutron?', 'Chadwick', 'Einstein', 'Newton', 'Curie', 'A', 'ADVANCED'),
(331, 'Who is the king of Panenka ?', 'Zidane', 'Pirlo', 'Brahim Diaz', 'Antonin Panenka', 'C', 'INTERMEDIATE');

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE `results` (
  `result_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `quiz_number` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `level` enum('BEGINNER','INTERMEDIATE','ADVANCED') NOT NULL,
  `quiz_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `results`
--

INSERT INTO `results` (`result_id`, `player_id`, `quiz_number`, `score`, `level`, `quiz_date`) VALUES
(18, 7, 1, 2, 'BEGINNER', '2026-01-25 10:18:30'),
(19, 7, 2, 2, 'BEGINNER', '2026-01-25 10:18:54'),
(20, 7, 3, 2, 'BEGINNER', '2026-01-25 10:19:16'),
(21, 7, 4, 3, 'BEGINNER', '2026-01-25 10:19:32'),
(22, 7, 5, 2, 'BEGINNER', '2026-01-25 10:19:50'),
(23, 8, 1, 5, 'ADVANCED', '2026-01-25 16:40:04'),
(24, 8, 2, 4, 'ADVANCED', '2026-01-25 16:41:04'),
(25, 8, 3, 3, 'ADVANCED', '2026-01-25 16:41:30'),
(26, 8, 4, 0, 'ADVANCED', '2026-01-25 16:41:45'),
(27, 8, 5, 3, 'ADVANCED', '2026-01-25 16:42:08'),
(28, 9, 1, 3, 'INTERMEDIATE', '2026-01-25 16:43:53'),
(29, 9, 2, 3, 'INTERMEDIATE', '2026-01-25 16:44:27'),
(30, 9, 3, 4, 'INTERMEDIATE', '2026-01-25 16:44:53'),
(31, 9, 4, 3, 'INTERMEDIATE', '2026-01-25 16:45:17'),
(32, 9, 5, 4, 'INTERMEDIATE', '2026-01-25 16:45:37'),
(38, 8, 1, 0, 'ADVANCED', '2026-02-10 14:31:27'),
(39, 8, 2, 2, 'ADVANCED', '2026-02-10 14:31:54'),
(40, 8, 3, 3, 'ADVANCED', '2026-02-10 14:32:13'),
(41, 8, 4, 3, 'ADVANCED', '2026-02-10 14:32:31'),
(42, 8, 5, 4, 'ADVANCED', '2026-02-10 14:32:50'),
(43, 8, 1, 0, 'ADVANCED', '2026-02-10 14:33:55'),
(44, 8, 2, 0, 'ADVANCED', '2026-02-10 14:33:59'),
(45, 8, 3, 0, 'ADVANCED', '2026-02-10 14:34:02'),
(46, 8, 4, 0, 'ADVANCED', '2026-02-10 14:34:05'),
(47, 8, 5, 0, 'ADVANCED', '2026-02-10 14:34:08'),
(48, 8, 1, 2, 'ADVANCED', '2026-02-10 15:00:06'),
(49, 8, 2, 3, 'ADVANCED', '2026-02-10 15:00:22'),
(50, 8, 3, 2, 'ADVANCED', '2026-02-10 15:00:35'),
(51, 8, 4, 0, 'ADVANCED', '2026-02-10 15:00:39'),
(52, 8, 5, 1, 'ADVANCED', '2026-02-10 15:00:52'),
(74, 10, 1, 5, 'ADVANCED', '2026-02-10 17:05:16'),
(75, 10, 2, 5, 'ADVANCED', '2026-02-10 17:05:16'),
(76, 10, 3, 5, 'ADVANCED', '2026-02-10 17:05:16'),
(77, 10, 4, 5, 'ADVANCED', '2026-02-10 17:05:16'),
(78, 10, 5, 5, 'ADVANCED', '2026-02-10 17:05:16'),
(80, 6, 1, 5, 'BEGINNER', '2026-02-10 18:15:32'),
(81, 6, 2, 4, 'BEGINNER', '2026-02-10 18:15:32'),
(82, 6, 3, 5, 'BEGINNER', '2026-02-10 18:15:32'),
(83, 6, 4, 3, 'BEGINNER', '2026-02-10 18:15:32'),
(84, 6, 5, 4, 'BEGINNER', '2026-02-10 18:15:32'),
(85, 6, 1, 5, 'BEGINNER', '2026-02-10 18:19:17'),
(86, 6, 2, 5, 'BEGINNER', '2026-02-10 18:19:17'),
(87, 6, 3, 5, 'BEGINNER', '2026-02-10 18:19:17'),
(88, 6, 4, 5, 'BEGINNER', '2026-02-10 18:19:17'),
(89, 6, 5, 5, 'BEGINNER', '2026-02-10 18:19:17'),
(90, 9, 1, 5, 'INTERMEDIATE', '2026-02-10 18:21:34'),
(91, 9, 2, 5, 'INTERMEDIATE', '2026-02-10 18:21:34'),
(92, 9, 3, 5, 'INTERMEDIATE', '2026-02-10 18:21:34'),
(93, 9, 4, 5, 'INTERMEDIATE', '2026-02-10 18:21:34'),
(94, 9, 5, 5, 'INTERMEDIATE', '2026-02-10 18:21:34'),
(95, 9, 1, 4, 'INTERMEDIATE', '2026-02-10 18:37:15'),
(96, 9, 2, 4, 'INTERMEDIATE', '2026-02-10 18:37:15'),
(97, 9, 3, 4, 'INTERMEDIATE', '2026-02-10 18:37:15'),
(98, 9, 4, 4, 'INTERMEDIATE', '2026-02-10 18:37:15'),
(99, 9, 5, 4, 'INTERMEDIATE', '2026-02-10 18:37:15'),
(100, 10, 1, 4, 'ADVANCED', '2026-02-10 18:39:48'),
(101, 10, 2, 4, 'ADVANCED', '2026-02-10 18:39:48'),
(102, 10, 3, 4, 'ADVANCED', '2026-02-10 18:39:48'),
(103, 10, 4, 3, 'ADVANCED', '2026-02-10 18:39:48'),
(104, 10, 5, 3, 'ADVANCED', '2026-02-10 18:39:48'),
(105, 6, 1, 2, 'BEGINNER', '2026-02-10 18:51:03'),
(106, 6, 2, 3, 'BEGINNER', '2026-02-10 18:51:22'),
(107, 6, 3, 1, 'BEGINNER', '2026-02-10 18:51:32'),
(108, 6, 4, 1, 'BEGINNER', '2026-02-10 18:51:41'),
(109, 6, 5, 3, 'BEGINNER', '2026-02-10 18:52:03'),
(110, 6, 1, 0, 'BEGINNER', '2026-02-10 18:54:32'),
(111, 6, 2, 0, 'BEGINNER', '2026-02-10 18:54:36'),
(112, 6, 3, 1, 'BEGINNER', '2026-02-10 18:54:45'),
(113, 6, 4, 0, 'BEGINNER', '2026-02-10 18:54:49'),
(114, 6, 5, 0, 'BEGINNER', '2026-02-10 18:54:52'),
(115, 6, 1, 3, 'BEGINNER', '2026-02-10 18:55:31'),
(116, 6, 2, 2, 'BEGINNER', '2026-02-10 18:55:51'),
(117, 6, 3, 2, 'BEGINNER', '2026-02-10 18:56:12'),
(118, 6, 4, 3, 'BEGINNER', '2026-02-10 18:56:30'),
(119, 6, 5, 3, 'BEGINNER', '2026-02-10 18:56:47'),
(120, 6, 1, 4, 'BEGINNER', '2026-02-10 19:06:18'),
(121, 6, 2, 4, 'BEGINNER', '2026-02-10 19:06:39'),
(122, 6, 3, 5, 'BEGINNER', '2026-02-10 19:06:55'),
(123, 6, 4, 5, 'BEGINNER', '2026-02-10 19:07:21'),
(124, 6, 5, 5, 'BEGINNER', '2026-02-10 19:07:40'),
(125, 8, 1, 1, 'ADVANCED', '2026-02-10 19:08:36'),
(126, 8, 2, 1, 'ADVANCED', '2026-02-10 19:08:49'),
(127, 8, 3, 3, 'ADVANCED', '2026-02-10 19:09:11'),
(128, 8, 4, 1, 'ADVANCED', '2026-02-10 19:09:32'),
(129, 8, 5, 3, 'ADVANCED', '2026-02-10 19:09:53'),
(130, 7, 1, 4, 'BEGINNER', '2026-02-10 19:11:22'),
(131, 7, 2, 5, 'BEGINNER', '2026-02-10 19:11:41'),
(132, 7, 3, 4, 'BEGINNER', '2026-02-10 19:11:56'),
(133, 7, 4, 4, 'BEGINNER', '2026-02-10 19:12:14'),
(134, 7, 5, 5, 'BEGINNER', '2026-02-10 19:12:29'),
(135, 13, 1, 3, 'INTERMEDIATE', '2026-02-11 06:38:41'),
(136, 13, 2, 4, 'INTERMEDIATE', '2026-02-11 06:39:21'),
(137, 13, 3, 4, 'INTERMEDIATE', '2026-02-11 06:39:42'),
(138, 13, 4, 2, 'INTERMEDIATE', '2026-02-11 06:39:58'),
(139, 13, 5, 2, 'INTERMEDIATE', '2026-02-11 06:40:08'),
(140, 9, 1, 0, 'INTERMEDIATE', '2026-02-11 07:52:28'),
(141, 9, 2, 4, 'INTERMEDIATE', '2026-02-11 07:52:46'),
(142, 9, 3, 4, 'INTERMEDIATE', '2026-02-11 07:53:06'),
(143, 9, 4, 3, 'INTERMEDIATE', '2026-02-11 07:53:26'),
(144, 9, 5, 5, 'INTERMEDIATE', '2026-02-11 07:53:44');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`player_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`question_id`);

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`result_id`),
  ADD KEY `player_id` (`player_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `players`
--
ALTER TABLE `players`
  MODIFY `player_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=334;

--
-- AUTO_INCREMENT for table `results`
--
ALTER TABLE `results`
  MODIFY `result_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
