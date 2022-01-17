show databases;

-- db 생성
create database webdb;

-- user 생성 
-- localhost에서 작동하는 webdb
create user 'webdb'@'localhost' identified by 'webdb';

-- 권한 부여
-- 모든 권한을 webdb에 있는 모든 것에 
grant all privileges on webdb.* to 'webdb'@'localhost';