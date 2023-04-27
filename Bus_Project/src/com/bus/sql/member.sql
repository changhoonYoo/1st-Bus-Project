-- 버스 회원 가입
create table B_mem(
  b_id varchar2(50) primary key --회원아이디
  ,b_pwd varchar2(50) not null--회원 비밀번호
  ,b_name varchar2(50) not null --회원 이름
  ,b_birth varchar2(80) not null --회원 생일
  ,b_phone varchar2(80) not null --회원 폰번호
  ,b_addr varchar2(100) not null --회원 주소
  ,b_email varchar2(100) not null --회원 이메일
  ,b_date date
);

commit;

drop table B_mem;

select * from B_mem;

delete from B_mem;

--회원 정보 임시 저장 테이블
create table B_MemData(
   b_id varchar2(50) primary key --회원아이디
);
select * from b_memdata;

drop table B_MemData;

delete from b_memdata;

commit;
