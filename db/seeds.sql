use fms;

truncate files;
insert into files values(0, "IMG_1012.JPG", "/path/to/IMG_1012.JPG", 4989, "image/jpeg", 0, 2, "VISIBLE", now(), "1,2");
insert into files values(1, "IMG_1119.JPG", "/path/to/IMG_1119.JPG", 4416, "image/jpeg", 0, 1, "VISIBLE", now(), "1");
insert into files values(2, "IMG_1012.JPG", "/path/to/IMG_1012.JPG", 745, "image/jpeg", 0, 2, "VISIBLE", now(), "1,2");

truncate settings;
insert into settings values(0, 20, 5, "Image",'2022-01-01 00:00:01');
-- insert into settings values(1, 30, 4, "Image",2013-07-04 05:05:10);
-- insert into settings values(2, 40, 3, "Image",2013-07-04 05:05:10);
-- insert into settings values(3, 50, 2, "Image",2013-07-04 05:05:10);
