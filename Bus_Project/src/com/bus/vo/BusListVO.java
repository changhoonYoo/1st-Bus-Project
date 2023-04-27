package com.bus.vo;

public class BusListVO {

	private String bus_day;//버스 타는 날짜
	private String bus_time;//버스 타는 시간
	private String bus_start;//출발 터미널
	private String bus_end;//도착 터미널
	private int bus_seat;//남은좌석 수
	private int bus_price;//가격
	private int bus_no;//버스 번호
	public String getBus_day() {
		return bus_day;
	}
	public void setBus_day(String bus_day) {
		this.bus_day = bus_day;
	}
	public String getBus_time() {
		return bus_time;
	}
	public void setBus_time(String bus_time) {
		this.bus_time = bus_time;
	}
	public String getBus_start() {
		return bus_start;
	}
	public void setBus_start(String bus_start) {
		this.bus_start = bus_start;
	}
	public String getBus_end() {
		return bus_end;
	}
	public void setBus_end(String bus_end) {
		this.bus_end = bus_end;
	}
	public int getBus_seat() {
		return bus_seat;
	}
	public void setBus_seat(int bus_seat) {
		this.bus_seat = bus_seat;
	}
	public int getBus_price() {
		return bus_price;
	}
	public void setBus_price(int bus_price) {
		this.bus_price = bus_price;
	}
	public int getBus_no() {
		return bus_no;
	}
	public void setBus_no(int bus_no) {
		this.bus_no = bus_no;
	}
	
}
