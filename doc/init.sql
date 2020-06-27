
#密码 123456
INSERT INTO `user` (`userId`,`username`,`name`,`password`,`salt`,`state`)
VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 1);

INSERT INTO `permission` (`permissionId`,`available`,`permissionname`,`parentid`,`parentids`,`permission`,`resourcetype`,`url`)
VALUES (1,1,'用户管理',0,'0/','user:view','menu','user/userList');
INSERT INTO `permission` (`permissionId`,`available`,`permissionname`,`parentid`,`parentids`,`permission`,`resourcetype`,`url`)
VALUES (2,1,'用户添加',1,'0/1','user:add','button','user/userAdd');
INSERT INTO `permission` (`permissionId`,`available`,`permissionname`,`parentid`,`parentids`,`permission`,`resourcetype`,`url`)
VALUES (3,1,'用户删除',1,'0/1','user:del','button','user/userDel');

INSERT INTO `role` (`roleid`,`available`,`description`,`role`) VALUES (1,1,'管理员','admin');

INSERT INTO `rolepermission` (`permissionid`,`roleid`) VALUES (1,1);
INSERT INTO `rolepermission` (`permissionid`,`roleid`) VALUES (2,1);

INSERT INTO `userrole` (`roleid`,`userId`) VALUES (1,1);