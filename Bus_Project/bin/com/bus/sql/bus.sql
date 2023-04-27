--버스테이블
create table buslist(
    bus_day varchar2(50) --날짜
    ,bus_time varchar2(50) --시간
    ,bus_start varchar2(50) --출발지
    ,bus_end varchar2(50) --도착지
    ,bus_seat number(38) --좌석번호
    ,bus_price number(38) --가격
    ,bus_no number(38) primary key --버스번호
);

select * from buslist;

commit;

drop table buslist;

delete from buslist;

--시퀀스
create sequence bno_seqT
start with 1
increment by 1
nocache;

drop sequence bno_seqT;

--버스 예매 목록 테이블
create table B_BusData(
     bd_id varchar2(50) --회원아이디
     ,bb_day varchar2(50)--출발날짜
     ,bb_time varchar2(50)--출발시간
     ,bb_start varchar2(50)--출발 터미널
     ,bb_end varchar2(50)--도착 터미널
     ,bb_seat varchar2(50)-- 선택한 좌석
     ,bb_price number(38)--결제한 금액
);

commit;

select * from B_BusData;

drop table b_busdata;

delete from b_busdata;