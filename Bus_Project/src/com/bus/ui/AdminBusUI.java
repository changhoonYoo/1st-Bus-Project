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

public class AdminBusUI extends JFrame implements ActionListener{
	
	private	JPanel btnPan;		//버스 추가,수정,삭제 버튼이 들어갈 패널
	private JPanel searchPan;	//검색 패널
	
	private JButton insertBtn; 	//버스 등록 버튼
	private JButton updateBtn; 	//버스 수정 버튼
	private JButton deleteBtn; 	//버스 삭제 버튼
	private JButton backBtn; 	//뒤로가기 버튼
	private JButton searchBtn; 	//검색 버튼
	
	private Color mainColor;	//배경색
	
	String[] word = {"전체","날짜","시간","출발터미널","도착터미널"};	//검색 콤보박스에 들어갈 목차
	private JComboBox searchCBox;	//검색 콤보박스
	private JTextField searchText;	//검색어 입력 필드
	
	//버스 정보 출력 테이블
	String[] name = {"날짜","시간","출발터미널","도착터미널","남은좌석","가격","버스번호"};	//dt에 넣을 목차
	DefaultTableModel dt;
	JTable jt;
	JScrollPane jsp;
	
	AdminDAO dao=new AdminDAO();
	
	AdminBusUI(){
		dt=new DefaultTableModel(name,0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
		         return false;
		      }
		};//셀 값 수정 못하게
		jt=new JTable(dt);
		jsp=new JScrollPane(jt);
		
		mainColor=new Color(166,221,248);
		
		btnPan=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
		btnPan.setBackground(mainColor);
		insertBtn=new JButton("버스 등록");
		insertBtn.setPreferredSize(new Dimension(100,40));
		updateBtn=new JButton("버스 수정");
		updateBtn.setPreferredSize(new Dimension(100,40));
		deleteBtn=new JButton("버스 삭제");
		deleteBtn.setPreferredSize(new Dimension(100,40));
		backBtn=new JButton("뒤로가기");
		backBtn.setPreferredSize(new Dimension(100,40));
		
		btnPan.add(insertBtn);
		btnPan.add(updateBtn);
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
		
		eventSet();
		
		dao.busListAll(dt);
		
		if(dt.getRowCount() > 0) {
			jt.setRowSelectionInterval(0, 0);
		}
		setTitle("관리자 버스 정보 관리");
		setSize(600,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
	}
	public void eventSet() {
		insertBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		backBtn.addActionListener(this);
		searchBtn.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {//로그아웃 버튼 클릭 시
			dispose();//창 하나만 사라짐
			new AdminUI();
		}else if(e.getSource() == insertBtn) {//버스 등록 버튼 클릭 시
			dispose();
			new AdminBusInUI();
		}else if(e.getSource() == updateBtn) {//버스 수정 버튼 클릭 시
			new AdminBusUpUI(this);
		}else if(e.getSource() == deleteBtn) {//버스 삭제 버튼 클릭 시
			int row=jt.getSelectedRow();//JTable 선택된 행번호 구해 row에 저장
			Object obj=jt.getValueAt(row, 6);//선택된 행의 버스번호를 구함
			if(dao.busDelete(Integer.parseInt(obj.toString()))>0) {//버스번호 문자열을 정수 숫자로 변경
				AdminDAO.messageBox(this, "버스 삭제 완료!");
				dao.busListAll(dt);
			}
		}else if(e.getSource() == searchBtn) {//버스 검색 버튼 클릭 시
			String combo = searchCBox.getSelectedItem().toString();
			if(combo.trim().equals("전체")) {//전체 검색 선택 시
				dao.busListAll(dt);
				if(dt.getRowCount()>0) {//가져올 값이 있다면
					jt.setRowSelectionInterval(0, 0);//첫번째열 선택된 상태
				}
			}else if(combo.trim().equals("날짜")) {
				combo="bus_day";
				dao.getBusSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("시간")) {
				combo="bus_time";
				dao.getBusSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("출발터미널")) {
				combo="bus_start";
				dao.getBusSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("도착터미널")) {
				combo="bus_end";
				dao.getBusSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(searchText.getText().trim().equals("")){
				AdminDAO.messageBox(this, "검색어를 입력하세요.");
				searchText.requestFocus();//검색 필드로 커서 이동
			}
		}
	}
	
}
