package com.bus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.bus.vo.BusCheckVO;
import com.bus.vo.BusListVO;

public class BusCheckDAO {

	Connection con;//db연결 con
	Statement st;//쿼리문 수행 st
	PreparedStatement pt;//쿼리문 수행 pt
	ResultSet rs;//검색결과 레코드를 저장할 rs
	String sql = null;//쿼리문 저장변수
	BusListVO bvo = new BusListVO();

	//생성자에서 DB연결 => 반복적 코드를 조금 줄여준다.
	public BusCheckDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe"
					,"buspr","56789");
		}catch(ClassNotFoundException e) {
			System.out.println(e+"=>로드 fail");
		}catch(SQLException e) {
			System.out.println(e+"=>연결 fail");
		}
	}//생성자

	/* DB 닫기 기능 메서드  => 중복코드를 줄이는 효과가 발생 */

	public void dbClose() {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(pt != null) pt.close();
		}catch(Exception e) {
			System.out.println(e+"=>dbClose failed");
		}
	}

	public boolean[] seatCheck(BusListVO vo){

		boolean[] re = new boolean[25];
		for(int i = 0; i < re.length; i++) {
			re[i] = false;
		}

		String a = null;
		int b = 0;
		//boolean re = false;
		sql = "select bb_seat from b_busdata where bb_day = ? and bb_time = ? and bb_start = ? and bb_end = ?";
		try {
			pt=con.prepareStatement(sql);

			pt.setString(1, vo.getBus_day());
			pt.setString(2, vo.getBus_time());
			pt.setString(3, vo.getBus_start());
			pt.setString(4, vo.getBus_end());
			rs=pt.executeQuery();

			while(rs.next()) {
				a = rs.getString("bb_seat");
				for(int c = 0; c < re.length; c++) {

					if(c<4) { b = a.indexOf("A0"+(c+1));}
					else if(c<8) { b = a.indexOf("B0"+(c-3));}
					else if(c<12) {b = a.indexOf("C0"+(c-7));}
					else if(c<16) {b = a.indexOf("D0"+(c-11));}
					else if(c<20){b = a.indexOf("E0"+(c-15));}
					else {b = a.indexOf("F0"+(c-19));}

					if(b != -1) {
						re[c] = true;
					}
				}
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return re;

	}

	public void checkListAll(DefaultTableModel dt) {
		try {
			st=con.createStatement();//쿼리문을 수행할 st생성
			rs=st.executeQuery("select * from B_BusData order by bd_id asc");

			//DefaultTableModel에 있는 기존 데이터 지우기
			for(int i=0;i<dt.getRowCount();) {
				dt.removeRow(0);
			}
			while(rs.next()) {//다음 레코드 값이 있다면 참
				Object[] data= {
						rs.getString("bd_id"),
						rs.getString("bb_day"),
						rs.getString("bb_time"),
						rs.getString("bb_start"),
						rs.getString("bb_end"),
						rs.getString("bb_seat"),
						rs.getString("bb_price")
				};
				dt.addRow(data);//디폴트 테이블모델에 레코드 추가
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}

	public void getCheckId(DefaultTableModel dt, String id) {

		String sql="select * from B_BusData where bd_id='"+id.trim()+"'";

		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);

			for(int i=0;i<dt.getRowCount();) {
				dt.removeRow(0);
			}
			while(rs.next()) {
				Object[] data = {
						rs.getString("bb_day"),
						rs.getString("bb_time"),
						rs.getString("bb_start"),
						rs.getString("bb_end"),
						rs.getString("bb_seat"),
						rs.getString("bb_price")
				};
				dt.addRow(data);
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}

	public void getCheckSearch(DefaultTableModel dt, String combo, String text) {

		String sql="select * from B_BusData where "+combo.trim()+" like '%"+text.trim()+"%'";

		try {
			st=con.createStatement(); //쿼리문 수행 st생성
			rs=st.executeQuery(sql); //검색 쿼리문 수행 - 결과레코드를 rs에 저장

			for(int i=0;i<dt.getRowCount();) {
				dt.removeRow(0);
			}
			while(rs.next()) {
				Object[] data = {
						rs.getString("bd_id"),
						rs.getString("bb_day"),
						rs.getString("bb_time"),
						rs.getString("bb_start"),
						rs.getString("bb_end"),
						rs.getString("bb_seat"),
						rs.getString("bb_price")
				};
				dt.addRow(data);//디폴트테이블모델 에 레코드 추가
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}

	public int checkInsert(BusCheckVO bcvo) {
		int re=-1;
		try {
			pt=con.prepareStatement("insert into B_BusData values(?,?,?,?,?,?,?)");
			pt.setString(1, bcvo.getBd_id());
			pt.setString(2, bcvo.getBb_day());
			pt.setString(3, bcvo.getBb_time());
			pt.setString(4, bcvo.getBb_start());
			pt.setString(5, bcvo.getBb_end());
			pt.setString(6, bcvo.getBb_seat());
			pt.setInt(7, bcvo.getBb_price());

			re=pt.executeUpdate();

		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return re;
	}

	public void seatUpdate(int sum, String bb_seat, BusCheckVO bcvo) {

		int a = 0;
		int re = -1;

		if(bb_seat.length() == 4) { a = 1; }
		else if(bb_seat.length() == 8) { a = 2; }
		else if(bb_seat.length() == 12) { a = 3; }
		else if(bb_seat.length() == 16) { a = 4; }
		else if(bb_seat.length() == 20) { a = 5; }
		else if(bb_seat.length() == 24) { a = 6; }
		else if(bb_seat.length() == 28) { a = 7; }
		else if(bb_seat.length() == 32) { a = 8; }
		else if(bb_seat.length() == 36) { a = 9; }
		
		// int value = (sum - a);

		sql = "update buslist set bus_seat = ? where bus_day = ? and bus_time = ? and bus_start = ? and bus_end = ?";
		try {
			pt = con.prepareStatement(sql);

			pt.setInt(1, sum-a);
			pt.setString(2, bcvo.getBb_day());
			pt.setString(3, bcvo.getBb_time());
			pt.setString(4, bcvo.getBb_start());
			pt.setString(5, bcvo.getBb_end());
			
			re = pt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}

	public int seatCheck(BusCheckVO bcvo) {

		int a = 0;
		sql = "select bus_seat from buslist where bus_day = '"+bcvo.getBb_day()+"' and bus_time = '"+bcvo.getBb_time()+"' and "
				+ ""+"bus_start = '"+bcvo.getBb_start()+"' and bus_end = '"+bcvo.getBb_end()+"'";
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			

			if(rs.next()) {
				a = rs.getInt("bus_seat");
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		
		return a;
	}

	public int seatUpdate(String seat, String day, String time, String start, String end) {
		int a=0;
		return 0;
	}

	public int checkDelete(BusCheckVO bcvo) {
		int re=-1;

		try {
			pt=con.prepareStatement("delete from B_BusData where bd_id=? and bb_day=? and bb_time=?"
					+ " and bb_start=? and bb_end=? and bb_seat=? and bb_price=?");
			pt.setString(1, bcvo.getBd_id());
			pt.setString(2, bcvo.getBb_day());
			pt.setString(3, bcvo.getBb_time());
			pt.setString(4, bcvo.getBb_start());
			pt.setString(5, bcvo.getBb_end());
			pt.setString(6, bcvo.getBb_seat());
			pt.setInt(7, bcvo.getBb_price());
			re=pt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return re;
	}

	public void seatUpdatePlus(int sum, String bb_seat, BusCheckVO bcvo) {

		int a = 0;
		int re = -1;

		if(bb_seat.length() == 4) { a = 1; }
		else if(bb_seat.length() == 8) { a = 2; }
		else if(bb_seat.length() == 12) { a = 3; }
		else if(bb_seat.length() == 16) { a = 4; }
		else if(bb_seat.length() == 20) { a = 5; }
		else if(bb_seat.length() == 24) { a = 6; }
		else if(bb_seat.length() == 28) { a = 7; }
		else if(bb_seat.length() == 32) { a = 8; }
		else if(bb_seat.length() == 36) { a = 9; }
		
		// int value = (sum - a);

		sql = "update buslist set bus_seat = ? where bus_day = ? and bus_time = ? and bus_start = ? and bus_end = ?";
		try {
			pt = con.prepareStatement(sql);

			pt.setInt(1, sum+a);
			pt.setString(2, bcvo.getBb_day());
			pt.setString(3, bcvo.getBb_time());
			pt.setString(4, bcvo.getBb_start());
			pt.setString(5, bcvo.getBb_end());
			
			re = pt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}

	public boolean ticketCheck(String id) {
		boolean re = false;
		sql = "select * from B_BusData where bd_id='"+id+"'";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				re = true;
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return re;
	}
}
