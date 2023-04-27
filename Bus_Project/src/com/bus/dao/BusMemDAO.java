package com.bus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.bus.ui.NewMemUI;
import com.bus.vo.MemberVO;

 public class BusMemDAO {
	 
	 Connection con;// 오라클 연결 참조변수
		PreparedStatement pt;// 쿼리문 실행 참조변수
		Statement st;// 쿼리문 실행 참조변수
		ResultSet rs;// select문 실행 후 결과 레코드를 rs에 저장
		String sql;// 쿼리문 저장변수
		
		MemberVO m;
		
		public BusMemDAO() {
			
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


		public int newUserInsert(MemberVO m) { //멤버가입
			
       int re = -1;
			
			try {
				pt=con.prepareStatement("insert into B_mem values(?,?,?,?,?,?,?,sysdate)");
				pt.setString(1, m.getB_id());
				pt.setString(2, m.getB_pwd());
				pt.setString(3, m.getB_name());
				pt.setString(4, m.getB_birth());
				pt.setString(5, m.getB_phone());
				pt.setString(6, m.getB_addr());
				pt.setString(7, m.getB_email());
				
				re=pt.executeUpdate();
				
			}catch(Exception e) {e.printStackTrace();}
			finally {
				dbClose();
			}
			
			return re;
		}

		public boolean checkId(String id) { //id 중복체크
			
			boolean re = true;
			
			try {
				pt=con.prepareStatement("select * from B_mem where b_id=?");
				pt.setString(1, id);
				rs=pt.executeQuery();
				
				if(rs.next()) {
					re = false;
				}
			}catch(Exception e) {e.printStackTrace();}
			finally {
				dbClose();
			}
			return re;
		}

		public MemberVO memCheck(String id) { //로그인
			
			MemberVO m = null;
			
			try {
				pt=con.prepareStatement("select * from B_mem where b_id=?");
				pt.setString(1, id);
				rs=pt.executeQuery();
				if(rs.next()) {
					m = new MemberVO();
					m.setB_id(rs.getString("b_id"));
					m.setB_pwd(rs.getString("b_pwd"));
					m.setB_name(rs.getString("b_name"));
				}
			}catch(Exception e) {e.printStackTrace();}
			finally {
				dbClose();
			}
			return m;
		}
		
		// 마이페이지에서 수정한 이메일, 전화번호 넣기
		
		public int myPageUpdate(MemberVO mvo, String id) {
			int rs = -1; // 수정 실패시 반환값
			sql = "update B_mem set b_email =?, b_phone = ? where b_id = ?";
			try {
				pt = con.prepareStatement(sql);
				pt.setString(1, mvo.getB_email());
				pt.setString(2, mvo.getB_phone());
				pt.setString(3, id);
				rs = pt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return rs;
			
		}
		
		// 비밀번호 수정창에서 수정한 비밀번호 저장
		public int editPwd(MemberVO mvo, String id) {
			int rs = -1; // 수정 실패시 반환값
			sql = "update B_mem set b_pwd = ? where b_id = ?";
			try {
				pt = con.prepareStatement(sql);
				pt.setString(1, mvo.getB_pwd());
				pt.setString(2, id);
				rs = pt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return rs;
			
		}
		
		// 회원탈퇴 버튼 클릭시 해당 회원 삭제
		public int delId(MemberVO mvo) {
			int rs = -1;
			sql = "delete from B_mem where b_id = ? ";
			
			try {
				pt = con.prepareStatement(sql);
				pt.setString(1, mvo.getB_id());
				rs = pt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return rs;
		}

		public String pwdFind(String id, String name, String email) {
	         
	         String pwd = null;
	         try {
	            sql="select * from B_mem where b_id = '"+id+"' and b_name = '"+name+"' and b_email = '"+email+"'";
	         st=con.prepareStatement(sql);
	         rs=st.executeQuery(sql);
	         
	         if(rs.next()) {
	            pwd = rs.getString("b_pwd");
	         }
	         }catch(Exception e) {e.printStackTrace();}
	         finally {
	            dbClose();
	         }
	         return pwd;
	     }
	 
	 
	 public String idFind(String name, String pnum, String email) {
	      
	      String id = null;
	      
	      sql = "select * from B_mem where b_name = '"+name+"' and b_phone = '"+pnum+"' and b_email = '"+email+"'";
	      
	      try {
	         st = con.prepareStatement(sql);
	         rs = st.executeQuery(sql);
	         
	         if(rs.next()) {
	            id = rs.getString("b_id");
	         }
	         
	      }catch(Exception e) {e.printStackTrace();}
	      finally {
	         dbClose();
	      }
	      return id;
	   }
	}
		

	
	
	
	
