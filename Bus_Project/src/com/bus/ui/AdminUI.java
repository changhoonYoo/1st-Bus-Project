package com.bus.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminUI extends JFrame implements ActionListener {

	private JPanel mainPan=new JPanel();//메인패널
	
	private Color mainColor;	//배경색
	
	private JButton manageMemBtn;	//회원 관리 버튼
	private JButton manageBusBtn;	//버스 관리 버튼
	private JButton logoutBtn;		//로그아웃 버튼
	private JButton manageCheckBtn;	//예약 내역 버튼
	private JLabel mainLabel;	//관리자 접속 성공
	
	AdminUI(){
		mainColor = new Color(166, 221, 248);
		mainPan.setLayout(null);
		mainPan.setBackground(mainColor);
		
		mainLabel=new JLabel("관리자 접속 성공");
		mainLabel.setFont(new Font("맑은 고딕",Font.BOLD,15));
		mainLabel.setBounds(25,12,250,30);
		
		manageMemBtn=new JButton("회원 관리");
		manageMemBtn.setBounds(10,60,375,100);
		manageMemBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		manageBusBtn=new JButton("버스 관리");
		manageBusBtn.setBounds(10,160,375,100);
		manageBusBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		manageCheckBtn=new JButton("예약 내역 확인");
		manageCheckBtn.setBounds(10, 260, 375, 100);
		manageCheckBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		logoutBtn=new JButton("로그아웃");
		logoutBtn.setFont(new Font("맑은 고딕",Font.BOLD,12));
		logoutBtn.setBounds(280,17,100,30);
		
		
		mainPan.add(mainLabel);
		mainPan.add(manageBusBtn);
		mainPan.add(manageMemBtn);
		mainPan.add(logoutBtn);
		mainPan.add(manageCheckBtn);
		add(mainPan);
		
		logoutBtn.addActionListener(this);
		manageMemBtn.addActionListener(this);
		manageBusBtn.addActionListener(this);
		manageCheckBtn.addActionListener(this);
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/admin.png");
		setIconImage(img);
		
		setTitle("관리자 메인");
		setSize(400, 400);
		setLocationRelativeTo(null);//창이 가운데 나오게 설정
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==manageMemBtn) {
			dispose();
			new AdminMemUI();
		}
		if(e.getSource()==manageBusBtn) {
			dispose();
			new AdminBusUI();
		}
		if(e.getSource()==manageCheckBtn) {
			dispose();
			new AdminCheckUI();
		}
		if(e.getSource()==logoutBtn) {
			dispose();
			new LoginUI();
		}
	}
	public static void main(String[] args) {
		new AdminUI();
	}

}
