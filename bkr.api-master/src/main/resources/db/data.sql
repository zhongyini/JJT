-- 清空记录
delete  from `permission`;
delete  from `user`;
delete  from `role`;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '管理员');
INSERT INTO `role` VALUES ('2', '前台用户', '前台用户');
INSERT INTO `role` VALUES ('3', '后台用户', '后台用户');

-- ----------------------------
-- Records of user
-- ----------------------------

INSERT INTO `user` VALUES ('1', 'user1@xxx.com', 'user1', '202CB962AC59075B964B07152D234B70', '1.png', '1');
INSERT INTO `user` VALUES ('2', 'user2@xxx.com', 'user2', '202CB962AC59075B964B07152D234B70', '2.png', '2');
-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '管理员权限', '1');
INSERT INTO `permission` VALUES ('2', '超级管理员权限', '1');
