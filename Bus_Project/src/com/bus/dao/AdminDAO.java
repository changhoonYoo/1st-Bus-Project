package com.bus.dao;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.bus.ui.AdminBusInUI;
import com.bus.vo.BusListVO;



public class AdminDAO {
	
	
	Connection con;//db연결 con
	Statement st;//쿼리문 수행 st
	PreparedStatement ps;//쿼리문 수행 ps
	ResultSet rs;//검색결과 레코드를 저장할 rs
	
	//생성자에서 DB연결 => 반복적 코드를 조금 줄여준다.
	public AdminDAO() {
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
			if(ps != null) ps.close();
		}catch(Exception e) {
			System.out.println(e+"=>dbClose failed");
		}
	}//dbClose()
	
	//버스 레코드 조회
	public void busListAll(DefaultTableModel dt) { //버스 목록
		try {
			st=con.createStatement();//쿼리문을 수행할 st생성
			rs=st.executeQuery("select * from buslist order by bus_no asc");
			
			//DefaultTableModel에 있는 기존 데이터 지우기
			for(int i=0;i<dt.getRowCount();) {
				dt.removeRow(0);
			}
			while(rs.next()) {//다음 레코드 값이 있다면 참
				Object[] data= {
						rs.getString("bus_day"),
						rs.getString("bus_time"),
						rs.getString("bus_start"),
						rs.getString("bus_end"),
						rs.getString("bus_seat"),
						rs.getString("bus_price"),
						rs.getString("bus_no")
				};
				dt.addRow(data);//디폴트 테이블모델에 레코드 추가
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}

	public int busListInsert(BusListVO vo) {// 버스 등록
		int re=-1;
		
		try {
			ps=con.prepareStatement("insert into buslist values(?,?,?,?,?,?,bno_seqT.nextval)");
			ps.setString(1, vo.getBus_day());
			ps.setString(2, vo.getBus_time());
			ps.setString(3, vo.getBus_start());
			ps.setString(4, vo.getBus_end());
			ps.setInt(5, vo.getBus_seat());
			ps.setInt(6, vo.getBus_price());
			
			re=ps.executeUpdate();
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return re;
	}

	public int busListUpdate(BusListVO vo) {// 버스 수정
		int re=-1;
		
		try {
			ps=con.prepareStatement("update buslist set bus_day=?, bus_time=?, bus_start=?,"
					+ " bus_end=?, bus_seat=?, bus_price=? where bus_no=?");
			ps.setString(1, vo.getBus_day());
			ps.setString(2, vo.getBus_time());
			ps.setString(3, vo.getBus_start());
			ps.setString(4, vo.getBus_end());
			ps.setInt(5, vo.getBus_seat());
			ps.setInt(6, vo.getBus_price());
			ps.setInt(7, vo.getBus_no());
			
			re=ps.executeUpdate();
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return re;
	}

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}

	public int busDelete(int no) { //버스 삭제
		int re=-1;
		
		try {
			ps=con.prepareStatement("delete from buslist where bus_no=?");
			ps.setInt(1, no);
			re=ps.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return re;
	}

	public void getBusSearch(DefaultTableModel dt, String combo, String text) { //버스 검색
		
		String sql="select * from buslist where "+combo.trim()+" like '%"+text.trim()+"%'";
		
		try {
			st=con.createStatement(); //쿼리문 수행 st생성
			rs=st.executeQuery(sql); //검색 쿼리문 수행 - 결과레코드를 rs에 저장
			
			for(int i=0;i<dt.getRowCount();) {
				dt.removeRow(0);
			}
			while(rs.next()) {
				Object[] data = {
					rs.getString("bus_day"),
					rs.getString("bus_time"),
					rs.getString("bus_start"),
					rs.getString("bus_end"),
					rs.getString("bus_seat"),
					rs.getString("bus_price"),
					rs.getString("bus_no")
				};
				dt.addRow(data);//디폴트테이블모델 에 레코드 추가
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}

	public void memListAll(DefaultTableModel dt) { //회원 목록
		try {
			String sql;
			st = con.createStatement();
			sql = "select * from B_mem order by b_date asc";
			rs = st.executeQuery(sql);
			
			for(int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			
			while(rs.next()) {
				Object[] data = {
					rs.getString("b_id"),
					rs.getString("b_pwd"),
					rs.getString("b_name"),
					rs.getString("b_birth"),
					rs.getString("b_phone"),
					rs.getString("b_addr"),
					rs.getString("b_email"),
					rs.getString("b_date")
				};
				dt.addRow(data);
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}

	public int memDelete(String id) {	//회원 삭제
	int re=-1;
		
		try {
			ps=con.prepareStatement("delete from B_mem where b_id=?");
			ps.setString(1, id);
			re=ps.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return re;
	}

	public void getMemSearch(DefaultTableModel dt, String combo, String text) { //회원 검색
		
		String sql="select * from B_mem where "+combo.trim()+" like '%"+text.trim()+"%'";
		
			try {
				st=con.createStatement();
				rs=st.executeQuery(sql);
				
				for(int i=0;i<dt.getRowCount();) {
					dt.removeRow(0);
				}
				while(rs.next()) {
					Object[] data = {
						rs.getString("b_id"),
						rs.getString("b_pwd"),
						rs.getString("b_name"),
						rs.getString("b_birth"),
						rs.getString("b_phone"),
						rs.getString("b_addr"),
						rs.getString("b_email"),
						rs.getString("b_date")
					};
					dt.addRow(data);
				}
			}catch(Exception e) {e.printStackTrace();}
			finally {
				dbClose();
		}
	}
}
