DROP DATABASE IF EXISTS jparestresource;
create database jparestresource;
CREATE USER jparestresource WITH PASSWORD 'jparestresource';
GRANT ALL PRIVILEGES ON DATABASE jparestresource to jparestresource;
