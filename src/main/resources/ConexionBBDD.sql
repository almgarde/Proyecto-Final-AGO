alter session set "_ORACLE_SCRIPT"=TRUE;

create user PF_AGO IDENTIFIED by PF_AGO
default tablespace users
temporary tablespace temp
quota unlimited on users;

grant create session to PF_AGO;
grant create table to PF_AGO;
grant create sequence to PF_AGO;

