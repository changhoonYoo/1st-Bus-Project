package com.bus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bus.vo.MemberVO;

public class MyDataDAO {

	Connection con;// 오라클 연결 참조변수
	PreparedStatement pt;// 쿼리문 실행 참조변수
	Statement st;// 쿼리문 실행 참조변수
	ResultSet rs;// select문 실행 후 결과 레코드를 rs에 저장
	String sql;// 쿼리문 저장변수

	MemberVO m;

	public MyDataDAO() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","buspr","56789");
		}catch(ClassNotFoundException e) {
			System.out.println(e+"=>로드 fail");
		}catch(SQLException e) {
			System.out.println(e+"=>연결 fail");
		}
	}//생성자

	public void dbClose() {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(pt != null) pt.close();
		}catch(Exception e) {
			System.out.println(e+"=>dbClose Failed");
		}
	}//dbClose()

	public boolean checkData() {
		boolean re = false;
		sql = "select * from B_MemData";

		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery(sql);

			if(rs.next()) {
				re = true;
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}

		return re;
	}
	public MemberVO getMyData(String id) {

		MemberVO mvo = new MemberVO();
		sql = "select * from B_mem inner join B_MemData on B_mem.b_id = '"+id+"'";

		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery(sql);

			if(rs.next()) {
				mvo.setB_id(rs.getString("b_id"));
				mvo.setB_pwd(rs.getString("b_pwd"));
				mvo.setB_name(rs.getString("b_name"));
				mvo.setB_birth(rs.getString("b_birth"));
				mvo.setB_phone(rs.getString("b_phone"));
				mvo.setB_addr(rs.getString("b_addr"));
				mvo.setB_email(rs.getString("b_email"));
				mvo.setB_date(rs.getString("b_date"));

			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return mvo;
	}

	public MemberVO setMyId(MemberVO mvo) {

		sql = "insert into B_memdata values(?)";
		try {

			pt=con.prepareStatement(sql);
			pt.setString(1, mvo.getB_id());

			pt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		return mvo;
	}

	public void delData() {

		sql = "delete from B_MemData";

		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery(sql);
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
	}
	// 마이페이지에 쓸 회원정보 가져오기
	public String getMyId() {

		String a = null;
		sql = "select * from B_MemData";
		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				
				a = rs.getString("b_id");
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		
		return a;
	}

}
