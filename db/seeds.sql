use fms;

truncate files;
insert into files values(0, "12886.jpg", "public/v1_12886.jpg", 117564, "image/jpeg", 0, 1, "VISIBLE", now(), "1,2");
insert into files values(1, "12886.jpg", "public/v2_12886.jpg", 117564, "image/jpeg", 0, 2, "VISIBLE", now(), "1,2");
insert into files values(2, "1083535.png", "public/v1_1083535.png", 372853, "image/png", 0, 1, "VISIBLE", now(), "1");

truncate settings;
insert into settings values(0, 20, 500000000, "all", now());
