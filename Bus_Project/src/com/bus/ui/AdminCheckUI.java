package com.bus.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.bus.dao.AdminDAO;
import com.bus.dao.BusCheckDAO;
import com.bus.dao.MyDataDAO;
import com.bus.vo.BusCheckVO;
import com.bus.vo.MemberVO;

public class AdminCheckUI extends JFrame implements ActionListener {
	private	JPanel btnPan;		//버스 추가,수정,삭제 버튼이 들어갈 패널
	private JPanel searchPan;	//검색 패널
	
	private JButton deleteBtn; 	//회원 삭제 버튼
	private JButton backBtn; 	//뒤로가기 버튼
	private JButton searchBtn; 	//검색 버튼
	
	private Color mainColor;	//배경색
	
	String[] word = {"전체","아이디","날짜","시간","출발터미널","도착터미널","좌석번호","결제금액"};	//검색 콤보박스에 들어갈 목차
	private JComboBox searchCBox;	//검색 콤보박스
	private JTextField searchText;	//검색어 입력 필드
	
	String[] name = {"아이디","날짜","시간","출발터미널","도착터미널","좌석번호","결제금액"};	//dt에 넣을 목차
	DefaultTableModel dt;
	JTable jt;
	JScrollPane jsp;
	
	BusCheckVO bcvo=new BusCheckVO();
	BusCheckDAO bcdao=new BusCheckDAO();
	
	MyDataDAO mydao = new MyDataDAO();
	String id = mydao.getMyId();//정보 테이블에 있는 아이디를 id에 저장
	MemberVO mvo = mydao.getMyData(id);//정보 테이블과 회원테이블을 조인해서 얻은 데이터를 mvo에 저장
	
	AdminCheckUI(){
		dt=new DefaultTableModel(name,0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
		         return false;
		      }
		};//셀 값 수정 못하게
		jt=new JTable(dt);
		jsp=new JScrollPane(jt);
		
		mainColor=new Color(166,221,248);
		
		btnPan=new JPanel(new FlowLayout(FlowLayout.CENTER,80,20));
		btnPan.setBackground(mainColor);
		
		deleteBtn=new JButton("예매 취소");
		deleteBtn.setPreferredSize(new Dimension(100,40));
		backBtn=new JButton("뒤로가기");
		backBtn.setPreferredSize(new Dimension(100,40));
		
		btnPan.add(deleteBtn);
		btnPan.add(backBtn);
		
		searchPan=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
		searchPan.setBackground(mainColor);
		searchCBox=new JComboBox(word);
		searchText=new JTextField(20);
		searchBtn=new JButton("검색");
		
		searchPan.add(searchCBox);
		searchPan.add(searchText);
		searchPan.add(searchBtn);
		
		add(btnPan,"North");
		add(jsp,"Center");
		add(searchPan,"South");
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/admin.png");
		setIconImage(img);
		
		deleteBtn.addActionListener(this);
		backBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		
		bcdao.checkListAll(dt);
		
		if(dt.getRowCount() > 0) {
			jt.setRowSelectionInterval(0, 0);
		}
		setTitle("회원 예매 정보 관리");
		setSize(900,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	public static void main(String[] args) {
		new AdminCheckUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			dispose();//창 하나만 사라짐
			new AdminUI();
		}else if(e.getSource() == deleteBtn) {
			int row=jt.getSelectedRow();//JTable 선택된 행번호 구해 row에 저장
			
			bcvo.setBd_id(jt.getValueAt(row, 0).toString());
			bcvo.setBb_day(jt.getValueAt(row, 1).toString());
			bcvo.setBb_time(jt.getValueAt(row, 2).toString());
			bcvo.setBb_start(jt.getValueAt(row, 3).toString());
			bcvo.setBb_end(jt.getValueAt(row, 4).toString());
			bcvo.setBb_seat(jt.getValueAt(row, 5).toString());
			bcvo.setBb_price(Integer.parseInt(jt.getValueAt(row, 6).toString()));
			
			if(bcdao.checkDelete(bcvo)>0) {
				AdminDAO.messageBox(this, "예매 취소 완료!");
				
				int a= bcdao.seatCheck(bcvo);
				bcdao.seatUpdatePlus(a,bcvo.getBb_seat(),bcvo);
				bcdao.checkListAll(dt);
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}
		}else if(e.getSource() == searchBtn) {
			String combo =  searchCBox.getSelectedItem().toString();
			if(combo.trim().equals("전체")) {
				bcdao.checkListAll(dt);
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}
			else if(combo.trim().equals("아이디")) {
				combo="bd_id";
				bcdao.getCheckSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("날짜")) {
				combo="bb_day";
				bcdao.getCheckSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("시간")) {
				combo="bb_time";
				bcdao.getCheckSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("출발터미널")) {
				combo="bb_start";
				bcdao.getCheckSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("도착터미널")) {
				combo="bb_end";
				bcdao.getCheckSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("좌석번호")) {
					combo="bb_seat";
					bcdao.getCheckSearch(dt, combo, searchText.getText());
					if(dt.getRowCount()>0) {
						jt.setRowSelectionInterval(0, 0);
					}
			}else if(combo.trim().equals("결제금액")) {
				combo="bb_price";
				bcdao.getCheckSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}
		}
	}
}
