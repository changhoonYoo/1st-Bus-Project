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

public class AdminMemUI extends JFrame implements ActionListener {

	private	JPanel btnPan;		//회원 추가,수정,삭제 버튼이 들어갈 패널
	private JPanel searchPan;	//검색 패널

	private JButton deleteBtn; 	//회원 삭제 버튼
	private JButton backBtn; 	//뒤로가기 버튼
	private JButton searchBtn; 	//검색 버튼

	private Color mainColor;	//배경색

	String[] word = {"전체","아이디","비밀번호","이름","생년월일","전화번호","주소","이메일","가입 날짜"};	//검색 콤보박스에 들어갈 목차
	private JComboBox searchCBox;	//검색 콤보박스
	private JTextField searchText;	//검색어 입력 필드

	String[] name = {"아이디","비밀번호","이름","생년월일","전화번호","주소","이메일","가입 날짜"};	//dt에 넣을 목차
	DefaultTableModel dt;
	JTable jt;
	JScrollPane jsp;

	AdminDAO dao=new AdminDAO();
	BusCheckDAO bcdao = new BusCheckDAO();

	AdminMemUI(){
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

		deleteBtn=new JButton("회원 삭제");
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

		dao.memListAll(dt);

		if(dt.getRowCount() > 0) {
			jt.setRowSelectionInterval(0, 0);
		}
		setTitle("관리자 회원 정보 관리");
		setSize(900,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			dispose();//창 하나만 사라짐
			new AdminUI();
		}else if(e.getSource() == deleteBtn) {
			int row=jt.getSelectedRow();//JTable 선택된 행번호 구해 row에 저장
			Object obj=jt.getValueAt(row, 0);//선택된 행의 아이디를 구함
			String id = (String) jt.getValueAt(row, 0);
			
			if(bcdao.ticketCheck(id) == true){
				AdminDAO.messageBox(this, "회원 삭제는 예매 내역 삭제후 가능합니다.");
			}else {
				if(dao.memDelete(obj.toString())>0) {
					AdminDAO.messageBox(this, "회원 삭제 완료!");
					dao.memListAll(dt);
				}
			}
		}else if(e.getSource() == searchBtn) {
			String combo =  searchCBox.getSelectedItem().toString();
			if(combo.trim().equals("전체")) {
				dao.memListAll(dt);
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("아이디")) {
				combo="b_id";
				dao.getMemSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("비밀번호")) {
				combo="b_pwd";
				dao.getMemSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("이름")) {
				combo="b_name";
				dao.getMemSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("생년월일")) {
				combo="b_birth";
				dao.getMemSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("전화번호")) {
				combo="b_phone";
				dao.getMemSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("주소")) {
				combo="b_addr";
				dao.getMemSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("이메일")) {
				combo="b_email";
				dao.getMemSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}else if(combo.trim().equals("가입 날짜")) {
				combo="b_date";
				dao.getMemSearch(dt, combo, searchText.getText());
				if(dt.getRowCount()>0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}
		}
	}

	public static void main(String[] args) {
		new AdminMemUI();
	}

}
