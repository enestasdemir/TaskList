/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100129
 Source Host           : localhost:3306
 Source Schema         : task_list

 Target Server Type    : MySQL
 Target Server Version : 100129
 File Encoding         : 65001

 Date: 10/04/2018 19:42:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tasks
-- ----------------------------
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks`  (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_user_id` int(11) NULL DEFAULT NULL,
  `task_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  `task_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  `task_start_date` date NULL DEFAULT NULL,
  `task_due_date` date NULL DEFAULT NULL,
  `task_status` int(1) NULL DEFAULT NULL COMMENT 'Active 0, Postponed 1, Canceled 2, Finished 3',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_turkish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tasks
-- ----------------------------
INSERT INTO `tasks` VALUES (1, 1, 'My first task', 'My first task description', '2018-04-03', '2018-04-10', 3);
INSERT INTO `tasks` VALUES (2, 1, 'Task Two', 'Task 2 Description', '2018-04-04', '2018-04-20', 1);
INSERT INTO `tasks` VALUES (3, 1, 'New Dat', 'New Task', '2018-04-05', '2018-04-20', 0);
INSERT INTO `tasks` VALUES (4, 1, 'Test', 'Task desc', '2018-04-05', '2018-04-27', 2);
INSERT INTO `tasks` VALUES (5, 1, 'Test Task', 'Test description', '2018-04-06', '2018-05-25', 0);
INSERT INTO `tasks` VALUES (7, 2, 'New Task', 'Today\'s Task', '2018-01-06', '2018-01-06', 2);
INSERT INTO `tasks` VALUES (10, 1, 'My first task', 'My first task description', '2018-04-03', '2018-04-10', 1);
INSERT INTO `tasks` VALUES (11, 3, 'Quest', 'New quest', '2018-04-07', '2018-04-07', 0);
INSERT INTO `tasks` VALUES (12, 11, 'Buy fruits', '2 kg', '2018-04-07', '2018-04-22', 0);
INSERT INTO `tasks` VALUES (13, 2, 'New Task', 'Hello', '2018-04-07', '2018-04-29', 0);
INSERT INTO `tasks` VALUES (14, 2, 'Postponed', 'Post 1', '2018-04-07', '2018-04-14', 1);
INSERT INTO `tasks` VALUES (15, 2, 'Finished', 'Successful', '2018-04-02', '2018-04-06', 3);
INSERT INTO `tasks` VALUES (16, 2, 'Finished Task', 'Successful', '2018-04-01', '2018-04-08', 3);
INSERT INTO `tasks` VALUES (17, 2, 'Postponed Task', 'Post', '2018-04-01', '2018-04-08', 1);
INSERT INTO `tasks` VALUES (18, 2, 'Canceled Task', 'Successful', '2018-04-01', '2018-04-08', 2);
INSERT INTO `tasks` VALUES (19, 2, 'Active Task', 'Successful', '2018-04-01', '2018-04-08', 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  `user_surname` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  `user_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  `user_role` int(1) NULL DEFAULT NULL COMMENT 'Admin 1, User 0',
  `user_register_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_mail`(`user_mail`) USING BTREE COMMENT 'Login unique email'
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_turkish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'Enes', 'Taşdemir', 'admin@mail.com', '12345', 1, '2018-04-03');
INSERT INTO `users` VALUES (2, 'Muharrem', 'Didici', 'user@mail.com', '12345', 0, '2018-04-04');
INSERT INTO `users` VALUES (3, 'Ahmet', 'Taşdemir', 'ahmet_tasdemir@mail.com', '12345', 0, '2018-04-04');
INSERT INTO `users` VALUES (4, 'Ali', 'Fazıl', 'ali_fazil@mail.com', '12345', 0, '2018-04-05');
INSERT INTO `users` VALUES (5, 'Yeşim', 'Tuğçe', 'yesim_tugce@mail.com', '12345', 0, '2018-04-05');
INSERT INTO `users` VALUES (6, 'Burçin', 'Şeker', 'burcin_seker@mail.com', '12345', 0, '2018-04-05');
INSERT INTO `users` VALUES (7, 'Buğra', 'Arkın', 'bugra_arkin@mail.com', '12345', 0, '2018-04-05');
INSERT INTO `users` VALUES (8, 'Hüssam', 'Durak', 'hussam_durak@mail.com', '12345', 0, '2018-04-05');
INSERT INTO `users` VALUES (9, 'Çetin', 'Meral', 'cetin_meral@mail.com', '12345', 0, '2018-04-05');
INSERT INTO `users` VALUES (10, 'Emre', 'Göktuğ', 'emre_goktug@mail.com', '12345', 0, '2018-04-05');
INSERT INTO `users` VALUES (11, 'Ülkü', 'Beşiktaş', 'ulku_besiktas@mail.com', '12345', 0, '2018-04-07');
INSERT INTO `users` VALUES (12, 'Kemal', 'Işık', 'kemal_isik@mail.com', '12345', 0, '2018-04-07');
INSERT INTO `users` VALUES (14, 'Özge', 'Çakır', 'ozge_cakir@mail.com', '12345', 0, '2018-04-07');

SET FOREIGN_KEY_CHECKS = 1;
