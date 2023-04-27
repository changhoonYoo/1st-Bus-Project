package com.bus.dao;

public class BusMake {

	public static void main(String[] args) {
		
		int a=2;
		int b=6;
		int c=3;
		for(int i=2;i<=8;i++) {
			String sql="insert into buslist values('2월 2"+a+"일','6 : 00','대구','부산',25,10000,bno_seqT.nextval);";
			System.out.println(sql);
			for(int j=1;j<=12;j++) {
				sql="insert into buslist values('2월 2"+a+"일','"+b+" : "+c+"0','대구','부산',25,10000,bno_seqT.nextval);";
				System.out.println(sql);
				b++;
				sql="insert into buslist values('2월 2"+a+"일','"+b+" : 00','대구','부산',25,10000,bno_seqT.nextval);";
				System.out.println(sql);
			}
			b=6;
			a++;
		}
	}
}
