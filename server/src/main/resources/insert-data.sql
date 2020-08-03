
INSERT INTO `ebuy`.`role`(`id`,`name`)VALUES(1,'Admin'),(2,'Vendor'),(3,'EndUser');
INSERT INTO `ebuy`.`cardtype`(`id`,`name`)VALUES(1,'Visa'),(2,'MasterCard'),(3,'AMEX');


INSERT INTO `ebuy`.`user`
(`ImageUrl`,`address`,`email`,`isActive`,`name`,`password`,`phone`,`roleId`)
VALUES
('ImageUrl','addreass','admin1.gmail.com',1,'admin1','123','0215487877',1),
('ImageUrl','addreass','admin2.gmail.com',1,'admin2','123','0215487877',1),
('ImageUrl','addreass','admin3.gmail.com',1,'admin3','123','0215487877',1),
('ImageUrl','addreass','Vendor1.gmail.com',1,'Vendor1','123','0215487877',2),
('ImageUrl','addreass','Vendor2.gmail.com',1,'Vendor2','123','0215487877',2),
('ImageUrl','addreass','Vendor3.gmail.com',1,'Vendor3','123','0215487877',2),
('ImageUrl','addreass','Vendor4.gmail.com',1,'Vendor4','123','0215487877',2),
('ImageUrl','addreass','Vendor5.gmail.com',1,'Vendor5','123','0215487877',2),
('ImageUrl','addreass','use1.gmail.com',1,'use1','123','0215487877',3),
('ImageUrl','addreass','use2.gmail.com',1,'use2','123','0215487877',3),
('ImageUrl','addreass','use3.gmail.com',1,'use3','123','0215487877',3),
('ImageUrl','addreass','use4.gmail.com',1,'use4','123','0215487877',3),
('ImageUrl','addreass','use5.gmail.com',1,'use5','123','0215487877',3),
('ImageUrl','addreass','use6.gmail.com',1,'use6','123','0215487877',3),
('ImageUrl','addreass','use7.gmail.com',1,'use7','123','0215487877',3),
('ImageUrl','addreass','use8.gmail.com',1,'use8','123','0215487877',3),
('ImageUrl','addreass','use9.gmail.com',1,'use9','123','0215487877',3),
('ImageUrl','addreass','use10.gmail.com',1,'use10','123','0215487877',3);




INSERT INTO `ebuy`.`productstatus`
(`id`,`name`)VALUES(1,'Pending'),(2,'Approved'),(3,'Rejected');


INSERT INTO `ebuy`.`category`
(`id`,`name`)VALUES(1,'category 1'),(2,'category 2'),(3,'category 3');



INSERT INTO `ebuy`.`product`
(`cost`,`description`,`imageUrl`,`isPublished`,`isService`,`name`,`price`,`categoryId`,`statusId`)
VALUES
(10.0,'product 1 description','imageUrl',1,0,'product 1',50.0,1,2),
(10.0,'product 2 description','imageUrl',1,0,'product 2',50.0,1,2),
(10.0,'product 3 description','imageUrl',1,0,'product 3',50.0,1,2),
(10.0,'product 4 description','imageUrl',1,0,'product 4',50.0,1,2),
(10.0,'product 5 description','imageUrl',1,0,'product 5',50.0,1,2),
(10.0,'product 6 description','imageUrl',1,0,'product 6',50.0,1,2),
(10.0,'product 7 description','imageUrl',1,0,'product 7',50.0,1,2),
(10.0,'product 8 description','imageUrl',1,0,'product 8',50.0,1,2),
(10.0,'product 9 description','imageUrl',1,0,'product 9',50.0,1,2),
(10.0,'product 10 description','imageUrl',1,0,'product 10',50.0,1,2),

(150.0,'product 10 description','imageUrl',1,0,'product 10',180,2,2),
(150.0,'product 11 description','imageUrl',1,0,'product 11',180,2,2),
(150.0,'product 12 description','imageUrl',1,0,'product 12',180,2,2),
(150.0,'product 13 description','imageUrl',1,0,'product 13',180,2,2),
(150.0,'product 14 description','imageUrl',1,0,'product 14',180,2,2),
(150.0,'product 15 description','imageUrl',1,0,'product 15',180,2,2),
(150.0,'product 16 description','imageUrl',1,0,'product 16',180,2,2),
(150.0,'product 17 description','imageUrl',1,0,'product 17',180,2,2),
(150.0,'product 18 description','imageUrl',1,0,'product 18',180,2,2),
(150.0,'product 19 description','imageUrl',1,0,'product 19',180,2,2),
(150.0,'product 20 description','imageUrl',1,0,'product 20',180,2,2),

(200,'product 20 description','imageUrl',1,0,'product 20',200,3,2),
(200,'product 21 description','imageUrl',1,0,'product 21',200,3,2),
(200,'product 22 description','imageUrl',1,0,'product 22',200,3,2),
(200,'product 23 description','imageUrl',1,0,'product 23',200,3,2),
(200,'product 24 description','imageUrl',1,0,'product 24',200,3,2),
(200,'product 25 description','imageUrl',1,0,'product 25',200,3,2),
(200,'product 26 description','imageUrl',1,0,'product 26',200,3,2),
(200,'product 27 description','imageUrl',1,0,'product 27',200,3,2),
(200,'product 28 description','imageUrl',1,0,'product 28',200,3,2),
(200,'product 29 description','imageUrl',1,0,'product 29',200,3,2),
(200,'product 30 description','imageUrl',1,0,'product 30',200,3,2),


(300,'product 40 description','imageUrl',1,0,'product 40',370,4,2),
(300,'product 41 description','imageUrl',1,0,'product 41',370,4,2),
(300,'product 42 description','imageUrl',1,0,'product 42',370,4,2),
(300,'product 43 description','imageUrl',1,0,'product 43',370,4,2),
(300,'product 44 description','imageUrl',1,0,'product 44',370,4,2),
(300,'product 45 description','imageUrl',1,0,'product 45',370,4,2),
(300,'product 46 description','imageUrl',1,0,'product 46',370,4,2),
(300,'product 47 description','imageUrl',1,0,'product 47',370,4,2),
(300,'product 48 description','imageUrl',1,0,'product 48',370,4,2),
(300,'product 49 description','imageUrl',1,0,'product 49',370,4,2),
(300,'product 50 description','imageUrl',1,0,'product 50',370,4,2);