package com.bus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import com.bus.ui.TicketingMenuUI;
import com.bus.vo.BusListVO;

public class BusListDAO {
	String driver = "oracle.jdbc.OracleDriver";
	
	Connection con = null;//오라클 연결 참조변수
	PreparedStatement pt = null;//쿼리문 실행 참조변수
	Statement st = null;//쿼리문 실행 참조변수
	ResultSet rs = null;//select문 실행 후 결과 레코드를 rs에 저장
	String sql = null;//쿼리문 저장변수
	BusListVO bvo = new BusListVO();

	public BusListDAO() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "buspr", "56789");
		}catch(ClassNotFoundException e) {
			System.out.println(e + "=> 로드 fail");
		}catch(SQLException e) {
			System.out.println(e + "=> 연결 fail");
		}//try - catch
	}//생성자
	public void dbClose() {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(pt != null) pt.close();
		}catch(Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}
	
	public void busListAll(DefaultTableModel dt) {
		
		try {
			st = con.createStatement();
			sql = "select * from buslist order by bus_time asc";
			rs = st.executeQuery(sql);
			
			for(int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			
			while(rs.next()) {
				Object[] data = {
					rs.getString("bus_day"),
					rs.getString("bus_time"),
					rs.getString("bus_start"),
					rs.getString("bus_end"),
					rs.getString("bus_seat"),
					rs.getString("bus_price")
				};
				dt.addRow(data);
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}
	
	public void busListAll(DefaultTableModel dt, String v1, String v2) {
		try {
			st = con.createStatement();
			sql = "select * from buslist where "+v1.trim()+" = '"+v2.trim()+"'";
			rs = st.executeQuery(sql);
			
			for(int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			
			while(rs.next()) {
				Object[] data = {
					rs.getString("bus_day"),
					rs.getString("bus_time"),
					rs.getString("bus_start"),
					rs.getString("bus_end"),
					rs.getString("bus_seat"),
					rs.getString("bus_price")
				};
				dt.addRow(data);
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			//dbClose();
		}
	}
	
	public void busListAll(DefaultTableModel dt, String v1, String v2, String v3, String v4) {
		try {
			st = con.createStatement();
			sql = "select * from buslist where "+v1.trim()+" = '"+v2.trim()+"' and "+v3.trim()+" = '"+v4.trim()+"'";
			rs = st.executeQuery(sql);
			
			for(int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			
			while(rs.next()) {
				Object[] data = {
					rs.getString("bus_day"),
					rs.getString("bus_time"),
					rs.getString("bus_start"),
					rs.getString("bus_end"),
					rs.getString("bus_seat"),
					rs.getString("bus_price")
				};
				dt.addRow(data);
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}
	
	public void busListAll(DefaultTableModel dt, String v1, String v2, String v3, String v4, String v5, String v6) {
		try {
			st = con.createStatement();
			sql = "select * from buslist where "+v1.trim()+" = '"+v2.trim()+"' and "+v3.trim()+" = '"+v4.trim()+"' and "+v5.trim()+" = '"+v6.trim()+"'";
			rs = st.executeQuery(sql);
			
			for(int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			
			while(rs.next()) {
				Object[] data = {
					rs.getString("bus_day"),
					rs.getString("bus_time"),
					rs.getString("bus_start"),
					rs.getString("bus_end"),
					rs.getString("bus_seat"),
					rs.getString("bus_price")
				};
				dt.addRow(data);
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}
	
	public void busListAll(String v1, String v2, String v3, String v4, DefaultTableModel dt) {
		try {
			st = con.createStatement();
			sql = "select * from buslist where bus_day = '"+v1.trim()+"' and bus_time = '"+v2.trim()+"' and bus_start = '"+v3.trim()+"' and bus_end = '"+v4.trim()+"'";
			rs = st.executeQuery(sql);
			
			for(int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			
			while(rs.next()) {
				Object[] data = {
					rs.getString("bus_day"),
					rs.getString("bus_time"),
					rs.getString("bus_start"),
					rs.getString("bus_end"),
					rs.getString("bus_seat"),
					rs.getString("bus_price")
				};
				dt.addRow(data);
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}
}
	

