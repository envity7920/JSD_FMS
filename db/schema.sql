drop database if exists fms;

create database fms;
use fms;

create table files (
  id bigint primary key not null auto_increment,
  name varchar(100) not null,
  path varchar(100) not null,
  fileSize int not null,
  mime varchar(50) not null,
  numberOfDownload int not null default(0),
  version int not null default(1),
  status varchar(20) not null default("VISIBLE"),
  createdDateTime timestamp not null default(now()),
  versionIds varchar(500)
);

create table settings (
  id bigint primary key not null auto_increment,
  maxFileSize int not null default(20),
  itemPerPage int not null default(5),
  mimeTypeAllowed varchar(20) not null,
  lastUpdatedTime timestamp not null default(now())
);
