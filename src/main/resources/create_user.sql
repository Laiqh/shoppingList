alter session set "_ORACLE_SCRIPT"=true;

create user shopping_list identified by "password";

GRANT CONNECT, RESOURCE TO shopping_list;

GRANT CREATE SESSION TO shopping_list;

GRANT UNLIMITED TABLESPACE TO shopping_list;

grant create view, create procedure, create sequence to shopping_list;

alter user shoppiing_list quota unlimited on users;